# User-uploaded video pipeline: hypothetical design case

## Attempt-first scope

Design a service that accepts user videos, transcodes a few renditions and serves adaptive playback. Copyright adjudication and a production codec are excluded. This product-class exercise does not describe the named company's implementation.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 100 uploads/day×1 GB average source=100 GB/day decimal raw ingest. Three retained renditions totaling 1.5 GB per upload add150 GB/day under this invented size assumption; popularity/egress must be modeled separately.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /video-uploads` creates an authorized resumable session; `POST /videos/{id}/complete` verifies uploaded parts; status reports uploaded/processing/ready/failed. Playback returns only a published version and manifest.

Source objects are immutable; video metadata owns tenant/creator/visibility and publication version. Transcode jobs identify source checksum plus recipe version. Segment/manifests are derived artifacts, not the authorization source.

## Critical flow

Store source privately; verify upload completion; commit processing intent. Workers generate renditions into a new generation, validate segments/manifest consistency and atomically activate that generation only when required outputs are ready. Serve cached immutable media through an authorization/CDN policy.

## Failure trace

A worker crashes after writing half a rendition. Unpublished partial objects must not be referenced by an active manifest. Retry idempotently to the intended generation or start a new attempt and garbage-collect abandoned objects. Do not publish a manifest before referenced segments are available.

## Tradeoffs, security and operation

Precompute popular formats for simple playback; just-in-time transcoding saves cold storage but creates request-time latency/compute risk. Adaptive bitrate depends on player/network behavior and format compatibility. A standards document does not establish browser support or throughput of this hypothetical pipeline.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Can “upload succeeded” mean playback is ready?

**Expected reasoning:** No. Upload durability, processing success and publication are different states. Expose processing/failure explicitly and publish only a coherent validated generation. Followup: what should a client see when one optional rendition fails?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [Media Source Extensions working draft](https://www.w3.org/TR/media-source-2/).
- [HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
