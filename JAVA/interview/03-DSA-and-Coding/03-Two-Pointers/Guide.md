# Two pointers

## What this pattern solves

Two positions move through a sequence while preserving a provable set of remaining candidates. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Sorted pair-sum, in-place compaction, palindrome comparison and fast/slow list traversal are different pointer invariants. Name which one applies.

**Counterexample to overusing the pattern:** On an unsorted array, increasing the left pointer after a low sum does not safely discard candidates. Sorting may change index semantics and costs O(n log n).

## Worked trace

Target 9, sorted `[1,2,4,7,11]`:

| Left/right values | Sum | Safe move |
|---|---:|---|
| 1 / 11 | 12 | Drop right |
| 1 / 7 | 8 | Drop left |
| 2 / 7 | 9 | Return indices 1,3 |

## From brute force to a correct optimization

If a[left]+a[right] is too small, every pair using this left and a no-larger right is also too small. Discard left. If it is too large, every pair using this right and a no-smaller left is also too large. Discard right. Each move eliminates only impossible pairs, so a valid pair remains until found. `left<right` enforces distinct indices.

## Time and space

Brute force checks O(n²) pairs with O(1) space. For already-sorted input, opposing pointers use O(n) time/O(1) auxiliary space; the constant-sized return array is output. Sorting first adds O(n log n) time and may require storage for original indices. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test length 0/1, duplicates, negative values, no match and extreme ints. Cast before adding to avoid overflow. The example assumes sorted input rather than silently checking it; validating order would add O(n), still leaving total O(n).

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static int[] sortedPair(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        int left = 0, right = a.length - 1;
        while (left < right) {
            long sum = (long) a[left] + a[right];
            if (sum == target) return new int[]{left, right};
            if (sum < target) left++;
            else right--;
        }
        return new int[]{-1, -1};
    }
}
```

**Contract:** Requires nondecreasing input, which is not checked by this method. Returns any matching distinct indices or {-1,-1}; does not mutate the array.

## Practice: attempt before expanding answers

### DSA-03-01: Compact a sorted array to one copy of each value and return its logical length.

| Field | Value |
|---|---|
| Domain/topic | Coding / Two pointers |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-03-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Maintain a write index after the unique prefix; scan once and overwrite only when the next value differs.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary space. The prefix before write is unique and corresponds exactly to distinct values scanned so far.

**Follow-up:** How do you retain at most two duplicates?

**Common mistake:** Treating stale tail cells as part of the logical result.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-03-02: Find two distinct indices with a target sum in nondecreasing input.

| Field | Value |
|---|---|
| Domain/topic | Coding / Two pointers |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-03-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Move opposing pointers using sorted-order elimination and long addition.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary space for already-sorted input; output has constant size. Cast before adding.

**Follow-up:** How do you return all unique value pairs?

**Common mistake:** Applying the elimination rule to unsorted input.

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
