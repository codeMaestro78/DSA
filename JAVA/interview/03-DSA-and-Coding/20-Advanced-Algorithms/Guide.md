# Fenwick trees and selecting advanced structures

## Scope and recognition

A Fenwick tree stores prefix aggregates in overlapping binary-sized blocks. Use it for point additions and range sums when a static prefix array would be invalidated frequently. Use a segment tree for richer associative aggregates/updates when the simpler additive structure does not meet the contract. This guide uses one-based indices1..n and exact sums in an adequately wide type.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

For values[2,1,3,4,5,2], internal block6 covers5..6 (sum7), block4 covers1..4 (sum10); prefix(6)=17. Prefix(3) uses block3=3 then block2=3, giving6. Add2 at index3 updates blocks3,4 (then8 lies beyond n); prefix(6) becomes19.

## Why the mechanism works

Block i covers i−lowbit(i)+1 through i, where lowbit(i)=i&−i for positive i. Prefix query adds that block then sets i←i−lowbit(i), stripping a suffix without overlap. Update sets i←i+lowbit(i), visiting affected blocks. Index zero cannot be updated because lowbit(0)=0 would prevent progress.

## Baselines, complexity and tradeoffs

Repeated raw range summation is O(n) per query and O(1) update; static prefix sums invert that tradeoff. Fenwick addition/query each O(log n), O(n) retained state; build O(n log n) by successive additions or O(n) with a carefully derived propagation build. Range sum[l,r]=prefix(r)−prefix(l−1) relies on additive inverses, so an arbitrary minimum operation is not an interchangeable replacement.

## When Fenwick is not the right structure

A **segment tree** stores an associative aggregate for a declared interval at each node. With point assignments, recompute the leaf-to-root path; a range query combines a logarithmic number of disjoint canonical intervals in their proper order. For n≥1 and constant-cost combination, build takes O(n) work, point update and a single range query take O(log n) work, and retained storage is O(n). Noncommutative combinations must preserve left-to-right order. A sum query has identity 0; a minimum query needs a suitable empty-range identity or explicit optional result. Range updates require an additional update/composition contract; a point-update tree does not automatically implement lazy propagation.

For example, minimum over values [5,2,7,1] is 1, and minimum over the first three is 2. Assigning the second value to 8 changes that first-three minimum to 5. Unlike sum, minimum has no inverse that lets prefix(r) minus prefix(l−1) recover a range answer. This is why the simple additive Fenwick subtraction formula cannot be reused unchanged.

A **disjoint-set union** structure addresses another task: merging equivalence classes and testing connectivity under additions. Start with each vertex as its own parent; find follows parents to a representative, and union joins two different roots, decrementing the component count only when a merge occurs. Union by rank/size controls tree shape; path compression shortens future finds. For n elements, initialization/storage are O(n). With both balancing and path compression, a sequence of m finds/unions has O(m·α(n)) operation work after initialization, where α is inverse Ackermann; it is an amortized sequence bound, not a worst-case O(1) per-call promise.

On vertices 0..3, union(0,1), union(1,2), union(0,2) leaves two components; the third operation does not merge another component. This models undirected connectivity/equivalence. It does not answer arbitrary directed reachability, reconstruct a shortest path or support arbitrary edge deletion without a different algorithm. [The authors’ implementation](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/UF.java) documents balancing/path-compression behavior; these traces remain original reviewed reasoning, not an executed implementation.

## Practice — attempt before opening review

### DSA-20-01 — Assignment versus addition

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** Starting from[2,1,3,4,5,2], assign index3=8 and query sum[2,5]. Explain required old-value state.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Use delta=8−3=5, not add8. New range sum1+8+4+5=18. Store current values or query the old point before assignment; an additive update API must not silently promise assignment. O(log n) per query/update and O(n) retained data. Followup: many range additions need a different representation.

</details>

### DSA-20-02 — Coordinate compression and inversions

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** Count strict inversions in [3,1,3,2] using compressed value ranks; equal values are not inversions.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Ranks are1→1,2→2,3→3. Scan right-to-left; query prefix(rank−1) before inserting current rank. Counts0(for2),1(for3),0(for1),2(for3) total3. Compression preserves ordering, not numeric distance. Sort costs O(n log n), tree work O(n log n), O(n) memory. Followup: querying prefix(rank) would incorrectly count equal3 values.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[Princeton repository Fenwick implementation](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/FenwickTree.java) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
