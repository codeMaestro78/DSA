# Calculus and gradients

## Sensitivity and composition

A derivative measures local sensitivity; a partial derivative varies one coordinate. The gradient collects a scalar function's derivatives, the Jacobian describes vector-output derivatives and the Hessian collects second derivatives of a scalar objective.

For z=x·w+b with logistic cross entropy, the logit derivative simplifies to p-y. Chain rule gives x*(p-y). For squared loss L=||Xw-y||²/(2n), grad_w=X.T@(Xw-y)/n. Without the 1/2, the gradient has factor 2. Compare the same objective before comparing derivatives.

## Numerical gradient check

A central difference `(L(w+eps*e_j)-L(w-eps*e_j))/(2*eps)` approximates a derivative. Too-large eps introduces approximation error; too-small eps amplifies floating-point cancellation. Use a small float64 fixture and a justified tolerance. Near a ReLU kink the derivative is not unique; move to a smooth point or explain the convention.

A passing check validates the observed routine/cases, not all possible graphs. Check input/output shapes, reduction, dtype, dependency and the parameter actually updated.

## Worked learning-rate counterexample

For f(w)=w² at w = 3, gradient 6. Small negative-gradient steps decrease f locally, but eta = 2 sends w to -9, increasing f from 9 to 81. A valid descent direction does not justify every step size. In a network, saturation, detachment or a wrong objective can produce unhelpful gradients even when automatic differentiation is functioning correctly.

## Interview exercise — attempt first

**ID:** ML3-CALCULUS. **Category:** ML/DL / Calculus and gradients. **Difficulty:** Beginner. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** A gradient differs by exactly batch size. What do you inspect?

<details><summary>Expected reasoning, followup and common mistake</summary>

Compare sum versus mean reductions and whether division occurred twice. Ensure analytic and numerical paths differentiate the same scalar loss.

**Followup:** Why can finite differences disagree at a ReLU kink?

**Common mistake:** Comparing derivatives of different reductions or using an excessively tiny step.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 4 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/numerical.html).
- [Deep Learning chapter 6 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/mlp.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
