# Architecture decisions with evidence

Use this chapter to explain a real project with defensible evidence. Examples are original **[INFERRED]** hypotheticals, not claims about your implementation, deployment or accomplishments.

## Concept and worked example

An architecture decision records the problem, constraints, alternatives, choice and consequences at a specific time. It is stronger than naming a stack: a reviewer should see which constraint the decision satisfies and what would change it.

A hypothetical two-person team needs a durable report job, status lookup and one worker. An application plus relational job store and worker can be simpler than several networked services. If independent ownership or isolation later becomes a demonstrated problem, splitting a component becomes a new decision with migration and operating costs. The example supplies reasoning, not evidence that your project used this design.

## Build your explanation

Use a short decision record: date/owner; observed versus assumed requirements; alternatives under the same workload; invariant/source of truth; chosen flow; failure/security/cost consequences; evidence collected; revisit trigger. Include rejected alternatives with the real reason, not an exaggerated strawman. A transaction boundary or access check needs an actual owner, not a box labeled database/security.

## Tradeoffs and evidence limits

For a real project, point to the relevant code/configuration and say whether the decision was yours, shared or inherited. A diagram is evidence of explanation, not deployment. State a changed assumption that would reverse the recommendation. Avoid retroactively inventing measurements to make an earlier choice appear inevitable.

## Practice — PROJECT-P7-01

Explain why your project chose its storage model. Then assume the busiest tenant now contributes half the writes.

<details><summary>Expected reasoning and followups — attempt first</summary>

Start from the real access patterns and invariants and show where they are enforced. Separate a workload observation from an assumption. Analyze hot keys, tenant fairness, contention and recovery before proposing sharding; a different storage engine does not automatically distribute a single hot key. If you cannot inspect deployment evidence, label the reasoning as a proposed evolution.

Followups: show an actual artifact if discussing your project; identify your contribution and one unresolved limit. Change one workload/failure assumption and explain what evidence would change the recommendation.

</details>

## Continue and verify

[Canonical lesson or worked case](../04-System-Design/System_Design_Interview_Framework.md), [project explanation framework](Project_Explanation_Framework.md), [project question bank](Project_Question_Bank.md), [blank mock scorecard](../13-Mock-Interviews/Mock_Scorecard.md). These chapters supply their scoped primary sources. This page adds original explanation/practice; its hypothetical calculation or trace is reviewed reasoning rather than a new production test.

[Source scope](PHASE7_SOURCES.md) · [Parent index](README.md). Last reviewed: **2026-11-03**.
