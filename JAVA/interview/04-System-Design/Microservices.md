# Split by owned behavior, not by table count

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

Microservices place independently operated business capabilities behind explicit contracts. A service boundary includes code, data authority, deployment, observation and incident ownership. A separate repository or container without ownership does not establish a useful boundary. Begin with a modular monolith when one team and one transactional model meet the constraints.

Extract a service when independent change cadence, scale, reliability isolation or organizational ownership justifies network and operational cost. Identify which invariants remain within one transaction and which become cross-service workflows. Do not have several services casually mutate the same tables while claiming independent ownership.

## Worked trace

An order module owns accepted order state; stock owns available inventory. Calling stock then writing order can fail between steps. Model a reservation with expiry/confirmation and an order lifecycle; use stable request identity, explicit compensation and reconciliation. Compensation is a new business action, not time travel: an email already sent or item already shipped cannot be undone by a database rollback. If all-or-none order/stock is essential and feasible in one store, keeping that boundary together may be the better design.

## Alternatives and operating limits

A synchronous call chain amplifies latency and availability dependencies. Give every outbound call a timeout and bounded retry policy only where effect semantics permit. Circuit breaking and load shedding do not repair a violated invariant. Observe traces, per-service errors and user-journey outcomes; preserve API/schema compatibility during rollout. Compare the added deploy/runtime/security cost against the concrete autonomy gained. Extract incrementally with a strangler route and one authoritative owner; dual writes need an explicit consistency mechanism and rollback plan.

## Attempt first — P7-MICROSERVICES

Should every CRUD entity become its own service?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Entities participating in one invariant may belong together. Split by capability and independent ownership only when the benefit outweighs distributed transaction/latency/operations cost. Followup: which measurable symptom would motivate extraction?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [Outbox and duplicate limits](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html).
- [Existing boundary comparison](Monolith_vs_Microservices.md).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
