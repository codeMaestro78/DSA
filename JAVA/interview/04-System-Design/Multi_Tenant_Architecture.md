# Tenant isolation is an end-to-end invariant

Original **[INFERRED]**, company-neutral teaching. Workloads and numerical examples are stipulated fixtures, not service measurements.

## Concept and decision

A multi-tenant system serves multiple organizations while preventing unintended data, work and administrative access across them. Authentication identifies a principal; authorization decides its current action on a resource in a tenant. A client-supplied tenant ID is a requested scope, not proof of membership.

Shared tables, separate schemas and separate databases offer different isolation and operations costs. In a pooled model, include tenant identity in ownership keys, query predicates, uniqueness and relationships. Database row-level policies can add a boundary, but roles that bypass policies, background jobs, exports, caches and object stores still need design and tests. Separate storage does not remove application-level credential mistakes.

## Worked trace

Tenant A asks for report17 belonging to B. The handler must resolve current membership/action rights and constrain the resource lookup, not fetch by global ID and trust the URL. A cache keyed only by report17 can leak B's text after an otherwise correct A request. A background job must carry a trusted tenant/resource context and revalidate publication authority. Signed object URLs create bearer access for their valid lifetime; revoking membership does not necessarily retract a URL already issued.

## Alternatives and operating limits

Noisy-neighbor protection requires tenant and aggregate limits: ten concurrent jobs per tenant can still overload a database when many tenants arrive. Use fair admission, quotas, bounded queues and scoped observability. Migration needs tenant routing/version state, one current writer and verification of copied data; a rollback cannot reauthorize deleted records. Retention/deletion must account for derived indexes, logs and backups under the actual policy. Test two tenants with equal resource names, guessed IDs, batch requests, retries and admin actions.

## Attempt first — P7-MULTI-TENANT-ARCHITECTURE

A report cache key includes tenant ID. Is isolation complete?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Verify trusted tenant derivation, resource/action rights, source queries, event/job context, download credentials, exports and administration. The cache key closes one possible leak but does not prove the whole flow. Followup: how does permission revocation affect an in-flight report?

</details>

## Evidence and references

This chapter provides reviewed mechanism/trace reasoning. No database, network service, cloud deployment, load test or incident was executed.

- [Authorization guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [Constraint foundations](https://www.postgresql.org/docs/18/ddl-constraints.html).

[Source scope](PHASE7_SOURCES.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
