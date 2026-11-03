# Probability and statistics

## Conditional probability

P(A|B)=P(A and B)/P(B) for positive P(B). Bayes gives P(A|B)=P(B|A)P(A)/P(B). Independence factors the joint; zero covariance does not generally imply independence. Expectation is linear without needing independence; variance measures squared deviation from the mean.

## Base-rate example

For an illustrative classifier population, prevalence 1%, sensitivity 90%, false-positive rate 5%. Among 10,000 examples, expect 90 true positives and 495 false positives. Precision is 90 / (90+495)≈15.38%, not 90%. These are illustrative classifier assumptions, not observed performance data. Sensitivity conditions on actual positives; precision conditions on predictions.

## Statistical evidence

A sample mean estimates a population quantity under sampling assumptions. Many correlated rows from one user are not equivalent to independent users. Frequentist 95% confidence describes the procedure's coverage under assumptions, not automatically a 95% posterior probability for one fixed parameter. A p-value is not the probability the null is true.

For an A/B test, specify randomization unit, outcome, horizon and stopping plan. Treating clicks as independent when users are randomized can understate uncertainty. Repeatedly stopping a fixed-horizon test after favorable peeks requires an appropriate sequential analysis rather than ordinary fixed-horizon interpretation.

## Shift hypotheses

Input distribution, class prevalence and the conditional label relationship can change in different ways. A drift statistic is a diagnostic signal, not proof that retraining fixes quality. Delayed labels limit immediate outcome measurement. Offline comparisons should respect grouping, share a valid held-out set and report variability/slices, rather than only a tiny mean gain.

## Interview exercise — attempt first

**ID:** ML3-PROBABILITY_AND_STATISTICS. **Category:** ML/DL / Probability and statistics. **Difficulty:** Beginner. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can high sensitivity coexist with low precision?

<details><summary>Expected reasoning, followup and common mistake</summary>

Use population counts and the base rate. The negative population can produce many false positives even at a modest false-positive rate. State threshold and evaluation population.

**Followup:** Recompute precision if prevalence changes while sensitivity and false-positive rate stay fixed.

**Common mistake:** Interchanging P(actual positive | predicted positive) with sensitivity.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 3 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/prob.html).
- [Metrics and scoring / scikit-learn](https://scikit-learn.org/stable/modules/model_evaluation.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
