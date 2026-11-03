# System design attempt

For a hypothetical practice design, label all workload numbers as assumptions. Do not imply the architecture is a named company's internal implementation.

## Prompt and scope

- Date / ID / role / level / time budget:
- Actors and critical user journey:
- Must / should / out of scope:
- Functional requirements:
- Quality targets: latency percentile/window, throughput, availability, durability, freshness:
- Unknowns and assumptions:

## Contracts and data

- Public API, validation, authorization and error behavior:
- Entities, authoritative owner and access patterns:
- Business invariant and concrete enforcement mechanism:
- Retention, deletion and sensitivity:

## Design and flow

- Simplest viable architecture and a diagram:
- Normal read/write flow:
- Transaction/async/external boundaries:
- Partial failure, unknown outcome, retries and deduplication:
- Backpressure, degraded behavior and recovery:

## Sizing and decision

- Rates/storage/concurrency with units and arithmetic:
- Peak/locality assumptions and sensitivity:
- Actual bottleneck and justification for each added component:
- Alternative considered and tradeoff accepted:
- Security/isolation, observability, restore/rollback and cost:

## Review

- Evidence, untested assumptions and missing work:
- Hints used / dimension scores / next exercise:

## References

[Design framework](../04-System-Design/System_Design_Interview_Framework.md), [practice rubrics](../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md).

## Last verified

2026-11-02: reusable editorial template; no candidate attempt has been prefilled.
