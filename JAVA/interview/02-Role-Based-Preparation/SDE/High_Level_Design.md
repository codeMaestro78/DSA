# High-level design for an early-career SDE

## Purpose and prerequisites

High-level design connects a user journey to component/data ownership and quality requirements. Specify actor, critical operation, expected load, freshness, durability, failure tolerance and operating team before choosing deployment units. Values not supplied are assumptions to discuss, not measured traffic.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../04-System-Design/System_Design_Interview_Framework.md) when a mechanism is unfamiliar.

## Working method

Start with one application, authoritative database and only the asynchronous boundaries actually needed. Describe API meaning and transaction boundaries, then trace normal and failed work. Compare a simpler design with a credible alternative under the same assumptions.

## Concrete reasoning example

A report job accepts an authorized request, commits job plus work intent, returns stable ID, runs asynchronously, and exposes an authorized status. A crash after acceptance must leave recoverable intent. A queue alone does not bind its message to the database commit; an outbox is one way to close that local dual-write gap.

## Tradeoffs and failure boundaries

Average load does not establish peak capacity or tail latency. Reads from replicas require a freshness/session policy; current authorization must survive cache/replay paths. A successful backup configuration is not a restore test. Design prose is a proposed system, not a deployment.

## Practice — attempt first

### SDE-P7-05-A — Choose asynchronous scope

**Difficulty:** Intermediate. **Prompt:** An export takes variable time and the browser can disconnect. What should the create response promise?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Only return acceptance after the declared durable job/work-intent boundary is met, then return identity/status location. Reconnect reads that resource rather than creating a second job. Followup: cancellation intent and remote completion are separate facts.

</details>

### SDE-P7-05-B — Explain a component choice

**Difficulty:** Intermediate with optional stretch. **Prompt:** A diagram includes cache, queue, search store and five services for a small CRUD app. How do you review it?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Map each component to a requirement or concrete risk; remove unsupported complexity. Retain alternatives and the evidence that would trigger evolution, such as a measured independent scaling bottleneck. Followup: operational ownership and migration cost belong in the comparison.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://www.rfc-editor.org/rfc/rfc9110.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
