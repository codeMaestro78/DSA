# LlamaIndex and retrieval pipeline ownership

## What the inspected documentation establishes

[OFFICIAL] LlamaIndex's RAG introduction organizes the pipeline around loading, indexing, storing, querying and evaluation. It provides abstractions for these stages; the source does not establish that a default pipeline satisfies your corpus, permissions, freshness or quality targets.

The older `docs.llamaindex.ai` URL redirected to the current developer documentation. Use the actual installed framework/integration versions and current official API reference before implementing. This chapter contains no SDK code that is claimed to have been executed.

## Keep data contracts visible

A reader/parser produces documents; transformations create nodes/chunks and metadata; indexes/stores support lookup; retrievers select candidates; response synthesis uses selected context. Preserve source/version/spans and embedding configuration across those abstractions. If a connector omits access labels or normalization changes offsets, the downstream application cannot repair provenance by adding a citation marker alone.

## Worked ingestion scenario

A PDF policy update changes a table and revokes an appendix. The pipeline should parse the new version, retain table context, produce versioned chunks and activate the intended index version. Old chunks and derived summaries must be invalidated according to policy. The query layer must not combine an old appendix with a new title simply because both remain in a vector store.

A cached ingestion transformation can improve repeat runs, but its key must reflect inputs and transformation/model versions. A stale cache is a correctness issue when it preserves obsolete content or permissions.

## Retrieval and synthesis

Use a simple lexical/dense baseline and labeled examples before adding routers, recursive retrieval or agentic query engines. Inspect what text actually entered the model. A response synthesizer can produce fluent text from poor candidates; evaluation needs both retrieval relevance and answer support.

Filtering must enforce current access at an appropriate trusted boundary. A library's metadata field is a mechanism for carrying a policy input, not proof that every connector/store/query path enforces the policy.

## Validation plan and tradeoff

Use tiny source fixtures with known spans, updates, deletions, repeated IDs and denied tenants. Test pipeline idempotency and version migrations. Then assess real retrieval/answer quality on reviewed cases. The local chunk tests in this phase check Python offset behavior only, not LlamaIndex readers, OCR, embeddings or vector integrations. A framework can save plumbing while increasing dependency/version and abstraction-debugging work; choose consciously.

## Interview practice — P3-AI-21

**Question:** A framework returns a citation ID but the visible document text does not support the claim. What do you inspect?

- **Category/topic:** AI engineering / LlamaIndex and retrieval pipeline ownership.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Trace source version, parsing/spans, retrieved node text, context assembly and generated claim alignment. Citation formatting alone does not establish support.

**Followup:** How would an ingestion cache become stale after a model or parser change?

**Common mistake:** Assuming a reader/index abstraction automatically enforces ACLs, deletion or citation correctness.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [LlamaIndex: introduction to RAG](https://developers.llamaindex.ai/python/framework/understanding/rag/) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [pgvector official README](https://github.com/pgvector/pgvector) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
