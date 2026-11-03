# API contracts for interactive AI products

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Separate create/run, status, event subscription and cancellation when the work outlives one connection. Use stable request/job IDs and tenant-scoped idempotency for creation when duplicate submission matters. Define terminal states and error codes; a timeout can leave the server outcome unknown.

Native browser EventSource is a server-to-client event mechanism; its constructor is not a general arbitrary-method request API with custom header options. If the product needs a POST body or a bearer header, choose a suitable fetch stream or an authenticated job-create followed by a subscription design. Cookie-based subscriptions need deliberate origin/session/CSRF treatment for state-changing operations.

## Worked role scenario

A create call commits a job but its response is lost. Retrying with the same logical idempotency key returns the same job. Reconnecting the event subscription must not create another generation. Cancel is a separate authorized operation and may report “too late” after an irreversible step.

## Interview practice: FSAI-P3-05

- **Category / difficulty:** API contracts for interactive AI products / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you design reconnect without duplicate generation?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Separate the durable job from the subscription, persist ordered events if replay is promised, and resume by a scoped cursor. Recheck authorization on reconnect and deduplicate event IDs client-side as needed.

**Followup:** What does an expired event cursor return?

**Common mistake:** Treating a new stream connection as permission to rerun the job.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Define job and transport states separately.
- [ ] Specify replay/retention and cursor expiry.
- [ ] Handle duplicate creation and cancellation races.

## Canonical lessons and references

- [Core API design](../../05-Database-and-Backend/API_Design.md).
- [Realtime flow](Real_Time_Applications.md).
- [WHATWG: server-sent events](https://html.spec.whatwg.org/multipage/server-sent-events.html).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
