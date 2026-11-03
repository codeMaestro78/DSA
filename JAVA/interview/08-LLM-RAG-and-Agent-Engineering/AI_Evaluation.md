# AI evaluation as a versioned experiment

## Start from the user task

Define what success means, which mistakes matter and what inputs represent expected use. A general answer-quality score can hide a critical failure such as a wrong citation, unauthorized action or broken structured output. Evaluate components and complete user journeys with distinct criteria.

A versioned case contains input, allowed context/resources, expected properties or labels, rubric, slice tags and provenance. Exact-match answers suit some tasks; open-ended reasoning needs a reviewed rubric or supported constraints. Do not ask the evaluated model to define its own success after seeing the result.

## Worked comparison

Prompt A and B answer the same 40 reviewed policy questions under the same corpus snapshot. Compare supported claims, correct abstention, citation alignment, latency and token usage per query. A shorter average answer is not an improvement if it omits required conditions. Treat the 40 as a small illustrative sample, not sufficient evidence for every deployment population.

Use a development set for tuning and a held-out set for final comparison. Repeatedly choosing a prompt based on the held-out score leaks evaluation information into development. Keep a separate adversarial/safety set and report it transparently rather than hiding failures in an overall mean.

## Judges and uncertainty

Human labels can disagree; record the rubric and adjudication process. Model judges can be useful but require calibration, version tracking, order/verbosity-bias checks and resistance to instructions inside evaluated text. A judge's rationale is not independent proof of correctness. Review severe disagreements manually where feasible.

Report case counts, denominators, slice performance and uncertainty. For stochastic systems, repeat selected cases or use controlled seeds/settings while recognizing that provider behavior may not be fully deterministic. One passing run is not a reliability probability.

## Regression and release decisions

Track model, prompt, retrieval/index, tool schema and application versions. A change in any can invalidate a comparison. Define stop conditions for critical regressions and acceptable tradeoffs before optimizing the metric. Pair offline cases with monitored user-visible behavior under appropriate privacy controls; feedback data can itself be biased.

The local metric/loop tests validate specific deterministic functions and contracts. They do not score a real LLM or establish task-quality gains. This chapter's experiment examples are original, company-neutral preparation exercises, not reports of hidden evaluation practices.

## Interview practice — P3-AI-27

**Question:** Why is repeatedly selecting prompts by the same final test-set score a problem?

- **Category/topic:** AI engineering / AI evaluation as a versioned experiment.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** The selection process adapts to that set, making it development data and biasing the claimed final estimate. Keep tuning separate and document versions, denominators and critical failure slices.

**Followup:** How would you calibrate an LLM judge against reviewed examples?

**Common mistake:** Treating a single mean judge score or passing local tests as general model quality.

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
- [scikit-learn: NDCG API and conventions](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.ndcg_score.html) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
