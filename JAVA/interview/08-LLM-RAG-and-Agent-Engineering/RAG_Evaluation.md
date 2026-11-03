# RAG evaluation: retrieval, support and citations

## Define the unit and labels

Evaluation needs a query population, corpus/version, authorization context and relevance judgments. A relevant document, relevant chunk and exact supporting span are different labels. Include answerable, unanswerable, stale, conflicting and permission-sensitive cases. Keep tuning examples separate from the final evaluation set.

## Retrieval metrics

For a ranking of unique IDs and cutoff k, Precision@k is relevant hits/k under this chapter's convention; a short list is penalized for missing positions. Recall@k is hits divided by the number of known relevant items. Reciprocal rank is 1/r for the first relevant result within the cutoff, or 0 if none is found. MRR averages reciprocal rank over queries under a stated inclusion policy.

NDCG uses position-discounted graded relevance divided by ideal DCG. The local helper chooses gain 2^grade − 1, discount log₂(rank+1), grades 0..4 and absent labels treated as 0. Other valid implementations use linear gain or different unjudged policies; report the convention before comparing numbers. The cited scikit-learn API uses its own documented scoring convention; the local helper does not claim byte-for-byte equivalence.

If no positive relevance labels exist, local recall, reciprocal rank and NDCG return None; precision remains defined. Exclude or separately report undefined cases instead of silently averaging them as 0. Also report coverage/counts, because exclusions can hide difficult queries.

## Worked graded example

Rank `[a,x,b]`, grades a=3,b=1 and k=3: precision 2/3, recall 1, reciprocal rank 1. DCG=7+0+1/2=7.5. Ideal DCG=7+ 1/log₂(3), so NDCG≈ 0.983. The [local suite](../validation/ai/README.md) asserts these results and edge-case policies.

## Generation and citation checks

Retrieval relevance is not answer correctness. Check each material claim for support in the provided source, whether the citation points to that supporting span, and whether the source itself is current/appropriate. A quote can be copied exactly yet fail to support the surrounding claim. A faithful answer to a false source can still be externally wrong.

Human review remains useful for ambiguity. Model judges require a rubric, calibration against reviewed examples, version tracking and disagreement analysis. They can be biased, inconsistent or influenced by evaluated text; do not treat a judge score as independent ground truth.

## Compare improvements

Report query count, slice results, uncertainty, labeling limitations and latency/cost together. Use paired comparisons on the same queries. A small synthetic test validates metric formulas, not real RAG quality or hidden-company interview performance.

## Interview practice — P3-AI-12

**Question:** Why can recall@k improve while final answer correctness gets worse?

- **Category/topic:** AI engineering / RAG evaluation: retrieval, support and citations.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Additional or differently ranked evidence can introduce stale/conflicting context, distract generation or displace needed spans. Inspect source quality, context selection and claim support separately from retrieval metrics.

**Followup:** How would you report queries with no known relevant source?

**Common mistake:** Averaging undefined metrics silently or equating citation presence with citation support.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [scikit-learn: NDCG API and conventions](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.ndcg_score.html) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
