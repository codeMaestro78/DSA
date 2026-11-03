# Authorization: bind principal, action and resource

## Overview

Authorization answers: **May this verified principal perform this action on this particular resource now?** Start with an invariant such as “a user can read only documents in a tenant where they currently have read permission.” A UI hiding the delete button does not enforce that invariant at the API boundary.

## A decision model

Inputs include verified identity, action, resource attributes, membership/relationship and relevant context. Role-based access control maps roles to permissions; attribute/relationship-based decisions express rules such as resource ownership or project membership. Choose a model that makes the actual policy clear rather than assuming one acronym solves all cases.

Deny by default, apply least privilege, and verify permission for each request/action. In particular, a successful collection request does not authorize arbitrary ids in a detail endpoint or later mutation. [OWASP authorization guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html)

## Worked request flow

Hypothetical `GET /documents/42`:

1. Verify the caller's session/token and establish its principal id.
2. Resolve current authorized tenant/project scope using trusted server-side policy.
3. Fetch the document **within that scope**, not merely by a user-supplied id.
4. Return only permitted fields; sensitive fields can require additional permission.
5. Log the decision and resource identifier without logging credentials/content unnecessarily.

A random UUID reduces guessability, not the need for permission checks. A signed download URL is a bearer capability with an explicit expiry/scope; forwarding or logging it can transfer access.

For writes, bind authorization to the mutation: use an appropriately scoped statement/transaction and handle no-row/conflict outcomes. A permission check long before a mutation can race with permission/resource changes. State what concurrent revocation semantics the application promises rather than implying an atomic permission lookup guarantees every future action.

## Tenant boundaries extend beyond SQL

- Database queries and mutations must use trusted scope.
- Cache keys must not mix authorized results across tenants or users when results differ.
- Queue jobs must retain authorized context and revalidate when delayed actions require current permission.
- Retrieval filters and source-document fetches must enforce access independently of model output.
- Exports, object storage links and search indexes need equivalent policy; securing only the main API is incomplete.

An agent-generated tool argument is not trusted identity. A tool adapter must derive the execution principal and enforce authorization before side effects. A model instruction such as “only read your tenant” cannot replace an access-control boundary.

## PostgreSQL row-level security

RLS can provide database-enforced row filtering, but it depends on roles and policy configuration. Ordinary table access is default-deny after RLS is enabled without applicable policies; owners typically bypass it unless configured otherwise, and superusers/`BYPASSRLS` roles bypass policies. Test using the real restricted application role, not only an owner account. RLS supplements table privileges; it does not automatically secure every object or application side effect. [PostgreSQL row security](https://www.postgresql.org/docs/18/ddl-rowsecurity.html)

This phase deliberately does not provide a copy-paste tenant-session-variable policy: an unsafe trust source or pooled session leakage would make a plausible-looking policy misleading. A later implementation must define who sets identity, whether callers can spoof it, transaction/pool lifecycle and the exact read/write policies.

## A useful test matrix

| Principal/context | Resource/action | Expected outcome |
|---|---|---|
| Anonymous | Private document read | Authentication required |
| Tenant A member | Tenant A permitted read | Allowed |
| Tenant A member | Tenant B guessed-id read | Denied without disclosing content |
| Tenant A viewer | Tenant A edit | Denied |
| Revoked member | Previously allowed resource | Denied within the stated revocation contract |
| Background job | Resource deleted or permission changed | Defined revalidation/cancellation outcome |

Test both direct ids and list/export/cache paths. A successful denial for one endpoint does not prove complete tenant isolation.

## Interview practice

[INFERRED] Intermediate, backend/full-stack/AI, design/debugging.

1. **AUTHZ-01:** A user changes a URL id and sees another tenant's record. Where must the authorization decision be enforced?
2. **AUTHZ-02:** Tests pass as the database owner but fail under the application role after RLS is enabled. What do you inspect?

<details><summary>Expected reasoning and followups</summary>

1. Bind the caller's verified scope and allowed action to the resource lookup/mutation at the trusted server boundary, backed by database policy where appropriate. Audit caches, exports and tools that can bypass the path. Followup: how do you avoid a policy-check/mutation race?
2. Check grants, effective role, policy expressions and read/write coverage; owners and bypass roles can produce misleading test results. Followup: can an untrusted client set the identity value used by the policy?

Mistakes: trusting a request-body tenant id, relying on UUID secrecy, caching one user's authorized result for everyone, or treating a model/tool schema as an authorization mechanism. Related: [authentication](Authentication.md), [API](API_Design.md).

</details>

## Checklist and last verified

- [ ] Name principal, action, resource, policy source and enforcement point.
- [ ] Cover reads, writes, lists, exports, caches and background jobs.
- [ ] Explain current-permission versus snapshot-permission semantics.
- [ ] Test with the actual application role and cross-tenant negative cases.

Last verified **2026-11-02**: documentation and conceptual boundary review; the matrix is a test design, not an executed application audit. [Source register](PHASE2_SOURCES.md).
