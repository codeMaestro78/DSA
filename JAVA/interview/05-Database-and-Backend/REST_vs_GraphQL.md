# REST and GraphQL: compare contracts, not slogans

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

REST is an architectural style organized around resources, representations and uniform interface constraints; an HTTP JSON API is not automatically fully RESTful. GraphQL specifies a typed query/mutation language and execution model through which clients select response fields. Neither style replaces authentication, resource authorization or business invariants.

Compare under one user journey. A mobile screen needing order summary and customer name can use a purpose-built resource representation or a bounded GraphQL query. More client flexibility can reduce overfetching while increasing query-cost control and cache complexity.

## Worked trace

A GraphQL resolver loads one order, its 100 items and each item's product independently. One endpoint has still created an N+1 database workload. Batch within the request/authorization scope, cap pagination/depth/complexity according to actual resolver cost and inspect the real statements. A REST collection can also overfetch or fan out badly; endpoint count alone predicts neither latency nor efficiency.

## Design choices and failure checks

HTTP method/cache/status semantics can support resource APIs; GraphQL transport and partial-data/error handling require an explicit contract. Do not assume HTTP200 proves every requested field succeeded. Mutations need durable identity and concurrency rules for side effects just as resource writes do. Query text or operation name is not authority to read a tenant object. Schema evolution should deprecate compatibly, observe client use and coordinate removal; versioned resource representations have their own rollout costs. Choose from consumer needs, team skills, operational tooling and measurable workloads rather than claiming one approach is universally faster.

## Attempt first — P7-DB-REST-VS-GRAPHQL

Does GraphQL's type system guarantee the caller may read every valid field?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. Type validation and current resource/action authorization are separate. Enforce permissions in the relevant domain/data resolution path and test nested/batched access. Followup: how do you bound the cost of an otherwise valid query?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [GraphQL over HTTP](https://graphql.org/learn/serving-over-http/).
- [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
