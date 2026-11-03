# Events report facts; consumers still own effects

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

An event-driven design uses durable facts or messages to decouple when producers and consumers work. Distinguish a command (“send this receipt”) from an event (“order accepted”). Event sourcing makes an event log the authoritative history; merely adding an event bus does not make a system event-sourced.

Define event ID, aggregate ID/version, schema version, occurrence time and payload authority. A producer owns its fact; consumers own derived state. Ordering is usually scoped to a key/partition, not global. Consumers must handle duplicate, delayed and incompatible events. A dead-letter queue is a visible unresolved-work state with an owner and recovery policy, not successful completion.

## Worked trace

An order transaction writes order O and outbox event E together. A relay publishes E, then crashes before marking it sent. E can be published again. A consumer that updates an invoice read model should commit its processed-event identity and local projection change atomically, or use an equivalent idempotent version rule. If it calls an external email service, the local transaction cannot make that service deduplicate. Deduplicate at the actual effect boundary or explicitly accept duplicate/loss tradeoffs.

## Alternatives and operating limits

For a simple order API, synchronous validation plus a background dispatcher can be sufficient; a general event platform adds operations and debugging costs. Events improve temporal decoupling but can make user-visible completion asynchronous. Expose accepted/pending/failed states instead of pretending queued work already finished. Track oldest event age, retry counts, projection lag and invariant violations. Rollouts need compatible schemas and replay tests; old events may require an adapter rather than reinterpretation under a new unit definition.

## Attempt first — P7-EVENT-DRIVEN-ARCHITECTURE

A consumer logs E as processed, then updates its projection in a separate transaction. What can go wrong?

<details><summary>Expected reasoning and followup — attempt first</summary>

A crash between the two steps permanently skips the projection update on replay. Reverse order permits a duplicate update. Commit identity and local effect together, or use a demonstrably idempotent conditional effect. Followup: how does deletion affect replayed personal data?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [Transactional outbox](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html).
- [Queue foundations](Message_Queues.md).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
