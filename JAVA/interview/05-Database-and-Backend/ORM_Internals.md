# ORM internals: object convenience does not remove transactions

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

An object-relational mapper translates between language objects and relational storage. Its identity map can return one in-memory instance for a database identity within a session; that is not a cross-request cache or proof the object reflects the newest committed row. A unit of work tracks changes and flushes statements. Flush and commit have different meanings.

This lesson uses SQLAlchemy2.0 terminology as a concrete documented example; other frameworks differ in lazy loading, tracking and transaction defaults. Inspect generated statements and session lifecycle rather than assuming identical behavior across ORMs.

## Worked trace

A request loads 100 orders referencing 100 distinct customers that are not already loaded in the session, then accesses each ordinary lazy customer relationship. Under that stated loading setup, one initial query plus 100 additional customer SELECTs is an N+1 pattern. If orders share customers or their simple many-to-one targets are already present in the session identity map, SQLAlchemy can satisfy those accesses without one new SELECT per order; the actual count must be inspected rather than inferred from the number of parent rows. Eager/batched loading can reduce trips, but joining several one-to-many collections may multiply rows and memory. Count round trips, result grain and requested fields, not just method calls. Pagination should be applied at the intended entity grain, not a fan-out join that truncates parents unexpectedly.

## Design choices and failure checks

Keep request/session ownership explicit; do not share mutable session objects across concurrent tasks unless the framework explicitly supports the pattern. Roll back failed transactions before reusing state according to the framework contract. Database uniqueness/foreign keys remain authoritative even when application validation runs first. Optimistic version checks need the affected-row/result decision and an explicit conflict policy. Bulk updates may bypass ordinary tracking hooks; tenant predicates and authorization must still hold. Test generated queries and constraints on the actual engine; mocking repository methods alone cannot establish SQL/concurrency behavior.

## Attempt first — P7-DB-ORM-INTERNALS

A flush succeeded. May the API claim durable transaction success?

<details><summary>Expected reasoning and followup — attempt first</summary>

Not on that fact alone. Flush may execute statements within an uncommitted transaction; commit can still fail. Followup: what happens to tracked state after a failed flush/transaction?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [SQLAlchemy Session basics2.0](https://docs.sqlalchemy.org/en/20/orm/session_basics.html).
- [SQLAlchemy2.0 relationship loading](https://docs.sqlalchemy.org/en/20/orm/queryguide/relationships.html) — lazy-loading/identity-map qualifications; directly rechecked2026-11-03.

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
