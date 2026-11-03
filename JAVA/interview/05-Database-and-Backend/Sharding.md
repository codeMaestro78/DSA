# Sharding: distribute ownership with a migration protocol

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

Sharding partitions data across independent ownership/storage units. Horizontal table partitioning on one server is not automatically sharding across servers. Choose a key that supports the dominant access paths, bounded partition size and skew; a uniform hash of tenant IDs does not split one enormous tenant's work.

Every operation needs a routing version and an invariant scope. Local uniqueness does not establish global uniqueness. Cross-shard joins/transactions introduce coordination or application-level workflows; do not hide these under a routing library.

## Worked trace

Tenants A and B share shard1. Move A to shard2: snapshot at a known cutoff, copy, apply subsequent changes/deletes, verify data and then switch the authoritative routing epoch. Fence old writers at the real storage boundary. Requests with stale routing must redirect/retry safely rather than write to both owners. Keep stable operation identities across the move. Rollback requires reconciling any shard2 writes, not simply pointing back at the old snapshot.

## Design choices and failure checks

Range sharding supports locality/range scans but may create hot ranges; hash sharding spreads keys but adds scatter/gather for range queries; directory routing supports placement flexibility but adds an authoritative metadata dependency. Consistent hashing limits remapping under membership changes but does not solve hot keys, global transactions or copied-data correctness. Prefer query/index/retention fixes or vertical scaling when they meet the measured need with less operational cost. Test resharding under concurrent writes, timeouts, deletes and authority revocation.

## Attempt first — P7-DB-SHARDING

Can dual-writing during migration make cutover safe automatically?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Partial failures create divergence unless ordering, idempotency, reconciliation and a single authoritative owner are defined. Followup: how does the new owner reject an old writer after route-cache expiry?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [Partitioning distinction](https://www.postgresql.org/docs/18/ddl-partitioning.html).
- [Distributed failure reasoning](../04-System-Design/Distributed_Systems.md).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
