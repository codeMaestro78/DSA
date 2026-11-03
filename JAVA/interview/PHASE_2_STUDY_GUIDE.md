# Phase 2 study guide

## What this phase is for

Learn to solve and explain core engineering problems before adding advanced AI specialization. Start with the first module you cannot independently demonstrate. Reading a chapter is preparation; readiness requires an attempt on a new task, relevant checks and an explanation under followup.

The sequence and practice budgets are **[INFERRED]**, company-neutral recommendations for your early-career profile. They are not requirements of every employer. Full company dossiers, broad role-specific question banks and complete mock simulations remain later-phase work.

## First session: do this now

1. Read [coding interview fundamentals](00-Interview-Fundamentals/Coding_Interview.md) and [complexity](03-DSA-and-Coding/Complexity_Analysis.md).
2. Attempt the first exercise in [arrays/strings](03-DSA-and-Coding/01-Arrays-and-Strings/Guide.md) before expanding its answer.
3. State the contract, a brute-force baseline, an invariant and the time/space model. Write or trace tests for the exact contract.
4. Record the attempt using [the DSA template](14-Interview-Preparation-Templates/DSA_Problem_Template.md), including hints and unexecuted assumptions.
5. End with a 30-second explanation of one real project using [the project framework](11-Project-Deep-Dive/Project_Explanation_Framework.md).

Do not begin by memorizing every pattern or reading all solutions. Move between explanation, attempt and correction.

## Learning modules

Treat each row as a module that may require several sessions. A starting session budget is 60–90 minutes; repeat or split it according to the readiness gate. These modules do not replace the wider [16-week roadmap](MASTER_INTERVIEW_ROADMAP.md).

| Order | Read and practice | Evidence before moving on |
|---|---|---|
| 1 | Coding fundamentals, complexity and problem-solving framework | Explain a simple solution, its invariant and auxiliary/output space |
| 2 | Arrays, hashing and prefix-state problems | Derive an optimization from repeated work; handle duplicates and numeric bounds |
| 3 | Two pointers and sliding windows | Explain the elimination/valid-window condition and a counterexample to misuse |
| 4 | Binary search, sorting and intervals | State boundary conventions, comparator behavior and endpoint policy |
| 5 | Lists, stacks and queues | Trace mutation/order; test cycle, empty-state and duplicate-value risks |
| 6 | Trees, BSTs and heaps | Distinguish height/width/node count and state heap contents precisely |
| 7 | Graphs and backtracking | State representation/preconditions; explain visited state and undo behavior |
| 8 | Dynamic programming and greedy | Derive state/recurrence or an exchange argument; disprove an invalid shortcut |
| 9 | SQL, normalization and PostgreSQL foundations | Predict exact rows, NULL behavior and constraints for a small fixture |
| 10 | Indexes, plans, transactions and isolation | Explain one concurrent anomaly and a mechanism that enforces its invariant |
| 11 | API design, authentication and authorization | Trace identity, resource permissions, retry and unknown request outcome |
| 12 | Design requirements, capacity and diagrams | Make assumptions explicit and calculate with consistent units |
| 13 | Cache/load balancing/queues/consistency/recovery | Trace one crash and recovery without claiming unsupported exactly-once effects |
| 14 | URL-shortener and notification exercises | Attempt first, then compare tradeoffs and failure behavior with the reference |
| 15 | Behavioral stories and project explanations | Support personal contribution, actual outcome and learning under followup |
| 16 | Mixed retrieval practice and review | Attempt a fresh coding task, SQL explanation and small design without notes |

Behavioral/project practice begins in module 1 and continues alongside technical work; module 15 is a deeper review, not the first time you practice communication.

## Reading entry points

- [DSA roadmap](03-DSA-and-Coding/DSA_Master_Roadmap.md), [recognition](03-DSA-and-Coding/Pattern_Recognition.md), [question index](03-DSA-and-Coding/DSA_Question_Index.md).
- [SQL](05-Database-and-Backend/SQL.md), [indexing](05-Database-and-Backend/Database_Indexing.md), [isolation](05-Database-and-Backend/Isolation_Levels.md).
- [Design framework](04-System-Design/System_Design_Interview_Framework.md), [capacity](04-System-Design/Capacity_Estimation.md), [notification case](04-System-Design/Classic-System-Design/Notification_System.md).
- [Behavioral roadmap](10-Behavioral-and-Communication/Behavioral_Interview_Roadmap.md), [STAR](10-Behavioral-and-Communication/STAR_Method.md), [question bank](10-Behavioral-and-Communication/Behavioral_Question_Bank.md).

## How to use validation artifacts

The [validation index](validation/README.md) explains reproducible checks and their limits. Read a code example's contract before running or copying it. Passing a suite verifies its cases and environment; it does not establish that you can reproduce the reasoning or that every algorithm variation in the curriculum is tested.

The Java examples are isolated educational implementations under `interview/validation/`. Existing practice files elsewhere in the repository are not implicitly audited or changed by this phase.

## Exit gate for the learner

Use [the practice rubrics](00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) and [tracker](PROGRESS_TRACKER.md). Require repeated independent evidence on varied tasks, rather than a fixed problem count or a single successful session. Keep critical correctness, authorization or evidence errors visible even when communication is strong.

If you cannot explain a precondition or reproduce a trace, revisit the relevant module. If a score is unobserved, leave it unassessed. Completing this writing phase does not mark your skills as complete.

## References and last verified

2026-11-02: sequence reviewed against the Phase 2 chapters. Technical and hiring references are attached to those chapters and indexed in [SOURCE_INDEX.md](SOURCE_INDEX.md).
