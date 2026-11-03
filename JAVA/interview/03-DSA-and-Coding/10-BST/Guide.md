# Binary search trees

## What this pattern solves

A search tree maintains an ordering invariant across entire subtrees. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Ordered predecessor/successor, range queries and dynamic ordered keys suggest a balanced search structure.

**Counterexample to overusing the pattern:** A node larger than its parent can still violate an earlier ancestor bound. A plain BST can degrade to a chain; sorted insertions do not guarantee logarithmic search.

## Worked trace

Invalid tree: root 10, left child 5, and 5.right=12. Local edges look reasonable (5<10 and 12>5), but 12 lies in the left subtree of 10 and violates its upper bound 10.

## From brute force to a correct optimization

For distinct-key validation, pass an open interval of allowed values. The left subtree receives upper=node.value, the right receives lower=node.value; reject a node outside its interval. Alternatively, inorder traversal must be strictly increasing. Choose a duplicate policy first; allowing duplicates on one side requires consistent bound inclusivity.

## Time and space

Looking up each node via the root can take O(nh). One validation DFS is O(n) time/O(h) stack. BST search/insert/delete take O(h), logarithmic only when balance is guaranteed. Finding kth by ordinary inorder costs O(h+k) time in the early-stopping traversal; an augmented balanced tree supports rank operations in O(log n). Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test int minimum/maximum, ancestor violations, duplicates and a skewed tree. Use long bounds or optional bounds so valid int extremes are not accidentally excluded. Deleting a two-child node requires moving a successor/predecessor correctly and maintaining any subtree-size metadata.

## Practice: attempt before expanding answers

### DSA-10-01: Search for a key in a distinct-key BST.

| Field | Value |
|---|---|
| Domain/topic | Coding / Binary search trees |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-10-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Compare against the current node and descend into the only possible subtree.

**Complexity and boundary reasoning:** O(h) time/O(1) space iteratively; h may be n without balancing.

**Follow-up:** What is the worst case without balancing?

**Common mistake:** Claiming every BST lookup is O(log n).

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-10-02: Validate the entire strict BST invariant.

| Field | Value |
|---|---|
| Domain/topic | Coding / Binary search trees |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-10-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Propagate ancestor lower/upper bounds or verify a strictly increasing inorder traversal.

**Complexity and boundary reasoning:** O(n) time/O(h) stack. Optional bounds or long sentinels accommodate every int value; define duplicate policy before choosing strictness.

**Follow-up:** How would duplicates-on-the-right change the bounds?

**Common mistake:** Checking only immediate children.

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
