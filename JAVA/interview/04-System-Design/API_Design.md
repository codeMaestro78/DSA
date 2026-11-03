# API design for architecture interviews

## Start from the operation

An API is a contract between callers and the service. Explain inputs, outputs, identity, authorization, validation, failure behavior and compatibility. REST resource names alone do not settle these questions.

For an asynchronous notification, an illustrative contract is:

| Operation | Request | Result and meaning |
|---|---|---|
| `POST /v1/notifications` | Authenticated identity, idempotency key, recipient reference, template ID, variables | `202 Accepted` plus notification ID: intent is durable, delivery is not yet complete |
| `GET /v1/notifications/{id}` | Authenticated identity and resource ID | `200 OK` plus scoped state; no other tenant's data |
| `POST /v1/notifications/{id}/cancel` | Identity and resource ID | Cancel pending work if policy permits; cannot retract an already accepted email |

This is an original example, not a prescribed HTTP standard route layout. HTTP status and method semantics should remain consistent with [RFC 9110](https://www.rfc-editor.org/rfc/rfc9110.html).

## Define the idempotency boundary

Scope the key by tenant and operation. Store the request's normalized payload hash and resulting identifier transactionally with the accepted intent. Concurrent first requests contend on a unique constraint. Same key/same payload returns the same accepted operation; same key/different payload returns a conflict. Define retention: an expired key record may no longer prevent another operation. An idempotency key is not authentication and should not be predictable authority to view someone else's result.

HTTP idempotence concerns intended effect when repeating a request; it does not imply that every response is byte-identical or that arbitrary `POST` calls are safe to repeat. Application-specific deduplication can give a particular `POST` operation a retry contract.

## Bound the contract

Validate maximum payload size, recipient count, supported channel and template version. Return stable machine-readable error codes with a request identifier; do not expose tokens or raw internal stack traces. Distinguish invalid input, unauthorized access, throttling, temporary unavailability and accepted-but-pending work. A timeout can leave the outcome unknown, so clients need a status lookup or a safe replay mechanism.

For collections, specify page size bounds, deterministic ordering and a cursor tied to that ordering. A cursor over `(created_at, id)` handles timestamp ties. New inserts and deletions affect traversal: promise snapshot behavior only if it is actually implemented. Do not infer total result count from one page.

## Evolution and compatibility

Adding an optional field may be compatible, while changing units, defaults, enum meanings or ordering may break clients. Version the contract when semantics change; keep old and new readers working during a migration. Reject unknown fields or ignore them deliberately rather than accidentally. Define cancellation separately from deleting an audit record.

## Practice question

**SD-API-01 — Intermediate; backend/full-stack; design; 0–2 years.** The create endpoint times out after the database commits. The client retries with a new idempotency key. Is duplication prevented?

<details><summary>Expected answer and followups</summary>

No: the deduplication contract recognizes the same logical operation only when its stable key is reused. The client must preserve that key across retries, or the domain must provide another unique business identifier. Followup: what happens if the payload changed under the same key? Reject the conflict rather than silently returning an unrelated result. Mistake: assuming TCP or a load balancer knows whether the business operation committed.

</details>

## Checklist

- [ ] Accepted, completed, failed and unknown outcomes are distinguished.
- [ ] Authorization applies to every object operation.
- [ ] Validation, limits, pagination and errors have explicit semantics.
- [ ] Repeated requests and contract evolution are covered.

## References

- [RFC 9110 — HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html)
- [OWASP — authorization guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
