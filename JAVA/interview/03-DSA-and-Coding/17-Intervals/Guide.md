# Intervals and sweep-line reasoning

## What this pattern solves

Intervals encode a range with explicit endpoint semantics; sorting exposes overlap or events. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Use for union/merge, occupancy counts, conflict detection and scheduling. Each has a different output and invariant.

**Counterexample to overusing the pattern:** Overlapping intervals, compatible intervals and rooms required are different problems. Half-open `[1,2)` and `[2,3)` do not overlap, though their union may be represented as `[1,3)` if adjacent union coalescing is allowed.

## Worked trace

Merge closed intervals `[1,4],[2,5],[7,9],[9,10]`:

| Next | Action | Completed / current |
|---|---|---|
| [1,4] | start | current [1,4] |
| [2,5] | 2≤4, extend | current [1,5] |
| [7,9] | gap, finalize | [1,5]; current [7,9] |
| [9,10] | 9≤9, extend | [1,5]; current [7,10] |

## From brute force to a correct optimization

For closed merge, sort by start and retain a current union interval. A next start beyond current end cannot overlap any earlier completed interval because starts are nondecreasing. Otherwise extend current end with max, not the new end alone. For occupancy of nonempty half-open intervals, process departures before arrivals at equal times so touching bookings can reuse a room.

## Time and space

A repeated pairwise merge can take O(n²) or worse if rescanning carelessly. Sort-and-scan takes O(n log n) time plus O(n) output. Extra workspace depends on whether input is cloned and the sort implementation. Sweep-line room counting sorts 2n events: O(n log n) time/O(n) events. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test nested ranges, duplicate ranges, touching endpoints, empty input and invalid start>end. Define whether zero-length intervals are allowed. Avoid mutating input records accidentally by returning references that callers later modify. For time intervals, clarify timezone/clock interpretation separately from ordering.

## Practice: attempt before expanding answers

### DSA-17-01: Merge closed integer intervals into their union components.

| Field | Value |
|---|---|
| Domain/topic | Coding / Intervals and sweep-line reasoning |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-17-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Sort by start, extend with max end on overlap, and flush the final current interval.

**Complexity and boundary reasoning:** O(n log n) time/O(n) output. Input-preserving implementations also copy the intervals; sorting workspace is separate.

**Follow-up:** How do you preserve the original input?

**Common mistake:** Replacing current end with a smaller nested end.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-17-02: Find minimum rooms for nonempty half-open bookings.

| Field | Value |
|---|---|
| Domain/topic | Coding / Intervals and sweep-line reasoning |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-17-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Sort start/end events and release before acquire at equal times; maximum concurrent count is the answer.

**Complexity and boundary reasoning:** O(n log n) time/O(n) event storage. Releasing at a tied endpoint before acquiring preserves half-open compatibility.

**Follow-up:** How would a heap of active end times implement the same invariant?

**Common mistake:** Processing arrivals before departures and overcounting touching bookings.

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
