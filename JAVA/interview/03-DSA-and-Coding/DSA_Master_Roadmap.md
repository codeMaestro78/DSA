# DSA master roadmap

## Goal and assumptions

[INFERRED] Prepare to solve unfamiliar early-career coding problems with a clear contract, a defensible algorithm and tests. Java is the default practice language because this repository already contains Java work; this is not a claim that employers require Java. Use one language fluently before dividing practice across several.

Study [complexity](Complexity_Analysis.md), [the solving framework](Problem_Solving_Framework.md) and [coding methodology](Coding_Interview_Methodology.md) first. “I recognize the template” is not a readiness gate. Independent reasoning on a changed constraint is stronger evidence.

## Sequence and dependencies

| Stage | Topics | Gate |
|---|---|---|
| A | Arrays/strings, hashing, sorting, long arithmetic and Java collections | Explain a prefix-state invariant and a counterexample to int arithmetic |
| B | Two pointers, windows, binary search | Prove which candidates a boundary move discards; state a case where the technique fails |
| C | Lists, stacks/queues, trees/BSTs, heaps | Preserve pointer identity, account for recursion/queue memory, distinguish heap from sorted order |
| D | Graph representation, BFS/DFS, topo order, connectivity | Choose traversal using direction/weights; trace a cycle and unreachable vertex |
| E | Backtracking, introductory DP, greedy, intervals | Define state, recurrence or exchange argument; explain why a tempting alternative fails |
| F | Tries, bit operations, number theory, advanced algorithms | Optional extension after A–E, selected from a target role or repeated weak area |
| Adjacent | SQL and concurrency | Separate contracts, null/multiplicity or interleaving reasoning; do not treat them as ordinary array templates |

Phase 2 delivers focused guides for A–E; Phase 7 adds complete teaching guides for F and the adjacent SQL/concurrency areas. All 22 pattern directories now have authored guides. SQL fundamentals remain canonical in [database preparation](../05-Database-and-Backend/README.md); new SQL/threading exercises are reviewed reasoning rather than native or concurrent executions.

## A repeatable study unit

1. Attempt one problem for 20–30 minutes to discover the actual gap. This is an editorial learning budget, not an employer duration.
2. Read the guide and explain its invariant without code.
3. Trace the worked example and change one constraint: allow negatives, require original indices, allow duplicates or change endpoint semantics.
4. Implement from a blank file. Test boundaries, typical input and a counterexample to your first incorrect idea.
5. Compare with the expected reasoning only after the attempt. Record hints and the first wrong decision.
6. Re-solve after a delay and attempt a fresh variant. Adjust review intervals to your error rate.

Start with two questions per delivered pattern using [the index](DSA_Question_Index.md). The original 32 core exercises plus 12 Phase7 extension exercises provide 44 distinct diagnostic prompts. Use [the 22-problem public catalog](PUBLIC_PROBLEM_CATALOG.md) for transfer; no finite list covers every possible interview problem.

## Readiness and prioritization

You are ready to advance from a pattern when you can state its preconditions, derive the algorithm, explain time/space, implement it and test a variant independently. A timeout can mean a missing invariant, API friction or implementation bugs; classify the cause before doing more of the same.

If tree recursion remains unclear, do not jump to advanced tree DP. If binary search boundaries fail repeatedly, practice lower bound before searching capacities. If DP state is vague, return to the recursive decision tree before optimizing storage.

## References and last verified

[MIT algorithm notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) support the foundational topic sequence; the interview priorities above are editorial. [Sources](PHASE2_SOURCES.md), [master preparation roadmap](../MASTER_INTERVIEW_ROADMAP.md).

Last reviewed: 2026-11-03; Phase 2 foundations and Phase 7 extensions reconciled; candidate skill remains unassessed.
