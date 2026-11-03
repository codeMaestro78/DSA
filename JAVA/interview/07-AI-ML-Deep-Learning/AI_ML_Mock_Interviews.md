# AI/ML foundation transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../13-Mock-Interviews/ML_Engineer_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](Model_Evaluation.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

A binary classifier operates on a hypothetical population of1,000: prevalence10%, sensitivity80%, false-positive rate10%. A threshold review uses predicted probability0.2 with false-positive cost1 and false-negative cost9. Costs are stipulated, not a financial/medical deployment.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| AIML-V1 | 10 | Define all conditional probabilities and compute the expected confusion counts and precision. |
| AIML-V2 | 20 | Derive the minimum-cost binary action under calibrated probabilities and stated costs. |
| AIML-V3 | 20 | Diagnose leakage if labels or preprocessing were used before splitting, and design a held-out comparison. |
| AIML-V4 | 10 | Explain a gradient/shape check for a small logistic model and the difference between an optimization step and generalization. |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**AIML-V1:** There are100 actual positives,900 negatives; expected TP80,FN20,FP90,TN810. Precision80/(80+90)=8/17≈47.06%. Sensitivity80% is not precision. Expected counts come from supplied rates, not a sampled experiment or confidence interval.

**AIML-V2:** Predict positive when1×(1−p)<9p, hence p>0.1 with equality a tie policy. At p0.2 positive expected cost0.8 versus negative1.8. Calibration, population and action-cost assumptions matter; capacity caps or changed interventions need separate decision reasoning.

**AIML-V3:** Train/validation/test responsibilities differ; fit transforms on training data only and tune thresholds on development data. Preserve temporal/group structure where needed. A repeatedly consulted “test” set becomes selection data; a fresh untouched evaluation is required for the selected procedure.

**AIML-V4:** For n examples,d features, logistic weight gradient has shape(d,) and bias is scalar. Compare a tiny analytic gradient with finite differences under the same loss/reduction; a lower training objective after a step does not establish held-out quality. No framework, training or deployment is executed by this mock.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](Model_Evaluation.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../13-Mock-Interviews/README.md) · [Local index](README.md).
