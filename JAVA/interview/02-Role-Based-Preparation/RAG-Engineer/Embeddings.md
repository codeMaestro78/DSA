# Embeddings, similarity and compatibility

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Embeddings map inputs into vectors under a model's training objective. Similarity is task/model dependent, not a universal meaning score. Match query/document encoding conventions, normalization, dimension and distance metric. Equal dimensions do not make vectors from different models comparable.

Record embedding model/version and preprocessing with the index. A model migration normally needs re-embedding or a validated compatibility strategy; simply pointing queries at an old index can silently damage relevance. Compare exact search on a manageable sample before attributing misses to an ANN index.

## Worked role scenario

A system changes only query embeddings to a new model with the same dimension. Retrieval degrades despite valid vector shapes. The shared dimension established storage compatibility, not a shared vector space. Evaluate a fully consistent new index and a controlled migration/rollback path.

## Interview practice: RAGE-P3-05

- **Category / difficulty:** Embeddings, similarity and compatibility / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What checks separate embedding mismatch from ANN approximation error?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Confirm model/version/preprocessing/metric, then compare exact and approximate rankings on the same vectors and relevance labels. Measure filtered cases and document-update freshness too.

**Followup:** What happens with a zero vector in cosine similarity?

**Common mistake:** Treating high similarity as proof of factual support or permission.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Pin vector-space metadata.
- [ ] Explain metric and normalization assumptions.
- [ ] Use exact-reference comparisons where feasible.

## Canonical lessons and references

- [Canonical embeddings](../../08-LLM-RAG-and-Agent-Engineering/Embeddings.md).
- [Vector search](../../08-LLM-RAG-and-Agent-Engineering/Vector_Search.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
