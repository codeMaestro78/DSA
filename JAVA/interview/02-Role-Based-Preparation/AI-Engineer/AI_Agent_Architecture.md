# Agent architecture for applied engineering

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

An agent loop lets model decisions influence the next action. The application still owns the action space, state, authorization, time budget and termination conditions. Separate a proposed action from an approved/executed action. A tool name or argument produced by a model is an untrusted request.

Start with a state machine: ready → decision → validated action → observation → decision or terminal result. Persist durable work when recovery matters. Limit steps, total elapsed time, tool concurrency and spend. A larger limit is not a correctness fix for a loop that lacks progress.

## Worked role scenario

A document assistant proposes “search,” then “update CRM.” Search can use scoped read permission; CRM writes require explicit application authorization and possibly approval. A model citing the user's urgency does not gain that permission.

## Interview practice: AIE-P3-05

- **Category / difficulty:** Agent architecture for applied engineering / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What must the application enforce even when the model selects actions?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Allowed tool/arguments, current actor/resource permissions, side-effect policy, budgets, cancellation, durable state/recovery and output acceptance.

**Followup:** What happens if a tool succeeds just before a timeout?

**Common mistake:** Treating the model plan as an authorized execution plan.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Draw trust and persistence boundaries.
- [ ] Define terminal/error states.
- [ ] Explain one unknown tool outcome.

## Canonical lessons and references

- [Agent architecture](../../08-LLM-RAG-and-Agent-Engineering/Agent_Architecture.md).
- [Tool calling](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md).
- [FastAPI: concurrency and async](https://fastapi.tiangolo.com/async/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
