# Distributed systems for infrastructure roles

## What to learn

A distributed system coordinates work across independently failing processes and networks. The practical skill is tracing partial failure and determining who can make each authoritative state transition. Replication and message delivery are mechanisms with scoped guarantees, not interchangeable synonyms for durability or exactly-once outcomes.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Name the invariant and its owner, request/effect identity, acknowledgment boundary, replication policy and recovery behavior. A network timeout leaves remote state uncertain. Choose a supported consensus/transaction/conditional-write mechanism for an invariant rather than hoping all replicas observe the same order. Leases/fencing need an explicit old-owner rejection rule; a timeout alone does not ensure an old process stopped acting.

## Worked reasoning

Fixture: scheduler A holds a lease, pauses, and B acquires a later lease. A resumes and writes an output. If the output sink ignores ownership epochs, both can act despite the lease protocol. Bind accepted writes to a monotonic fencing epoch or another authoritative mechanism at the sink. Cancellation or process scheduling cannot supply the missing rejection contract.

## Tradeoffs and failure boundaries

Partitions force explicit choices for conflicting availability/consistency requirements; do not use CAP as a generic product ranking. Retries need bounded backoff and idempotency/reconciliation. Event delivery, accepted database result and external notification effect have different scopes. Preserve tenant authority and replay compatibility across restarts.

## Attempt first — P7-INFRA-DISTRIBUTED-SYSTEMS

A worker receives the same job twice after a crash. What makes its result safe to accept once?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Use stable logical job identity and an authoritative conditional/transactional acceptance boundary tied to the intended input/version. Reject incompatible duplicate meaning, and fence stale attempts as needed. This can deduplicate result acceptance while duplicate remote computation/cost still occurs; an external action needs its own verified idempotency/reconciliation contract.

**Followups:** How does asynchronous replication affect acknowledged-write recovery? What evidence distinguishes delay from failure?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html) — IETF RFC9110;June2022; HTTP method/retry contracts; no automatic external-effect guarantee.
- [Spark Structured Streaming overview](https://spark.apache.org/docs/latest/streaming/index.html) — Apache Spark; rolling latest docs; Incremental stream model; guarantees depend on mode/source/sink and custom effects.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
