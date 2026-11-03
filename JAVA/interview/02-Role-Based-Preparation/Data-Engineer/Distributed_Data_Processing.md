# Distributed processing: partitions, shuffle and time

## What to learn

Distributing a computation means partitioning work and coordinating data movement, failures and output. More workers help only if the limiting work can be divided and dependencies keep up. Teach local correctness first, then where partitioning changes cost and semantics.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Maps often run near independent partitions; grouping/joining by key can require shuffle. Examine key distribution, partition sizes, serialization, spill and actual stage times. A hot key can dominate one task even when total data is large. A broadcast join can avoid shuffling a sufficiently small side but requires a demonstrated memory/size fit. Adaptive optimizations do not remove the need to inspect the plan and workload.

## Worked reasoning

Fixture: ten partitions contain 100 records each except one containing 100,000 records for one customer. Adding ten workers leaves the dominant key work largely intact. Diagnose skew, then consider preaggregation, a contract-preserving two-stage salted aggregation or splitting an appropriate business unit. Arbitrary salting is wrong for an operation whose per-key ordering or nonassociative behavior must be preserved.

## Tradeoffs and failure boundaries

For streams, separate event time from processing/ingestion time, state retention, allowed lateness and output-update policy. A watermark represents a progress assumption, not certainty that no earlier event will ever arrive. Checkpoint/replay guarantees depend on engine mode and source/sink semantics; custom HTTP effects may duplicate even when an internal table result is replay-safe.

## Attempt first — P7-DATA-DISTRIBUTED-DATA-PROCESSING

A stream counts purchases per hour, then a purchase arrives two hours late. What must the contract specify before saying it is wrong to drop it?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Declare event-time window, accepted lateness, correction/finality policy, retained state and user-facing update semantics. If the input violates the accepted bound, quarantine/reconcile under an explicit policy. If arbitrary late correction is required, design retained history/backfill rather than promising bounded state and exact finality without assumptions.

**Followups:** Which reductions can be preaggregated safely? How can a faster join still change row multiplicity incorrectly?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Spark Structured Streaming overview](https://spark.apache.org/docs/latest/streaming/index.html) — Apache Spark; rolling latest docs; Incremental stream model; guarantees depend on mode/source/sink and custom effects.
- [Spark SQL performance tuning](https://spark.apache.org/docs/latest/sql-performance-tuning.html) — Apache Spark; rolling latest docs; Partitions, join planning and skew; no cluster benchmark.
- [Beam programming guide](https://beam.apache.org/documentation/programming-guide/) — Apache Beam; rolling docs; Event time, windowing and pipeline model; no runner tested.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
