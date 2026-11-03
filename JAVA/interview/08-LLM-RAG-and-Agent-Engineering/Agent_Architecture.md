# Agent architecture and bounded control

## Define the control boundary

An agent is an application in which model decisions influence a sequence of actions. A deterministic workflow with one model extraction step may be simpler and more reliable for a fixed process. “Agent” does not imply autonomy without constraints, and a framework's name does not define persistence, authority or recovery.

A useful architecture separates planner/model adapter, state store, policy/validator, tool executor, event log and user-facing result. The model proposes; trusted application code decides what can execute. Model text and retrieved documents remain lower-trust inputs at the action boundary.

## State machine

Use explicit states such as accepted, planning, awaiting-tool, executing, awaiting-approval, completed, failed and cancelled. Persist transitions that must survive process loss before exposing them as durable. An in-memory task can continue while a process lives but is not automatically recoverable after restart.

Worked trace: a travel assistant searches options, proposes a booking, waits for a scoped approval, then calls a booking service. Approval should bind the specific action, payload and relevant version/price constraints. It is not a blanket grant for future changed proposals. Revalidate authority and current constraints at execution.

## Budgets and stop conditions

Set limits on steps, total tokens/cost, wall-clock deadline, tool concurrency and output size. A finite number of steps does not bound wall-clock time if one tool hangs. Enforce tool deadlines and propagate cancellation, while recognizing that cancellation cannot undo an already committed external effect.

The [local bounded loop](../validation/ai/core_examples.py) is a synchronous simulation over precomputed proposals and a read-only sum tool. It checks allowlisted tool names, exact argument keys, numeric limits and replay conflicts. It does not run a model, persist checkpoints, supervise a real process or implement resource-level authorization.

## Recovery and side effects

A crash after an external action but before local acknowledgment leaves an unknown outcome. Use stable operation identities and provider idempotency/reconciliation where supported. Checkpointing orchestration state cannot atomically include an unrelated provider's operation. Replay must not blindly repeat a non-idempotent effect.

## Evaluate the architecture

Test invalid tool names/arguments, changed replay payloads, budget exhaustion, denied resources, stale approvals, process restart and unknown outcomes. Measure completed user tasks under permission/cost constraints, not only tool-call validity. Multi-step demonstrations and successful local arithmetic tests do not prove production agent reliability.

## Interview practice — P3-AI-14

**Question:** Why is a persisted graph checkpoint insufficient to guarantee one external payment?

- **Category/topic:** AI engineering / Agent architecture and bounded control.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** A checkpoint and provider effect are separate commit domains. Crash/timeout ordering can leave an unknown result. Use a provider/business idempotency contract, durable intent and reconciliation, or explicitly accept a duplicate/loss tradeoff.

**Followup:** Which limits bound wall-clock time versus number of decisions?

**Common mistake:** Equating a checkpoint or tool schema with durable exactly-once external action.

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
- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
