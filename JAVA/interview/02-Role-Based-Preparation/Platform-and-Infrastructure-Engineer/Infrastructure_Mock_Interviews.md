# Infrastructure mock — bounded capacity under a partial failure

This is a complete focused **[INFERRED]**, company-neutral variant for approximately 0–2 years of experience. All facts and numbers are fictional teaching fixtures. It supplements the [Phase 6 integrated session](../../13-Mock-Interviews/System_Design_Mock_Interview.md); it is not an employer assessment or permission to perform a real action. No implementation, provider, customer, host, cluster or deployment is exercised here.

## Candidate packet — attempt first

**Mock ID:** INFRA7-01. **Duration:** 60 live minutes; preflight and 10–15-minute debrief are additional. Use notes, a diagram and calculator. No real system access or generative assistance is needed for the default independent attempt. Agree tools and format first. Keep the disclosure below closed. Read [the prerequisite](Platform_Engineering.md) before the session if needed.

### Stipulated scenario

A fictional service has two identical worker replicas, each with a stipulated10 jobs/s service rate. For a pure arithmetic fixture both remain available: arrivals peak at 30 jobs/s for120 seconds, then return to 10 jobs/s; the queue starts empty. Each job is a durable request with a stable identity and no external irreversible action.

In a separate incident variant, one replica restarts repeatedly after a liveness check queries an unhealthy shared dependency. Host CPU appears low; one workload also records cgroup throttling. No actual logs, kernel profile, dependency recovery time or loss of accepted data is established. Current traffic shape during the incident is unknown. The operator asks for “more replicas and larger timeouts” immediately.

### Four-stage timeline and questions

| Stage | Minutes | Candidate task |
|---|---|---|
| 1 | 10 | Restate acceptance/durability, queue and tenant boundaries. Compute the idealized backlog/drain with both replicas healthy and identify what that calculation excludes. |
| 2 | 20 | Trace client/API, durable acceptance, queue ownership and worker claim/result state. Define bounded admission, per-tenant fairness and what survives a worker crash. |
| 3 | 20 | Investigate the incident without confusing the idealized calculation with observed behavior. Separate liveness/readiness/startup, cgroup/host scope and dependency effects; propose containment and discriminating checks. |
| 4 | 10 | Give a recovery/rollout handoff with owners, verified-versus-proposed evidence and one unresolved risk. Outline a restore check rather than claiming configured backups establish recovery. |

Save assumptions, calculations, flows and uncertainty before reading the review. Asking for a stipulated clarification is H0 and does not reduce independence. Record supplied direction as H1/H2/H3, then assess its actual effect. A new attempt after reading the answer is guided retention evidence; use a changed case later to test transfer.

<details><summary>Interviewer packet and worked review — reveal only after attempting</summary>

## Interviewer administration

Ask one focused question at a time and use the four-stage budget. Offer a neutral clarification before a hint. Do not silently add advanced requirements or reward an unsupported production promise. Keep optional extensions after a sound core. Record tools, pauses and exact hint content.

H0: the idealized service rate is 20 jobs/s total while both workers remain healthy. Rates are invented fixed inputs, not a measured guarantee. The incident variant cannot reuse that capacity without new evidence. A worker crash may occur after a durable result but before delivery acknowledgement; accepted jobs must not disappear solely because the worker exits.

### Stage 1 review — minutes 0–10

Net burst accumulation is 30−20=10 jobs/s for120s, giving 1,200 jobs. Healthy net drain after the burst is 20−10=10 jobs/s, so120 seconds. This excludes variability, retries, downstream failures, cold start and incident capacity. State the unit and stable-flow assumptions; low average utilization or a daily mean cannot establish tail completion time or failure headroom.

### Stage 2 review — minutes 10–30

A modular service plus durable job store/queue can suffice. Return accepted only after the defined durable boundary; record scoped operation identity/fingerprint and authorize tenant access. Bound outstanding payloads as well as worker count. Claim attempts with an ownership/version protocol and condition result acceptance so an old worker cannot overwrite a newer accepted result. Re-delivery may recompute work, so deduplicate acceptance and reconcile durable state after a crash. Retention, retry budgets, poison work and overload responses require explicit policies.

### Stage 3 review — minutes 30–50

Contain restart amplification using an appropriate local liveness check and explicit dependency degradation/readiness policy, not indiscriminate restarts. Do not remove all serving replicas mechanically. Inspect actual restarts, probe outcomes, dependency latency and queue age to distinguish causes. Host spare CPU does not remove a cgroup quota; compare quota/period, throttling and runnable work at the same scope. Adding replicas can worsen a shared dependency overload, while a longer timeout can retain more work. Each change needs a bounded experiment/stop point under review.

### Stage 4 review — minutes 50–60

State known symptoms and unresolved causes. Name the service/dependency/operator owners, a recovery gate based on user-level completion and queue age, and rollback compatibility. Reconcile accepted/result state before replaying. Verify a representative restore including known data and identity/state continuity; a backup checkbox cannot establish recovery point/time targets. All incident actions here remain proposed, not executed host/cluster work.

### Hints and followup branches

**H1:** ask which requirement or fact changes the current answer. **H2:** point to the decisive identity, version, denominator or ownership boundary, without finishing the trace. **H3:** supply one worked transition or numerator/denominator and ask the candidate to finish independently. Log what was supplied; do not apply a universal score deduction to unrelated evidence.

**If stuck:** reduce to one record, one request or one failure point and retain partial correct work. **If finished:** change the failure point, introduce an out-of-order/duplicate transition or remove an assumed guarantee. Ask what observation would refute the preferred explanation. The reference approach is not the only valid implementation: accept a simpler batch/polling/manual-review design when it meets the stated contract and explains its costs.

### Task-specific evaluation

Use the [shared six-dimension rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). These are level 3 evidence anchors, not a pass threshold. Use 0–2 according to actual errors/help; leave unobserved dimensions Not assessed. Level 4 requires repeated independent evidence across variations, not one fluent mock.

| Dimension | Observable level 3 evidence | Blocker or next remediation |
|---|---|---|
| CONTRACT | Defines accepted work, tenant scope and healthy-fixture assumptions | Treats acceptance as merely enqueuing in process memory |
| REASONING | Separates resource and dependency hypotheses | Scales blindly from host CPU alone |
| CORRECTNESS | Computes1,200/120 and consistent claim/result ownership | Reuses healthy capacity through unknown failure |
| VALIDATION | Proposes controlled probe/throttle/crash and restore checks | Claims one health endpoint proves recovery |
| RISK | Bounds queues/retries and avoids restart amplification | Creates unbounded retry or cross-tenant work starvation |
| COMMUNICATION | Names observations, unknowns, owners and next check | Invents root cause or recovery ETA |

**Common mistakes:** converting a proposed test into an executed claim, hiding a known contract violation behind a favorable metric, assuming a local state change cancels a remote effect, or inventing past ownership. **Strong indicators:** a concrete counterexample, a bounded next check, honest source/authority limits and an alternative with explicit consequences.

</details>

## Debrief and proof boundary

Spend 10–15 minutes recording task evidence, hints and one or two improvements. No candidate score, customer endorsement or observed system result is prefilled. Worked arithmetic and traces are reviewed reasoning only; no new executable suite is claimed. The linked canonical chapters and [source register](PHASE7_SOURCES.md) support the mechanisms; they do not establish the fictional outcomes.

[Parent index](README.md). Last reviewed: **2026-11-03**.
