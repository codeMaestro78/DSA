# Monolith versus microservices

## Define the distinction

A modular monolith groups responsibilities in one deployable application while preserving internal module boundaries. Microservices make selected responsibilities independently deployed and operated through network contracts. Neither name guarantees clean code, reliability or scale.

Start from team ownership, change patterns, failure isolation, workload and data invariants. A small team can often implement and operate a modular application more effectively than a fleet of independently deployed services. That is an [INFERRED] starting heuristic, not a rule forbidding services.

## Compare against the same requirements

| Concern | Modular monolith | Independently deployed services |
|---|---|---|
| Local multi-module invariant | Often one transaction is available | Cross-service coordination becomes explicit |
| Deployment | One coordinated release | Independent releases need contract compatibility |
| Scaling | Entire runtime scales together | A costly component can scale separately |
| Fault isolation | Bugs/resource pressure may share process | Some faults isolate; networks/dependencies add partial failures |
| Development | Simpler local execution | More runtimes, discovery, telemetry and integration work |
| Ownership | Internal module API discipline | External contracts and on-call boundaries are needed |

A worker can be deployed separately from an API without requiring every domain concept to become a microservice. Share a codebase or schema only with an explicit ownership plan; arbitrary shared-database writes undermine independent service boundaries.

## Worked evolution: notification platform

Begin with modules for accepted intent, templates and delivery, one authoritative relational database, and an independently runnable worker. The critical invariant—accept an intent and its outbox event together—fits a local transaction.

Later, suppose push delivery has a separate team, a distinct release cadence and a workload that overwhelms email workers. First test whether separate queues and worker pools solve the problem. If independent domain ownership is still needed, extract a delivery service with a versioned contract and explicit ownership of attempts. Keep the accepted-intent source authoritative and define how state updates are reconciled.

Do not split first and only then discover a transaction spanning every service. Identify ownership and invariants before migration.

## Migration and failure behavior

A safe migration needs routing/feature control, observable comparison, compatible schemas and a rollback condition. If both old and new implementations send notifications during a shadow test, users may get duplicates. Shadow computation should suppress external effects unless the experiment explicitly authorizes them.

Network boundaries introduce deadlines, retries, duplicate delivery, authentication and schema compatibility. See [fault tolerance](Fault_Tolerance.md) and [queues](Message_Queues.md). Synchronous chains can multiply latency and availability dependencies; asynchronous flows add state and eventual behavior.

## Practice question

**SD-BOUNDARY-01 — Intermediate; SDE/backend; design; 0–2 years.** A slow reporting query harms create requests. Must reporting become a microservice?

<details><summary>Expected reasoning and followups</summary>

First measure the bottleneck: query plan, lock contention, shared connection/CPU pressure. An index, bounded reporting pool, scheduled materialization or read replica for eligible stale reads may suffice. A service split helps only if it changes the binding resource/ownership boundary. Followup: what consistency does reporting require? Mistake: treating deployment separation as automatic resource isolation when both services still saturate the same database.

</details>

## Checklist

- [ ] Team and operational capability are part of the decision.
- [ ] Invariants and data ownership survive the proposed boundary.
- [ ] A simpler isolation mechanism has been considered.
- [ ] Migration, side-effect control and rollback are explicit.

## References

- [AWS Builders Library — timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)
- [PostgreSQL — transaction isolation](https://www.postgresql.org/docs/current/transaction-iso.html)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
