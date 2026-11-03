# SQL: reason about rows before writing syntax

## Overview and prerequisites

SQL describes a result over relations. Start by defining the **grain**: what does one output row represent? A customer, an order, or a customer-day? Most interview mistakes involving joins and aggregation start with an unstated grain.

This chapter assumes basic tables and primary keys. Examples use PostgreSQL syntax compatible with versions 16 and 18. The shared [fixture](../validation/database/fixture.sql) is fictional; amounts are integer cents, timestamps are UTC instants, and cancelled orders are not revenue. Validation scope is recorded in [the validation notes](../validation/database/README.md).

## Shared data

`customers(id, name)` contains `(1,Ada), (2,Lin), (3,Mira), (4,NoOrders)`.

| Order id | customer_id | amount_cents | status | created_at (UTC) |
|---|---:|---:|---|---|
| 101 | 1 | 1200 | paid | 2026-01-01 00:00 |
| 102 | 1 | 800 | paid | 2026-01-02 00:00 |
| 103 | 1 | 400 | cancelled | 2026-01-03 00:00 |
| 104 | 2 | 2000 | paid | 2026-01-02 00:00 |
| 105 | 2 | 700 | pending | 2026-01-04 00:00 |
| 106 | 3 | 500 | paid | 2026-01-02 00:00 |

The fixture has primary keys, a foreign key, non-null columns and a nonnegative amount check. Those constraints describe this toy domain; refunds, multiple currencies and payment settlement require a richer model.

## Build the result in logical stages

For reasoning, consider `FROM/JOIN → WHERE → GROUP BY/aggregates → HAVING → window calculations → SELECT/DISTINCT → ORDER BY → LIMIT`. This is a conceptual model, **not a forced physical execution order**. PostgreSQL can rearrange execution while preserving semantics. A subquery is useful when a later expression must be filtered in a separate logical stage. [SELECT semantics](https://www.postgresql.org/docs/18/sql-select.html)

A join creates one output combination for each matching pair. If an order has three items and two payments, joining both child tables can create six rows for that order. Summing order totals afterward overcounts. Aggregate each child to the required grain first, or use `EXISTS` when only existence matters.

## Worked example: paid totals including customers with zero orders

```sql
-- example: paid_totals
SELECT c.id, c.name,
       COUNT(o.id) AS paid_orders,
       COALESCE(SUM(o.amount_cents), 0) AS paid_cents
FROM customers AS c
LEFT JOIN orders AS o
  ON o.customer_id = c.id AND o.status = 'paid'
GROUP BY c.id, c.name
ORDER BY c.id;
```

Expected rows: `1|Ada|2|2000`, `2|Lin|1|2000`, `3|Mira|1|500`, `4|NoOrders|0|0`.

The paid condition belongs in `ON` because unmatched customers must survive. Putting `o.status = 'paid'` in `WHERE` removes the null-extended rows. `COUNT(*)` would count the unmatched placeholder row as one; `COUNT(o.id)` counts actual matched orders because order ids are non-null. `SUM` of no non-null inputs is null, so use `COALESCE` only when zero is the intended business meaning.

## NULL and anti-joins

NULL means an absent/unknown value, not an empty string or zero. `NULL = NULL` is unknown; use `IS NULL`. `WHERE` retains only true conditions. `2 NOT IN (1, NULL)` is unknown rather than true, so `NOT IN` is easy to misuse with nullable input.

```sql
-- example: no_paid_orders
SELECT c.id
FROM customers AS c
WHERE NOT EXISTS (
  SELECT 1 FROM orders AS o
  WHERE o.customer_id = c.id AND o.status = 'paid'
)
ORDER BY c.id;
```

Expected: `4`. `NOT EXISTS` asks whether a matching row exists and avoids the nullable-list trap. [Comparison and NULL semantics](https://www.postgresql.org/docs/18/functions-comparison.html)

## Window functions preserve input-row identity

Grouping collapses rows; a window function computes across a related set while preserving rows. The `PARTITION BY` group, window ordering and final result ordering have separate jobs.

```sql
-- example: top_paid_order
WITH ranked AS (
  SELECT id, customer_id, amount_cents,
         ROW_NUMBER() OVER (
           PARTITION BY customer_id
           ORDER BY amount_cents DESC, id ASC
         ) AS position
  FROM orders
  WHERE status = 'paid'
)
SELECT customer_id, id, amount_cents
FROM ranked
WHERE position = 1
ORDER BY customer_id;
```

Expected: `1|101|1200`, `2|104|2000`, `3|106|500`. A tie-breaker makes the selected row deterministic. `RANK()` instead returns equal ranks for ties; it can return multiple winners if filtered to rank 1.

```sql
-- example: running_paid_total
SELECT id, SUM(amount_cents) OVER (
  ORDER BY created_at, id
  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
) AS running_cents
FROM orders
WHERE customer_id = 1 AND status = 'paid'
ORDER BY created_at, id;
```

Expected: `101|1200`, `102|2000`. The explicit `ROWS` frame states row-by-row accumulation; default frames can include peers. [Window functions](https://www.postgresql.org/docs/18/functions-window.html)

## Interview practice

[INFERRED] Company-neutral exercises, Beginner–Intermediate, SDE/data/backend, coding round, 0–2 years.

1. **SQL-01:** Why does the paid-total query need `COUNT(o.id)`? What breaks with a payment/item join? Evaluates grain and join multiplicity.
2. **SQL-02:** Return customers with no paid orders. What changes if order status is null? Evaluates anti-join and three-valued logic.
3. **SQL-03:** Return all highest-value paid orders per customer, retaining ties. Evaluates ranking semantics.

<details><summary>Expected reasoning and followups</summary>

1. The left join emits a placeholder for an unmatched customer; count the non-null child key. Multiplying children requires preaggregation, not a blanket `DISTINCT` that may hide legitimate duplicate values.
2. The `NOT EXISTS` query above counts only status exactly equal to paid. A null status does not match paid and therefore does not prevent a customer from qualifying.
3. Use `RANK() OVER (PARTITION BY customer_id ORDER BY amount_cents DESC)` and filter rank 1. Do not include id in the rank ordering when ties in amount must remain tied. Add id to the final output ordering instead. Followup: if a customer has no paid orders, do you need a zero-row result or an explicit null winner?

Common mistakes: forgetting an output order, conflating `WHERE` and `HAVING`, converting missing data to zero without checking meaning, and ranking after the wrong filter. Related: [indexing](Database_Indexing.md), [query diagnosis](Query_Optimization.md).

</details>

## Preparation checklist

- [ ] State output grain and duplicate policy.
- [ ] Explain join cardinality with a two-row counterexample.
- [ ] Test zero matches, ties and null values.
- [ ] Explain the logical result before discussing indexes.
- [ ] Distinguish a tested result from a performance claim.

## References and last verified

Primary references are linked at each mechanism above and registered in [Phase 2 sources](PHASE2_SOURCES.md). Last verified **2026-11-02**: written examples and exact logical-result checks; see validation notes for runtime evidence.
