# Optimization discussion

## Role-specific focus

Separate optimization failure from generalization failure. Declare objective, reduction, batch, update frequency and optimizer state. This is original [INFERRED], company-neutral preparation for approximately 0–2 years of experience, not an employer syllabus or eligibility guarantee.

## Canonical foundation

Read [Optimization Algorithms](../../07-AI-ML-Deep-Learning/Optimization_Algorithms.md) for derivations and mechanisms. This chapter supplies role-specific drills and evidence. Keep [coding/backend fundamentals](../../00-Interview-Fundamentals/README.md) active.

## Preparation drill

Explain SGD, momentum and adaptive scaling; then calculate correct accumulation for equal and unequal microbatches. Trace one actual parameter update rather than only a loss curve.

## Artifact and evidence

Use a diagnosis record: data/labels, tiny-batch fit, gradient check, update inspection, rate study and held-out comparison. State what observation rejects each hypothesis.

## Interview exercise — attempt first

**ID:** MLROLE-06. **Category:** ML engineer / Optimization discussion. **Difficulty:** Intermediate. **Target:** early-career ML/AI; technical, coding or design practice as appropriate. **Evaluates:** mechanism and evidence. **Related:** canonical chapter above and [role roadmap](ML_Engineer_Interview_Roadmap.md). **Source:** original exercise with primary support linked from its foundation.

**Question:** Does lower training loss after switching optimizer prove improvement?

<details><summary>Expected reasoning and followup</summary>

It establishes a narrower optimization result under the tested budget, not generalization. Compare held-out quality, variability and operational cost fairly.

**Followup:** What would demonstrate the change generalizes across relevant slices?

**Common mistake:** Optimizing training loss while ignoring validation quality.

</details>

## Checklist

- [ ] Attempt the drill independently before reading the reference.
- [ ] State shapes, data availability and evidence limits.
- [ ] Explain my actual contribution separately from planned improvements.

## References and last verified

[ML source register](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md), [numerical validation](../../validation/ml/README.md), [practice rubric](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

2026-11-02: role drill reviewed. Full role sessions are now linked in this track’s mock chapter; this individual drill retains its narrower scope.
