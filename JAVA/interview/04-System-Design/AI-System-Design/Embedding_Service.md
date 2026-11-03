# Design a versioned embedding service

## Candidate prompt

**AI-SD-10 — Intermediate; RAG/ML/backend; architecture; company-neutral.** Embed documents and queries for retrieval. Support re-embedding without mixing incompatible vector spaces. Clarify input types, dimensions, throughput, privacy and downstream index requirements.

<details><summary>Reference data and request design</summary>

The model registry owns immutable embedding model/tokenizer/preprocessing versions, vector dimension and normalization policy. The document catalog owns source identity, version and permissions. An embedding record identifies source/chunk version, model version and relevant transform options. A dimension match alone does not establish semantic compatibility between two models.

The synchronous query path validates size, selects the embedding/index generation pair and returns a vector to a permitted search service. The async document path accepts bounded work, uses per-tenant quotas, batches compatible inputs and persists success/failure keyed by source/model version. Deduplication keys must include all transformations that change the result, not only raw text.

### Worked storage estimate

One million vectors × 768 dimensions × 4 bytes/float32 = 3,072,000,000 bytes, or 3.072 decimal GB, for raw values only. This excludes IDs, metadata, graph/index structures, copies and database overhead. Reducing precision changes storage and possibly ranking quality; it is not a free exact substitute. Unicode characters, text tokens and vector dimensions are different quantities.

### Migration flow

Build a new index generation using the new model and transformations; embed queries with the corresponding model. Evaluate labeled retrieval/permissions and ingestion completeness, then switch the query route to the new compatible pair. Keep rollback artifacts and update/deletion handling coherent across both generations. Never append new-model vectors into an old semantic space merely because dimensions happen to match.

### Failure and security

A provider timeout can leave an unknown compute/billing outcome even if no business record was stored. Retry under an explicit policy and retain version-specific deduplication on local results. Handle partial batch failure per item rather than falsely marking the entire batch complete. Check deletion/tombstones before publishing late worker results.

Embeddings can retain sensitive information; avoid assuming they are anonymous. Treat vector caches, model requests and external providers as data-handling boundaries. Partition or scope caches by authorized use and version; do not expose arbitrary private embeddings through a public API.

</details>

## Practice and checklist

What if a new model improves a public benchmark but harms your exact-identifier queries? Evaluate the actual corpus and task mix, compare lexical/hybrid alternatives and retain the better permitted configuration. No benchmark establishes universal suitability.

- [ ] Query and document vectors use a compatible generation.
- [ ] Storage arithmetic excludes overhead explicitly.
- [ ] Partial batches, late writes, migration and deletion are handled.

## References

- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).
- [OWASP: authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
