# Memory management: reachability, lifetime and resource budgets

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Memory management answers when storage can be reused, who owns references and how allocation affects a workload. Stack versus heap is a runtime/implementation choice distinct from a language-level ownership promise. A value can escape into a closure, cache or queue and remain live after its original request ends.

A cache stores callbacks that capture entire request objects. Even if only one identifier is needed, each callback can retain a large graph. Completing the request does not remove cache reachability. Replace the capture with the minimal permitted state, bound cache lifetime and verify retention paths in an actual heap/profile rather than assuming a collector failed.

## What to explain and implement

Distinguish live objects, retained size, allocation rate, resident process memory and external/native buffers. Removing one reference may not make an object unreachable, and reclaiming an object may not immediately return pages to the operating system. A bounded number of queue entries is not a byte budget when payload sizes vary.

## Failure boundaries and tradeoffs

Files, sockets and locks need explicit cleanup regardless of whether object storage is garbage-collected. Pooling can reduce allocations yet increase retention, cross-request contamination and complexity. Adopt it only with measurement and a reset/ownership contract. For native code distinguish dangling references, double release and leaks; for managed code inspect unintended reachability and native allocations.

## Practice — LANG-10

An application has stable heap-live size but rising RSS. Is a managed-object leak established?

<details><summary>Expected reasoning and followups — attempt first</summary>

No. Inspect allocation/retention, native buffers, fragmentation, mappings, thread stacks and runtime reporting before attributing cause. Reproduce under comparable workload and record units/time. A heap snapshot can localize managed reachability but cannot explain every resident byte; choose the next measurement that distinguishes the competing causes.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://doc.rust-lang.org/book/ch04-01-what-is-ownership.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
