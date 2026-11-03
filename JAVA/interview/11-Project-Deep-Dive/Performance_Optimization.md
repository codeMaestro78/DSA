# Defend performance claims with comparable measurements

Use this chapter to explain a real project with defensible evidence. Examples are original **[INFERRED]** hypotheticals, not claims about your implementation, deployment or accomplishments.

## Concept and worked example

An optimization claim needs a baseline, representative workload, environment, metric and correctness check. Latency percentiles, throughput, utilization and cost answer different questions. A synthetic local improvement does not establish production capacity or user benefit.

Suppose a hypothetical query benchmark changes from 100 ms to 20 ms after adding an index. If the second run has warmed caches or returns fewer rows, the comparison does not isolate the index. Fix result semantics, data distribution, concurrency, cache policy and version, then compare repeated trials and representative large/small tenant slices.

## Build your explanation

Start with a user-visible target and instrument request stages. Locate the dominant work before optimizing. Keep complete latency alongside stage spans: percentiles generally do not add, and selective successful-request timing hides failures. Inspect query plans/allocations/network wait as evidence permits, and distinguish an estimate from a measured result.

## Tradeoffs and evidence limits

An index can accelerate reads and add write/storage cost. Caching can improve repeated requests while changing freshness. Batching can raise throughput and increase waiting time. State these consequences and test failure/cancellation paths. Report sample counts, units and uncertainty justified by the sampling process; do not attach benchmark precision unsupported by the setup.

## Practice — PROJECT-P7-04

A report says p95 improved because slow failed requests were excluded from the new run. How should it be corrected?

<details><summary>Expected reasoning and followups — attempt first</summary>

Define the same eligible request population for both runs, report errors/timeouts separately and include user-visible failed latency according to the declared measurement policy. Explain whether timeout values are censored. Re-run the controlled comparison if available; otherwise withdraw the unqualified improvement claim and state the evidence limit. A better successful-request metric alone can coexist with worse reliability.

Followups: show an actual artifact if discussing your project; identify your contribution and one unresolved limit. Change one workload/failure assumption and explain what evidence would change the recommendation.

</details>

## Continue and verify

[Canonical lesson or worked case](../12-Interview-Question-Bank/Advanced_Questions.md#qb-adv-010), [project explanation framework](Project_Explanation_Framework.md), [project question bank](Project_Question_Bank.md), [blank mock scorecard](../13-Mock-Interviews/Mock_Scorecard.md). These chapters supply their scoped primary sources. This page adds original explanation/practice; its hypothetical calculation or trace is reviewed reasoning rather than a new production test.

[Source scope](PHASE7_SOURCES.md) · [Parent index](README.md). Last reviewed: **2026-11-03**.
