# Optimization algorithms

## Objective and update

The objective defines what to minimize. SGD applies theta←theta-eta*g using a batch gradient. Momentum maintains a direction history. Adam tracks first and second gradient moments, corrects initialization bias and scales by m_hat/(sqrt(v_hat)+eps). Its second moment is not a full Hessian.

An L2 loss penalty and AdamW-style decoupled decay need not produce identical adaptive updates. State reduction, regularization convention, step frequency and learning-rate schedule before comparing training.

## Exact small example

For f(w)=w²/2, gradient=w and w_next=(1-eta)w. For this quadratic, 0<eta<2 contracts magnitude, eta = 2 oscillates and larger eta diverges. This condition is specific to the objective, not a universal network bound.

## Batch and accumulation

Larger batches alter noise, memory, update count and parallelism; they do not guarantee faster or better training. Summing K equal-sized microbatch mean gradients needs division by K to represent the combined mean. Unequal sizes need example-count weights. Stateful layers, randomness and schedules can prevent equivalence even with correct arithmetic.

## Debugging sequence

Validate labels/shapes/objective, overfit a tiny batch, inspect gradients and actual parameter changes, then study rate/schedule/precision. Clipping can bound a chosen gradient norm but does not prove no NaNs or exploding activations. Compare optimizers under a fair budget and valid validation protocol; lower training loss alone is not better generalization.

## Interview exercise — attempt first

**ID:** ML3-OPTIMIZATION_ALGORITHMS. **Category:** ML/DL / Optimization algorithms. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can four accumulated mean losses scale the gradient incorrectly?

<details><summary>Expected reasoning, followup and common mistake</summary>

Their summed gradients are four times the equal-sized combined-batch mean unless normalized. Unequal microbatches require weighting by count; check optimizer-step frequency and stateful layers too.

**Followup:** How would unequal microbatch sizes change the averaging rule?

**Common mistake:** Summing local means as though each represented the same number of examples.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 8 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/optimization.html).
- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
