# Admission policy needs identity, scope and atomicity

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

A rate limit controls how much work an identity may admit over time. A concurrency limit controls work currently occupying a resource. A queue limit bounds waiting work. They protect different failure modes and usually need to coexist. Define identity from trusted authentication/tenant context rather than a spoofable request header; an IP is a policy choice with shared-network and proxy implications.

A fixed window is simple but can admit nearly twice its limit around a boundary. A sliding log gives exact rolling counts with per-request state; a sliding counter approximates. A token bucket allows a bounded burst and replenishes credit over time. A leaky-bucket style queue shapes departure timing but creates latency and a capacity/overflow decision. State clock semantics, units and whether denied attempts consume credit. Operation cost must come from trusted policy with a positive finite value; an unchecked negative client cost can increase the bucket instead of limiting work.

## Worked trace

A token bucket has capacity 10 and refill 2 tokens/s, initially full. Five requests at time 0 consume five tokens. At time 1.5, credit is min(10,5+3)=8 before another request. That admitted request leaves seven. Two gateways independently granting a full bucket to the same tenant double the intended burst unless they share an atomic authority or receive bounded, nonoverlapping allocations. Read-then-write on a remote counter races; use an atomic decision/update at the chosen owner. Persisted refill state also needs a clock/recovery contract: monotonic values from incompatible machine/boot epochs cannot be compared after owner migration. See the [shared bucket case](Classic-System-Design/Rate_Limiter.md).

## Alternatives and operating limits

On limiter failure, fail-open preserves traffic but may violate resource budgets; fail-closed preserves the budget but reduces availability. Choose per operation and declare it. Local emergency caps can protect a process while a global quota is unavailable, but are not automatically exact global enforcement. Return an explicit denial, often HTTP429, with appropriate retry guidance; clients still require bounded backoff/jitter. Monitor accepted/denied/queued by scope, storage latency and hot identities without high-cardinality secrets in metric labels.

## Attempt first — P7-RATE-LIMITING

A tenant gets 100 requests/minute on each of four gateways. Is that an exact global 100/minute policy?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Independent counters may admit 400/minute and create boundary bursts. Use a shared atomic decision or bounded leases whose aggregate cannot exceed the intended budget, with stated uncertainty/failure semantics. Followup: distinguish a strict financial budget from approximate abuse throttling.

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [HTTP429](https://www.rfc-editor.org/rfc/rfc6585.html).
- [Overload handling](https://sre.google/sre-book/handling-overload/).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
