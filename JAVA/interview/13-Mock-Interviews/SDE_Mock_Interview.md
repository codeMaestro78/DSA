# SDE mock — rolling activity service

This is original **[INFERRED]**, company-neutral practice for an early-career candidate. It is not a company's interview, policy, hiring bar or timing. All datasets, workloads and product requirements here are fictional teaching fixtures. This candidate packet contains the tasks only; keep the [interviewer companion](SDE_Mock_Interview_Interviewer.md) closed until the attempt ends.

## Metadata

| Field | Value |
|---|---|
| Mock ID | MOCK-SDE-01 |
| Target role | SDE / backend engineer |
| Level | 0–2 years; optional advanced followups |
| Difficulty | Intermediate |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Tools | Java 17-compatible API/source; compiler and local tests; collections allowed |
| Tasks | SDE-T1, SDE-T2, SDE-T3, SDE-T4 |
| Validation | Exact reference Java block extracted and locally tested; distributed extension is reasoning only |

## Before starting

Preflight is outside the 75-minute session: verify the chosen runtime works, prepare a scratch file and timer, and choose a partner or solo mode. For a strict independent attempt, do not use generative assistance or read the companion; this is an exercise rule, not an employer policy. Documentation lookup is allowed if recorded. A partner gives one task at a time and records questions/hints. Solo candidates write assumptions, stop at each checkpoint and open the companion only after the timed session. Never invent real project outcomes.

## Timeline

| Stage | Minutes | Task |
|---|---|---|
| 1 | 10 | SDE-T1 — contract and examples |
| 2 | 30 | SDE-T2 — implementation and checks |
| 3 | 20 | SDE-T3 — service failure extension |
| 4 | 15 | SDE-T4 — project evidence and closing |

## Candidate tasks

### SDE-T1 — Establish the contract

A dashboard shows how many distinct users were active in its recent window. Design a single-threaded in-memory component `RecentUsers(long window)` with `int record(long timestamp, String user)`. Each accepted call adds one event and returns the number of distinct users with an accepted event in **(timestamp − window, timestamp]**.

`window` must be a positive Java long. Timestamps are nonnegative Java longs, nondecreasing across accepted calls; equal timestamps are allowed. Users are nonempty case-sensitive strings; null and empty are invalid. Reject invalid constructor/input values with `IllegalArgumentException`. An invalid record call must not change state or advance time. Strings are immutable; no normalization is requested. Assume at most 1,000,000 accepted calls per object, sufficient memory, and no concurrent calls. A repeated user event is another event, not an event-ID duplicate to suppress.

Restate boundaries and create expected results yourself for window 10 and calls `(0,"A"), (5,"B"), (10,"A"), (15,"C")`. Add an equality-boundary and a repeated-timestamp example. What information must be retained to answer future calls?

### SDE-T2 — Implement and verify

Implement the interface, explain why it is correct, and name total versus per-call time and retained-space bounds. Test invalid calls between valid calls to show that failure does not corrupt state. Include a very large timestamp near `Long.MAX_VALUE`. Do not edit the repository's existing linked-list solutions; use a scratch implementation.

### SDE-T3 — Turn the component into a service

The same product now has three ingest workers; events can arrive late or be delivered twice. A product manager still asks for an exact global count. Explain which assumptions of your component no longer hold. Propose a bounded first service design, including event identity, time semantics, ownership of state, failure recovery and what the API tells users while results are incomplete. No deployment, database SQL or distributed implementation is required.

### SDE-T4 — Explain evidence and finish

Describe a real debugging episode in which a boundary or state transition mattered. If no suitable real episode exists, explicitly use the code you just wrote as a hypothetical debugging exercise. Distinguish what you personally observed from what you infer. End with a two-minute summary of your implementation, its strongest check and one limitation.

## After the attempt

Spend 10–15 minutes outside the session comparing with the [companion](SDE_Mock_Interview_Interviewer.md). Record elapsed time, artifacts, clarifications, hints H1/H2/H3, unresolved errors and the six dimension scores in the [blank scorecard](Mock_Scorecard.md). H0 clarification is not a directional hint. Mark unobserved dimensions **Not assessed**. Pick one specific reattempt; do not infer a hiring decision from the score.

## Prerequisites and sources

[Coding methodology](../03-DSA-and-Coding/Coding_Interview_Methodology.md), [complexity](../03-DSA-and-Coding/Complexity_Analysis.md), [Java contract questions](../12-Interview-Question-Bank/SDE_Questions.md) and [delivery/queues](../04-System-Design/Message_Queues.md). Read prerequisites before the session, not as a substitute for an independent attempt.

[Source scope](PHASE6_CORE_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
