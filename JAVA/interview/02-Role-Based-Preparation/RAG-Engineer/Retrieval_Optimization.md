# Diagnosing and improving retrieval

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Optimize the failing stage, not the most fashionable component. Classify misses: source absent/stale, extraction failure, bad chunk boundary, embedding mismatch, lexical mismatch, metadata filter, ANN candidate loss or reranker error. An end-to-end answer score alone cannot distinguish them.

Use an exact vector baseline on a tractable sample, relevance labels and stage-specific candidate logs with sensitive content controls. Change one important setting at a time for causal diagnosis. Then test combined changes and critical slices; a development-set win needs a separate final check before a broad claim.

## Worked role scenario

Recall drops only for a small tenant. Compare filtered exact results with filtered approximate retrieval, inspect filter metadata and index execution behavior, and check whether enough candidates survive. Increasing generation temperature cannot repair a missing authorized passage.

## Interview practice: RAGE-P3-08

- **Category / difficulty:** Diagnosing and improving retrieval / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What experiment would you run before raising ANN search effort?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Confirm source presence/permissions/vector compatibility, compare exact versus approximate candidates under the same filter and inspect query plans/settings. Increase effort only when evidence points to ANN approximation, then measure recall/latency tradeoff.

**Followup:** When can increasing top k hurt the final answer?

**Common mistake:** Optimizing generation before proving relevant evidence reaches context.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Classify the failure before changing settings.
- [ ] Compare controlled retrieval baselines.
- [ ] Measure quality and latency together.

## Canonical lessons and references

- [Vector-store choices](Vector_Databases.md).
- [Canonical retrieval](../../08-LLM-RAG-and-Agent-Engineering/Retrieval_Strategies.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).
- [Milvus: filtered search](https://milvus.io/docs/filtered-search.md).
- [Qdrant: filtering](https://qdrant.tech/documentation/search/filtering/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
