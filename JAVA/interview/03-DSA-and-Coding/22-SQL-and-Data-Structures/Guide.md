# SQL as relational problem solving

## Scope and recognition

SQL describes relations and their transformations rather than a required physical algorithm. Define row grain, keys, duplicate multiplicity, NULL policy and ordering before choosing a query. A join can multiply rows; a window preserves rows while attaching a calculation, whereas grouping changes grain. Query-plan cost depends on data, indexes, memory and engine.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

Orders O1=100,O2=100; O1 has tags a,b and O2 none. A left join creates three rows with amounts100,100,100. SUM(amount)=300 is wrong for the invoice-grain total200. SUM(DISTINCT amount)=100 is also wrong because different orders share an amount. Aggregate orders independently and use EXISTS for an optional tag predicate.

## Why the mechanism works

Specify each intermediate relation: one row per order before amount aggregation; one row per customer after grouping. For latest-per-device, ROW_NUMBER partitioned by device ordered by observed_at DESC,id DESC produces one deterministic winner if IDs are unique. SQL output order still needs a final ORDER BY. Default window frames can include peers; an explicit ROWS frame is needed when a row-by-row cumulative contract is intended.

## Baselines, complexity and tradeoffs

A hash aggregation may be near O(n) expected work when memory suffices; sorting/window execution often requires O(n log n), but a compatible index or spilling changes the plan. Do not infer an engine guarantee from SQL syntax. Correlated subqueries can be optimized; inspect EXPLAIN on a disposable fixture before making runtime claims.

## Practice — attempt before opening review

### DSA-22-01 — Top ranks and ties

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** Scores are90,90,80. State ROW_NUMBER, RANK and DENSE_RANK results under descending score; explain why row-number identity is incomplete.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

RANK gives1,1,3; DENSE_RANK gives1,1,2. ROW_NUMBER assigns1,2,3 but which90 row gets1 is unspecified without a secondary unique key. Add ID when deterministic selection is required. Followup: preserve ties versus select exactly k rows—they are different output contracts.

</details>

### DSA-22-02 — Filter unknown approvals

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** A boolean approved column has true,false,NULL. Select false or unknown rows and explain why approved<>true is insufficient.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Use the semantic predicate approved IS NOT TRUE; ordinary comparison with NULL yields unknown, which WHERE removes. Expected selected rows are false and NULL. Followup: placing a right-table predicate in WHERE after a left join can eliminate unmatched rows; reason from the preserved-row contract. No new native SQL execution is claimed here.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[PostgreSQL18 window semantics](https://www.postgresql.org/docs/18/functions-window.html) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
