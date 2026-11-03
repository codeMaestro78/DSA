# Multi-agent systems and coordination

## Start with a reason for multiple agents

Multiple model-controlled workers can divide independent tasks, isolate context or apply distinct tools. They also add calls, coordination, failure states and reconciliation. A single bounded workflow may be better when the task is small or the workers share the same blind spots. Agreement among models is not independent evidence.

Useful topologies include a supervisor assigning bounded tasks, a fixed parallel map/reduce flow and role-specific handoffs. A free-form group chat is not automatically a well-defined execution protocol. State who owns the final decision and shared state.

## Worked research flow

A coordinator asks one worker to inspect backend code and another to inspect UI behavior. Each receives the same objective, evidence rules and scope, then returns findings with sources, assumptions and unresolved questions. The coordinator checks contradictions against actual evidence instead of merging every statement. If a worker times out, the final answer states the missing coverage.

The example is a hypothetical engineering process, not a company interview report. Distinct roles help coverage only when their work is genuinely independent and their outputs are reviewable.

## Contracts and budgets

A task record needs ID, owner, inputs/version, allowed tools, deadline, cost/step budget and output schema. Shared writes need a single owner or explicit concurrency control. Handoff should transfer relevant state and authority, not every credential or unrelated private record. Receiving a message from another agent does not make its content trusted instructions.

Apply a request-level budget across workers; ten agents with individual limits can still exceed the user's total budget. Bound fan-out, recursive delegation and retries. Cancellation should propagate and durable work should have a recovery/ownership policy.

## Failure and trust boundaries

Workers may cite nonexistent evidence, duplicate work or disagree because they used different source versions. One worker's tool result can inject instructions into another's context. Validate outputs, preserve provenance and enforce permissions at each actual tool call. Do not grant a reviewer write access merely because its role is called reviewer.

## Evaluation

Compare a multi-agent design against a simpler baseline on task correctness, evidence coverage, disagreement resolution, latency and cost. More tokens or longer discussion is not a quality metric. Use failure injection such as one missing worker, conflicting outputs and a stale source. This phase provides architecture reasoning, not an executed distributed agent platform.

## Interview practice — P3-AI-18

**Question:** Two agents agree on an unsupported claim. Does that increase confidence enough to publish it?

- **Category/topic:** AI engineering / Multi-agent systems and coordination.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** No. Inspect source support, scopes and shared assumptions. Model agreement can reflect correlated errors; the coordinator remains responsible for evidence and unresolved gaps.

**Followup:** How would you bound recursive delegation and shared-state conflicts?

**Common mistake:** Equating consensus, worker count or role labels with independent verification.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Yao et al.: ReAct](https://arxiv.org/abs/2210.03629) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [LangGraph persistence](https://docs.langchain.com/oss/python/langgraph/persistence) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
