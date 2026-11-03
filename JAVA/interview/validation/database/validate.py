#!/usr/bin/env python3
"""Check teaching examples on an explicitly disposable PostgreSQL Unix socket.

Requires only Python's standard library and an installed psql. This script
refuses non-/tmp socket paths and servers whose data_directory differs from
<socket-dir>/data. It creates a uniquely named schema and removes only that
schema. It does not initialize/start/stop PostgreSQL or connect using TCP.
"""
import argparse
import os
from pathlib import Path
import pwd
import re
import signal
import subprocess
import tempfile
import uuid

HERE = Path(__file__).resolve().parent
ROOT = HERE.parents[1]
parser = argparse.ArgumentParser()
parser.add_argument('--socket-dir', required=True)
parser.add_argument('--port', default='55439', type=int)
args = parser.parse_args()
socket_dir = Path(args.socket_dir).resolve()
if not socket_dir.is_relative_to(Path(tempfile.gettempdir()).resolve()):
    raise SystemExit('Refusing a socket outside the temporary directory.')
if not 1 <= args.port <= 65535:
    raise SystemExit('Invalid port.')
schema = 'interview_validation_' + uuid.uuid4().hex
base_env = {
    'PATH': '/usr/bin:/bin',
    'PGHOST': str(socket_dir),
    'PGPORT': str(args.port),
    'PGDATABASE': 'postgres',
    'PGUSER': pwd.getpwuid(os.getuid()).pw_name,
    'PGCONNECT_TIMEOUT': '3',
}
PSQL = ['psql', '-X', '-q', '-A', '-t', '-F', '|', '-P', 'null=<NULL>']
checks = 0
sessions = []

def run(sql, *, allow_error=False):
    result = subprocess.run(
        PSQL + ['-v', 'ON_ERROR_STOP=1'], input=sql, text=True,
        capture_output=True, env=base_env, timeout=8,
    )
    if not allow_error and result.returncode:
        raise AssertionError(result.stderr)
    return result

def equal(actual, expected, description):
    global checks
    assert actual == expected, (description, actual, expected)
    checks += 1
    print('PASS:', description, flush=True)

class Session:
    def __init__(self):
        self.process = subprocess.Popen(
            PSQL + ['-v', 'ON_ERROR_STOP=0'], stdin=subprocess.PIPE,
            stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
            text=True, bufsize=1, env=base_env,
        )
        sessions.append(self)

    def execute(self, sql):
        marker = '__END_' + uuid.uuid4().hex
        self.process.stdin.write(sql + '\n\\echo ' + marker + ' :SQLSTATE\n')
        self.process.stdin.flush()
        lines = []
        while True:
            line = self.process.stdout.readline()
            if not line:
                raise AssertionError('psql exited before completion: ' + repr(lines))
            line = line.rstrip('\n')
            if line.startswith(marker + ' '):
                return '\n'.join(lines), line.split()[-1]
            lines.append(line)

    def ok(self, sql):
        output, state = self.execute(sql)
        if state != '00000':
            raise AssertionError((sql, state, output))
        return output

    def close(self):
        if self.process.poll() is None:
            try:
                self.process.communicate('ROLLBACK;\n\\q\n', timeout=3)
            except subprocess.TimeoutExpired:
                self.process.kill()
                self.process.communicate()

# Bound interactive checks too; no unbounded waits if an assertion/lock fails.
def timed_out(signum, frame):
    raise TimeoutError('Validation exceeded 45 seconds.')
signal.signal(signal.SIGALRM, timed_out)
signal.alarm(45)
actual_dir = Path(run('SHOW data_directory;').stdout.strip()).resolve()
if actual_dir != socket_dir / 'data':
    raise SystemExit('Refusing server not owned by this disposable directory.')
version = run('SHOW server_version;').stdout.strip()
print('Runtime:', version)
run('CREATE SCHEMA ' + schema + ';')
base_env['PGOPTIONS'] = (
    '-c search_path=' + schema +
    ' -c statement_timeout=5000 -c lock_timeout=2000 -c timezone=UTC'
)
try:
    run((HERE / 'fixture.sql').read_text())
    examples = {}
    for name in ['SQL.md', 'Database_Indexing.md', 'Query_Optimization.md',
                 'Transactions_and_ACID.md', 'API_Design.md']:
        text = (ROOT / '05-Database-and-Backend' / name).read_text()
        for block in re.findall(r'```sql\n(.*?)```', text, re.S):
            match = re.match(r'-- example: (\w+)\n', block)
            if match:
                examples[match.group(1)] = block
    equal(sorted(examples), sorted([
        'paid_totals', 'no_paid_orders', 'top_paid_order', 'running_paid_total',
        'customer_history', 'history_index', 'history_plan', 'reserve_inventory',
        'cursor_page']), 'all nine marked SQL blocks extracted from the actual Markdown')
    equal(run(examples['paid_totals']).stdout.strip(),
          '1|Ada|2|2000\n2|Lin|1|2000\n3|Mira|1|500\n4|NoOrders|0|0',
          'left join, aggregate and zero-order customer')
    equal(run(examples['no_paid_orders']).stdout.strip(), '4', 'NOT EXISTS anti-join')
    equal(run('SELECT 2 NOT IN (1, NULL);').stdout.strip(), '<NULL>', 'nullable NOT IN trap')
    equal(run(examples['top_paid_order']).stdout.strip(),
          '1|101|1200\n2|104|2000\n3|106|500', 'top-1 window partitioning')
    equal(run(examples['running_paid_total']).stdout.strip(),
          '101|1200\n102|2000', 'explicit ROWS running frame')
    equal([line.split('|')[0] for line in run(examples['customer_history']).stdout.strip().splitlines()],
          ['102', '101'], 'stable descending history')
    run(examples['history_index'])
    checks += 1
    print('PASS: partial covering index DDL')
    equal(run(examples['cursor_page']).stdout.strip().split('|')[0], '101', 'keyset cursor boundary')
    plan = run(examples['history_plan']).stdout
    equal('Execution Time:' in plan, True, 'EXPLAIN ANALYZE read-only statement executes')
    equal(run(examples['reserve_inventory']).stdout.strip(), '0', 'first guarded decrement succeeds')
    equal(run(examples['reserve_inventory']).stdout.strip(), '', 'second guarded decrement refuses')
    equal(run('UPDATE inventory SET stock=-1 WHERE product_id=1;', allow_error=True).returncode != 0,
          True, 'database stock constraint rejects negative value')
    equal(run('INSERT INTO orders VALUES (107,999,1,\'paid\',now());', allow_error=True).returncode != 0,
          True, 'foreign key rejects nonexistent customer')
    run('UPDATE accounts SET balance=100;')
    # Statements are deliberately synchronized; no sleep-based race assumptions.
    a, b = Session(), Session()
    a.ok('BEGIN ISOLATION LEVEL READ COMMITTED;')
    equal(a.ok('SELECT balance FROM accounts WHERE id=1;'), '100', 'RC initial read')
    b.ok('UPDATE accounts SET balance=80 WHERE id=1;')
    equal(a.ok('SELECT balance FROM accounts WHERE id=1;'), '80', 'RC next statement sees commit')
    a.ok('ROLLBACK;')
    run('UPDATE accounts SET balance=100;')
    a.ok('BEGIN ISOLATION LEVEL REPEATABLE READ;')
    equal(a.ok('SELECT balance FROM accounts WHERE id=1;'), '100', 'RR establishes initial snapshot')
    b.ok('UPDATE accounts SET balance=80 WHERE id=1;')
    equal(a.ok('SELECT balance FROM accounts WHERE id=1;'), '100', 'RR reread retains snapshot')
    a.ok('ROLLBACK;')
    # The stale-constant schedule from the teaching chapter.
    run('UPDATE accounts SET balance=100;')
    a.ok('BEGIN;'); b.ok('BEGIN;')
    equal((a.ok('SELECT balance FROM accounts WHERE id=1;'),
           b.ok('SELECT balance FROM accounts WHERE id=1;')), ('100', '100'),
          'both stale-calculation transactions read 100')
    a.ok('UPDATE accounts SET balance=90 WHERE id=1;'); a.ok('COMMIT;')
    b.ok('UPDATE accounts SET balance=80 WHERE id=1;'); b.ok('COMMIT;')
    equal(run('SELECT balance FROM accounts;').stdout.strip(), '80', 'lost update ends at 80, not 70')
    # Guarded arithmetic handles successive committed requests correctly.
    run('UPDATE accounts SET balance=100;')
    a.ok('UPDATE accounts SET balance=balance-10 WHERE id=1 AND balance>=10;')
    b.ok('UPDATE accounts SET balance=balance-20 WHERE id=1 AND balance>=20;')
    equal(run('SELECT balance FROM accounts;').stdout.strip(), '70', 'relative arithmetic preserves both debits')
    for isolation, expected in [('REPEATABLE READ', '0'), ('SERIALIZABLE', '1')]:
        run('UPDATE doctors SET on_call=TRUE;')
        a.ok('BEGIN ISOLATION LEVEL ' + isolation + ';')
        b.ok('BEGIN ISOLATION LEVEL ' + isolation + ';')
        equal((a.ok('SELECT COUNT(*) FROM doctors WHERE on_call;'),
               b.ok('SELECT COUNT(*) FROM doctors WHERE on_call;')), ('2', '2'),
              isolation + ': both read two on-call doctors')
        a.ok('UPDATE doctors SET on_call=FALSE WHERE id=1;')
        b.ok('UPDATE doctors SET on_call=FALSE WHERE id=2;')
        a.ok('COMMIT;')
        output, state = b.execute('COMMIT;')
        equal(state, '00000' if isolation == 'REPEATABLE READ' else '40001',
              isolation + ': expected second commit outcome')
        equal(run('SELECT COUNT(*) FROM doctors WHERE on_call;').stdout.strip(), expected,
              isolation + ': final on-call invariant')
        b.ok('ROLLBACK;')
    # Retry the failed unit from fresh reads; candidate decision now changes.
    b.ok('BEGIN ISOLATION LEVEL SERIALIZABLE;')
    equal(b.ok('SELECT COUNT(*) FROM doctors WHERE on_call;'), '1', 'retry sees only one doctor')
    b.ok('COMMIT;')
    print(f'PASS: {checks} checks; all marked SQL examples executed natively.')
finally:
    for session in sessions:
        session.close()
    run('DROP SCHEMA ' + schema + ' CASCADE;')
    signal.alarm(0)
