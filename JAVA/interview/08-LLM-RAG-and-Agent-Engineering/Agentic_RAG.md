# Agentic RAG: adaptive retrieval with limits

## What changes from a fixed RAG pipeline

A fixed pipeline performs a known retrieval/assembly/generation sequence. An agentic variant lets a model choose searches, decompositions, tools or stopping decisions. This can help ambiguous multi-part queries, but adds calls, budget risk, inconsistent branches and more difficult evaluation. Do not add an agent merely to rename ordinary retrieval.

## Worked bounded flow

Question: “Compare contractor travel rules in the two latest policy versions.” A controller resolves the versions from authoritative metadata, retrieves both, checks whether each required section is present, and permits at most one targeted followup search per missing section. It then compares supported differences or reports a gap. A model can help formulate targeted queries, while version/permission constraints remain fixed application inputs.

A poor version of this flow repeatedly searches broad terms until a plausible answer appears. It may drop the version constraint, retrieve an obsolete source and spend the whole budget without recognizing missing evidence.

## State and contracts

Track original intent, hard filters, subquestions, retrieved source/version/spans, remaining budget and unresolved evidence. Store structured observations such as no authorized match, timeout or contradictory versions. These observations should drive different actions; a timeout is not proof that the corpus lacks evidence.

Apply authorization before context assembly and before subsequent tool execution. The agent must not route around a denied source using another connector. Query expansion, graphs and summaries can all preserve sensitive information even after an original passage is filtered, so derivative permissions matter.

## Stop and abstain

Define successful evidence coverage, no-progress detection, maximum calls, aggregate tokens/cost and deadline. If a subquestion remains unsupported, return a partial answer with the gap or ask for clarification. A model saying “I have enough evidence” is an input to evaluation, not proof of support.

## Compare with baseline

Use the same corpus, queries, permissions and labels for fixed and adaptive pipelines. Measure supported task completion, source coverage, redundant calls, tail latency and cost. Include unanswerable questions and contradictory documents. A more detailed answer is not automatically better if it adds unsupported claims.

The local bounded-loop and metric tests illustrate contracts but do not execute an agentic RAG system. Full quality claims require actual retrieval/model runs and reviewed outputs under the target task distribution.

## Interview practice — P3-AI-23

**Question:** When does adaptive retrieval justify its extra cost?

- **Category/topic:** AI engineering / Agentic RAG: adaptive retrieval with limits.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** When a controlled comparison shows better supported task completion or needed evidence coverage under acceptable latency/cost, with hard constraints and bounded failure behavior preserved.

**Followup:** What distinguishes no evidence from a transient search failure?

**Common mistake:** Searching until something sounds plausible or allowing query rewrites to weaken permissions/version constraints.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Yao et al.: ReAct](https://arxiv.org/abs/2210.03629) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Edge et al.: GraphRAG](https://arxiv.org/abs/2404.16130) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
