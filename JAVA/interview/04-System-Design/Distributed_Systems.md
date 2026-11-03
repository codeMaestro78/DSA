# Reason about separate failure and knowledge

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

A distributed system coordinates components that can fail or communicate independently. The difficult case is partial failure: one side completed an operation while another side cannot observe the result. Network timeouts, process crashes, retries and reordered messages make “call once” different from “effect once.” Define the source of truth and operation identity before discussing availability.

A linearizable operation behaves as if it took effect at one instant between invocation and response, respecting real-time order. A serializable transaction history has an equivalent serial ordering; that alone does not establish real-time ordering across every external read. Eventual convergence is conditional on the system's delivery/conflict rules and communication recovering; it is not a fixed staleness deadline. During a partition, a system cannot promise both linearizability and formal availability for all affected operations. Scope this claim to the operation rather than labeling every database simply CP or AP.

## Worked trace

Client sends reserve(K), authority commits reservation R, response is lost. The client knows the result is unknown. Retrying the same scoped K can resolve to R if the authority retains a durable uniqueness/fingerprint/result protocol. Retrying a new K may create a second reservation. A local log saying “send failed” cannot prove the remote commit did not happen. For multiple writers, a lease needs a fencing/version rule enforced by the actual resource; an expired clock lease alone cannot stop a paused old writer.

## Alternatives and operating limits

Compare a single authoritative owner with replicated consensus and independent regional writes. A single owner simplifies invariants but needs recovery/fencing and may become unavailable. Consensus helps agree on an ordered log under its fault assumptions, but does not make the application or external provider infallible. Independent writes can improve local availability only when conflict resolution and business invariants permit them. Version vectors, monotonic sequence numbers and wall clocks answer different ordering questions; do not substitute a timestamp for a proof of causality.

## Attempt first — P7-DISTRIBUTED-SYSTEMS

A payment call times out. Is it safe to retry because the request failed locally?

<details><summary>Expected reasoning and followup — attempt first</summary>

The outcome is unknown. Preserve logical operation identity, consult the actual provider's deduplication/reconciliation contract, and avoid creating a new effect blindly. Followup: what if the provider retention window expired?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [HTTP retry semantics](https://www.rfc-editor.org/rfc/rfc9110.html).
- [Existing consistency foundations](Consistency_and_Replication.md).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
