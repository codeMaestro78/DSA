# Sliding window

## What this pattern solves

Maintain state for a contiguous interval as its boundaries move forward. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

A fixed-size window supports add-one/remove-one updates. A variable window needs a validity rule that justifies moving the left edge.

**Counterexample to overusing the pattern:** For arbitrary signed sums, sum-too-large does not imply that shrinking is safe. For `[5,-4]` and target 1, prematurely discarding 5 misses the whole-array solution.

## Worked trace

Longest distinct UTF-16-unit window in `abba`:

| Right char | Left after update | Window | Best |
|---|---:|---|---:|
| a at 0 | 0 | a | 1 |
| b at 1 | 0 | ab | 2 |
| b at 2 | 2 | b | 2 |
| a at 3 | 2 | ba | 2 |

## From brute force to a correct optimization

Store the most recent index of each unit. If the current unit was at p, a distinct window ending here must start after p when p is inside the active window. Set left=max(left,p+1); never move left backward. The current window is the longest valid one ending here, and the best over endpoints is globally optimal.

## Time and space

Enumerating starts and extending until a duplicate occurs takes O(n²) expected time with sets. A last-position map uses expected O(n) time and O(min(n,U)) entries, where U is the unit alphabet size. Left and right each progress monotonically; a nested shrink loop can still be linear. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test empty string, all equal, all distinct and `abba` (the backward-left trap). This example counts UTF-16 code units: supplementary Unicode characters occupy two units, and grapheme clusters may contain several code points. Choose the representation from the prompt, not from intuition about visible characters.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static int longestDistinct(String text) {
        Objects.requireNonNull(text, "text");
        Map<Character, Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < text.length(); right++) {
            char c = text.charAt(right);
            Integer previous = last.put(c, right);
            if (previous != null) left = Math.max(left, previous + 1);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
```

**Contract:** Measures UTF-16 code units, as String.charAt does; empty input returns 0. It is not a grapheme-counting function.

## Practice: attempt before expanding answers

### DSA-04-01: Find the largest sum among contiguous windows of exactly k integers, with 1≤k≤n.

| Field | Value |
|---|---|
| Domain/topic | Coding / Sliding window |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-04-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Initialize the first long sum, then subtract the outgoing and add the incoming value.

**Complexity and boundary reasoning:** O(n) time/O(1) auxiliary space with random-access input; a stream needs a size-k buffer to identify outgoing values.

**Follow-up:** What is the space cost when input arrives as a stream?

**Common mistake:** Initializing the answer to zero for all-negative windows.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-04-02: Return the length of the longest substring with distinct UTF-16 code units.

| Field | Value |
|---|---|
| Domain/topic | Coding / Sliding window |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-04-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Track last indices and advance the left bound with max to preserve a distinct window.

**Complexity and boundary reasoning:** Expected O(n) time/O(min(n,U)) map entries, where U is the UTF-16-unit alphabet size; left never moves backward.

**Follow-up:** How would code-point or grapheme semantics change the implementation?

**Common mistake:** Moving left backward when a repeated unit is outside the window.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

## Readiness checklist

- [ ] Explain the recognition cue and a case where this pattern is inappropriate.
- [ ] Reproduce the trace from a blank page and state the invariant or recurrence.
- [ ] Derive time/space bounds and state output, hashing and recursion assumptions.
- [ ] Try an empty/boundary case and a counterexample to the common mistake.
- [ ] Solve one unfamiliar variation independently, then record hints and test evidence.

## References and verification

- [Java String API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html) — UTF-16 indexing contract.

- [MIT 6.006 lecture notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) — foundational algorithm structures; this teaching chapter is an original synthesis, not an interview-frequency claim.
- [Java Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html) — array sorting/search contracts; [HashMap API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html) — map behavior. Consult only the APIs relevant to the implementation.
- [Phase 2 source metadata](../PHASE2_SOURCES.md) and [validation instructions](../../validation/dsa/README.md).

**Last verified:** 2026-11-02. Explanation and worked trace reviewed; executable validation covers only methods explicitly included in the linked Java suite. The rest of this chapter is reasoning/practice material, not tested code.
