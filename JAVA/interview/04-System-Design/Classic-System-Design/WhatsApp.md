# Private messaging: hypothetical design case

## Attempt-first scope

Design one-to-one text messaging with multi-device delivery, offline catch-up and explicit sent/delivered/read states. Group chat, voice and cryptographic protocol implementation are out of scope. Product names in filenames are navigation only; this is not the company's architecture.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 1,000 active senders, each sends one message per 20 seconds at the modeled busy interval:50 new messages/s. If durable encoded message records average1 KB, that interval writes50 KB/s before indexes, replication and recipient-device fan-out.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /conversations/{id}/messages` carries a stable client operation ID. `GET /conversations/{id}/messages?after=cursor` returns authorized durable history. Acknowledgment states must distinguish server commit from recipient receipt and human read.

A conversation owns membership and an ordered server sequence. Message identity is unique within sender/conversation operation scope. Retain device delivery cursors separately from the message record; multiple recipient devices do not create multiple logical messages.

## Critical flow

Authenticate sender and current conversation membership; durably append message and dispatch intent atomically; return the accepted ID/sequence. Deliver to connected devices and let disconnected devices replay from a durable cursor. A read receipt is another authorized event, not inferred from a TCP acknowledgment.

## Failure trace

The response is lost after append. Retry the same operation identity returns the existing message rather than appending again. A push notification is only a wake-up hint; missing it must not remove history. Device cursors need coherent replay/reset when retention prunes old messages.

## Tradeoffs, security and operation

A per-conversation owner gives a simple order but a hot group would require a new scale strategy. Server ordering is not proof of real-world causality or end-to-end encryption. A real E2EE design requires a separately reviewed key/device/authenticated encryption protocol and a policy for metadata/backup exposure.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** The recipient receives message9 twice after reconnect. What should the UI and storage do?

**Expected reasoning:** Apply immutable message identity/sequence once, preserve server ordering, and treat delivery acknowledgments idempotently. Do not deduplicate by equal text: two intentional identical messages remain distinct. Followup: how do edits/deletes change version and replay?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html).
- [Outbox](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
