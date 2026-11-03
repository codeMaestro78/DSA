# MongoDB: document boundaries and atomic changes

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

MongoDB stores BSON documents, whose shape should follow access patterns and invariants rather than an assumption that schema design is unnecessary. A single-document write is atomic. Multi-document transactions are available in supported deployments, but their guarantees and costs depend on deployment/read/write concern configuration. “NoSQL has no transactions” is incorrect; “transactions make every model equally good” is also incorrect.

## Worked trace

A cart document embeds a bounded set of line items updated together; a versioned conditional write can reject an edit based on an old revision. An unbounded order-history array can eventually make the document a poor aggregate. If inventory and purchase span documents, define the transaction or reservation workflow rather than assuming two independent updates are atomic. A successful read is still not authority to overwrite another tenant's document.

## Design choices and failure checks

Embedding reduces joins and can preserve one aggregate's atomicity, but duplicates facts and can grow documents. References allow independent lifecycle at the cost of additional reads/coordination. Specify indexes and bounded pagination, compare explain results and reject unbounded query shapes. Sharding distributes by a chosen key; a hot tenant or monotonic range can still dominate. Keep transaction scopes short and reconcile uncertain external effects separately. Test schema-validation rules, missing fields versus explicit null, revision conflicts and migration compatibility.

## Attempt first — P7-DB-MONGODB

Should every relationship be embedded because document updates are atomic?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Bound aggregate size, shared lifecycle and update patterns first. Independently growing/shared records often need references or another ownership boundary. Followup: what invariants require a multi-document transaction?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [MongoDB transactions](https://www.mongodb.com/docs/manual/core/transactions/).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
