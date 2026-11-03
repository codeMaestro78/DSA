# Scale the access pattern before splitting ownership

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

Database scaling means increasing useful capacity while preserving the application contract. More connections can reduce capacity when they create contention. Begin with the slow operation, its query plan, rows examined, lock time and resource saturation. Separate read volume, write contention, working-set size and retention growth: each suggests a different intervention.

For a fictional tenant dashboard, 100 requests/s each run 20 per-order queries: that is 2,000 database round trips/s before any optimization. A bounded batch fetch can reduce trips without changing storage topology. An index should match actual filters/order; it adds write and maintenance cost. Cache only a declared freshness-tolerant representation, with tenant and authority context. A replica adds read capacity for eligible stale reads but does not remove primary write contention. Table partitioning can prune irrelevant data or simplify retention; it does not itself distribute writes across independent servers.

## Worked trace

A customer creates order O and immediately opens it. Returning O from the committed response and reading the primary, or using an applied-position gate, preserves the required journey. Sending the next request to an arbitrary lagging replica may show “not found.” A five-second timer does not prove catch-up. If the primary is saturated by one hot counter, adding replicas cannot make that single authoritative update cheap; reconsider the invariant and its owner before sharding.

## Alternatives and operating limits

| Choice | Helps when | New cost or constraint |
|---|---|---|
| Query/index/data-shape fix | Waste dominates a known access path | Verify plan and write cost |
| Pool/admission limits | Too much concurrent work causes collapse | Queue/reject explicitly |
| Cache/read replica | Repeated reads tolerate declared staleness | Invalidation, lag and authority |
| Partition/archive | Queries/retention align to a partition key | Cross-partition constraints and plans |
| Shard | One writer/storage owner is genuinely insufficient | Routing, resharding, cross-shard invariants |

## Attempt first — P7-DATABASE-SCALING

An API has slow writes and fast replica reads. Should you add three replicas?

<details><summary>Expected reasoning and followup — attempt first</summary>

Not as the primary fix. Measure write locks, WAL/storage and hot keys; replicas serve read work but do not multiply the primary's write path. Bound admission and verify a specific intervention. Followup: what changes if writes are fast but reporting scans dominate?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [PostgreSQL partitioning](https://www.postgresql.org/docs/18/ddl-partitioning.html).
- [Replication contracts](https://www.postgresql.org/docs/18/warm-standby.html).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
