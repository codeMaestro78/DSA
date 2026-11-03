# Fault tolerance

## Start from partial failure

A distributed call can succeed remotely while its response is lost. From the caller's perspective the outcome is unknown. This is why retries need business semantics, not just another HTTP attempt.

A fault-tolerant design limits damage, detects the problem, recovers within its objectives and avoids worsening the failure. Redundant instances help some crashes; they do not protect against every shared dependency, bad release, credential failure or data corruption.

## Deadlines, retries and overload

A timeout limits a wait; an end-to-end deadline limits the total operation budget. If the caller has 500 ms left, giving every downstream stage a fresh 500 ms can exceed that budget. Propagate remaining time and cancel eligible abandoned work. Asynchronous accepted work needs a separate lifecycle rather than pretending the original client connection still owns it.

Retry only eligible transient failures, within an attempt/time budget, with backoff and jitter. Preserve idempotency keys for side effects. If five nested layers each make up to three attempts, the deepest dependency can see up to 3⁵ = 243 attempts for one initial call. Choose a retry owner and cap amplification.

A circuit breaker can temporarily stop calls to a failing dependency and test recovery. It does not repair the dependency; an unsafe fallback can be worse than a clear failure. For an authorization service, stale “allow” decisions may be unacceptable. Bound concurrency and queues so overload is rejected predictably instead of consuming every resource.

## Failure table for a notification service

| Scenario | Impact | Detection | Containment/recovery |
|---|---|---|---|
| API crashes before commit | No durable acceptance | Client timeout, absent status/key | Retry same key according to contract |
| API crashes after commit | Client may not know acceptance | Existing key/status lookup | Return existing intent on replay |
| Relay is unavailable | Accepted work waits | Outbox oldest age | Restore relay and drain at bounded rate |
| Provider throttles | Queue delay and retries | Provider codes, job age | Per-provider limit, backoff, explicit expiry policy |
| Bad release affects workers | Widespread failures | Error-rate change correlated with version | Stop rollout, roll back compatible binary, preserve jobs |
| Database lost/corrupted | Accepted state at risk | Integrity/availability checks | Restore tested backup and logs within stated RPO/RTO |

## Recovery is a procedure

A backup is evidence only that a copy was created, not that restore works. Specify restore steps, dependency order, validation, expected duration and who executes them. Schema changes can make binary rollback unsafe: use expand/migrate/contract when necessary and define the compatibility window. Replaying queued work may require versioned event schemas.

## Practice question

**SD-FAULT-01 — Intermediate; backend/platform; design; 0–2 years.** Why can automatic retries turn a small provider slowdown into an outage?

<details><summary>Expected answer and followups</summary>

Slow calls occupy connections/threads longer. Retried calls add demand while capacity is already strained, and synchronized retries produce bursts. Use bounded deadlines, concurrency limits, backoff/jitter, admission control and a retry owner. Followup: when should you return a pending status instead of retrying synchronously? When durable asynchronous acceptance meets the product contract. Mistake: retrying validation or permission failures as though they were transient.

</details>

## Checklist

- [ ] A timeout is treated as an unknown side-effect outcome.
- [ ] Retries, resource budgets and fallbacks are bounded.
- [ ] Correlated failures and schema-compatible rollback are covered.
- [ ] Restore has an owner, procedure and validation plan.

## References

- [AWS Builders Library — timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)
- [Google SRE — handling overload](https://sre.google/sre-book/handling-overload/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
