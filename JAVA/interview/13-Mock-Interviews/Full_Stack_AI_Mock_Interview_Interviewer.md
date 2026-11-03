# Draft-report workspace recovery: interviewer packet

**Spoilers — keep this file closed during the candidate attempt.** Companion to [the candidate packet](Full_Stack_AI_Mock_Interview.md), **MOCK-FS-01**. All scenario requirements are original [INFERRED]; this is role practice, not a real employer loop. Do not turn this reference approach into the only acceptable design.

## Interviewer instructions

Read the candidate contract first. Complete preflight outside the clock and start only when the agreed environment works. Run the four live stages in order for **75 minutes**. Ask one focused question, then allow reasoning time. Use neutral prompts such as “What assumption makes that safe?” rather than prematurely naming a technique. Give H0 factual answers freely; log H1/H2/H3 directional hints with time, wording and their effect. Offer the next hint only when the candidate requests help or is stalled after a neutral check. Preserve partial work rather than extending the clock to force completion. Do not penalize agreed accommodations or score speaking speed as technical correctness. Reserve 10–15 minutes after the clock for debrief.

## Clarification desk — H0

The job belongs to one tenant/user resource scope. The operation key is an application contract, not a magical property of POST. A semantic request fingerprint excludes irrelevant transport metadata but includes intended source/version/options. Duplicate worker delivery is possible. The authoritative durable record decides terminal state; the browser never does. Draft content remains retained but marked failed in this scenario; real retention/access policy needs a separate decision. A content delta has a unique per-job sequence and is immutable. Old events may be pruned, but the current snapshot includes its watermark and enough state/text to rebuild the screen. No strict latency or cancellation deadline is supplied. Eight slots are service capacity, not evidence of provider quota or a successful deployment.

## Timed script and reference solution

### FS-T1 — Frame the user contract

**Checkpoint:** minutes 0–10.

**Expected solution:** A small backend can own a relational job store, durable work queue/outbox, worker and object/draft storage, with a client reading status/events. A single deployable application plus worker is adequate until measurements justify more separation. State examples: queued → running → validating → succeeded, with failed/cancelled as terminal alternatives; a cancellation request can be a separate flag until its effect is known. Export/share is permitted only from succeeded state under current resource authority, not from a received final token. All draft events remain provisional. Make terminal transitions conditional so concurrent workers cannot overwrite a completed result arbitrarily.

**Hints:** H1 ask which stored fact enables export. H2 ask if token completion is validation success. H3 name the need for a server-owned terminal state.

**Branches:** If stuck, draw one successful job before adding events. If finished, ask which additional requirement would justify separate services, rather than rewarding a larger architecture by default.

### FS-T2 — Specify and trace recovery

**Checkpoint:** minutes 10–35.

**Expected solution:** One coherent sketch is `POST /report-jobs` with a trusted session, operation key and request fingerprint; `GET /report-jobs/{id}` for an authorized snapshot; `GET /report-jobs/{id}/events` for an authorized event stream; and a server-checked export endpoint. In one durable transaction, creation claims the scoped operation key and stores the job plus outbox/work record. A repeated same key/fingerprint returns the same job identity/status; a conflicting payload returns an explicit conflict. A relay may redeliver work, so workers conditionally claim/update by job and attempt identity. An outbox solves the database/notification dual-write gap within its transaction scope; it does not make all external work exactly once.

For reconnect, the last applied sequence is 3. Ignore replayed 3; apply 4 once, then 5 and terminal failed6. The provisional text is `Revenue rose 8%.`, status failed, export/share disabled. Seeing the text is not successful validation. If client detects a gap, malformed/conflicting sequence or retention reset, obtain an authorized authoritative snapshot rather than appending blindly.

Avoid a snapshot/subscription race: snapshot at watermark w must contain state/text through w; subsequent replay/subscription must return every retained event >w, including events committed during subscription setup. This can use a durable event log with cursor catch-up, or an established subscription plus buffering while the snapshot is fetched. Do not simply fetch a snapshot and then subscribe to only future events. When retention has pruned the cursor, explicitly reset to a coherent snapshot+watermark and resume after it. Authorization is checked for status, events, reconnect and export, not just creation.

If Server-Sent Events are selected, the standard's Last-Event-ID enables reporting the last received ID on reconnection. The application must still define IDs, retained replay, tenant authority and client application semantics. A transport receipt cursor may differ from the last **applied** application event; a robust client must recover using its actual state/cursor contract. A polling snapshot protocol is also valid and simpler when token streaming is not required.

**Hints:** H1 replay the received sequences on paper. H2 ask how a repeated delta changes text. H3 supply snapshot watermark plus durable catch-up as the missing race closure.

**Branches:** If stuck, accept a correct polling fallback and score unimplemented streaming separately. If finished, ask about reconnect after permission revocation or an event with sequence4 and a different payload. No real HTTP handler/browser execution is required.

### FS-T3 — Handle capacity and failure

**Checkpoint:** minutes 35–60.

**Worked solution:** Arrival rate is 200/3600=1/18 jobs/s. With mean active time 12s, average offered generation concurrency is (1/18)×12=2/3 slots under stable flow and the stated definition of active time. Nominal utilization is (2/3)/8=1/12≈8.33%. This arithmetic does not bound tail latency or account for queue delays, validation cost, correlated slow jobs or a provider limit. At most eight jobs can occupy the stipulated generation slots simultaneously; the eight-job burst alone fits if all slots are free, but no completion deadline follows from a mean.

Bound admission by global/provider/tenant capacity and queue limits; reject/defer excess work with a clear client state instead of spawning unlimited calls. A crash after persisting draft data may leave an uncertain attempt. Check durable attempt/job state and reconcile rather than blindly creating a new accepted report. Conditional terminal transitions or fencing prevent an old worker from replacing a newer accepted terminal result. Duplicate computation may still consume provider cost even when result acceptance is deduplicated.

Cancellation first records intent and prevents unauthorized publication; request cooperative worker/provider cancellation and reconcile uncertain running work. Do not release an externally occupied slot solely because the client disconnected if that would oversubscribe the real resource. Failed6 must remain authoritative on reload; a worker must not convert it to success based only on having emitted all tokens. Any retry/revalidation is a new explicit version/attempt with compatible state rules.

**Hints:** H1 convert hourly arrivals to per-second before multiplying. H2 ask whether a mean predicts the burst's longest request. H3 identify durable job/attempt ownership as distinct from the browser connection.

**Branches:** If stuck, work the units aloud without substituting a benchmark. If finished, ask about versioned validation rules across retries, or a provider response whose cost is unknown after a timeout.

### FS-T4 — Review the product behavior

**Checkpoint:** minutes 60–75.

**Expected solution:** A queued/running/validating status is distinct from the text preview. Failed validation leaves the draft clearly labeled failed and export/share unavailable; a reload derives that state from the server. A disconnection banner means connectivity is uncertain, not that the job stopped. If authority is revoked, suppress protected text/actions under policy rather than continuing from a private cache. Make progress/status accessible with text, sensible focus order and a restrained status announcement; do not announce every token or rely on color alone. Accessibility choices need an actual UI review before being called verified.

A focused verification plan includes same-key retry after lost response, conflicting payload, duplicate work/completion, crash at state/event boundary, replayed3 and missing4, pruned cursor reset, validation failure/reload, revocation before export and cancellation with unknown provider state. Distinguish a unit test of a reducer from integration proof of database/queue/browser ordering. A concise handoff states unresolved retention, tail latency, provider accounting and stronger revocation semantics.

**Strong indicators:** Candidate traces actual sequence values, closes the snapshot/subscription race, keeps terminal authority on the server and states arithmetic assumptions. **Common mistakes:** treating the last token as success; assuming EventSource itself stores/replays application history; counting client disconnect as remote cancellation; quoting utilization as a measured service SLO.

**Hints:** H1 ask what the user sees after failed6 and reload. H2 ask which statuses are connectivity versus job states. H3 name the server terminal state as the export gate.

**Branches:** If stuck, ask for a plain-language error message and one next action. If finished, ask for keyboard/screen-reader verification steps or a test proving that an accepted snapshot cannot race with later events. These remain proposed tests unless actually executed.

## Evaluation evidence

Use the six shared dimensions in [the rubric](Interview_Evaluation_Rubric.md). The table gives **task-specific level-3 evidence**, not automatic points for a keyword. Apply 0–2 to the actual help/errors and reserve 4 for repeated demonstrated performance across variations or attempts. Mark unobserved dimensions **Not assessed**. Record a known critical defect separately; averaging cannot erase it.

| Dimension | Observable level-3 evidence | Critical blocker / reason to withhold 3 |
|---|---|---|
| CONTRACT | Defines provisional versus accepted output and authoritative job ownership | Enables export from received draft text or UI state alone |
| REASONING | Connects scoped idempotency, work delivery and snapshot/cursor recovery | Invokes a queue/SSE as automatic exactly-once recovery |
| CORRECTNESS | Derives failed6 screen state, deduplicates3 and computes2/3 concurrency | Appends replayed deltas twice, loses terminal state or mishandles units |
| VALIDATION | Lists targeted race/crash/revocation checks with honest execution scope | Calls a diagram or arithmetic check end-to-end proof |
| RISK | Keeps resource authority current and distinguishes cancellation intent/effect | Leaks cross-tenant status/text or assumes provider work stops on disconnect |
| COMMUNICATION | Explains visible status/accessibility and scoped limitations coherently | Makes unsupported latency/cost claims or omits a known failure path |

## Debrief and next attempt

Ask the candidate to summarize one decision, one check and one unresolved risk before providing feedback. Save task completion, H0/H1–H3, exact evidence, dimension scores and what was not assessed. For replay errors, hand-simulate a snapshot at sequence4 followed by live5/6 with a delayed duplicate4. For lifecycle gaps, trace one crash between durable write and notification and identify the owner that repairs it. Do not write a hiring decision or invent a candidate result. Any example of strong behavior above is an editorial anchor, not a prefilled attempt.

## Validation, sources and limits

[The local suite](../validation/mocks-ai/README.md) checks the stipulated capacity arithmetic and a synthetic replay fold only. No API server, database transaction, queue, browser, EventSource connection, authentication flow, cancellation API or export integration was executed. Proposed state/replay designs remain reviewed reasoning. [WHATWG SSE](https://html.spec.whatwg.org/multipage/server-sent-events.html), [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html) and [AWS outbox guidance](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) support the mechanisms, not a tested deployment.

[Source register](PHASE6_AI_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
