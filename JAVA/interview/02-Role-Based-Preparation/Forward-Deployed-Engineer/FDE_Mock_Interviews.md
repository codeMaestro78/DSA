# FDE transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/FDE_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../02-Role-Based-Preparation/Forward-Deployed-Engineer/Technical_Discovery.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

A fictional warehouse manager requests autonomous restock orders by Friday. Current permission covers a sanitized stock export only; no supplier ordering credential or external-model transfer is approved. An offline diagnostic proposes40 orders,35 are judged appropriate;10 additional items abstain. Supplier availability and reviewer capacity are unknown.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| FDE-V1 | 10 | Run discovery: outcome, costly mistake, data/action permission, deadline meaning and acceptance owner. |
| FDE-V2 | 20 | Specify a suggestion-only integration with stock item identity/version, human review and stale-proposal behavior. |
| FDE-V3 | 20 | Calculate proposal coverage/correctness, define a baseline/evaluation and negotiate scope. |
| FDE-V4 | 10 | The manager asks to submit an old proposal after stock changed. Give a clear response and a controlled next step; end with an honest customer update. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**FDE-V1:** Friday should be clarified as demo versus production rather than assumed. Prioritize costly overstock/stockout, allowed processing environment, supplier interface, stock authority, acceptance owner and review capacity. A useful next milestone can be an offline reviewed proposal demonstration, not autonomous ordering.

**FDE-V2:** Bind proposals to tenant/item/source revision, processor/config version and explicit state. The stock system remains authoritative. An accepted action needs current authority and a conditional revision check under the actual interface contract; read-then-unconditional-write is racy. No live supplier behavior can be inferred from an export.

**FDE-V3:** Coverage40/(40+10)=80%; conditional correctness35/40=87.5% in the selected fixture. Compare with a simple rule baseline on paired, separately reviewed data and show costly errors/abstentions. Unknown human capacity prevents claiming review is free or the pilot can handle production workload.

**FDE-V4:** Mark the old proposal stale and obtain a fresh authorized decision rather than replaying it blindly. State knowns, unknowns, containment, owner and next update point without inventing a root cause/ETA. Hypothetical roleplay demonstrates proposed reasoning, not real customer impact.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../02-Role-Based-Preparation/Forward-Deployed-Engineer/Technical_Discovery.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
