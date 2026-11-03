# Failure and learning

## Pick a real failure

A failure story needs an outcome below an expectation, a decision or omission you can discuss, and a correction. “I cared too much” avoids the question. Do not manufacture a dramatic production outage if your experience is a local project.

## Make causality precise

Separate observation, contributing conditions and your action. For example, a missing timeout may contribute to stuck requests, but a single log line does not prove the root cause. In a real story, state what evidence established and what remained a hypothesis. Identify the part you could influence without claiming control over every dependency.

## Structure the answer

[INFERRED] Use context → expected outcome → what happened → your contribution to the failure → containment/correction → verified learning. Explain how you communicated the problem. If the result remained imperfect, state that rather than forcing a happy ending.

## A hypothetical rehearsal

A demo depended on a third-party model service. Requests stalled because there was no application timeout or fallback. A sound hypothetical response would contain the issue, add a bounded timeout/cancellation policy, define degraded behavior and test provider-failure cases. A genuine learning claim needs later evidence that the practice changed. Merely promising to “test more” is too vague.

Use this as a scenario if you lack a real example; never present it as your history.

## Followups

Why did the review miss it? Which test would have caught it? What was the user impact? What changed in the development process? Which part of the fix did you implement? Answer within the evidence you possess; do not invent incident duration or affected-user counts.

## Common mistakes

Blaming a teammate, listing every mistake without explaining one, focusing only on recovery heroics, minimizing consequences or implying one new test guarantees no recurrence. A useful lesson names a concrete process or design change and its remaining limitations.

## Checklist

- [ ] Failure and personal responsibility are clear.
- [ ] Cause is supported or explicitly uncertain.
- [ ] Corrective action and later behavior are specific.
- [ ] Outcome metrics and context are truthful.

## References

- [Microsoft interview tips](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — STAR(R) and role-specific guidance.
- [Research methodology](../RESEARCH_METHODOLOGY.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
