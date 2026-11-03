# LangChain: harness responsibilities and limits

## What the inspected documentation establishes

[OFFICIAL] The current Python overview describes LangChain's `create_agent` as a configurable agent harness around model, tools, prompt and middleware. That is a documented abstraction, not a statement that installing the package gives an application durable storage, safe tools or adequate evaluations. Version-pin the package and provider integration before implementing actual calls.

This chapter intentionally includes no unexecuted SDK snippet. Interface names and imports can change; the official page is the implementation reference. The local examples use standard-library code to make the control boundary visible without a framework dependency.

## Map concepts to responsibilities

A model adapter translates application requests into provider calls. A tool definition describes a callable contract. Middleware can shape inputs, limits or handling around the loop. State/history supports continuity. Your application still owns authentication, object permissions, secrets, source freshness, user-visible lifecycle, retention and the evidence needed to accept results.

Do not bury a business invariant inside a prompt merely because a framework makes prompt configuration convenient. A transfer limit or tenant boundary belongs in trusted execution logic and data constraints where appropriate.

## Worked integration design

A support assistant has a read-only knowledge search tool and a ticket-creation tool. The harness may choose the next tool, but the executor verifies the current user, allowed tenant, normalized payload and duplicate-operation key. A cancelled request stops future scheduling where possible; a ticket already created needs a known outcome, not a repeated blind call.

Record tool inputs/outputs with sensitive-field controls, stable IDs and model/prompt/tool versions. A framework trace can show an execution path; it does not by itself prove that the path was authorized or the final answer supported.

## Choose framework versus explicit code

A harness can reduce integration boilerplate and standardize common interfaces. Explicit application code can be easier to audit for a small fixed workflow. Compare complexity, persistence needs, testability, observability and upgrade burden. Avoid using a legacy tutorial's import path as proof of the installed version's behavior.

## Validation plan

Test adapter boundaries with deterministic fakes first, then a bounded provider integration only when authorized and configured. Check denied tools/resources, invalid structured data, timeouts, duplicate effects and total request budgets. This phase does not install LangChain or run a hosted model; the framework discussion is source-grounded guidance, not a verified integration.

## Interview practice — P3-AI-19

**Question:** Which responsibilities remain after adopting an agent harness?

- **Category/topic:** AI engineering / LangChain: harness responsibilities and limits.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Identity, resource authorization, state/side-effect contracts, retention, budgets, evaluation and operational recovery remain application responsibilities. Name the exact integration/version before claiming behavior.

**Followup:** When is a fixed explicit workflow easier to maintain?

**Common mistake:** Treating a framework overview or trace as proof of production reliability.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [LangChain overview](https://docs.langchain.com/oss/python/langchain/overview) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
