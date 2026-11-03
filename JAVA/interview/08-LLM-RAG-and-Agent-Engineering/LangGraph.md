# LangGraph persistence and replay boundaries

## Documented persistence model

[OFFICIAL] The inspected Python persistence documentation describes checkpointers for thread-scoped graph state and stores for application-defined information across threads. Persistence can support continuity, interruption and recovery when configured with an appropriate backend. The older durable-execution URL redirected to the persistence page during this research pass.

Do not infer that an in-memory checkpointer survives process loss or that every external action is included atomically in a checkpoint. Backend durability, deployment ownership and execution/replay rules require version-specific configuration and tests.

## State and transition design

Define what a node consumes, what state it updates, what events it emits and whether it has external effects. State reducers/merge rules must preserve the intended invariant when branches update concurrently. A graph diagram alone does not specify who owns a conflicting field.

Worked flow: retrieve → compose answer → human review → publish. A checkpoint before review can let a user resume. If publishing commits externally before the next checkpoint and the worker crashes, replay may encounter an unknown publish outcome. A stable operation identity and reconciliation are still needed.

## Replay-safe work

Separate deterministic computation from operations requiring independent idempotency. Persist enough inputs and version information to interpret resumed work. If a tool's meaning, prompt or source version changes while a run is paused, define whether to resume the old contract, migrate or restart. Do not silently replay an approval under a changed payload.

Human approval needs durable state and a binding to the exact proposed action. A review UI that merely displays a message without recording the decision/version does not establish recoverable approval semantics.

## Failure tests

Stop a worker before and after a checkpoint, interrupt while a tool result is unknown, replay the same event, and submit concurrent updates. Verify the chosen backend's actual behavior and the application's recovery policy. Distinguish event publication from durable event persistence; reconnecting a UI is not equivalent to recovering execution.

## Adoption tradeoff

An explicit state graph can make branching and recovery easier to reason about, but introduces schema/versioning and operational responsibilities. For a fixed two-step transformation, simpler code may suffice. This phase does not install or execute LangGraph; its local bounded-loop test demonstrates only a small provider-neutral contract and does not claim checkpoint/replay validation.

## Interview practice — P3-AI-20

**Question:** A node creates an external ticket and the process dies before the next checkpoint. What must recovery account for?

- **Category/topic:** AI engineering / LangGraph persistence and replay boundaries.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** The ticket may exist despite the missing local checkpoint. Reconcile by stable operation identity or rely on a documented provider idempotency contract; do not blindly repeat the effect.

**Followup:** What changes when a paused run resumes after a tool schema upgrade?

**Common mistake:** Equating state persistence with atomic external effects or assuming an in-memory backend is durable.

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
- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
