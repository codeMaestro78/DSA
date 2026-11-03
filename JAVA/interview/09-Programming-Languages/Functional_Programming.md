# Functional programming: pure transformations and explicit effects

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

A pure function depends on its declared inputs and produces no observable side effects. Referential transparency makes substitution and local reasoning easier. An immutable binding alone is insufficient when its referenced data is mutable or the function reads a hidden clock/global configuration.

A price calculator reads a mutable global discount. Repeating the same visible inputs can return a different result. Pass a versioned discount policy or explicit time into a pure calculation, then let an outer effect boundary read policy, authorize and persist the result. Persisted effects still require their own concurrency/idempotency contract.

## What to explain and implement

Map transforms elements; filter selects; fold combines with an initial state and operation. Associativity permits regrouping a reduction while preserving element order; changing that order additionally needs commutativity or another explicit order-independent contract. For example, string concatenation is associative, but combining ordered chunks into `abc` differs from reordering them into `acb`. Floating-point addition is not exact associative real addition. Laziness can avoid unused work but can also retain large sources, defer exceptions or repeat effects if iteration is restarted. Make evaluation ownership explicit.

## Failure boundaries and tradeoffs

Persistent data structures share immutable structure; they do not copy every node on each update, but have representation-specific time/allocation costs. Choose a simple loop when it expresses the invariant more clearly than a chain of transformations. Functional style does not itself establish thread safety for external resources or guarantee faster execution.

## Practice — LANG-09

Can a fold over currency represented as floating point be parallelized with no change in its exact result?

<details><summary>Expected reasoning and followups — attempt first</summary>

Not generally: changing grouping can change rounded sums. Use a stated exact integer/minor-unit or decimal policy with overflow/rounding rules where appropriate, or specify numerical tolerances and reproducibility requirements. Separately prove associativity of the actual operation used. Test cancellation, empty input and order-dependent effects before moving work across boundaries.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://docs.python.org/3.12/tutorial/datastructures.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
