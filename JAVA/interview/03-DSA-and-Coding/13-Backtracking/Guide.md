# Backtracking

## What this pattern solves

Explore a decision tree while incrementally maintaining a candidate and undoing local changes. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Use for generating combinations, assignments or paths when many branches must be considered and constraints can prune partial candidates.

**Counterexample to overusing the pattern:** Backtracking does not make exponential output linear. Memoization is not always safe when different paths must produce distinct outputs or state lacks relevant history.

## Worked trace

Subsets of `[a,b]` by include/exclude: at a choose exclude→at b exclude gives [], include gives [b]; choose include a→exclude b gives [a], include b gives [a,b]. Each element gets one binary decision, so each subset has one unique path.

## From brute force to a correct optimization

Define the state precisely: next index, current choice list and any constraint summaries. At a leaf copy the current list before storing it. After recursive exploration, remove the appended choice; otherwise sibling branches inherit state. Prune only when a partial state cannot lead to a valid solution, not merely because it looks unpromising.

## Time and space

Generating all n-element input subsets has 2^n outputs and Θ(n·2^n) total element-copy work when materializing lists, plus O(n) recursion/path auxiliary space. Permutations of n distinct items have n! outputs and Θ(n·n!) output work. Stating only O(2^n) omits materialization costs when n values may be copied per result. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test empty input (one empty subset), duplicates (define distinct-by-value versus distinct-by-index), impossible constraints and mutable-output aliasing. For sorted duplicate-aware subsets, skip equal values only at the same recursion depth; skipping globally removes valid repeated-value choices.

## Practice: attempt before expanding answers

### DSA-13-01: Generate all subsets of an array of distinct values.

| Field | Value |
|---|---|
| Domain/topic | Coding / Backtracking |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-13-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use include/exclude or increasing start-index recursion and copy each completed path.

**Complexity and boundary reasoning:** Θ(n·2^n) total copied-element work for n≥1 and O(n) path/recursion auxiliary space, excluding the output. Empty input still yields the empty subset.

**Follow-up:** Why does empty input yield one subset rather than zero?

**Common mistake:** Storing the same mutable list object repeatedly.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-13-02: Generate unique subsets when values may repeat.

| Field | Value |
|---|---|
| Domain/topic | Coding / Backtracking |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-13-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Sort and skip equal choices at the same depth; later positions can still repeat a chosen value.

**Complexity and boundary reasoning:** Sorting costs O(n log n); output work is O(n·2^n) in the distinct worst case and smaller with duplicates. Copy each path and skip only same-depth equal choices.

**Follow-up:** How would frequency-based generation avoid sorting?

**Common mistake:** Skipping every repeated value globally.

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
