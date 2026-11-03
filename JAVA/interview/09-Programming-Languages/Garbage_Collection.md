# Garbage collection: reclaiming storage is not closing a workflow

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Tracing garbage collection identifies objects reachable from roots and can reclaim unreachable managed objects. Reachability is not the same as business usefulness: a never-used entry in a global map remains reachable. Collector algorithms, pause/concurrency behavior and tuning options depend on the actual runtime/version.

A service keeps finished tasks in a static map. The tasks stay reachable, so increasing collector aggressiveness does not make them disappear. Remove/bound entries according to a retention contract and inspect a path from a root to the retained task. If a task owns a socket, also ensure its cleanup protocol ran; reclaiming Java/Python memory cannot undo a remote operation.

## What to explain and implement

Explain roots, generations as an optional implementation strategy, allocation pressure, throughput and pause distributions. Reference counting and tracing have different tradeoffs; cycles and weak references need runtime-specific reasoning. A pause is one possible latency source, not a diagnosis from a slow request alone. Correlate allocation, collector events, CPU, queues and complete request latency.

## Failure boundaries and tradeoffs

Do not promise a particular collector from a language name or universal stop-the-world duration. Finalization/cleanup timing is not an external-effect guarantee. Weak references may help some cache ownership patterns but make entry presence nondeterministic and do not supply authorization, capacity or semantic freshness.

## Practice — LANG-11

A developer clears a local reference then asserts that process memory must immediately fall. Explain the missing assumptions.

<details><summary>Expected reasoning and followups — attempt first</summary>

Other roots may retain the object; reclamation may run later; a runtime allocator may keep reusable pages; and native memory may be unrelated. Verify a controlled object-retention example and runtime telemetry. Report the actual metric, not a generic statement that memory was freed. Required resource cleanup should be deterministic through the owning API.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://docs.oracle.com/en/java/javase/17/gctuning/introduction-garbage-collection-tuning.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
