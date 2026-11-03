# Feature engineering and leakage

## Prediction-time availability

A feature must be legitimately available when the decision is made. A ticket field recorded after escalation leaks the outcome; random train/test separation cannot repair that invalid availability.

## Learned preprocessing

Imputation, scaling, category vocabularies, feature selection and PCA are fitted state. Fit training rows only, transform validation/test with frozen state, and refit within each training fold during cross-validation. A pipeline helps discipline but cannot detect every invalid raw feature.

## Worked scaler example

Training[0, 2] has population mean 1, std 1. A held-out 100 transforms to 99. Fitting[0, 2, 100] changes mean/scale using held-out information. Calling it unsupervised does not remove leakage from an inductive test. A genuine transductive protocol needs an explicitly different evaluation contract.

The local standardizer validates finite nonempty matrices, treats a constant feature's scale as 1 and rejects derived nonfinite statistics. Finite huge inputs can overflow a reduction, so finite-input checks alone are insufficient. Transformation magnitude and representable output remain caller concerns.

## Representation and deployment

Scale distance/regularization-sensitive features; trees have different sensitivity. Encode categories with a defined unknown policy. Target encoding needs out-of-fold/causal discipline to avoid using a row's own label. Time-window features must use available past data. Group splits should match whether prediction concerns new users or future events from known users.

Version transform/schema/model together. Monitor missing/unknown categories and training-serving skew. A silent zero fallback can change meaning; it is correct only if declared. Retention/deletion policies also affect derived features and artifacts.

## Interview exercise — attempt first

**ID:** ML3-FEATURE_ENGINEERING. **Category:** ML/DL / Feature engineering and leakage. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can label-free scaling invalidate evaluation?

<details><summary>Expected reasoning, followup and common mistake</summary>

It still learns from held-out input statistics. Freeze preprocessing from training under the stated inductive protocol, and separately verify raw feature timing/grouping.

**Followup:** How should target encoding be fit without leaking each training row's label?

**Common mistake:** Fitting a transformation on all rows because it does not use labels.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Common pitfalls / scikit-learn](https://scikit-learn.org/stable/common_pitfalls.html).
- [Deep Learning chapter 2 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/linear_algebra.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
