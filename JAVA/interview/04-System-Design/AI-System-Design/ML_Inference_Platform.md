# Design an ML inference platform

## Candidate prompt

**AI-SD-09 — Intermediate/Advanced; ML/platform; design; company-neutral.** Serve versioned models with predictable input/output contracts, canary rollout and rollback. Distinguish small tabular models, batch jobs and autoregressive generation rather than assuming one serving policy fits them all.

<details><summary>Reference control and request paths</summary>

### Control plane

The registry owns immutable model artifacts, tokenizer/preprocessing versions, schema, evaluation results and approved deployment configurations. Deployment control resolves a named route to a particular artifact/configuration and records changes. Build compatible runtime images and verify that weights, code and preprocessing match. A model file alone is not the full prediction function.

### Data plane

Authenticate → validate shape/type/size → resolve approved model version → acquire capacity → apply matching transformations → infer → validate/postprocess → return result and useful version metadata. The service owns request deadlines, concurrency and tenant policy. For sensitive data, the inference backend needs the same access/retention constraints as the API.

### Batch and queue policy

Batching can improve utilization but adds queueing and may violate latency goals. Triton's dynamic batching guidance concerns compatible stateless requests; sequence/stateful workloads need different handling. Do not equate ordinary static batch collection with LLM continuous decoding schedules. Test representative shape, length and arrival distributions, not a single repeated input.

An illustrative service receiving 40 requests/s with mean residence time 0.25s has mean 10 concurrent requests at that boundary under stable conditions. This is not a worker/GPU count or tail-latency guarantee. Memory, batch shapes and failure headroom still need measurement.

### Rollout and diagnosis

Shadow traffic can compare outputs without affecting users, provided privacy and duplicate-work costs are acceptable. A canary uses a defined eligible population and quality/latency/error gates. Route rollback must restore compatible model and preprocessing versions. Old cached predictions and in-flight requests need version-aware interpretation.

Monitor schema rejections, unavailable capacity, timeouts, prediction distribution and task outcome where labels become available. Distribution drift is a signal to investigate, not proof of worse quality; stable aggregate averages can conceal important subgroup failures. Late labels need a joining/retention strategy.

</details>

## Practice

A new model has the same tensor shape but uses a different feature order. Can the existing API safely deploy it?

<details><summary>Expected reasoning</summary>

Shape compatibility is insufficient. Bind semantic schema/preprocessing versions and evaluate the complete prediction function. Reject or transform incompatible inputs. A rollback that restores only weights may leave the error in place.

</details>

## Checklist

- [ ] Model, transforms and schema form one versioned contract.
- [ ] Queueing, concurrency and admission controls are bounded.
- [ ] Canary/rollback and delayed-label evaluation are specified.

## References

- [Martin Zinkevich: Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml).
- [NVIDIA Triton: batchers](https://docs.nvidia.com/deeplearning/triton-inference-server/user-guide/docs/user_guide/batcher.html).
- [Google SRE: alerting on SLOs](https://sre.google/workbook/alerting-on-slos/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
