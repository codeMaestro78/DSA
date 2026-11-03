# Architecture from product acceptance criteria

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Start with the user's decision and acceptable failure behavior. A writing assistant, factual lookup and autonomous transaction system have different quality and permission boundaries. Define a useful fallback: request more evidence, offer a draft for review, or report unavailable status. A generic apology does not restore an incomplete workflow.

Map acceptance criteria to the UI, backend and evaluation. “Helpful” becomes observable through specific tasks and reviewer labels; “fast” needs stage and percentile definitions. Keep a prototype's claim narrow and make the next validation step explicit before calling it production-ready.

## Worked role scenario

A contract assistant highlights clauses for a human reviewer. The acceptance test can require supported highlights with source locations and clear uncertainty. Automatically approving a contract is a different product with different authority and error consequences; it cannot inherit the same acceptance evidence.

## Interview practice: FSAI-P3-10

- **Category / difficulty:** Architecture from product acceptance criteria / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you turn “make this AI feature trustworthy” into concrete checks?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Define the task, source/permission requirements, supported-output criteria, abstention behavior, critical failure cases, operational response and human control. Test those specific claims.

**Followup:** Which metric could improve while user outcomes worsen?

**Common mistake:** Choosing architecture from a demo before specifying the user decision.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Define user success and unacceptable failure.
- [ ] Tie each component to a requirement.
- [ ] Separate prototype and rollout evidence.

## Canonical lessons and references

- [AI architecture](../AI-Engineer/AI_Application_Architecture.md).
- [Evaluation](../AI-Engineer/Evaluation_and_Observability.md).
- [React: effects and derived state](https://react.dev/learn/you-might-not-need-an-effect).
- [FastAPI: response models](https://fastapi.tiangolo.com/tutorial/response-model/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
