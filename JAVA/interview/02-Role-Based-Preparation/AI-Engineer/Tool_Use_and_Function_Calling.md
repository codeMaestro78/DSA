# Tool use and function calling

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

A model-produced function-call proposal is structured output requesting an operation. The executor’s tool result is a separate observation returned after an attempted execution. The host chooses whether to run it. Validate both syntax and semantics: enum/range/size checks, required fields, current tenant/resource permission, and whether the operation is still allowed. Reject unknown tools and unexpected fields deliberately.

Read tools and write tools have different retry risks. A lookup can often be repeated, though load and freshness still matter. A payment or message-send needs durable idempotency/reconciliation across unknown outcomes. Do not place secrets into broad tool responses or trust returned content as new policy.

## Worked role scenario

The model requests `get_invoice` with a guessed invoice ID. The application must scope the lookup to the authenticated tenant and object permissions. A valid ID format proves neither ownership nor authority. A forbidden lookup should not leak whether another tenant's invoice exists.

## Interview practice: AIE-P3-07

- **Category / difficulty:** Tool use and function calling / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What checks follow successful argument-schema validation?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Authenticate the actor context, authorize the exact resource/action, enforce domain limits, classify side effects and apply the operation deadline/idempotency contract.

**Followup:** What if the tool result contains instructions to call another tool?

**Common mistake:** Equating schema-valid arguments with authorized business intent.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Separate proposal, validation and execution.
- [ ] Enforce resource permissions server-side.
- [ ] Classify retry policy per tool.

## Canonical lessons and references

- [Tool calling](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md).
- [Authorization](../../05-Database-and-Backend/Authorization.md).
- [FastAPI: response models](https://fastapi.tiangolo.com/tutorial/response-model/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
