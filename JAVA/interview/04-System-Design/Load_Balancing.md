# Load balancing

## Purpose and placement

A load balancer distributes requests or connections across backends. It can improve utilization and survive an individual backend failure when there is spare capacity. It does not fix a shared database bottleneck, corrupted data or every instance running a bad release.

Layer-4 balancing commonly routes transport connections; layer-7 balancing can use HTTP properties such as host/path. Details depend on the implementation. State where TLS terminates and how trusted identity and forwarded-client metadata reach the application.

## Choosing a distribution rule

| Rule | Useful intuition | Limitation |
|---|---|---|
| Round robin | Similar requests and backend capacity | Equal request counts can hide unequal request cost |
| Least active requests/connections | Variable duration | Connection count is not necessarily work, especially with multiplexing |
| Weighted distribution | Unequal instance sizes or canary release | Stale weights can overload a changed backend |
| Key-based hashing | Affinity and cache locality | Hot keys skew load; membership changes need care |

Do not assume an algorithm's name is identical across products. Confirm health checks, timeout behavior and retry ownership for the chosen implementation.

## Stateless request handling

If any healthy API instance should handle the next request, persist durable state outside local process memory and make authentication/session validation portable. Sticky sessions can reduce migration work but create imbalance and failover problems. “Stateless API” does not mean the application has no state; it means a specific request is not dependent on an unavailable instance's private state.

Health checks should distinguish a live process from one ready to serve. A readiness check that fails whenever a shared database is slow may remove every instance at once. Choose checks and degraded behavior deliberately. Drain connections before termination; account for long-lived streams so a rollout does not cut them without a reconnect contract.

## Capacity after failure

If a safe measured capacity is 4,000 requests/s per instance, four instances give 16,000/s before other bottlenecks. Losing one leaves 12,000/s. Routing cannot create the missing capacity. A design serving 15,000/s before failure needs admission control or additional headroom. Queue length and request deadlines must be bounded during overload.

Autoscaling has observation, scheduling and warm-up delays. Also inspect connection pools, external quotas and hot partitions. Adding instances can exhaust the database connection budget faster.

## Practice question

**SD-LB-01 — Intermediate; backend/platform; design; 0–2 years.** A service doubles API replicas but latency increases. Give two plausible hypotheses and checks.

<details><summary>Expected reasoning and common mistake</summary>

One hypothesis is connection-pool multiplication exhausting the shared database; check active/waiting connections and query/lock latency. Another is cold caches or a downstream rate limit; compare miss rate, dependency errors and traffic before/after. Confirm routing distribution and workload shape. Followup: how would you contain overload? Bound concurrency, shed eligible work and roll back if the change caused it. Mistake: adding more replicas without locating the bottleneck.

</details>

## Checklist

- [ ] The failure domain and remaining capacity are explicit.
- [ ] Health checks and draining behavior are defined.
- [ ] Retry behavior is coordinated with the application deadline.
- [ ] State affinity and long-lived connections are considered.

## References

- [Google SRE — handling overload](https://sre.google/sre-book/handling-overload/)
- [AWS Builders Library — timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
