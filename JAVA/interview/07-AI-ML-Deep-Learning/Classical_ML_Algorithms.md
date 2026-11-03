# Classical ML algorithms

## Mechanism comparison

| Method | Main idea | Limit to explain |
|---|---|---|
| Linear/logistic | Linear feature score; regression or binary likelihood | Missing interactions unless represented; scaling/collinearity |
| Tree | Nonlinear rule splits | Overfitting/instability; depth and leaf constraints |
| Forest | Average randomized trees | Inference/model size; probability calibration not guaranteed |
| Boosting | Add weak models to reduce an objective | Tuning/overfitting; no universal superiority |
| k-nearest neighbors | Predict using nearby stored cases | Distance/scale and prediction cost |
| Naive Bayes | Factor likelihood under conditional independence | Assumption can be wrong despite useful classification |
| SVM | Margin-based decision, optionally kernels | Solver/kernel/sample-size cost |
| k-means | Reduce squared within-cluster distortion | Chosen k, scaling and cluster geometry |

## Worked comparison

For ticket escalation, use a constant baseline, logistic model and constrained tree/ensemble with the same valid split and metric. A tree can capture an interaction absent from plain linear features; that motivates a test, not a guaranteed win. Preprocessing and model selection must stay within training/validation boundaries.

Explain the optimized objective: logistic negative likelihood differs from least squares; a local tree impurity gain is not proof of reduced business cost. Select classification threshold from validation and deployment error costs.

## Cost and interpretation

Training/prediction cost depends on sample count, dimensions, solver, tree count/depth or stored examples. “SVM is O(n²)” without a concrete algorithm/model is too broad. Feature importance is not automatically causal explanation. A complex model that improves only training score has not established a generalization advantage.

## Interview exercise — attempt first

**ID:** ML3-CLASSICAL_ML_ALGORITHMS. **Category:** ML/DL / Classical ML algorithms. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** An ensemble beats logistic only on training. What follows?

<details><summary>Expected reasoning, followup and common mistake</summary>

Only better fit to that training score. Check valid held-out results, calibration, slices, variation and tuning budget before adopting complexity.

**Followup:** Which feature interaction would a plain linear model need represented explicitly?

**Common mistake:** Declaring an ensemble universally best from one training score.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Linear models / scikit-learn](https://scikit-learn.org/stable/modules/linear_model.html).
- [Decision trees / scikit-learn](https://scikit-learn.org/stable/modules/tree.html).
- [Ensembles / scikit-learn](https://scikit-learn.org/stable/modules/ensemble.html).
- [Common pitfalls / scikit-learn](https://scikit-learn.org/stable/common_pitfalls.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
