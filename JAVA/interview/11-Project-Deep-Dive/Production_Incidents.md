# Discuss incidents without inventing operational history

Use this chapter to explain a real project with defensible evidence. Examples are original **[INFERRED]** hypotheticals, not claims about your implementation, deployment or accomplishments.

## Concept and worked example

An incident explanation distinguishes user impact, observations, hypotheses, containment, root cause evidence, recovery and prevention. A timeline matters because a symptom after a deployment does not by itself prove the deployment caused it. Early-career candidates can use a local bug honestly when they have no production ownership.

In a hypothetical service, errors rise after a release and queue age grows. First establish affected paths/users, changes and dependency health. A rollback can be justified as containment even before a precise cause is known, provided rollback compatibility is understood. After recovery, a controlled reproduction can test the suspected mechanism. Do not call correlation a confirmed cause.

## Build your explanation

Describe what you personally did, what a teammate did and what the team learned. Keep private payloads out of shared evidence. Use a few timestamps, relevant telemetry and one causal check. Record whether a fix was merged, locally tested, staged or actually deployed; these are different facts. Name who owns reconciliation of uncertain or duplicate effects.

## Tradeoffs and evidence limits

For a real incident, state unknown scope and whether recovery was verified from user-visible signals. A restart might remove the symptom without correcting the defect. Preventive actions should target the mechanism: a regression fixture, bounded queue, compatibility gate or clearer ownership. “Add monitoring” needs a signal, threshold intent and response owner.

## Practice — PROJECT-P7-03

A worker times out after calling a partner and an operator suggests replaying every failed task. What is the response?

<details><summary>Expected reasoning and followups — attempt first</summary>

A timeout does not establish that the partner did nothing. Preserve operation/attempt IDs, reconcile authoritative outcomes and replay only under the actual deduplication/retention contract. Contain the unsafe replay path without disabling unrelated work. Communicate knowns, unknowns and next check; do not invent an ETA or perform a real external correction in an interview exercise.

Followups: show an actual artifact if discussing your project; identify your contribution and one unresolved limit. Change one workload/failure assumption and explain what evidence would change the recommendation.

</details>

## Continue and verify

[Canonical lesson or worked case](../13-Mock-Interviews/Startup_Mock_Interview.md), [project explanation framework](Project_Explanation_Framework.md), [project question bank](Project_Question_Bank.md), [blank mock scorecard](../13-Mock-Interviews/Mock_Scorecard.md). These chapters supply their scoped primary sources. This page adds original explanation/practice; its hypothetical calculation or trace is reviewed reasoning rather than a new production test.

[Source scope](PHASE7_SOURCES.md) · [Parent index](README.md). Last reviewed: **2026-11-03**.
