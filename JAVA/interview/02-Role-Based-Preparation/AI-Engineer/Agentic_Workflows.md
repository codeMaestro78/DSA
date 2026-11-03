# Fixed workflows and agentic decisions

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

A fixed workflow has predefined transitions; an agentic workflow delegates selected decisions to a model. This is a spectrum. Use a fixed flow when the process is known and auditability matters. Add model choice only where rules cannot express the useful decision economically and evaluation can detect failure.

Durable checkpoints record enough state to resume, but cannot automatically reverse or deduplicate external side effects. Identify which steps are pure/replayable and which need stable operation IDs. Human approval must bind to the actual proposed action and expire or be rechecked when conditions change.

## Worked role scenario

Expense processing follows upload → extraction → validation → reviewer decision → payment instruction. The model may extract fields or recommend a category; a deterministic policy and human permission boundary control payment. Replaying extraction is different from replaying payment.

## Interview practice: AIE-P3-06

- **Category / difficulty:** Fixed workflows and agentic decisions / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Where would you place model decisions and human approval in this flow?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Use model decisions for uncertain interpretation, deterministic checks for invariants and explicit authority for consequential actions. Persist the reviewed payload/version and handle changed inputs.

**Followup:** How do cancellation and restart affect a previously approved action?

**Common mistake:** Assuming a checkpoint library makes every side effect replay-safe.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Classify replayable and consequential steps.
- [ ] Bind approval to a specific action version.
- [ ] Explain recovery from one interrupted step.

## Canonical lessons and references

- [Agentic systems](../../08-LLM-RAG-and-Agent-Engineering/Agent_Architecture.md).
- [Workflow persistence](../../08-LLM-RAG-and-Agent-Engineering/LangGraph.md).
- [FastAPI: concurrency and async](https://fastapi.tiangolo.com/async/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
