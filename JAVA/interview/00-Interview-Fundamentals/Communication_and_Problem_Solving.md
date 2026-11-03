# Communication and problem solving

## Speak at decision points

The listener needs to follow your model of the problem. Explain what information matters, which alternatives exist and why the chosen step follows. Continuous narration of syntax adds noise. Long unexplained silence hides whether you are reasoning or stuck.

## Four useful communication moves

| Move | Example | Purpose |
|---|---|---|
| Clarify | “Are duplicate values distinct items, and do you want indices?” | Prevents implementing the wrong contract |
| State an assumption | “I will assume input fits in memory unless you want a streaming solution.” | Makes a reversible decision visible |
| Explain an invariant | “After each iteration, the window contains no repeated symbol.” | Gives meaning to state updates |
| Check and revise | “That argument depends on nonnegative values; this negative example breaks it.” | Demonstrates correction using evidence |

These are [INFERRED] practice techniques, not scripts to recite mechanically.

## A worked revision

You propose shrinking a sliding window when its sum exceeds a target. The interviewer says negative numbers are allowed. Instead of patching the code blindly, show why monotonicity fails: a future negative can reduce the sum. Reconsider the problem's exact objective and choose a method whose assumptions hold, such as prefix sums with a map for certain sum-counting tasks. That alternative is not a universal replacement for every window problem.

## Precision in explanations

Say “expected O(n) under ordinary hashing assumptions” rather than “always linear.” Say “the transaction retries on serialization failure” rather than “the database handles all races.” Say “tested on a local fixture” rather than “production ready.” Precision is useful even when the answer is short.

For design, put units on quantities and describe ownership: requests/second, bytes/record, which service writes the authoritative row, and which store is a cache. For behavior, name your action and the actual consequence.

## Recover after a mistake

Name it, explain the smallest counterexample, correct the reasoning and rerun the relevant checks. Do not defend an invalid invariant merely because implementation has begun. Changing your mind for a stated reason is understandable; silently replacing an approach without explanation is harder to assess.

## Practice

Explain a solved problem in three minutes using no code, only contract, invariant, example and complexity. Then explain the same idea to someone unfamiliar with the pattern. A simpler explanation must retain the important precondition.

<details><summary>Review criteria</summary>

The reviewer should be able to restate the contract and why the method works. If only the technique's name is memorable, add the state meaning and a small trace. If the explanation is too long, remove syntax narration before removing the correctness argument.

</details>

## Checklist

- [ ] Assumptions are visible and revisable.
- [ ] I explain why an update is valid.
- [ ] Corrections are explicit and checked.

## References

- [Microsoft technical guidance](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing).
- [Coding methodology](../03-DSA-and-Coding/Coding_Interview_Methodology.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
