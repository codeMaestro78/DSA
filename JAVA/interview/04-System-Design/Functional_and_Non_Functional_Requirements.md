# Functional and non-functional requirements

## Definitions

A functional requirement says what the system does: create a short link, redirect a code, revoke a link. A quality requirement says how well or under what conditions: latency, availability, freshness, durability, isolation, operability or cost. The labels are less important than making the requirement testable.

An internal service-level objective (SLO) is a target for an indicator over a window. It is different from a contractual service-level agreement (SLA), which can have business consequences. A product requirement can motivate either; do not invent an SLA from a latency preference.

## Turn vague language into a contract

| Vague request | Clarification | Illustrative measurable form |
|---|---|---|
| “Fast” | Which operation, percentile and measurement boundary? | p95 redirect latency below 150 ms at the API boundary under the stated load |
| “Always available” | Which eligible requests and time window? | 99.9% of valid redirect requests succeed over 30 days |
| “Immediately deleted” | Which copies and maximum delay? | Revoked links stop redirecting within 5 seconds; backup retention is separately documented |
| “Secure” | Who may access/change which object? | An authenticated creator can modify only links in their tenant |
| “Scalable” | Which demand and failure scenario? | Handle the illustrative peak after losing one API instance |

These values are exercise assumptions, not industry defaults or observed service promises. A percentile alone is incomplete: define whether timed-out and failed requests are excluded, and track failures separately.

## Prioritize and expose conflict

Use must/should/could/out-of-scope. A redirect service might require creation, redirect and revocation; analytics can be eventual and initially minimal. If strict five-second revocation is a must, a one-hour cache TTL without invalidation cannot satisfy it. Either change the cache policy or renegotiate the freshness requirement. Do not say both are met without showing the mechanism.

Record workload shape: read/write mix, request size, bursts, geographic distribution, popular-key skew and retention. Two services with the same requests/day can need very different designs if one has a single global hot key.

## A requirement record

For each important requirement, record the actor, operation, priority, condition, metric, target or unknown, owner and verification method. Example: “Creator revokes a malicious link; must; caches healthy and degraded; maximum redirect staleness; provisional ≤5 seconds; link service; repeated redirect test after revocation.” If invalidation fails, identify whether stale redirects are still allowed or the service must bypass/fail closed.

## Interview exercise

**SD-REQ-01 — Beginner; all engineering roles; design; 0–2 years.** A customer asks for a “real-time, reliable notification platform.” Ask five questions that change the architecture.

<details><summary>Expected reasoning and followups</summary>

Ask about channels, permitted delay, durable acceptance versus actual delivery, duplicate tolerance, and peak fanout/provider limits. Also clarify user consent and retention. “Real-time” might mean an API response, provider acceptance or a user's device receipt. Followup: which quality target is measured at which boundary? Mistake: choosing WebSockets before knowing whether the channel is email.

</details>

## Checklist

- [ ] Actors and critical operations are explicit.
- [ ] Quality goals have scope, conditions and metrics.
- [ ] Assumptions are distinguished from requirements.
- [ ] At least one conflict or unknown is recorded.
- [ ] Verification can determine whether the requirement was met.

## References

- [Google SRE workbook — implementing SLOs](https://sre.google/workbook/implementing-slos/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
