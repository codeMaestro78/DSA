# Concurrency across languages: ownership, ordering and liveness

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Concurrency permits overlapping progress; parallelism permits simultaneous execution. Correctness needs both state safety and progress under the stated scheduling/failure assumptions. A visibility mechanism does not make a multi-step invariant atomic, and an atomic primitive does not automatically protect a larger transaction.

Two workers each inspect a shared remaining capacity of one, then both decrement after deciding to accept. The invariant can fail even if each individual read/write is atomic. Place the decision and mutation in one correctly synchronized critical section or conditional atomic transition; a distributed database/provider boundary needs a different authority mechanism.

## What to explain and implement

State the shared resource, invariant, linearization point, lock/channel/atomic ownership and cleanup protocol. A consistent lock order removes certain cycles but does not promise scheduler fairness. Use condition waits in a loop around the predicate; another worker or spurious wakeup can invalidate an assumption. Bound queues, workers and retained payloads.

## Failure boundaries and tradeoffs

Cancellation, timeout and remote completion are separate facts. Interruption must leave resource ownership clear; releasing a reservation while remote work continues can oversubscribe. Tests should control interleavings where possible and assert the invariant, while recording that exercised schedules are not exhaustive proof. Prefer established concurrent collections/protocols over a handmade lock-free structure without a justified need.

## Practice — LANG-12

A thread-safe map protects each method. Can get-then-put safely initialize exactly one expensive external resource?

<details><summary>Expected reasoning and followups — attempt first</summary>

Not from per-method safety alone: two callers can observe absence and both act. Use an initialization/claim protocol suitable to the resource; atomic map operations may serialize local publication, but their callback/retry semantics and external effect contract still matter. Keep uncertain external outcomes reconcilable rather than hiding them behind local synchronization.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://go.dev/ref/mem) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
