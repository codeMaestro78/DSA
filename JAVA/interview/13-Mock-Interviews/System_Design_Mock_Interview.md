# System design mock — shared equipment bookings

## Candidate packet

This original **[INFERRED]**, company-neutral simulation practices a complete design conversation for an early-career engineer. It is not an employer interview loop. Use the questions in order; diagrams and prose are sufficient. Do not implement a service or access a real database.

| Field | Value |
|---|---|
| Mock ID | MOCK-SYS-01 |
| Target role | SDE; backend; system design |
| Level | 0–2 years; core with optional stretch |
| Difficulty | Intermediate |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED] |
| Tools | Editor or paper; calculator; no live services; agree reference/AI rules before starting |
| Tasks | SYS-T1, SYS-T2, SYS-T3, SYS-T4 |
| Validation | Scenario reasoning; only supplied capacity arithmetic has local fixture checks |

## Preflight and timing

Agree whether this is a closed-reference assessment or guided practice; record assistance. Preflight and the 10–15-minute debrief are outside the 75-minute live session. The interviewer asks one task at a time. If practicing alone, set a timer and write assumptions before moving on. Factual clarification is available without a hint penalty.

| Stage | Minutes | Task |
|---|---|---|
| 1 | 15 | SYS-T1 — requirements and workload |
| 2 | 25 | SYS-T2 — contracts, ownership and booking flow |
| 3 | 20 | SYS-T3 — races, retries and notification recovery |
| 4 | 15 | SYS-T4 — launch evidence and tradeoffs |

## Scenario and stipulated facts

A small team needs a web service for staff to reserve shared equipment within their organization. Each equipment item belongs to exactly one tenant organization. Users may book one to four consecutive half-hour slots on one UTC date. Slot IDs are integers 0–47, with slot 0 covering [00:00,00:30). A booking either acquires all requested slots or none. Adjacent slots do not overlap. Temporary holds, recurring reservations, payments and cross-date bookings are out of scope.

A user may see equipment availability, create a confirmed booking, read their booking, and cancel their own booking. A tenant administrator may cancel bookings within that tenant. Browser requests can be retried after a timeout. Tenant data must remain isolated. The UI needs an explicit accepted, conflict, pending or failed result; it must not infer durable acceptance from an optimistic screen.

All workload numbers below are fictional inputs, not measurements. There are 20,000 successful bookings on a typical day. At the modeled peak, successful booking creation generates one notification job per booking. That notification is informational: delivery failure must not undo a confirmed booking. Read traffic, database capacity, retention period and acceptable notification delay need clarification. Do not infer them from the write count.

<!-- phase6-fixture: SYS-capacity -->
```json
{"bookings_per_day":20000,"seconds_per_day":86400,"peak_bookings_per_second":40,"slots_per_booking":3,"peak_seconds":300,"notification_service_per_second":20,"after_peak_arrivals_per_second":5}
```

## Live questions

### SYS-T1 — Define the user promise

Clarify the important unknowns and restate the booking invariant. Estimate average booking requests per second, peak slot-claim insert rate, notification backlog after the peak, and its drain time after arrivals return to the supplied baseline. Explain which calculations can influence a design and which capacity claims they cannot establish.

### SYS-T2 — Design one complete booking journey

Propose the smallest credible architecture, main entities, ownership and API contracts. Trace create → durable response → subsequent read and cancellation. Explain how authorization enters the flow. Availability search may be stale; state how acceptance differs from a displayed free slot. Include a request identity contract for retries and the response when a key is reused for a different request.

### SYS-T3 — Handle conflicting and uncertain work

Two users race to book slot 20 on the same equipment/date. One request also includes slot 21. A database write commits, the response is lost, and the notification worker later crashes after sending an email. Walk through these failures separately, including cancellation racing with retry. Show how you prevent duplicate bookings without promising duplicate-free external email delivery.

### SYS-T4 — Make a bounded launch decision

Recommend checks and monitoring for authorization, concurrency, retry, notification lag and recovery. Explain one simpler alternative and one reason to postpone a more complex architecture. End with a two-minute summary: decision, evidence still needed, a risk and the next verification step. Extra multi-region discussion is optional after the core flow is sound.

## Submission and debrief

Keep the request flow, API/data contracts, worked arithmetic, failure traces and uncertainty list. Record time, factual clarifications, hints, and evidence using the [shared rubric](Interview_Evaluation_Rubric.md). No score or story is prefilled. Prepare to explain an alternative that still satisfies the contract.

**Spoiler boundary:** the [interviewer companion](System_Design_Mock_Interview_Interviewer.md) contains clarification answers, hints, solutions and scoring anchors. Open it only after attempting, or give it to your interviewer.

## Prerequisites and provenance

[Design framework](../04-System-Design/System_Design_Interview_Framework.md), [capacity estimation](../04-System-Design/Capacity_Estimation.md), [API design](../04-System-Design/API_Design.md), [transactions](../05-Database-and-Backend/README.md). Optional transfer practice: [SYS-005](../12-Interview-Question-Bank/System_Design_Questions.md#qb-sys-005).

[Source scope](PHASE6_DESIGN_SOURCES.md). Last reviewed: **2026-11-03**. No database, queue, authorization service or email provider was exercised.
