# Regularization

## Mechanisms

Regularization favors some solutions to improve behavior beyond the training sample under task assumptions. It may increase training loss while improving validation. It cannot repair invalid evaluation data.

L2 adds a squared-parameter penalty; L1 often favors sparse coefficients; early stopping selects a validation checkpoint; dropout changes training computation; augmentation supplies variation when transformations preserve labels. These methods have distinct mechanisms and limits.

## Worked penalty

For L_total=L_data+(lambda/2)||w||², the extra gradient is lambda*w. Specify whether bias is penalized. Sum-versus-mean data loss changes the relative penalty unless lambda is adjusted. L2 and decoupled weight decay are not universally identical with adaptive updates.

## Controlled comparison

Keep split and baseline fixed, select a modest regularization range using validation, inspect train/validation curves and slices, then freeze settings before final test. Repeated test-driven selection removes its untouched status. Compare methods with a fair tuning budget rather than the best cherry-picked seed.

## Counterexamples

Too much regularization underfits. Invalid augmentation changes labels. Dropout unintentionally active during ordinary inference changes output; intentionally stochastic inference needs its own declared protocol. Early stopping can overfit a noisy validation sample through repeated selection. A larger network may generalize well, so capacity alone is not a diagnosis.

Store selected preprocessing/configuration/checkpoint together. Report how selection occurred and distinguish the penalized training objective from the unpenalized reporting metric when comparing curves.

## Interview exercise — attempt first

**ID:** ML3-REGULARIZATION. **Category:** ML/DL / Regularization. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Is worse training but better validation loss after L2 contradictory?

<details><summary>Expected reasoning, followup and common mistake</summary>

No. The procedure restricts fitting and may change the objective. Compare the same reporting data loss and valid held-out behavior;state whether the reported training number includes the penalty.

**Followup:** Why may L2 and decoupled decay differ under an adaptive optimizer?

**Common mistake:** Using test performance to repeatedly select regularization strength.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 7 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/regularization.html).
- [Deep Learning chapter 8 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/optimization.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
