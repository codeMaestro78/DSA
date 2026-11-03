# LoRA and parameter-efficient fine-tuning

## Orientation and count

For W:(out, in), LoRA represents DeltaW=(alpha/r)*B*A, B:(out, r), A:(r, in), freezing W while training selected adapters. Adapter count is r*(in+out), excluding other deliberately trainable modules/biases. If in=4, out=3, r=2, adapters have 14 weights versus 12 base weights: this small shape is not a saving. Savings depend on rank being small enough.

## Computation

For row batches X, output is XW.T+(alpha/r)*(X A.T)B.T. A compatible representation can merge the update into full weights, or keep a separate path for adapter switching. Quantized backbones and deployment formats need their own contract. The local suite checks one float64 merged/unmerged algebra fixture with scale 1; it does not train an adapter or profile memory/latency.

## Rank and memory

Low rank constrains the update subspace. Target modules, rank, scaling, initialization, data and optimizer affect quality. Reduced trainable count saves some gradient/optimizer state but does not remove backbone forward/backward activations or all compute. Quality requires evaluation, not a rank formula.

## PEFT and QLoRA

PEFT includes multiple adaptation approaches. QLoRA combines a frozen quantized backbone with adapters using particular original-paper mechanisms. Historical hardware/quality results do not guarantee success for arbitrary models, context lengths or implementations.

## Failure cases

Wrong base model/tokenizer/template, adapter scaling, target modules or checkpoint can invalidate comparisons. Validate deployed merged/unmerged paths. Frozen base weights do not guarantee no memorization of sensitive adaptation data. Lower weight memory is not automatically lower end-to-end latency.

## Interview exercise — attempt first

**ID:** ML3-LORA_AND_PEFT. **Category:** ML/DL / LoRA and parameter-efficient fine-tuning. **Difficulty:** Advanced. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Does rank 8 mean eight parameters or guaranteed speedup?

<details><summary>Expected reasoning, followup and common mistake</summary>

No: one selected out×in layer has 8*(in+out) adapter weights under this convention. Latency depends on merging, kernels, batching and workload, requiring actual deployment measurement.

**Followup:** At what ranks would the example adapter no longer save parameters?

**Common mistake:** Calling rank the total parameter count or inferring latency from weight count.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [LoRA / Hu et al.](https://arxiv.org/abs/2106.09685).
- [QLoRA / Dettmers et al.](https://arxiv.org/abs/2305.14314).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
