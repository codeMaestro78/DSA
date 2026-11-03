# Optimize the cost of a correct user outcome

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

Cost optimization compares the resources required to deliver an accepted outcome under its reliability, privacy and latency constraints. A lower unit price can increase total spend through retries, idle capacity, data movement or operational complexity. Separate fixed, per-request, retained-storage and transfer costs; include engineering/on-call cost where it changes the choice.

Start with attribution: workload/tenant, resource hours, bytes retained/transferred and successful user outcomes. Measure waste before changing architecture. Remove unused resources, bound retention and retries, batch appropriate work and match capacity to measured demand. Discounts and commitments are commercial/version-specific decisions, not free savings when demand is uncertain.

## Worked trace

Fictional service A costs 0.01 units per attempt with 10% independent failures; retry-until-success would average 1/0.9 attempts and about 0.0111 units per success under those assumptions. Service B costs 0.012 with no stipulated failures, but the fixture says nothing about quality or correlated outages. A bounded retry policy changes success probability and cost; do not claim the cheap option wins from price alone. For storage, 1,000 uploads/day×2 MB×30 days=60,000 MB decimal before replication, versions and egress.

## Alternatives and operating limits

Trade off caching against memory/invalidation, compression against CPU/latency, and asynchronous batching against freshness. Enforce budgets at the admitted-work scope, not only a dashboard after spend occurs. Keep cost estimates separate from actual provider billing and currency/tax assumptions. Before rightsizing, verify headroom for peaks/failover and a rollback trigger. Never remove backups or isolation controls solely because a line item looks large without reconsidering the required recovery/exposure contract.

## Attempt first — P7-COST-OPTIMIZATION

A cache saves80% of database reads. Does that prove total cost fell80%?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Database cost may be fixed, the cache has its own memory/operations costs, and misses/invalidations can change work. Compare the same correct workload and quality targets with attributed measured cost. Followup: what changes if the database can actually be downsized?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [Cost framework](https://docs.aws.amazon.com/wellarchitected/latest/cost-optimization-pillar/welcome.html).
- [Capacity estimation](Capacity_Estimation.md).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
