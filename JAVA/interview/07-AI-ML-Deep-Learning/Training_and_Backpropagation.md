# Training and backpropagation

## Complete update

Batch → training-fit preprocessing → forward → scalar loss → clear obsolete gradients → backward → intentional gradient processing → optimizer step. Accumulation is valid only with correct weighting and step frequency. Ordinary evaluation does not update parameters.

## Logistic derivation

For X:(n, d), w:(d,), a scalar bias and binary labels y:(n,), L = mean(softplus(Xw+b)-y*(Xw+b)). The logit derivative is(p-y)/n, yielding grad_w=X.T@(p-y)/n and grad_b = mean(p-y). Every gradient matches its parameter shape.

The local routine uses stable logaddexp and sigmoid expressions on float64 fixtures. It rejects nonfinite input/logits/loss/gradients and nonscalar bias to avoid silent broadcasting. Its numeric boundary also rejects complex, numeric-string/object and boolean feature/weight/bias data before conversion; boolean binary labels remain allowed. This prevents complex arrays from losing imaginary components during a float64 cast. Finite operands can still overflow products at extreme magnitudes; rejection is preferable to treating NaN as a valid result.

## Worked initial step

One positive example x=[1, 2], w=[0, 0], b = 0 gives residual -0.5. At rate 0.1, weights become[.05,.1], bias 0.05; the next logit is 0.3. The example demonstrates one local loss-reducing direction, not held-out generalization.

## Validation and debugging

[The suite](../validation/ml/check_ml.py) compares analytic gradients with central differences on 25 seeded fixtures and checks a small step against the same objective. It does not validate every neural layer or optimizer.

Debug labels, shape, dtype, reduction, dependency, gradient and actual parameter changes. Overfit a tiny batch before scaling. Log data/split/code/runtime, preprocessing, configuration and selected checkpoint. A seed helps repeatability but does not establish bitwise determinism across devices and kernels.

## Interview exercise — attempt first

**ID:** ML3-TRAINING_AND_BACKPROPAGATION. **Category:** ML/DL / Training and backpropagation. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** The loss changes but weights do not. What do you inspect?

<details><summary>Expected reasoning, followup and common mistake</summary>

Check optimizer parameter ownership, step call, disconnected/zero gradients and accidental reinitialization. A loss change can arise from changing data or stochastic behavior, so compare parameters across one controlled step.

**Followup:** What evidence would show the optimizer updates the intended parameters?

**Common mistake:** Taking a changing loss as proof that weights or held-out quality improved.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Deep Learning chapter 6 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/mlp.html).
- [Deep Learning chapter 4 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/numerical.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
