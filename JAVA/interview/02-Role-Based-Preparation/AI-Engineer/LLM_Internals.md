# LLM internals for application decisions

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Learn enough internals to explain behavior and constraints: tokenization, attention, autoregressive decoding, context representation and training-versus-inference differences. For a decoder model, prefill processes the supplied context and subsequent decode steps extend the output. Actual serving systems add batching, caches and scheduling; latency cannot be inferred from parameter count alone.

Application interviews need the consequence: token limits affect document packing, generated output consumes budget, and fluent continuation is not a factual verification operation. Avoid claiming a provider uses a particular private architecture from product behavior.

## Worked role scenario

A long-context request has slow first output, while a short request with a long answer has a slow completion. Measure time to first useful output separately from total time, and correlate with prompt/output tokens before deciding whether retrieval or output length is the better optimization.

## Interview practice: AIE-P3-03

- **Category / difficulty:** LLM internals for application decisions / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Why can reducing input and reducing output tokens affect different latency stages?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Input processing and sequential output generation have different work shapes. Explain the distinction, then measure the actual provider/workload because caching, batching and scheduling change the result.

**Followup:** What would a KV cache change, and what would it not prove?

**Common mistake:** Treating a larger advertised context as guaranteed effective recall.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Explain prefill and decode without claiming measured speed.
- [ ] Distinguish tokens from characters.
- [ ] State the difference between training and serving.

## Canonical lessons and references

- [Transformer internals](../../08-LLM-RAG-and-Agent-Engineering/Transformer_Internals.md).
- [Inference optimization](../../08-LLM-RAG-and-Agent-Engineering/Inference_Optimization.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
