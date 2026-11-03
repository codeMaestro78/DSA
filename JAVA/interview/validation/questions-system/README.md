# Phase 5 SQL fixture validation

## Observed status

**2026-11-03: native execution is blocked and no SQL checks are claimed passed.** The first disposable PostgreSQL start inside the sandbox failed (`pg_ctl: could not start server`). A required escalation request to permit its private Unix socket was interrupted before execution approval completed. No successful server start or SQL output was observed. The prepared runner was syntax-checked only. Previously executed Phase 2 PostgreSQL checks cover different examples and do not validate these new queries.

## Prepared checks

[validate_sql.py](validate_sql.py) extracts the four marked SQL blocks directly from [Database questions](../../12-Interview-Question-Bank/Database_Questions.md), rather than maintaining separate query copies. Its 15 assertions are prepared for invoice join fan-out and empty aggregates; latest-event ties, empty and single-row input; true/false/NULL queue filters; partial unique index rejection of duplicate insertion/conflicting activation; permitted inactive/other-tenant aliases; explicit case-sensitive equality; retire/activate sequence; and NOT NULL rejection. This is a planned fixture suite, not an observed result.

The runner uses only Python standard library and installed `psql`. It refuses non-temporary socket paths and any server whose data_directory is not exactly `<socket-dir>/data`, creates a fresh generated schema, and drops that schema in a finally block. No shared database should be used. Tests are not a performance benchmark or a concurrent-insert stress proof; new prose transaction schedules remain reasoned examples.

## Reproduce after local socket permission is available

Installed binaries found at `/usr/lib/postgresql/16/bin`; documentation references PostgreSQL 18. No version-18-specific feature or plan behavior is exercised. Use a new private temporary directory, disable TCP listening, and always stop the server. Run this block in Bash from the repository root:

```bash
set -eu
task_pg_bin=/usr/lib/postgresql/16/bin
task_pg_dir="$(mktemp -d /tmp/interview-phase5-pg-XXXXXX)"
"$task_pg_bin/initdb" -D "$task_pg_dir/data" -A trust --no-locale -E UTF8
"$task_pg_bin/pg_ctl" -D "$task_pg_dir/data" -l "$task_pg_dir/server.log" \
  -o "-k $task_pg_dir -h '' -p 55440 -c max_connections=10 -c shared_buffers=16MB" start
trap '"$task_pg_bin/pg_ctl" -D "$task_pg_dir/data" stop -m fast' EXIT
python3 interview/validation/questions-system/validate_sql.py \
  --socket-dir "$task_pg_dir" --port 55440
```

`trust` authentication is restricted to this disposable cluster's private Unix socket with TCP disabled; it is not production authentication guidance. Each psql call has an eight-second process timeout plus database statement/lock timeouts. No dependency downloads or cloud operations are involved.
