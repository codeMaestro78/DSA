# Practical system design questions

Ten bounded scenarios test contracts and failure reasoning. Draw a critical flow and name the data owner before discussing components. Beginner is the starting point; Expert is an optional extension.

All prompts and example workloads are original, company-neutral **[INFERRED]** practice. Difficulty, role and round labels are editorial guidance, not employer ratings. Sources support underlying mechanisms, not the invented scenario or a unique correct architecture. Attempt the prompt before opening the review.

<a id="qb-sys-001"></a>

## QB-SYS-001 — A retry must not create another export

| Field | Value |
|---|---|
| Category | API contracts and idempotency |
| Difficulty | Beginner |
| Target roles | SDE; backend; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | API contracts and idempotency: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-002, QB-SYS-003 |
| Sources | [HTTP: HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html); [CONSTRAINT: Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html) |

### Prompt

A tenant submits POST /exports. Creation commits but its response is lost; the browser retries. Specify the request identity, stored data and responses. Export workers may run minutes later. Do not assume the client can tell whether the first request succeeded.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Accept a client-generated idempotency key scoped to tenant and operation. Store its request fingerprint, export ID and initial durable response atomically with the export job. A uniqueness constraint makes simultaneous submissions converge on one record. Return the existing job for the same key and same intent; reject reuse with different input. A successful creation can return 202 with an authorized status URL. Persist queued/running/succeeded/failed state separately from HTTP connection lifetime.

**Explanation:** For example, tenant A retries key k after job 42 committed: both responses identify 42, even if worker execution has not begun. Tenant B using k addresses a separate namespace. Authentication must precede access to the stored response. Define retention and client retry windows; removing a key too early can permit a new job. HTTP method semantics alone do not give this POST application-level deduplication. Worker retries still need separate outcome handling.

**Followups:** How does a key expire? What if the first transaction is still open? How are cancellation and result deletion represented?

**Common mistakes:** Keeping keys only in one process; returning another tenant’s response; treating a timeout as proof that creation failed.

</details>

<a id="qb-sys-002"></a>

## QB-SYS-002 — A stale writer overwrites an edited document

| Field | Value |
|---|---|
| Category | Optimistic concurrency and data modeling |
| Difficulty | Intermediate |
| Target roles | Backend; full-stack; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Optimistic concurrency and data modeling: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-003, QB-SYS-004 |
| Sources | [HTTP: HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html) |

### Prompt

A document contains body and version. Two editors read version 7. A saves, then B saves an unrelated local revision based on version 7. Design a contract that detects this conflict and explain delete/recreate behavior.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Give every document lifetime a stable identity and every change a fresh version token. Return a strong ETag for the selected representation and require If-Match on updates. Atomically update only when tenant, document ID and expected version match; advance the version in that same write. If the representation changed, return a precondition failure and let B fetch the new representation, merge or explicitly abandon edits. Do not silently replay B’s stale full replacement.

**Explanation:** In the trace, A changes 7 to 8; B’s condition on 7 matches zero rows. Preserve access checks and distinguish inaccessible/missing resources according to the API’s disclosure policy. A document recreated under a friendly name must receive a new identity or non-reused generation token; resetting a small version counter can accidentally accept an old request. Partial patches may reduce conflicts but cannot automatically prove semantic independence. This design favors visible conflicts over silent lost work.

**Followups:** When can server-side field merging be safe? How should clients handle an ambiguous successful save followed by lost response?

**Common mistakes:** Reading version then issuing an unconditional update; using coarse timestamps with collisions; assuming PATCH itself prevents lost updates.

</details>

<a id="qb-sys-003"></a>

## QB-SYS-003 — An expiring cache serves old policy-sensitive results

| Field | Value |
|---|---|
| Category | Cache keys, invalidation and privacy |
| Difficulty | Intermediate |
| Target roles | Backend; AI engineer; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Cache keys, invalidation and privacy: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-004, QB-SYS-005 |
| Sources | [CACHE: HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html) |

### Prompt

A report cache uses only report ID. Users from different tenants can request similarly numbered reports; access can be revoked while a report is cached. Reports allow 30 seconds of data staleness, but revoked users must receive no new authorized download. Explain a safe read flow.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Authenticate and authorize each request against the current access policy before serving report bytes or issuing a download credential. Include tenant, report identity, representation parameters and relevant data/policy generation in cache identity. Keep a bounded data TTL for the permitted freshness window, and invalidate or version entries on changes. A cache hit supplies data; it does not substitute for permission checking. Prevent shared HTTP intermediaries from retaining private responses through appropriate response directives and deployment checks.

**Explanation:** If user U loses permission at t=5 while bytes remain cached until t=30, the authorization check at t=6 must deny U even though the cache entry exists. Previously issued credentials have their own lifetime and revocation limits, so the strict requirement excludes long-lived bearer download URLs unless they are independently revocable. A stale authorized report and stale authorization are different contracts. Avoid logging report contents or keying solely on guessed IDs.

**Followups:** What happens during policy-service failure? Can an already-started download be revoked? What bounds apply to private browser copies?

**Common mistakes:** Believing TTL enforces revocation; reusing a cross-tenant cache key; claiming deletion retracts data already downloaded.

</details>

<a id="qb-sys-004"></a>

## QB-SYS-004 — Two minutes of traffic create six minutes of recovery

| Field | Value |
|---|---|
| Category | Queue capacity and backpressure |
| Difficulty | Intermediate |
| Target roles | Platform; backend; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Queue capacity and backpressure: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-005, QB-SYS-006 |
| Sources | [OVERLOAD: Handling overload](https://sre.google/sre-book/handling-overload/) |

### Prompt

Assume arrivals jump from 100 jobs/s to 500 jobs/s for 120 seconds. Workers together complete 200 jobs/s and then remain at that capacity. Each job takes equal work. The queue initially has zero jobs. Estimate backlog and drain time after arrivals return to 100/s; propose a bounded overload policy.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The burst adds (500−200)×120 = 36,000 jobs. Afterward the net drain rate is 200−100 = 100 jobs/s, so clearing takes 360 seconds if service capacity and arrivals remain constant. Treat these as illustrative fluid-model estimates, not percentile latency predictions. Set a maximum queue age and backlog, reject or defer excess demand explicitly, and prioritize critical work with tenant-level quotas. Surface accepted job status and expiration rather than silently dropping accepted tasks.

**Explanation:** Adding workers helps only if the actual bottleneck scales; database connections, external quotas and large-job skew can cap throughput. Monitor oldest-job age, completion rate and retry traffic. Dead-letter repeatedly failing jobs so one poison input does not consume capacity indefinitely. A queue absorbs a bounded burst but does not create sustainable capacity. During saturation, admission control protects useful completions; otherwise retries and expired work can consume the same scarce resources.

**Followups:** What changes for variable job sizes? What if half the backlog expires? How do you fairly schedule small and large tenants?

**Common mistakes:** Dividing backlog by gross worker rate; assuming an unlimited queue is reliable overload handling; scaling workers without checking dependencies.

</details>

<a id="qb-sys-005"></a>

## QB-SYS-005 — Database change commits but the event disappears

| Field | Value |
|---|---|
| Category | Transactional outbox and consumer ownership |
| Difficulty | Advanced |
| Target roles | Backend; platform; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Transactional outbox and consumer ownership: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-006, QB-SYS-007 |
| Sources | [OUTBOX: Transactional outbox pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html); [SQS: At-least-once delivery](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/standard-queues-at-least-once-delivery.html) |

### Prompt

An inventory service commits a reservation then publishes an event to a broker. A crash between these steps leaves the reporting service unaware. Design the write and relay flow. The broker may redeliver and the relay may crash after publishing.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Write the reservation and an outbox event in one local database transaction. A relay reads committed outbox records and publishes them with stable event IDs; marking progress can follow publication, accepting possible duplicates. The consumer atomically records the event ID and applies its own database mutation, so replay of an already-applied ID has no further database effect. Define each service as sole owner of its writes and version the event contract.

**Explanation:** A crash before commit exposes neither reservation nor event; after commit the relay can recover the pending record. A crash after broker acceptance but before marking publication creates a repeated event, which consumer deduplication must tolerate. Deduplication retention and replay duration must align. If a consumer calls an external fulfillment provider, its local inbox alone cannot guarantee a single provider action: use provider idempotency or reconciliation. Monitor outbox age and poison events without equating event delivery with business completion.

**Followups:** How are events ordered per item? What happens during replay after deduplication records expire? How is schema evolution handled?

**Common mistakes:** Committing first and hoping publication succeeds; claiming the outbox creates exactly-once external actions; deleting failed events without an audit trail.

</details>

<a id="qb-sys-006"></a>

## QB-SYS-006 — A global rate limit must survive multiple gateways

| Field | Value |
|---|---|
| Category | Distributed rate limiting |
| Difficulty | Advanced |
| Target roles | Backend; platform; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Distributed rate limiting: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-007, QB-SYS-008 |
| Sources | [RATE: Additional HTTP status codes](https://www.rfc-editor.org/rfc/rfc6585.html); [OVERLOAD: Handling overload](https://sre.google/sre-book/handling-overload/) |

### Prompt

Three API gateways serve one tenant with a contracted sustained allowance of 60 requests/minute and an allowed burst of 10 requests. Propose token-bucket state and atomic admission. Define behavior when the shared limiter is unavailable; do not assume a window counter has identical semantics.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use capacity 10 and refill one token per second. Atomically refill based on elapsed time up to capacity, check for at least one token, and deduct on admission for the tenant bucket. A shared authority or correctly partitioned owner prevents each gateway independently granting the full allowance. Define whether retries consume tokens and which operation classes need separate buckets. Return 429 for policy rejection with useful retry guidance when determinable.

**Explanation:** A full bucket admits ten simultaneous requests, then roughly one per second; sixty requests over every possible sliding minute is a different contract. A failure policy follows operation risk: reject or use a conservative bounded local fallback for costly writes, while low-risk reads may accept temporary excess if explicitly allowed. A local fallback must divide allowance across instances or quantify maximum overshoot. Monitor hot keys, limiter latency, fairness and admission failures separately from backend errors.

**Followups:** How do clock anomalies affect refill? How would weighted expensive requests consume tokens? What is the partition overshoot bound?

**Common mistakes:** Giving every gateway the full global budget; failing open without a cost bound; describing token buckets as exact sliding-window counters.

</details>

<a id="qb-sys-007"></a>

## QB-SYS-007 — One tenant saturates the connection pool

| Field | Value |
|---|---|
| Category | Tenant isolation and scaling |
| Difficulty | Advanced |
| Target roles | Platform; backend; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Tenant isolation and scaling: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-008, QB-SYS-009 |
| Sources | [OVERLOAD: Handling overload](https://sre.google/sre-book/handling-overload/); [PLAN: Using EXPLAIN](https://www.postgresql.org/docs/18/using-explain.html) |

### Prompt

A shared database serves 100 tenants. One tenant launches expensive report queries and saturates the application’s 50-connection pool. Other tenants time out while CPU averages look acceptable. Propose immediate containment and an incremental design; the team has four engineers.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** First identify active queries, waits and per-tenant concurrency using safe operational views and request traces. Apply bounded admission and query timeouts, protect interactive requests with reserved capacity, and pause the offending report workload if authorized. Move reports to a separate worker queue/pool with per-tenant concurrency caps and a global cap. Keep tenant scope in every job and data-access path; queue isolation does not provide authorization.

**Explanation:** For example, allowing two report queries per tenant is insufficient if 100 tenants simultaneously consume 200 connections; enforce both local and aggregate budgets. A pool is a queueing limit, not extra database capacity. Preserve the simple shared deployment initially, measure which workloads dominate, then consider a dedicated reporting store or tenant placement strategy if evidence justifies the operational cost. A replica may tolerate stale reports but does not automatically remove expensive write or freshness constraints.

**Followups:** How do background jobs avoid starving small tenants? What metric triggers dedicated placement? What happens when workers retry timed-out queries?

**Common mistakes:** Scaling API replicas until total connections explode; using tenant identity from an untrusted body; proposing one database per tenant without operating-cost analysis.

</details>

<a id="qb-sys-008"></a>

## QB-SYS-008 — A newly created item is missing immediately after save

| Field | Value |
|---|---|
| Category | Consistency by user journey |
| Difficulty | Intermediate |
| Target roles | Full-stack; backend; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Consistency by user journey: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-009, QB-SYS-010 |
| Sources | [HTTP: HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html); [ISO: Transaction isolation](https://www.postgresql.org/docs/18/transaction-iso.html) |

### Prompt

Writes go to a primary, list reads to a replica that may lag by several seconds. After creating an item, the creator immediately reloads the list and sees nothing. Public viewers tolerate five seconds of staleness; creators require read-your-writes. Design the flow without promising zero replica lag.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Return the created representation and stable ID in the successful write response so the UI can confirm the accepted result. Route the creator’s subsequent reads to the primary for the required consistency window, or use a replication-position token and wait until the chosen replica has applied that position before reading. If a wait exceeds its budget, fall back to primary or explicitly return a pending-freshness state according to the contract. For public lists, serve a replica/cache only when an applied-position or equivalent freshness watermark establishes the five-second bound, including cache age. If the bound cannot be established, use a primary read or return an explicit pending/unavailable state; do not silently serve arbitrarily old data. A replica’s recent activity timestamp alone is not proof it has applied all relevant writes.

**Explanation:** A fixed five-second stickiness timer is only a heuristic unless replica lag is actually bounded. Do not cache a stale empty list above a fresh primary read. Read-your-writes is scoped to the creator/session or explicit token; it does not imply every user globally sees every write immediately. During failover, determine whether an acknowledged write can be lost and what durability guarantee the database deployment actually provides.

**Followups:** How do multiple devices share a freshness token? What happens when a user edits then deletes? How are caches coordinated with the read route?

**Common mistakes:** Claiming eventual consistency guarantees a fixed deadline; treating optimistic UI as durable storage; routing to primary while serving an older shared cache.

</details>

<a id="qb-sys-009"></a>

## QB-SYS-009 — A timeout triggers a storm of repeated calls

| Field | Value |
|---|---|
| Category | Deadlines and retry budgets |
| Difficulty | Intermediate |
| Target roles | Backend; full-stack; FDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Deadlines and retry budgets: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-010, QB-SYS-001 |
| Sources | [RETRY: Timeouts, retries, and backoff with jitter](https://builder.aws.com/content/3EumjoZascWd1oZiEgL8ORlv3qE/timeouts-retries-and-backoff-with-jitter) |

### Prompt

An API has a two-second end-to-end deadline and calls a provider. Client, API and SDK each retry up to three attempts. The provider becomes slow. Explain amplification, choose an owner for retries, and state which operations may be retried safely.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Three attempts at each of three layers can produce up to 27 provider attempts for one logical request, depending on call structure. Pick one intentional retry layer, bound total attempts and elapsed time, and propagate the remaining deadline so queueing and network time count. Use backoff with jitter only when time remains and the failure is plausibly transient. For side-effecting requests, establish durable idempotency or outcome reconciliation before retrying.

**Explanation:** An illustrative budget might reserve time for authentication and response handling, then allow at most two provider attempts inside the remainder; choose actual values from measured latency and product needs. A timed-out provider call may still complete, and canceling a local future does not undo that effect. Limit concurrent in-flight attempts and stop retrying persistent validation failures. Track logical-request success separately from per-attempt errors to expose amplification.

**Followups:** How does a circuit breaker recover? Should 429 respect a long Retry-After beyond the request deadline? How do background jobs differ?

**Common mistakes:** Adding retries at every layer; giving each retry a fresh full deadline; blindly retrying an unprotected charge after timeout.

</details>

<a id="qb-sys-010"></a>

## QB-SYS-010 — A regional partition cannot preserve every promise

| Field | Value |
|---|---|
| Category | Partition policy for globally scarce inventory |
| Difficulty | Expert |
| Target roles | Backend; platform; system design |
| Round | Technical scenario / practical discussion |
| Experience | Extension beyond the core 0–2 year target |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Partition policy for globally scarce inventory: correctness, assumptions, failure reasoning |
| Related IDs | QB-SYS-001, QB-SYS-002 |
| Sources | [ISO: Transaction isolation](https://www.postgresql.org/docs/18/transaction-iso.html); [OUTBOX: Transactional outbox pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) |

### Prompt

Two regions sell one globally unique ticket. Both can reach local users but lose connectivity to each other. The product insists on no double sale and wants both regions to confirm purchases during the partition. Explain the contradiction and propose an explicit business policy.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** For an unrestricted globally unique ticket, both isolated regions cannot independently confirm ownership while guaranteeing a single global sale. Choose a single authoritative region or quorum that can serialize the ticket transition; the side unable to reach that authority must decline or queue unconfirmed intent. An alternative preallocates exclusive ticket ownership before the partition, so each region sells only inventory it already owns. Ownership transfer requires a safe handoff protocol and cannot be improvised during uncertainty.

**Explanation:** In the counterexample both regions read “available” locally, then confirm different buyers. Conflict resolution after reconnection cannot make both earlier confirmations truthful. A pending reservation screen can preserve an available intent path, but must not claim confirmed purchase. State how leases, clock assumptions and fencing prevent an old owner acting after reassignment; a lease name alone is not enforcement. Document recovery, reconciliation and user compensation for ambiguous prior acknowledgements.

**Followups:** Can inventory be partitioned by ticket range? What if the last acknowledged write was not replicated before failover? What UI state represents uncertainty?

**Common mistakes:** Using last-write-wins to claim no double sale; citing CAP without defining the operation; promising confirmed success from both partitioned owners.

</details>

## References and verification

[Phase 5 system source register](PHASE5_SYSTEM_SOURCES.md). [Question-bank navigation](README.md).

Last verified: **2026-11-02**. Primary sources inspected for the stated mechanisms. Scenario reasoning reviewed; this is not a production design, benchmark, cloud deployment or candidate assessment.
