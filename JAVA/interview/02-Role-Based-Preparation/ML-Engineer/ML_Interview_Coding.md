# ML coding exercises

## Role-specific focus

Practice small complete functions with declared shapes and undefined-case policies. These are original company-neutral drills, not employer question reports. This is original [INFERRED], company-neutral preparation for approximately 0–2 years of experience, not an employer syllabus or eligibility guarantee.

## Canonical foundation

Read [Training and Backpropagation](../../07-AI-ML-Deep-Learning/Training_and_Backpropagation.md) for derivations and mechanisms. This chapter supplies role-specific drills and evidence. Keep [coding/backend fundamentals](../../00-Interview-Fundamentals/README.md) active.

## Preparation drill

Implement confusion metrics, a training-fit standardizer, stable logistic loss/gradients and a masked attention head. Attempt first, then inspect the linked reference. Explain O(nd) logistic/scaling work, O(n) metric work and O(Lq*Lk) materialized attention scores.

## Artifact and evidence

Test ordinary/empty/invalid shapes, finite-value boundaries and undefined metric denominators. Compare numerical gradients and per-query attention references, not only a favorable sample. Framework variants require separate execution.

## Interview exercise — attempt first

**ID:** MLROLE-11. **Category:** ML engineer / ML coding exercises. **Difficulty:** Intermediate. **Target:** early-career ML/AI; technical, coding or design practice as appropriate. **Evaluates:** mechanism and evidence. **Related:** canonical chapter above and [role roadmap](ML_Engineer_Interview_Roadmap.md). **Source:** original exercise with primary support linked from its foundation.

**Question:** Is returning 1 for precision with no positive predictions necessarily a coding bug?

<details><summary>Expected reasoning and followup</summary>

The ratio is mathematically undefined. An API can declare a numeric convention; the reference returns None. Compare contracts rather than assuming every implementation uses one convention.

**Followup:** How would you report an undefined metric rather than hide it in an average?

**Common mistake:** Assuming every library has the same zero-denominator convention.

</details>

## Checklist

- [ ] Attempt the drill independently before reading the reference.
- [ ] State shapes, data availability and evidence limits.
- [ ] Explain my actual contribution separately from planned improvements.

## References and last verified

[ML source register](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md), [numerical validation](../../validation/ml/README.md), [practice rubric](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

2026-11-02: role drill reviewed. Full role sessions are now linked in this track’s mock chapter; this individual drill retains its narrower scope.
