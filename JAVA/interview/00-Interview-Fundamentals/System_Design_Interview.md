# System design interview

## The task

Design practice evaluates how you turn uncertain needs into a coherent system. This chapter is an [INFERRED] learning framework, not a hidden employer rubric. There can be several good designs if they satisfy the stated constraints and explain their tradeoffs.

## Begin with the user journey

For a notification service, identify who creates a notification, who receives it, which channels exist and how success is observed. Then ask about delivery guarantees, latency, traffic shape, retention and sensitive content. “Highly scalable” is incomplete until workload and targets are specified.

A useful small starting design is an authenticated API, relational source of truth, durable work record and delivery worker. Do not add global sharding because the example might become large someday. Add a component when a requirement or measured bottleneck justifies it.

## Walk through one operation

Create request → authorize → validate → persist business intent → make work discoverable → execute external delivery → record outcome. Decide which steps share a transaction and which cannot. A crash after a provider accepts delivery but before acknowledgment creates uncertainty. Retrying can duplicate the external effect unless the provider supports a suitable idempotency/deduplication contract.

The database and external provider do not become one atomic transaction merely because they appear in one diagram. Explain recovery, not just the happy path.

## What a good diagram communicates

Label component responsibilities, network/API boundaries, data ownership, synchronous calls and asynchronous work. Place security checks in the flow. Pair the diagram with a sequence describing failure, retry, cancellation or duplicate delivery where relevant.

## Practice flow

For a 45-minute practice budget, reserve roughly 5 minutes for requirements, 5 for workload/contracts, 15 for architecture and critical flows, 15 for one deep dive and 5 for tradeoffs/summary. Change the budget if the interviewer steers. The employer's invitation determines actual duration.

## Practice questions

Why is “use a queue” not a complete reliability answer? When should you discuss caching?

<details><summary>Expected reasoning</summary>

A queue does not by itself prove the business intent and message were committed together, prevent duplicate effects or define recovery. Caching belongs after the source of truth and access pattern are known; explain invalidation, staleness, failure and capacity rather than treating it as free performance.

</details>

## Checklist

- [ ] Requirements are explicit assumptions or confirmed constraints.
- [ ] One invariant has a concrete enforcement mechanism.
- [ ] One partial failure and recovery path is explained.
- [ ] Tradeoffs follow from requirements.

## References

- [System-design framework](../04-System-Design/System_Design_Interview_Framework.md).
- [Notification exercise](../04-System-Design/Classic-System-Design/Notification_System.md).
- [SRE: implementing SLOs](https://sre.google/workbook/implementing-slos/) — reliability measurement.

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
