# Data warehousing: grain, history and consumable truth

## What to learn

A warehouse organizes data for repeatable analytical questions. Its central contract is the grain of each fact and the time meaning of dimensions, not merely a star-shaped drawing. Explain what business event or snapshot one row represents and which measures can be summed across which dimensions.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Distinguish transaction facts, periodic snapshots and accumulating lifecycle snapshots. Use stable source identities and explicit business/surrogate keys as needed. A changing dimension can overwrite current attributes or preserve time-bounded historical versions; choose based on whether reports ask current or as-was questions. Join historical facts to the intended dimension version with nonoverlapping validity ranges and clear boundary conventions. Do not silently reclassify all past facts when a current attribute changes.

## Worked reasoning

Fixture: customer C belongs to region East in January and West from February. A January purchase of 100 should remain East in an as-was report, while a current-territory report may intentionally attribute it to West. Both can be valid but answer different questions. A dimension with two overlapping January-valid versions would duplicate that purchase when joined; quality checks must assert the intended one-to-one match.

## Tradeoffs and failure boundaries

Adding an aggregate table improves some reads but creates a freshness/reconciliation dependency. Late facts and corrected dimensions need deterministic reprocessing, lineage and consumer communication. Restrict row/column access by actual data policy; a curated table is not automatically public. Record definitions and ownership so two dashboards do not label different measures “revenue.”

## Attempt first — P7-DATA-DATA-WAREHOUSING

An order fact has one row per order, but a report groups by product and joins order-lines. Which risk appears?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Order totals repeat across product lines. Decide whether to allocate the total by an explicit rule or use line-level measures at line grain; do not sum repeated order totals or use DISTINCT as a generic repair. A reconciliation test should compare the same defined measure/grain and explain legitimate differences.

**Followups:** Which measure is not additive over time? How does a dimension correction affect a published aggregate?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Grain / Kimball Group](https://www.kimballgroup.com/data-warehouse-business-intelligence-resources/kimball-techniques/dimensional-modeling-techniques/grain/) — author/publisher technique; page date not stated; Explicit fact-table grain and aggregation discipline.
- [PostgreSQL window functions](https://www.postgresql.org/docs/18/tutorial-window.html) — PostgreSQL18; page date unstated; Partition/order semantics; no native SQL execution.
- [dbt incremental models](https://docs.getdbt.com/docs/build/incremental-models-overview) — dbt Labs; rolling docs; Incremental-model mechanism; actual merge support depends on adapter/configuration.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
