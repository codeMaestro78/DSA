# Full Stack AI practical questions

These ten original **[INFERRED]**, company-neutral exercises are focused practice, not employer questions or encounter-frequency claims. Start with Beginner/Intermediate questions for 0–2 years; Advanced questions are extensions. These are individual exercises; complete role simulations belong to Phase 6. Attempt the prompt before opening its answer. Alternatives that preserve the stated contract can be valid.

Read the linked canonical lesson when a mechanism is unfamiliar. Each “Round” is a suggested practice format, not a company process. Difficulty is an editorial judgment, not a measured pass rate. For design/diagnosis questions, the answer is a reviewed reference approach, not a live integration result.

Last verified: **2026-11-02** — prompt contracts, answer reasoning, local links and question records reviewed. Only QB-AI-001/002 include locally executed code; other scenarios are reasoning exercises. [Source scope](PHASE5_AI_SOURCES.md). [Question-bank directory](README.md).

<a id="qb-fs-001"></a>

## QB-FS-001 — Create one job despite a double click

| Field | Value |
|---|---|
| Category | Asynchronous API lifecycle |
| Difficulty | Beginner |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Asynchronous API lifecycle; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-002 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/API_Design.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A user submits the same report-generation form twice while the first response is delayed. The API creates asynchronous jobs. Specify an application idempotency contract, response shape and frontend behavior. Assume one trusted server-owned database can atomically enforce a unique key; no external report publication occurs.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The client creates one stable idempotency key for the logical submission and reuses it for transport retries. The server scopes the key to the authenticated actor or tenant and operation, stores a canonical request digest, and atomically creates or returns the associated job. A repeated key with the same payload returns the same job identity; a repeated key with a different payload returns an explicit conflict. Define the key retention window so a late retry has predictable behavior. Return a job ID, status and authorized status/stream endpoints, rather than holding an HTTP request open until generation finishes. The UI disables accidental repeat actions for clarity, but correctness comes from the server constraint because two tabs or retrying clients can bypass that UI state. This establishes one local job record; it does not by itself deduplicate every worker attempt or external side effect.

**Explanation:** A database uniqueness rule closes the race between concurrent submissions. Checking whether a key exists and inserting in two unprotected steps does not.

**Followups:** How is an intentional second report distinguished from a retry? What if the job record is committed but the response is lost?

**Common mistakes:** Using a new key for each retry; deduplicating across different tenants globally; calling UI disabling an idempotency guarantee.

</details>

<a id="qb-fs-002"></a>

## QB-FS-002 — Resume a stream without rerunning generation

| Field | Value |
|---|---|
| Category | Streaming event replay |
| Difficulty | Intermediate |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Streaming event replay; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-003 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Real_Time_Applications.md); [WHATWG server-sent events](https://html.spec.whatwg.org/multipage/server-sent-events.html); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A job persists events with monotonically increasing per-job IDs. The browser has applied event 14. It disconnects while events 15 and 16 are committed. On reconnect, its cursor is 14. Describe delivery and rendering, including duplicates and a cursor older than retention. Native EventSource or a fetch-based stream may be chosen; state which behavior is application-owned.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Authorize access to the job, then replay retained events strictly after cursor 14 in order: 15, 16 and later committed events. Attach the live subscription without a gap between the replay snapshot and new events, for example using one ordered durable log cursor. The UI tracks the last applied ID and ignores an already applied duplicate; it must distinguish append deltas from replacement snapshots. Reconnection resumes observation of the same job and must not create another generation request. Native EventSource has reconnection and Last-Event-ID behavior, while a fetch stream needs its own reconnect/cursor handling; neither creates a durable server log. If 14 predates retention, return a defined resync outcome and fetch an authorized current snapshot plus its cursor before continuing. Document how a completed or failed terminal event is recovered, and avoid treating a transport close as successful job completion.

**Explanation:** Event delivery identity differs from generation execution identity. Persisting a result without a cursor-consistent subscription can still lose an event during reconnect.

**Followups:** What if the user reloads before persisting cursor 14? How do you prevent a replay/live subscription race?

**Common mistakes:** Starting a new job on every reconnect; blindly appending duplicate deltas; assuming EventSource supplies storage or authorization.

</details>

<a id="qb-fs-003"></a>

## QB-FS-003 — Authorize a stream by resource ownership

| Field | Value |
|---|---|
| Category | Server resource access |
| Difficulty | Intermediate |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Server resource access; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-004 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Authentication_and_Authorization.md); [OWASP authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Alice is signed in to tenant T1. She changes a URL from /jobs/J1/events to /jobs/J2/events, where J2 belongs to T2. Describe the expected server behavior, how tenant context is derived and a focused dummy-user test. Assume the stream can contain sensitive report text.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Derive Alice’s identity and active membership from trusted authentication state, then authorize the requested job against that context before sending any event. Do not accept a query-string tenant identifier as proof of membership. J2 must be denied without streaming its content, even if Alice has a valid login and J2 is a correctly formatted or hard-to-guess ID. Apply the same ownership rule to status, snapshots, downloads and replay endpoints so the stream cannot be bypassed through another route. Use an indistinguishable not-found/denied response if that matches the product’s resource-disclosure policy. In a local fixture, create two dummy tenants and jobs, confirm Alice can observe J1, and verify J2 returns no event body or report metadata. Also exercise a cursor for another job and a stale membership according to the product’s explicit revocation policy.

**Explanation:** The boundary is Alice reading T2’s report through a legitimate endpoint. Identifier unpredictability reduces guessing but does not replace resource authorization.

**Followups:** How should long-lived streams handle membership removal? What job identifier is safe to include in logs?

**Common mistakes:** Checking only login; hiding links in the frontend; authorizing the initial page while leaving replay/download routes open.

</details>

<a id="qb-fs-004"></a>

## QB-FS-004 — Prevent a late response from overwriting the active chat

| Field | Value |
|---|---|
| Category | Frontend request races |
| Difficulty | Beginner |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Frontend request races; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-005 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Frontend_Engineering.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

The user opens conversation A, starts loading its history, then switches to B. B’s history arrives first; A’s slow response arrives last. Explain frontend state handling so B remains visible. The backend is already returning correct authorized data. No specific JavaScript framework is required.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Associate every history request with its conversation ID and a local request generation. On response, write data to the cache entry for that conversation and update visible state only if the active conversation and relevant request generation still match. Switching to B should synchronously select B’s state rather than leaving an ambiguous global “messages” array that any response can replace. Abort A’s fetch where supported to avoid unnecessary work, but keep the identity check because a response can already be resolved or cancellation may not take effect. Separate loading/error status per conversation and prevent an A error from covering B. Test both arrival orders, rapid A→B→A navigation, retries and component unmount. This is a presentation race, not evidence of a backend permission failure; preserving frontend association complements rather than substitutes for server authorization.

**Explanation:** Cancellation is an optimization; identity-aware state updates establish correctness. A single shared loading flag also loses meaning when requests overlap.

**Followups:** How should a streamed event update an inactive conversation? What key belongs in a query cache?

**Common mistakes:** Relying only on abort; updating one global array from every callback; clearing B’s state when A fails.

</details>

<a id="qb-fs-005"></a>

## QB-FS-005 — Make terminal job transitions atomic

| Field | Value |
|---|---|
| Category | Cancellation races |
| Difficulty | Advanced |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Cancellation races; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-006 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Backend_Engineering.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A worker is about to mark a report succeeded at the same time the user requests cancellation. Jobs have queued, running, succeeded, failed and cancelled states. Define a deterministic terminal transition contract. Cancellation is cooperative and no irreversible external action is involved.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Choose and publish a linearization rule, such as the first successful database compare-and-set from a nonterminal state wins. Cancellation records the requested intent and attempts the permitted transition; completion atomically commits output and succeeds only if the job is still in the expected running state. If cancellation won, discard or quarantine late output and do not publish a succeeded event. If completion won, return the already succeeded status and explain that cancellation was too late. Terminal states must not transition into one another merely because callbacks arrive later. Persist terminal state and its event consistently, for example through a transactional outbox, then allow repeated delivery without changing the outcome. The UI should display the authoritative returned state, not assume a click means cancellation succeeded. Test both interleavings, duplicate cancellation, worker retry and a crash around the terminal-state commit.

**Explanation:** “Cancel requested” and “cancelled” are different facts. The contract makes the outcome reviewable even when the timing is nondeterministic.

**Followups:** What changes when a tool has already sent an email? Which outputs need retention after cancellation?

**Common mistakes:** Setting cancelled only in browser state; allowing a late worker to overwrite a terminal status; promising immediate remote preemption.

</details>

<a id="qb-fs-006"></a>

## QB-FS-006 — Do not stream unvalidated content as a final result

| Field | Value |
|---|---|
| Category | UI result lifecycle |
| Difficulty | Intermediate |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | UI result lifecycle; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-007 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/AI_Integration.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A generated financial summary streams text before a deterministic reconciliation check. The final check fails. Design event types and UI behavior so provisional content is not mistaken for an accepted report. This is a product-design exercise, not financial advice.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use explicit provisional content events followed by a validation outcome and a terminal job state. Label the visible draft while generation is in progress, and keep export/share actions unavailable until the server records successful reconciliation and acceptance. A failed check should replace the acceptance status with a clear failure, preserve an appropriate diagnostic reference, and avoid leaving a success indicator beside the streamed draft. Decide whether draft text remains available for debugging or is hidden; either choice needs a stated retention and user-experience policy. Do not “undo” an already sent email by changing the screen, so external publication must wait for acceptance or have a separate compensating workflow. Test success, validation failure, disconnect before terminal status and reload after failure. A client should recover the server’s final validation state rather than infer acceptance from receiving the last token.

**Explanation:** Transport completion, generation completion and business acceptance are distinct transitions. Naming them prevents one visual event from silently implying all three.

**Followups:** Could validation be performed incrementally? Which failure explanation is useful without exposing private intermediate data?

**Common mistakes:** Treating a closed stream as success; enabling export after the first token; removing a warning while leaving a stale accepted badge.

</details>

<a id="qb-fs-007"></a>

## QB-FS-007 — Queue fairly when one tenant floods the system

| Field | Value |
|---|---|
| Category | Backpressure and fairness |
| Difficulty | Advanced |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Backpressure and fairness; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-008 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Multi_Tenant_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A worker pool runs ten concurrent generation jobs. Tenant A submits 1,000 long jobs, while B submits one short job. Describe admission control and scheduling that keep B usable. Do not invent a throughput benchmark; job durations are not supplied.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Apply bounded per-tenant admission and concurrency limits in addition to a global capacity limit. Queue accepted work durably and return an explicit queued state; reject or defer work beyond the tenant’s allowance with a documented retry policy. Use fair scheduling across tenant queues, optionally weighted by purchased capacity, so A cannot occupy every future slot indefinitely. Decide whether a separate interactive class is necessary and cap it too, or all work will be labeled urgent. Track queue age, running count, rejection rate and completion latency by tenant and class without exposing another tenant’s payload. A global FIFO queue alone lets A place B behind hundreds of jobs; a concurrency cap without bounded queue length still permits unbounded storage and waiting. Test small synthetic schedules with long and short jobs, worker loss and cancellation. Actual capacity planning requires measured service-time distributions and provider constraints.

**Explanation:** Admission controls bound accepted demand, while scheduling chooses who runs next. Both are needed to control queue growth and tenant starvation.

**Followups:** How would you avoid starvation under weighted scheduling? Which cost estimate can help admission without treating it as exact?

**Common mistakes:** Adding workers without controlling demand; promising B’s latency without job durations; applying only a global rate limit.

</details>

<a id="qb-fs-008"></a>

## QB-FS-008 — Rotate a secret without putting it in the browser

| Field | Value |
|---|---|
| Category | Backend provider integration |
| Difficulty | Intermediate |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Backend provider integration; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-009 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/AI_Product_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A web app currently invokes a generation provider through its own backend. The provider credential must be rotated while some jobs are running. Describe ownership, rollout and observability. Do not use or request any real secret.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Keep the provider credential in server-side secret storage and inject it only into the backend or worker that needs it. Introduce the new credential, verify it through an approved bounded health check or staging workflow, and roll workers so new calls use the new version. Define whether already-running requests finish with the old credential and how long overlap is allowed. After confirming adoption and expected error rates, revoke the old credential and remove stale configuration. Record credential version identifiers and success/error counts, never raw keys, authorization headers or secret-bearing URLs. The browser should authenticate to the application and receive only application-scoped results; it should not need the provider secret to stream an answer. Handle authentication failures as configuration incidents rather than blindly retrying them. The exact provider’s rotation and revocation behavior needs verification before executing a real rollout.

**Explanation:** The question tests a rollout contract and secret ownership. No current provider capability or live rotation success is assumed.

**Followups:** What if old-key calls fail immediately on revocation? How would you locate a worker still using the previous version?

**Common mistakes:** Bundling the provider key into frontend code; logging credentials for debugging; assuming key revocation cancels running requests.

</details>

<a id="qb-fs-009"></a>

## QB-FS-009 — Separate output artifacts from chat messages

| Field | Value |
|---|---|
| Category | Data modeling and provenance |
| Difficulty | Intermediate |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Data modeling and provenance; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-010 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Database_Design.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A conversation generates a report, then regenerates it after a source document changes. Users need to download either version and know which source snapshot produced it. Propose entities, keys and an ownership invariant without writing a database migration.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Model conversation, generation job, artifact version and source snapshot as separate records with explicit relationships. Give each artifact an immutable identity, creation status, checksum or content reference, generating job, pipeline/model configuration and source-version manifest. A conversation may point to a current artifact while retaining older versions under policy; regenerating should create a new artifact rather than overwrite the old file behind the same unversioned label. Derive ownership through trusted job/conversation relationships and enforce it when accessing metadata and bytes. Record failed or provisional artifacts separately from accepted downloads. Define deletion behavior so removing a conversation does not leave an anonymously accessible orphaned object. Test two successful versions, an interrupted generation, a source update during generation and an unauthorized artifact URL. Provenance supports reproducibility and explanation, but a checksum alone cannot prove the report’s factual correctness.

**Explanation:** Messages capture interaction; artifacts capture versioned outputs. Conflating them makes downloads, retention and source lineage difficult to enforce.

**Followups:** How would signed download URLs expire? What if the artifact’s source permissions change later?

**Common mistakes:** Overwriting one object key without version metadata; relying on a filename for ownership; marking partial uploads as accepted.

</details>

<a id="qb-fs-010"></a>

## QB-FS-010 — Roll back one failing prompt version

| Field | Value |
|---|---|
| Category | Controlled product rollout |
| Difficulty | Advanced |
| Target roles | Full Stack AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Controlled product rollout; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FS-001 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/AI_Integration.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Prompt P2 is rolled out to 10% of tenants; P1 remains the baseline. A reviewer finds that P2 omits mandatory caveats for long documents. Describe immediate containment, how to identify affected outputs and a clean follow-up comparison. No actual deployment is requested.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Disable new P2 assignments through a versioned routing control and route affected traffic to the reviewed baseline while investigating. Tag every job and accepted artifact with prompt, model, retrieval and source versions so P2 outputs can be identified, including jobs queued before rollback. Decide whether those queued/running jobs should finish, restart or enter review based on consequence and cost; do not assume changing a feature flag rewrites already captured configuration. Review potentially affected long-document outputs and communicate corrections through the product’s established process. Build a long-document slice that includes caveat-required and caveat-not-required cases, hold out final cases and compare P1/P2 or the fix under controlled configurations. Preserve assignment information to distinguish a prompt effect from tenant composition or concurrent model changes. A 10% rollout limits exposure but does not establish safety or statistical representativeness.

**Explanation:** Reproducible version capture turns rollback from a visual toggle into an actionable containment process. Cohort selection and paired evaluation answer different questions.

**Followups:** Which alert could detect omission without logging full documents? What should a user see if a previously downloaded report is corrected?

**Common mistakes:** Removing version metadata after rollback; comparing different source snapshots; treating canary traffic as a complete evaluation.

</details>
