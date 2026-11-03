# Observability in a design interview

## Define the user outcome first

Logs, metrics and traces are tools for explaining behavior. Start by defining success at a user-visible boundary. For notifications, durable API acceptance and timely provider acceptance are distinct outcomes; an HTTP 202 alone does not prove delivery.

An SLI is a measured indicator. An SLO is its target over a stated window. An illustrative availability SLI is successful eligible requests / all eligible requests. Define eligibility, measurement location and treatment of errors/timeouts; otherwise a team can improve the metric merely by excluding failures.

## Choose complementary signals

| Signal | Example | Why it helps |
|---|---|---|
| User outcome | Fraction of accepted notifications reaching provider acceptance within 60 s | Exposes end-to-end delay |
| Request metrics | Rate, error count and latency distribution | Detects changed load and response behavior |
| Resource metrics | CPU, memory, pool wait, lock wait | Narrows capacity hypotheses |
| Async metrics | Oldest outbox/job age, retry rate, permanent failures | Exposes stalled work even at low queue depth |
| Structured logs | Event ID, attempt, state transition, error category | Explains one failed operation |
| Traces | API, database and provider spans | Shows a sampled request's dependency path |

The 60-second target is illustrative. A sampled trace cannot establish the behavior of every request. Missing traces can be a sampling or instrumentation issue rather than proof of no work.

## Error budget example

For a request-based 99.9% success objective over one million eligible requests, the budget is (1−0.999) × 1,000,000 = 1,000 failed requests. This is not automatically “43.2 minutes of downtime”: that time conversion assumes a time-based 30-day objective or additional uniform-traffic assumptions. Keep request and time budgets separate.

Do not average p95s across instances and call the result a global p95. Aggregate compatible observations/histogram buckets or use an appropriate quantile aggregation system. The percentile's definition, window and population matter.

## Debugging a slow service

Observe scope first: all users or one tenant, new version or old, one endpoint or all, cache misses or all requests? Check recent changes and dependency timing. Form a falsifiable hypothesis, then choose a check. Example: “pool wait increased while query execution stayed stable” suggests connection pressure rather than a slower SQL plan. Contain user impact, then investigate cause; do not turn a hypothesis into a root-cause claim prematurely.

Avoid user identifiers as unbounded metric labels. Keep sensitive content and credentials out of logs, restrict access and specify retention. A correlation ID should let operators join telemetry without exposing the whole payload.

## Practice question

**SD-OBS-01 — Intermediate; all engineering roles; design/debugging; 0–2 years.** Queue depth is near zero but customers say messages arrive late. What would you inspect?

<details><summary>Expected reasoning and followups</summary>

Look at outbox publication age, scheduled jobs, end-to-end timestamps, provider acceptance versus final delivery, polling intervals and missing instrumentation. A queue can be empty because work never reached it. Followup: choose an SLI spanning accepted intent through the defined completion boundary. Mistake: treating a healthy component metric as proof of a successful user journey.

</details>

## Checklist

- [ ] Outcome indicators differ from internal component health.
- [ ] Rates, latency, failures, queue age and resource pressure are visible.
- [ ] Percentiles and budgets use consistent populations and units.
- [ ] Telemetry has privacy, cardinality and retention constraints.

## References

- [Google SRE workbook — implementing SLOs](https://sre.google/workbook/implementing-slos/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
