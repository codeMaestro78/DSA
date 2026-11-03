# Redis: explicit durability for a fast data structure

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

Redis offers server-side data structures commonly used for caches, counters, coordination and queues. Being memory-oriented does not mean persistence is impossible; configured snapshot/AOF policies have different recovery and performance tradeoffs. Conversely, enabling persistence does not by itself prove no acknowledged write can be lost under every failure. Product/deployment/version settings must be inspected.

## Worked trace

A report cache misses for key(tenantA,report7,version 3). Fifty requests may all compute the same expensive report. A scoped single-flight/admission mechanism can limit fills, but lease expiry must not allow a stale fill to overwrite version 4 after invalidation. Atomic server operations can protect local counter decisions; a read and later write from the application is not the same atomic operation. A cache hit never substitutes for current resource authorization.

## Design choices and failure checks

Choose an eviction policy and max-memory/admission behavior consistent with whether data is disposable. TTL expiration is a lifecycle rule, not a business transaction or precise global clock. A lock-like key needs ownership token/release checks and a real fencing mechanism when stale holders can act elsewhere. Monitor memory, eviction, latency, persistence/rewrite work and replication lag. Losing a cache can stampede the authoritative database; enforce bounded fallback. Do not deploy Redis as the sole durable financial authority merely because a small counter demo works.

## Attempt first — P7-DB-REDIS

Can a Redis TTL lock prove an old paused worker cannot write after expiry?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. The actual protected resource must reject stale ownership/version, or the operation needs another safe protocol. Expiration alone only changes cache state. Followup: what should happen if a cache outage coincides with peak traffic?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [Redis persistence](https://redis.io/docs/latest/operate/oss_and_stack/management/persistence/).
- [Cache race design](../04-System-Design/Classic-System-Design/Distributed_Cache.md).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
