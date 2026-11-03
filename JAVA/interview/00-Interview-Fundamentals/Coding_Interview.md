# Coding interview

## What constitutes a defensible solution

A solution needs a contract, algorithm, correctness argument, complexity analysis and evidence from tests. Syntax alone is insufficient. A memorized pattern without its preconditions can produce a fast wrong answer.

## From problem statement to implementation

1. Restate the output and meaningful constraints. Ask questions that can change the algorithm.
2. Construct one ordinary case and one boundary case. Confirm duplicate and empty-input behavior.
3. Derive a correct baseline. Identify its expensive repeated operation.
4. Choose a data structure or invariant that removes that repeated work.
5. Explain correctness before or while coding: initialization, preservation and termination.
6. Test the contract, then calculate time and auxiliary space with all variables named.

An invariant is a property true at a defined program point. “I use two pointers” names a technique; “all positions before left are already finalized” states a property that can support a proof.

## Worked test design: sorted first occurrence

For a method returning the first index of a target or -1, test empty, one present element, one absent element, duplicates at the beginning/end, all-equal input, and target outside the range. A test for target 2 in `[1,2,2,3]` must expect index 1, not merely any matching index. This difference exposes algorithms that stop at the first midpoint match.

## Complexity without shortcuts

Specify whether n means number of elements, characters, nodes or edges. A hash map's constant lookup is normally an expected-model claim. Recursive DFS uses stack space; an output list consumes space even if auxiliary space is constant. If sorting mutates input, state that. If a comparator subtracts integers, overflow can violate ordering; use a comparison operation designed for the type.

## Communication example

Instead of “This is binary search, O(log n),” say: “The predicate is false and then true over the answer range. I preserve a boundary separating eliminated candidates and shrink the remaining range each iteration.” Explain how bounds change even when duplicates occur.

## Practice and review

Attempt a new list-reordering or tree-traversal variant without reading your existing solution. Trace pointers/state on paper first. This chapter does not assert that the repository's Java solutions are correct or have been run in this phase.

<details><summary>What to review afterward</summary>

Was the first wrong decision a contract misunderstanding, invalid precondition, implementation bug or missing test? Record that decision and a counterexample, then retry a different variation after a delay. An accepted result after reading a solution is learning evidence, not independent mastery.

</details>

## Checklist

- [ ] I can defend correctness independently of passing examples.
- [ ] Every loop/recursive state has a clear meaning.
- [ ] Tests reflect the exact contract and numeric bounds.

## References

- [Microsoft technical guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing) — role-dependent coding/testing guidance.
- [Complexity](../03-DSA-and-Coding/Complexity_Analysis.md).
- [Problem-solving framework](../03-DSA-and-Coding/Problem_Solving_Framework.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
