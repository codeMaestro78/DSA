# AI, ML and deep-learning roadmap

## Goal and prerequisites

Retain Phase 2 coding, SQL and design practice. ML adds reasoning about data and uncertain outcomes; it does not replace software correctness. Start with a modest tabular task before a large network.

## Learning sequence

| Stage | Study | Evidence before advancing |
|---|---|---|
| 1 | [Math](Mathematics_for_ML.md), [probability](Probability_and_Statistics.md), [linear algebra](Linear_Algebra.md), [calculus](Calculus.md) | Derive a mean-loss gradient and check shapes |
| 2 | [Classical ML](Classical_ML_Algorithms.md), [features](Feature_Engineering.md), [evaluation](Model_Evaluation.md) | Valid split, baseline and cost-aware metric |
| 3 | [Nets](Neural_Networks.md), [loss](Loss_Functions.md), [backprop](Training_and_Backpropagation.md), [optimization](Optimization_Algorithms.md), [regularization](Regularization.md) | Explain one update and a numerical gradient check |
| 4 | [CNN](CNN.md), [RNN](RNN_LSTM_GRU.md), [attention](Attention_Mechanism.md), [Transformers](Transformers.md) | Trace shapes, masking and compute implications |
| 5 | [PyTorch](PyTorch.md), [TensorFlow](TensorFlow.md), [distributed training](Distributed_Training.md) | Distinguish intended behavior from executed validation |
| 6 | [Fine-tuning](Fine_Tuning.md), [LoRA](LoRA_and_PEFT.md), [quantization](Quantization.md), [RL](Reinforcement_Learning.md) | Controlled experiment and explicit limits |

This is an editorial sequence, not an employer syllabus. Role titles vary: research work may emphasize reproduction/novelty, while applied ML may emphasize data, serving and product outcomes. Check the requisition.

## Running task

Imagine predicting ticket escalation. Define prediction time, label horizon, unit, user grouping and error costs. A field filled after escalation is unavailable at prediction time. Compare constant/logistic baselines on future or appropriately grouped held-out tickets. Add complexity only when evaluation justifies it.

## Readiness

Explain objective, split, baseline, metric, uncertainty, error slices and deployment failure behavior. Decreasing training loss is evidence of optimization, not generalization. Candidate proficiency remains unassessed; reading these chapters is not independent performance.

## Interview exercise — attempt first

**ID:** ML3-AI_ML_DL_INTERVIEW_ROADMAP. **Category:** ML/DL / AI, ML and deep-learning roadmap. **Difficulty:** Beginner. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Specify the first experiment before selecting a neural network.

<details><summary>Expected reasoning, followup and common mistake</summary>

Define label/prediction time, a valid split and frozen test, train-only preprocessing, a simple baseline, validation-selected threshold and relevant error slices. Record actual versus hypothetical evidence.

**Followup:** How would the split change if deployment predicts future tickets from existing users?

**Common mistake:** Selecting a network before defining label availability or a baseline.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Common pitfalls / scikit-learn](https://scikit-learn.org/stable/common_pitfalls.html).
- [Metrics and scoring / scikit-learn](https://scikit-learn.org/stable/modules/model_evaluation.html).
- [Deep Learning chapter 5 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/ml.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
