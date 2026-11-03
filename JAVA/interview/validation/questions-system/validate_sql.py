#!/usr/bin/env python3
"""Run four published PostgreSQL question examples on a disposable /tmp cluster.

Only a fresh generated schema is modified; no TCP or external DB is supported.
The caller owns cluster setup/shutdown. Requires Python 3.9+ and local psql.
"""
import argparse
import os
from pathlib import Path
import pwd
import re
import subprocess
import tempfile
import uuid

HERE = Path(__file__).resolve().parent
BANK = HERE.parents[1] / '12-Interview-Question-Bank' / 'Database_Questions.md'

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--socket-dir', required=True)
    parser.add_argument('--port', type=int, default=55440)
    args = parser.parse_args()
    socket_dir = Path(args.socket_dir).resolve()
    temp_root = Path(tempfile.gettempdir()).resolve()
    if socket_dir == temp_root or not socket_dir.is_relative_to(temp_root):
        raise SystemExit('Refusing a socket outside a dedicated temporary directory.')
    if not 1 <= args.port <= 65535:
        raise SystemExit('Invalid port.')
    env = {
        'PATH': '/usr/bin:/bin', 'PGHOST': str(socket_dir),
        'PGPORT': str(args.port), 'PGDATABASE': 'postgres',
        'PGUSER': pwd.getpwuid(os.getuid()).pw_name, 'PGCONNECT_TIMEOUT': '3',
    }
    command = ['psql', '-X', '-q', '-A', '-t', '-F', '|',
               '-v', 'ON_ERROR_STOP=1', '-v', 'VERBOSITY=verbose']
    checks = 0
    def run(sql, expect_state=None):
        result = subprocess.run(command, input=sql, text=True, capture_output=True,
                                env=env, timeout=8)
        if expect_state:
            if result.returncode == 0 or expect_state not in result.stderr:
                raise AssertionError((expect_state, result.stdout, result.stderr))
        elif result.returncode:
            raise AssertionError(result.stderr)
        return result.stdout.strip()
    def equal(actual, expected, label):
        nonlocal checks
        if actual != expected:
            raise AssertionError((label, actual, expected))
        checks += 1
        print('PASS:', label)
    def rejects(sql, state, label):
        run(sql, expect_state=state)
        nonlocal checks
        checks += 1
        print('PASS:', label)
    actual_dir = Path(run('SHOW data_directory;')).resolve()
    if actual_dir != socket_dir / 'data':
        raise SystemExit('Refusing a database outside the disposable socket directory.')
    print('Runtime:', run('SHOW server_version;'))
    examples = dict(re.findall(
        r'<!-- phase5-sql: (QB-DB-\d{3}) -->\s*```sql\n(.*?)```',
        BANK.read_text(), re.S))
    equal(sorted(examples), ['QB-DB-001','QB-DB-002','QB-DB-003','QB-DB-004'],
          'exactly four marked SQL blocks extracted from the published bank')
    schema = 'phase5_system_' + uuid.uuid4().hex
    run('CREATE SCHEMA ' + schema + ';')
    env['PGOPTIONS'] = ('-c search_path=' + schema +
                        ' -c statement_timeout=5000 -c lock_timeout=2000 -c timezone=UTC')
    try:
        run('''CREATE TABLE invoices (id bigint PRIMARY KEY, tenant_id bigint NOT NULL,
                    cents bigint NOT NULL);
               CREATE TABLE tags (invoice_id bigint REFERENCES invoices(id), label text);
               INSERT INTO invoices VALUES (10,1,100),(11,1,100),(12,2,999);
               INSERT INTO tags VALUES (10,'a'),(10,'b'),(12,'a');
               CREATE TABLE device_events (id bigint PRIMARY KEY, device_id text NOT NULL,
                    observed_at timestamptz NOT NULL, state text NOT NULL);
               INSERT INTO device_events VALUES
                 (1,'A','2026-11-02 10:00Z','boot'),
                 (2,'A','2026-11-02 10:01Z','up'),
                 (3,'A','2026-11-02 10:01Z','down'),
                 (4,'B','2026-11-02 10:02Z','up');
               CREATE TABLE reviews (id bigint PRIMARY KEY, approved boolean);
               INSERT INTO reviews VALUES (1,true),(2,false),(3,NULL);
               CREATE TABLE aliases (id bigint PRIMARY KEY, tenant_id bigint NOT NULL,
                    alias text COLLATE "C" NOT NULL, active boolean NOT NULL);''')
        equal(run(examples['QB-DB-001']), '200|1', 'invoice grain survives tag fan-out and equal amounts')
        run('DELETE FROM tags WHERE invoice_id IN (10,11); DELETE FROM invoices WHERE tenant_id=1;')
        equal(run(examples['QB-DB-001']), '0|0', 'tenant without invoices retains zero aggregate')
        equal(run(examples['QB-DB-002']), 'A|3|down\nB|4|up', 'latest event ties resolve by descending ID')
        run('DELETE FROM device_events;')
        equal(run(examples['QB-DB-002']), '', 'empty event relation returns no fabricated devices')
        run("INSERT INTO device_events VALUES(8,'C','2026-11-02 11:00Z','up');")
        equal(run(examples['QB-DB-002']), 'C|8|up', 'single-event partition survives ranking')
        equal(run(examples['QB-DB-003']), '2\n3', 'queue includes false and unknown approvals')
        run('UPDATE reviews SET approved=NULL;')
        equal(run(examples['QB-DB-003']), '1\n2\n3', 'all unknown approvals remain queued')
        run('DELETE FROM reviews;')
        equal(run(examples['QB-DB-003']), '', 'empty review queue stays empty')
        run(examples['QB-DB-004'])
        run("INSERT INTO aliases VALUES(1,1,'help',true),(2,1,'help',false),(3,2,'help',true);")
        equal(run('SELECT count(*) FROM aliases;'), '3', 'inactive history and other-tenant alias accepted')
        rejects("INSERT INTO aliases VALUES(4,1,'help',true);", '23505', 'duplicate active alias rejected')
        rejects('UPDATE aliases SET active=true WHERE id=2;', '23505', 'activation into conflict rejected')
        run("INSERT INTO aliases VALUES(5,1,'HELP',true);")
        equal(run('SELECT count(*) FROM aliases WHERE active;'), '3', 'explicit case-sensitive name policy')
        run('BEGIN; UPDATE aliases SET active=false WHERE id=1; UPDATE aliases SET active=true WHERE id=2; COMMIT;')
        equal(run("SELECT id FROM aliases WHERE tenant_id=1 AND alias='help' AND active;"), '2',
              'retire then activate in one transaction')
        rejects("INSERT INTO aliases VALUES(6,1,'other',NULL);", '23502', 'NOT NULL prevents unknown active flag')
        print(f'PASS: {checks} checks; no concurrency or performance claim.')
    finally:
        run('DROP SCHEMA ' + schema + ' CASCADE;')

if __name__ == '__main__':
    main()
