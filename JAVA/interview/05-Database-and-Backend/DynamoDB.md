# DynamoDB: design keys and consistency per access path

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

DynamoDB is a managed key-value/document database. Start with item identity, partition key, sort-key access and required reads rather than a generic “schemaless” claim. A key arrangement should answer concrete queries without unbounded scans. Partition-key skew/hot items remain possible even when a service manages infrastructure.

The inspected read-consistency documentation states tables and local secondary indexes support eventual or strongly consistent reads, while global secondary indexes and streams use eventual reads. Global-table modes have their own contracts; do not infer that every replica/index shares one universal consistency rule.

## Worked trace

A write to order O succeeds, then the application queries a status GSI and sees no O yet. That can be index propagation rather than a failed base-table write. For a critical immediate read, choose a supported authoritative read path and explicit consistency option, or return the committed representation. A conditional item write can decide a version/invariant; a prior read alone cannot prevent another caller changing the item before the write.

## Design choices and failure checks

Model tenant/order lookups, history sorting and index projections together. Adding an index creates write/storage and freshness costs. Multi-item atomic requirements need the service's actual transaction/condition contract, not an application loop. Pagination returns bounded pages and continuation state; a live query is not automatically a frozen snapshot. Specify capacity/admission, retries only for safe effects, backup/restore and tenant authorization. No current pricing, quota, regional topology or benchmark is asserted here.

## Attempt first — P7-DB-DYNAMODB

Can a GSI query be treated as immediate confirmation of the latest base-item write?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Under the inspected contract GSI reads are eventual; use an appropriate supported path or expose pending freshness. Followup: what is your operation's behavior if the authoritative path is unavailable?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [DynamoDB read consistency](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/HowItWorks.ReadConsistency.html).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
