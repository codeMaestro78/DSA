# Subscription video playback: hypothetical design case

## Attempt-first scope

Design a catalog and playback service for licensed videos with regional/device eligibility and resume positions. Licensing negotiation and DRM cryptography are excluded. This is an original subscription-video exercise, not the named company's private architecture.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 1,000 simultaneous streams averaging 4 Mbit/s:4 Gbit/s of media payload before protocol overhead. Playback-control requests are a different workload from media egress; the API server should not proxy every media byte by default.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`GET /catalog` returns allowed metadata; `POST /playback-sessions` checks subscription/region/device rules; `PUT /me/progress/{title}` records a versioned position under a defined conflict policy.

Catalog/entitlement authority owns eligibility and title versions. Object/CDN stores immutable renditions; short-lived credentials are derived access. Resume state belongs to the account/profile and may merge updates by session/version rather than an untrusted largest timestamp.

## Critical flow

Authorize a session, choose a compatible active manifest and issue appropriately scoped media access. The player fetches segments from the delivery tier and reports progress separately. Availability of media caches should not bypass entitlement checks or leak private account metadata.

## Failure trace

A subscription is revoked while a playback credential is valid. Define the accepted revocation bound and segment access policy; a credential already issued can remain usable until expiry unless a coordinated check invalidates it. Data already buffered cannot be retracted.

## Tradeoffs, security and operation

CDN delivery lowers origin load but brings cache/egress/region costs. More renditions improve network/device fit but consume transcode/storage. Use aggregate playback error/startup/quality signals with privacy limits; no single control-plane p95 describes media quality.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Why separate control and media paths?

**Expected reasoning:** Their bandwidth, caching and authorization lifecycles differ. Keep eligibility/session state authoritative while serving immutable segments through a controlled delivery tier. Followup: what happens if entitlement checks fail while cached segments remain reachable?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html).
- [Media Source Extensions working draft](https://www.w3.org/TR/media-source-2/).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
