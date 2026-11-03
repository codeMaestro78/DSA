# Classical ML preparation

## Role-specific focus

Explain a model through objective, representation, assumptions and cost. A justified simple baseline is more informative than an algorithm list. This is original [INFERRED], company-neutral preparation for approximately 0–2 years of experience, not an employer syllabus or eligibility guarantee.

## Canonical foundation

Read [Classical ML Algorithms](../../07-AI-ML-Deep-Learning/Classical_ML_Algorithms.md) for derivations and mechanisms. This chapter supplies role-specific drills and evidence. Keep [coding/backend fundamentals](../../00-Interview-Fundamentals/README.md) active.

## Preparation drill

Compare logistic regression, a constrained tree and boosting on the same tabular task. Hold split/metric fixed. Discuss interactions, sample size, missing values, calibration and serving constraints without declaring a universal winner.

## Artifact and evidence

Prepare a comparison table with preprocessing, hyperparameters, train/predict cost drivers, counterexample and fair tuning budget. Include uncertainty and slice behavior.

## Interview exercise — attempt first

**ID:** MLROLE-03. **Category:** ML engineer / Classical ML preparation. **Difficulty:** Intermediate. **Target:** early-career ML/AI; technical, coding or design practice as appropriate. **Evaluates:** mechanism and evidence. **Related:** canonical chapter above and [role roadmap](ML_Engineer_Interview_Roadmap.md). **Source:** original exercise with primary support linked from its foundation.

**Question:** Why keep a linear baseline after a complex model scores better?

<details><summary>Expected reasoning and followup</summary>

It remains a sanity check and potentially simpler deployment. Determine whether the measured gain, uncertainty and added cost justify complexity.

**Followup:** How would you compare tuning budgets and inference costs fairly?

**Common mistake:** Dropping a simple baseline before validating the improvement.

</details>

## Checklist

- [ ] Attempt the drill independently before reading the reference.
- [ ] State shapes, data availability and evidence limits.
- [ ] Explain my actual contribution separately from planned improvements.

## References and last verified

[ML source register](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md), [numerical validation](../../validation/ml/README.md), [practice rubric](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

2026-11-02: role drill reviewed. Full role sessions are now linked in this track’s mock chapter; this individual drill retains its narrower scope.
