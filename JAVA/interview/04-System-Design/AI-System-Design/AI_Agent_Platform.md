# Design an AI agent platform

## Candidate prompt

**AI-SD-05 — Advanced extension; AI/backend/platform; design; company-neutral.** Run bounded model-directed tasks with tools and optional human approval. Define supported tasks and side-effect permissions before choosing an agent framework.

<details><summary>Reference execution model</summary>

### Durable state and authority

A job API accepts a task, principal and budget, stores intent and returns a job ID. The durable job store owns lifecycle, step sequence, attempt IDs, accepted action proposals and terminal status. A worker leases work but must assume it can restart or overlap after a lease expires. A state-version compare-and-swap prevents stale transitions; external effects require a separate action protocol.

The tool registry maps permitted names to narrow adapters. Each call validates typed arguments, binds current identity, rechecks permission, bounds result size and records the attempted action. Tool output and retrieved text remain untrusted data. A model instruction cannot expand the tool allowlist or create credentials.

### Normal step

Load current state → check deadline/cancellation/budget → assemble permitted context → call model → validate answer/proposal → apply policy → execute approved tool or request review → record observation → choose the next bounded step. Terminal conditions include completed, unsupported, budget exhausted, cancelled, permanent failure and needs-review. “Keep thinking until done” is not an execution policy.

### Approval and unknown effects

Approval is a durable decision over specific action arguments, principal and policy version. If state changes, revalidate before execution. A crash after an external write but before recording success leaves an unknown outcome. A stable provider-supported idempotency key or reconciliation can resolve it; replaying the model step is not a proof against duplicate side effects. Read-only tools can also expose sensitive information and need authorization.

### Operations and tradeoffs

Use bounded parallelism, total call/token/cost budgets, retry classification, checkpoint ownership and a recovery queue. Measure useful task completion and harmful/incorrect actions, not just successful model requests. Trace policy decisions and action IDs with redaction; keep privileged replay tools audited. Version prompts/tools/models and test a small adversarial task set before canary rollout.

Start with a deterministic workflow if steps are known. Add model-directed choice only where measured task performance benefits justify variance, complexity and cost. A framework checkpointer can support state persistence, but cannot supply all required business invariants or external idempotency.

</details>

## Practice followup

A worker lease expires while its tool request is still running. Does a second worker safely repeat it?

<details><summary>Expected reasoning</summary>

No guarantee follows from the lease alone. The first request may complete. Protect database transitions with versions and handle the external action with an appropriate idempotency/fencing contract or reconciliation. Do not assume an arbitrary provider honors a fencing token merely because the application created it.

</details>

## Checklist

- [ ] Durable state and current policy live outside the model.
- [ ] Step, action and provider attempt identities are separate.
- [ ] Approval, cancellation and crash recovery have concrete boundaries.

## References

- [Anthropic: Building effective agents](https://www.anthropic.com/research/building-effective-agents).
- [AWS Builders Library: timeouts and retries](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/).
- [OWASP: authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
