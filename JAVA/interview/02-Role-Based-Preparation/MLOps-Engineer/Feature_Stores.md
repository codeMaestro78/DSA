# Feature stores: historical meaning and serving freshness

## What to learn

A feature store manages feature definitions and retrieval paths for training and inference. It can reduce repeated work, but correctness depends on entity identity, event/availability time, computation versions and freshness. Matching a column name in offline/online stores is not proof of matching meaning.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

For historical retrieval, select the feature value appropriate to the prediction timestamp with a defined age/TTL rule. Track late ingestion or corrections: an event before T but unavailable to the serving system until after T may leak future knowledge into a decision reconstructed at T. Online materialization needs versioned definitions, missing/default behavior, source lag and entity access controls. Feature TTL is not automatically authorization revocation or historical availability tracking.

## Worked reasoning

Fixture: a customer purchase occurred 09:50, arrived 10:10 and a decision was made 10:00. A training join by business event time alone includes the purchase, while online serving at 10:00 could not. Model both event time and available/ingested time where the deployment question requires it, or reconstruct an accepted immutable feature snapshot. A generic point-in-time engine needs the right source timestamps and contract to answer this question. The inspected Feast documentation distinguishes default event-time joining from an optional created-timestamp filter: `filter_by_created_timestamp=True` requires a suitable created/availability timestamp and supporting offline store. Verify the selected version/store; the flag cannot make a timestamp represent online availability when the source does not. No Feast query was executed.

## Tradeoffs and failure boundaries

Online caches can serve stale/default features while the model still returns 200. Monitor freshness and defaults by source/entity, and choose explicit degraded behavior. Centralized feature reuse saves work but can create hidden dependencies when a definition changes. Keep sensitive feature values scoped and version migrations coordinated with model/preprocessing bundles.

## Attempt first — P7-MLOPS-FEATURE-STORES

A feature name `is_active` changes from 30-day to 7-day activity but keeps the same boolean type. Is schema validation enough?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

No. The semantic definition changed while shape stayed compatible. Version the feature contract, regenerate training/evaluation or provide a compatible adapter, coordinate materialization/model rollout and retain a valid rollback target. Test a customer active 20 days ago to expose the changed meaning.

**Followups:** How does a stale default affect calibration? Which clock defines a freshness promise?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Feast point-in-time joins](https://docs.feast.dev/getting-started/concepts/point-in-time-joins) — Feast; rolling docs; Historical feature joining; exercise separately requires availability-time correctness.
- [Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml) — Martin Zinkevich, Google; page date not established; Objectives, pipeline integrity and training/serving reasoning; no deployed model result.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
