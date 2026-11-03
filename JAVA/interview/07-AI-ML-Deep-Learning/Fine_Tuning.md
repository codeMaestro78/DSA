# Fine-tuning

## Choose the intervention

Fine-tuning updates parameters using task/domain data. Prompting changes conditioning; retrieval supplies selected external information. These can be combined but solve different failures. Training is not a reliable substitute for a fresh, permission-aware document source.

## Experiment plan

Define task, slices, baseline and quality/latency budget first. Compare simpler prompting/retrieval or a conventional model where appropriate. If failures reflect missing current facts or access filters, training may not address them. Stable behavior/format adaptation with representative examples can justify a controlled fine-tuning trial.

## Worked taxonomy adaptation

A baseline mishandles a stable output taxonomy. Collect labeled examples and difficult negatives, check provenance/permission, and hold out template families if that reflects deployment. Train an adaptation and compare unchanged baseline on the same evaluation. Inspect regressions on unrelated tasks; do not report only favorable samples.

## Data and artifact boundaries

Separate train/validation/test by dependency such as customer, document family or time. Near-duplicates can inflate results. For token prediction, define target alignment, loss mask and padding denominator. Store base model, tokenizer/template, adaptation settings, data/split identities and selected checkpoint.

## Risks and costs

Overfitting, forgetting, label error and memorization can harm quality/privacy. Parameter-efficient updates reduce some trainable state, not all activations/backbone compute. Serving needs a compatible artifact/preprocessing contract and rollback/evaluation plan. Lower training loss does not prove a useful deployment; no provider price, training throughput or model availability claim is made here.

## Interview exercise — attempt first

**ID:** ML3-FINE_TUNING. **Category:** ML/DL / Fine-tuning. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** When does retrieval fit better than fine-tuning?

<details><summary>Expected reasoning, followup and common mistake</summary>

Fresh, attributable and permission-scoped documents need selection/update/deletion mechanisms. Fine-tuning can adapt behavior but does not automatically enforce those document boundaries. Diagnose the actual failure before choosing.

**Followup:** How would you detect degradation of unrelated behavior after adaptation?

**Common mistake:** Training to fix a missing permission-scoped knowledge source without diagnosing the failure.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [LoRA / Hu et al.](https://arxiv.org/abs/2106.09685).
- [QLoRA / Dettmers et al.](https://arxiv.org/abs/2305.14314).
- [Common pitfalls / scikit-learn](https://scikit-learn.org/stable/common_pitfalls.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
