# Photo sharing and home feeds: hypothetical design case

## Attempt-first scope

Design private/public photo publication and a following feed. Direct messages, advertising and exact ranking-model design are excluded. This is a hypothetical social-photo product, not private company architecture.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 10,000 uploads/day with 2 MB originals:20,000 MB/day decimal before derivatives/replicas. A post delivered to 500 followers creates500 feed references under fan-out-on-write; large publishers change that cost sharply.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /uploads` authorizes a bounded upload; `POST /posts` publishes only a verified completed object; `GET /feed?cursor=...` returns a stable paginated view under an explicit freshness policy.

Metadata store owns post identity, owner, visibility and publication state. Object storage owns immutable media versions; feed entries hold post references rather than independent authority. Follows/blocks and current visibility gate reads and media credentials.

## Critical flow

Upload privately, validate type/size and process safe derivatives asynchronously. Commit a published post with durable feed work. A fan-out worker materializes references for ordinary publishers; merge high-fan-out sources at read time if justified. Resolve references under current visibility before returning a feed.

## Failure trace

A post becomes private after its public thumbnail was cached. Stop new unauthorized publication and invalidate/version controlled caches, but do not promise retracting data already downloaded or a still-valid bearer URL. Feed-entry deletion alone cannot revoke every media copy.

## Tradeoffs, security and operation

Fan-out-on-write makes reads cheap but produces write amplification and stale references; fan-out-on-read reduces publication work but adds read merging. A hybrid needs a threshold justified by workload, not a famous-company diagram. Track publication lag and media-processing failures separately from ranking quality.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** A feed contains a reference to a deleted private post. Is deleting the feed row sufficient?

**Expected reasoning:** It helps cleanup, but current authority must also gate resolution and object access. Version/invalidate derived caches, track deletion lineage and follow the real retention policy. Followup: what remains impossible for copies already downloaded?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
