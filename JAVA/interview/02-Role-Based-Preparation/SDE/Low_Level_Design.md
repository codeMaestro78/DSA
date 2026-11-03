# Low-level design from invariants

## Purpose and prerequisites

Low-level design assigns responsibilities and valid transitions within a component. Begin with what must remain true, then choose interfaces, state ownership and collaborators. Class count, UML decoration or naming many patterns is not correctness evidence.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../12-Interview-Question-Bank/SDE_Questions.md) when a mechanism is unfamiliar.

## Working method

For a seat reservation component, separate immutable request/value objects, authoritative reservation state and an injected clock/notification boundary. An interface should express meaningful operations such as reserve and cancel rather than expose mutable collections. Document whether calls are single-threaded, locked or backed by a transaction.

## Concrete reasoning example

A reservation with identity R transitions active→cancelled once. A duplicate cancel returns the existing result; it must not add capacity again. If capacity5 has active3, one cancellation yields active2; repeating it leaves2. Updating inventory and reservation state in unrelated steps permits disagreement.

## Tradeoffs and failure boundaries

Validate public inputs before mutation. Return snapshots or immutable views when callers must not alter internal state. Composition isolates changing policies, while deep inheritance can leak assumptions. A local object model cannot promise durable restart recovery or cross-process exclusion without those mechanisms.

## Practice — attempt first

### SDE-P7-04-A — Design duplicate cancellation

**Difficulty:** Intermediate. **Prompt:** An API exposes releaseSeat() without a reservation identity. What contract is missing?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

It cannot associate a release with an active ownership record, so duplicate calls can over-release. Accept reservation identity/current version, verify ownership and transition once under the component’s synchronization boundary. Followup: a timeout requires a stable retry identity, not a fresh reservation.

</details>

### SDE-P7-04-B — Avoid leaking state

**Difficulty:** Intermediate with optional stretch. **Prompt:** A getter returns the internal mutable List of active reservations. What can a caller break?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

A caller may remove/insert entries without validation, violating capacity and identity invariants. Return a defensive snapshot or controlled read-only representation, considering whether elements are themselves mutable. Followup: an unmodifiable list wrapper does not deep-freeze mutable element objects.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
