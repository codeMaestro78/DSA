# Bias, variance and generalization

## The decomposition's scope

For squared-error regression at a fixed input under a suitable data model, expected error across training samples decomposes into squared bias, estimator variance and irreducible noise. Bias compares mean learned prediction to the conditional mean; variance describes prediction changes across training samples. This is not a universal exact equation for accuracy or every deep-network learning curve.

## Worked mental experiment

Repeatedly fit independent samples. A constant model can miss a curved relationship consistently: bias. An unconstrained tree can fit each sample closely yet vary greatly: variance. Data, representation, capacity and regularization can change the outcome; complexity is not a one-variable rule.

## Diagnose observations

| Observation | Hypothesis/check |
|---|---|
| Train loss never decreases | Verify labels, gradient, update and rate; overfit a tiny batch |
| Train improves; validation worsens | Test regularization/early stopping under valid split |
| Both poor after fitting | Examine representation, signal, label quality and bias |
| Seeds/sample draws vary greatly | Report variation and inspect sample size/stability |

High train error can mean optimization failure rather than insufficient capacity. Low train/high validation error suggests a generalization issue but still requires leakage, distribution and metric checks.

## Selection boundary

Validation repeatedly used for tuning becomes part of selection. An untouched final test evaluates the chosen procedure. Many hyperparameter/seed attempts can overfit validation even without fitting individual models on it. Report selection method, uncertainty and important slices rather than only the best run.

## Interview exercise — attempt first

**ID:** ML3-BIAS_VARIANCE_TRADEOFF. **Category:** ML/DL / Bias, variance and generalization. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why not immediately increase model size for high training error?

<details><summary>Expected reasoning, followup and common mistake</summary>

First verify computation, labels, objective and optimization. Capacity does not repair a detached gradient or invalid target; a tiny-batch fit is a useful discriminating check.

**Followup:** What would a tiny-batch overfit test distinguish from a capacity problem?

**Common mistake:** Treating the squared-error decomposition as a universal exact accuracy equation.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 5 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/ml.html).
- [Deep Learning chapter 7 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/regularization.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
