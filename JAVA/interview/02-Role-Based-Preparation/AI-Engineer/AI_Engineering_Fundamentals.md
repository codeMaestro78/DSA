# Applied AI engineering fundamentals

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Separate model capability from application guarantees. The model estimates an output distribution; the application controls identity, data access, tool permissions, state transitions and budgets. Validation can check structure and selected business rules, but cannot generally prove that every generated statement is true.

Think in contracts: what inputs are trusted, which context is authorized, what output is acceptable and what happens on timeout/refusal/unsupported claims. Establish a deterministic baseline where possible. A lookup or ordinary rule may meet the requirement with less uncertainty than generation.

## Worked role scenario

A form needs a currency code from an allowed list. A validated enum can reject malformed values; the application must still check whether the selected currency matches the source document. If evidence is missing, a defined “needs review” result is safer than inventing confidence.

## Interview practice: AIE-P3-02

- **Category / difficulty:** Applied AI engineering fundamentals / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** An answer passes JSON validation. What remains to evaluate?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Correctness against source facts, permission scope, completeness, calibration/abstention where relevant, and downstream behavior. Structure alone establishes none of these.

**Followup:** Which checks can be deterministic?

**Common mistake:** Equating a successful HTTP response or valid JSON with task success.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Name application and model responsibilities separately.
- [ ] Define unsupported/unknown behavior.
- [ ] Compare against a simple baseline.

## Canonical lessons and references

- [LLM architecture](../../08-LLM-RAG-and-Agent-Engineering/LLM_Architecture.md).
- [AI evaluation](../../08-LLM-RAG-and-Agent-Engineering/AI_Evaluation.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
