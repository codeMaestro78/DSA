# Guardrails and authoritative controls

## Separate categories of control

Input checks validate size, format and allowed content. Output checks validate structure, citations or policy. Tool controls enforce authorization and side-effect rules. Runtime controls bound time, memory, concurrency and cost. Human review can authorize a specific consequential action. Calling all of these guardrails is convenient, but their guarantees differ.

A classifier or model instruction is probabilistic evidence. A trusted authorization check and database constraint can enforce a defined invariant within their boundary. Do not present a prompt saying “never disclose secrets” as equivalent to keeping secrets out of the model's accessible context.

## Worked action flow

A model proposes creating a customer support ticket. Validate the proposal schema and size, authenticate the caller, check tenant/resource permission, normalize the payload and bind any required approval to that exact action/version. Persist intent and a stable idempotency key if the workflow requires recovery. Validate the tool result before rendering it.

If an output validator rejects the answer, do not automatically retry indefinitely. Track aggregate budgets and decide whether to repair, abstain or escalate. A repair step can itself introduce unsupported details; revalidate the final output under the same contract.

## Structured outputs

Provider-supported structured output can improve schema adherence under its documented model/interface constraints. It does not guarantee semantic truth, current permission or correct arithmetic. Handle refusal, incomplete output, transport errors and validation failures distinctly. Local application validation remains necessary even when the provider offers constrained generation.

This chapter uses the official OpenAI documentation only for that interface distinction; it makes no claim about a particular model's availability, hidden behavior or current price. No hosted structured-output request was run.

## Failure cases

An injection detector can miss an obfuscated instruction; a citation checker can validate a real URL while missing unsupported entailment; a PII filter can misclassify legitimate data. Evaluate false positives and false negatives under the actual task and fail-open/fail-closed policy. A broad blocklist is not a complete security boundary.

## Readiness and tradeoff

For each proposed guardrail, state what input it observes, what decision it makes, which principal/resource it protects, its failure behavior and how it is tested. Layers can reduce risk, but avoid claiming complete prevention from a set of uncalibrated classifiers. Security guidance here is conceptual; it is not an audit of a deployed application.

## Interview practice — P3-AI-24

**Question:** A schema validator accepts an answer containing a fabricated invoice amount. Which guarantee did it provide?

- **Category/topic:** AI engineering / Guardrails and authoritative controls.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Only the declared structural/type constraints, not factual correctness or authorization. Validate amount against authoritative data and the action contract, and distinguish provider refusal/incomplete output.

**Followup:** How can a repair retry change the risk or total budget?

**Common mistake:** Equating valid JSON, an injection score or human-looking text with a trusted business decision.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [OpenAI: structured model outputs](https://developers.openai.com/api/docs/guides/structured-outputs) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
