# Versioned private file storage: hypothetical design case

## Attempt-first scope

Design private upload, metadata, download and deletion for files. Collaborative text editing and a distributed filesystem implementation are excluded.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 5,000 files/day×4 MB=20,000 MB/day decimal raw content. Three retained versions would triple that content estimate only if every file actually has three equally sized versions; replicas, metadata and egress remain additional.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /upload-sessions` creates a bounded authorized upload; completion verifies parts/checksum and records a committed version. `GET /files/{id}/versions/{version}` checks current rights before issuing scoped access. Deletion has a visible state and policy.

Metadata authority owns file identity, tenant/owner, version, checksum, size and status. Object keys identify immutable generations rather than user-provided paths. A multipart upload is temporary until completion/publication; orphan garbage collection must not delete active versions.

## Critical flow

Authorize the session, upload parts privately, verify completion/integrity and atomically publish metadata pointing to the complete immutable object. A lost completion response resolves to the same version identity. Readers receive only committed eligible generations; new versions do not overwrite bytes being read.

## Failure trace

A client retries completion after a timeout. Reuse the session/version identity and detect matching versus conflicting content. If metadata commit fails after object completion, reconcile the orphan rather than advertise it. Deleting metadata must also schedule permitted object/index/cache cleanup without claiming every backup vanished instantly.

## Tradeoffs, security and operation

Proxy downloads simplify centralized checks but consume application bandwidth; scoped direct-object credentials reduce that cost but introduce bearer lifetime/revocation limits. Content-addressed storage helps integrity/deduplication but requires privacy-aware ownership/refcount design; a checksum is not authority to read another user's file.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** May a guessed object hash authorize download?

**Expected reasoning:** No. Identity/content address and current resource permission are different. Resolve ownership and policy before exposing content or credentials. Followup: what happens if two tenants reference the same deduplicated blob and one deletes its file?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html).
- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
