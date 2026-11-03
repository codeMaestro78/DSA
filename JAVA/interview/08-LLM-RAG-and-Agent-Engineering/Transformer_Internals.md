# Transformer internals and causal attention

## From token vectors to contextual vectors

Attention lets each position combine information from allowed positions. For one head, Q=XWq, K=XWk and V=XWv. With T positions and head dimension dₖ, Q and K have [T,dₖ]; QKᵀ has [T,T]. Attention is softmax(QKᵀ/√dₖ + mask)V. The softmax is row-wise over allowed keys. Batch/head axes add dimensions without changing that relationship.

A decoder uses a causal mask so position i cannot read future positions j>i. Padding masks exclude invalid positions. A fully masked row needs defined implementation behavior; blindly softmaxing all negative infinities can produce NaNs. Masks are not interchangeable with application authorization: a causal mask prevents future-token access, not cross-tenant document retrieval.

## Small causal trace

At position 2 in a three-token sequence, the model may attend to positions 0, 1, 2. Position0 may attend only to 0. If future tokens affect position 0's output in a causal test, inspect mask polarity, broadcasting and cached-position handling. Equal dimensions do not prove the axes were applied correctly.

Multiple heads learn different projections; their outputs are concatenated/projected. Feed-forward transformations, residual connections, normalization and positional information complete the block. Modern implementations differ in normalization order, positional encoding, gating, head sharing and attention pattern; the original paper is foundational, not a universal current architecture specification.

## Cost and inference

For full dense attention, the score interactions scale as O(T²dₖ) per head, while other projections/feed-forward work has additional width-dependent cost. A naïve materialized score tensor uses O(T²) elements per head. IO-aware attention can compute exact attention without retaining that entire matrix in high-bandwidth memory; quadratic pair interactions do not automatically disappear.

During autoregressive inference, cached keys/values avoid recomputing earlier K/V projections. The new query still attends to the relevant past. KV memory grows with sequence length, layer count, batch and KV-head configuration. Do not equate a KV cache with a cache of completed answers.

## Interview reasoning

Explain tensor shapes before formulas. Distinguish masking, positional information, attention computation and application permission filtering. A test that compares batched and single-item behavior or perturbs only future tokens can reveal bugs that a shape check misses. Numerical correctness and production throughput require different validation.

## Interview practice — P3-AI-04

**Question:** Why does a KV cache speed autoregressive decoding without making the total generation cost independent of context length?

- **Category/topic:** AI engineering / Transformer internals and causal attention.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** It reuses past keys/values and avoids recomputing their projections, but each new query still interacts with the permitted cached history; memory and attention work depend on retained context.

**Followup:** How can a future-token perturbation test expose a wrong causal mask?

**Common mistake:** Saying FlashAttention is approximate or that caching removes all attention work.

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
- [Dao et al.: FlashAttention](https://arxiv.org/abs/2205.14135) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Kwon et al.: PagedAttention](https://arxiv.org/abs/2309.06180) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
