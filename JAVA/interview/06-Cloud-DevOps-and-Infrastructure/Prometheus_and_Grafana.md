# Prometheus and Grafana: metrics versus presentation

## Learning goal and scope

Prometheus records/query time series; Grafana queries data sources and presents dashboards/alerts. A chart does not repair missing instrumentation or an invalid denominator. Explain metric type, unit, labels, aggregation and data-source ownership before describing panel styling.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Counters accumulate events and may reset; gauges describe an instantaneous value. Histogram buckets/counts support distribution estimates across compatible populations, while client-computed quantiles generally cannot be averaged to recover a combined percentile. State bucket boundaries/resolution and query window; quantile estimates can be coarse. Use bounded categorical labels such as route templates rather than arbitrary URLs or user identifiers.

## Worked example

Fictional trace: instance A serves 100 requests and B serves 10,000. Averaging their individual error percentages gives equal weight to unequal populations and can misstate service reliability. Sum matching error counts and total counts over the intended interval, accounting for counter resets/missing series with the actual query semantics. A Grafana panel showing a smooth line is not evidence those conditions hold.

## Tradeoffs, failure and access boundaries

One new label with a million distinct values can create many series; cardinality compounds across label combinations. Restrict exporter/metrics access, avoid secrets in labels, and validate alert behavior with missing/stale samples. A saved dashboard should identify its data source, units, window and intended action.

## Attempt first — OPS7-PROMETHEUS-AND-GRAFANA

Two replicas report p95 values of 100 ms and 900 ms. Can their arithmetic mean be called the service p95?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

No. The underlying request counts/distributions and compatible histogram data are needed for a population estimate. A mean of quantiles is not the quantile of the union. If only these two numbers exist, state the evidence gap rather than manufacturing 500 ms as a measured service percentile.

**Followups:** What changes when buckets are too coarse near the SLO? Which labels belong in logs instead?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Prometheus metric naming](https://prometheus.io/docs/practices/naming/) — Prometheus; rolling documentation; scope: Metric/label design and cardinality concerns; no collector run.
- [Prometheus histograms and summaries](https://prometheus.io/docs/practices/histograms/) — Prometheus; rolling documentation; scope: Distribution aggregation mechanisms; no latency benchmark.
- [Grafana fundamentals](https://grafana.com/docs/grafana/latest/fundamentals/) — Grafana Labs; rolling documentation; scope: Visualization/query role; no dashboard rendered.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
