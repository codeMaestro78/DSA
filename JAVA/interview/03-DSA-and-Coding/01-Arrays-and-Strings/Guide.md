# Arrays and strings

## What this pattern solves

Index-based sequences; prefix summaries and contiguous-subarray state. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Many queries over an unchanged sequence suggest paying one preprocessing cost. An optimum ending at the current position suggests a recurrence rather than enumerating all starts.

**Counterexample to overusing the pattern:** A substring is contiguous; a subsequence may skip positions. A fixed-size frequency array is valid only when the alphabet is bounded by the contract.

## Worked trace

For maximum nonempty subarray on `[-2, 3, -1, 4, -5]`:

| Value | Best sum ending here | Best seen |
|---|---:|---:|
| -2 | -2 | -2 |
| 3 | 3 | 3 |
| -1 | 2 | 3 |
| 4 | 6 | 6 |
| -5 | 1 | 6 |

## From brute force to a correct optimization

Every nonempty subarray ending at index i either starts at i or extends a subarray ending at i−1. Therefore `ending = max(a[i], oldEnding + a[i])`. Taking the best of all ending positions covers every nonempty interval. Initialize from the first element so all-negative arrays work. Prefix sums solve a different task: with `prefix[0]=0`, sum on `[l,r)` is `prefix[r]-prefix[l]`.

## Time and space

Enumerating all intervals while maintaining each running sum is O(n²) time, O(1) auxiliary space. Re-summing every interval is O(n³). Maximum-subarray recurrence is O(n) time and O(1) auxiliary space. Prefix preprocessing is O(n) time/space and each range-sum query O(1). Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Empty maximum-subarray input is rejected here; an alternative “empty allowed” contract gives a different answer for negatives. Use long before addition; for Java int arrays, long suffices for sums of all elements within int-indexable lengths. Strings use UTF-16 units unless another representation is selected. Do not confuse subsequence and substring.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static long maxSubarray(int[] a) {
        Objects.requireNonNull(a, "array");
        if (a.length == 0) throw new IllegalArgumentException("nonempty array required");
        long ending = a[0], best = ending;
        for (int i = 1; i < a.length; i++) {
            ending = Math.max((long) a[i], ending + a[i]);
            best = Math.max(best, ending);
        }
        return best;
    }
}
```

**Contract:** Requires at least one element; empty input throws IllegalArgumentException. Returns long; the array is not mutated.

## Practice: attempt before expanding answers

### DSA-01-01: Answer many `[l,r)` range-sum queries over a fixed int array.

| Field | Value |
|---|---|
| Domain/topic | Coding / Arrays and strings |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-01-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Build n+1 long prefixes; validate 0≤l≤r≤n and subtract two prefixes.

**Complexity and boundary reasoning:** O(n) preprocessing time/space and O(1) per valid query; the n+1 prefix representation also handles empty ranges.

**Follow-up:** How would point updates change the structure?

**Common mistake:** Recomputing a sum for every query.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-01-02: Return the maximum sum of a nonempty contiguous subarray.

| Field | Value |
|---|---|
| Domain/topic | Coding / Arrays and strings |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-01-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use the ending-here recurrence and an all-negative initialization.

**Complexity and boundary reasoning:** O(n) time and O(1) auxiliary space for the sum only; preserve a start candidate and an explicit tie rule if returning indices.

**Follow-up:** How would you return the interval with a tie-breaking rule?

**Common mistake:** Initializing the best sum to zero.

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
