# Go: bounded goroutines and synchronized state

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

A goroutine is a concurrent execution unit, not an ownership or capacity policy. Shared mutable state needs synchronization. Channels coordinate communication, but using a channel somewhere in a program does not make every other shared-memory access safe. Data-race-free reasoning requires the actual happens-before relationships.

Two goroutines read a counter, add one and write back. Both can read zero and leave one, despite two logical increments. Protect the compound update with a mutex or suitable atomic operation. Passing a pointer through a channel still leaves a question of who may mutate the referenced object afterward.

## What to explain and implement

Know slices as views of underlying storage, map restrictions, interface values, error returns, defer and context cancellation. Appending to a slice may allocate new backing storage or reuse existing storage; aliases determine whether mutations are shared. A typed nil pointer inside an interface can produce a non-nil interface value. Cancellation is a request observed cooperatively, not forced termination of arbitrary work.

## Failure boundaries and tradeoffs

Bound concurrency, outstanding requests and queues. A goroutine blocked forever on send can retain data/resources. The sender/owner should define channel-close policy; closing while senders still use it can panic. A race-detector run supplies evidence only for exercised executions and does not prove all schedules correct. No Go runtime or race detector is run by this chapter.

## Practice — LANG-06

A request starts ten workers and returns after the first result. What must happen to the other nine?

<details><summary>Expected reasoning and followups — attempt first</summary>

Define cancellation propagation and cleanup, ensure workers can stop or deliver without blocking forever, and account for effects already started. Use buffered delivery or cancellation-aware sends under an explicit contract; neither automatically cancels a remote request. Retain an owner that waits/reconciles when needed. Test slow workers, errors, cancellation before send and an unresponsive dependency.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://go.dev/ref/mem) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
