# SQL interviews: grain, ordering and explicit semantics

## What to learn

SQL questions assess set/bag semantics, joins, aggregation, windows and the ability to verify a result. Start by stating what one row means. A syntactically valid query can be wrong because it multiplies rows, changes an outer join or chooses an arbitrary tie.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Write keys and expected multiplicity before joining. Aggregate the many-side to the intended grain when required; SUM(DISTINCT amount) is not a general fan-out repair because equal legitimate amounts exist. Understand NULL as unknown and use an explicit missing-value policy. Windows retain rows while computing across partitions; ORDER BY inside a window is not a guarantee of final result ordering. A latest-row query needs a deterministic tie rule, such as event_time then unique event ID.

## Worked reasoning

Fixture: order O1 has two items of 100 each and two tag rows. A direct join of items to tags produces four rows and a naive sum 400 instead of 200. Summing distinct amount produces 100, also wrong. Aggregate item totals per order before attaching tags, or use an existence test if tags are only a filter. The correct answer depends on whether the output is one order row or one order-tag row; derive that contract first.

## Tradeoffs and failure boundaries

Do not claim an index makes every filter cheap. Inspect selectivity, key order, data distribution and the actual plan; a plan estimate is not a measured runtime. Window frames can affect running aggregates; say whether peers belong together or rows need a unique order. Use parameterized inputs and scoped access in application SQL.

## Attempt first — P7-DATA-SQL-INTERVIEW-PREPARATION

Events A and B for one entity share a timestamp; B has a higher unique ID. A prompt asks for one latest event and a final list sorted by entity. What rules must appear?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Partition by entity, rank by timestamp descending and ID descending using a one-row ranking policy, select the first row, then order the final result by entity. This enforces the stipulated tie policy rather than claiming the higher ID represents later real-world time. Trace empty input, one event and equal timestamp before optimizing. No native query was executed here.

**Followups:** When should ties all remain? What changes if timestamps may be NULL?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [PostgreSQL window functions](https://www.postgresql.org/docs/18/tutorial-window.html) — PostgreSQL18; page date unstated; Partition/order semantics; no native SQL execution.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
