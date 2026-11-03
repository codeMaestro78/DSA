# Location-based dispatch: hypothetical design case

## Attempt-first scope

Design driver availability, candidate matching and one accepted assignment per driver. Fare calculation, safety decisions and real navigation are excluded. This hypothetical dispatch exercise does not establish any actual company architecture.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 10,000 online drivers report every 5 seconds:2,000 location updates/s before replication. A cell index reduces candidate search area but does not make stale positions or cross-cell matching exact.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`PUT /drivers/me/location` carries authenticated driver identity and monotonic version; `POST /ride-requests` creates a stable request; `POST /offers/{id}/accept` conditionally attempts allocation.

Location cache owns a freshness-tagged observation, not the assignment invariant. Dispatch store owns driver/ride assignment states and offer versions. A driver may have at most one active assignment, and a ride request may have at most one accepted driver. Enforce both claims together at the authoritative allocation boundary; independently checking two cached availability flags is insufficient. For an initial relational design, active driver and ride identities can each be unique, with allocation and request/offer state committed in one transaction. Other mechanisms are valid only if they preserve both claims under concurrent acceptance.

## Critical flow

Index recent positions by spatial cell, fetch nearby candidates and filter availability/freshness. Rank by a stated heuristic, issue bounded expiring offers and let a conditional authority select one active assignment. Recheck the ride and driver version at acceptance, then durably publish the result.

## Failure trace

Two offers race for one driver. Both can read available, but only one versioned allocation may commit. Conversely, two drivers accepting offers for the same ride must not both win; a unique driver claim alone does not prevent that second race. Expiring an offer in an application clock does not by itself revoke an already committed assignment. Reconciliation must distinguish pending offer, accepted trip and stale location.

## Tradeoffs, security and operation

Coarse cells reduce index size but widen distance/error; fine cells increase boundary fan-out/update movement. Location retention is sensitive and needs restricted access/deletion policy. Prefer one assignment owner initially; cross-region independent assignment is a new invariant problem, not an automatic cache feature.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Can the location cache decide assignment because it is fast?

**Expected reasoning:** Not safely by itself. A stale availability flag is a candidate hint; final allocation needs an authoritative conditional invariant. Followup: how does the system recover when the acceptance response is lost?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [Database constraints](https://www.postgresql.org/docs/18/ddl-constraints.html).
- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
