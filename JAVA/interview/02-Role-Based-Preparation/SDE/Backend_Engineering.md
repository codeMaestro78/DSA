# Backend engineering as durable contracts

## Purpose and prerequisites

Backend interviews test how requests become authorized state changes and reliable responses. Specify identity, validation, database invariant, transaction scope and replay behavior. Authentication identifies a principal; authorization decides whether that principal may perform this operation on this resource now.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../05-Database-and-Backend/README.md) when a mechanism is unfamiliar.

## Working method

For create operations, define a stable request identity scoped to actor/tenant and semantic parameters. Store a request fingerprint/result under a unique identity so a matching retry returns the same resource and a changed payload conflicts. An idempotent HTTP method is a semantic promise, not a database mechanism supplied by its spelling.

## Concrete reasoning example

Create invoice I commits but its HTTP response is lost. The caller cannot infer failure. A retry with the same identity resolves I; a new identity can duplicate it. If an external email/payment is involved, local commit and remote effect are separate facts and require their own retry/reconciliation contract.

## Tradeoffs and failure boundaries

Queries need row-grain, pagination/tie and NULL semantics. A cache hit cannot bypass current resource access. ORM convenience does not remove N+1 queries, transaction isolation or database constraints. Place secrets/tenant decisions server-side; client IDs/prices are inputs, not authority.

## Practice — attempt first

### SDE-P7-08-A — Design request replay

**Difficulty:** Intermediate. **Prompt:** Two POSTs share a key but different currency values. May the server return the first result silently?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Reject the semantic mismatch as a conflict under the stated identity contract; validate a normalized fingerprint including amount/currency/tenant and relevant operation inputs. Followup: recheck current access before revealing even a replayed result.

</details>

### SDE-P7-08-B — Prevent duplicate business state

**Difficulty:** Intermediate with optional stretch. **Prompt:** Two requests read username availability as free and then insert. What mechanism handles the absent-row race?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

A database unique constraint over the intended scope enforces the write invariant; read-then-insert application checks alone race. Catch the constraint conflict and provide a useful response. Followup: case normalization/collation and NULL policy must match the business definition.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://www.rfc-editor.org/rfc/rfc9110.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
