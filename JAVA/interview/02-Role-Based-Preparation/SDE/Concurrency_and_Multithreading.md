# Java concurrency and lifecycle reasoning

## Purpose and prerequisites

Specify shared state and a safety/liveness contract before selecting primitives. Safety says a forbidden state is not reached; liveness concerns eventual progress under stated assumptions. A method can be safe but block indefinitely. Single-JVM synchronization does not coordinate separate application instances.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../03-DSA-and-Coding/21-Concurrency-and-Threading/Guide.md) when a mechanism is unfamiliar.

## Working method

Choose a shared monitor/lock for combined invariants, atomic operations for suitable single-state changes, and bounded concurrent queues/executors for handoff. Establish happens-before via the actual synchronization protocol, not sleep timing. Protect all accesses that rely on an invariant.

## Concrete reasoning example

A volatile counter increment is read→add→write: two callers can both read0 and write1. AtomicInteger.incrementAndGet supplies a single-counter atomic operation. For free/reserved counters, separately atomic increments do not make the combined reservation transition atomic; use a shared lock or a combined CAS-managed state.

## Tradeoffs and failure boundaries

A fixed thread count does not imply a bounded task queue. ThreadPoolExecutor may queue before growing beyond corePoolSize; queue strategy and rejection define overload. Interruption is cooperative, shutdownNow does not prove tasks or remote effects stopped, and holding locks across network calls can amplify contention.

## Practice — attempt first

### SDE-P7-09-A — Diagnose executor overload

**Difficulty:** Intermediate. **Prompt:** A fixed-size pool receives tasks faster than they complete while using an unbounded work queue. Why does increasing maximumPoolSize not necessarily help?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Queueing occurs after core workers exist; with an unbounded queue it may never become full enough to trigger growth beyond core. Define bounded queue, admission/rejection and dependency capacity; more threads can overload the dependency. Followup: measure queue age and active workload, not thread count alone.

</details>

### SDE-P7-09-B — Handle conditional waiting

**Difficulty:** Intermediate with optional stretch. **Prompt:** A consumer checks if queue empty then calls wait(). Is one if enough?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Wait must be inside a condition loop under the same monitor, because wakeup does not reserve the condition and may be spurious. Handle interruption/close explicitly and signal after state changes. A BlockingQueue can express handoff, but its shutdown protocol still needs design. Followup: a lock-free implementation is not automatically wait-free.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://docs.oracle.com/javase/specs/jls/se17/html/jls-17.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
