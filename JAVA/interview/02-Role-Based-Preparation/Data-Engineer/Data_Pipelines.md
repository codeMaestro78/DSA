# Data pipelines: identity, retries and atomic publication

## What to learn

A pipeline moves and transforms data through owned stages. Each stage has an input contract, output contract and failure policy. Reliable processing requires a stable definition of the work being retried; “run the latest data again” can produce a different answer and corrupt a backfill.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Bind a run to source partition/snapshot, schema/code version and intended destination grain. Write to a staging generation, validate quality/row counts and publish with a supported atomic metadata/transaction boundary. Retrying must replace/merge the same logical output under a defined key/version policy, not append duplicate facts. Persist progress only in coordination with accepted output or use replay-safe sink writes. Scheduler retries do not make arbitrary external writes transactional.

## Worked reasoning

Fixture: source partition 2026-11-02 has event IDs E1,E2,E2,E3, where the repeated E2 is identical. An ingestion run accepts three unique identities, writes generation G1 and publishes it. The task crashes after publication but before reporting success. A replay resolving the same run/input must keep three facts, not six. If E2 later has a new revision, decide whether it replaces current state or is a new historical fact; do not deduplicate distinct revisions accidentally.

## Tradeoffs and failure boundaries

Exactly-once claims need source, replay, checkpoint and sink scope. A transactional sink result is separate from notifications or user actions emitted by a task. Track freshness, failed/quarantined records, publication identity and lineage. A pipeline can finish while silently dropping a tenant; totals and representative slices matter.

## Attempt first — P7-DATA-DATA-PIPELINES

A daily pipeline appends output then records its watermark. It crashes between those steps. Explain a safe redesign.

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Use an atomic output/progress commit where supported, or make replay target the same partition/key/generation with idempotent semantics and reconcile an already-published result. Advance the accepted watermark only when the output contract is satisfied. Preserve unknown state for reconciliation rather than skipping unconfirmed input. Test crashes before and after each boundary.

**Followups:** How is a backfill isolated from live processing? What if source corrections arrive after a watermark?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Airflow best practices](https://airflow.apache.org/docs/apache-airflow/stable/best-practices.html) — Apache Airflow; rolling stable docs; Retry/partition task discipline; no DAG executed.
- [Spark Structured Streaming overview](https://spark.apache.org/docs/latest/streaming/index.html) — Apache Spark; rolling latest docs; Incremental stream model; guarantees depend on mode/source/sink and custom effects.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
