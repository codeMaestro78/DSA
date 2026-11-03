# Complexity analysis

## Start with the model

Complexity describes how resource use grows with input size under a stated operation model. It is not a stopwatch result. Define n, m, V, E, k or numeric amount A before presenting a bound. Treat primitive arithmetic as constant time only within the bounded integer model being used; arbitrary-precision arithmetic depends on operand length.

**O(g(n))** is an asymptotic upper bound, **Ω(g(n))** a lower bound, and **Θ(g(n))** a tight bound. These symbols do not inherently mean worst, best or average case: say which case you analyze. “An algorithm is O(n²)” alone is a valid but possibly loose upper bound for a linear algorithm; use the tightest useful justified statement.

## Count operations using structure

| Structure | Work | Reason |
|---|---|---|
| Two independent scans of n items | Θ(n) | n+n operations, not n² |
| For each of n items, scan all n items | Θ(n²) | n×n |
| For i=0..n−1, scan j=i+1..n−1 | Θ(n²) | n(n−1)/2 pairs |
| Double a positive index until reaching n | Θ(log n) | Each step removes a constant fraction of remaining scale |
| Two pointers each moving only forward | O(n) pointer moves | At most 2n total advances, even if one loop is nested |
| Process adjacency-list graph once | O(V+E) | Each vertex and adjacency entry is examined a bounded number of times |
| Generate all subsets as copied lists | Θ(n·2^n) total output work | There are 2^n subsets and n·2^(n−1) element appearances for n≥1 |

A nested loop is not automatically quadratic. A library call inside a loop may hide linear work: repeatedly removing the first ArrayList item shifts the remainder. Repeated immutable-string concatenation can copy a growing prefix. Analyze actual operations, not indentation.

## Expected, amortized and worst-case

Expected complexity uses assumptions about randomness or input distribution. HashMap basic operations are constant time under adequate key dispersion; hashing a long string itself may involve its length. Do not casually promise worst-case O(1) for every hash-table operation.

Amortized analysis bounds a whole sequence of operations without assuming random inputs. A dynamic-array append can trigger a linear resize, but geometric capacity growth gives constant amortized append cost. A monotonic stack pushes and pops each index at most once, giving O(n) aggregate stack operations.

Worst-case complexity considers the most expensive valid input of a given size. A plain BST lookup costs O(h), with h up to n; balancing provides a logarithmic-height guarantee. Average-looking input is not proof of balance.

## Space accounting

State auxiliary space separately from input and output. Include recursive stack frames, temporary copies, maps, queues and sort workspace. DFS uses O(h) stack on a tree, with h=Θ(n) in a chain. BFS uses O(w) queue memory, with w=Θ(n) for a wide level. “In place” does not automatically mean zero recursion memory.

For distinct variables, retain them: merging two sorted arrays is O(n+m), scanning a V-vertex/E-edge graph is O(V+E), and retaining k values in a heap is O(n log k) for k≥2. Never simplify these to O(n) without defining the relationship.

## Numeric size and overflow

For positive amount A and at least one denomination, coin-change tabulation has the familiar O(A·m) bound and is pseudopolynomial because the input represents A in about log₂ A bits. The linked implementation validates m coin values and initializes A+1 states even at boundary inputs, so its unsimplified bound is O(m+A+A·m+1), or O((A+1)·(m+1)); A=0 does not remove validation work. A huge numeric target can make a compact input computationally expensive. Bound memory as well as arithmetic.

In Java, int arithmetic can overflow before assignment to long: `long x = a+b` adds ints first when a and b are ints. Cast an operand before adding. Use `Integer.compare(a,b)` in comparators instead of subtraction. Capacity and product calculations often need long. State which range guarantees the result itself fits.

## Interview checks

Try to justify: why can a nested window loop be linear? Why is recursive tree space O(h)? Why can O(A) be too large even when A fits int? Why is the output size a lower bound on a generator’s work? Answer with an operation count or counterexample, not only a name.

## References and last verified

[MIT notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/), [HashMap contract](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html), [ArrayDeque contract](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html). [Source metadata](PHASE2_SOURCES.md).

Last verified: 2026-11-02; mathematical reasoning and API scope reviewed.
