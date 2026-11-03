# Neural networks

## Composition and shapes

For row batches X:(B, d), W1:(h, d), b1:(h,), hidden H=ReLU(XW1.T+b1):(B, h). W2:(c, h), b2:(c,) produce logits (B, c). Trainable count is hd+h+ch+c. Without nonlinearities, composing affine maps still yields one affine map; extra linear layers alone do not create arbitrary nonlinear boundaries.

## Worked example

For d = 2, h = 3, c = 1, parameters total 6 + 3 + 3 + 1 = 13. Five examples give hidden (5, 3), output (5, 1). For binary loss, decide whether predictions/targets are (5,) or(5, 1); accidental broadcasting between them can create 25 pairwise residuals instead of 5.

## Learning mechanism

Forward computes predictions, scalar loss measures mismatch, backprop computes derivatives and optimizer updates parameters. Training retains intermediate state needed for derivatives. Ordinary inference normally disables training-only behavior and gradient recording; mode switching and disabling gradients are distinct controls.

Initialization, activation and normalization affect gradient/activation scale. ReLU has zero derivative in its negative region; sigmoid/tanh can saturate. Residual paths can help some deep regimes, not guarantee convergence. A larger model does not repair bad labels or a disconnected objective.

## Cost and diagnosis

Two dense layers require O(Bdh+Bhc) straightforward arithmetic, excluding activation/loss overhead. Parameter count differs from activation and optimizer memory. Overfit a tiny batch, inspect shapes and updates, and compare a simple baseline before adding depth. Memorizing a fixture is debugging evidence, not held-out quality. The local logistic routine validates one differentiable model, not every architecture in this chapter.

## Interview exercise — attempt first

**ID:** ML3-NEURAL_NETWORKS. **Category:** ML/DL / Neural networks. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why do affine layers without nonlinearity collapse to one affine map?

<details><summary>Expected reasoning, followup and common mistake</summary>

Expand(XW1.T+b1)W2.T+b2 into an effective weight and bias. Nonlinear behavior requires a nonlinear representation or activation, not depth alone.

**Followup:** Which state must training retain that ordinary inference may not need?

**Common mistake:** Confusing parameter count with activation memory or creating pairwise broadcast errors.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 6 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/mlp.html).
- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
