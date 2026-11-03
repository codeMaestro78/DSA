# Coding expectations and correctness evidence

## Purpose and prerequisites

A coding solution must satisfy a specific input/output contract. Before optimizing, state representation, ordering, duplicate/null/empty behavior, mutation and numeric bounds. A clear baseline supplies both a comparison and an independent oracle for small cases.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../03-DSA-and-Coding/Coding_Interview_Methodology.md) when a mechanism is unfamiliar.

## Working method

Use contract → examples/counterexample → baseline → invariant → implementation → checks → complexity. Ask material clarifications; avoid a checklist recital when the prompt already fixes a fact. If execution is unavailable, explicitly mark code unexecuted and trace it carefully.

## Concrete reasoning example

For lower-bound search on a nondecreasing array, use a half-open interval [lo,hi). A lower-bound search preserves that elements before lo are <target and elements at/after hi are >=target. Every update reduces hi−lo. With [1,3,3,7], lower_bound(3)=1; exact membership is a separate check.

## Tradeoffs and failure boundaries

An accepted sample is not proof. A null policy must match the method; copying input changes space/mutation cost; int overflow can invalidate an otherwise correct comparison. Compare O(n²) interval enumeration with O(n) state only after deriving its precondition. Hash costs are expected, not unconditional constant-time promises.

## Practice — attempt first

### SDE-P7-02-A — Build a test plan

**Difficulty:** Intermediate. **Prompt:** An implementation handles [1,3,3,7], target3. Name checks that distinguish lower bound from arbitrary matching search.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Test target0→0, target8→4, empty→0, all-equal values and a one-element sequence. Verify returned i satisfies every earlier value<target and every later value>=target, rather than merely a[i]==target. Followup: target type/overflow does not alter index safety if midpoint uses lo+(hi−lo)/2.

</details>

### SDE-P7-02-B — Review a dishonest success claim

**Difficulty:** Intermediate with optional stretch. **Prompt:** A candidate changed the method after the last passing run and says all tests pass. What should be recorded?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

The old revision passed; the new revision is unexecuted until rerun. Preserve the exact command/runtime/output and revision. A manual check may provide partial evidence but cannot be upgraded to execution. Followup: which changed boundary needs a focused rerun?

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
