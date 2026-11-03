# Tenant-aware shared cache: hypothetical design case

## Attempt-first scope

Design a shared cache for expensive report reads while preserving current permission and a declared data freshness window. It is a derived acceleration layer, not the report authority.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 1,000 reads/s with 90% valid cache hits:100 reads/s reach the report store in the idealized steady fixture. This excludes misses caused by eviction, invalidation, stampedes and authority checks; it is not a measured saving.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`GET /reports/{id}` authorizes current access before serving cached bytes. Cache keys include tenant, report representation parameters and relevant source/version context. A cache-miss fill has a bounded deadline and result-size cap.

The report database owns content/version and access facts. Cache entries hold derived bytes and generation/expiry. Multiple writers need a version fence so a slow stale fill cannot overwrite a newer generation after invalidation.

## Critical flow

Check current resource rights, find the matching generation, serve an eligible hit; otherwise collapse concurrent fills under a scoped single-flight/lease strategy. Compute from the authoritative source, publish only if its version is still eligible, and return a declared fallback/error if freshness cannot be met.

## Failure trace

Delete invalidates a key, but a fill started earlier completes later and writes old text. A generation tombstone/conditional version check prevents resurrection. A TTL alone cannot prove immediate revocation; access decisions and already-issued download credentials need their own contract.

## Tradeoffs, security and operation

Write-through, cache-aside and invalidation events have distinct dual-write/race costs. Replication improves cache availability but may replicate stale data. Consistent hashing reduces remapping under membership changes; it does not create persistence, eliminate hot keys or make cache loss harmless to an overloaded origin.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** What if every key expires at once?

**Expected reasoning:** A stampede can overload the origin. Use bounded fills/admission, staggered expiry where policy permits and stale serving only within an explicit allowed contract. Followup: how do sensitive permission changes differ from ordinary data staleness?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html).
- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
