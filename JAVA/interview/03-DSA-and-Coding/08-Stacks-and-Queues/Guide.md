# Stacks, queues and monotonic state

## What this pattern solves

A stack exposes the most recently unresolved item; a queue exposes the earliest pending item. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Nested delimiters and nearest-greater relations suggest a stack; level-order processing and arrival order suggest a queue.

**Counterexample to overusing the pattern:** A FIFO queue does not answer maximum-over-window by itself. A priority queue is not arrival ordered. Java ArrayDeque is not a blocking concurrent queue.

## Worked trace

Next strictly greater value for `[2,1,2,4]`:

| Incoming | Pop unresolved indices | Stack indices after push |
|---|---|---|
| 2 at 0 | none | 0 |
| 1 at 1 | none | 0,1 |
| 2 at 2 | 1 gets 2 | 0,2 |
| 4 at 3 | 2 gets 4; 0 gets 4 | 3 |

## From brute force to a correct optimization

Keep unresolved indices with nonincreasing values. When a new larger value arrives, it is the first greater value for popped indices: earlier scanned values did not resolve them, and the new value does. For strict greater, equal values must not trigger resolution. Each index is pushed once and popped at most once, yielding a linear aggregate bound.

## Time and space

Brute-force next-greater search is O(n²) time/O(n) output. A monotonic stack uses O(n) time/O(n) auxiliary space plus output. ArrayDeque end operations are generally amortized O(1); searching/removing arbitrary values is linear. Recursion also uses a stack and consumes depth-dependent memory. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test increasing/decreasing inputs, equal values, no answer and repeated plateaus. Store indices when output needs distance or duplicate identity. Avoid using null as a sentinel in ArrayDeque, which prohibits null elements.

## Practice: attempt before expanding answers

### DSA-08-01: Validate a sequence containing only (), [] and {} brackets.

| Field | Value |
|---|---|
| Domain/topic | Coding / Stacks, queues and monotonic state |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-08-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Push openers; each closer must match the top; require an empty stack at the end.

**Complexity and boundary reasoning:** O(n) time/O(n) worst-case stack, with an immediate mismatch when a closer has no matching top. Equal counts alone cannot prove nesting.

**Follow-up:** What contract would you use for other characters?

**Common mistake:** Checking only equal total counts.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-08-02: For each value return its distance to the next strictly larger value, or zero.

| Field | Value |
|---|---|
| Domain/topic | Coding / Stacks, queues and monotonic state |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-08-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Keep unresolved indices and pop only when current value is strictly larger.

**Complexity and boundary reasoning:** O(n) time/O(n) auxiliary stack plus O(n) output. Each index enters and leaves the stack at most once.

**Follow-up:** How does circular input change the scan?

**Common mistake:** Using values instead of indices or resolving equal values.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

## Readiness checklist

- [ ] Explain the recognition cue and a case where this pattern is inappropriate.
- [ ] Reproduce the trace from a blank page and state the invariant or recurrence.
- [ ] Derive time/space bounds and state output, hashing and recursion assumptions.
- [ ] Try an empty/boundary case and a counterexample to the common mistake.
- [ ] Solve one unfamiliar variation independently, then record hints and test evidence.

## References and verification

- [Java ArrayDeque API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html) — exact operation/ordering contracts.

- [MIT 6.006 lecture notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) — foundational algorithm structures; this teaching chapter is an original synthesis, not an interview-frequency claim.
- [Java Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html) — array sorting/search contracts; [HashMap API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html) — map behavior. Consult only the APIs relevant to the implementation.
- [Phase 2 source metadata](../PHASE2_SOURCES.md) and [validation instructions](../../validation/dsa/README.md).

**Last verified:** 2026-11-02. Explanation and worked trace reviewed; executable validation covers only methods explicitly included in the linked Java suite. The rest of this chapter is reasoning/practice material, not tested code.
