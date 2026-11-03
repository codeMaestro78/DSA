# Technical screen

## Define the format before optimizing for it

A technical screen can be an algorithm task, SQL exercise, debugging session, practical implementation or domain discussion. The name alone does not establish the content. Use recruiter-confirmed instructions; when absent, state the uncertainty.

## A repeatable session workflow

[INFERRED] For a self-imposed 45-minute coding practice, try 5 minutes to clarify and test examples, 8 to derive an approach, 22 to implement, 7 to test and 3 to summarize. Adjust when the task changes. These are practice allocations, not company timings.

Before implementation, establish input/output, mutation, invalid-input policy, constraints and one example. State a brute-force baseline and the property that permits improvement. If you cannot prove the optimization, begin with a correct simple solution and explain its limits.

During implementation, speak at decision points: what state means, why it changes and how termination happens. Reading each line aloud obscures the reasoning. Use the environment to compile/run if permitted; distinguish a passing sample from evidence about the whole input space.

## Worked reasoning example

Prompt: “Find two values with a target sum.” Clarify whether the output is values or indices, whether one answer suffices and whether the input can be changed. Two nested loops provide O(n²) time and O(1) auxiliary space. For original indices, a map of previously seen values gives expected O(n) time and O(n) auxiliary space under a standard hash-table model. Check before inserting to prevent reusing the same index. Numeric range matters for subtraction and addition.

See [hashing](../03-DSA-and-Coding/02-Hashing/README.md) for the implementation lesson; the point here is how the clarification changes the solution.

## When stuck

Say what is established, identify the failing step and request a focused hint if needed: “I can enumerate candidates correctly; I have not yet found a way to avoid rescanning. Is a sorted representation allowed?” Record the hint in practice. Do not silently use a prohibited assistant.

## Practice questions

What should you do if one sample passes but you suspect a boundary bug? What if the time budget is nearly over?

<details><summary>Expected reasoning</summary>

Construct the smallest counterexample and fix the underlying invariant. Near the end, preserve the clearest correct work, state remaining defects and complexity, and describe the next check. Do not claim completion when code is untested or known incorrect.

</details>

## Checklist

- [ ] Contract and constraints clarified.
- [ ] Approach justified, not just named.
- [ ] Boundary tests and complexity explained.
- [ ] Any unfinished work stated precisely.

## References

- [Microsoft technical guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing) — clarification, coding and testing.
- [Coding methodology](../03-DSA-and-Coding/Coding_Interview_Methodology.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
