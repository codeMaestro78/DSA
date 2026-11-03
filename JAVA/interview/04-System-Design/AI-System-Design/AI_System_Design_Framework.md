# AI system-design framework

## Begin with an outcome

An AI system inherits ordinary API/database failures and adds uncertain output, variable inference time and model-dependent quality. Define the task before selecting a model or agent framework: who needs which outcome, what evidence supports an answer, what actions are permitted, and what happens when the system is uncertain?

For a document assistant, “produce fluent text” is insufficient. A useful contract might require an authorized source, evidence supporting each material answer claim, acceptable latency and an abstention route when evidence is missing. These are product decisions, not guarantees supplied by RAG.

## Choose the least complex adequate topology

| Need | Starting design | Extra obligation |
|---|---|---|
| Transform one input | One bounded model call | Validate output and handle refusal/truncation |
| Answer using private sources | Retrieval plus generation | Source permissions, relevance, provenance and deletion |
| Known sequence of steps | Deterministic workflow | Durable state, retries and recovery |
| Choose among actions dynamically | Bounded agent loop | Server-side policy and action budgets |
| Independent specialist work | Coordinator with bounded workers | Cancellation, merge/conflict policy and aggregate budget |

Anthropic's 2024 article distinguishes workflows from agents; its page flags subsequent tooling changes. Use that source for architectural patterns, not as current platform setup instructions.

## Contract, data and state

Define a stable request/job ID, tenant/principal, task input, allowed capability set, deadline and budget. The application owns authorization, durable workflow state and final status; the model proposes content or actions. Record model/prompt/retrieval/tool versions and evidence identifiers. Do not put unrestricted credentials or unnecessary personal data into context.

A useful lifecycle is accepted → queued → running → completed/failed/cancelled/needs-review, with action-level unknown outcomes when appropriate. A network timeout after a tool side effect does not establish that it failed. See [fault tolerance](../Fault_Tolerance.md) for retry boundaries.

## Evaluation precedes optimization

Define a versioned dataset and task-specific labels, including abstention, invalid input, adversarial content and permission changes. Separate retrieval relevance, answer support, tool correctness and final task success. Keep tuning data distinct from held-out evaluation. A model judge is a fallible measurement instrument; calibrate it against human labels and inspect disagreements.

Track user-visible time to first useful output, completion latency, error/abstention rates, cost per successful task and failure categories. Streaming a placeholder does not count as useful output by assertion. Compare quality and latency under the same workload and configuration.

## Worked budget

For an illustrative 8,192-token limit, reserve 1,024 instruction tokens, 512 question tokens, 1,536 output tokens and 512 safety/protocol overhead. Remaining retrieval/history allowance is 4,608 tokens. This arithmetic assumes all counted tokens share that limit; actual providers may have separate limits and accounting. Use the chosen tokenizer and provider contract before enforcing a real request.

## Practice

**AI-SD-01 — Intermediate; AI/backend; design; early career; company-neutral.** A model returns valid JSON with a wrong account ID. Which layer failed and what prevents an unauthorized action?

<details><summary>Expected reasoning and followup</summary>

Schema validity establishes shape, not identity or permission. Validate business semantics, bind the authenticated principal server-side and authorize the target resource at execution. A model-generated account ID cannot grant authority. Followup: how does a revoked permission affect a paused workflow? Recheck current authority before the action; do not rely on an old approval/context snapshot.

</details>

## Checklist

- [ ] Task quality, autonomy and failure tolerance are explicit.
- [ ] Each data/action boundary has an owner and policy.
- [ ] Budget, cancellation, replay and unknown outcomes are defined.
- [ ] Evaluation and rollback gates exist before optimization.

## References

- [Anthropic: Building effective agents](https://www.anthropic.com/research/building-effective-agents).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).
- [AWS Builders Library: timeouts and retries](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/).
- [OWASP: authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
