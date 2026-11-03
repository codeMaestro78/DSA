# Design a recommendation system

## Candidate prompt

**AI-SD-08 — Intermediate/Advanced; ML/backend; design; company-neutral.** Recommend eligible items to users. Clarify the user outcome, inventory, feedback signals, privacy, latency and how an improvement will be measured. More clicks may not mean better long-term value.

<details><summary>Reference architecture and experiment</summary>

### Data and stages

A catalog owns available items and eligibility. An interaction pipeline owns timestamped, deduplicated observations such as impressions and clicks; the training pipeline builds reproducible datasets with feature/label availability times. A model registry owns immutable model/preprocessing artifacts and promotion history.

Serve through candidate generation → scoring → eligibility/business filtering and reranking → presentation. Google's course presents this common multi-stage pattern; the particular stores, targets and API here are hypothetical. Candidate generators can combine popularity, content similarity or learned retrieval. Rank only items permitted and currently available for the user.

### Leakage and feedback

Training examples must reflect information available at decision time. Future purchases, post-outcome flags or aggregates including the label period can leak the answer. An impression log distinguishes items shown from the entire catalog; unclicked does not automatically mean disliked. Exposure bias and feedback loops affect interpretation of observed labels.

Use time-aware and user-aware splits as the deployment question requires. Start with a credible popularity/content baseline. Evaluate ranking and coverage, inspect cold-start users/items and sparse groups, then design an online experiment with a clear randomization unit and guardrails. Offline ranking gain is not causal proof of online user benefit.

### Serving and failure

The request API binds user identity and eligibility. Feature computation uses versioned transformations with defined freshness; missing features follow an evaluated fallback. Avoid training-serving skew by comparing logged serving features with training transforms. If the learned model fails, a permitted popular-items list can be an explicit fallback, with availability/eligibility filters still enforced.

### Capacity and tradeoffs

Candidates number C, feature dimensions d and top result count k determine different costs. Scoring every catalog item may be too expensive, so candidate generation trades recall against latency. A sophisticated reranker cannot repair excluded candidates. Caching a user feed reduces compute but needs invalidation/freshness and may reduce diversity; define the product tradeoff and measure it.

</details>

## Followup question

Your offline NDCG rises, but an experiment shows worse retention. Does the experiment prove the ranking code is buggy?

<details><summary>Expected reasoning</summary>

No. Check experiment validity, exposure/label assumptions, distribution changes and whether the proxy aligned with user value. It can reveal objective mismatch without a code defect. Do not tune repeatedly on the same test cohort without accounting for selection and repeated comparisons.

</details>

## Checklist

- [ ] Labels and features obey availability time.
- [ ] Offline and online outcomes are distinguished.
- [ ] Cold start, eligibility and fallback remain explicit.

## References

- [Google: recommendation architecture](https://developers.google.com/machine-learning/recommendation/overview/types).
- [Martin Zinkevich: Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
