# Pattern recognition without guessing

## A pattern is a hypothesis

[INFERRED] Recognition should reduce the search space, not replace proof. Translate wording into an invariant, then find a counterexample that would invalidate it. One problem can combine sorting, two pointers and hashing; the first familiar keyword does not determine the answer.

| Signal | Candidate technique | Required condition / trap | Guide |
|---|---|---|---|
| Repeated range sums, immutable sequence | Prefix summaries | Updates change the tradeoff | [Arrays](01-Arrays-and-Strings/Guide.md) |
| Need a previously seen complement/state | Hashing | Multiplicity may require counts, not a set | [Hashing](02-Hashing/Guide.md) |
| Sorted pair relationship | Opposing pointers | Original-index requirements may forbid destructive sorting | [Pointers](03-Two-Pointers/Guide.md) |
| Contiguous segment with maintained validity | Window | Signed sums often break shrinking rules | [Window](04-Sliding-Window/Guide.md) |
| First feasible value under a monotone predicate | Binary search | State the predicate and prove monotonicity | [Search](05-Binary-Search/Guide.md) |
| Need equality groups or ordered processing | Sorting | Sorting costs time and changes original order | [Sorting](06-Sorting/Guide.md) |
| Local rewiring of a chain | List pointers | Preserve identity and save links before mutation | [Lists](07-Linked-Lists/Guide.md) |
| Most recent unresolved relation | Stack | Strict versus non-strict comparison changes ties | [Stack/queue](08-Stacks-and-Queues/Guide.md) |
| Ancestor/path or subtree state | Tree DFS/BFS | Path state must not leak across siblings | [Trees](09-Trees-and-Binary-Trees/Guide.md) |
| Ordered dynamic keys | Balanced search tree | Plain BST height can be linear | [BST](10-BST/Guide.md) |
| Repeated extreme or top-k | Heap | Iteration is not sorted order | [Heap](11-Heaps-and-Priority-Queues/Guide.md) |
| Minimum number of equal-cost edges | BFS | Arbitrary weighted edges require another algorithm | [Graphs](12-Graphs/Guide.md) |
| Generate assignments with constraints | Backtracking | Account for output size and undo state | [Backtracking](13-Backtracking/Guide.md) |
| Equivalent subproblems recur | DP | State must capture every future-relevant condition | [DP](14-Dynamic-Programming/Guide.md) |
| Local decision with an exchange proof | Greedy | Plausibility is not proof | [Greedy](15-Greedy/Guide.md) |
| Overlap, occupancy or compatible bookings | Sort/sweep/heap | Define endpoints and objective | [Intervals](17-Intervals/Guide.md) |
| Shared prefixes and autocomplete | Trie | Declare alphabet, terminal markers and output cost | [Tries](16-Tries/Guide.md) |
| Fixed-width mask/parity operation | Bit reasoning | Signed interpretation, shift width and multiplicity promise matter | [Bits](18-Bit-Manipulation/Guide.md) |
| Divisibility or repeated modular arithmetic | Number theory | Define zero/signed/overflow and arithmetic-cost contracts | [Math](19-Math-and-Number-Theory/Guide.md) |
| Dynamic aggregates or connectivity additions | Fenwick/segment tree/disjoint sets | Match algebra and operation set; DSU is not directed reachability | [Advanced structures](20-Advanced-Algorithms/Guide.md) |
| Shared invariant with overlapping execution | Synchronization/coordination | Visibility, atomicity and progress differ; bound work and shutdown | [Concurrency](21-Concurrency-and-Threading/Guide.md) |
| Relational joins, ranks or grouped results | SQL grain/window reasoning | Preserve multiplicity, NULL and deterministic ordering semantics | [SQL](22-SQL-and-Data-Structures/Guide.md) |

## Contrast exercises

**Subarray sum with negative values:** naive sum-too-large shrinking can miss a future negative that restores the target. Prefix differences plus a map preserve the needed information without a monotonicity assumption.

**Coin minimum:** largest-first fails on denominations 1,3,4 at target 6. DP considers the best final coin; do not adopt greedy without a denomination-specific proof.

**Shortest path:** a one-edge path of weight 100 loses to a two-edge path of total weight 2. BFS minimizes edge count, not arbitrary weight. Dijkstra’s nonnegative-weight condition is a correctness requirement, not a speed hint.

**Ordered lookup:** a heap gives the smallest current item but does not efficiently provide all predecessor/range operations. Select a structure from operations, not its name.

## Recognition drill

For an unfamiliar prompt, write two plausible techniques, state the assumption that would make each correct, and construct one input that distinguishes them. Only then select one. Explain the retained state and candidate-elimination rule aloud. If neither can be justified, a correct baseline is a productive starting point.

## References and last verified

[MIT notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/), [source metadata](PHASE2_SOURCES.md). Pattern-to-interview suggestions are editorial and company-neutral.

Last reviewed: 2026-11-03; contrasts and navigation cover all 22 authored guide areas.
