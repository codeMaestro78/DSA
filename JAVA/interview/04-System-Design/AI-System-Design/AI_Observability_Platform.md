# Design an AI observability platform

## Candidate prompt

**AI-SD-14 — Intermediate/Advanced; AI/platform; design; company-neutral.** Diagnose slow, expensive or incorrect AI requests without logging all private input by default. Distinguish online operational signals from delayed quality evaluation.

<details><summary>Reference telemetry model</summary>

### Correlation and boundaries

The application assigns request/run/attempt IDs and propagates them through retrieval, reranking, model calls and tools. Spans represent work boundaries; metrics summarize populations; controlled events/audit records capture policy/action outcomes. The workflow store remains authoritative for lifecycle. A trace is diagnostic evidence, not the sole durable job record.

Useful illustrative fields include tenant-safe routing context, model/prompt/tool/index versions, input/output token counts where available, queue/model/tool duration, retries and terminal status. These are application field descriptions, not an assertion about current OpenTelemetry attribute spelling. OpenTelemetry's former GenAI page points to a separate maintained repository; pin the conventions/instrumentation version before implementing exact names.

### Latency and cost diagnosis

Break user-visible time into queue wait, retrieval/reranking, context preparation, model first output, subsequent generation and postprocessing. Do not add stage p95 values and call the result end-to-end p95: the percentile populations and correlations differ. Trace individual requests, then measure the end-to-end distribution directly.

Cost attribution records actual provider usage when returned, retry attempts and configuration. Estimated token usage is not a billing invoice. A successful HTTP response may still be a failed user task; delayed labels can join run IDs for quality review without conflating network availability with answer correctness.

### Privacy and sampling

Default to metadata and bounded/redacted samples under explicit access/retention rules. Prompt/output content can include secrets, private documents or personal data; do not assume hashing everything creates anonymous telemetry. Avoid high-cardinality user text in metric labels. Keep privileged action audits in an appropriate protected store and include deletion/retention propagation in the data policy.

### Alerts and feedback

Define service-level indicators for eligible user outcomes and windows. Alert on meaningful consumption of the chosen error budget; also monitor outbox age, tool unknown outcomes, cancellation failures and spend spikes. Quality regressions may require sampled human review or scheduled evaluation, with alert ownership distinct from infrastructure on-call.

Compare failures by recorded configuration versions and slices. A trace showing slow retrieval supports a localized hypothesis; it does not prove a new cache will preserve authorization/freshness or solve the bottleneck under load. Validate the proposed change against quality and privacy constraints.

</details>

## Practice

Two stages each report p95=2 seconds. Is total request p95 necessarily4 seconds?

<details><summary>Expected reasoning</summary>

No. Percentiles cannot generally be added. Correlation, overlap, different request populations and other work matter. Measure complete request durations and use spans to explain the distribution. Similarly, sums of averages apply only to a properly defined serial path/population.

</details>

## Checklist

- [ ] Operational status, task quality and durable state are distinct.
- [ ] Telemetry is versioned, bounded, redacted and access-controlled.
- [ ] Alerts name a user-impact signal and an owner.

## References

- [OpenTelemetry GenAI conventions repository](https://github.com/open-telemetry/semantic-conventions-genai).
- [Google SRE: alerting on SLOs](https://sre.google/workbook/alerting-on-slos/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
