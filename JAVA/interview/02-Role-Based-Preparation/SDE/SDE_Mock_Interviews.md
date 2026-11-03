# SDE transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/SDE_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../03-DSA-and-Coding/12-Graphs/Guide.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

A build service resolves dependency order. Jobs A,B,C,D have edges A→C,B→C,C→D, meaning prerequisites must finish first. A retry may submit an existing job identity; a changed definition with that identity is a conflict. The fixture is a DAG until the interviewer optionally adds D→A.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| SDE-V1 | 10 | Define vertex identity, edge orientation, treatment of disconnected jobs, duplicate edges and cycle output. |
| SDE-V2 | 20 | Derive a topological-order algorithm, trace the supplied graph, and write tests for duplicate edges and a cycle. Pseudocode or scratch Java is acceptable; mark whether it was executed. |
| SDE-V3 | 20 | Extend acceptance into a durable build API with scoped request identity and an explicit job state. Trace a lost response and a worker crash. |
| SDE-V4 | 10 | Explain the strongest check, complexity and remaining operational risk; give a truthful or explicitly hypothetical debugging reflection. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**SDE-V1:** A valid order is A,B,C,D or B,A,C,D. Kahn’s algorithm tracks each remaining incoming edge; removing a zero-indegree vertex preserves that all its emitted prerequisites are complete. No fixed order among independent vertices is implied. A cycle is detected if fewer than V vertices are emitted. Either normalize duplicate edges consistently or count/decrement every duplicate consistently.

**SDE-V2:** Adjacency storage plus indegrees gives O(V+E) time/space including output. Empty graph yields empty order. D→A leaves A,C,D cyclic, while B may still emit; that partial sequence is not a complete build plan. A DFS colors implementation is a valid alternative with an explained cycle witness/stack bound.

**SDE-V3:** Durable creation stores job identity/fingerprint and work intent under an authoritative transaction. Same request replays the same job; changed definition conflicts. Worker retries need task-version/idempotent-effect/recovery rules; a topological order alone is not a distributed scheduler. Preserve dependency failures and cancellation policy rather than marking descendants complete.

**SDE-V4:** Graph order and service recovery are separate claims. Use an independent small graph oracle or verify every emitted edge order; test cycle and disconnected vertices. Real runtime/concurrency/deployment remains untested here.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../03-DSA-and-Coding/12-Graphs/Guide.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
