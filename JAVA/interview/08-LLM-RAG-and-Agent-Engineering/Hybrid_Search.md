# Hybrid search and reciprocal rank fusion

## Why combine rankings

Lexical and dense retrieval often fail on different queries. Hybrid retrieval creates candidates from both and combines their evidence. A lexical score and cosine score are not automatically comparable numeric scales; summing them without calibration can give arbitrary weighting.

Rank fusion uses position rather than raw score. For reciprocal rank fusion, score(d)=Σ1/(c+rankᵢ(d)) over lists containing d, with ranks starting at 1 and a nonnegative smoothing constant c. A missing document contributes 0 from that list. The constant is a tunable fusion parameter, not the number of results requested. Weighted variants need explicit weights.

## Worked trace

Lexical list `[a,b]`, dense list `[b,c]`, smoothing 60:

| Document | Contributions | Fused score |
|---|---|---|
| a | 1/61 | approximately 0.016393 |
| b | 1/62+1/61 | approximately 0.032522 |
| c | 1/62 | approximately 0.016129 |

The fused order is b,a,c. The [local implementation](../validation/ai/core_examples.py) accepts an iterable of list/tuple branches (not one-shot branch generators) and rejects duplicate IDs within a branch so one document cannot earn multiple rank contributions there. It uses stable string-ID tie-breaking. Those are explicit teaching contracts rather than universal engine behavior.

## Retrieval and permission boundaries

Use the same stable document/chunk identity across branches and a consistent authorized corpus/version. A fusion step must not reintroduce a document removed by a permission filter. Overlapping chunks can dominate ranks through duplicates even when their IDs differ; deduplicate or diversify according to the evidence task, retaining the source spans needed for citations.

Fusion cannot recover a relevant document absent from every candidate list. A reranker following fusion also cannot fix that absence. Increase candidate coverage or repair ingestion/query constraints when recall is the problem; do not tune the final ranking alone.

## Evaluate the actual benefit

Compare each branch and fusion on a fixed labeled set, with exact-ID and semantic queries separated. Measure both retrieval quality and added latency. Different smoothing constants, candidate sizes and branch weights can change order. Use a development set for tuning and a held-out set for the final comparison.

RRF does not produce calibrated relevance probabilities; a higher score means higher fusion preference under that configuration. Do not compare absolute scores across arbitrary numbers of branches as if they had identical semantics. The tests check the shown ranking and score formula, not enterprise search quality.

## Interview practice — P3-AI-10

**Question:** A reranker follows hybrid fusion, but relevant evidence is still absent. Which stage needs investigation?

- **Category/topic:** AI engineering / Hybrid search and reciprocal rank fusion.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Inspect source availability, ingestion, branch constraints/candidate recall and filters first. Fusion/reranking only reorder candidates they receive.

**Followup:** How do duplicate chunks and an extra retrieval branch affect score interpretation?

**Common mistake:** Adding uncalibrated raw scores or treating an RRF score as a relevance probability.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Microsoft Learn: reciprocal rank fusion](https://learn.microsoft.com/en-us/azure/search/hybrid-search-ranking) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.

Local-helper boundary rechecked2026-11-03: the isolated floating-point fixture accepts integer smoothing in0..1,000,000 and rejects larger/boolean values. The general RRF formula above has no such teaching bound; no exact-real ranking or arbitrary-precision guarantee is claimed by that helper.
