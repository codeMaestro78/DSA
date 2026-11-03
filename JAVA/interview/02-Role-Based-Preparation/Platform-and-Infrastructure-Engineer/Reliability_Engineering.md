# Reliability engineering: define the promise and test recovery

## What to learn

Reliability engineering turns a desired user experience into explicit measurable contracts and repeatable recovery. Availability, latency, durability and correctness are distinct. A service can answer quickly with stale or unauthorized data, so a process-up measure alone is insufficient.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Define the critical journey, eligible event population, success/latency/freshness indicator, window and objective. Name failure domains and recovery ownership. RPO describes tolerated data loss under the recovery model; RTO describes recovery duration. Redundancy reduces selected failure exposure, while backup restore/reconciliation demonstrates another path. Neither a replica nor a backup checkbox guarantees both bounds.

## Worked reasoning

Fixture: a database backup restores yesterday's state within 20 minutes, but the service promised at most 5 minutes of lost writes. The restore can satisfy a recovery-time target yet violate the data-loss target. Add an appropriate verified log/replication/recovery mechanism or change the promise explicitly. Test restoration of a known write and application consistency, not merely creation of an empty database process.

## Tradeoffs and failure boundaries

Error budgets help prioritize risk, but a critical security/data-integrity issue should not be averaged away by success percentages. Design bounded retries, overload shedding, rollback and degraded modes. A game day must have scoped authority and stop conditions; this chapter describes such checks but executes none.

## Attempt first — P7-INFRA-RELIABILITY-ENGINEERING

An availability report excludes all timed-out requests. Can it establish a 99.9% success objective?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Only if the declared objective intentionally excludes them, which would be unsuitable for many user-success promises and must be explicit. Normally include eligible timeouts/failures in the denominator/outcome policy, report unknowns and validate the measurement fixture. An attractive number cannot repair a misleading population definition.

**Followups:** What makes a retry storm a correlated failure? Which restore evidence would change an RPO claim?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Google SRE Workbook;2018; User indicators/error-budget alert method; no measured SLO.
- [Managing Incidents](https://sre.google/sre-book/managing-incidents/) — Google SRE book;2016; Response ownership and communication; no real incident.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
