# Phase 6 full mock interview study guide

Phase 6 turns the earlier lessons and question banks into **nine complete, company-neutral practice sessions**, each with four connected stages and a separate interviewer packet. They are original **[INFERRED]** simulations for early-career preparation, not actual employer questions or replicas of a company's loop. A “frontier lab” or “startup” title describes a practice context; it establishes neither eligibility nor a universal hiring format.

## Start without seeing the answer

1. Choose one role in [the mock directory](13-Mock-Interviews/README.md). Read only its **candidate packet**. Close the interviewer companion and validation source files: they contain reference answers.
2. Complete preflight before the timer: check prerequisites, editor/calculator/runtime, allowed references and AI assistance, expected artifacts and any agreed format needs. Practice settings do not override a real invitation's rules.
3. Open the blank [scorecard](13-Mock-Interviews/Mock_Scorecard.md) and record mode, conditions and packet/rubric version. Leave all scores **Not assessed** until something is observed.
4. Follow the packet's four-stage timeline. Explain assumptions and decisions, preserve your work and distinguish code actually executed from manual traces. Ask for clarification when it changes the answer.
5. After the session, read the companion. Compare contracts, mechanisms and evidence; do not require an identical reference architecture or wording. Spend another **10–15 minutes outside the timed session** on scoring and one or two next exercises.

Each packet states its own total duration; these are editorial practice budgets, not employer interview lengths. Preflight/debrief are outside that total. Pauses or changed conditions should be recorded rather than hidden in the timing. Optional stretch branches are for extra evidence when the core is complete; missing them does not automatically fail an early-career core task.

## Session selection

| Session | Intended practice |
|---|---|
| [SDE](13-Mock-Interviews/SDE_Mock_Interview.md) | Clarify a coding contract, implement, test and reason about changed assumptions |
| [AI engineer](13-Mock-Interviews/AI_Engineer_Mock_Interview.md) | Integrate structured model output with deterministic checks, evaluation and failure handling |
| [ML engineer](13-Mock-Interviews/ML_Engineer_Mock_Interview.md) | Define valid data/evaluation, implement numerical analysis and defend experimental conclusions |
| [Full-stack AI](13-Mock-Interviews/Full_Stack_AI_Mock_Interview.md) | Connect client experience to durable server state, access and recovery |
| [FDE](13-Mock-Interviews/FDE_Mock_Interview.md) | Discover a customer problem, scope integration and define evidence of useful delivery |
| [RAG engineer](13-Mock-Interviews/RAG_Engineer_Mock_Interview.md) | Diagnose retrieval/answer failures, calculate metrics and preserve authority/provenance |
| [System design](13-Mock-Interviews/System_Design_Mock_Interview.md) | Build a defensible architecture with explicit workload, data invariants and failure paths |
| [Frontier research engineering](13-Mock-Interviews/Frontier_Lab_Mock_Interview.md) | Inspect experiment evidence, separate competing explanations and propose a discriminating next check |
| [Startup engineer](13-Mock-Interviews/Startup_Mock_Interview.md) | Make scoped product/reliability tradeoffs, investigate an incident and explain priorities |

For the stated SDE/backend plus applied-AI focus, begin with **SDE**, then **AI engineer** or **full-stack AI**, then **system design**. Use the FDE route for customer-facing interests. Move to ML/research sessions after [the ML foundation](07-AI-ML-Deep-Learning/README.md) and relevant [Phase 5 questions](12-Interview-Question-Bank/Question_Bank_Index.md) are clear. This is an editorial sequence, not a prediction of hiring likelihood.

## Pair, solo and assisted modes

**With a peer:** the candidate gets only the candidate packet. The interviewer reads both files beforehand, follows the checkpoint script and supplies stipulated clarifications as H0. Record material hints and branches; avoid adding unannounced requirements. Accept alternatives that meet the contract.

**Solo baseline:** keep companion files closed, write or speak your reasoning, and score afterward against saved artifacts. You cannot simulate unknown interviewer followups after reading them. A reattempt of the same problem measures retention; use a changed input/assumption later to test transfer. Do not equate self-scoring with an independent interview observation.

**AI-assisted practice:** explicitly choose coached or assisted mode and state permitted tools. Give an assistant the candidate packet first and instruct it to ask one question at a time without revealing solutions. If it uses the companion, require it to keep answer/hint material separate and label supplied help. Disclosure cannot be guaranteed by a file layout alone; if an answer is revealed early, mark that stage coached and choose a fresh variant for an independent attempt. No live interview-agent implementation is supplied or tested.

## Score the attempt consistently

Use [rubric v1.0](13-Mock-Interviews/Interview_Evaluation_Rubric.md): CONTRACT, REASONING, CORRECTNESS, VALIDATION, RISK and COMMUNICATION. The same 0–4 meaning applies across roles, with task-specific anchors in every interviewer packet. Most single attempts support 0–3 or Not assessed; **4 requires repeated independent evidence across variations**. No sum, cutoff or “hire” decision is computed.

H0 is factual clarification. H1/H2/H3 progressively supply direction, a mechanism or a worked step. Log what was said and its effect; do not deduct a universal amount or penalize unrelated independent work. A critical defect stays explicit rather than disappearing in an average. A hypothetical roleplay demonstrates proposed reasoning, not a real accomplishment or measured customer impact.

Before reviewing a peer, try [the calibration exercises](13-Mock-Interviews/Calibration_Exercises.md). They teach how to distinguish a corrected answer, independent derivation and unobserved delivery. They are synthetic guidance, not a validated scoring study.

## Review and progression

Save the first failing trace or decision. Select one concrete improvement: clarify the boundary, derive the invariant, fix a leakage source, identify the action owner or design a check that distinguishes two causes. Link the relevant foundation chapter or bank question in the [practice ledger](PROGRESS_TRACKER.md). Choose the next review date based on your schedule; no fixed number of sessions guarantees readiness.

Use [the company evidence](01-Company-Research/Company_Comparison.md) and [conditional preparation map](12-Interview-Question-Bank/Company_Preparation_Map.md) only after choosing the actual posting. Confirm level, format, location and assistance rules. No company labels, universal round counts or confidential prompts are added to the mocks.

## Validation and remaining scope

[Phase 6 QA](PHASE_6_QA_REPORT.md) distinguishes executed reference-code/arithmetic fixtures from reviewed design/protocol/behavioral reasoning. Structural checks can establish links, paired files, task mapping and timing arithmetic; they cannot prove answer truth, realistic predictive scoring or live production behavior. Reading a solution is not evidence of your own independent performance. Existing repository Java files remain untouched.

[Delivery report](PHASE_6_REPORT.md) · [Mock directory](13-Mock-Interviews/README.md) · [Validation instructions](validation/README.md).

Full-library content, freshness and rendering review remains **Phase 7**; unwritten requested technical chapters remain in [the manifest](STRUCTURE_MANIFEST.md). Completing Phase 6 does not silently author those chapters.

Last verified: **2026-11-03**.
