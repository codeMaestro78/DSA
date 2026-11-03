# Sorting as a transformation

## What this pattern solves

Arrange elements under an explicit total-order contract, often exposing adjacency or monotonicity. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Useful when grouping equal values, merging intervals or establishing greedy order saves repeated work.

**Counterexample to overusing the pattern:** Sorting changes order and may invalidate “original adjacent” or stable-index requirements. Selection can solve some kth-element tasks without fully sorting.

## Worked trace

Stable merge of tagged values `[2a,4]` and `[2b,3]`: compare 2a/2b, take left 2a on equality, then 2b,3,4. Taking right on equality would reverse the original order of equal tagged values.

## From brute force to a correct optimization

Merge sort recursively sorts each half and merges by repeatedly choosing the smaller unconsumed head. Every output prefix is sorted and contains exactly the consumed elements. Stability requires taking the left item when keys tie. Comparison sorting has an Ω(n log n) worst-case lower bound in the general comparison model; restricted-key counting/radix methods use different assumptions.

## Time and space

Insertion sort is O(n²) worst-case and O(1) extra space, but O(n) time on already-sorted data with the standard shifting implementation. Standard array merge sort uses O(n log n) time/O(n) auxiliary storage. Quicksort has O(n²) worst case without guarantees. State the exact Java overload before claiming implementation-specific bounds. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Use `Integer.compare(x,y)` rather than `x-y` for comparators. Consider ties, duplicate IDs, mutation, stability and whether indices must survive. A comparator must be consistent and transitive; arbitrary tie handling can violate the ordering contract.

## Practice: attempt before expanding answers

### DSA-06-01: Sort records by score ascending and then ID ascending, allowing extreme int values.

| Field | Value |
|---|---|
| Domain/topic | Coding / Sorting as a transformation |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-06-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Compare score with Integer.compare, then ID only on a score tie.

**Complexity and boundary reasoning:** Comparator work is O(1) for fixed-width integer fields; a suitable comparison sort uses O(n log n) comparisons. Workspace and mutation depend on the chosen sort.

**Follow-up:** When would preserving original order on equal scores be required?

**Common mistake:** Comparator subtraction overflow.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-06-02: Count inversions in an array; equal values are not inversions.

| Field | Value |
|---|---|
| Domain/topic | Coding / Sorting as a transformation |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-06-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** During merge sort, when a right value is smaller, add the number of unconsumed left values. Use long for the count.

**Complexity and boundary reasoning:** O(n log n) time/O(n) merge workspace; when a right item precedes a left item, every remaining left item forms an inversion with it.

**Follow-up:** How does a Fenwick-tree alternative handle duplicate values?

**Common mistake:** Counting equal keys as inversions or counting only one left item.

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
