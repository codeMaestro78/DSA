# Inference optimization

## Measure the stage before changing it

Separate queue time, prompt preparation/retrieval, prefill, time to first visible output, decoding, postprocessing and network delivery. Time to first token from the provider can differ from first visible output if the application buffers the stream. Throughput, per-request latency and cost are separate objectives.

## Prefill and decode

Prefill processes prompt positions; autoregressive decode emits successive tokens using prior state. Prompt length, generated length, batching, hardware, attention implementation and cache configuration affect behavior. A shorter output can reduce latency without changing the model; a longer prompt can increase prefill work even if the answer is short.

Illustrative trace: retrieval 300 ms, queue 100 ms, first model output 500 ms later, but the server buffers another 900 ms before returning. The user sees first output after 1,800 ms from the sequence assumed here, not the model's 500 ms stage alone. These are synthetic measurements; an actual trace must define start/end boundaries and overlapping stages.

## Techniques and their costs

KV caching reuses prior key/value computation during decoding. Prefix caching can reuse eligible shared-prefix work under the serving system's exact rules; cache isolation and version keys matter. Continuous batching improves utilization by scheduling active requests, but admission policy and contention can affect tails. Quantization reduces representation/storage requirements with accuracy and kernel/hardware tradeoffs. Speculative decoding uses a draft/verification process; a valid exact-distribution implementation is not equivalent to accepting unchecked draft text.

FlashAttention is an IO-aware exact-attention technique; PagedAttention addresses KV-memory management in a serving system. Their historical reported results do not establish speedups on your workload. Do not replace a bottleneck measurement with a paper's headline multiplier.

## Optimize the application too

Avoid unnecessary serial model calls, repeated retrieval or unbounded retries. Stream only when partial results are safe and the interface handles cancellation/error states. Cache answers only when tenant identity, permissions, source freshness, model/prompt versions and request semantics permit it. A cache hit that leaks another user's answer is not a valid optimization.

## Validation plan

Compare the same query distribution, output constraints, concurrency and quality criteria before/after. Record warm/cold behavior, failures, p50/p95/p99 where sample support permits, tokens, cache hits and cost assumptions. Small local arithmetic checks are not a serving benchmark. If quality regresses, report the tradeoff instead of claiming unqualified improvement.

## Interview practice — P3-AI-06

**Question:** The model has fast first-token time but users still wait. Where would you look?

- **Category/topic:** AI engineering / Inference optimization.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Trace buffering, transport, frontend rendering and upstream stages using consistent clocks/boundaries. First-provider-token latency alone cannot locate the user-visible bottleneck.

**Followup:** When can batching improve throughput while worsening an individual request tail?

**Common mistake:** Promising a paper speedup or attributing end-to-end latency to an unmeasured model stage.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Dao et al.: FlashAttention](https://arxiv.org/abs/2205.14135) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Kwon et al.: PagedAttention](https://arxiv.org/abs/2309.06180) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
