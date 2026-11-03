# Model serving: a versioned decision contract

## What to learn

Model serving turns a versioned input contract into predictions within an operational budget. Treat the model, preprocessing, feature definitions, output semantics and action threshold as a compatible bundle. A loaded weight file and a healthy process do not establish correct decisions.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Validate shape/type/unit/freshness before inference, apply fitted transforms exactly as evaluated and identify the resolved model version. Define batch versus online paths from latency and throughput needs. Admission, queue limits, batching and timeout budgets must account for tail behavior and downstream capacity. A batch can improve hardware utilization while adding waiting latency; dynamic batching requires a measured deadline policy rather than assuming every request becomes faster.

## Worked reasoning

Fixture: training uses `account_age_days` but serving sends hours under the same field name. A 10-day account becomes 240; shape validation passes, predictions change. Golden raw examples through both paths reveal the factor 24 mismatch. Bundle compatible transforms/units with weights and compare before model-quality debugging. A fallback to the previous model is safe only if current inputs/features remain compatible.

## Tradeoffs and failure boundaries

A canary needs representative traffic, success/latency/quality indicators and a rollback owner. Shadow inference checks computation without changing decisions and therefore does not establish causal user benefit. Cancellation of a request may not stop expensive remote work; separate acceptance, result and billing/capacity evidence. Restrict inference and artifact access by actual resource policy.

## Attempt first — P7-MLOPS-MODEL-SERVING

A serving rollback restores weights but leaves new preprocessing. What must be corrected?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Roll back or forward-repair the entire compatible bundle, verify the runtime/input contract and run golden cases plus representative operational checks. Restoring a version label without actual loaded-artifact evidence is insufficient. Any decisions made during the bad interval may require separate review/reconciliation.

**Followups:** Which requests should a batch share? Why can nominal GPU utilization conceal missed deadlines?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [TensorFlow Serving guide](https://www.tensorflow.org/tfx/guide/serving) — TensorFlow/TFX; rolling guide; Serving/version integration concepts; TensorFlow not executed.
- [MLflow Model Registry](https://mlflow.org/docs/latest/ml/model-registry/) — MLflow; rolling docs; Version/alias/lineage concepts; no registry created.
- [Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml) — Martin Zinkevich, Google; page date not established; Objectives, pipeline integrity and training/serving reasoning; no deployed model result.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
