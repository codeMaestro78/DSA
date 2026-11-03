# Elasticsearch: search visibility is not source durability

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

Elasticsearch builds searchable indexes over documents, using inverted-index and other structures for its supported queries. Treat it as the search representation with an explicit source-of-truth relationship. Search visibility is near-real-time and tied to refresh behavior; it is not the same event as receiving a write acknowledgment or durable backup.

Analyzers transform text into indexed terms. Exact identifiers, full-text tokens and numeric/range fields need intentionally chosen mappings. A schema/mapping change can require reindexing rather than silently changing the meaning of already indexed values.

## Worked trace

Source document D version 7 is committed; the search API still finds version 6 before refresh/update propagation. Immediate source reads and search freshness have different contracts. A reindex built from a snapshot must replay later updates/deletes before activation, or a deleted D may reappear. Versioned immutable index generations plus an activation pointer help rollout, but current authorization must remain independent of the old snapshot.

## Design choices and failure checks

Shard count, routing, replicas and query shape change resource work. More shards can increase overhead and scatter/gather cost; they are not free parallelism. Bound expensive queries/aggregations and use stable pagination with an explicitly chosen live or snapshot model. Never rely on final UI filtering after unauthorized source text already reached a model/client. Test analyzer behavior on literal IDs, empty/no-match inputs, deletes and generation changes. Search recall/latency require representative measured workloads; no “within one second always” service guarantee is inferred from a documentation example/default.

## Attempt first — P7-DB-ELASTICSEARCH

A search result cites a valid document ID. Does that prove the answer is current and authorized?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Verify source/version/effective policy and current resource rights, then evaluate claim support. Search relevance and a citation are separate evidence. Followup: what if a deletion lands during reindex?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [Near-real-time search](https://www.elastic.co/docs/manage-data/data-store/near-real-time-search).
- [Search case](../04-System-Design/Classic-System-Design/Search_Engine.md).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
