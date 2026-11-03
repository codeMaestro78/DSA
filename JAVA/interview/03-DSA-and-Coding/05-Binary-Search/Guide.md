# Binary search

## What this pattern solves

Repeatedly halve a candidate range using an ordered sequence or monotone feasibility predicate. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Ask whether an answer can be expressed as the first true index of `false...false,true...true`. Identify the predicate and boundary convention.

**Counterexample to overusing the pattern:** A nonmonotone predicate such as “value is even” on arbitrary sorted integers cannot be searched this way. Sorted data alone does not make every property monotone.

## Worked trace

Lower bound of 2 in `[1,2,2,4]` with half-open candidate interval:

| low | high | mid | Test | Update |
|---:|---:|---:|---|---|
| 0 | 4 | 2 | 2<2 false | high=2 |
| 0 | 2 | 1 | 2<2 false | high=1 |
| 0 | 1 | 0 | 1<2 true | low=1 |

## From brute force to a correct optimization

Invariant: every index below low holds a value <target, and every index at or above high holds a value ≥target. The unknown interval is `[low,high)`. If a[mid]<target, discard through mid; otherwise retain mid as a possible first answer by setting high=mid. Each step shrinks high−low. When low==high, low is the first qualifying index, possibly n.

## Time and space

Linear scan takes O(n) time/O(1) space. Array lower bound takes O(log n) time/O(1) auxiliary space; input is assumed sorted. Validating order costs O(n). Binary search on the answer costs O(log R × costOfPredicate), not simply O(log n). Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test empty input, all below, all above, repeated target, integer extremes and target absent between values. Use `low+(high-low)/2` with nonnegative int index bounds. Java Arrays.binarySearch does not promise the first duplicate and has a negative insertion-point encoding; do not mix its contract with lower bound.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static int lowerBound(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        int low = 0, high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
```

**Contract:** Requires nondecreasing input, which is not checked. Returns an insertion position in [0,n], including n when no value qualifies.

## Practice: attempt before expanding answers

### DSA-05-01: Return the first index whose sorted value is at least target, or n.

| Field | Value |
|---|---|
| Domain/topic | Coding / Binary search |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-05-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use the half-open lower-bound invariant; the answer can equal the array length.

**Complexity and boundary reasoning:** O(log n) time/O(1) auxiliary space, assuming sorted input. The interval shrinks and low may legitimately finish at n.

**Follow-up:** How do you implement an upper bound?

**Common mistake:** Discarding mid when it is a possible first qualifying value.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-05-02: Find the smallest integer capacity allowing ordered nonnegative workloads to fit within d groups.

| Field | Value |
|---|---|
| Domain/topic | Coding / Binary search |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-05-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Binary search a capacity predicate; greedily count groups for a fixed capacity. Set bounds from max item through total and use long.

**Complexity and boundary reasoning:** O(n log R) time/O(1) extra space when feasibility scans n workloads and R is the inclusive numeric search-range width; account for endpoint arithmetic and d≥1.

**Follow-up:** What assumption makes feasibility monotone?

**Common mistake:** Searching without establishing feasibility or overflow-safe bounds.

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
