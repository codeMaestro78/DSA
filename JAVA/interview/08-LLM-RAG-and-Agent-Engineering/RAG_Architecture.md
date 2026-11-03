# RAG architecture from ingestion to supported answer

## Two pipelines

Retrieval-augmented generation conditions a model on retrieved information. The ingestion pipeline creates retrievable evidence; the query pipeline selects authorized evidence and uses it. The original RAG paper describes a particular research architecture; current applications can use different retrievers, prompts and storage designs.

Ingestion: source acquisition → parsing/OCR → structure extraction → chunking → embedding/indexing → version activation. Query: identity/permissions → query representation → candidate retrieval → filtering/fusion/reranking → context assembly → generation → support/citation checks → response. Store stable source IDs, versions and spans so an answer can be traced beyond a chunk's text.

## Worked source-span contract

A chunk record carries document ID, version, tenant/ACL references, span convention, content hash, text and embedding version. The local character chunker returns [start,end) Python code-point spans. Production PDFs/OCR need page/layout anchors and a mapping to original content; raw extraction offsets alone may not reproduce the visible page.

For `abcdefghij`, size 4 / overlap 1 yields `abcd`, `defg`, `ghij` at [0,4),[3,7), [6,10). Overlap preserves boundary context but adds duplicate retrieval and storage. This helper tests offset behavior, not semantic chunk quality or PDF parsing.

## Source-of-truth and freshness

The source owns content and permissions. The search index is derived state. Activate a complete document version consistently so a query does not mix old and new chunks unexpectedly. Deletion and permission revocation must propagate through source records, index candidates, caches, persisted conversations and any permitted retained artifacts under an explicit policy.

Apply authorization before evidence enters generation. Filtering only after the model saw the content cannot undo disclosure. A final permission check may be needed when a long request spans a permission change; define the consistency contract.

## Evaluation and failure isolation

Create labeled queries with relevant spans, not just plausible answers. If an answer is wrong, inspect whether the source contained the answer, parsing retained it, retrieval found it, context included it and generation used it correctly. These are distinct failure stages with different fixes.

RAG does not guarantee factuality. A source can be wrong or stale, retrieval can select a conflicting policy, and generation can attach a citation to an unsupported sentence. Define abstention and conflict handling. Start with a simple bounded baseline before query agents or graph summaries; additional stages add cost, latency and failure modes that need independent justification.

## Interview practice — P3-AI-07

**Question:** An answer cites a document that was revoked yesterday. Which copies and boundaries must you inspect?

- **Category/topic:** AI engineering / RAG architecture from ingestion to supported answer.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Trace authoritative permission state, index metadata, candidate filtering, context/cache assembly and retained answer/citation policy. The index is derived state; model instructions cannot substitute for access enforcement.

**Followup:** How would you make a document-version update visible without mixing incompatible chunks?

**Common mistake:** Calling RAG a hallucination guarantee or treating an index as permission authority.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [pgvector official README](https://github.com/pgvector/pgvector) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
