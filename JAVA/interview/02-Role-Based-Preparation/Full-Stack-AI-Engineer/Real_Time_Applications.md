# Streaming, cancellation and replay

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

A stream carries incremental events; it does not define durable execution by itself. Assign an event identity/order and distinguish progress, content delta, validation result and terminal outcome. Persist events before publishing if the contract promises recovery/replay. A token rendered in a browser is not necessarily a completed accepted answer.

Browser EventSource supports reconnect behavior and Last-Event-ID, but replay correctness requires server retention, authorization and ordering semantics. A fetch stream has different lifecycle control. Define backpressure and bounded buffers. If the client falls behind, a resync/status path can be safer than unbounded in-memory buffering.

## Worked role scenario

Events 1–10 were delivered, the connection drops, and event 11 was persisted but not seen. Reconnecting from event 10 can replay event 11 onward. Replaying an event must not repeat its external tool effect; event delivery and action execution have different identities and owners.

## Interview practice: FSAI-P3-08

- **Category / difficulty:** Streaming, cancellation and replay / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What does stopping the client stream actually cancel?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** It stops that transport subscription. Remote work stops only under an implemented cancellation protocol; queued/running/irreversible steps need defined behavior and acknowledgment.

**Followup:** How do you recover if event 10 is older than retention?

**Common mistake:** Equating AbortController or EventSource.close with a durable job cancellation guarantee.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Define terminal states and event ordering.
- [ ] Handle reconnect, duplicate events and cursor expiry.
- [ ] Test cancel-versus-complete races.

## Canonical lessons and references

- [API lifecycle](API_Design.md).
- [Backend async ownership](Backend_Engineering.md).
- [WHATWG: server-sent events](https://html.spec.whatwg.org/multipage/server-sent-events.html).
- [FastAPI: concurrency and async](https://fastapi.tiangolo.com/async/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
