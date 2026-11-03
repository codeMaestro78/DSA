# Startup engineer mock — paid data exports

## Candidate packet

This original **[INFERRED]**, company-neutral simulation combines scoped delivery, backend correctness, incident response and stakeholder communication. It is not a claim about startup hiring stages or a real customer/product. Use hypothetical decisions explicitly; never invent a past accomplishment.

| Field | Value |
|---|---|
| Mock ID | MOCK-START-01 |
| Target role | Startup engineer; full-stack/backend engineer |
| Level | 0–2 years; supported ownership |
| Difficulty | Intermediate |
| Duration minutes | 60 |
| Company scope | Company-neutral; original [INFERRED] |
| Tools | Editor/paper and calculator; no live payment, database or customer actions; agree reference/AI rules before starting |
| Tasks | START-T1, START-T2, START-T3, START-T4 |
| Validation | Design/incident reasoning; only supplied size arithmetic has local fixture checks |

## Preflight and timing

Preflight and a 10–15-minute debrief are outside the 60 minutes. Agree tool/reference policy, then run the four tasks in order. The interviewer supplies factual clarifications as H0. Record direction hints separately; asking an important clarification is not a failure.

| Stage | Minutes | Task |
|---|---|---|
| 1 | 10 | START-T1 — scope a useful first release |
| 2 | 20 | START-T2 — define export and payment state |
| 3 | 20 | START-T3 — contain and investigate an incident |
| 4 | 10 | START-T4 — negotiate a changed priority |

## Scenario and stipulated facts

A three-person engineering team has an existing authenticated web application, relational database, background worker and a payment-provider adapter. The founder asks for paid CSV exports of a user's own authorized records: “Show something useful in two days.” You have support from a senior reviewer; no solo production authority is implied. The fixed exercise price is 1,000 minor currency units, with the currency supplied by server configuration. A client cannot choose a cheaper amount. This is a software contract, not financial advice.

Exports are capped at 100,000 rows. They must not include another tenant's data; downloading a completed artifact requires current authorization and the declared successful-payment condition. A failed or uncertain response does not establish whether an external payment occurred. A browser may disconnect and retry. You may narrow the first release and state unknown effort instead of promising a deadline without evidence.

<!-- phase6-fixture: START-size -->
```json
{"maximum_rows":100000,"assumed_bytes_per_row":200,"simultaneous_exports":10}
```

The byte size is a stipulated raw serialized estimate, not an observed object-memory size, compression ratio or throughput. CSV escaping/header overhead is excluded from this small arithmetic fixture; a real maximum needs measured/validated encoded-size limits.

For the incident task, use this **fictional authoritative provider-reconciliation fixture**, not an inference from HTTP logs:

| Time | Event |
|---|---|
| 10:00 | Local export operation E42 creates charge attempt with merchant reference K42 |
| 10:01 | Request using K42 times out at the application |
| 10:02 | Worker retries with new reference K99; provider returns charge C2 successful |
| 10:05 | Authoritative provider reconciliation reports C1/K42 successful and C2/K99 successful for the same intended purchase |

No real refund or charge is requested in this exercise.

## Live questions

### START-T1 — Bound the first useful release

Clarify customer outcome, acceptance and responsibilities. Propose an initial scope, explicit exclusions, missing dependencies and a review/stop point. Estimate raw bytes per maximum-size export and for ten simultaneous fully materialized exports. Explain why those numbers do not establish actual memory use or a two-day delivery guarantee.

### START-T2 — Trace one purchase and export

Specify request identity, server-owned price/currency, durable states and the download authorization rule. Trace successful work, a lost response, a disconnect and one export failure. Explain the boundary between local database changes and an external provider effect, and which evidence you would need before safely enabling retries. No specific payment provider's API behavior may be assumed.

### START-T3 — Respond to the duplicate-charge report

Use the incident fixture to identify what is known and what remains unknown. Propose containment, an evidence-preserving investigation, customer communication and a controlled reconciliation/correction plan. Explain why a new reference after timeout is dangerous and what contract could prevent this class of failure. State tests and restart gates without claiming they were run.

### START-T4 — Negotiate a changed priority

The founder now asks for AI-generated summaries in the same two-day release while the incident is being investigated. Give a two-minute recommendation that explains user impact, sequencing, a bounded alternative and what needs the decision owner's agreement. If you use a personal example, keep it truthful and attributable; otherwise respond as a hypothetical engineer in this scenario.

## Submission and debrief

Keep a scope note, lifecycle/identity sketch, size calculations, incident action list and stakeholder update. Use the [shared rubric](Interview_Evaluation_Rubric.md) after attempting; no hiring conclusion follows from the score.

**Spoiler boundary:** the [interviewer companion](Startup_Mock_Interview_Interviewer.md) supplies facts, hints and reference reasoning. Give it to the interviewer or open after the live attempt.

## Prerequisites and provenance

[Startup conversations](../10-Behavioral-and-Communication/Founder_and_Startup_Interviews.md), [API design](../04-System-Design/API_Design.md), [fault tolerance](../04-System-Design/Fault_Tolerance.md), [ownership](../10-Behavioral-and-Communication/Ownership.md). Optional transfer: [uncertain invoice effects](../12-Interview-Question-Bank/SDE_Questions.md#qb-sde-008).

[Source scope](PHASE6_DESIGN_SOURCES.md). Last reviewed: **2026-11-03**. No payment API, refund, deployment, load test or customer incident was exercised.
