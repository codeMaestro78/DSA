# Reranking and candidate recall

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

A reranker reorders a retrieved candidate set. A cross-encoder can examine query and passage jointly; this often adds per-candidate computation compared with a stored-vector lookup. Whether it improves the task must be measured on the relevant corpus and queries.

Reranking cannot recover a relevant document absent from its input. Separate candidate recall from ordering quality and downstream support. Bound candidate count, input length and total time; preserve permissions/provenance and handle model failure with an explicit fallback policy. A fallback to original ranks can be reasonable if its quality contract is understood.

## Worked role scenario

The relevant passage is at rank 80 from initial retrieval, but only the top 20 candidates are reranked. A perfect reranker cannot select it. Compare candidate depths and measure the increased latency/cost; also inspect whether a better initial retriever finds it more efficiently.

## Interview practice: RAGE-P3-09

- **Category / difficulty:** Reranking and candidate recall / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Which metrics distinguish a candidate-generation problem from a ranking problem?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Recall at the candidate cutoff checks coverage; MRR/NDCG or task-specific ranking criteria measure placement given relevance judgments. Then inspect answer support separately. State label incompleteness and relevance assumptions.

**Followup:** How would you test a reranker timeout fallback?

**Common mistake:** Reporting only final top-1 relevance without initial-candidate coverage.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Measure candidate coverage first.
- [ ] Bound candidate count and input length.
- [ ] Preserve provenance and a tested fallback policy.

## Canonical lessons and references

- [Canonical reranking](../../08-LLM-RAG-and-Agent-Engineering/Reranking.md).
- [Evaluation](RAG_Evaluation.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).
- [Weaviate: hybrid search](https://docs.weaviate.io/weaviate/search/hybrid).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
