# ML pipelines: valid dependencies and release boundaries

## What to learn

An ML pipeline is a dependency graph that turns permitted data into a versioned evaluated artifact. Its correctness includes statistical boundaries: a perfectly repeatable pipeline can reproducibly leak test information or train on features unavailable at prediction time.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Separate data extraction/validation, split assignment, fit-only-on-training transforms, training, validation selection, final evaluation and packaging. Bind outputs to input/code/config identities. Task caching is safe only when the key covers every input that changes meaning; a path named `latest` is not an immutable dataset. Keep intermediate artifacts permitted and recoverable, and fail closed on material schema/label problems rather than silently dropping rows.

## Worked reasoning

Fixture: normalization is fitted on all dates before the temporal split. The pipeline is deterministic and cached, yet future data influenced training preprocessing. Fit on the permitted training partition and apply the same fitted transform elsewhere. Another failure changes a feature definition but reuses a cache key containing only model hyperparameters; the cache may supply an incompatible old artifact.

## Tradeoffs and failure boundaries

Pipeline retry must not duplicate promotion or overwrite accepted artifacts. Use immutable versioned outputs and explicit publication/promotion state; a task scheduler cannot make an external deployment action atomic automatically. Capture failed/aborted runs and metric denominators. Compare pipeline versions under the same evaluation contract before attributing gains.

## Attempt first — P7-MLOPS-ML-PIPELINES

A failed evaluation task is replaced with its previous successful result so the release can continue. When is that valid?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Only when provenance proves the cached result corresponds to exactly the same relevant model, data, preprocessing, metric and configuration and the reuse policy permits it. Otherwise the result is missing, and promotion must wait or explicitly change its evidence claim. Do not equate availability of an old green run with validation of the new bundle.

**Followups:** What changes invalidate a feature cache? How would a retry after promotion response loss be reconciled?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Airflow best practices](https://airflow.apache.org/docs/apache-airflow/stable/best-practices.html) — Apache Airflow; rolling stable docs; Retry/partition task discipline; no DAG executed.
- [MLflow Tracking](https://mlflow.org/docs/latest/ml/tracking/) — MLflow; rolling docs; Run metadata/artifact tracking; no experiment executed.
- [Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml) — Martin Zinkevich, Google; page date not established; Objectives, pipeline integrity and training/serving reasoning; no deployed model result.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
