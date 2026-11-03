# AI integration across the product boundary

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Use a server-owned provider adapter with typed application inputs/outputs. The browser should not receive a reusable provider secret. Normalize provider errors into stable product states without erasing useful distinctions such as throttled, invalid request, unavailable or rejected output.

Budget the full request: retrieval, prompt/context, generation, validation and optional tools. Schema validation checks structure; quality evaluation checks task success. Record provider/model/configuration version when allowed by privacy policy so regressions can be investigated. Do not hardcode a current model's price or context capacity into an unreviewed interview claim.

## Worked role scenario

A model returns a valid object with a source ID not in the authorized evidence set. Reject or route to review according to the contract; the UI must not turn that ID into an unrestricted source fetch. Provider adaptation and citation authorization are separate responsibilities.

## Interview practice: FSAI-P3-04

- **Category / difficulty:** AI integration across the product boundary / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Where should schema, business-rule and citation validation occur?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** At the server/application boundary before committing trusted business effects or presenting authoritative state. The browser can add usability checks, but cannot be the sole enforcement point.

**Followup:** How does a model change affect stored output compatibility?

**Common mistake:** Exposing raw SDK responses and provider secrets as the public interface.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Use an application-owned output contract.
- [ ] Keep provider credentials server-side.
- [ ] Evaluate model changes beyond schema validity.

## Canonical lessons and references

- [Tool calling](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md).
- [AI evaluation](../../08-LLM-RAG-and-Agent-Engineering/AI_Evaluation.md).
- [FastAPI: response models](https://fastapi.tiangolo.com/tutorial/response-model/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
