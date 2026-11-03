# Dynamic programming

## What this pattern solves

Reuse solutions of overlapping subproblems whose state captures all information needed for future decisions. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Write the recursive meaning and dependency graph before choosing memoization or tabulation. DP is appropriate when many branches reach equivalent states.

**Counterexample to overusing the pattern:** A recurrence is wrong if its state omits relevant history. Polynomial in a numeric amount can be exponential in that amount’s bit-length (pseudopolynomial).

## Worked trace

Unlimited positive coins `[1,3,4]`, amount 6:

| Amount | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---:|---:|---:|---:|---:|---:|---:|
| Minimum coins | 0 | 1 | 2 | 1 | 1 | 2 | 2 |

Greedy largest-first gives 4+1+1 (3 coins); DP finds 3+3 (2 coins).

## From brute force to a correct optimization

Let dp[x] be the minimum number of coins forming exactly x, or unreachable. dp[0]=0. For each positive coin c≤x, a solution ending in c consists of an optimal solution for x−c plus one coin. Minimize across these choices. Positive coins make dependencies strictly smaller, so ascending x is valid. A sentinel greater than amount is safe because a feasible solution needs at most amount positive integer coins; guard unreachable values before adding.

## Time and space

Naive recursion can branch exponentially. For the shown tabulation with amount A and m denominations, input validation costs O(m), allocation/initialization costs O(A+1), and the nested recurrence costs O(A·m). Together this is O(m+A+A·m+1), equivalently O((A+1)·(m+1)), time and O(A+1) auxiliary space. When A≥1 and m≥1, the usual simplification is O(A·m) time/O(A) space. At A=0, this method still validates all m coins; at m=0, it still initializes A+1 states. This is pseudopolynomial in A; it is not polynomial in log A. Actual allocation requires an amount that fits available memory. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test amount 0, no coins, unreachable targets, repeated denominations and invalid zero/negative coins. An unbounded coin problem differs from using each input coin at most once. Loop direction matters in one-dimensional 0/1 knapsack; a memorized template can accidentally allow reuse.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static int minCoins(int[] coins, int amount) {
        Objects.requireNonNull(coins, "coins");
        if (amount < 0 || amount == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("amount must be in 0..MAX_VALUE-1");
        }
        for (int coin : coins) {
            if (coin <= 0) throw new IllegalArgumentException("positive coins required");
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (coin <= value && dp[value - coin] <= amount) {
                    dp[value] = Math.min(dp[value], dp[value - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

**Contract:** Coins must be strictly positive; amount must be 0 through Integer.MAX_VALUE−1 and small enough for the available memory. Invalid numeric arguments throw IllegalArgumentException; an infeasible allocation can still fail. Time/space scale with the numeric amount, so practical constraints are essential.

## Practice: attempt before expanding answers

### DSA-14-01: For nonnegative house rewards, maximize reward without selecting adjacent houses.

| Field | Value |
|---|---|
| Domain/topic | Coding / Dynamic programming |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-14-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Let best[i]=max(best[i−1],reward[i]+best[i−2]); define empty prefix as zero.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary state for reward only. Reconstructing selected indices can retain O(n) decisions or recompute with a stated tradeoff.

**Follow-up:** How do you reconstruct selected indices?

**Common mistake:** Updating a rolling state before saving the older dependency.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-14-02: Return the minimum coins for an amount with unlimited positive denominations, or -1.

| Field | Value |
|---|---|
| Domain/topic | Coding / Dynamic programming |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-14-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use exact-amount DP with unreachable states and smaller-amount dependencies.

**Complexity and boundary reasoning:** O((A+1)·(m+1)) time/O(A+1) space including coin validation and DP initialization; only when both A≥1 and m≥1 does this simplify to O(A·m) time/O(A) space. A=0 still requires O(m) validation. The numeric amount A makes it pseudopolynomial.

**Follow-up:** Why does greedy fail on [1,3,4] for 6?

**Common mistake:** Treating the first reachable solution as optimal or allowing zero coins.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

## Readiness checklist

- [ ] Explain the recognition cue and a case where this pattern is inappropriate.
- [ ] Reproduce the trace from a blank page and state the invariant or recurrence.
- [ ] Derive time/space bounds and state output, hashing and recursion assumptions.
- [ ] Try an empty/boundary case and a counterexample to the common mistake.
- [ ] Solve one unfamiliar variation independently, then record hints and test evidence.

## References and verification

- [MIT 6.006 lecture notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) — foundational algorithm structures; this teaching chapter is an original synthesis, not an interview-frequency claim.
- [Java Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html) — array sorting/search contracts; [HashMap API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html) — map behavior. Consult only the APIs relevant to the implementation.
- [Phase 2 source metadata](../PHASE2_SOURCES.md) and [validation instructions](../../validation/dsa/README.md).

**Last verified:** 2026-11-02. Explanation and worked trace reviewed; executable validation covers only methods explicitly included in the linked Java suite. The rest of this chapter is reasoning/practice material, not tested code.
