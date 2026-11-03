# MySQL: reason from the storage engine and query contract

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

MySQL is a relational database system; behavior depends on engine, configuration and version. This lesson scopes transaction discussion to InnoDB in the 8.4 documentation. Do not transfer PostgreSQL snapshot, index or locking rules to MySQL simply because both accept SQL.

InnoDB combines multiversion reads and locking. A transaction's ordinary consistent read and its locking/current read can answer different questions. Index choice affects both access cost and which records/ranges a locking operation examines. Autocommit, isolation and transaction lifetime matter; a connection-pool checkout is not automatically a useful transaction boundary.

## Worked trace

Inventory has available=1. Two callers each read1 before decrementing. Both cannot safely promise a reservation based only on that observation. A guarded authoritative update or an appropriate lock/transaction protocol must decide availability, and the caller must check affected rows/commit outcome. A timeout after commit is an unknown client result, not proof of rollback. Inspect the actual isolation and unique/constraint design instead of relying on a generic “ACID” label.

## Design choices and failure checks

Choose indexes from selective predicates, join keys and requested ordering; verify with an actual plan and representative data. A covering path may reduce lookups while increasing index size/write cost. Long transactions retain versions/locks and complicate maintenance. Replication/read routing needs a stated freshness and durability contract. Backups require tested restore, not just scheduled files. Credentials, least privilege, query parameters and tenant ownership remain application responsibilities.

## Attempt first — P7-DB-MYSQL

Does REPEATABLE READ remove the need for a business uniqueness constraint?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Isolation and invariant enforcement answer different questions. Express uniqueness/atomic updates at the database boundary and test conflicting transactions under the actual engine/settings. Followup: why can the access path affect a locking operation?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [InnoDB transaction model](https://dev.mysql.com/doc/refman/8.4/en/innodb-transaction-model.html).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
