# Trees and binary trees

## What this pattern solves

A rooted tree supports recursive decomposition into disjoint child subtrees. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Path constraints suggest carrying ancestor state downward; subtree aggregates suggest returning state upward.

**Counterexample to overusing the pattern:** A general binary tree is not a BST. A graph with shared children/cycles needs a different visitation contract and may not be a tree.

## Worked trace

Good node example: root 3, left child 1 with child 3, right child 4. A node is good if no ancestor is greater. Carry path maximum: root 3 good, node 1 not good, its child 3 good, node 4 good. Total 3; equality is allowed.

## From brute force to a correct optimization

For good-node counting, at each node compare its value with the maximum of ancestors, then pass max(oldMax,node.value) to each child. Each child receives only its own path state; a mutable global maximum incorrectly leaks values across sibling paths. For height, return 1+max(leftHeight,rightHeight), defining an empty subtree height consistently.

## Time and space

A repeated ancestor scan can cost O(nh), up to O(n²) in a chain. Carrying path state visits every node once: O(n) time/O(h) recursive auxiliary space. BFS uses O(w) queue space where w is maximum width. In a skewed tree h=Θ(n), so recursion is not always O(log n). Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test empty root, singleton, negative values, equality, a chain and sibling values that expose global-state contamination. Avoid an initial numeric sentinel when a type boundary could cause confusion; initialize the path maximum from the root. Deep recursion may overflow the JVM stack.

## Practice: attempt before expanding answers

### DSA-09-01: Compute the maximum depth of a finite binary tree, with empty depth 0.

| Field | Value |
|---|---|
| Domain/topic | Coding / Trees and binary trees |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-09-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Return 0 for null, otherwise 1+max of child depths.

**Complexity and boundary reasoning:** O(n) time/O(h) recursive stack; h may be n. An iterative traversal replaces recursion with explicit storage.

**Follow-up:** How would you avoid recursion on a long chain?

**Common mistake:** Confusing edge-height and node-depth conventions.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-09-02: Count nodes whose value is at least every ancestor value.

| Field | Value |
|---|---|
| Domain/topic | Coding / Trees and binary trees |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-09-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Carry the path maximum independently to each child and count equality as good.

**Complexity and boundary reasoning:** O(n) time/O(h) stack; pass max(ancestorMaximum,node.value) by value to keep sibling paths independent.

**Follow-up:** How would a global maximum fail across siblings?

**Common mistake:** Using a BST assumption or global path state.

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
