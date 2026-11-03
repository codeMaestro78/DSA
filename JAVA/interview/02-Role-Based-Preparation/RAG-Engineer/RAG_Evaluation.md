# RAG evaluation from retrieval to citations

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Build a query set with task intent, authorized source snapshot, relevance/support judgments and expected behavior when evidence is missing. Separate retrieval metrics from answer metrics: precision/recall@k, MRR or NDCG describe ranked results under specified labels; faithfulness/support, correctness and citation alignment describe the answer.

Define denominators and edge cases. Queries with no known relevant item need an explicit policy rather than division by zero or silent exclusion. Incomplete judgments can mark useful unseen material as irrelevant. Human review and model judges both need a rubric, sampling strategy and disagreement handling.

## Worked role scenario

A query has two labeled relevant passages. The top 3 results contain one relevant and two nonrelevant passages: precision@3 = 1/3 and recall@3 = 1/2 under those labels. An answer may still fail if it cites a non-supporting passage or answers beyond the retrieved evidence. These numbers do not establish whole-corpus quality.

## Interview practice: RAGE-P3-11

- **Category / difficulty:** RAG evaluation from retrieval to citations / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you evaluate citations beyond checking that a URL exists?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Check the cited source/version is accessible to the actor, the cited span supports the associated claim, important claims have support and the displayed reference resolves to that evidence. Evaluate retrieval misses and unsupported generation separately.

**Followup:** How do you avoid tuning chunking on the same final test queries?

**Common mistake:** Using one aggregate faithfulness score as factual truth and permission validation.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Specify labels, cutoffs and empty-gold policy.
- [ ] Separate relevance, support, correctness and access.
- [ ] Inspect failure slices and annotation disagreement.

## Canonical lessons and references

- [Canonical RAG evaluation](../../08-LLM-RAG-and-Agent-Engineering/RAG_Evaluation.md).
- [ML evaluation](../../07-AI-ML-Deep-Learning/Model_Evaluation.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
