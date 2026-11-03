# Concurrency contracts and bounded coordination

## Scope and recognition

Concurrency concerns overlapping executions; parallelism concerns simultaneous execution. Start with a shared invariant and an adversarial interleaving. Visibility, atomicity and ordering are distinct. Java volatile establishes visibility/order for its accesses but does not make a read-modify-write expression such as count++ atomic. Single-process locks cannot by themselves coordinate separate servers.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

Two threads increment volatile count initially0: A reads0; B reads0; A writes1; B writes1. Final1 is allowed despite two increments. An AtomicInteger increment gives an atomic single-counter operation; a shared lock can instead protect a multi-field invariant if every relevant reader/writer uses it.

## Why the mechanism works

A producer-consumer queue must maintain0<=size<=capacity. With a monitor, wait in a loop while the precondition is false; wait releases the monitor and returns only after reacquiring it. Recheck because another thread can consume the condition or wakeup may be spurious. A library BlockingQueue expresses bounded handoff more directly than hand-written polling.

## Baselines, complexity and tradeoffs

State work may be O(1), but lock/scheduling/condition waits have no fixed completion bound absent fairness/progress assumptions. A bounded queue limits queued elements, not total arrival rate or execution cost. Define rejection, timeout, interruption, shutdown and ownership of unfinished work. Never hold a scarce lock while indefinitely waiting for unrelated network capacity.

## Practice — attempt before opening review

### DSA-21-01 — Protect a two-field invariant

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** A ledger has free=5,reserved=0. Reserve3 must decrement free and increment reserved atomically with respect to readers. Is replacing both ints with atomics sufficient?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

No: individually atomic operations permit observers to see an intermediate state and two reservations can violate the combined precondition. Protect check plus both updates and corresponding snapshot reads with one lock, or publish an immutable combined state through a suitable CAS loop. Followup: CAS retries may starve and side effects must not be repeated inside the retry computation.

</details>

### DSA-21-02 — Bounded queue shutdown

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** A consumer waits on an empty queue. A producer will never submit again because shutdown has begun. State a shutdown contract that prevents an indefinite wait.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Choose explicit close-and-drain state with a wakeup/recheck protocol, cancellation/interruption or a declared poison-message contract. BlockingQueue itself has no universal built-in close operation. Notify/wake all potentially blocked parties when closing and define whether pending elements drain or abort. Followup: poison pills need a policy for multiple consumers, blocked producers and enqueue ordering. This is reasoned coordination guidance, not an executed concurrent implementation.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[Java17 BlockingQueue contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/BlockingQueue.html) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
