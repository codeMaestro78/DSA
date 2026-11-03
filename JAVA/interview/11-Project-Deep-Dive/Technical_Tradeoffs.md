# Explain technical tradeoffs under the same assumptions

Use this chapter to explain a real project with defensible evidence. Examples are original **[INFERRED]** hypotheticals, not claims about your implementation, deployment or accomplishments.

## Concept and worked example

A tradeoff exchanges one useful property for another under constraints. Comparing two designs requires the same workload, failure model, security boundary and operating capacity. There is rarely a meaningful universally best tool independent of those inputs.

A hypothetical report API can wait for generation or accept a durable asynchronous job. Waiting reduces client lifecycle states but ties the request to long work and uncertain disconnects. A job API adds status/retention/retry logic but can expose queueing and support resume. Neither guarantees cancellation or exactly-once provider effects by itself.

## Build your explanation

Compare correctness, latency distribution, operational complexity, cost drivers, recovery and reversibility. Include what is excluded from estimates: a faster happy path may omit retries or use a different cache state. Write one trace for normal work and one for failure, then identify what each alternative actually simplifies. Count engineering/on-call burden alongside compute expense without inventing a monetary rate.

## Tradeoffs and evidence limits

A useful answer acknowledges the accepted disadvantage and how it is monitored or bounded. “We used microservices for scale” is incomplete until the owner, scaling unit and actual bottleneck are named. Separate current implementation from a preferred redesign; a suggestion is not a completed migration.

## Practice — PROJECT-P7-02

Your cache lowers average read latency but serves obsolete permissions. Defend or revise the design.

<details><summary>Expected reasoning and followups — attempt first</summary>

Current authority must be enforced at the exposure/action boundary. Cache identity, policy generations, invalidation and data freshness are different mechanisms. A short TTL alone does not establish immediate revocation. State the propagation/consistency contract, invalidate or refuse protected stale results, and measure complete request/error behavior under the same workload. Do not hide a correctness failure behind a lower average latency.

Followups: show an actual artifact if discussing your project; identify your contribution and one unresolved limit. Change one workload/failure assumption and explain what evidence would change the recommendation.

</details>

## Continue and verify

[Canonical lesson or worked case](../04-System-Design/Caching.md), [project explanation framework](Project_Explanation_Framework.md), [project question bank](Project_Question_Bank.md), [blank mock scorecard](../13-Mock-Interviews/Mock_Scorecard.md). These chapters supply their scoped primary sources. This page adds original explanation/practice; its hypothetical calculation or trace is reviewed reasoning rather than a new production test.

[Source scope](PHASE7_SOURCES.md) · [Parent index](README.md). Last reviewed: **2026-11-03**.
