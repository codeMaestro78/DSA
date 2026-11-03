# ETL and ELT: decide where transformations belong

## What to learn

Extract-transform-load processes data before it enters the destination analytical store; extract-load-transform performs transformation there. These describe ordering and responsibility, not a universal quality hierarchy. Choose based on trust boundaries, source shape, processing capability, cost and reprocessing needs.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Before either path, validate authority, input identity/schema, transfer protection and retention. Keep a restricted raw layer only when policy permits retaining it. ETL can minimize sensitive data crossing a boundary and prepare unsupported formats; ELT can reuse destination compute and preserve raw replay inputs. In both, version transformations, test grain/quality and publish compatible outputs. Late corrections need explicit incremental or backfill semantics.

## Worked reasoning

Fictional case: source contains customer email and support category; the analytics purpose needs only daily category counts. Loading unrestricted raw text/email into a broadly accessible warehouse expands exposure without serving that purpose. A restricted transformation can aggregate/redact before publication. Another task needs reproducible historical transformations; a permitted encrypted raw snapshot with tightly controlled access may justify retention. Neither scenario proves all ETL or all ELT is preferable.

## Tradeoffs and failure boundaries

An ELT job reading yesterday's modified rows can miss a correction dated earlier than its lookback. An ETL job can destroy needed lineage if it overwrites its only source copy. Bound lookback/reconciliation by actual source correction guarantees, and monitor omissions. Schema drift should fail or quarantine intentionally rather than coerce silently.

## Attempt first — P7-DATA-ETL-AND-ELT

A team chooses ELT because storage is cheap, but policy allows only anonymized aggregates in analytics. What determines the design?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

The permitted data/processing boundary governs. Transform within an approved restricted environment before publishing only allowed aggregates, or prove the destination has an approved restricted raw zone. Cost convenience cannot waive data-use authority; document retention and reprocessing consequences. Anonymization itself needs an actual risk/policy assessment, not just dropping one column.

**Followups:** When can hashing identifiers still leave sensitive data? What makes an incremental model miss updates?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [dbt incremental models](https://docs.getdbt.com/docs/build/incremental-models-overview) — dbt Labs; rolling docs; Incremental-model mechanism; actual merge support depends on adapter/configuration.
- [Airflow best practices](https://airflow.apache.org/docs/apache-airflow/stable/best-practices.html) — Apache Airflow; rolling stable docs; Retry/partition task discipline; no DAG executed.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
