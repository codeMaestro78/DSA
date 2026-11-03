# Shared token-bucket admission: hypothetical design case

## Attempt-first scope

Design a multi-gateway tenant token bucket with explicit failure behavior. Billing-grade financial settlement and arbitrary global instantaneous availability are excluded.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume a tenant bucket capacity 20, refill5 tokens/s and cost1/request. From a full bucket,20 simultaneous requests are admissible; after 2 idle seconds, credit grows by 10 up to the capacity. These are deterministic policy inputs.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

A decision API receives trusted scope and logical request identity if retry deduplication is required. Derive the operation cost from trusted policy, or validate it against that policy: require a positive finite cost in the bucket’s token unit rather than accepting arbitrary client-supplied credit. A zero/negative cost can bypass enforcement or mint credit; a cost above bucket capacity cannot become admissible merely by waiting. It returns allowed/denied, remaining credit under its contract and a retry hint; callers must not treat the hint as a reservation.

One atomic authority owns each bucket's credit and last refill time, or distributes nonoverlapping bounded credit allocations. The key includes tenant/policy version. Use a monotonic elapsed-time source at the owner; never trust client timestamps to mint tokens. A process/machine monotonic reading is not a portable persisted timestamp: after restart, reboot or owner migration, its reference can differ. Define a compatible authority clock/epoch and recovery protocol, or conservatively restore credit without granting an unjustified refill. Do not subtract a new owner's clock from an old owner's saved reading. [Clock reference semantics](https://docs.python.org/3.12/library/time.html#time.monotonic) illustrate why only compatible elapsed differences are meaningful.

## Critical flow

At decision time, atomically compute min(capacity,old_credit+elapsed×rate), compare cost and deduct only on admission. Store decision/refill state together. Gate actual protected work so a later layer cannot bypass the decision; independently cap active work and queue depth.

## Failure trace

Two gateways both read20 and write19 after accepting two requests. That lost update admits more work than the stored credit reflects. An atomic read/compute/write or transaction closes this race. Authority failure requires declared fail-open/closed/degraded behavior; independent emergency buckets weaken exactness.

## Tradeoffs, security and operation

Exact shared limits add remote latency/hot-key pressure. Bounded local leases reduce round trips but need conservative expiry/ownership accounting; unused credits may reduce utilization. A hash ring distributes keys, not one hot bucket's exact decisions without coordination.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** A denied request is retried three times. Should each retry cost a token?

**Expected reasoning:** That is a policy choice to specify. Under the stated admission-only deduction, denials do not consume credit; retry traffic still needs abuse/concurrency protection. Followup: how does response loss affect an admitted repeated logical request?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP429](https://www.rfc-editor.org/rfc/rfc6585.html).
- [Overload guidance](https://sre.google/sre-book/handling-overload/).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
