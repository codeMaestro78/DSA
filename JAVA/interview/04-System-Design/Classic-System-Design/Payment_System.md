# Durable purchase and reconciliation: hypothetical design case

## Attempt-first scope

Design an intended-purchase workflow across a local database and an external provider. This is software-state reasoning, not financial/legal guidance or a claim about a real provider contract. Do not implement real charges.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 100 intended purchases/minute and one provider attempt per purchase initially. At2 seconds mean active provider time, nominal offered concurrency is(100/60)×2≈3.33; retries/unknown outcomes and bursts require separate limits.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /purchases` uses authenticated tenant/user, a durable operation key and server-owned price/currency. Status distinguishes pending/unknown/confirmed/failed; fulfillment is separately recorded. A new browser request is not a new intended purchase automatically.

Local intent, immutable amount/currency, operation fingerprint and effect references have durable identities. Provider status is an external fact. Correction/refund is a new owned operation with its own identity and reconciliation, not deletion of the original transaction history.

## Critical flow

Authorize and validate the purchase, commit intent/outbox locally, call the provider under its verified deduplication contract, then record authoritative outcome and fulfill idempotently. Use a ledger/reconciliation model appropriate to the actual product, with reviewed accounting rules rather than an invented universal schema.

## Failure trace

Provider commits; response times out. Mark unknown and reconcile or retry the same logical effect only under a demonstrated contract. A new key may double-charge. Provider callbacks can duplicate or arrive out of order; authenticate them and conditionally apply versioned state, preserving evidence rather than blindly trusting a success string.

## Tradeoffs, security and operation

Exactly-once business effects require proof at every relevant boundary, not merely queue delivery guarantees. A staged/manual reconciliation path is more honest than unsafe automatic retries when contracts are absent. Restrict sensitive data, avoid logging payment secrets, and separate support authority from charging authority.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Does a local database transaction roll back an external successful charge?

**Expected reasoning:** No. It controls local atomicity only. Preserve authoritative external facts and use an owned correction/reconciliation workflow when necessary. Followup: how do you avoid a duplicate correction after its response is lost?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [HTTP effect/retry semantics](https://www.rfc-editor.org/rfc/rfc9110.html).
- [Outbox scope](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
