# Context windows and context management

## Capacity versus useful evidence

A context window bounds what the model can receive and/or generate according to its interface. Exact accounting is provider/model-specific. A large advertised window does not guarantee reliable use of every position, citation correctness or acceptable latency. Separate syntactic fit from measured task quality.

## Budget arithmetic

For an illustrative total budget of 8,000 tokens, reserve 1,000 for instructions/tools, 1,500 for output and 2,000 for history; at most 3,500 remain for retrieved context under that simplified accounting. This is a teaching budget, not a specific provider limit. Message wrappers, special tokens and any separately accounted tokens must be included according to the actual API.

A budget manager should operate on the full request representation and define what happens when it does not fit: reject, summarize, select fewer passages or ask the user to narrow the query. Silently cutting the end can remove the question, evidence or tool schema. Do not promise an exact ceiling from a character-count heuristic.

## Retention policy

Keep system constraints and the latest user intent. Preserve tool-call/result pairing and unresolved actions. Select history for relevance, recency and provenance; summaries lose detail and can introduce errors. A summary should identify its source span/version and not become a higher-trust instruction. Long-term stored memory remains subject to access/deletion rules.

Worked example: a conversation changes a shipping address after an earlier address was summarized. The context assembler must resolve the current authoritative value, not simply append two conflicting memories. For actions, re-read authoritative state rather than treating a conversational summary as permission.

## Retrieval and compression

Choose passages based on relevance and source coverage before compressing. Deduplicating overlapping chunks can save tokens without discarding independent evidence. Query-focused compression can accidentally remove a qualifier or negative statement; compare support after compression. Preserve original spans for citation validation.

## Quality checks and tradeoffs

Evaluate with relevant evidence near the beginning, middle and end; include conflicting, irrelevant and long-history cases. Measure answer support and task completion, not only successful API acceptance. More context can raise prefill latency and cost while lowering signal-to-noise. A smaller, well-selected context can be preferable, but only task evidence can establish that improvement.

Cancellation and token ceilings must also cover multi-call workflows. Four individually bounded calls can exceed a request's global budget unless the orchestrator tracks aggregate usage and deadline.

## Interview practice — P3-AI-05

**Question:** A request fits the token limit but answers from an obsolete earlier message. What should you inspect?

- **Category/topic:** AI engineering / Context windows and context management.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Inspect history selection, summary provenance/conflicts, authoritative current state and evidence placement. Token fit does not establish correct evidence use.

**Followup:** Which parts of a tool interaction must remain consistent when trimming history?

**Common mistake:** Treating a summary as lossless or assuming a larger window guarantees better answers.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Vaswani et al.: Attention Is All You Need](https://arxiv.org/abs/1706.03762) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
