# Linked lists

## What this pattern solves

Nodes encode adjacency explicitly; identity and reachability matter more than random access. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Reversal, merging, midpoint selection and cycle detection use local pointer rewiring.

**Counterexample to overusing the pattern:** An array-style indexed loop over a singly linked list can cost O(n²). A list is not automatically preferable for insertion: locating the insertion point can still cost O(n).

## Worked trace

Reorder `1→2→3→4→5` into alternating ends:

| Stage | Reachable lists |
|---|---|
| Find/split after middle | 1→2→3 and 4→5 |
| Reverse second half | 1→2→3 and 5→4 |
| Alternate merge | 1→5→2→4→3 |

## From brute force to a correct optimization

Find the first-half tail using slow/fast pointers. Detach its next pointer before reversing so the two lists no longer share a forward edge. Reverse the second half while saving the next node before overwriting. Alternate merge, saving both next pointers. The first half has the same number of nodes as the second or one extra, so first is available whenever second is nonnull.

## Time and space

Putting node references into an array and rebuilding links uses O(n) time/O(n) auxiliary space. Repeatedly finding a tail costs O(n²). Split/reverse/merge uses O(n) time/O(1) auxiliary space and reuses all node objects. The iterative reversal avoids O(n) recursion depth. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test null, singleton, length 2, odd/even lengths, identity preservation and no cycles. This algorithm requires an acyclic, exclusively mutable list; it does not detect or repair cyclic input. Never allocate replacement nodes if the contract requires the original identities.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static final class Node {
        public final int value;
        public Node next;
        public Node(int value) { this.value = value; }
    }

    public static void reorder(Node head) {
        if (head == null || head.next == null) return;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node current = slow.next;
        slow.next = null; // Separate the halves before reversing/merging.
        Node previous = null;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        Node first = head, second = previous;
        while (second != null) {
            Node nextFirst = first.next, nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }
    }
}
```

**Contract:** Node values are unchanged; next pointers are mutated in place. Requires an acyclic list and exclusive mutation ownership. Cyclic inputs are outside the contract.

## Practice: attempt before expanding answers

### DSA-07-01: Reverse an acyclic singly linked list using the original nodes.

| Field | Value |
|---|---|
| Domain/topic | Coding / Linked lists |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-07-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Maintain previous/current; save next before changing current.next and advance both.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary space iteratively; return the previous pointer as the new head. A recursive variant consumes O(n) stack.

**Follow-up:** What changes when reversing only a subrange?

**Common mistake:** Losing the remaining chain after overwriting next.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-07-02: Reorder nodes L0,L1,…,Ln into L0,Ln,L1,Ln−1,… in place.

| Field | Value |
|---|---|
| Domain/topic | Coding / Linked lists |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-07-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Split at the first-half tail, reverse the detached second half and alternate merge.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary space; split/reverse/merge all touch each node a bounded number of times.

**Follow-up:** How would you prove there is no cycle after the merge?

**Common mistake:** Skipping the split or assuming both halves have equal length.

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
