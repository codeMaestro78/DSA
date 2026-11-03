# Full-stack AI transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/Full_Stack_AI_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Real_Time_Applications.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

A workspace lets a user generate a private draft. Creation response is lost; the browser retries. The server emits sequence1 queued,2 running,3 preview,4 validating,5 succeeded. A user opens two tabs, then permission is revoked before export. The candidate must distinguish job facts from each tab’s connectivity state.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| FS-V1 | 10 | Define ownership, accepted/draft states, session scope and current download authority. |
| FS-V2 | 20 | Specify create/status/events/export contracts and a same-key retry/conflicting-body response. |
| FS-V3 | 20 | Trace two tabs, duplicate sequence3, a missed terminal event, pruned history and revocation. |
| FS-V4 | 10 | Provide accessible states and focused tests, then explain implementation versus design evidence. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**FS-V1:** The durable server owns job acceptance and terminal validation; the browser owns display/connectivity state. Creation claims a scoped operation key/fingerprint in the same durable acceptance boundary as job/work intent. A matching retry returns the same job identity, not another generation.

**FS-V2:** Each job has ordered immutable event identities. Tabs independently deduplicate applied sequences; a transport receipt is not always the applied cursor. Rebuild from an authorized snapshot/watermark and catch up every later event, closing the snapshot/subscription race. Polling coherent snapshots is a valid simpler alternative.

**FS-V3:** A preview does not permit export; success plus current resource authority is required. If old events are pruned, explicitly reset to a current snapshot, never append a partial replay as a complete draft. Revocation applies at protected exposure/action boundaries under a declared consistency/propagation contract; previously displayed text cannot be retroactively unseen.

**FS-V4:** Tests should isolate response loss, duplicate work, cursor gap, conflicting event payload, failure reload, keyboard/status announcements and revoked export. They are proposed checks here, not a tested browser or auth system. Do not equate a disconnect with remote cancellation or silently claim screen-reader compliance.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Real_Time_Applications.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
