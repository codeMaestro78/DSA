# Query optimization: diagnose before changing

## Overview

The goal is correct results within a workload's latency and resource budget. An interview answer should show how evidence narrows a hypothesis. “Add an index” is a possible intervention, not a diagnosis.

Prerequisites: [SQL grain and joins](SQL.md), [index access patterns](Database_Indexing.md).

## A repeatable investigation

1. State the symptom: endpoint p95, affected parameters, time window and normal baseline.
2. Separate pool wait, lock wait, server execution, transfer and application time.
3. Capture the exact query shape and representative bound parameters without leaking secrets.
4. Inspect a plan and compare estimated versus actual rows, including loops and filtering.
5. Change one justified factor, verify identical results and remeasure with a comparable workload.
6. Check write overhead, concurrency, rollback and regressions for other parameter values.

`EXPLAIN` shows a proposed plan. `EXPLAIN (ANALYZE, BUFFERS)` executes the statement and instruments it. Use read-only toy data for learning. `EXPLAIN ANALYZE UPDATE ...` performs the update; it is not a harmless preview. Rollback is not a universal way to undo sequence changes, external effects or triggered integrations. [Using EXPLAIN](https://www.postgresql.org/docs/18/using-explain.html)

## Worked read-only example

After loading [the fixture](../validation/database/fixture.sql):

```sql
-- example: history_plan
EXPLAIN (ANALYZE, BUFFERS)
SELECT id, created_at, amount_cents
FROM orders
WHERE customer_id = 1 AND status = 'paid'
ORDER BY created_at DESC, id DESC
LIMIT 20;
```

Read leaf scans upward to their parents. Distinguish estimated row counts from actual emitted rows. An actual row count in a repeatedly executed node is per-loop information; consider loops when assessing total work. Planner cost units are not elapsed milliseconds. The exact plan and timings vary with data, statistics and hardware; this guide intentionally does not invent a plan output for your environment.

In this six-row fixture, a sequential scan plus sort can be cheaper than index traversal. This establishes syntax/result reasoning, not production performance. To study performance, create a controlled, realistically skewed dataset separately and report its size, selectivity, versions, cache state, concurrency and measurements.

## Common hypotheses and discriminating evidence

| Observation | Possible explanation | Next check |
|---|---|---|
| Estimated 10 rows, actual 100,000 | Stale stats, skew, correlated predicates | Inspect statistics and parameter distribution; refresh appropriate stats |
| Many rows discarded after scan | Access path reads a broad set | Check selectivity, predicate form and index design |
| Large repeated nested-loop work | Too many outer rows or costly inner probe | Compare row estimates and inner access path |
| Slow sort with disk spill | Result too large for available memory | Reduce rows/width or evaluate memory settings under total concurrency |
| API slow, query execution fast | Pool wait, network, serialization or app work | Trace the full request; do not tune the wrong stage |
| Lock wait dominates | Long transaction or incompatible lock | Identify blocker, transaction age and reason for holding it |

None of these observations proves its first hypothesis. A sequential scan on most of a table may be optimal; a disk spill is not permission to raise memory globally without a concurrency budget.

## Rewrite without changing the result

Example: an ORM sends one customer query followed by one order query for every customer. A single joined/aggregated query or a bounded batch can reduce round trips. But naïvely joining multiple child relations can multiply rows. Preserve grain and validate counts before comparing performance.

For a timestamp filter, a half-open range `created_at >= start AND created_at < end` has clear boundary semantics. An expression on the column may need a matching expression index; do not assume every function predicate uses a plain-column index. Check the actual plan.

## Interview practice

[INFERRED] Intermediate, backend/data, technical debugging.

1. **OPT-01:** A query is fast in a console but slow in the API. Give three distinct hypotheses and a measurement for each.
2. **OPT-02:** Why can a query become slow for only one customer? Evaluate data skew and plan selection.

<details><summary>Expected reasoning, mistakes and followups</summary>

1. Pool wait: measure acquisition duration. Transfer/serialization: compare server runtime with request bytes and encode time. Different parameters/session/plan: capture representative execution context. Lock waits and app-level retries are further possibilities.
2. One customer may have vastly more orders, so average statistics or one generic plan can be misleading. Compare relevant parameter values and estimated/actual cardinalities before changing an index or plan policy. Followup: how would you avoid testing only a warm-cache small customer?

Mistakes: presenting estimated costs as time; comparing workloads with different result counts; running a mutating instrumented statement on shared data; assuming adding an index cannot regress writes. Related: [PostgreSQL](PostgreSQL.md), [transactions](Transactions_and_ACID.md).

</details>

## Checklist and references

- [ ] Identify which latency stage is actually slow.
- [ ] Explain one row-estimate discrepancy without claiming certainty.
- [ ] Check results before accepting an optimization.
- [ ] State the limitations of the measured workload.

Last verified **2026-11-02**. Primary source: [PostgreSQL EXPLAIN](https://www.postgresql.org/docs/18/using-explain.html), registered in [Phase 2 sources](PHASE2_SOURCES.md). Read-only plan syntax is validated; production performance is not.
