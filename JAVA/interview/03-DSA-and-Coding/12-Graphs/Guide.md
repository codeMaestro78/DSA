# Graph traversal and shortest paths

## What this pattern solves

Vertices and edges model arbitrary relationships; define direction and weights before choosing an algorithm. This chapter assumes basic Java syntax and [complexity analysis](../Complexity_Analysis.md). Exercises and difficulty labels are editorial **[INFERRED]**, company-neutral, for early-career SDE/backend candidates; they are not employer question reports.

## Recognition and limits

Reachability suggests DFS/BFS; minimum number of equal-cost edges suggests BFS; prerequisites suggest topological order; repeated connectivity merges suggest union-find.

**Counterexample to overusing the pattern:** BFS is not a minimum-weight path algorithm with arbitrary weights. DFS discovery depth is not generally shortest distance. Union-find does not solve arbitrary directed reachability.

## Worked trace

Edges 0→1,0→2,1→3,2→3 and isolated vertex 4. BFS from 0 first discovers 1 and 2 at distance 1, then 3 at distance 2; vertex 4 remains -1. Marking 3 on enqueue prevents adding it twice.

## From brute force to a correct optimization

BFS processes the queue in nondecreasing distance. When u first discovers an unvisited neighbor v, every shorter path would have discovered v from an earlier layer; therefore distance[v]=distance[u]+1 is minimal in edge count. A visited marker is essential for cycles. In an undirected adjacency list, represent each edge in both directions.

## Time and space

Enumerating all simple paths can be exponential. BFS/DFS on adjacency lists use O(V+E) time/O(V) auxiliary space, excluding input storage. An adjacency matrix scan costs O(V²). The provided BFS validates all adjacency entries first, so O(V+E) covers unreachable components too. Dijkstra requires nonnegative edge weights; topological shortest paths work on DAGs even with negatives. Unless stated otherwise, auxiliary space excludes input and returned output but includes recursion and temporary structures. Preconditions must be stated; omitting their validation is an explicit example contract, not permission to assume them silently in an interview.

## Edge cases and common mistakes

Test disconnected vertices, self-loops, cycles, duplicate edges and invalid IDs. Empty graph has no valid source. For directed cycle detection, a single visited bit is insufficient for DFS back-edge reasoning: distinguish active and completed vertices. Topological sorting succeeds only when all vertices are emitted.

## Java example

The complete runnable implementation and focused tests are in [CoreAlgorithms.java](../../validation/dsa/CoreAlgorithms.java) and [CoreAlgorithmsTest.java](../../validation/dsa/CoreAlgorithmsTest.java). This excerpt uses the same method body. Null sequence inputs throw NullPointerException; the linked list treats null as empty. See the contracts below before reusing it.

```java
import java.util.*;

class Example {
    public static int[] distances(int[][] adjacency, int source) {
        Objects.requireNonNull(adjacency, "adjacency");
        int n = adjacency.length;
        if (source < 0 || source >= n) throw new IllegalArgumentException("source");
        for (int[] row : adjacency) {
            Objects.requireNonNull(row, "adjacency row");
            for (int v : row) {
                if (v < 0 || v >= n) throw new IllegalArgumentException("vertex");
            }
        }
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        distance[source] = 0;
        queue.addLast(source);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            for (int v : adjacency[u]) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.addLast(v); // Mark on enqueue, not on dequeue.
                }
            }
        }
        return distance;
    }
}
```

**Contract:** Valid source and all referenced vertex IDs are checked. Every adjacency row must be nonnull. The graph is directed/unweighted; output -1 means unreachable.

## Practice: attempt before expanding answers

### DSA-12-01: Return minimum edge counts from a source in a directed unweighted graph.

| Field | Value |
|---|---|
| Domain/topic | Coding / Graph traversal and shortest paths |
| Difficulty | Beginner |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-12-02 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** BFS, mark distance when enqueuing, return -1 for unreachable vertices.

**Complexity and boundary reasoning:** O(V+E) time including full representation validation; O(V) distance/queue storage. Each vertex is enqueued at most once.

**Follow-up:** What changes for undirected edges or weighted edges?

**Common mistake:** Marking only on dequeue and adding duplicates unnecessarily.

**Source:** Original curriculum exercise [INFERRED], informed by the primary algorithm/API references below; no company attribution.

</details>

### DSA-12-02: Determine whether all courses can be completed from prerequisite edges.

| Field | Value |
|---|---|
| Domain/topic | Coding / Graph traversal and shortest paths |
| Difficulty | Intermediate |
| Target role / level | SDE, backend; 0–2 years |
| Round / company | Practice coding round; company-neutral |
| Evaluates | Correctness reasoning, data-structure choice, complexity, edge cases and communication |
| Related | [Pattern recognition](../Pattern_Recognition.md), DSA-12-01 in this guide |

<details>
<summary>Expected reasoning, follow-up and mistake to check after attempting</summary>

**Expected answer and explanation:** Use indegrees and a queue of zero-indegree vertices; a processed count below V proves a directed cycle.

**Complexity and boundary reasoning:** O(V+E) time/O(V) indegree and queue space, excluding the adjacency input. Parallel edges must be represented consistently in indegrees and decrements.

**Follow-up:** How would you produce one valid ordering?

**Common mistake:** Applying undirected parent-edge cycle detection to a directed graph.

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
