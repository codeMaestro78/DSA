# MLOps mock — one compatible model release

This is a complete focused **[INFERRED]**, company-neutral variant for approximately 0–2 years of experience. All facts and numbers are fictional teaching fixtures. It supplements the [Phase 6 integrated session](../../13-Mock-Interviews/ML_Engineer_Mock_Interview.md); it is not an employer assessment or permission to perform a real action. No implementation, provider, customer, host, cluster or deployment is exercised here.

## Candidate packet — attempt first

**Mock ID:** MLOPS7-01. **Duration:** 75 live minutes; preflight and 10–15-minute debrief are additional. Use notes, a diagram and calculator. No real system access or generative assistance is needed for the default independent attempt. Agree tools and format first. Keep the disclosure below closed. Read [the prerequisite](MLOps_Roadmap.md) before the session if needed.

### Stipulated scenario

A fictional team serves a risk score through an existing authenticated API. Its candidate release changes model weights and a feature transformer. The old transformer expresses account age in days; the new one expects hours. Model, transformer, schema/units and serving code must have compatible identities. The reviewed exercise does not authorize a deployment, model call or data upload.

A frozen synthetic set of 100 paired predictions has 88 cases both releases get correct,2 only A gets correct,6 only B gets correct and 4 both get wrong. Labels are treated as correct for this fixture; representativeness, costs, slices and delayed-label maturity are unknown. One proposed feature uses an event dated before decision time but only ingested after the decision. The team wants to promote B immediately and keep rollback by “swapping the weights.”

### Four-stage timeline and questions

| Stage | Minutes | Candidate task |
|---|---|---|
| 1 | 15 | Clarify the prediction timestamp, allowed feature availability, user decision, evaluation unit, costs and release owners. Separate supplied facts from missing evidence. |
| 2 | 25 | Compute each release’s accuracy and paired changes. Design an artifact/feature contract, a leakage-free evaluation and an admission check for the serving bundle. |
| 3 | 20 | Trace rollout of an incompatible transformer and recovery when some requests already used the new bundle. Specify rollback compatibility, observability and a bounded canary/stop policy without inventing numeric gates. |
| 4 | 15 | Give a release recommendation, explain what the local fixture establishes and propose the next discriminating check. If discussing a past release, keep personal contributions and actual deployment evidence explicit. |

Save assumptions, calculations, flows and uncertainty before reading the review. Asking for a stipulated clarification is H0 and does not reduce independence. Record supplied direction as H1/H2/H3, then assess its actual effect. A new attempt after reading the answer is guided retention evidence; use a changed case later to test transfer.

<details><summary>Interviewer packet and worked review — reveal only after attempting</summary>

## Interviewer administration

Ask one focused question at a time and use the four-stage budget. Offer a neutral clarification before a hint. Do not silently add advanced requirements or reward an unsupported production promise. Keep optional extensions after a sound core. Record tools, pauses and exact hint content.

H0: evaluation rows are paired and the numbers sum to 100. B is a candidate, not an approved production model. The feature is intended to reproduce a decision at the stated historical time. Training/storage/serving systems are not running in this exercise. Owners must agree population, error costs and stop criteria before a real rollout.

### Stage 1 review — minutes 0–15

Choose the actual target decision before optimizing an aggregate. A feature unavailable to serving at decision time must not be used merely because its event timestamp is earlier. Record availability/revision time and training/serving transforms. Ask about delayed outcomes, dependent customers, costly errors and whether the API caller is authorized for the resource; a model confidence score supplies none of those contracts.

### Stage 2 review — minutes 15–40

A is correct on 88+2=90/100; B on 88+6=94/100. B gains 6 cases and regresses 2, with 8 discordant pairs and a4-percentage-point sample difference. That finite selected fixture is not proof of universal improvement or a calibrated uncertainty statement. Inspect the paired regressions/slices and cost objective. Register a compatible bundle containing weights, transformer/configuration, schema/units, code/runtime contract and lineage/version. Validate golden raw records through both training/serving feature paths and declared output bounds. A version tag alone is insufficient if mutable artifacts can change behind it.

### Stage 3 review — minutes 40–60

A weight-only rollback can preserve the unit mismatch. Restore a complete compatible prior bundle and a safe routing configuration, or perform a separately verified forward repair. Make promotion an authoritative version selection, maintain request/run provenance and prevent mixed incompatible reads. Already served decisions need a scoped impact/reconciliation policy; changing the pointer cannot retract them. A shadow check can compare computation without changing decisions, but does not establish user benefit. A canary needs representative traffic, current label/operational monitoring, an owner and explicit agreed stop conditions; no particular percentage guarantees safety.

### Stage 4 review — minutes 60–75

A bounded recommendation is to hold unconditional promotion while verifying availability, bundle compatibility and the actual decision metric. Report sample90% versus94% and the2 regressions clearly. Propose a golden-record parity check and untouched representative evaluation, followed by controlled rollout only if the agreed criteria support it. Record what is reasoned versus executed. A registry record and one successful health response are not production-readiness proof.

### Hints and followup branches

**H1:** ask which requirement or fact changes the current answer. **H2:** point to the decisive identity, version, denominator or ownership boundary, without finishing the trace. **H3:** supply one worked transition or numerator/denominator and ask the candidate to finish independently. Log what was supplied; do not apply a universal score deduction to unrelated evidence.

**If stuck:** reduce to one record, one request or one failure point and retain partial correct work. **If finished:** change the failure point, introduce an out-of-order/duplicate transition or remove an assumed guarantee. Ask what observation would refute the preferred explanation. The reference approach is not the only valid implementation: accept a simpler batch/polling/manual-review design when it meets the stated contract and explains its costs.

### Task-specific evaluation

Use the [shared six-dimension rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). These are level 3 evidence anchors, not a pass threshold. Use 0–2 according to actual errors/help; leave unobserved dimensions Not assessed. Level 4 requires repeated independent evidence across variations, not one fluent mock.

| Dimension | Observable level 3 evidence | Blocker or next remediation |
|---|---|---|
| CONTRACT | Defines timestamp, availability and intended decision/cost | Allows post-decision knowledge as historical input |
| REASONING | Uses paired evidence and compatible bundle reasoning | Selects by a single attractive unqualified metric |
| CORRECTNESS | Derives90/94 and 8 discordant; rollback includes transformer | Swaps weights alone despite unit mismatch |
| VALIDATION | Specifies golden records, untouched evaluation and rollback checks | Reports synthetic predictions as live model results |
| RISK | Names impact owner, permission, delayed labels and stop behavior | Assumes a registry alias guarantees every request is compatible |
| COMMUNICATION | Explains gains/regressions and missing evidence plainly | Invents deployment benefit or claims shadow is causal proof |

**Common mistakes:** converting a proposed test into an executed claim, hiding a known contract violation behind a favorable metric, assuming a local state change cancels a remote effect, or inventing past ownership. **Strong indicators:** a concrete counterexample, a bounded next check, honest source/authority limits and an alternative with explicit consequences.

</details>

## Debrief and proof boundary

Spend 10–15 minutes recording task evidence, hints and one or two improvements. No candidate score, customer endorsement or observed system result is prefilled. Worked arithmetic and traces are reviewed reasoning only; no new executable suite is claimed. The linked canonical chapters and [source register](PHASE7_SOURCES.md) support the mechanisms; they do not establish the fictional outcomes.

[Parent index](README.md). Last reviewed: **2026-11-03**.
