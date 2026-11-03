# Backend API design: contracts survive retries

## Overview

An API is a behavioral contract across a failure boundary. Specify inputs, caller permissions, outputs, state transitions and failure responses before selecting a framework. This chapter focuses on backend/database correctness; [system-design API material](../04-System-Design/API_Design.md) covers the wider service boundary.

## Example contract

Hypothetical endpoint: `POST /orders` accepts an authenticated customer's product/quantity list and an application-defined idempotency key. The server derives the customer and tenant from verified identity plus authorized membership; it does not trust an arbitrary request-body customer id.

On success, return `201 Created`, a durable order id, state and a location URL. For asynchronous work, `202 Accepted` means accepted for processing, not completed delivery; expose a status resource and failure state. Use consistent validation errors and request correlation ids. Avoid returning internal SQL errors or credentials.

HTTP defines safe and idempotent method semantics. POST is not generically idempotent; an application can give a specific POST operation an idempotency protocol. DELETE's idempotence concerns intended state, not identical response codes or zero incidental logging. [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html)

## Idempotency needs durable coordination

A defensible original design:

1. Scope the key to the authenticated tenant/principal and operation.
2. Compute a canonical request fingerprint so reuse with a different payload is rejected.
3. Atomically claim the key using a database uniqueness constraint; a preliminary lookup alone races.
4. Commit the order result and its operation record together when they share one database transaction.
5. On retries, return/reconcile the existing result rather than executing a second business operation.

Specify expiry/retention and concurrent in-progress behavior. Once a key record expires, a late retry may no longer be deduplicated. If the business side effect is at an external provider, the database transaction cannot atomically commit that provider's work. Use its idempotency facility if available and retain a reconciliation path for ambiguous outcomes. Do not promise end-to-end exactly-once delivery from a local uniqueness constraint.

## Pagination with a defined order

Offset pagination is simple but deeper offsets can require discarding work, and intervening inserts/deletes can shift page boundaries. Keyset pagination seeks from a stable ordering tuple.

```sql
-- example: cursor_page
SELECT id, created_at, amount_cents
FROM orders
WHERE customer_id = 1 AND status = 'paid'
  AND (created_at, id) < (TIMESTAMPTZ '2026-01-02 00:00:00+00', 102)
ORDER BY created_at DESC, id DESC
LIMIT 20;
```

Expected on the fixture: order `101`. The cursor carries both fields; timestamp alone loses rows at ties. This query assumes ordering fields are non-null and unchanged. Keyset pagination does **not** automatically produce one transactionally frozen snapshot across requests. State whether freshness, a fixed cutoff, or a snapshot/export contract is required.

Treat a cursor as untrusted input: parse and bound it; authenticate it if tampering would violate the contract. Reapply authorization on every page. A signature proves integrity, not access rights.

## Validation and concurrency

Validate shape/ranges at the API boundary and use parameterized SQL. Allowlist dynamic identifiers such as sort fields; value placeholders do not substitute table/column names. Enforce persistent uniqueness and constraints in the database. For optimistic concurrency, an expected version or `If-Match` condition can reject a stale write; clients need a conflict/reload strategy. [Conditional requests](https://www.rfc-editor.org/rfc/rfc9110.html#name-conditional-requests)

Bound body size, page size, execution time and retries. A timeout is not proof an operation was never performed. Report safe retry behavior as part of the contract.

## Interview practice

[INFERRED] Intermediate, backend/full-stack, implementation/design round.

1. **API-01:** Two requests submit the same idempotency key concurrently. How does your design ensure only one order is created?
2. **API-02:** A cursor is signed but references another tenant. Why must the next page still enforce authorization?

<details><summary>Expected reasoning and followups</summary>

1. A unique operation key coordinates writers; order creation and result storage use an appropriate transaction. Define what the losing/in-progress caller sees and how the response is recovered after a lost acknowledgement. Followup: how do you handle key reuse with another payload?
2. Integrity and authorization are separate. Restrict results to the current verified principal's permitted tenant/resources on every request, independent of cursor contents. Followup: what happens after membership is revoked between pages?

Mistakes: using an in-memory key cache as the only durable deduplication record, returning 202 as proof of completion, or claiming keyset pagination freezes changing data. Related: [transactions](Transactions_and_ACID.md), [authorization](Authorization.md).

</details>

## Checklist and last verified

- [ ] Define caller, invariant, state transition and failure outcomes.
- [ ] Explain lost acknowledgement and concurrent duplicate requests.
- [ ] State pagination ordering and consistency semantics.
- [ ] Bound work and preserve authorization across retries/pages.

Last verified **2026-11-02**: HTTP primary documentation and toy cursor-query validation. Endpoint prose is an original hypothetical design, not a deployed implementation or a confirmed company question.
