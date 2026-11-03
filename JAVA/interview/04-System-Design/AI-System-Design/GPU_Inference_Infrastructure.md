# Reason about GPU inference infrastructure

## Scope and prompt

**AI-SD-12 — Advanced/Expert extension; ML infrastructure; capacity/design; company-neutral.** Explain memory and scheduling for autoregressive inference. Values below describe an illustrative decoder, not a real named model or a hardware benchmark. Study ordinary inference contracts first.

<details><summary>Resource model and failure tradeoffs</summary>

### Memory is more than weights

Raw parameter storage is parameter count × bytes per parameter. Seven billion parameters at two bytes each require 14 billion bytes before quantization metadata, runtime buffers, activations, allocator overhead and KV cache. Decimal GB and binary GiB differ. Do not infer that a nominal 16GB device can safely serve the model from weights alone.

For a simplified decoder KV cache, use:

`KV bytes = 2 × layers × KV_heads × head_dim × cached_tokens × active_sequences × bytes_per_element`

The factor 2 represents keys and values. Using 32 layers, 8 KV heads, head dimension 128, 4,096 cached tokens per sequence, 4 active sequences and two-byte elements gives **2,147,483,648 bytes = 2 GiB**. Use KV heads, not query heads, for this grouped-query example. Real engines may add block padding, metadata, replication/sharding and model-specific layouts; this is a raw tensor estimate.

### Prefill, decode and batching

Prefill processes prompt context and initializes cache. Decode produces subsequent tokens with cached state. Input/output length distributions determine different resource demands. Continuous batching can admit/release sequences during decoding; ordinary dynamic batching of compatible stateless requests is a distinct scheduler pattern. Larger batches may improve throughput but can harm queueing or latency; measure time to first token, per-output-token behavior and completion time under representative load.

Prefix caching can reuse compatible prior prefix computation. It does not make arbitrary requests identical, remove decode work or justify sharing private cross-tenant state. Pin model/tokenizer/cache policy and consider isolation, eviction and side channels. The vLLM document is one implementation reference, not a guarantee for every serving engine.

### Admission and degradation

Reserve capacity using conservative sequence/token limits and memory headroom. On pressure, reject/defer or route only to an evaluated compatible fallback. Repeatedly admitting oversized requests and hoping OOM recovery works is not a policy. Bound queues and cancellation; a client timeout may leave device work running unless cancellation is supported and propagated.

Multi-GPU sharding can reduce per-device state while increasing communication and operational complexity. Different quantization formats require compatible kernels and may alter quality. Compare measured quality/latency/cost on the target task before choosing a lower precision or a larger replica count.

</details>

## Practice

Why does halving KV heads in the simplified formula halve raw KV storage but not total serving memory?

<details><summary>Expected reasoning</summary>

Only the cache component changes under the formula's assumptions. Weights, activations, runtime buffers, fragmentation and replicated state remain. A model change can also change quality or kernels; this calculation alone does not establish a deployable optimization.

</details>

## Validation and checklist

Arithmetic is checked in [AI architecture calculations](../../validation/ai-design/check_architecture.py). No GPU runtime or serving benchmark was executed.

- [ ] Weight, KV and overhead estimates are separated.
- [ ] KV heads, sequence lengths, precision and units are explicit.
- [ ] Scheduler/quality effects require measurement, not just arithmetic.

## References

- [NVIDIA Triton: batchers](https://docs.nvidia.com/deeplearning/triton-inference-server/user-guide/docs/user_guide/batcher.html).
- [vLLM: prefix caching design](https://docs.vllm.ai/en/latest/design/prefix_caching/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
