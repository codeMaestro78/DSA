# Rust: ownership and borrowing contracts

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Rust’s ownership rules track responsibility for values and restrict conflicting access through safe references. Borrowing lets code use a value without transferring ownership. Lifetimes describe relationships needed to keep references valid; annotations do not extend an object’s actual lifetime.

Moving a String into another binding transfers ownership unless a specific borrowing or clone operation is used. An immutable borrow permits reading; an exclusive mutable borrow restricts other overlapping access. A reference returned to a local value cannot become valid just by assigning a longer-looking lifetime annotation.

## What to explain and implement

Practice Option/Result, pattern matching, slices, trait bounds and iteration ownership. A collection of references can be cheaper than copying data only while the source outlives the use. Clone is an explicit semantic operation and may allocate. Arc provides shared ownership across threads when its type bounds permit; it does not grant unsynchronized mutable access to its contents.

## Failure boundaries and tradeoffs

Safe Rust prevents important classes of memory/data-race errors under its contracts, not all deadlocks, leaks or business bugs. A reference-count cycle can still leak. A mutex can poison/block; handling errors and avoiding inconsistent lock order remain design work. Unsafe code shifts proof obligations to its author rather than turning checks into guarantees. No Rust compilation is claimed in this document.

## Practice — LANG-07

A service wraps a mutable map in Arc and assumes concurrent updates are now safe. What is missing?

<details><summary>Expected reasoning and followups — attempt first</summary>

Shared ownership and mutation synchronization are separate concerns. Choose an appropriate mutex/RwLock or ownership/message-passing design and handle contention/poisoning/cancellation according to the actual API. Keep lock scope small and avoid holding guards across incompatible awaits. Review a two-key invariant: independently synchronized operations can still violate a business transaction.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://doc.rust-lang.org/book/ch04-01-what-is-ownership.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
