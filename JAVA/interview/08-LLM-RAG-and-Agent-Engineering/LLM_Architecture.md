# LLM architecture: model versus application

## Fundamental distinction

A language model maps a context to a distribution over possible next tokens. A product around it manages identity, history, retrieval, tools, billing and failure recovery. Model weights are not a searchable database of attributable facts, and the product's visible behavior does not disclose its private architecture.

A common teaching model is a decoder-only Transformer: token IDs → embedding vectors → repeated causal-attention/feed-forward blocks → vocabulary logits → sampling or selection. Encoder-only and encoder–decoder architectures serve different tasks; do not describe every LLM as decoder-only. The original Transformer paper describes an encoder–decoder translation architecture, not a disclosure of any current proprietary model.

## Shapes and training objective

For batch B, length T, hidden width D and vocabulary V, token IDs have shape [B,T], hidden activations [B,T,D], and output logits [B,T,V]. Next-token cross-entropy compares each prediction with the subsequent target token. Padding and task-specific masks determine which positions contribute to loss. Dense logits need not all be retained in an optimized serving implementation.

Pretraining learns broad statistical patterns. Instruction tuning changes responses toward task instructions; preference methods modify behavior according to another training signal. None turns a generated assertion into verified evidence. Retrieval, calculators and authoritative APIs remain useful when provenance, freshness or exact arithmetic matters.

## Worked application trace

A user asks an internal leave-policy question. The gateway verifies identity; retrieval selects authorized, versioned passages; the prompt provides those passages and output requirements; the model generates a draft; the application checks citations and returns a supported answer or abstention. Only the model step predicts tokens. The surrounding application must still enforce permissions and handle missing evidence.

If the model generates a payroll query, that is a proposed action. The application validates and authorizes it before any database operation. A model's ability to name a tool does not grant authority to execute it.

## Failure and tradeoff

A model can sound confident about information absent from its context. Increasing parameter count or sampling determinism does not establish factual correctness. A larger model may improve an evaluated task while increasing cost, latency or operational burden; choose using task-specific evidence rather than parameter count alone.

Distinguish training compute, prefill over the input, and iterative generation. A single API request can hide multiple application/model calls; record the actual call graph before discussing latency.

## Interview practice — P3-AI-01

**Question:** Explain which components are required to answer a permission-sensitive policy question and which are inside the model.

- **Category/topic:** AI engineering / LLM architecture: model versus application.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Separate token prediction from identity, retrieval, permissions, citations and lifecycle. Describe missing-evidence behavior and one failure at the tool boundary.

**Followup:** What changes when the source document is revoked during the request?

**Common mistake:** Calling the model itself a database, or claiming an illustrative product diagram is a proprietary architecture.

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
