# Interviewer guide — shared equipment bookings

## Restricted preparation

**Contains spoilers.** Use [the candidate packet](System_Design_Mock_Interview.md) for MOCK-SYS-01. This 75-minute original [INFERRED] simulation assesses early-career reasoning, not knowledge of an employer's architecture. All four tasks are live-session work; preflight and a 10–15-minute debrief are additional. Read the [common rubric](Interview_Evaluation_Rubric.md) before scoring.

Ask one useful question at a time. H0 is a factual clarification, not a direction hint. Offer H1 → H2 → H3 only as needed, record the wording/time and assess the resulting independence; do not automatically subtract points for every hint. Do not force the reference architecture when another approach preserves the contract. Do not silently add multi-region or payment requirements.

## Fixed clarification answers — H0

- One region initially, one small development team and a managed relational database are acceptable assumptions; the exact deployment vendor is not assessed.
- Read-your-writes is required for a successful booking/cancellation response. Availability browse can be up to five seconds old, but final allocation must be authoritative. A freshness promise needs an actual gate or fallback; an unchecked timer is not a proof.
- Stipulated practice targets: p95 booking API latency ≤500 ms under the stated peak, excluding asynchronous email; notification backlog should clear within ten minutes after this one modeled burst. These are requirements to verify, not observed performance. Database benchmark and read-traffic data are unavailable.
- Keys are scoped to authenticated tenant/principal and create operation. Keep request/result records at least 24 hours; a client must not retry an old operation under a new key without reconciliation. Longer-term booking identity remains in the booking record. Discuss retention policy explicitly before deployment.
- Slot acceptance is defined at transaction commit. Cancelled bookings retain history, while their active slot claims are released atomically. Subsequent cancellation is idempotent for an already-cancelled booking the caller may access.
- An email provider offers no demonstrated duplicate-suppression contract in this exercise. Do not award an exactly-once email claim.

## Timed script and reference reasoning

### SYS-T1 — Define the user promise

**0–15 minutes.** Ask: “What does a confirmed booking promise?” At minute 8, ask for the workload calculations; at minute 13, ask which result changes the design.

**Expected solution:** One active booking may own a tenant/equipment/date/slot key; all requested slots must be allocated together. Average creation rate is 20,000/86,400 ≈0.2315/s. Peak slot-claim inserts are 40×3=120/s before booking/outbox/index overhead. Notifications arrive at 40/s and are served at 20/s for 300 s, creating (40−20)×300=6,000 jobs from an initially empty queue. With arrival 5/s after the burst and service 20/s, net drain is 15/s, so 6,000/15=400 s, or 6 minutes 40 seconds. This fits the stipulated ten-minute *modeled* recovery target. It does not establish a real worker's rate, retry overhead or correlated outage tolerance.

**Hints:** H1 “Separate arrivals from service.” H2 “How many jobs remain after one second?” H3 supply `(arrival−service)×duration`, then ask them to derive drain time. **Stuck branch:** reduce to a ten-second burst and trace a queue. **Finished branch:** make baseline arrivals 20/s; there is no finite drain with unchanged capacity. Strong candidates distinguish average load, peak and finite-burst assumptions rather than sizing from the daily mean.

### SYS-T2 — Design one complete booking journey

**15–40 minutes.** Ask for an API, a data invariant and the critical transaction before requesting a component diagram. At minute 30, ask who authorizes a returned resource; at minute 38, ask for cancellation.

**Expected solution:** A modular application, relational source of truth and a background notification dispatcher suffice initially. The API authenticates a principal, checks tenant membership/resource rights and validates equipment/date/consecutive slots. `POST /bookings` accepts an idempotency key; `GET /bookings/{id}` checks current resource rights; cancellation is a state transition, for example `POST /bookings/{id}/cancel`. Exact URI/status alternatives are acceptable if semantics are explicit.

Store `booking(id, tenant_id, owner_id, equipment_id, date, state, version)`, active `slot_claim(tenant_id, equipment_id, date, slot, booking_id)`, an idempotency record with unique scoped key/fingerprint/result, and an outbox event. Required key columns are NOT NULL. Enforce one active claim per tenant/equipment/date/slot using a database unique/primary key; ensure booking/equipment/claim references cannot cross tenants. Slot range/consecutiveness is validated before the transaction; the all-or-none write is transactional. The historical booking survives cancellation; slot claims represent current allocations only.

Reserve the scoped idempotency identity, write the booking and all slot claims, persist its result and append the notification event in **one local transaction**. A conflicting claim aborts the allocation; there is no partial booking. For simultaneous uses of one key, the database uniqueness/transaction protocol serializes ownership or yields an explicit in-progress response. A matching key/fingerprint returns the same booking identity; a mismatched fingerprint is a conflict, never a second booking. Normalize date/equipment/ordered slots and include relevant caller scope in the fingerprint. Recheck current authority before disclosing a replayed result.

Return the committed booking and ID. Use the primary or a replica-position/freshness mechanism for required immediate reads; never let a stale cache erase acceptance. For cancel, lock/version-check the owned booking, transition it once and remove its claims/append an event in the same transaction. An already-cancelled booking stays cancelled. A create replay does not resurrect it: stable creation identity and current booking state are separate facts.

**Alternative:** Precreate every equipment-slot row and lock requested rows in a consistent order before allocating. This is valid if row existence, atomicity and ownership are explicit; a plain application `SELECT` for absent claims is insufficient. Distributed locks are not necessary for this initial invariant. **Hints:** H1 “Where is allocation decided?” H2 “What stops both transactions inserting a missing claim?” H3 name a unique slot key and ask for the transaction boundary. **Stuck branch:** design a single-slot booking first. **Finished branch:** discuss a repeated cancel racing with a new booking; cancellation deletes only claims owned by its booking, never every claim for that slot.

### SYS-T3 — Handle conflicting and uncertain work

**40–60 minutes.** Give the three failures one at a time. Ask the candidate to identify known, unknown and committed state before proposing a retry.

**Expected solution:** Transactions A and B both see slot 20 free; A inserts claim 20, B's conflicting write cannot also commit under the unique constraint. If B requested 20 and 21, rollback removes any tentative claim 21 as part of its failed transaction. Ordering claim inserts consistently reduces deadlock opportunities; deadlock/serialization errors still need bounded whole-transaction retry when safe. Availability reads alone do not protect the invariant.

A successful commit with lost HTTP response leaves the caller uncertain, not the database undone. Retry the same scoped identity and validated fingerprint; resolve to the same booking. After cancellation, replay must not reallocate the original slots. An outbox event makes “booking committed but dispatch record missing” impossible within that transaction, but a dispatcher may send twice after a crash. Stable event IDs, dispatch state and provider deduplication where actually supported reduce duplicates. Without provider support, retain at-least-once attempts with possible duplicate informational email, or accept a documented at-most-once/loss tradeoff. Neither email choice creates a second booking. Old creation notifications should be checked against current booking state or clearly describe historical events to avoid misleading recipients after cancellation.

**Hints:** H1 “A timeout tells you about which system?” H2 “Separate database acceptance from email effect.” H3 draw commit → send → worker crash → redelivery. **Stuck branch:** ask only whether an email failure should release equipment; expected answer is no. **Finished branch:** dispatcher crashes with unknown provider outcome; ask which evidence would permit a safe retry rather than asserting a universal provider guarantee.

### SYS-T4 — Make a bounded launch decision

**60–75 minutes.** Ask for a launch recommendation and one unknown that could reverse it. Reserve the final two minutes for a concise handoff.

**Expected solution:** Before claiming launch readiness, run simultaneous overlapping/nonoverlapping requests against the actual database, multi-slot rollback, same-key duplicate/conflicting body, lost-response replay, cancel/new-booking race, cross-tenant authorization and stale-cache cases. Inject worker crashes before/after send and measure retry/lag behavior. A tested backup-restore plan needs demonstrated restoration/reconciliation, not merely an enabled backup checkbox.

Monitor booking outcomes/latency, constraint conflicts/deadlocks, database saturation, oldest outbox age, dispatch failures, tenant-level resource use and audit decisions without unnecessary private payloads. Bound queues and retry work; name an operator and stop condition for lag or unexpected duplicate allocation. Test the stipulated p95 with a representative read/write mix; arithmetic alone cannot establish capacity. Defer microservices/sharding/multi-region until evidence warrants their coordination cost. A relational monolith is an acceptable decision; the reference is not a component-count contest.

**Hints:** H1 “Which failure would most damage trust?” H2 ask for one test that produces two contenders for a slot. H3 offer a concurrency timeline and ask what invariant to assert. **Stuck branch:** request one correctness test, one authorization test and one recovery test. **Finished branch:** ask how deleting a tenant affects booking history, idempotency/audit records and retention policy without assuming a universal legal rule.

## Task-specific score evidence

Apply the shared 0–4 scale separately; **Not assessed** for unobserved dimensions. Level 4 needs repeated strong performance across variants/multiple attempts, not one fluent session. These are level-3 anchors, not pass thresholds.

| Dimension | Observable level-3 evidence | Critical blocker / remediation |
|---|---|---|
| CONTRACT | States atomic slots, tenant boundaries, cancellation/read promises and exclusions | Partial acceptance or conflated email promise; rework SYS-T1 |
| REASONING | Derives workload units and chooses mechanisms from requirements | Tool catalog without decisions; redraw one transaction in SYS-T2 |
| CORRECTNESS | Unique authoritative allocation plus idempotent identity, rollback and owned release | Read-then-write race, replay resurrection or cross-tenant deletion; trace SYS-T2/3 |
| VALIDATION | Proposes conflicting interleavings and failure injection; labels arithmetic limits | Claims design prose proves capacity; specify executable checks in SYS-T4 |
| RISK | Treats uncertain effects, current authority and retry/lag limits explicitly | Claims exactly-once email without evidence; revisit SYS-T3 |
| COMMUNICATION | Separates fact/assumption/unknown and gives a concise decision with tradeoff | Hides unresolved correctness behind confident wording; repeat final handoff |

**Common mistakes:** applying TTL to a uniqueness predicate as if time advances index membership; accepting a second identity after timeout; deleting claims without booking ownership; making cache the allocator; averaging away a known safety defect. **Strong indicators:** concrete interleaving, simplest sufficient ownership boundary, current authorization on replay, and explicit missing evidence.

## Debrief and sources

Use 10–15 minutes after the session: candidate self-scores first; reviewer cites exact behavior/hints; compare one failed trace; choose one prerequisite and one unfamiliar reattempt. Record observations rather than a hiring verdict. No candidate outcome is prefilled.

[Primary source register](PHASE6_DESIGN_SOURCES.md): HTTP semantics, database constraints/locking, transactional outbox and authorization. [Capacity fixture checks](../validation/mocks-design/README.md) execute only arithmetic extracted from the candidate fixture; no database, queue or concurrency schedule is executed. Reference flow reviewed **2026-11-03**.
