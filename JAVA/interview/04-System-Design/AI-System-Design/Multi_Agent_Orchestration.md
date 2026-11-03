# Design bounded multi-agent orchestration

## Candidate prompt

**AI-SD-06 — Advanced; AI/platform; architecture; company-neutral.** A research task can split into independent subquestions. Compare one model call, a fixed workflow and multiple workers. Do not assume more agents improve quality.

<details><summary>Reference decomposition</summary>

A coordinator owns the task graph, total budget, deadlines and final answer. Each worker receives a bounded subtask, explicit source permissions, output schema and evidence requirements. Workers return findings with provenance, uncertainty and tool-cost metadata. A merge/validation stage resolves contradictions against evidence; a majority vote is insufficient when workers share models or sources.

### State and flow

Accept one job → persist a small task graph → allocate budgets → dispatch independent ready tasks → collect validated results → cancel or expire missing work → synthesize supported findings → record completion or partial result. The task store, not worker memory, determines task status. Single ownership or compare-and-swap resolves races when duplicate completion events arrive.

Dependencies determine latency. If two independent stages take illustrative 2 and 3 seconds, ideal parallel completion is max(2,3)=3 seconds plus dispatch/merge overhead; a dependent 4-second synthesis makes the critical path at least 7 seconds before other overhead. Work/cost still includes both branches. Shared rate limits and contention can make the real result slower.

### Bound amplification

Give the root job a hard total call/depth/concurrency budget that children cannot reset. If each of three levels spawned three children, uncontrolled growth would be large even with a small local fanout; calculate the full graph, including the root. Reserve budget for merge/evaluation and recovery instead of spending it all on workers.

Parallel workers that can write must not independently perform the same side effect. Route writes through a single action owner and the execution-time authorization/idempotency protocol described in [the agent platform](AI_Agent_Platform.md). Consensus text is not a commit record.

### Failure and evaluation

Specify required versus optional tasks, partial-result policy, cancellation propagation, late-result handling and retry ownership. A timed-out worker may still be running; track attempts and discard stale state transitions while reconciling any external effects. Evaluate whether decomposition improved held-out task success under comparable total budget and latency. Inspect conflicting evidence and correlated failures explicitly.

A deterministic fanout/fanin pipeline may be sufficient when subquestions are known. Dynamic delegation adds flexibility but also unpredictable scope and a larger attack surface. Keep this as an advanced extension after a reliable single workflow.

</details>

## Followups and checklist

What if all agents agree on one unsupported statement? Agreement supplies no missing source. What if an optional branch misses the deadline? Return a labeled partial result only if the product contract allows it.

- [ ] The root owns aggregate budget and final validation.
- [ ] Critical path and total work are calculated separately.
- [ ] Duplicate events, late workers and write ownership are handled.

## References

- [Anthropic: Building effective agents](https://www.anthropic.com/research/building-effective-agents).
- [AWS Builders Library: timeouts and retries](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
