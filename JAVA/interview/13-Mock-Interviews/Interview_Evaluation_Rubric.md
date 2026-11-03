# Mock interview evaluation rubric

**Version 1.0 — 2026-11-03.** This is an original **[INFERRED]**, company-neutral practice instrument for all nine Phase 6 sessions. It is not an employer scorecard, a validated selection test or an offer predictor. Keep the same six dimensions and scale across roles, then use the companion packet's task-specific evidence. No attempt has been scored on your behalf.

## Score the evidence, not the impression

| Score | Meaning | Evidence needed |
|---|---|---|
| Not assessed | The dimension was not attempted or sufficiently observed | Explain why; do not enter zero or invent a numeric equivalent |
| 0 | Attempted, with no usable evidence on that dimension | Identify the actual attempted behavior and unresolved gap |
| 1 | Major assistance needed or a serious error remains | Record the hint/error and its consequence; confidence or fluency cannot repair it |
| 2 | Substantially sound familiar reasoning with prompting, or incomplete independent checks | Identify what was supplied and what the candidate completed themselves |
| 3 | Independent, correct reasoning on the new task with relevant checks and stated limits | Record the contract, mechanism and concrete evidence; a different valid approach can earn the same rating |
| 4 | Sustained level-3 performance across independently attempted variations and difficult followups | Cite multiple attempts/variations and dates; one polished response is insufficient |

A single session usually supports **0–3 or Not assessed**, plus a note of evidence toward 4. Repeated attempts at a memorized solution are not independent transfer evidence. Use the [scorecard](Mock_Scorecard.md) to preserve the task, hint history, revision and limitation. These ratings are ordinal judgments; a difference of one point is not a calibrated quantity of skill.

## Six dimensions shared by every mock

| Dimension ID | What is observed | General evidence for 3 | A gap that prevents 3 on this dimension |
|---|---|---|---|
| CONTRACT | Problem understanding and material clarifications | Restates users/output, constraints, exclusions and assumptions; distinguishes known facts from choices | Solves a different problem or leaves a material ambiguity silently assumed |
| REASONING | Choice of method and tradeoffs | Derives a suitable mechanism and explains why a simpler or competing choice fits worse under these inputs | Names technologies/patterns without connecting them to constraints |
| CORRECTNESS | Whether the proposed answer satisfies the contract | Establishes an invariant, valid calculation, coherent state transition or evidence-supported conclusion | A known counterexample, invalid arithmetic, leakage or contradictory state remains |
| VALIDATION | Quality and limits of checks | Checks a normal case and a likely failure/boundary with an oracle, trace or discriminating experiment; states what actually ran | Only repeats the happy-path example or reports an unexecuted check as executed |
| RISK | Relevant failure, authority and operational boundaries | Identifies the material failure/exposure, names a prevention/recovery mechanism and its limits | Ignores a core duplicate effect, unauthorized access, data loss or unjustified evaluation claim |
| COMMUNICATION | Clarity, collaboration and honest evidence | Explains decisions plainly, responds to clarification and separates own work, assumptions and uncertainty | Conceals hints/unknowns, fabricates an outcome or cannot explain a key decision |

Score dimensions separately. A security failure can affect CORRECTNESS and RISK if both are actually contradicted; explain each observation rather than mechanically copying a penalty to every row. Good communication can coexist with incorrect code, and correct code can coexist with unclear reasoning. Neither cancels the other.

## Role-specific evidence to retain

These mappings make the broader preparation rubrics explicit without adding incompatible score scales. The companion's concrete anchors govern the individual task.

| Practice family | Required observations | Shared dimensions |
|---|---|---|
| Coding | Problem understanding, clarifying questions, approach, algorithm selection and preconditions | CONTRACT, REASONING |
| Coding | Correctness/invariant, numeric/mutation behavior, readable state/interfaces | CORRECTNESS |
| Coding | Time/space variables, edge cases, tests and code review | REASONING, VALIDATION |
| Coding | Relevant concurrency/exception/resource boundaries and explanation | RISK, COMMUNICATION |
| System design | Requirements, measurable quality targets and scope | CONTRACT |
| System design | Architecture, scalability estimates, tradeoffs and cost drivers | REASONING |
| System design | Data model/invariants, API contract and complete critical flow | CORRECTNESS |
| System design | Failure traces, recovery/restore checks and detection | VALIDATION, RISK |
| System design | Reliability, security, access/deletion and operational ownership | RISK, COMMUNICATION |
| AI engineering | AI fundamentals, model/task suitability and a baseline | CONTRACT, REASONING |
| AI engineering | Architecture, retrieval semantics and output/tool authority | CORRECTNESS, RISK |
| AI engineering | Valid evaluation population/split, error localization and uncertainty | VALIDATION |
| AI engineering | Reliability, latency/cost accounting and production-readiness evidence | REASONING, RISK, VALIDATION |
| Behavioral/project | Specific context, personal ownership, problem-solving and outcome evidence | CONTRACT, CORRECTNESS, VALIDATION |
| Behavioral/project | Collaboration, learning, leadership at the actual level and customer impact | REASONING, RISK, COMMUNICATION |

Leadership can mean surfacing a problem, helping a teammate or making a responsible handoff; it does not require a senior title. A real incident with a poor outcome can provide strong reflection evidence. A hypothetical answer demonstrates proposed reasoning only, not proof of past ownership, delivery or customer impact. Do not turn a fictional roleplay into a candidate accomplishment.

## Hints, interruptions and allowed tools

Use these **[INFERRED]** labels consistently; the guides contain task-specific wording:

- **H0 — clarification:** supplies a stipulated fact or resolves unclear wording. It does not reduce independence.
- **H1 — direction:** draws attention to a relevant constraint without giving the mechanism.
- **H2 — mechanism:** supplies the main structure/invariant or points out the decisive defect.
- **H3 — worked step:** gives a significant solution step, calculation or code fragment.

Record time, wording, task and what happened next. Supplying a crucial mechanism means the candidate did not independently derive that mechanism; other independent evidence can still earn 3. Do not apply a universal subtraction for every hint or cap unrelated dimensions. Optional advanced branches are not mandatory core requirements.

Agree on code execution, references, calculators and AI assistance in preflight. The packet's tool setting is a practice setting, not a company's rule. If tools are changed, log the change and assess assisted explanation separately from an independent baseline. Pause for technical failures or agreed access needs; record active work time and adjusted duration rather than silently comparing unequal conditions. Do not score accent, speaking speed, disability disclosure or unfamiliarity with a particular drawing tool as technical skill.

## Critical findings and next actions

A finding needs a concrete contract violation or evidence gap: for example, cross-tenant output, an unbounded duplicate charge, training on held-out outcomes, loss of acknowledged data contrary to the promise, or a known code counterexample. Record **observed**, **resolved during the attempt**, **unresolved**, or **not exercised**, with the affected task and smallest next check. Treating every hypothetical concern as an observed failure is also incorrect.

Do not average away a critical finding or report a total as “pass,” “hire” or probability of an offer. Choose one or two remediations: revisit a named prerequisite, reproduce the failing trace, implement a small missing guard or retry a changed scenario. Improvement requires a new independent attempt, not just agreement after reading the answer.

## Reviewer calibration

Before scoring, read the candidate contract and companion anchors. Two reviewers can record independent evidence/ratings, then compare disagreements about observable behavior before changing a number. Preserve both original ratings and the reconciled rationale. Changing the contract, hint policy or rubric version makes longitudinal comparisons conditional.

Use [the calibration exercises](Calibration_Exercises.md) before reviewing another person. Their short synthetic excerpts are not real candidate performances, benchmarks or a validated inter-rater reliability study.

## Source scope

OPM describes structured interviews using predetermined questions and common rating standards. That motivates consistent practice administration here; it does not validate this custom scale. [Structured interviews, U.S. Office of Personnel Management](https://www.opm.gov/policy-data-oversight/assessment-and-selection/structured-interviews/).

Microsoft's published technical guidance emphasizes clarification, planning, coding and testing. This is a scoped example of published guidance, not authority for these practice durations, questions or six dimensions. [Microsoft technical interviewing](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing).

Both pages were directly inspected **2026-11-03**; publication/update dates were not established. [Source register](PHASE6_REVIEW_SOURCES.md). [Earlier foundational rubric](../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md). [Mock directory](README.md).
