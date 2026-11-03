# Practical cloud and DevOps questions

Ten operational scenarios span Linux, network layers, builds, orchestration, delivery and incidents. These are diagnostic discussions: no commands, clusters, cloud accounts or privileged workflows were executed. Detailed Cloud/DevOps teaching chapters remain separate backlog work.

All prompts and example workloads are original, company-neutral **[INFERRED]** practice. Difficulty, role and round labels are editorial guidance, not employer ratings. Sources support underlying mechanisms, not the invented scenario or a unique correct architecture. Attempt the prompt before opening the review.

<a id="qb-ops-001"></a>

## QB-OPS-001 — The process died while the host looked healthy

| Field | Value |
|---|---|
| Category | Linux resource diagnosis |
| Difficulty | Beginner |
| Target roles | Backend; platform; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Linux resource diagnosis: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-002, QB-OPS-003 |
| Sources | [CGROUP: Control Group v2](https://docs.kernel.org/admin-guide/cgroup-v2.html) |

### Prompt

A container repeatedly exits with code 137 during report generation. Host free-memory graphs look comfortable, and the service has a 512 MiB container memory limit. Describe evidence that distinguishes a cgroup out-of-memory kill from another SIGKILL. Suggest containment before changing limits.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Exit code 137 is commonly consistent with termination by SIGKILL, but does not prove an out-of-memory cause. Inspect container/runtime termination reason, kernel events when permitted, and the relevant cgroup memory.current, memory.max and memory.events counters. Compare event increments with the failure timestamp and workload. Host free memory can remain available while a workload hits its own cgroup limit. Distinguish process resident memory, native allocations, page cache accounting and application heap metrics.

**Explanation:** If one report loads an entire dataset, temporarily cap report concurrency or input size and stream/chunk work where possible. Increasing a limit may help a known legitimate working set but can move pressure to the node; check total reservations and headroom. A supervisor can also send SIGKILL during shutdown escalation, so correlate runtime and deployment events. Preserve bounded diagnostic metadata without collecting customer report contents. The next validation should reproduce the largest representative report in an isolated environment with memory measurement.

**Followups:** What if the language heap stays below 300 MiB? How do memory requests differ from limits in a scheduler? What happens when concurrency doubles?

**Common mistakes:** Treating exit code alone as definitive OOM proof; reading only host graphs; raising every container limit without checking node capacity.

</details>

<a id="qb-ops-002"></a>

## QB-OPS-002 — Some clients still reach the old address

| Field | Value |
|---|---|
| Category | DNS caching and failure localization |
| Difficulty | Beginner |
| Target roles | Full-stack; backend; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | DNS caching and failure localization: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-003, QB-OPS-004 |
| Sources | [DNS: Domain names: concepts and facilities](https://www.rfc-editor.org/rfc/rfc1034.html) |

### Prompt

An API hostname’s address changed ten minutes ago. Authoritative DNS answers show the new address, but some clients still connect to the old one. The old positive answer had a one-hour TTL. Explain a safe diagnostic sequence and what flushing one laptop proves.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Compare the authoritative answer, the client’s configured recursive resolver, operating-system/application caches and the address used by the failing connection. DNS caching permits a previously received positive answer to remain until its remaining TTL expires. A later reduction of the authoritative TTL does not retroactively shorten a copy already cached elsewhere. Also check whether a long-lived connection keeps using the old server without another DNS lookup.

**Explanation:** If the old address is still safe, maintain an overlap period during migration; if it is unsafe or unavailable, document that cached clients may fail until they refresh and choose a deployment-specific containment. Flushing one laptop only tests that local path. It does not demonstrate that every recursive resolver or application has refreshed. A lookup can succeed while TCP/TLS/HTTP later fails, so record the resolved address and failed stage instead of calling every connection problem “DNS propagation.” Commands and resolver behavior vary by environment.

**Followups:** What differs for a negative cached answer? How would a connection pool affect migration? Which TTL did the actual client receive?

**Common mistakes:** Assuming the authoritative answer is every client’s answer; expecting TTL changes to invalidate prior caches instantly; disabling all caching as a fix.

</details>

<a id="qb-ops-003"></a>

## QB-OPS-003 — TLS fails only through one entry point

| Field | Value |
|---|---|
| Category | TLS handshake and certificate scope |
| Difficulty | Intermediate |
| Target roles | Backend; platform; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | TLS handshake and certificate scope: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-004, QB-OPS-005 |
| Sources | [TLS: TLS 1.3](https://www.rfc-editor.org/rfc/rfc8446.html) |

### Prompt

https://api.example.test works through one load-balancer address but another reports a hostname/certificate error after a certificate rotation. DNS resolution and TCP connection both succeed. Explain what to compare, and why disabling certificate verification is not a repair.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Compare the certificate chains actually served by both entry points, the requested hostname and Server Name Indication, certificate validity times, Subject Alternative Name coverage, trust chain and load-balancer listener configuration. A rotation may have updated only one listener or backend. Ensure the client uses the hostname rather than treating a raw IP connection as equivalent; the server may select its certificate based on the offered name. Identify where TLS terminates and whether there is a second encrypted hop.

**Explanation:** The successful TCP connection rules out only some network failures; it says nothing about authenticated TLS completion. A valid certificate for another name does not authenticate this API. Restore the correct certificate/configuration consistently and verify with a trusted client from representative paths. Disabling verification conceals the failed identity guarantee and permits interception. Collect certificate metadata and handshake errors, never private keys. An internal self-signed trust model, if intentional, still needs explicit configured trust and hostname validation.

**Followups:** How can clock skew cause a validity failure? Does client-to-edge TLS prove edge-to-backend encryption? How would mutual TLS change diagnosis?

**Common mistakes:** Uploading private keys to a debug site; confusing encryption with endpoint authentication; concluding “network healthy” after TCP alone.

</details>

<a id="qb-ops-004"></a>

## QB-OPS-004 — A rebuilt image behaves differently with the same code

| Field | Value |
|---|---|
| Category | Container build inputs and reproducibility |
| Difficulty | Intermediate |
| Target roles | Backend; full-stack; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Container build inputs and reproducibility: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-005, QB-OPS-006 |
| Sources | [DOCKER: Building best practices](https://docs.docker.com/build/building/best-practices/) |

### Prompt

A team rebuilds a service from the same commit a week later and gets a different dependency behavior. Its base image uses a floating tag; package installation ignores the lockfile. Propose a reproducible build and deployment identity, including security-update tradeoffs.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Pin base-image content by digest, install the committed lockfile through the package manager’s deterministic mode, and make build-time configuration explicit. Use stages to separate build tools from the runtime image and copy only needed artifacts. Record source commit, dependency/lockfile inputs, toolchain and resulting image digest. Promote the same tested image digest between environments instead of rebuilding independently for each one. Keep secrets outside image layers and build arguments intended for persistent image metadata.

**Explanation:** Identical source is insufficient when registry tags or dependency resolution change. Digest pinning makes inputs identifiable but does not magically eliminate nondeterminism from timestamps, network downloads or native toolchains. It also freezes vulnerabilities until a controlled update rebuilds and tests the image. Schedule refreshes with reviewable dependency changes and rollback to a known compatible digest. Image size alone is not a security assessment; validate required runtime libraries and non-root filesystem access against the actual service.

**Followups:** How do you reproduce a native library build? Which environment values belong at runtime? What evidence links the deployed digest to CI results?

**Common mistakes:** Equating a Git tag with immutable image content; copying secrets then deleting them in a later layer; pinning forever without an update process.

</details>

<a id="qb-ops-005"></a>

## QB-OPS-005 — A dependency outage causes a restart cascade

| Field | Value |
|---|---|
| Category | Kubernetes probe semantics |
| Difficulty | Intermediate |
| Target roles | Backend; platform; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Kubernetes probe semantics: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-006, QB-OPS-007 |
| Sources | [PROBE: Configure liveness, readiness and startup probes](https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/) |

### Prompt

An API takes 90 seconds to initialize. Its liveness endpoint checks a remote database, and readiness uses the same endpoint. A database outage causes every Pod to restart repeatedly. Propose separate startup, readiness and liveness behavior; state the tradeoff if all replicas become unready.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use a startup probe sized to the observed initialization bound so liveness and readiness do not interfere before startup succeeds. Liveness should test whether this process is irrecoverably stuck and a restart is likely to help, not simply whether a shared remote database is reachable. Readiness indicates whether the Pod should currently receive service traffic; include dependency checks only when loss actually prevents the supported request path. Set timeouts/thresholds to avoid reacting to harmless brief delays.

**Explanation:** If the database fails globally, restarting healthy processes adds work without restoring the dependency. Marking every Pod unready may protect callers from unusable instances but leaves no ready service endpoints; decide whether graceful error responses or a degraded read path are preferable. Readiness removal does not cancel existing requests or guarantee instantaneous traffic cessation across every intermediary. Monitor probe failures alongside user-visible success and database health. Avoid returning success from readiness while the service cannot perform its promised critical function.

**Followups:** How do long-lived streams drain? Which endpoints remain usable without the database? How would a permanently stuck event loop be detected?

**Common mistakes:** Using remote dependency reachability as universal liveness; believing readiness failure restarts the container; using a longer initial delay without modeling startup variability.

</details>

<a id="qb-ops-006"></a>

## QB-OPS-006 — Rolling back the image does not roll back the schema

| Field | Value |
|---|---|
| Category | Deployment compatibility and stop conditions |
| Difficulty | Advanced |
| Target roles | Backend; platform; full-stack |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Deployment compatibility and stop conditions: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-007, QB-OPS-008 |
| Sources | [DEPLOY: Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/); [CONSTRAINT: Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html) |

### Prompt

Version N reads column full_name. Version N+1 splits it into given_name/family_name and immediately drops full_name. During a rolling deployment both versions run. Propose a safe migration with a rollback point and explain what Kubernetes rollout status does not validate.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use an expand-and-contract sequence. First add compatible new fields without removing the old representation. Deploy code that maintains compatibility, with an explicit strategy for synchronizing concurrent writes and backfilling old rows. Validate row counts and semantic mapping, especially names that cannot be reliably split. Then switch reads after verification; only remove the old field after old binaries/jobs and the rollback window no longer depend on it. Treat irreversible data changes as a separate release decision.

**Explanation:** A rollout controller can report replica progress/availability while the application writes wrong names or serves semantically broken results. Observe representative user journeys, error rates and data correctness during each stage. Stop or revert the application when gates fail while the expanded schema still supports the prior version. Rolling back an image cannot recreate dropped data. Bound migration batches and watch database locks. Use surge/unavailable settings appropriate to capacity and draining, but do not confuse those settings with data compatibility.

**Followups:** How would duplicate writes race during backfill? What does an old background worker need? Which metric stops the deployment before full rollout?

**Common mistakes:** Dropping a field during mixed-version operation; calling a successful rollout command an end-to-end correctness test; promising instant reversal of destructive data migration.

</details>

<a id="qb-ops-007"></a>

## QB-OPS-007 — A pull request must not become cloud authority

| Field | Value |
|---|---|
| Category | CI trust boundaries and short-lived credentials |
| Difficulty | Advanced |
| Target roles | Full-stack; platform; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | CI trust boundaries and short-lived credentials: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-008, QB-OPS-009 |
| Sources | [CI: Secure use reference](https://docs.github.com/en/actions/reference/security/secure-use); [OIDC: OpenID Connect](https://docs.github.com/en/actions/concepts/security/openid-connect) |

### Prompt

A public-repository pull request can modify build scripts. CI currently executes those scripts in a job that also holds a long-lived cloud deployment key. Design separate validation and deployment trust paths using GitHub Actions concepts; no live workflow changes are requested.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Run untrusted pull-request validation without deployment secrets or write permissions, using least-privilege tokens and isolated disposable execution. Treat modified scripts, dependency hooks and artifacts as untrusted. Restrict deployment to a trusted reviewed revision and protected environment with appropriate approval/policy gates. Use OIDC federation for short-lived cloud credentials, with cloud trust bound to the intended repository, branch/environment and audience. Grant only the necessary deployment role; remove the stored long-lived key after a managed rotation.

**Explanation:** OIDC reduces credential persistence but does not make arbitrary code in a privileged job safe: that code can use the short-lived role during its validity. Keep privilege acquisition after the trust decision, pin third-party actions to reviewed immutable revisions, and avoid interpolating untrusted text into shell source. Verify which commit and digest were built and promoted; a poisoned artifact from an untrusted run must not gain trust merely because a later job downloads it. Never print tokens to logs.

**Followups:** How do forked PRs differ? What if an action is compromised? Which claim in the cloud trust policy restricts a deployment environment?

**Common mistakes:** Giving write tokens to all jobs; assuming masked logs make secret execution safe; believing short-lived credentials remove the need for a trust boundary.

</details>

<a id="qb-ops-008"></a>

## QB-OPS-008 — A release and an outage happen at the same time

| Field | Value |
|---|---|
| Category | Incident triage and evidence |
| Difficulty | Intermediate |
| Target roles | Backend; platform; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Incident triage and evidence: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-009, QB-OPS-010 |
| Sources | [INCIDENT: Managing incidents](https://sre.google/sre-book/managing-incidents/); [DEPLOY: Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) |

### Prompt

Five minutes after a release, checkout errors rise from 0.1% to 12%. Database latency also increased, but its dashboard is delayed. You are the first responder. Give the first actions, a rollback criterion and the evidence needed before declaring a root cause.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Confirm current user impact from live request/error signals, declare an incident at the appropriate severity, and establish an incident lead, communication channel and timestamped timeline. Pause unrelated changes and identify the changed revision, affected cohorts and dependencies. If a known-good compatible rollback is available and the release plausibly causes ongoing impact, prioritize restoring service under the team’s change authority, with explicit success/stop criteria. Preserve logs, traces and deployment metadata without delaying containment.

**Explanation:** After rollback, compare checkout success and latency over a meaningful window; if the rate remains high, investigate correlated database saturation, traffic changes or provider failures. Improvement strengthens a release association but does not identify the defective line or exclude other concurrent causes. Record hypotheses and disconfirming evidence separately from facts. Keep status updates focused on impact, mitigation and next checkpoint, then follow with a blameless review and owned prevention actions. Do not wait for a perfect dashboard before protecting customers.

**Followups:** What if the migration makes rollback unsafe? What if only one payment method fails? How do you avoid losing evidence during mitigation?

**Common mistakes:** Arguing root cause before containment; presenting correlation as proof; announcing recovery after a single successful request.

</details>

<a id="qb-ops-009"></a>

## QB-OPS-009 — A fast average hides a broken user journey

| Field | Value |
|---|---|
| Category | SLIs, SLOs and burn rate |
| Difficulty | Intermediate |
| Target roles | Platform; backend; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | SLIs, SLOs and burn rate: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-010, QB-OPS-001 |
| Sources | [SLO: Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) |

### Prompt

For a hypothetical checkout service, define success as a valid purchase attempt completed correctly within two seconds. Its 30-day SLO is 99.9%. In a sufficiently large ten-minute window, 1% of eligible attempts fail that definition. Compute error-budget burn rate and propose an alert strategy; do not assume traffic is uniform.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The allowed bad fraction is 0.1%, while the observed bad fraction is 1%; the short-window burn rate is 10×. Define eligible events carefully, avoiding accidental inclusion of malformed requests or exclusion of real server-caused failures. Measure the complete user journey, not an average of per-service latency or the sum of component p95s. Count incorrect-but-fast responses as bad events too. Keep a durable definition and consistent numerator/denominator.

**Explanation:** Use sustained multi-window evidence to page on rapid budget consumption while reducing single-window noise; slower burn can create a lower-urgency action. Ten minutes at 10× does not by itself determine the exact wall-clock time to exhaust a 30-day request-based budget when future traffic is nonuniform. Low traffic needs special treatment because one failure can dominate a fraction. Segment diagnostic views by tenant/region/payment method without defining away aggregate customer harm. The numbers are illustrative, not an observed service measurement.

**Followups:** How do you classify client cancellations? What counts during provider outages? How does a time-based availability SLO differ from a request-based one?

**Common mistakes:** Using average latency as the only SLI; adding component p95s; claiming exact remaining time from one small window without denominator assumptions.

</details>

<a id="qb-ops-010"></a>

## QB-OPS-010 — The cloud bill rises while useful output stays flat

| Field | Value |
|---|---|
| Category | Cost diagnosis and unit economics |
| Difficulty | Intermediate |
| Target roles | Platform; backend; AI engineer; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Cost diagnosis and unit economics: correctness, assumptions, failure reasoning |
| Related IDs | QB-OPS-001, QB-OPS-002 |
| Sources | [COST: Cost Optimization Pillar](https://docs.aws.amazon.com/wellarchitected/latest/cost-optimization-pillar/welcome.html) |

### Prompt

A batch service’s monthly cost doubles while successful jobs remain roughly constant. No current price list or account access is available. Give a data collection plan and an optimization experiment that preserves reliability; provide no invented provider prices or promised savings.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Normalize cost by successful useful jobs and segment spend by compute, idle capacity, storage, request operations, network transfer, logs and retries. Compare matching time periods, traffic mix, region, resource ownership and billing attribution; separate one-off migration charges from recurring increases. Investigate failed/retried work, longer runtimes and overprovisioned always-on resources before buying discounts. Record current reliability targets and minimum recovery capacity so optimization has an explicit constraint.

**Explanation:** A plausible experiment caps duplicate background retries and measures cost per successful job, completion delay and failure rate on a bounded workload. If jobs double in size, flat job count alone is misleading; include an appropriate work unit such as records processed. Rightsizing may reduce idle spend but can worsen queue age or recovery performance. Commitments can lower effective rates for known steady demand while creating financial lock-in; require measured usage and current pricing before selecting them. Keep an owner, rollback trigger and before/after evidence.

**Followups:** How does cross-region egress appear? When does batching change latency? What evidence justifies a commitment purchase rather than a runtime change?

**Common mistakes:** Quoting stale prices from memory; cutting redundancy without an SLO decision; optimizing total cost while failed-job cost per success increases.

</details>

## References and verification

[Phase 5 system source register](PHASE5_SYSTEM_SOURCES.md). [Question-bank navigation](README.md).

Last verified: **2026-11-02**. Primary sources inspected for the stated mechanisms. Scenario reasoning reviewed; this is not a production design, benchmark, cloud deployment or candidate assessment.
