# Experiment tracking: comparable runs and honest selection

## What to learn

Experiment tracking links a run to parameters, code/environment, data and resulting metrics/artifacts. It helps audit a comparison but does not make the comparison statistically valid. A dashboard with more decimal places cannot fix leaked labels, changed denominators or selection on the test set.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Name the hypothesis and primary objective before variants. Record dataset/split/feature/label versions, code/config/environment, seed policy, resource budget, failure status and metric population. Distinguish fixed versus searched choices. Use an immutable held-out comparison when possible; repeated use of final test feedback turns it into selection data. Keep unsuccessful runs visible so the apparent best result does not hide the search process.

## Worked reasoning

Fixture: run A gets 0.82 accuracy on 1,000 examples; run B gets 0.86 on 800 after dropping timeout/error cases. Those values alone cannot show B improved. Compare matched eligible examples, report omitted/failed cases and subgroup outcomes under the same metric contract. A seed mismatch can add variability, but matching one seed is not proof of determinism or generalization.

## Tradeoffs and failure boundaries

Logging raw features/prompts can expose private data; track permitted metadata/digests rather than uploading everything by default. More tracked fields can improve reproducibility while increasing retention/governance burden. Tracking supports evidence retrieval, not automatic promotion authority.

## Attempt first — P7-MLOPS-EXPERIMENT-TRACKING

After testing 50 prompt/model configurations on the same 40 evaluation tasks, can the best tracked score be called an untouched test result?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

No. Those tasks informed selection. Preserve the search record, then evaluate the selected configuration or selection procedure on genuinely held-out appropriate data, with dependence/label quality limits. Tracking all runs makes the selection visible but does not restore independence. Avoid a universal significance claim from 40 correlated tasks.

**Followups:** What budget makes two training runs comparable? How should aborted runs appear in a comparison?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [MLflow Tracking](https://mlflow.org/docs/latest/ml/tracking/) — MLflow; rolling docs; Run metadata/artifact tracking; no experiment executed.
- [Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml) — Martin Zinkevich, Google; page date not established; Objectives, pipeline integrity and training/serving reasoning; no deployed model result.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
