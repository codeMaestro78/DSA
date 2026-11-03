# Tries and prefix contracts

## Scope and recognition

A trie stores shared prefixes as paths; a terminal marker says a complete word ends at a node. Use it when prefix lookup is fundamental. For isolated exact membership, a hash set is often simpler. Declare the alphabet before claiming fixed-size child arrays; this guide assumes lowercase English letters and set semantics. Empty string is permitted and represented by the root terminal flag.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

Insert `car`, `cart`, `cat`: root→c→a branches to r and t. Nodes for `car`, `cart`, `cat` are terminal. Search `ca` is false while prefix `ca` is true. Delete `car` by clearing r’s terminal marker, retaining its t child; `cart` must remain searchable.

## Why the mechanism works

Each consumed character chooses exactly one edge. At depth d the path represents exactly the first d characters. Successful membership requires both a complete path and a terminal marker. Delete prunes a node only when it has no children and is not terminal. Counting duplicate insertions instead requires a multiplicity contract.

## Baselines, complexity and tradeoffs

Linear scanning N words costs up to O(N·(L+1)) per query under bounded-cost character comparison. Fixed-alphabet trie lookup/insert/delete costs O(L+1), with deletion stack O(L+1); the +1 includes an empty-string operation. Storage is O(Σ·(S+1)) child slots for at most S+1 nodes, including the root, where S is total inserted characters and fixed Σ=26. Enumeration costs the traversed subtree plus materialized output characters. Compressing edges or sparse maps changes constants and assumptions.

## Practice — attempt before opening review

### DSA-16-01 — Distinguish word from prefix

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** After inserting `be`, `bee`, `bed`, delete `be`. State membership/prefix results for `be`, `bee`, empty string.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Membership: be=false, bee=true; prefix be=true. Empty membership=false unless explicitly inserted; empty prefix=true under this contract because the root exists. Deletion must not sever descendants. Trace node terminal flags; followup: support duplicate counts without deleting all copies.

</details>

### DSA-16-02 — Bounded autocomplete

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** Return all stored words starting with `ca` in lexical order from `car, cart, cat, dog`. State how top-k changes stopping and cost.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Output car, cart, cat by terminal-before-children traversal using ordered letters. Search prefix O(2), then visit relevant nodes and copy output; do not claim O(k) regardless of output length. With k=2 stop after car, cart; an empty prefix can enumerate the whole vocabulary. Followup: rank by popularity—lexical DFS alone no longer suffices.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[Princeton authors’ trie source](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/TrieSET.java) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
