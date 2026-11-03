# Neural-network coding discussion

## Role-specific focus

Connect calculus to implementation. Declare weight orientation and check each output/gradient shape rather than relying on implicit broadcasting. This is original [INFERRED], company-neutral preparation for approximately 0–2 years of experience, not an employer syllabus or eligibility guarantee.

## Canonical foundation

Read [Training and Backpropagation](../../07-AI-ML-Deep-Learning/Training_and_Backpropagation.md) for derivations and mechanisms. This chapter supplies role-specific drills and evidence. Keep [coding/backend fundamentals](../../00-Interview-Fundamentals/README.md) active.

## Preparation drill

Derive logistic weight gradient X.T@(p-y)/n and bias mean(p-y), then compare finite differences on a float64 fixture. Explain how targets (n, 1) and logits (n,) can form an unintended (n, n) broadcast.

## Artifact and evidence

Use the tested NumPy routine as a reference after an independent attempt. The reference suite passing does not validate a separately rewritten implementation.

## Interview exercise — attempt first

**ID:** MLROLE-05. **Category:** ML engineer / Neural-network coding discussion. **Difficulty:** Intermediate. **Target:** early-career ML/AI; technical, coding or design practice as appropriate. **Evaluates:** mechanism and evidence. **Related:** canonical chapter above and [role roadmap](ML_Engineer_Interview_Roadmap.md). **Source:** original exercise with primary support linked from its foundation.

**Question:** What does gradient disagreement near ReLU zero mean?

<details><summary>Expected reasoning and followup</summary>

The point is nonsmooth and conventions differ. Test smooth points and the same objective before blaming autodiff.

**Followup:** How would you test the same gradient away from a nonsmooth point?

**Common mistake:** Calling every numerical disagreement an autodiff bug.

</details>

## Checklist

- [ ] Attempt the drill independently before reading the reference.
- [ ] State shapes, data availability and evidence limits.
- [ ] Explain my actual contribution separately from planned improvements.

## References and last verified

[ML source register](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md), [numerical validation](../../validation/ml/README.md), [practice rubric](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

2026-11-02: role drill reviewed. Full role sessions are now linked in this track’s mock chapter; this individual drill retains its narrower scope.
