# Convolutional neural networks

## Locality and weight sharing

Convolution applies a shared local filter over a grid. It reduces parameters relative to a fully connected map over pixels and introduces a locality prior. Equivariance means output shifts with input under suitable conditions; invariance means output stays unchanged. Stride, padding, boundaries and later layers prevent treating every CNN as exactly shift invariant.

## Shapes and count

Output length is `floor((L+2P-D*(K-1)-1)/S)+1` for valid input length L, padding P, dilation D, kernel K, stride S. With L = 32, P = 1, D = 1, K = 3, S = 2, output 16. Apply separately to height/width.

An ungrouped filter bank has Cout*Cin*Kh*Kw weights, plus Cout biases if enabled. Three input channels, eight output channels, 3×3 kernel gives 216 + 8 = 224 parameters, independent of spatial size. Under channels-first layout, (B, 3, 32, 32) with stride 1, padding 1 gives (B, 8, 32, 32); 2×2 stride 2 pooling gives (B, 8, 16, 16). Other layouts change axes.

## Cost and failure cases

Straightforward convolution uses O(B*Hout*Wout*Cout*Cin*Kh*Kw) arithmetic; kernels/hardware affect real speed. Fewer parameters do not prove lower activation memory or latency. Pooling may discard detail needed for localization.

Augmentations must preserve the task's labels. Similar images from one subject crossing splits can inflate quality; choose the grouping unit reflecting deployment. Background shortcuts can dominate prediction, so inspect counterexamples and slices. Batch normalization/dropout have mode-dependent behavior; a correct shape formula alone does not validate inference quality.

## Interview exercise — attempt first

**ID:** ML3-CNN. **Category:** ML/DL / Convolutional neural networks. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Derive 224 parameters and identify one possible image evaluation leak.

<details><summary>Expected reasoning, followup and common mistake</summary>

Count 8 filters×3 channels×3×3 plus 8 biases. A repeated subject crossing train/test can leak identity/background; group by the meaningful generalization unit.

**Followup:** What changes with grouped convolution or a channels-last layout?

**Common mistake:** Equating weight sharing with unconditional translation invariance.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 9 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/convnets.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
