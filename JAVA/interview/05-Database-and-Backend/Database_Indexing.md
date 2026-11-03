# Database indexing: start with an access pattern

## Overview

An index spends storage and write/maintenance work to reduce selected read work. It is useful when the avoided scanning, joining or sorting outweighs those costs. Do not begin an interview answer with “index every column.” Begin with a query, data distribution and workload.

## A concrete access pattern

Requirement: return the newest paid orders for one customer, at most 20 rows, with stable ordering.

```sql
-- example: customer_history
SELECT id, created_at, amount_cents
FROM orders
WHERE customer_id = 1 AND status = 'paid'
ORDER BY created_at DESC, id DESC
LIMIT 20;
```

On the shared fixture the ordered ids are `102,101`. The id breaks timestamp ties. An illustrative PostgreSQL index for this particular query is:

```sql
-- example: history_index
CREATE INDEX orders_paid_history_idx
ON orders (customer_id, created_at DESC, id DESC)
INCLUDE (amount_cents)
WHERE status = 'paid';
```

The key starts with equality on customer, then the requested ordering; the partial predicate excludes other statuses. An included amount is payload rather than an additional search key. On a six-row fixture the planner may still prefer scanning the table; successful creation does not demonstrate a speedup.

## B-tree intuition and limits

A B-tree keeps searchable ordered keys with bounded-height navigation and leaf-range access. A lookup can locate a starting position, then scan matching entries. For a range returning `k` rows, “O(log n)” alone omits the work of visiting/outputting those rows, possible heap fetches and I/O. A rough abstract model is O(log n + k), not a latency guarantee.

For multicolumn B-trees, equality on leading columns followed by a range is often useful. Do not claim an index is *unusable* whenever the leftmost column is missing: later-column predicates can still be checked, and PostgreSQL 18 can sometimes use skip scan. Whether that is cheaper is planner/workload dependent. The local runtime here is 16, so no skip-scan test is claimed. [Multicolumn indexes](https://www.postgresql.org/docs/18/indexes-multicolumn.html)

## Index variants and tradeoffs

| Technique | Useful when | Cost or limitation |
|---|---|---|
| Composite B-tree | Filtering and ordering match a stable query pattern | Key order and selectivity matter; wider keys increase overhead |
| Partial index | A stable subset is queried frequently | Query must imply predicate; generic parameterized plans can complicate that proof |
| Covering index with `INCLUDE` | Needed values are available in the index | Index-only execution also depends on visibility information; not guaranteed |
| Expression index | Queries consistently use an expression such as `lower(email)` | Query/operator/collation semantics must match; expression adds write work |
| GIN | Suitable membership/text/JSON operators | Not a drop-in replacement for ordered range scans |
| BRIN | Large tables whose values correlate with physical layout | Coarse page-range summaries require rechecks |

[Partial indexes](https://www.postgresql.org/docs/18/indexes-partial.html), [index-only scans](https://www.postgresql.org/docs/18/indexes-index-only-scans.html), [index types](https://www.postgresql.org/docs/18/indexes-types.html).

## Correctness and operations

PostgreSQL creates an index for a primary key or unique constraint. It does not automatically create an index on every referencing foreign-key column. Such an index can help joins and checks during parent modifications, but needs a workload justification. [Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html)

A nonunique performance index does not enforce uniqueness. An index build has CPU, I/O, locking and storage implications. Ordinary `CREATE INDEX` blocks writes while it builds; `CREATE INDEX CONCURRENTLY` has different operational constraints and failure cleanup and cannot run inside a transaction block. This guide's tiny fixture uses ordinary creation; it is not a production migration recipe. [CREATE INDEX](https://www.postgresql.org/docs/18/sql-createindex.html)

## Interview practice

[INFERRED] Intermediate, backend/SDE/data, design or query optimization.

1. **IDX-01:** Explain the history index above. Why does `LIMIT 20` not make every unindexed query cheap?
2. **IDX-02:** A selective query becomes slower after an index is added. What evidence do you collect?

<details><summary>Expected reasoning and followups</summary>

1. Matching ordering permits early stopping when the chosen path provides it. Without useful ordering/access, the database may inspect or sort many candidates before knowing the newest 20. Explain customer equality, status subset and tie-breaking. Followup: how would an all-status query change the index choice?
2. Compare actual plans and realistic parameters, estimated/actual cardinality, heap visits, cache state, writes and maintenance. Do not force an index merely because one exists. Followup: what if only a few customers account for most rows?

Common mistakes: asserting all sequential scans are bad, ignoring index write cost, equating index-only with zero heap access, and claiming benchmark results from the toy fixture. Related: [query diagnosis](Query_Optimization.md), [SQL](SQL.md).

</details>

## Checklist and last verified

- [ ] Name query filters, ordering and expected cardinality.
- [ ] Compare read benefit with write/storage cost.
- [ ] Explain why a planner might correctly choose a sequential scan.
- [ ] Distinguish version-specific features from locally validated behavior.

Last verified **2026-11-02**: cited PostgreSQL 18 documentation and fixture DDL/query checks on the runtime documented in [validation](../validation/database/README.md). No performance benchmark is claimed.
