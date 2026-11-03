# Hybrid search and rank fusion

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Lexical and dense retrieval have complementary failure modes: exact identifiers and rare terms may favor lexical matching; paraphrases may favor semantic similarity. A hybrid system generates candidates from both, deduplicates by stable document identity and combines scores or ranks under a defined policy.

Raw BM25 and vector scores generally do not share a calibrated scale. Rank fusion such as RRF avoids directly mixing incompatible raw magnitudes, but still has candidate-depth and weighting choices. A hybrid system cannot retrieve a document excluded from both candidate sets. Permission filtering must apply consistently to every branch.

## Worked role scenario

For an error code query, lexical retrieval ranks the exact manual first while dense search prefers a semantically related troubleshooting guide. Compare lexical-only, dense-only and fused results using labeled queries. A fusion change that improves paraphrases may still regress exact-code queries; inspect slices.

## Interview practice: RAGE-P3-07

- **Category / difficulty:** Hybrid search and rank fusion / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Why not average lexical and vector scores directly?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** The scales/distributions and meanings differ. Use justified normalization/calibration or rank fusion, then evaluate the chosen method and candidate depths. No fusion rule guarantees universal improvement.

**Followup:** What if one branch returns unauthorized candidates?

**Common mistake:** Assuming hybrid always beats the best single retriever on every query.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Explain candidate generation and fusion separately.
- [ ] Use stable deduplication IDs.
- [ ] Evaluate identifier and paraphrase slices.

## Canonical lessons and references

- [Canonical hybrid search](../../08-LLM-RAG-and-Agent-Engineering/Hybrid_Search.md).
- [Reranking](Reranking.md).
- [Weaviate: hybrid search](https://docs.weaviate.io/weaviate/search/hybrid).
- [Elasticsearch: hybrid search](https://www.elastic.co/docs/solutions/search/hybrid-search).
- [OpenSearch: hybrid search](https://docs.opensearch.org/latest/vector-search/ai-search/hybrid-search/index/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
