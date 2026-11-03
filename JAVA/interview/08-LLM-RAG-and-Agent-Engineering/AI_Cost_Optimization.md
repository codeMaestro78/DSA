# AI cost optimization under quality constraints

## Define the accounting boundary

Cost per user task can include model input/output, cached input under provider rules, embeddings, retrieval/reranking, storage, GPU/runtime, network and human review. A request may contain many calls or retries. Use the exact current provider/account prices when making a real estimate; this chapter asserts no live model price.

## Worked symbolic budget

Let pᵢ and pₒ be price per million input/output tokens. For I input and O output tokens, model cost is I·pᵢ/1,000,000 + O·pₒ/1,000,000 under that simplified tariff. If a workflow makes 3 calls with the same sizes, multiply that part by 3; if sizes differ, sum per call. Add retrieval/embedding and runtime costs separately. Subscription or reserved-capacity billing requires a different utilization model.

Example using **invented teaching rates**, pᵢ=$2 and pₒ=$8: I=2,000, O=500 gives $0.004+$0.004=$0.008 per call before other costs. At 3 such calls it is $0.024. These are arithmetic examples, not current vendor prices or a forecast of actual bills.

## Reduce unnecessary work

Eliminate redundant serial calls, repeated unchanged embeddings, irrelevant context and unbounded retries. Use task-specific routing only if its routing errors and fallback costs are evaluated. A cheaper model can cost more per successful task if it fails often or requires repeated repair.

Set maximum output, request-level tokens/cost, tool calls, attempts and deadlines. Track actual usage returned by the serving interface where available. Estimates can guide admission, but unknown usage on failed calls may still need accounting/reconciliation.

## Cache responsibly

Cache keys must reflect tenant/permission scope, semantic request identity, source freshness and model/prompt/tool versions as appropriate. A cache hit must remain authorized. Exact-match and semantic caches have different false-hit risks; evaluate answer correctness and invalidation rather than celebrating hit ratio alone.

For self-hosted inference, optimize utilization and capacity with quality/latency constraints. GPU memory, batching, replicas and idle capacity matter; lower precision or larger batches do not guarantee lower end-to-end cost on every workload.

## Evaluate the tradeoff

Report cost per successful supported task, not only per token. Include latency, failure/abstention rates and severe-regression slices. Compare under the same demand and output constraints. This phase validates no billing integration or provider tariff; use the formula as an interview reasoning tool and verify live prices before a real decision.

## Interview practice — P3-AI-29

**Question:** Why can a cheaper model increase cost per successful task?

- **Category/topic:** AI engineering / AI cost optimization under quality constraints.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Lower task success can cause retries, fallback, repair and review. Compare total workflow cost divided by accepted supported outcomes, with latency/quality constraints and actual price assumptions.

**Followup:** What must invalidate an answer cache after source permissions change?

**Common mistake:** Using invented example rates as current prices or ignoring failed/repair calls.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Kwon et al.: PagedAttention](https://arxiv.org/abs/2309.06180) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
