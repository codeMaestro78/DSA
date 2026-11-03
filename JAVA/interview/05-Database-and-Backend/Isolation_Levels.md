# Isolation levels: reason with an interleaving

## Overview

Isolation controls what concurrent transactions can observe and how their results relate. Always name the database engine. The SQL standard's minimum guarantees are not identical to every engine's implementation.

For **PostgreSQL**, Read Uncommitted behaves as Read Committed; Read Committed is the default. Repeatable Read gives a stable transaction snapshot and prevents phantom reads in PostgreSQL, but can still allow serialization anomalies. Serializable rejects executions that cannot be explained by some serial order of the participating transactions. Applications must handle serialization failures. [PostgreSQL isolation](https://www.postgresql.org/docs/18/transaction-iso.html)

## What the anomalies mean

| Anomaly | Small example |
|---|---|
| Dirty read | Read another transaction's uncommitted change |
| Nonrepeatable read | Reread a row and see another committed value |
| Phantom | Reexecute a predicate query and see a changed matching row set |
| Serialization anomaly | The committed outcome cannot match any serial ordering |

A stable snapshot alone is not full serializability. Two transactions can agree on a snapshot and write different rows that jointly violate a rule.

## Read Committed: statement snapshots

Initial account balance is 100. T1 starts, reads 100; T2 changes the row to 80 and commits; T1 reads again and sees 80. The two reads belong to the same transaction but separate statement snapshots. T1 still sees its own writes.

Under PostgreSQL Repeatable Read, if T1 established its snapshot before T2 committed, its second ordinary read still sees 100. This describes snapshot visibility, not a promise that every attempted update can commit.

## Lost update: stale application calculation

T1 and T2 both read 100 outside a shared locking strategy. T1 writes the constant 90 for a 10-unit debit; T2 then writes constant 80 for a 20-unit debit. Both commits can succeed at Read Committed and the final balance is 80 instead of 70.

For a simple per-row arithmetic debit, first require a positive amount in the declared exact numeric/currency contract; a negative amount would increase the balance rather than debit it. Then `UPDATE accounts SET balance = balance - :amount WHERE id = :id AND balance >= :amount` with checked affected-row count avoids that stale-constant calculation. For more complex logic, lock/read/update inside a transaction or use version-checked optimistic concurrency. A zero-row update is a result to handle, not permission to assume success. Place a suitable row constraint on the stored balance too.

## Write skew: two doctors

Initial data: doctors A and B both on call. Rule: at least one remains on call.

| Step | T1 (doctor A) | T2 (doctor B) |
|---|---|---|
| 1 | Begin Repeatable Read; count on-call rows → 2 | Begin Repeatable Read; count → 2 |
| 2 | Set A off call | Set B off call |
| 3 | Commit | Commit |

The writes affect different rows. Both can commit; afterward no doctor is on call. Neither serial order would permit both decisions if each rechecked the rule against the previous committed result.

Possible fixes, selected to the workload:

- **Serializable + complete transaction retry:** execute the read/decision/write as a serializable transaction, abort on failure and rerun the whole unit from fresh reads. In a correct retry, the second doctor sees only one on-call doctor and refuses to leave. Do not retain the first attempt's decision.
- **Shared guard row under Read Committed:** every relevant writer first locks the same existing schedule/team row, then checks the on-call set in a subsequent statement and changes it within that transaction. The fresh Read Committed statement snapshot after lock acquisition sees the previous holder's committed decision. All relevant writers must follow this protocol. A guard lock alone is not a universal fix at snapshot isolation: a Repeatable Read transaction can retain a snapshot from before it waited. At snapshot levels, deliberately updating/versioning the shared guard can force a conflict/retry, or use Serializable with complete retry. Locking only the doctor being changed does not protect the cross-row predicate.
- **Different data model:** make the invariant enforceable on one shared record if that matches the domain.

`SELECT ... FOR UPDATE` locks rows that exist and match. It is not a general lock on every future row that might satisfy an arbitrary predicate. “Check no booking exists, then insert” needs uniqueness/exclusion constraints, a common lock or suitable serializable handling; there may be no row to lock. [Locking mechanisms](https://www.postgresql.org/docs/18/explicit-locking.html)

## Retrying correctly

On a PostgreSQL serialization failure (`40001`), roll back and retry the **entire transaction**, including all reads and application decisions, with a bounded attempt/deadline policy and backoff as appropriate. Deadlock (`40P01`) may also be retryable under an application policy. Unique violations, invalid input and permission failures are not automatically transient. Keep externally visible non-idempotent actions out of retried units or give them their own durable idempotency protocol.

Serializable describes committed database histories, not a guarantee of wall-clock ordering of every external side effect or a guarantee of no transaction aborts. Preserve the distinction between serializable execution and stricter real-time guarantees.

## Interview practice

[INFERRED] Intermediate–Advanced, backend/SDE, debugging/design round.

1. **ISO-01:** Draw an interleaving where two successful transactions violate a business rule despite stable snapshots.
2. **ISO-02:** Why does retrying only `COMMIT` not repair a serialization failure?

<details><summary>Expected answer and followups</summary>

1. Use the two-doctor schedule above. Show what was read, which rows were written and why a serial execution would differ. Followup: which shared lock must all writers acquire?
2. The decisions were based on an invalid concurrent snapshot/history and the transaction was aborted. Restart the transaction with fresh reads; a new commit command cannot repair the earlier decisions. Followup: how do you prevent an email from being sent twice across retries?

Mistakes: teaching generic Repeatable Read as if every engine behaves like PostgreSQL, confusing snapshot stability with invariant preservation, and assuming a nonexistent row can be row-locked. Related: [ACID](Transactions_and_ACID.md), [constraints](Normalization.md).

</details>

## Checklist and validation

- [ ] Name engine, level, read set, write set and invariant.
- [ ] Construct a small interleaving rather than reciting a table alone.
- [ ] Explain the retry boundary and external side effects.

Last verified **2026-11-02**. Documentation is PostgreSQL 18; [local tests](../validation/database/README.md) identify the actual runtime and tested schedules. No behavior of MySQL, MongoDB or other engines is inferred from these tests.
