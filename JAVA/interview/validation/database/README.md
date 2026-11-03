# Native PostgreSQL validation

## What was executed

On **2026-11-02**, `validate.py` passed **28 checks** on **PostgreSQL 16.15 (Ubuntu 16.15-0ubuntu0.24.04.1)**. A new cluster under `/tmp` used a private Unix-domain socket, TCP listening disabled, dummy data and a uniquely named schema removed after testing. No shared or external database was used. After testing, `pg_ctl stop -m fast` completed and the server log confirmed “database system is shut down.” The socket required sandbox approval; successful native execution is recorded here rather than inferred from static SQL inspection.

The runner extracts all **nine marked SQL blocks from the actual Markdown** in the database chapter directory, so the queries tested are the published examples rather than separately maintained copies. It checks:

- Paid-order left-join aggregates, including the zero-order customer.
- `NOT EXISTS`, nullable `NOT IN`, ranking and an explicit running-sum window frame.
- Stable history ordering, partial covering-index DDL, keyset cursor results and a read-only `EXPLAIN ANALYZE` statement.
- Guarded inventory decrement and rejection after stock reaches zero; negative-stock and invalid foreign-key constraints.
- Synchronized two-session Read Committed rereads versus Repeatable Read snapshot stability.
- A deliberately unsafe stale-constant lost update, followed by correct relative arithmetic over successive committed debits.
- Two-doctor write skew committing at Repeatable Read, and the corresponding Serializable schedule rejecting the second commit with SQLSTATE `40001`.
- A new transaction rereading the surviving on-call doctor after the serialization failure. This demonstrates why the decision must be recomputed; it is not an implementation of a general application retry library.

The schedules use explicit statement completion markers, not sleep-based timing assumptions. All checks are bounded by statement/lock timeouts and a 45-second runner deadline.

## Files

- [Fixture schema and dummy data](fixture.sql).
- [Standard-library validation runner](validate.py).
- [Published SQL chapter](../../05-Database-and-Backend/SQL.md).
- [Published isolation chapter](../../05-Database-and-Backend/Isolation_Levels.md).

## Reproduce on a disposable cluster

Prerequisites: locally installed `initdb`, `pg_ctl`, `psql`, Python 3.9+ on Linux and permission to create a private local socket. Do not point this exercise at a real application's server. Executable locations vary by installation.

The commands below match the PostgreSQL 16 layout used here. Change `task_pg_bin` to the locally installed server-binary directory if necessary. Run the block in Bash; it traps exit to stop the disposable server even if validation fails. No external dependencies are downloaded.

```bash
set -eu
task_pg_bin=/usr/lib/postgresql/16/bin
task_pg_dir="$(mktemp -d /tmp/interview-pg-XXXXXX)"
"$task_pg_bin/initdb" -D "$task_pg_dir/data" -A trust --no-locale -E UTF8
"$task_pg_bin/pg_ctl" -D "$task_pg_dir/data" -l "$task_pg_dir/server.log" \
  -o "-k $task_pg_dir -h '' -p 55439 -c max_connections=10 -c shared_buffers=16MB" start
trap '"$task_pg_bin/pg_ctl" -D "$task_pg_dir/data" stop -m fast' EXIT
python3 interview/validation/database/validate.py \
  --socket-dir "$task_pg_dir" --port 55439
```

`mktemp -d` creates a private directory. `trust` authentication is used only for this throwaway local cluster, with TCP listening disabled and the socket in that private directory; it is not a production authentication recommendation. The trap stops the server; inspect or remove the temporary directory separately when no longer needed.

The runner refuses a non-temporary socket path or a server whose `data_directory` is not exactly `<socket-dir>/data`. It creates/removes only a fresh generated schema. Sandbox environments may require permission for the local socket and server stop; record a blocked check instead of presenting it as passed. Setup instructions are not a production administration procedure.

## What these checks do not prove

The tests establish native fixture semantics and particular transaction schedules on version **16.15**. Sources cite version **18**; no version18 skip-scan, exact plan shape or other version18-only feature was executed. The tiny data set is not a performance benchmark. Power-loss durability, replication/failover, stress behavior, deadlock retries, a live auth system, RLS policy implementation, HTTP endpoint correctness and complete tenant isolation were not tested. The shared guard-row alternative is explained with its isolation-level requirements but has no runnable implementation in this phase.

## Last verified

2026-11-02: 28 native checks passed. No claim of exhaustive correctness or production validation.
