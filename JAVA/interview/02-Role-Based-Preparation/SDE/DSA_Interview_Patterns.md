# Choosing DSA patterns from preconditions

## Purpose and prerequisites

A pattern is a compressed correctness argument, not a keyword-to-template rule. Derive the property that permits discarded work: sorted order, monotonic feasibility, optimal substructure, an acyclic dependency or a retained frontier.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../03-DSA-and-Coding/Pattern_Recognition.md) when a mechanism is unfamiliar.

## Working method

Choose brute-force state first. Then ask what future decisions need: prefix frequency for arbitrary signed subarray sums; a monotonic window only when its validity supports shrinking; BFS for unit edges; Dijkstra for nonnegative edge weights; dynamic programming for repeated states with a complete recurrence.

## Concrete reasoning example

For target sum3 in [2,−1,2], extending a window can reduce a sum because of the negative value; a nonnegative-only shrink rule is invalid. Prefixes0,2,1,3 allow count-by-prefix: at each prefix p add prior_count[p−3], then increment prior_count[p]. Inserting before querying can incorrectly count empty intervals when target0.

## Tradeoffs and failure boundaries

State complexity with variables and output. A recursion stack is memory; a tree height may be n; enumerating all subsets costs at least output size. A heap does not imply all graph algorithms become O(log n). Read [the pattern map](../../03-DSA-and-Coding/Pattern_Recognition.md) as a guide to conditions.

## Practice — attempt first

### SDE-P7-03-A — Select graph traversal

**Difficulty:** Intermediate. **Prompt:** A directed graph has nonnegative weights0,2,5. Is ordinary BFS sufficient for shortest total weight?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

No: BFS minimizes edge count, not arbitrary weighted cost. Dijkstra applies with nonnegative weights, including zero; stale heap entries need handling. With only0/1 weights, a deque-based0–1 BFS is an alternative. Followup: a negative edge invalidates Dijkstra’s finalization argument.

</details>

### SDE-P7-03-B — Recognize output limits

**Difficulty:** Intermediate with optional stretch. **Prompt:** Generate every subset of n distinct items. Can an implementation materializing each subset run in O(n) total time?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

No. There are2^n subsets and Θ(n2^n) total copied elements, so materialization has that output lower bound. An O(n) recursion state claim excludes output and must say so. Followup: a lazy iterator changes retained output, not total emitted size.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
