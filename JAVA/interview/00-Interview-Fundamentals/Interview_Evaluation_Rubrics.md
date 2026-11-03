# Practice evaluation rubrics

## Scope and scoring

These [INFERRED] rubrics support self-assessment and mock feedback. They are not leaked employer scorecards and cannot predict an offer. Score each dimension separately; do not average away a correctness or security defect. “Not observed” is different from 0.

Use **0** for no usable evidence on an attempted dimension, **1** for substantial help or a serious unresolved error, **2** for a mostly sound familiar solution with prompting or limited checks, **3** for independent correct reasoning on a new task with relevant checks, and **4** for repeated strong evidence across variations and difficult followups. Mark hints and the task's difficulty/level.

## Coding scorecard

| Dimension | Evidence for level 3 | What blocks that rating |
|---|---|---|
| Understanding | Restates output, constraints and assumptions correctly | Solves a different contract |
| Clarification | Resolves ambiguities that change approach | Assumes duplicate/mutation rules incorrectly |
| Approach/selection | Derives an appropriate method and its preconditions | Names a pattern without a valid argument |
| Correctness | Explains invariant/recurrence and termination | Known counterexample remains |
| Complexity | Names variables, hashing model and stack/output costs | Omits dominant repeated work |
| Edge cases/testing | Tests contract boundaries and a likely bug | Only the provided sample checked |
| Code quality | State and interfaces are readable and coherent | Hidden mutation or contradictory state |
| Communication | Explains decisions and incorporates feedback | Important reasoning remains opaque |

## System-design scorecard

| Dimension | Evidence for level 3 |
|---|---|
| Requirements | Identifies critical journey and measurable targets/assumptions |
| Architecture | Every major component has a stated purpose and boundary |
| Data/API | Ownership, access patterns and invariants are explicit |
| Scale | Units, peak assumptions and bottleneck implications are sound |
| Reliability | Partial failure, overload, retry and recovery are reasoned through |
| Security/privacy | Authentication, authorization, isolation and deletion are in the flow |
| Cost/tradeoffs | Compares credible alternatives under the same assumptions |
| Communication | Walks through the critical flow and identifies open decisions |

## AI-engineering extension

Use only when an AI task is attempted: model/task suitability, retrieval and permissions, valid evaluation, error diagnosis, latency/token/cost budgets, tool safety, and workflow recovery. A functional demo alone does not prove acceptable answer quality or production readiness. [Phase 6](../13-Mock-Interviews/Interview_Evaluation_Rubric.md) maps these areas to a shared score scale with task-specific AI/ML/RAG anchors in separate interviewer guides.

## Behavioral scorecard

Assess clear context, personal ownership, reasoning, collaboration, outcome evidence and reflection. Level 3 requires a specific truthful episode with observable action and learning; it does not require a heroic success. A fabricated detail invalidates that evidence rather than earning points for polish.

## Review record

For each dimension write score, evidence, limitation and next exercise. Select one or two improvements. Do not assign a company hiring decision such as “strong hire” from an uncalibrated self-score.

## Calibration practice

Two reviewers score the same attempt independently, then compare the exact behavior behind each rating. If they disagree, revise the evidence description before negotiating a number. A changed rubric needs a version/date so later comparisons remain meaningful.

## Checklist

- [ ] Score is tied to observable behavior and hints.
- [ ] Unobserved dimensions remain unassessed.
- [ ] Critical errors remain explicit even when other dimensions are strong.

## References

- [Microsoft technical guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing) — publicly described evaluation areas, not this score scale.
- [Progress tracker](../PROGRESS_TRACKER.md).

## Phase 6 administration

Use [the common mock rubric](../13-Mock-Interviews/Interview_Evaluation_Rubric.md), [blank scorecard](../13-Mock-Interviews/Mock_Scorecard.md) and [calibration exercises](../13-Mock-Interviews/Calibration_Exercises.md) for complete sessions. The same 0–4 meanings apply; Not assessed stays distinct from zero, hints stay visible and a single attempt does not establish repeated level-4 evidence.

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
