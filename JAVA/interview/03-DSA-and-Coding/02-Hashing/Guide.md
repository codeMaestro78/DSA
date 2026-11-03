# Hashing and prefix-state lookup

## What this pattern solves

Maps associate a key with a count, index or aggregate; sets retain membership. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Use when a current item needs a previously seen complement or when order is irrelevant but multiplicity matters.

**Counterexample to overusing the pattern:** A hash table does not maintain sorted order. A lookup does not automatically give the count of matching indices; store frequencies when multiplicity matters.

## Worked trace

Target sum 0 in `[1,-1,0]`:

| Prefix after item | Earlier matching prefixes | Cumulative subarrays |
|---|---:|---:|
| 1 | 0 | 0 |
| 0 | 1 | 1 |
| 0 | 2 | 3 |

## From brute force to a correct optimization

Let P[j] be the sum before position j. A subarray `[i,j)` has target sum k exactly when P[i]=P[j]−k. Store counts of earlier prefixes, initially `{0:1}`. Query before inserting the current prefix to exclude an empty subarray. This works with negative numbers because it uses equality rather than a monotonic-window assumption.

## Time and space

Nested-start/end enumeration is O(n²) time and O(1) auxiliary space. Prefix counting uses expected O(n) time under well-dispersed hashing and O(n) space; do not call hash operations unconditional worst-case O(1). HashMap iteration also depends on capacity, not only entry count. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Check all zero values, repeated prefixes, target zero, empty array and sums outside int range. `getOrDefault` distinguishes absent counts without inserting. Mutable keys can break lookup if equality/hash-relevant fields change. HashMap is not a concurrent map.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static long countSum(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        Map<Long, Long> seen = new HashMap<>();
        seen.put(0L, 1L);
        long sum = 0, result = 0;
        for (int value : a) {
            sum += value;
            result += seen.getOrDefault(sum - target, 0L);
            seen.merge(sum, 1L, Long::sum);
        }
        return result;
    }
}
```

**Contract:** Empty input returns 0; target is int and prefix/count arithmetic is long. The array is not mutated.

## Practice: attempt before expanding answers

### DSA-02-01: Given integers, return whether duplicates exist.

| Field | Value |
|---|---|
| Domain/topic | Coding / Hashing and prefix-state lookup |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-02-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use a set and stop when insertion reports an existing element; expected O(n) time, O(n) space.

**Complexity and boundary reasoning:** Expected O(n) time and O(n) entries under well-dispersed hashing; sorting is an O(n log n) alternative when mutation is allowed.

**Follow-up:** What if auxiliary memory is forbidden and mutation is allowed?

**Common mistake:** Using a set when counts are needed.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-02-02: Count nonempty subarrays summing to an int target, allowing negatives.

| Field | Value |
|---|---|
| Domain/topic | Coding / Hashing and prefix-state lookup |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-02-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Count earlier prefix values equal to currentPrefix−target before recording the new prefix.

**Complexity and boundary reasoning:** Expected O(n) time/O(n) entries; long prefixes and a long count avoid int-range errors.

**Follow-up:** Why is the initial zero prefix necessary?

**Common mistake:** Inserting before querying and counting empty subarrays.

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
