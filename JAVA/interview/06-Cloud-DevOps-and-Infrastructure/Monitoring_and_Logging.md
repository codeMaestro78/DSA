# Monitoring and logging: signals that answer a question

## Learning goal and scope

Observability begins with the user journey and an operational question. Metrics describe populations over time; logs record selected events; traces connect work across components. None is automatically complete or unbiased. A dashboard can show a correlation without identifying the cause of a regression.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Define a service-level indicator with population, success/failure classification and measurement boundary. Instrument request rate, errors, duration distribution and relevant saturation. Use correlation IDs/version metadata to connect a bad outcome to a path while controlling access/retention. Sampled traces or dropped logs need coverage/drop indicators; silence is not always health. Keep sensitive request content out of broad logs unless a justified controlled process requires it.

## Worked example

Fictional trace: median endpoint latency stays 100 ms, but the slowest 5% rise from 500 ms to 5 seconds. The median obscures a meaningful distribution change. Compare quantiles/histograms and affected endpoint/tenant/version slices, then correlate queue/database/provider spans. Do not add stage p95 values to claim an end-to-end p95; request-level correlation matters.

## Tradeoffs, failure and access boundaries

High-cardinality labels such as request IDs create resource pressure; use them in suitable logs/traces rather than unconstrained metric dimensions. Overly detailed logging raises privacy, storage and incident costs. Choose signals with a named investigation or alert action and test missing-telemetry behavior.

## Attempt first — OPS7-MONITORING-AND-LOGGING

A release reports zero errors because failed requests were removed before metric aggregation. How should the indicator be repaired?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Include all eligible attempts under a declared outcome/timeout policy and expose excluded/unknown counts. Separate product-quality labels from operational success without silently removing failure cases. Verify numerator and denominator using a small event fixture before interpreting a release comparison.

**Followups:** What can a sampled trace prove? How do you notice the collector itself failing?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [OpenTelemetry signals](https://opentelemetry.io/docs/concepts/signals/) — OpenTelemetry; rolling documentation; scope: Metrics/logs/traces as signals; no instrumentation run.
- [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Google SRE Workbook;2018; scope: Error budgets and alert intent; not a supplied production SLO.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
