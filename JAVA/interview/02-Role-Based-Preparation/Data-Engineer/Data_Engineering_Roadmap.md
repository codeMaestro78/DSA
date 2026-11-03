# Data engineering preparation roadmap

## What to learn

Data engineering turns source facts into dependable datasets used by people and applications. The early-career core is SQL grain, data contracts, repeatable processing and honest quality/freshness evidence. Distributed engines are an extension after a correct small pipeline is understood; a tool inventory is not a roadmap.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Progress through four gates: one source-to-table trace with keys/time semantics; joins/aggregations with NULL and fan-out tests; partitioned rerunnable ingestion/backfill; then distributed skew, late data and operations. Keep source owners, destination consumers, freshness targets and deletion policy explicit. For each gate, save an input fixture, expected output, failed-case trace and a limit. Choose one batch orchestrator and one processing model conceptually before comparing many products.

## Worked reasoning

Fictional practice sequence: ingest three order events, including a duplicate and a correction; derive one current order row and a daily total; replay the batch without changing the result; introduce a late event affecting yesterday. The gate is explaining identity/version and publication, not merely producing a CSV once. A second attempt changes the business grain from order to order-line; joining payment rows now requires deliberate aggregation.

## Tradeoffs and failure boundaries

Large-scale practice without grain discipline can efficiently duplicate money. A platform choice may add operational cost and new failure boundaries before data volume warrants it. Keep private source samples out of interview/public notes. Pipeline success means accepted output under a declared contract, not simply a green task.

## Attempt first — P7-DATA-DATA-ENGINEERING-ROADMAP

You have four study sessions before a junior data interview. What concrete artifacts cover more than memorizing Spark APIs?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Produce a grain/key diagram, a join/aggregation fixture with exact output, a rerunnable partition publication/replay trace, and a late-event/backfill plan with quality/freshness checks. Explain complexity and operational limits. Use the SQL, pipeline and warehouse chapters next, then the role mock. These are learning artifacts, not evidence a production pipeline was operated.

**Followups:** What gate would change for a streaming role? Which evidence distinguishes retry safety from one successful run?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Airflow best practices](https://airflow.apache.org/docs/apache-airflow/stable/best-practices.html) — Apache Airflow; rolling stable docs; Retry/partition task discipline; no DAG executed.
- [Beam programming guide](https://beam.apache.org/documentation/programming-guide/) — Apache Beam; rolling docs; Event time, windowing and pipeline model; no runner tested.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
