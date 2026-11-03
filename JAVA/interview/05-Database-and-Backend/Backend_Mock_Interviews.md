# Backend mock routes and a reservation variation

Begin with [the SDE mock](../13-Mock-Interviews/SDE_Mock_Interview.md) for implementation and [the full system-design mock](../13-Mock-Interviews/System_Design_Mock_Interview.md) for API/transaction reasoning. These have separate interviewer packets. [The database bank](../12-Interview-Question-Bank/Database_Questions.md) supplies smaller prerequisite exercises. Use [the common rubric](../13-Mock-Interviews/Interview_Evaluation_Rubric.md), not an invented employer pass mark.

## New35-minute transfer variation — candidate

Original **[INFERRED]**, company-neutral; Intermediate, target 0–2 years. Preflight and 10-minute debrief are outside the 35 minutes. Design or pseudocode only; SQL execution is not required or claimed.

A room has capacity 10. A reservation for a positive seat count must either atomically reserve all seats or return conflict. Two concurrent requests can arrive, browser responses can be lost, and a user may cancel their own reservation. The server owns tenant/room membership. A repeat create operation must resolve to the same reservation identity rather than reserve again. Clarify request identity, retention and current-state versus historical-response behavior.

| Stage | Minutes | Deliverable |
|---|---|---|
|1|7|Contract, authority, data invariants and material clarifications|
|2|13|Create/read/cancel API and authoritative transaction boundaries|
|3|10|Concurrent allocation, lost response and cancel/replay traces|
|4|5|Boundary tests, unresolved tradeoff and summary|

At available=3, two different requests each ask for2. Trace a correct outcome. Then cancel the winner and retry its original create identity. What does the API return and what must it not do?

<details><summary>Interviewer reference and scoring — attempt first</summary>

**H0:** A relational source of truth with transactions/unique keys is permitted. Requests have a stable tenant/principal-scoped create identity and normalized seat count/room fingerprint. Keep identity records for a declared sufficient retry interval; no universal24-hour requirement is implied. Cancellation is permitted only by current rights, returns the current cancelled state and releases capacity once.

**Worked reasoning:** In one transaction claim/resolve operation identity, conditionally decrement the room's authoritative available count only when available≥requested seats, create reservation and persist result. Enforce nonnegative bounded capacity and positive reservation quantities through the actual database/protocol; a prior read alone is insufficient. At available 3, one decrement by 2 succeeds, the other cannot also commit against the same remaining capacity 1. Serialization/deadlock conflicts require an appropriate bounded whole-transaction retry, not just retrying the last statement after half the work.

After response loss, the same identity/fingerprint resolves to the existing reservation. A changed fingerprint conflicts. Cancellation conditionally transitions that owned reservation once and increments capacity in the same transaction; a repeated cancel cannot release twice. Replaying the old create after cancel resolves to the original reservation identity with an explicitly documented current-state response or historical result plus current status. It must not create a new active reservation or silently resurrect the old one. Recheck authority before disclosing replayed records.

**Alternative:** Lock the room row then check/update in one transaction, with consistent lock order for multi-room extensions. A cache or application mutex alone does not establish cross-process enforcement. Per-seat slot claims can fit fixed-seat models but change storage/access cost. No implementation is forced if another mechanism preserves the invariant.

**Hints:** H1 ask where both contenders decide. H2 propose a guarded update or locked owner row. H3 trace available 3→1 and ask what the second transaction observes/commits. If stuck, scope to one room and no cancellation first. If finished, add timeout after cancellation commit or request-identity expiry.

**Checks:** Positive/zero/over-capacity quantities, two contenders, same-key duplicate/conflicting payload, response loss, repeated cancel, cancelled-create replay, cross-tenant IDs and rollback after a downstream local failure. These are reasoned schedules, not newly executed native SQL checks.

**Score evidence:** CONTRACT=capacity/identity/cancel promise; REASONING=enforcement and alternative; CORRECTNESS=one success and no double release; VALIDATION=normal/failing schedules with honest execution limits; RISK=current authority and unknown outcome; COMMUNICATION=clear state trace/tradeoff. Apply 0–4/Not assessed consistently;4 needs repeated independent evidence. A known oversell or repeated capacity release prevents level 3 correctness. Strong evidence includes the exact cancellation/replay state; common mistake is treating a lost HTTP response as a rolled-back transaction.

</details>

[Transaction foundations](Transactions_and_ACID.md) · [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
