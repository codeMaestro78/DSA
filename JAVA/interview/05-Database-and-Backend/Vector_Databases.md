# Vector stores: geometry is one retrieval stage

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

A vector store persists embeddings and supports similarity retrieval with associated metadata/indexes. The embedding model/version, dimensionality, normalization and distance function form a representation contract. Comparing vectors from incompatible models is not repaired merely by matching their length.

Exact nearest-neighbor search supplies a useful baseline. Approximate nearest-neighbor indexes trade resources/latency against retrieval recall under their actual configuration. A similarity score is not calibrated factual confidence, user permission or proof that the final answer follows from the text.

## Worked trace

Tenant A asks for refund policy R-17. Semantic nearest neighbors contain an obsolete A policy and a highly similar private B policy. Apply trusted eligibility/currentness before evidence is exposed; hybrid lexical candidates can preserve the literal identifier. If the correct authorized document never reaches the candidate set, a reranker limited to that set cannot recover it. Evaluate eligible recall separately from answer support and abstention.

## Design choices and failure checks

An index change needs model/chunking/source manifests, a frozen labeled query set, per-slice regressions, delete/revocation tests and an activation/rollback policy. Approximate filtering behavior can reduce returned candidates; verify the actual engine/index and compare an exact eligible oracle on small fixtures. Store provenance/chunk boundaries with stable document versions. A vector export may still be sensitive; embeddings are not automatically anonymous. Keep relational versus dedicated-store choice tied to workload, operations and required filtering/transaction contracts.

## Attempt first — P7-DB-VECTOR-DATABASES

Can increasing top-k alone solve cross-tenant retrieval?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. It can increase exposure and cost. Enforce current eligibility at the retrieval boundary and measure permitted recall; top-k is a quality/resource parameter, not authorization. Followup: how do you migrate incompatible embedding versions?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [pgvector project documentation](https://github.com/pgvector/pgvector).
- [Canonical store comparison](../02-Role-Based-Preparation/RAG-Engineer/Vector_Databases.md).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
