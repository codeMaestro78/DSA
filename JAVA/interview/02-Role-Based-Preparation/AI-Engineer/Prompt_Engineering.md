# Prompt engineering as a versioned experiment

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Treat a prompt as one part of a tested application contract. State the task, output requirements, relevant context and behavior when evidence is insufficient. Delimit retrieved material as untrusted data; instructions alone do not enforce permissions or prevent all prompt injection.

Version the prompt alongside model configuration, retrieval settings and evaluation data. Change one important variable at a time when identifying a cause. A few handpicked successful examples are useful debugging cases but weak evidence of broad improvement. Evaluate regressions, malformed outputs and unsupported answers as well as average quality.

## Worked role scenario

A summarizer omits exception clauses. Add an explicit requirement to preserve exceptions, but test both exception-heavy and ordinary documents. If the revised prompt adds incorrect exceptions, the change did not simply improve quality; it traded one error for another.

## Interview practice: AIE-P3-04

- **Category / difficulty:** Prompt engineering as a versioned experiment / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you compare two prompts without cherry-picking?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Use a fixed representative labeled set, predefined criteria, consistent configuration, per-case inspection and an untouched final check where feasible. Record tradeoffs rather than only wins.

**Followup:** How do you separate a retrieval regression from a prompt regression?

**Common mistake:** Calling a persuasive instruction a security boundary.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Version inputs/configuration.
- [ ] Record failing examples and abstention behavior.
- [ ] Keep trusted policy outside retrieved instructions.

## Canonical lessons and references

- [AI evaluation](../../08-LLM-RAG-and-Agent-Engineering/AI_Evaluation.md).
- [Prompt injection](../../08-LLM-RAG-and-Agent-Engineering/Prompt_Injection.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
