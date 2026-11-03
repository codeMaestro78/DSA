# Phase 5 practical question-bank study guide

Phase 5 adds **134 original questions across 13 banks**, plus a separately sourced catalog of **22 public DSA/SQL/concurrency problems**. The earlier 32 original DSA exercises remain available. Questions target distinct contracts and reasoning failures; this is a curated practice set rather than an exhaustive list of possible interviews.

## First session

1. Choose a primary role using [the role comparison](02-Role-Based-Preparation/Role_Comparison.md). Read the corresponding foundation chapters before attempting unfamiliar mechanisms.
2. Open [the question index](12-Interview-Question-Bank/Question_Bank_Index.md). Choose one Beginner or Intermediate question and keep its answer closed.
3. Write assumptions, an approach and a concrete worked example. For code, define empty/invalid/mutation/numeric behavior and test boundaries. For design, trace one request and one failure. For experience, use a real example or explicitly identify a hypothetical response.
4. Open the answer. Compare the mechanism and limitations, not just keywords. A different answer can be valid if it meets the prompt's contract and explains its tradeoffs.
5. Record ID, elapsed time, hints, evidence and one specific correction in [the practice ledger](PROGRESS_TRACKER.md). Attempt a related question later without reading its answer first.

Suggested first session: [SDE-003](12-Interview-Question-Bank/SDE_Questions.md#qb-sde-003), [DB-001](12-Interview-Question-Bank/Database_Questions.md#qb-db-001) and [PROJ-001](11-Project-Deep-Dive/Project_Question_Bank.md#qb-proj-001). Choose fewer if prerequisites are missing; the session is practice, not a hiring threshold.

## Routes through the banks

| Goal | Sequence |
|---|---|
| SDE/backend | [Public problems](03-DSA-and-Coding/PUBLIC_PROBLEM_CATALOG.md) and [core DSA](03-DSA-and-Coding/DSA_Question_Index.md) → [SDE](12-Interview-Question-Bank/SDE_Questions.md) → [database](12-Interview-Question-Bank/Database_Questions.md) → [system design](12-Interview-Question-Bank/System_Design_Questions.md) |
| AI engineer | SDE core → [AI engineering](12-Interview-Question-Bank/AI_Engineer_Questions.md) → [RAG](12-Interview-Question-Bank/RAG_Questions.md) → relevant [AI/ML foundations](12-Interview-Question-Bank/AI_ML_Questions.md) |
| Full-stack AI | SDE and API core → [full-stack AI](12-Interview-Question-Bank/Full_Stack_AI_Questions.md) → AI/RAG → selected database and operations failures |
| ML engineer | [AI/ML foundations](12-Interview-Question-Bank/AI_ML_Questions.md) → [ML engineering](12-Interview-Question-Bank/ML_Engineer_Questions.md) → selected deployment/system questions |
| FDE | [FDE](12-Interview-Question-Bank/FDE_Questions.md) → API/database integration → AI/RAG evaluation as relevant → project and behavioral evidence |
| Platform/infrastructure | [system design](12-Interview-Question-Bank/System_Design_Questions.md) → [cloud/DevOps](12-Interview-Question-Bank/Cloud_DevOps_Questions.md) → database concurrency |
| Every role | [behavioral](12-Interview-Question-Bank/Behavioral_Questions.md) and [project questions](11-Project-Deep-Dive/Project_Question_Bank.md) using truthful experience |

Use [advanced questions](12-Interview-Question-Bank/Advanced_Questions.md) after the underlying mechanisms are clear. **Expert is optional specialist depth**, not a requirement for every 0–2-year role. Beginner/Intermediate/Advanced/Expert are editorial labels on original questions; public problems retain their separately verified platform Easy/Medium/Hard ratings. Those scales are not equivalent or measured employer difficulty distributions.

## Review one answer consistently

Use the existing [0–4 rubric](00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) and [progress tracker](PROGRESS_TRACKER.md). Assess contract understanding, correctness/mechanism, concrete evidence, and communication/limitations separately. Record unattempted dimensions as **not assessed**, not zero. A correct term without a worked explanation is weak evidence; a partial solution with a clearly localized gap is useful practice evidence. Do not turn a score into an offer prediction.

Suggested followup chain: explain the base case → change one assumption → identify a failure or counterexample → propose a check → state what that check cannot establish. The question-specific followups provide the changes. Full timed role simulations and their scoring packages remain **Phase 6**.

## IDs, sources and company tailoring

`QB-*` denotes the 134 new original records. `PUB-01`–`PUB-22` denotes externally hosted public statements; the catalog supplies solution direction and edge cases rather than copied statements or verified platform submissions. Earlier DSA IDs remain in their original index and are not counted again. Use category/role/difficulty/round/experience columns or [JSON metadata](12-Interview-Question-Bank/question_index.json) for selection.

The [company preparation map](12-Interview-Question-Bank/Company_Preparation_Map.md) connects scoped dossiers to conditional routes. It supplies no attributed employer question bank: the evidence does not justify one. Sources explain mechanisms or published guidance; they do not corroborate invented scenarios or candidate experience. Refresh the exact posting and assistance rules before an assessment.

## What was checked

[Phase 5 QA](PHASE_5_QA_REPORT.md) separates executed Java/Python/NumPy examples and synthetic arithmetic from reviewed, unexecuted SQL and conceptual answers. Source inspection, compilation, unit/differential checks and production evidence are distinct. No cloud deployment, hosted-model evaluation, browser integration, concurrency implementation proof or hidden-platform test result is claimed. Your existing Java solutions were left untouched.

[Phase 5 delivery](PHASE_5_REPORT.md) · [Question-bank directory](12-Interview-Question-Bank/README.md) · [Validation instructions](validation/README.md).

Last integrated: **2026-11-03 IST**; source-review cutoff **2026-11-02**.
