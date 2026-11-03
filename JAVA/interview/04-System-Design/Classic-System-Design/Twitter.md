# Short-post timelines: hypothetical design case

## Attempt-first scope

Design posting, follow timelines, deletion and pagination for a short-message service. Trends and recommendation-model training are excluded. This is original product-class architecture, not a claim about a company's current stack.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 2,000 posts/s in a busy interval and 200 average recipients per author:400,000 feed-reference writes/s for naive fan-out, excluding indexes/retries. That amplification motivates comparing pull and hybrid feeds; it is not a measured workload.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /posts` accepts a scoped request identity; `GET /home?cursor=...` uses an opaque authorized cursor. `DELETE /posts/{id}` records the current deletion/visibility state.

Post store owns immutable identity and versioned status. Follow edges define candidate sources. Materialized timeline rows hold references with deterministic ordering keys; a cursor encodes the ordering boundary and relevant view policy, not an offset assumed stable during inserts.

## Critical flow

Commit post plus delivery intent. Materialize ordinary authors, query large-author recent posts separately, merge by chosen ordering and stable tie-breaker. Recheck visibility/deletion while resolving posts. Return a declared bounded/frozen pagination policy instead of guaranteeing an unchanging live list by accident.

## Failure trace

A new post arrives between page1 and page2. Offset pagination can duplicate or skip older items. Keyset pagination avoids shifting-position artifacts for its ordering contract, but live deletions/edits can still change membership; a frozen snapshot needs explicit storage/retention support.

## Tradeoffs, security and operation

Chronological order is easier to explain than ranked feeds; ranking needs a stable score/version or a session snapshot for repeatable pagination. Hybrid thresholds add operational complexity. Hot authors, backfill lag and block/delete propagation need monitored contracts.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Does a keyset cursor guarantee a globally frozen timeline?

**Expected reasoning:** No. It identifies an ordering boundary; live source membership, edits and deletes still matter. State whether the view is live or snapshot-based and enforce visibility either way. Followup: how do equal timestamps use a stable tie-breaker?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html).
- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
