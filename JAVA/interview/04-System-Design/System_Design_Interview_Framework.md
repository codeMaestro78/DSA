# System design interview framework

## A repeatable reasoning method

A design interview is a discussion under incomplete information. Make uncertainty visible instead of silently treating guesses as requirements. The following is an [INFERRED] practice method; use the actual prompt and recruiter's format over a memorized script.

## 1. Clarify the critical job

Ask who the actors are, what they must accomplish, and which operation matters most. For a notification service: an application submits an event, a user receives a channel notification, an operator investigates failures. Ask whether delivery is best-effort or must be auditable. Exclude marketing campaign editing if it does not affect the critical delivery design.

## 2. Establish a contract and invariants

Write the public API and important data before choosing infrastructure. Example: submitting the same `(tenant, idempotency_key)` with the same payload returns the same notification identifier; reusing the key with a different payload is rejected. That requires storage enforcement under concurrent inserts, not a check-then-insert in application memory.

Name the source of truth: the notification database owns accepted intent and state; the queue transports work; the provider owns whether its API accepted a send. These are different facts.

## 3. Estimate only decision-changing quantities

If asked to assume 1 million notifications/day, average arrivals are about 11.6/second. A burst factor of 20 yields about 232/second; label that factor an assumption. A small average does not prove an external provider can handle the burst. Derive fanout and retry demand before choosing worker capacity. See [capacity](Capacity_Estimation.md).

## 4. Propose the simplest credible baseline

An API, transactional store, outbox relay and bounded workers may be sufficient. Every additional component must solve a named constraint. Explain the normal request from authentication through durable acceptance, dispatch and status lookup. Use [diagrams](Architecture_Diagrams.md) to show ownership and protocols.

## 5. Spend depth on a critical risk

Choose the risk suggested by the requirements: duplicate side effects, hot keys, stale permissions, a database invariant or provider outage. Show a concrete failure interleaving. Example: the provider accepted the message, but the worker crashed before recording success. A retry may send twice unless the provider supports a reusable idempotency key. Do not claim a queue provides exactly-once external delivery.

## 6. Close with tradeoffs and evidence needed

State the recommended baseline, its main limitation, what was deliberately deferred, and what measurement would trigger change. For instance: “Start with one relational source of truth and workers; measure queue age and provider throttling before adding a streaming platform.” Distinguish a load-test result from an estimate.

## Practice question

**SD-FRAME-01 — Intermediate; SDE/backend; design round; 0–2 years.** A design already has six microservices but only one developer. What should you ask before proposing a rewrite?

<details><summary>Expected reasoning, followup and common mistake</summary>

Identify the actual pain: deployment risk, latency, ownership, development overhead or consistency. Map critical flows and shared invariants. Compare incremental simplification with leaving stable services alone. A rewrite is not justified by service count. Followup: how would you migrate one flow without losing accepted work? Mistake: treating a favorite architecture as the requirement.

</details>

## Readiness checklist

- [ ] Requirements and estimates are labeled separately.
- [ ] Each datastore has an owner and access pattern.
- [ ] The design includes an explicit failure and recovery flow.
- [ ] The conclusion names one accepted tradeoff.

## References

- [AWS Builders Library — timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)
- [Google SRE workbook — implementing SLOs](https://sre.google/workbook/implementing-slos/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
