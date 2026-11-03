# AI engineer transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/AI_Engineer_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

An assistant drafts expense categories but cannot approve reimbursement. A trusted server supplies expense ID, currency and amount; model output contains category and explanation. A diagnostic set has20 eligible expenses:16 proposals,12 correct categories,4 abstentions; among4 intentionally unsupported expenses it wrongly proposes twice. These are synthetic labels, not model results.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| AI-V1 | 10 | Define trusted fields, permissible output and the boundary between proposal, acceptance and payment. |
| AI-V2 | 20 | Specify deterministic validation plus semantic review; calculate coverage and correctness among eligible proposals. |
| AI-V3 | 20 | Design a held-out evaluation and a release decision that includes the unsupported cases, review capacity and costly errors. |
| AI-V4 | 10 | The product owner asks to pay any schema-valid proposal. Explain the changed contract, next check and what remains hypothetical. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**AI-V1:** The server owns expense identity/amount/currency. Validate exact schema and allowed category, bind output to the supplied expense version and never allow a rationale to redefine authority. Copying a permitted ID or passing an enum is structural evidence, not proof of category meaning. A baseline rule/manual workflow is a valid alternative to an LLM.

**AI-V2:** Eligible proposal coverage16/20=80%; selective correctness12/16=75%. Four eligible abstentions remain in coverage, not selective accuracy. Unsupported assertion rate2/4=50% on that stipulated slice. Do not merge denominators or extrapolate rare-error rates from24 selected examples.

**AI-V3:** Freeze task definition, gold labels/uncertainty, split and acceptance criteria before tuning. Compare systems on paired expenses and report false approvals, unsupported cases, review latency and actual total work. No automatic payment release is justified by this fixture or missing action authority.

**AI-V4:** Payment is a separate consequential action requiring authorization, stable action meaning, duplicate-effect handling and reconciliation. Keep the proposal-only boundary unless a separately verified policy is accepted. Prompt injection is not eliminated by saying “ignore malicious text.”

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
