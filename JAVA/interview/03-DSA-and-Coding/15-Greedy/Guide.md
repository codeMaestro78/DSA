# Greedy algorithms

## What this pattern solves

Commit to a local choice only when a proof shows that some global optimum can contain it. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Useful when an exchange argument or stay-ahead property allows removing a decision without retaining many alternative states.

**Counterexample to overusing the pattern:** A rule that works on examples can fail on another denomination or weighting. Minimum-coin greedy is not valid for arbitrary positive coin systems.

## Worked trace

Maximum number of non-overlapping half-open intervals: `[0,3),[1,2),[2,4)`. Earliest start chooses `[0,3)` and gets 1. Earliest finish chooses `[1,2)`, then `[2,4)`, getting 2. Touching endpoints do not overlap under this contract.

## From brute force to a correct optimization

Sort by finishing time. Take the earliest-finishing interval compatible with the previously chosen one. For an optimal solution’s first interval o and greedy choice g, end(g)≤end(o). Replacing o with g leaves every later interval feasible and preserves the number selected. Repeat on the remaining compatible suffix. The proof depends on maximizing count, not arbitrary interval weights.

## Time and space

Checking all subsets costs exponential time. Earliest-finish selection costs O(n log n) sorting plus O(n) scan; auxiliary space depends on sorting implementation and input mutation policy. Weighted interval scheduling generally requires DP; the greedy proof no longer preserves total weight. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Specify open/closed endpoints, empty intervals and ties. Test nested intervals, touching boundaries and equal finishing times. A min-heap solution to meeting-room count solves a different objective from choosing the largest compatible subset.

## Practice: attempt before expanding answers

### DSA-15-01: Maximize the number of compatible nonempty half-open intervals.

| Field | Value |
|---|---|
| Domain/topic | Coding / Greedy algorithms |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-15-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Sort by end and select each interval whose start is at least the previous selected end.

**Complexity and boundary reasoning:** O(n log n) time dominated by sorting; the scan uses O(1) extra state excluding sorting/output. The exchange proof applies to count, not weight.

**Follow-up:** State the exchange argument, not only the algorithm.

**Common mistake:** Sorting by start and accepting every compatible interval.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-15-02: Explain why the same greedy rule fails when intervals have positive weights.

| Field | Value |
|---|---|
| Domain/topic | Coding / Greedy algorithms |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-15-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Construct a long high-weight interval and multiple lower-weight intervals; earliest finish may lose total value. Use predecessor-index DP.

**Complexity and boundary reasoning:** After sorting by end, define p(i) as the last compatible predecessor. OPT(i)=max(OPT(i−1),weight(i)+OPT(p(i))); O(n log n) preprocessing/time and O(n) DP storage.

**Follow-up:** What recurrence solves the weighted variant?

**Common mistake:** Assuming a proof for cardinality also proves weighted optimality.

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
