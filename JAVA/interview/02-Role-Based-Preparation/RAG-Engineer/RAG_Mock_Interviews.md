# RAG engineer transfer mock

## Start with the complete baseline

The [Phase 6 candidate packet](../../13-Mock-Interviews/RAG_Engineer_Mock_Interview.md) and its separately linked interviewer companion supply the primary full session. This requested role/domain chapter adds an **original [INFERRED], company-neutral 60-minute transfer variant**, not a copy of that session or an employer interview report. Target approximately 0–2 years; optional deeper followups are not universal eligibility requirements.

Read the [prerequisite](../../08-LLM-RAG-and-Agent-Engineering/RAG_Evaluation.md) before the session. Preflight tools, references/AI permission and any agreed format needs; preflight and a 10–15-minute debrief are outside the 60 minutes. Paper/pseudocode/calculator is acceptable for this reasoning variant. Do not use live customer/provider services or private data. Label any scratch implementation unexecuted unless actually run.

## Candidate scenario

A tenant A user asks for a current leave policy. Gold document A2 is current; A1 is obsolete; B1 is tenant B only. Raw top2 contains A1,B1 and the draft cites A1. Two other answerable queries retrieve their single eligible gold document. One unsupported query nevertheless receives a confident answer. All judgments are stipulated; actual source text is absent.

## Four-stage timeline and tasks

| Stage | Minutes | Candidate task |
|---|---|---|
| RAG-V1 | 10 | Define permitted/current evidence, citation support and the meaning of an answerable query. |
| RAG-V2 | 20 | Compute macro Recall@2 on the three answerable queries and diagnose retrieval, version, permission and answer-support failures separately. |
| RAG-V3 | 20 | Plan a versioned index change and rollback that also respects deletes, current permissions and incompatible embeddings. |
| RAG-V4 | 10 | Explain a finite acceptance contract and incident update without promising “always grounded.” |

Stop after 60 active minutes and preserve notes before opening the review. A peer should provide one stage at a time. A solo attempt after reading the solution is a supported reattempt, not independent evidence.

<details><summary>Interviewer guidance and worked review — reveal after attempting</summary>

## Fixed administration and reference reasoning

Clarifications of the stipulated facts are H0 and do not reduce independence. If a decision needs an unspecified fact, let the candidate identify it and make a reversible assumption; do not invent hidden restrictions. At minutes 10,30,50,60 request the current artifact and move to the next stage, preserving incomplete work.

**RAG-V1:** A1 can be topically relevant yet invalid for a current-policy question; B1 is unauthorized regardless of its rank or whether it is cited. Enforce eligibility before protected text is exposed to the answerer. A citation ID does not prove the wording is supported; absent text keeps entailment unverified.

**RAG-V2:** Recall values0,1,1 give macro2/3. The unsupported query is excluded from empty-gold recall under this contract and separately has one assertion failure. Reranking A1/B1 cannot manufacture missing A2. Fix candidate eligibility/generation and assess support independently.

**RAG-V3:** Build an immutable generation manifest with source/chunk identity, embedding/configuration and content versions. Shadow paired queries and adversarial/delete/version fixtures. Publish a compatible active generation atomically with a cutoff/update catch-up policy. A rollback target may no longer be permitted/current; rollback must not resurrect deleted/private evidence.

**RAG-V4:** Define passing the specified policy/version/support suite, sample quality with reviewed labels and report uncertainty/slices. No finite suite proves universal grounding or production security. Include a concrete check of the trusted tenant filter and source manifest before asserting a model cause.

**Progressive hints:** H1 asks which stated condition changes the answer; H2 points to the relevant invariant/denominator/authority boundary; H3 supplies one reference step above, then asks the candidate to finish the trace. Record exact help and its effect. Do not turn a supplied derivation into independent evidence.

**Followup branches:** If stuck, reduce to one record/event/user and ask what may change next. If finished, change one material assumption—duplicate identity, stale version, empty population, changed cost or permission—and require a new trace. Accept alternatives meeting the same contract; names of frameworks do not earn points by themselves.

**Strong indicators:** concrete case analysis, correct denominators/units, precise state/authority and an honest limit on evidence. **Common mistakes:** solving a different contract, hiding incomplete work, inventing real outcomes or claiming a reference diagram/test proposal is a deployed result.

## Score evidence and remediation

Use the six dimensions and unchanged 0–4 scale in the [shared rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). CONTRACT requires the actual task/population/authority; REASONING the applicable derivation; CORRECTNESS a coherent worked result; VALIDATION a normal plus failing/boundary check with honest execution scope; RISK the material failure/permission/evaluation limit; COMMUNICATION clear decisions, hints and unknowns. Preserve known defects separately; do not average them into a hiring decision. Level 4 needs repeated independent transfer; unobserved dimensions stay Not assessed.

Select the first wrong decision and one changed-input reattempt, rather than rereading every answer. A hypothetical story supplies proposed reasoning only; past personal ownership requires a truthful episode and supporting evidence.

</details>

## Verification and navigation

This variant’s arithmetic/traces were reviewed **2026-11-03**; no code, native SQL, concurrent service, model evaluation or deployment was executed for it. Technical mechanisms reuse the relevant [canonical prerequisite](../../08-LLM-RAG-and-Agent-Engineering/RAG_Evaluation.md) and its scoped primary references; newly added SDE/DSA sources are in the [SDE register](../../02-Role-Based-Preparation/SDE/PHASE7_SOURCES.md). Practice timings/scoring are editorial; source guidance does not corroborate invented prompts.

[Blank scorecard](../../13-Mock-Interviews/Mock_Scorecard.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Local index](README.md).
