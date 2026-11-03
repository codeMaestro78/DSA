# Scaling a project by locating its actual limit

Use this chapter to explain a real project with defensible evidence. Examples are original **[INFERRED]** hypotheticals, not claims about your implementation, deployment or accomplishments.

## Concept and worked example

Scaling means preserving required behavior as workload or data grows. First identify the unit: requests, concurrent jobs, bytes, hot-key writes, tenants, retained history or model tokens. Daily averages can hide a brief peak that dominates queues and contention.

Assume a hypothetical worker processes 20 jobs/s. Arrivals are 40/s for 300 seconds, then 5/s. From an empty queue the burst builds 6,000 jobs; after it the net drain is 15/s, so drain time is 400 seconds. This deterministic fixture ignores retries and variable service time; it does not prove a real queue clears on schedule.

## Build your explanation

Trace the critical path, name its invariant/owner and estimate only quantities that change a decision. Check workload skew and dependencies. A replica can help eligible reads but not automatically serialize writes or satisfy immediate freshness. Partitioning adds routing, rebalancing and cross-partition operation costs. Load shedding should produce an explicit user-visible state, not silently lose accepted work.

## Tradeoffs and evidence limits

Compare vertical scaling, query/index improvements, bounded batching/caching and ownership-based partitioning before assuming a new architecture. Explain redundancy, recovery and restore separately from throughput. Add a revisit trigger and a representative validation plan; a diagram with many boxes is not proof of capacity.

## Practice — PROJECT-P7-05

A single tenant is hot, and all its updates must preserve one serial invariant. Will adding random shards necessarily solve it?

<details><summary>Expected reasoning and followups — attempt first</summary>

No. Random distribution can make the invariant require coordination or scatter work while the serial authority remains the limit. Identify whether the operation can be redesigned, partitioned by an independent subentity, batched or admitted under a bounded rate. Preserve the correctness contract and explain any accepted weaker consistency. Measure contention/queueing before claiming a scaling win.

Followups: show an actual artifact if discussing your project; identify your contribution and one unresolved limit. Change one workload/failure assumption and explain what evidence would change the recommendation.

</details>

## Continue and verify

[Canonical lesson or worked case](../04-System-Design/Capacity_Estimation.md), [project explanation framework](Project_Explanation_Framework.md), [project question bank](Project_Question_Bank.md), [blank mock scorecard](../13-Mock-Interviews/Mock_Scorecard.md). These chapters supply their scoped primary sources. This page adds original explanation/practice; its hypothetical calculation or trace is reviewed reasoning rather than a new production test.

[Source scope](PHASE7_SOURCES.md) · [Parent index](README.md). Last reviewed: **2026-11-03**.
