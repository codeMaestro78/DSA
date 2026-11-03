# Loss functions

## Objective contract

A loss maps predictions/targets to the optimized scalar. A reporting metric can be nondifferentiable or encode another cost. Declare reduction, target encoding, shape and valid values.

Squared error emphasizes large residuals; absolute error has different robustness and population-optimum interpretation. Huber combines quadratic/linear regions. None automatically matches every business cost. Binary-logit cross entropy is softplus(z)-y*z; mutually exclusive multiclass loss is logsumexp(logits)-logit[target]. Multi-label classification needs a different target interpretation from one-class softmax.

## Worked probability penalty

Natural-log loss for a correct-class probability of 0.9 is approximately 0.10536; probability 0.1 gives approximately 2.30259. Accuracy alone cannot distinguish all probability-quality differences. Confident wrong predictions are penalized strongly.

Use stable logits-based losses rather than exponentiating directly. Do not apply sigmoid/softmax first if the selected loss expects raw logits; that changes the computation. For padded sequences, exclude invalid tokens and define the denominator. Averaging per-sequence means weights variable-length examples differently from averaging all valid tokens.

## Weights and selection

Class weights change the optimized risk and can change probability interpretation. They do not remove threshold/calibration evaluation under deployment conditions. Label smoothing deliberately changes targets; compare reporting losses under a consistent definition.

## Failure boundary

Lower training loss does not prove better held-out business utility. Proxy optimization can reward undesirable behavior, especially in RL/preference tasks. Inspect objective/task alignment, correctness and evaluation protocol before interpreting a favorable number. The local logistic routine tests one loss contract; no framework-wide loss behavior was executed here.

## Interview exercise — attempt first

**ID:** ML3-LOSS_FUNCTIONS. **Category:** ML/DL / Loss functions. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can probabilities passed to a logits loss be wrong?

<details><summary>Expected reasoning, followup and common mistake</summary>

The loss treats them as logits and normalizes again. Check documented input/target/reduction contracts with a one-example hand calculation.

**Followup:** How does token averaging differ from averaging per-sequence means?

**Common mistake:** Applying sigmoid/softmax twice or comparing incompatible reductions.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Deep Learning chapter 6 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/mlp.html).
- [Metrics and scoring / scikit-learn](https://scikit-learn.org/stable/modules/model_evaluation.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
