# Transactions and ACID: name the invariant

## Overview

A transaction is a unit of database work with all-or-nothing commit/rollback behavior and a chosen isolation contract. Start with the invariant: for example, an inventory count must never go below zero, or a transfer must not create money. Merely writing `BEGIN` does not prove either invariant.

## The four properties

| Property | Meaning | Frequent misunderstanding |
|---|---|---|
| Atomicity | Related transactional changes commit together or roll back | A database rollback does not recall an email or external API call |
| Consistency | Correct transactions preserve declared/business invariants | The database cannot enforce a rule you never modeled |
| Isolation | Concurrency is constrained by the selected level/mechanism | Every transaction is not automatically serializable |
| Durability | Acknowledged commits survive the failures covered by the storage/configuration contract | One durable copy is not protection against loss of its storage system |

A transaction must be on one appropriate database connection/context. On an SQL error PostgreSQL normally marks the transaction failed; roll back or use a deliberately managed savepoint before continuing. [Transaction tutorial](https://www.postgresql.org/docs/18/tutorial-transactions.html)

## Worked example: sell the final unit once

```sql
-- example: reserve_inventory
UPDATE inventory
SET stock = stock - 1
WHERE product_id = 1 AND stock > 0
RETURNING stock;
```

With initial stock 1, the first successful call returns 0; a later call returns no row. The condition and update are one database statement. The shared schema also declares `stock INTEGER NOT NULL CHECK (stock >= 0)` as defense against other write paths.

At PostgreSQL Read Committed, a concurrent updater of the same row waits and reevaluates the condition against the updated row. If another transaction consumed the final unit, the guarded decrement does not apply. If you instead read stock into application memory and later write a calculated constant, you can use stale state. [Documented update behavior](https://www.postgresql.org/docs/18/transaction-iso.html)

If reserving inventory also creates an order, place both operations in one transaction and require the decrement to return a row before inserting the order. On failure, roll back both. A successful decrement by itself does not create an order or prevent duplicate requests from buying two units when stock is greater than one: request idempotency is a separate invariant.

## Transfers and deadlocks

A transfer needs at least: positive amount, valid distinct account ids, sufficient source funds, compatible currency and atomic debit/credit. Lock accounts in a stable id order, verify business rules and update both inside one transaction. Stable ordering reduces a common deadlock pattern but is not proof that no other path can deadlock.

A deadlock cycle is A waiting for a lock held by B while B waits for one held by A. PostgreSQL detects cycles and aborts a participant. The application must handle the error and may retry a bounded full transaction when appropriate. Do not blindly retry every integrity violation or unique-key conflict. [Explicit locking](https://www.postgresql.org/docs/18/explicit-locking.html)

## Durability and external work

Write-ahead logging supports recovery, but acknowledged persistence depends on WAL/storage settings and underlying hardware guarantees. With asynchronous commit (`synchronous_commit = off`), acknowledgement can precede durable WAL flush, allowing recent acknowledged transactions to be lost after a crash. Synchronous commit is not the same as synchronous replication; replica acknowledgement depends on configuration. [WAL settings](https://www.postgresql.org/docs/18/runtime-config-wal.html), [storage reliability](https://www.postgresql.org/docs/18/wal-reliability.html)

To send a notification after committing an order, a transactional outbox can insert order state and a dispatch-intent row atomically. A worker later sends it and marks progress. The worker may crash after sending but before marking completion, so the external receiver needs idempotency/deduplication or reconciliation. The outbox closes one atomicity gap; it does not create universal exactly-once side effects.

## Ambiguous commit

Suppose the database commits but the network drops before the application receives acknowledgement. Retrying with a new operation id can duplicate work. A durable operation key, uniqueness constraint and lookup/reconciliation path let the application discover the committed outcome. Distinguish “database said rollback” from “client does not know.”

## Interview practice

[INFERRED] Intermediate, backend/SDE, technical/design round.

1. **TX-01:** Explain why the guarded decrement protects a single stock row but not a two-row “at least one doctor on call” rule.
2. **TX-02:** An order committed but its email was not sent. What does an outbox guarantee and what still needs handling?

<details><summary>Expected reasoning and followups</summary>

1. The decrement's invariant is expressed in one row/statement. A cross-row predicate needs a shared serialization mechanism, correctly locked common resource or serializable transactions with retry. See [isolation](Isolation_Levels.md).
2. The outbox preserves the intent to dispatch alongside the order. Delivery can repeat, be delayed or fail permanently; use attempt budgets, monitoring, receiver idempotency and reconciliation. Followup: what if the provider times out after accepting the email?

Mistakes: promising ACID across an arbitrary HTTP call, equating replica presence with backup/restore, or retrying only the final failed statement after the transaction has aborted. Related: [API idempotency](API_Design.md).

</details>

## Checklist and last verified

- [ ] State an invariant and its enforcement point.
- [ ] Distinguish rollback, known failure and unknown outcome.
- [ ] Explain a crash between database and external side effect.
- [ ] State durability assumptions explicitly.

Last verified **2026-11-02**: primary documentation and toy transaction tests; no power-failure, failover or external provider test was run. [Validation scope](../validation/database/README.md).
