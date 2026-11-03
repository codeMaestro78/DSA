# Design an AI evaluation platform

## Candidate prompt

**AI-SD-13 — Intermediate/Advanced; AI/ML; architecture; company-neutral.** Compare model/prompt/retrieval versions on representative tasks and prevent regressions. The platform must preserve dataset privacy, reproducibility and trustworthy interpretation of scores.

<details><summary>Reference evaluation lifecycle</summary>

### Data and ownership

A dataset registry owns versioned examples, splits, label provenance, access controls and allowed use. An evaluation configuration identifies model/prompt/tool/retrieval versions, decoding settings, metric definitions and judge configuration. A run service owns task attempts, raw bounded artifacts, status and aggregate calculation; reports link back to example-level evidence.

Flow: select permitted frozen dataset/configuration → schedule bounded runs → record outputs and execution failures → apply deterministic/human/judge evaluations → compute declared aggregates and slices → compare to a baseline → make a promotion decision. A failed or timed-out example cannot silently disappear from the denominator.

### Metrics and labels

Separate task success, schema validity, citation support, retrieval relevance, permission violations, refusals, latency and spend. An answer can be valid JSON yet false, or accurate yet unauthorized. A weighted score can hide a critical defect; use explicit blocking gates where the product requires them. Define abstention policy and distinguish answerable from unanswerable tasks.

A language-model judge needs a versioned prompt, rubric, order/bias controls and calibration against suitable human labels. Shared model families and shared evidence can create correlated errors. Judge confidence or agent agreement is not an independent ground truth.

### Worked interpretation

Suppose configuration A succeeds on82/100 tasks and B on86/100 on the same frozen set. The four-point difference alone does not establish a reliable improvement. Inspect paired successes/failures, label noise, subgroup behavior and uncertainty. Repeatedly selecting prompts using this set converts it into tuning data; preserve a genuinely held-out evaluation or obtain fresh tasks.

### Reliability and cost

Use stable example/run IDs and distinguish infrastructure retry from a new stochastic sample. Rate-limit external calls, keep a total run budget and preserve partial results with explicit completion status. Mocked providers help test the harness but cannot establish a real model's quality. Store sensitive outputs with least privilege and retention; summary dashboards should avoid raw prompt exposure.

### Rollout integration

A report informs, rather than automatically proves, deployment readiness. Promotion can require quality thresholds, critical-case pass conditions and a limited canary with rollback. Compare the complete configuration, not just model name, and retain enough evidence to explain the decision later.

</details>

## Followups and checklist

What if an evaluator crashes for the hardest examples? Count and report missing/failed evaluations; do not call the remaining average complete. What if a source changed after the run? Preserve permitted version/provenance or clearly state non-reproducibility.

- [ ] Dataset/configuration/metric versions and denominator are explicit.
- [ ] Critical errors cannot be hidden by an aggregate score.
- [ ] Judge calibration, held-out boundaries and failed runs are visible.

## References

- [Martin Zinkevich: Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).
- [Google SRE: alerting on SLOs](https://sre.google/workbook/alerting-on-slos/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
