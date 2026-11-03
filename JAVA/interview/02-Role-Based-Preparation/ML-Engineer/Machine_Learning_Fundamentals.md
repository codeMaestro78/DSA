# ML fundamentals for interviews

## Role-specific focus

Start from the decision the prediction supports. State prediction unit/time, target horizon and error cost before naming an algorithm. This is original [INFERRED], company-neutral preparation for approximately 0–2 years of experience, not an employer syllabus or eligibility guarantee.

## Canonical foundation

Read [Model Evaluation](../../07-AI-ML-Deep-Learning/Model_Evaluation.md) for derivations and mechanisms. This chapter supplies role-specific drills and evidence. Keep [coding/backend fundamentals](../../00-Interview-Fundamentals/README.md) active.

## Preparation drill

For ticket escalation, distinguish pre-decision features from post-resolution fields. Group or time-split repeated tickets appropriately and compare constant/logistic baselines. Explain why a post-outcome field is invalid despite random splitting.

## Artifact and evidence

Write a specification with labels, split, baseline, metric, validation-selected threshold, error slices and limits. Distinguish offline quality from a product-impact claim.

## Interview exercise — attempt first

**ID:** MLROLE-02. **Category:** ML engineer / ML fundamentals for interviews. **Difficulty:** Intermediate. **Target:** early-career ML/AI; technical, coding or design practice as appropriate. **Evaluates:** mechanism and evidence. **Related:** canonical chapter above and [role roadmap](ML_Engineer_Interview_Roadmap.md). **Source:** original exercise with primary support linked from its foundation.

**Question:** Why might hiding test labels still leave leakage?

<details><summary>Expected reasoning and followup</summary>

Repeated entities, near-duplicates, future-derived features or learned preprocessing can cross boundaries. The deployment generalization unit determines a valid split.

**Followup:** Show one feature that exists in the data file but not at prediction time.

**Common mistake:** Treating hidden labels as proof that all other leakage is absent.

</details>

## Checklist

- [ ] Attempt the drill independently before reading the reference.
- [ ] State shapes, data availability and evidence limits.
- [ ] Explain my actual contribution separately from planned improvements.

## References and last verified

[ML source register](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md), [numerical validation](../../validation/ml/README.md), [practice rubric](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

2026-11-02: role drill reviewed. Full role sessions are now linked in this track’s mock chapter; this individual drill retains its narrower scope.
