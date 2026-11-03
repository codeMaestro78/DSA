# Database selection

## Choose for the data and invariants

A database is a mechanism for storing and coordinating state. Begin with access patterns, integrity constraints, freshness and operating ability. “SQL scales poorly” and “NoSQL is always eventually consistent” are not useful decision rules.

For a short-link service, the core access patterns are lookup by code, create a unique code and list links by creator. A relational store with a primary-key lookup and a creator/time index is a defensible initial choice. A key-value store can also fit if it supplies the required conditional insertion, durability and query patterns. Verify exact product semantics before selecting it.

## Decision table

| Need | Candidate family | Question that can change the decision |
|---|---|---|
| Multi-row invariants and flexible relational queries | Relational database | What isolation/locking/constraint behavior is required? |
| Simple key lookup at very large partitioned scale | Key-value store | Can conditional writes, hot keys and consistency meet the contract? |
| Flexible nested documents | Document database | Which fields need indexes and which invariants span documents? |
| Lexical retrieval/ranking | Search engine | How fresh must the index be and what remains the source of truth? |
| Disposable acceleration | Cache | Can data be rebuilt, and what happens during a complete miss storm? |
| Vector similarity | Vector index/store | What are relevance, permission/filter and freshness requirements? |
| Analytical scans/aggregations | Analytical warehouse/column store | What latency and ingestion delay are acceptable? |

These are families and questions, not vendor endorsements or performance rankings. Many products overlap; benchmark the actual access pattern and version.

## Source of truth versus derived views

A search index, cache or analytics table can be rebuilt from authoritative records if the source retains enough data. Specify how updates, deletions, schema evolution and reindexing propagate. A stale search result should not grant access to a revoked object. Separating an index from the source introduces a consistency boundary and operational work.

For the example shortener, code uniqueness belongs in the authoritative store. A cache lookup followed by an insert without a database constraint is unsafe under concurrency and eviction. PostgreSQL's index documentation describes both performance benefits and write/storage overhead; an index is not a free acceleration layer.

## Model scale before changing the storage family

Try appropriate indexes, efficient queries, a bounded connection pool, vertical capacity and a read cache when justified. Replicas can offload eligible reads but add lag and do not increase a single primary's write capacity. Sharding changes routing, transactions, rebalancing and operational recovery. Neither is a substitute for locating the measured bottleneck.

## Practice question

**SD-DATA-01 — Intermediate; SDE/backend; design; 0–2 years.** A team stores users in one database and their search representation in another. Which one should authorize profile access?

<details><summary>Expected reasoning and followups</summary>

Use the authoritative permission state at the access boundary, or a rigorously specified authorization mechanism with an acceptable revocation bound. A search index is a retrieval aid and can be stale. Followup: what happens after account deletion during index lag? Enforce denial and propagate removal without treating stale indexed content as authority. Mistake: assuming filtering in the UI protects the underlying record.

</details>

## Checklist

- [ ] Access patterns and invariants precede the vendor name.
- [ ] Strong and eventual requirements are assigned per operation.
- [ ] Derived views have rebuild and deletion strategies.
- [ ] Operational burden and failure behavior are compared.

## References

- [PostgreSQL — transaction isolation](https://www.postgresql.org/docs/current/transaction-iso.html)
- [PostgreSQL — indexes](https://www.postgresql.org/docs/current/indexes.html)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
