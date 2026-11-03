# ML engineer transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/ML_Engineer_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../07-AI-ML-Deep-Learning/Model_Evaluation.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

Predict next-week demand at each store using information available Sunday midnight. A source publishes corrected sales on Tuesday. Store A has errors[1,1,1,1], store B[5]. A proposed report averages all five rows. A historical campaign launches only in treated stores; naive before/after comparisons are also supplied without randomization.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| ML-V1 | 10 | Define prediction unit/time, label horizon, feature availability and the population that matters. |
| ML-V2 | 20 | Compute row-weighted MAE and equal-store MAE; explain which objective each estimates and how to implement/report both. |
| ML-V3 | 20 | Propose temporal/group-aware validation and an online causal comparison without using Tuesday corrections at Sunday. |
| ML-V4 | 10 | Give a release recommendation and a falsifiable next experiment, distinguishing prediction quality from campaign impact. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**ML-V1:** Point-in-time availability—not merely event time—controls usable features for Sunday decisions. Tuesday corrections leak future knowledge if reconstructed into Sunday training rows without availability history. Fit preprocessing only on the training portion; ensure labels mature before evaluation.

**ML-V2:** Row MAE=(4×1+5)/5=1.8. Equal-store MAE=(1+5)/2=3. Both can be legitimate objectives; neither automatically defines fairness or business value. Preserve store slices and selection/weighting rules. A hash-map sum/count aggregation costs expected O(n) plus any sorting of output keys, O(g) aggregates excluding input/output arrays.

**ML-V3:** Temporal evaluation answers future performance for the stated population; held-out stores probe new-store generalization. These can be separate reported cohorts instead of an incoherent universal split. Use an appropriate experiment/randomization unit and account for interference if studying campaign effects. Before/after alone can confound seasonality and selection.

**ML-V4:** Choose gates based on decision cost, confidence and operational behavior, not the nicer aggregate. Forecast accuracy does not prove that the campaign caused revenue improvement. Report absent evidence and a specific controlled or otherwise justified causal analysis; no model training or experiment was run here.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../07-AI-ML-Deep-Learning/Model_Evaluation.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
