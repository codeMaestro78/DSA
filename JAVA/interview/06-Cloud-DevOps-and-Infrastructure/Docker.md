# Docker: reproducible images and runtime boundaries

## Learning goal and scope

An image packages filesystem layers and metadata; a running container is a process environment built from it. Container isolation is not a substitute for application authorization, patched dependencies or durable storage. The goal in an interview is to explain what is reproducible and what still depends on the host and runtime configuration.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Separate build inputs, build-time secrets, output artifact and runtime settings. Multi-stage builds keep compiler/toolchain dependencies out of the final runtime stage. Pin and record image/dependency identities, then deliberately rebuild/update them; a pinned vulnerable image remains vulnerable. Use a small necessary runtime surface, a suitable non-root user and explicit writable locations. Keep persistent business data outside the disposable writable layer.

## Worked example

Fictional trace: revision R builds against a moving base tag on Monday and Friday. The application commit matches but the base digest differs, so a changed native library is a plausible explanation for the different behavior. Reproduce with recorded digests and dependency locks. If a build copies a secret into one layer and deletes it in a later layer, earlier layer history may still contain it. Do not claim deletion from the final directory makes the image safe.

## Tradeoffs, failure and access boundaries

A smaller image reduces transfer/surface but does not prove lower application latency or no vulnerabilities. Avoid mounting the container runtime socket into an untrusted build: it expands authority substantially. Test startup, signal handling and graceful shutdown against the actual runtime rather than assuming a successful image build proves service behavior.

## Attempt first — OPS7-DOCKER

A container stores uploaded files in its writable layer and loses them after replacement. What artifact/ownership change repairs the design?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Put durable uploads in an explicitly owned persistent store with authorization, retention and backup/recovery rules. Keep the image immutable and replaceable. A mounted volume may supply persistence on one runtime, but its failure/placement/backup semantics need proof; an object store is another option when the access pattern fits.

**Followups:** Which inputs must be recorded to reproduce a build? What remains when an image is pinned by digest?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Docker build practices](https://docs.docker.com/build/building/best-practices/) — Docker; rolling documentation; scope: Image/build inputs and stages; no image built.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
