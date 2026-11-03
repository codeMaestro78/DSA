# Behavioral and communication question bank

## Scope and use

These are original **[INFERRED], company-neutral** practice questions, not reports of employer questions or an unpublished rubric. Attempt the prompt before opening the answer. Difficulty is editorial: Beginner establishes a foundation, Intermediate applies it, Advanced adds interacting failure/measurement constraints, and Expert is an optional specialist extension for this early-career curriculum.

The guidance describes expected reasoning and valid alternatives rather than a single required script. Use real personal evidence for experience questions, or explicitly label a hypothetical answer. No candidate history, metric or production claim is supplied for you to adopt.

<a id="qb-beh-001"></a>

## QB-BEH-001 — Resolve two incompatible commitments

| Field | Value |
|---|---|
| Category | Behavioral / Prioritization |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Clarification, authority, prioritization and communication. |
| Related IDs | QB-BEH-002 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A manager asks you to deliver A by Friday; a customer-facing teammate promises B for the same deadline. You cannot complete both with the available capacity. Explain your next conversation; use a real episode only if it actually happened.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Restate the two outcomes and the capacity conflict, ask which commitment has decision authority, and make the tradeoff concrete. Offer a small set of options with scope, risk and revised expectations. Agree who informs affected people and when the decision will be reviewed. If using a real episode, state your actual role and observed result.

**Explanation:** The useful behavior is making the conflict visible while there is still time to act. A hypothetical wording example is: “These two scopes need the same remaining time. I can finish A, or deliver a smaller B; which outcome takes priority, and who will update the customer?” This does not invent a personal success story. Your answer should explain what evidence supports the estimate and which assumption could change it.

**Followups:** What if the two stakeholders disagree? What if neither owns the decision?

**Common mistakes:** Quietly promising both, blaming a colleague for the mismatch, or escalating without explaining options.

</details>

<a id="qb-beh-002"></a>

## QB-BEH-002 — Challenge a flattering metric

| Field | Value |
|---|---|
| Category | Behavioral / Evidence honesty |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Integrity and measurement scope. |
| Related IDs | QB-BEH-003 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A project summary says your change made responses 80% faster. You only measured three local requests after a warm cache. How do you revise the claim and explain the limitation?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Replace the broad claim with the actual observation and conditions, or omit the percentage if the comparison is not reliable. State sample size, environment, cache condition and measurement method. Propose a comparable before/after workload with enough observations to inspect variability and failures. Do not manufacture a production result or imply that the small run proved a general speedup.

**Explanation:** Good communication preserves useful work without exaggeration: “The local warm-cache sample was faster, but it is too small and unrepresentative to establish an 80% improvement. I would rerun controlled comparisons before using that number.” If a stakeholder already saw the stronger wording, explain how you would correct it. The behavioral signal is evidence-driven correction, not a polished defensive explanation.

**Followups:** What if the stronger number is already in a shared slide? Which user-facing metric would you measure?

**Common mistakes:** Treating a sample mean as a production p95 or hiding an inconvenient caveat.

</details>

<a id="qb-beh-003"></a>

## QB-BEH-003 — Help without taking over

| Field | Value |
|---|---|
| Category | Behavioral / Collaboration |
| Difficulty | Beginner |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Collaboration and accurate credit. |
| Related IDs | QB-BEH-004 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A teammate is stuck on a task you know well. You can finish it yourself quickly, but they own the work. Explain a helpful approach and what you would report about your contribution.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Ask what they have established and where they are blocked. Offer a focused explanation, pair on a minimal reproduction or agree a bounded split. Respect their ownership while ensuring the team can meet its obligation. Credit their implementation and your assistance accurately. If the deadline requires a handoff, make that explicit with the relevant owner rather than silently taking over.

**Explanation:** A useful answer balances progress and learning. It identifies one action you would take, how you would check that the blocker is resolved and what you would communicate afterward. If describing a real case, say what the teammate did as well as what you did; no numerical impact is required when it was not measured. Avoid inventing their feelings or attributing intent.

**Followups:** How would the answer change near a hard deadline? How would you know the explanation helped?

**Common mistakes:** Claiming the whole result, solving silently or providing vague encouragement without useful help.

</details>

<a id="qb-beh-004"></a>

## QB-BEH-004 — State a skill boundary honestly

| Field | Value |
|---|---|
| Category | Behavioral / Scope communication |
| Difficulty | Beginner |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Self-awareness and truthful technical scope. |
| Related IDs | QB-BEH-005 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

An interviewer asks about a production technology you used only in a local tutorial. Give a concise answer that communicates your actual knowledge and a reasonable next step.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** State the boundary directly, then describe the mechanism or local task you actually understand. Explain which production properties you have not operated or measured, and connect relevant adjacent experience. Ask a focused question if it changes the discussion. Do not inflate tutorial usage into ownership of a production service.

**Explanation:** A hypothetical structure is: “I have used it locally for this flow, but I have not operated it in production. I can explain that mechanism and the failure cases I would investigate; I would need experience with rollout, recovery and representative load before making operational claims.” This keeps the answer useful without an invented credential. Choose the amount of detail based on the interviewer’s question.

**Followups:** What artifact could demonstrate your local understanding? What would be the first production-readiness check?

**Common mistakes:** Claiming expertise from a dependency name or apologizing so extensively that actual knowledge disappears.

</details>

<a id="qb-beh-005"></a>

## QB-BEH-005 — Correct a review disagreement

| Field | Value |
|---|---|
| Category | Behavioral / Technical disagreement |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Evidence-based disagreement. |
| Related IDs | QB-BEH-006 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A reviewer suggests a change that appears to break a documented contract. How do you respond constructively and verify the decision?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Restate the shared goal, identify the exact contract and construct a small counterexample or focused test. Ask whether the intended behavior has changed. Compare the current and proposed approaches using the same constraints, then agree the implementation and documentation/test update. Respect the decision owner while making the risk visible.

**Explanation:** The answer should show how evidence can change either person’s view. A failing example is more useful than saying “that is wrong.” If the requirement is intentionally changing, the review may be correct and your compatibility concern still needs resolution. In a real story, report what actually happened and what you learned rather than forcing a victory narrative.

**Followups:** What if the contract is undocumented? What if the reviewer is more senior?

**Common mistakes:** Arguing from status, treating a test as proof of the intended product requirement, or silently ignoring review.

</details>

<a id="qb-beh-006"></a>

## QB-BEH-006 — Negotiate an unclear assessment scope

| Field | Value |
|---|---|
| Category | Behavioral / Ambiguity |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Scope management and resource integrity. |
| Related IDs | QB-BEH-007 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A hypothetical work-trial instruction says “build a complete AI product” but omits the time budget, evaluation criteria and allowed resources. What do you clarify and what preparation can proceed?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Ask for the expected deliverable, time budget, evaluated skills, environment, permitted data and per-stage documentation/AI rules. Clarify how incomplete work is evaluated and how submission access is handled. Independently practice a bounded feature and explain its tests/failure paths while waiting. Do not assume an employer permits outside assistance because the role uses AI.

**Explanation:** A concise clarification message should ask the few questions that materially affect the work. You can propose a reasonable bounded interpretation for review, but do not treat silence as approval of an assessment policy. This is a hypothetical preparation scenario, not an assertion that a named employer uses an unreasonable trial or that you should disclose private employer material.

**Followups:** Which missing answer changes the architecture? How would you report unfinished work?

**Common mistakes:** Inventing a tool policy, overcommitting to an undefined scope or sharing private assessment prompts.

</details>

<a id="qb-beh-007"></a>

## QB-BEH-007 — Protect confidential project evidence

| Field | Value |
|---|---|
| Category | Behavioral / Confidentiality |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Privacy judgment and evidence quality. |
| Related IDs | QB-BEH-008 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

An interviewer asks to see a private customer dataset to support your project claim. Explain how you can substantiate the work without exposing data you are not authorized to share.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Explain the confidentiality boundary and offer permitted alternatives: a sanitized architecture, aggregate results that are approved for sharing, a synthetic reproduction or a description of the measurement method and its limits. State which claim each alternative supports. Do not pretend synthetic data proves actual customer behavior, and do not disclose private records to make the story more impressive.

**Explanation:** A strong answer keeps the evidence boundary visible. An interviewer can learn how you designed and verified the work without receiving protected data. If no artifact is shareable, explain your contribution and uncertainty honestly. The decision concerns authorization and communication; this bank does not supply legal advice about a particular agreement. Use the actual organization’s rules and permitted disclosure.

**Followups:** What if anonymization still leaves a rare identifying combination? Which claim cannot be verified with a synthetic demo?

**Common mistakes:** Assuming removed names make all data anonymous or replacing a real measurement with an invented result.

</details>

<a id="qb-beh-008"></a>

## QB-BEH-008 — Communicate an unknown incident cause

| Field | Value |
|---|---|
| Category | Behavioral / Incident communication |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Causal precision and incident communication. |
| Related IDs | QB-BEH-009 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A service is intermittently failing. You have confirmed user-visible errors but not the cause. Write the substance of a brief status update and explain your next investigative step.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** State confirmed impact and scope, what is unknown, current safe mitigation and the next update time/owner. Separate observations from hypotheses. Choose the next check by its ability to distinguish plausible causes, using request IDs, dependency timing or a recent change as appropriate. Do not declare a root cause from one correlated log entry.

**Explanation:** A hypothetical update might say: “We confirmed intermittent failures on this operation. We have not yet identified the cause. We are limiting the affected path while checking dependency errors, and will update at the agreed time.” Add real details only when supported. In a real experience, identify your role rather than implying you controlled the entire incident.

**Followups:** What if the mitigation also harms another user flow? What evidence would falsify the leading hypothesis?

**Common mistakes:** Promising a recovery time without evidence, hiding uncertainty or giving a long technical narrative without impact/status.

</details>

<a id="qb-beh-009"></a>

## QB-BEH-009 — Request an accessible interview format

| Field | Value |
|---|---|
| Category | Behavioral / Remote logistics |
| Difficulty | Beginner |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Clear self-advocacy and logistics. |
| Related IDs | QB-BEH-010 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A hypothetical interview setup prevents you from demonstrating your work effectively. How would you request an adjustment without making assumptions about the employer’s policy?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Explain the practical barrier and the adjustment that would help, using the recruiter’s published contact route when available. Ask which arrangements are possible and confirm the agreed format, tools and schedule. Share only the personal information needed for the request. Do not assume approval, disclose unnecessary sensitive details or claim a jurisdiction-specific entitlement from this exercise.

**Explanation:** The task is communication, not an evaluation of a person’s health or identity. A clear request makes the functional need understandable while preserving privacy. You can continue compatible preparation while the arrangement is being resolved, but a missing response is not confirmation. Use the current employer’s instructions rather than a generic internet rule.

**Followups:** What details affect environment rehearsal? How would you confirm that an agreed tool is available?

**Common mistakes:** Avoiding the issue until the session fails or treating a suggested adjustment as already authorized.

</details>

<a id="qb-beh-010"></a>

## QB-BEH-010 — Explain an AI-assisted mistake

| Field | Value |
|---|---|
| Category | Behavioral / AI collaboration |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Technical ownership and learning. |
| Related IDs | QB-BEH-011 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A coding assistant produced a plausible change that passed the sample but violated an edge case. Describe how you would take responsibility, correct it and explain the tool’s role.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Reproduce the failure, identify the first incorrect assumption and inspect the generated change against the actual contract. Add a meaningful regression check, correct the implementation and review related paths. Explain where AI helped and what you personally verified. In a real account, report actual outcomes and any remaining gap; do not describe unchecked output as independent expertise.

**Explanation:** The tool is part of the workflow, but responsibility for accepting its output remains observable. The lesson should be more specific than “I will review AI better”: derive tests from the invariant or require evidence for a generated performance claim. If the assessment itself restricts AI, that policy is a separate requirement and cannot be excused by later verification.

**Followups:** What could the regression test still miss? Could you explain the corrected method without the assistant?

**Common mistakes:** Blaming the tool entirely, concealing its use or treating a passing sample as comprehensive proof.

</details>

<a id="qb-beh-011"></a>

## QB-BEH-011 — Respond to a changed acceptance criterion

| Field | Value |
|---|---|
| Category | Behavioral / Adaptation |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Adaptability and requirement ownership. |
| Related IDs | QB-BEH-012 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

Halfway through a feature, a stakeholder reveals that your original interpretation is wrong. How do you establish what changes and communicate the impact?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Ask for a concrete example of the desired behavior, identify which agreed criterion changed or was ambiguous, and trace the effect on contracts, tests, data and schedule. Offer options for revised scope and confirm the decision. Preserve useful existing work where compatible and explain any migration or rollback concern. Avoid assigning blame before understanding how the misunderstanding arose.

**Explanation:** The answer should show a feedback loop: new evidence changes the plan, and the revised plan becomes testable. If describing actual experience, distinguish your assumptions from what the stakeholder explicitly approved. A revised schedule is an estimate under stated constraints, not a guaranteed delivery promise. The result can include an imperfect compromise if that is what really happened.

**Followups:** What if the requested change breaks an existing client? Which acceptance test would you write first?

**Common mistakes:** Patching the UI while ignoring API/data semantics, or saying “I am flexible” without a concrete decision.

</details>

<a id="qb-beh-012"></a>

## QB-BEH-012 — Give a useful retrospective

| Field | Value |
|---|---|
| Category | Behavioral / Reflection |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Behavioral/project discussion |
| Experience | 0–2 years; scenario depth adjustable |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Outcome interpretation and corrective action. |
| Related IDs | QB-BEH-001 |
| Sources | [STAR and reflection](../10-Behavioral-and-Communication/STAR_Method.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — communication framework only, not these prompts |

### Prompt

A project met its delivery date but required repeated manual fixes afterward. How would you discuss success and failure in a retrospective without exaggeration or blame?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Separate the delivered scope from the later operational cost, identify supported contributing decisions and describe which parts you owned. Compare the acceptance criteria to actual user/operational outcomes. Propose one concrete corrective action with an owner and verification, while acknowledging uncertain causes. Credit useful work without presenting the date alone as complete success.

**Explanation:** A useful retrospective does not need a flawless ending. If manual intervention was not measured, state the observed incidents rather than inventing a rate. Explain how the next project would change its acceptance/recovery checks. A behavior such as documenting retry/reconciliation ownership is more concrete than promising to communicate more.

**Followups:** Which action is most valuable under limited time? What evidence would show the change helped?

**Common mistakes:** Equating on-time delivery with reliability, blaming another team or selecting improvements without a check.

</details>

## Verification and practice record

Answers were reviewed as reasoning guidance. Unless a record links executable evidence, no runtime, live interview, security audit or production validation is implied. Record the ID, assumptions, hints, evidence and next variation in the progress tracker. Related questions connect decisions; they are not duplicates or guaranteed employer followups.

**Last verified:** 2026-11-02. Primary sources support the underlying concepts; prompts and answer structures are original. [Complete timed role mocks](../13-Mock-Interviews/README.md) are delivered in Phase 6. Editorial status rechecked **2026-11-03**; the source-inspection date above is unchanged.
