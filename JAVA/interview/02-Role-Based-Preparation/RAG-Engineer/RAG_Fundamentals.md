# RAG fundamentals for interview reasoning

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Retrieval-augmented generation conditions a generated answer on retrieved evidence. Modern application pipelines vary; the original RAG paper describes a specific trained architecture, not every current search-plus-prompt system. Explain which interpretation your project uses.

Separate retrieval relevance, permission correctness, context selection and answer support. A passage can be relevant but unauthorized, authorized but obsolete, or correct but cited for the wrong sentence. RAG reduces some knowledge-access limitations, but neither retrieval nor citation formatting guarantees factuality.

## Worked role scenario

Question: “What is the current refund period?” Retrieved text includes an old 30-day policy and a new 14-day policy. Source dates and authority matter. A correct answer requires selecting the applicable evidence, explaining any ambiguity and citing the actual supporting version, not averaging the two numbers.

## Interview practice: RAGE-P3-02

- **Category / difficulty:** RAG fundamentals for interview reasoning / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Why can a RAG answer be wrong even when the retriever found the correct passage?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** The passage may be dropped/truncated, mixed with conflicting context, ignored during generation, or cited incorrectly. Evaluate each boundary and include an insufficient-evidence path.

**Followup:** How would you distinguish relevance from authority/freshness?

**Common mistake:** Treating retrieved text as trusted instructions or universally current truth.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Define retriever/generator responsibilities.
- [ ] Check permissions and source version.
- [ ] Explain a supported abstention.

## Canonical lessons and references

- [Canonical RAG architecture](../../08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md).
- [Evaluation](RAG_Evaluation.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
