# Reviewer calibration exercises

These **[INFERRED]** synthetic excerpts let reviewers practice the six-dimension rubric. They are not candidate transcripts, observed outcomes, employer examples or proof that the rubric predicts hiring. Score only what the excerpt establishes; many dimensions must remain Not assessed. Attempt each before opening its review.

## CAL-01 — A supplied invariant

A coding task says the retained interval is `(now-window, now]`. The candidate includes an event exactly at `now-window`. The reviewer supplies H2: “That boundary event must be removed; use a strict lower bound.” The candidate fixes it and manually traces that one case correctly. No executable run or other trace is shown.

Record CORRECTNESS, VALIDATION and COMMUNICATION evidence. What can you conclude about independence?

<details><summary>Calibration review — reveal after scoring</summary>

The final boundary logic is corrected, but the decisive invariant came from H2. **CORRECTNESS 2** is a reasonable rating for the observed portion, with the correction and dependence recorded; it is not evidence of independent derivation. **VALIDATION 1–2** depends on what the manual trace actually checks, and the record must say it was not executed. **COMMUNICATION Not assessed** is appropriate if “fixes it” is all the available evidence: a cooperative reaction does not by itself establish a clear explanation. No whole-task score can be inferred. CONTRACT and REASONING need more observation. A new interval convention or duplicate timestamp fixture can test transfer later.

</details>

## CAL-02 — A plausible design with an unresolved promise

The prompt promises no duplicate external charge. A candidate proposes a durable local request key. When asked what happens if the provider commits a charge but the response times out, they say “delete the key and retry with a new key.” They draw a clear diagram but do not establish provider deduplication, reconciliation or a weaker promise.

Record the finding, affected dimensions and next exercise. Can polished delivery compensate?

<details><summary>Calibration review — reveal after scoring</summary>

There is a concrete unresolved duplicate-effect path. **CORRECTNESS 1** and **RISK 1** are defensible for that attempted contract: a local key alone cannot prove the external effect did not occur, and the proposed new identity can duplicate it. State the actual gap; do not mark every unrelated dimension zero. The diagram may support some COMMUNICATION evidence, but “clear” alone does not establish all of level 3. Do not average scores into a pass. The next exercise is a four-event trace: local intent → provider commit → lost response → retry/reconciliation using the same logical identity and the provider's actual contract. A valid alternative may weaken the promise explicitly, but it cannot silently replace the stated requirement.

</details>

## CAL-03 — A sound plan without an observed outcome

In a fictional customer roleplay, the candidate asks who owns a routing decision, separates a dry-run pilot from production actions, proposes reviewed labels and a rollback owner, and states that no customer impact has yet been measured. They explain the tradeoffs clearly. The reviewer has no implementation, pilot result or real personal story.

Which statements concern proposed reasoning and which would require new evidence?

<details><summary>Calibration review — reveal after scoring</summary>

The excerpt can support **CONTRACT 3**, **REASONING 3**, **RISK 3** and **COMMUNICATION 3** for the observed roleplay scope if it was independent and the task-specific anchors are satisfied. A useful evaluation design can support VALIDATION reasoning, but it does not demonstrate that checks ran or that the intervention helped. Distinguish evaluating a plan from rating observed delivery. **Actual customer impact, implementation correctness and past personal ownership remain Not assessed**; do not fill them from the fictional scenario. There is no evidence for 4 without repeated independent variations. The next check is a small labeled pilot specification or a truthful past episode, depending on the intended dimension.

</details>

## Apply calibration to a complete attempt

Write evidence before a score. If reviewers disagree, identify whether the disagreement concerns the contract, assistance received, technical mechanism or sufficiency of observation. Preserve uncertainty instead of pretending there is a unique precise rating from a short excerpt. Reconcile against the companion's task anchors, then choose a discriminating followup.

[Rubric](Interview_Evaluation_Rubric.md) · [Blank scorecard](Mock_Scorecard.md) · [Source scope](PHASE6_REVIEW_SOURCES.md).

Last verified: **2026-11-03**; reviewed editorial examples, no real interviews or scoring study performed.
