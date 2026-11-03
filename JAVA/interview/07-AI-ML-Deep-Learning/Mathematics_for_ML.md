# Mathematics for ML

## One model connects the foundations

For n examples, d features, X:(n, d), w:(d,), z=Xw+b:(n,). A sigmoid maps each logit to a binary probability. A scalar objective measures mismatch; calculus differentiates it; optimization updates parameters. Shapes are part of correctness.

For binary labels y ∈{0, 1}, mean logistic loss is `L = mean(softplus(z)-y*z)`. Its gradient is `X.T@(sigmoid(z)-y)/n` for w and `mean(sigmoid(z)-y)` for b. Stable evaluation uses logaddexp rather than directly exponentiating a large z.

## Derive the result

For one example, d softplus(z)/dz=sigmoid(z), and d(y*z)/dz=y. The chain rule multiplies p-y by x for a weight derivative and by 1 for the bias. Average examples once, not twice. The gradient shape must match the parameter. With x=[1, 2], w=[0, 0], b = 0, y = 1, p = 0.5 and gradients are[-.5,-1] and-.5.

## Concepts to distinguish

A norm measures magnitude; a dot product also depends on alignment. Probability is not an arbitrary score. A gradient is local sensitivity, not a parameter value. Rank concerns independent directions; rectangular matrices admit SVD even when eigenvector analysis is not directly applicable. Minimizing empirical loss does not establish low future loss.

## Practice boundary

Use simple numerical derivations before advanced proofs. The local NumPy suite checks the included logistic gradient against finite differences on small fixtures, but does not validate an entire neural framework or arbitrary numerical magnitude. Floating-point arithmetic requires finite-value and stability checks beyond symbolic equations.

## Interview exercise — attempt first

**ID:** ML3-MATHEMATICS_FOR_ML. **Category:** ML/DL / Mathematics for ML. **Difficulty:** Beginner. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Derive the mean logistic weight/bias gradients with all shapes.

<details><summary>Expected reasoning, followup and common mistake</summary>

At logits the residual is p-y. Multiply by X.T and divide by n; bias is mean residual. Explain the effect of sum versus mean and avoid accidental (n, 1)/(n,) broadcasting.

**Followup:** How does replacing a mean loss with a sum alter this gradient?

**Common mistake:** Missing the averaging factor or allowing a target-shape broadcast.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 2 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/linear_algebra.html).
- [Deep Learning chapter 3 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/prob.html).
- [Deep Learning chapter 4 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/numerical.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
