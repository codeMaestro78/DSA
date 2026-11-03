# Project deep-dive question bank

## Scope and use

These are original **[INFERRED], company-neutral** practice questions, not reports of employer questions or an unpublished rubric. Attempt the prompt before opening the answer. Difficulty is editorial: Beginner establishes a foundation, Intermediate applies it, Advanced adds interacting failure/measurement constraints, and Expert is an optional specialist extension for this early-career curriculum.

The guidance describes expected reasoning and valid alternatives rather than a single required script. Use real personal evidence for experience questions, or explicitly label a hypothetical answer. No candidate history, metric or production claim is supplied for you to adopt.

<a id="qb-proj-001"></a>

## QB-PROJ-001 — Trace a claim to evidence

| Field | Value |
|---|---|
| Category | Project evidence / Resume evidence |
| Difficulty | Beginner |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Claim verification. |
| Related IDs | QB-PROJ-002 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Choose one actual resume claim. What exactly did you implement, what artifact or observation supports it, and what does that evidence not prove?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Identify the user/problem, your concrete contribution, the environment and the supporting artifact or measurement. Distinguish implementation from tests, a local demo from deployment and team outcomes from personal work. State an unmeasured property rather than supplying a plausible number.

**Explanation:** A repository can establish code presence within inspected scope; a test establishes its cases/environment; a local benchmark describes a workload. None alone proves production quality or user impact. If no artifact can be shared, describe the method and confidentiality limit honestly.

**Followups:** Which phrase in the bullet is strongest? How would you weaken it to match the evidence?

**Common mistakes:** Invented metrics or assuming dependency presence proves expertise.

</details>

<a id="qb-proj-002"></a>

## QB-PROJ-002 — Explain ownership at an integration boundary

| Field | Value |
|---|---|
| Category | Project evidence / Data ownership |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Architecture clarity. |
| Related IDs | QB-PROJ-003 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Walk through a real request in your project. Which component owns identity, business state, async work and final status? If a boundary was only designed, label it.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Trace entry, authentication/authorization, validation, source-of-truth read/write, external dependency and response/final status. Name the owner of each dataset/state transition and the actual transaction boundary. For unimplemented parts, state the proposed behavior rather than speaking as though it ran.

**Explanation:** The interviewer should be able to follow one concrete operation rather than a list of frameworks. A queue or agent library does not establish durable intent, replay or authority. Explain the known implementation and one limit before proposing a larger architecture.

**Followups:** Where could two requests race? What becomes uncertain after a timeout?

**Common mistakes:** Boxes without flows or calling process memory durable state.

</details>

<a id="qb-proj-003"></a>

## QB-PROJ-003 — Defend a rejected alternative

| Field | Value |
|---|---|
| Category | Project evidence / Architecture decisions |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Tradeoff reasoning. |
| Related IDs | QB-PROJ-004 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Describe one real technology or design decision. What alternative did you reject, which constraint mattered, and what evidence would make you change your choice?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Compare two credible choices under the same actual workload/team/feature constraints. Name the accepted downside and the evidence available at the time. If you did not benchmark, explain the reasoning and proposed measurement rather than inventing a speed/cost comparison.

**Explanation:** A defensible decision can be simple and provisional. The point is to connect a requirement to a mechanism and a tradeoff, not prove a favorite tool universally best. Separate later hindsight from facts known when the decision was made.

**Followups:** What bottleneck would justify migration? Which operational cost did you accept?

**Common mistakes:** Comparing unrelated benchmark numbers or claiming every alternative is worse.

</details>

<a id="qb-proj-004"></a>

## QB-PROJ-004 — Separate evaluated quality from a demo

| Field | Value |
|---|---|
| Category | Project evidence / AI project evaluation |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | AI evidence boundary. |
| Related IDs | QB-PROJ-005 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

For an AI project, what labeled cases and failure categories did you actually evaluate? If none existed, explain what the demo showed and design the missing evaluation.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** State task, corpus/version, labels, split/tuning policy and measured outcomes if available. Separate retrieval relevance, answer support, citation alignment, permission correctness and tool/final task success. If only a demo ran, say that and propose representative answerable/unanswerable/adversarial cases with defined criteria.

**Explanation:** Fluency, schema validity and a successful HTTP call are different properties. A proposed evaluation is useful design evidence but not an achieved score. Preserve judge/human-review limitations and do not label synthetic toy checks a production benchmark.

**Followups:** Which failed case taught you most? How would a revoked source affect the evaluation?

**Common mistakes:** Claiming no hallucinations or calling a screenshot an accuracy study.

</details>

<a id="qb-proj-005"></a>

## QB-PROJ-005 — Show how a bug changed the design

| Field | Value |
|---|---|
| Category | Project evidence / Debugging evidence |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Debugging and learning. |
| Related IDs | QB-PROJ-006 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Choose one actual defect. How was it reproduced, what was the first wrong assumption, and which correction and check addressed it?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Describe the observable symptom and minimal reproduction, distinguish hypothesis from confirmed cause and identify the specific implementation or contract correction. Explain a regression check tied to the failure and what remained outside its coverage. Credit collaborators and tools accurately.

**Explanation:** A useful account makes a causal decision visible. It does not need an outage or a heroic recovery; a local boundary bug is valid when labeled. “Added tests” is incomplete unless the tests exercise the failure mode and the fix is explained.

**Followups:** Which nearby case could still fail? What would falsify your cause hypothesis?

**Common mistakes:** Retelling activity without the failing assumption or blaming a tool instead of inspecting accepted changes.

</details>

<a id="qb-proj-006"></a>

## QB-PROJ-006 — Measure an optimization fairly

| Field | Value |
|---|---|
| Category | Project evidence / Performance proof |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Measurement rigor. |
| Related IDs | QB-PROJ-007 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Your project includes a performance claim. Explain baseline, workload, units, environment, variability and correctness checks; if these were not recorded, revise the claim.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Identify comparable before/after conditions, input distribution and resource limits. State whether the metric is mean, percentile, throughput or cost and include failures/quality/permission behavior. Describe sample size and variability when known. If measurement is missing, state the observation limit and a reproducible plan instead of an achieved improvement.

**Explanation:** Cache state, hardware, concurrency and data size can dominate a result. An optimization that drops difficult inputs or changes answer quality is not an equivalent comparison. Explain what would count as a meaningful improvement under the actual requirement.

**Followups:** What if p95 worsened while mean improved? How would you detect a semantic regression?

**Common mistakes:** Mixing cohorts, omitting errors or turning a local run into a universal speedup.

</details>

<a id="qb-proj-007"></a>

## QB-PROJ-007 — Explain production gaps directly

| Field | Value |
|---|---|
| Category | Project evidence / Operations boundary |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Readiness honesty. |
| Related IDs | QB-PROJ-008 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Which properties of your project are actually production-validated, and which are only implemented, locally tested or proposed?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Separate observed deployment/operation from configured mechanisms. Discuss critical journey, authorization, capacity, detection, rollback/restore and dependency failure using actual evidence. Identify the most consequential missing check and the next bounded validation. If no production deployment exists, state that clearly.

**Explanation:** A backup configuration is not a restore drill, and a passing unit suite is not end-to-end or capacity evidence. A prototype can be valuable without all production properties; exaggerating its scope makes followups less credible.

**Followups:** Who operates it? What happens if the provider is slow or a worker restarts?

**Common mistakes:** Saying Docker or cloud hosting alone proves production readiness.

</details>

<a id="qb-proj-008"></a>

## QB-PROJ-008 — Keep a team result attributable

| Field | Value |
|---|---|
| Category | Project evidence / Personal contribution |
| Difficulty | Beginner |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Attribution and collaboration. |
| Related IDs | QB-PROJ-009 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Explain a team project without overstating your contribution. Which part could you defend in implementation detail and which part belonged to others?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Name shared outcome, your responsibility, concrete actions, interfaces with teammates and the evidence you personally checked. Give credit to other owners. When asked beyond your scope, explain the boundary and what you understand from integration rather than pretending authorship.

**Explanation:** Clear attribution does not weaken a project story; it lets the interviewer assess your actual judgment. You can discuss a system-level tradeoff while acknowledging that another teammate implemented or operated a component.

**Followups:** How was the interface agreed? What integration failure did you observe?

**Common mistakes:** Using only “we,” claiming the whole system or inventing another person’s reasoning.

</details>

<a id="qb-proj-009"></a>

## QB-PROJ-009 — Explain versioning and reproducibility

| Field | Value |
|---|---|
| Category | Project evidence / Reproducibility |
| Difficulty | Advanced |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Experiment and artifact lineage. |
| Related IDs | QB-PROJ-010 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Could someone reproduce one important result from your project? Identify the necessary data, code, configuration and model/tool versions, plus any constraints on sharing.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** List the versions and permitted fixtures actually retained, the steps/environment and the expected type of result. Separate deterministic computation from stochastic outputs and record missing information. Preserve privacy/authorization limits; if exact reproduction is impossible, say which claim can still be supported and propose a controlled fixture.

**Explanation:** A seed alone does not guarantee identical behavior across devices, providers or changed external data. A model alias can change without code changes. The answer should describe what exists rather than an ideal artifact registry that was never implemented.

**Followups:** What if the original data must be deleted? Which result depends on a live API?

**Common mistakes:** Claiming bitwise reproducibility from a seed or sharing private data to satisfy the request.

</details>

<a id="qb-proj-010"></a>

## QB-PROJ-010 — Choose the next improvement

| Field | Value |
|---|---|
| Category | Project evidence / Evolution |
| Difficulty | Intermediate |
| Target roles | SDE, AI, ML, full-stack, RAG and FDE |
| Round | Resume/project deep dive |
| Experience | 0–2 years; Advanced questions assess evidence depth rather than job seniority |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Prioritization and evidence-driven evolution. |
| Related IDs | QB-PROJ-001 |
| Sources | [Project explanation framework](Project_Explanation_Framework.md); [AI project followups](AI_Project_Interview.md); [Microsoft public interview guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience communication, not these prompts |

### Prompt

Given your project’s actual constraints and evidence, choose one next improvement. Why that one, how would you check it, and what would you defer?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Choose a demonstrated weakness or material unverified risk, connect it to user impact and propose the smallest useful change/check. State the cost, owner or dependency and a success/failure criterion. Defer attractive additions without supporting requirements.

**Explanation:** The best next step may be measurement, a permission test or a restore check rather than a new model, framework or service. Use the project’s actual stage and available evidence. A proposed improvement remains proposed until implemented and validated.

**Followups:** Which finding would change the priority? How would you stop or roll back the change?

**Common mistakes:** Adding fashionable infrastructure before identifying a need or promising an unmeasured outcome.

</details>

## Verification and practice record

Answers were reviewed as reasoning guidance. Unless a record links executable evidence, no runtime, live interview, security audit or production validation is implied. Record the ID, assumptions, hints, evidence and next variation in the progress tracker. Related questions connect decisions; they are not duplicates or guaranteed employer followups.

**Last verified:** 2026-11-02. Primary sources support the underlying concepts; prompts and answer structures are original. [Complete timed role mocks](../13-Mock-Interviews/README.md) are delivered in Phase 6. Editorial status rechecked **2026-11-03**; the source-inspection date above is unchanged.
