# Model evaluation and calibration

## Validity before a score

Define prediction unit/time, target horizon and population. Split by time/entity/document family where dependencies matter. Train fits parameters; validation selects models, thresholds and calibration; a final untouched test estimates the selected procedure. Reusing test results for choices turns it into validation.

## Metrics and undefined cases

Precision=TP/(TP+FP), recall=TP/(TP+FN), F1=2TP/(2TP+FP+FN). Undefined denominators require a documented policy. The local routine returns None for undefined ratios; another library can choose a configured numeric convention. With TP = FP = FN = TN = 1, all these plus accuracy are 0.5.

High accuracy in an imbalanced population may match an always-majority baseline. ROC-AUC/PR summaries describe ranking under definitions, not the deployment threshold's utility. Report counts, threshold, error costs and relevant slices.

## Calibration and regression

Calibration concerns whether comparable predicted probabilities match observed frequencies. Ranking and probability reliability differ: monotone score changes can preserve ranking while altering probability meaning. Calibration plots, Brier/log loss assess different properties; fit calibration separately from its final evaluation.

MAE and MSE/RMSE penalize residuals differently; report units and target transformations. Uncertainty estimates must respect the sampling/grouping unit; independently resampling correlated rows can understate uncertainty.

## Production distinction

Offline quality does not establish product impact. Online experiments need valid randomization/stopping and guardrails. Input drift or healthy latency is not immediate outcome quality, especially with delayed labels. Monitor operational state and delayed labeled quality separately.

## Interview exercise — attempt first

**ID:** ML3-MODEL_EVALUATION. **Category:** ML/DL / Model evaluation and calibration. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** A classifier scores 99% accuracy with 1% positives. What next?

<details><summary>Expected reasoning, followup and common mistake</summary>

Compare always-negative baseline, confusion counts, precision/recall/error costs at chosen threshold, split validity and slices. No predicted positives makes precision undefined under the local contract.

**Followup:** How can ranking stay unchanged while calibration becomes worse?

**Common mistake:** Treating accuracy or an undefined denominator as sufficient quality evidence.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Metrics and scoring / scikit-learn](https://scikit-learn.org/stable/modules/model_evaluation.html).
- [Probability calibration / scikit-learn](https://scikit-learn.org/stable/modules/calibration.html).
- [Common pitfalls / scikit-learn](https://scikit-learn.org/stable/common_pitfalls.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
