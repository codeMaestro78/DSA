# Heaps and priority queues

## What this pattern solves

A binary heap exposes one extreme while maintaining a partial order, not full sorted order. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Use when repeatedly extracting the best pending item or retaining only k candidates.

**Counterexample to overusing the pattern:** Iterating a priority queue does not enumerate sorted order. A heap alone does not provide O(log n) removal by arbitrary value or key updates without handles/indexing.

## Worked trace

Keep largest k=2 values from `[5,1,7,3]` in a min-heap:

| Item | Retained candidates |
|---|---|
| 5 | 5 |
| 1 | 1,5 |
| 7 | 5,7 (discard 1) |
| 3 | 5,7 (discard 3) |

## From brute force to a correct optimization

Keep at most k values. Insert the new value, then remove the minimum if size exceeds k. After each prefix the heap contains k largest values from that prefix (or all values if fewer than k exist); an item below the retained minimum cannot become top-k later because already-seen larger items remain competitors. The root is the kth largest once k items exist.

## Time and space

Sorting everything costs O(n log n). Retaining k costs O(n log k) for k≥2 and O(k) space; for k=1 the work is O(n). Building a heap from all values can take O(n) heapification, distinct from n repeated O(log n) inserts. Java PriorityQueue peek is O(1), offer/poll O(log n), contains/remove(Object) O(n). Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Specify 1≤k≤n and whether duplicates count as separate occurrences. Use safe comparators, not subtraction. For merging sorted streams, keep one head per nonempty stream and include stream/index identifiers to handle equal values.

## Practice: attempt before expanding answers

### DSA-11-01: Return the kth largest value, counting duplicates, for 1≤k≤n.

| Field | Value |
|---|---|
| Domain/topic | Coding / Heaps and priority queues |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-11-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Maintain a size-k min-heap and return its root after processing all values.

**Complexity and boundary reasoning:** O(n log(k+1)) time/O(k) auxiliary space is a bound valid also for k=1. Counts include duplicate occurrences, not only distinct values.

**Follow-up:** When would selection or sorting be preferable?

**Common mistake:** Using a max-heap of size k but discarding its largest item.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-11-02: Merge m sorted streams into one sorted output.

| Field | Value |
|---|---|
| Domain/topic | Coding / Heaps and priority queues |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-11-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Keep each stream’s next value in a min-heap; after extracting, advance only that stream.

**Complexity and boundary reasoning:** O(m+N log(m+1)) time with heap construction for m streams and N output items; O(m) heap state plus output. Ended streams contribute no next item.

**Follow-up:** How do you handle an unbounded stream and downstream backpressure?

**Common mistake:** Inserting every stream item upfront and calling the structure streaming.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

## Readiness checklist

- [ ] Explain the recognition cue and a case where this pattern is inappropriate.
- [ ] Reproduce the trace from a blank page and state the invariant or recurrence.
- [ ] Derive time/space bounds and state output, hashing and recursion assumptions.
- [ ] Try an empty/boundary case and a counterexample to the common mistake.
- [ ] Solve one unfamiliar variation independently, then record hints and test evidence.

## References and verification

- [Java PriorityQueue API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html) — exact operation/ordering contracts.

- [MIT 6.006 lecture notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) — foundational algorithm structures; this teaching chapter is an original synthesis, not an interview-frequency claim.
- [Java Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html) — array sorting/search contracts; [HashMap API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html) — map behavior. Consult only the APIs relevant to the implementation.
- [Phase 2 source metadata](../PHASE2_SOURCES.md) and [validation instructions](../../validation/dsa/README.md).

**Last verified:** 2026-11-02. Explanation and worked trace reviewed; executable validation covers only methods explicitly included in the linked Java suite. The rest of this chapter is reasoning/practice material, not tested code.
