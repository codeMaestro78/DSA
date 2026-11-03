# Quantization

## Representation contract

Quantization maps values to a restricted numeric representation. Distinguish weight-only/activation quantization, post-training/quantization-aware methods, and stored bit width versus actual arithmetic. Smaller files do not guarantee faster kernels.

## Worked affine quantizer

Use q=clip(round(x/s)+z, qmin, qmax), x_hat=s*(q-z), scale s>0. For symmetric int8 levels [-127, 127], z=0 and range [-1, 1], choose s=1/127. x=0.3 becomes q=38 and x_hat≈0.29921. Nearest rounding error is at most s/2 away from saturation, up to numeric tolerance. Clipping can produce much larger error. The local suite checks this bound on a small bounded fixture, not a packed kernel.

## Granularity and calibration

Per-tensor/per-channel scales balance metadata and flexibility. Calibration data should represent deployment; final test should not select the quantization procedure. Outliers can dominate scale and reduce resolution. Supported formats, unquantized modules and kernels determine compatibility.

## Memory estimate

P weights at b bits need roughly P*b/8 raw bytes. One billion 4-bit weights give 0.5 billion raw bytes, excluding scales, zero points, packing, activations, KV cache, runtime buffers and other states. A fit estimate must include workload and representation. Training can add optimizer/gradient state.

## Evaluation and limits

Quantization can change outputs and slice quality; evaluate the intended task and operational path. QLoRA does not mean every training state/arithmetic operation is 4-bit. No GPU quantization, throughput or memory profile was run here; source descriptions and toy rounding checks remain narrower evidence.

## Interview exercise — attempt first

**ID:** ML3-QUANTIZATION. **Category:** ML/DL / Quantization. **Difficulty:** Advanced. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can serving memory exceed the raw weight estimate?

<details><summary>Expected reasoning, followup and common mistake</summary>

Metadata, unquantized modules, activation/KV caches, runtime buffers and batch/context size add memory. Training adds further states. State exact precision/workload before claiming a device fit.

**Followup:** When does the half-step rounding-error bound fail?

**Common mistake:** Ignoring clipping, metadata and activation/KV memory in a fit claim.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [QLoRA / Dettmers et al.](https://arxiv.org/abs/2305.14314).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
