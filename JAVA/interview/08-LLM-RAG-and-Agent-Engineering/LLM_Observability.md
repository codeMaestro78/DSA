# LLM observability and evidence

## Trace the actual request

A user request can include retrieval, multiple model calls, tools, retries, queues and streaming. Record a correlation ID and stage/operation identities so a slow or wrong result can be traced across boundaries. An application trace is an observation of what occurred, not proof of what should have occurred.

Useful application fields include request/run ID, tenant scope, stage, start/end time, outcome/error category, model/prompt/tool/index versions, token counts when provided, retry count and cache decision. These names are illustrative, not asserted OpenTelemetry semantic-convention field names. Pin the telemetry schema and library versions before relying on exact attributes.

## Latency definitions

Separate request acceptance, first provider token, first visible output, final output and durable completion. Streaming can make the UI feel responsive while total work remains expensive. Queue time may dominate latency even if inference is unchanged. Record stage overlap rather than adding durations that ran in parallel.

Worked trace: two retrieval calls run concurrently for 200 ms and 300 ms, followed by a 500 ms model stage. The sequential critical path is about 800 ms plus orchestration/network overhead, not 1,000 ms from summing both retrieval durations. These are synthetic numbers; a real trace needs consistent clocks and span boundaries.

## Quality and failure signals

Track supported-answer checks, abstentions, citation failures, invalid tool proposals, denied actions, budget stops and unknown side-effect outcomes. Distinguish user cancellation from provider error and a valid empty search from retrieval timeout. Aggregate success alone can conceal a failing tenant, language or document type.

Metrics need denominators and populations. Report requests, model calls and generated tokens separately; one request can create many calls. Averages hide tails, but p99 from a tiny sample is unstable. Include sample size/window and relevant exclusions.

## Privacy and cost

Prompts, retrieved passages and tool payloads can contain sensitive data. Prefer structured metadata, selective redaction, access controls, retention limits and explicit sampling. Do not log secrets or all customer documents just because a tracing tool makes it easy. Logging can itself create a new data store requiring deletion and security controls.

## Operational checks

Use traces to form a hypothesis, then test the implicated stage. Correlation between token count and latency does not prove causality. Observability does not replace a controlled evaluation or load test. This phase defines a reasoned instrumentation design; it does not deploy a collector, instrument a provider or validate a telemetry backend.

## Interview practice — P3-AI-28

**Question:** Why can an LLM service dashboard show healthy average latency while users report slow first output?

- **Category/topic:** AI engineering / LLM observability and evidence.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Inspect first-visible-output, queueing, buffering, tails and affected slices. The dashboard may measure a different boundary or hide a minority population.

**Followup:** How would you trace a request without storing sensitive prompt text?

**Common mistake:** Adding parallel span durations or presenting logs as proof of answer correctness.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [LangGraph persistence](https://docs.langchain.com/oss/python/langgraph/persistence) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
