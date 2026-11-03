# AI security through trust boundaries

## Threat model the application

Identify actors, sensitive resources, lower-trust inputs, allowed actions and the trusted enforcement points. A model application can read documents, call APIs, render generated text and retain memory; each crosses a different boundary. Security conclusions need concrete principals, resources and outcomes, not only a list of model failure terms.

Common risks include prompt injection, unauthorized retrieval, overly broad tools, secret exposure, unsafe generated-code execution, output injection in a UI and unbounded resource consumption. These are threat classes, not confirmed vulnerabilities in this repository. This chapter is educational guidance, not a security audit.

## Worked tenant boundary

Tenant A uploads a document containing instructions to retrieve Tenant B's records. The document is lower-trust content. The application should retrieve only authorized records, and every actual tool/resource request must independently enforce the caller's scope. A model refusing the instruction is useful behavior but not the sole protection for Tenant B's data.

Keep credentials in trusted execution components with least privilege. Do not put secrets in prompts and then rely on an instruction not to reveal them. Retrieved text can be valid evidence while still containing hostile instructions; provenance does not make every sentence authoritative policy.

## Tool and output controls

Use narrow typed operations, resource-specific permission checks, bounded arguments/output and operation identities for side effects. Avoid unrestricted shell/SQL execution from model text. If code execution is a product feature, it requires a real isolation design with explicit filesystem/network/resource boundaries; a Python `try/except` is not a sandbox.

Render generated text safely for the UI context. A model output treated as HTML, SQL or a shell command can cross a language/execution boundary. Validate or encode for that context; a general “safe output” label is not sufficient.

## Evaluation and operation

Use dummy tenants/data for local tests. Test direct/indirect instructions, cross-tenant IDs, stale permissions, replayed approvals, malformed tool data and overload. Record the exact boundary and observable outcome. Do not probe real users, production systems or external services to demonstrate a hypothetical flaw.

Log enough to trace decisions while redacting secrets and limiting sensitive content retention. Define incident response, key rotation and affected-artifact deletion. Passing the local sum-tool tests establishes only that small schema/allowlist contract, not complete agent security or production isolation.

## Interview practice — P3-AI-25

**Question:** Why should permission enforcement remain outside the language model even if an injection detector performs well?

- **Category/topic:** AI engineering / AI security through trust boundaries.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Classifier/model behavior can fail or change. The trusted executor/data layer must enforce resource authority independently so lower-trust text cannot grant access.

**Followup:** Which boundary is crossed if generated Markdown is rendered as raw HTML?

**Common mistake:** Declaring the application secure from a refusal demo or confusing exception handling with OS isolation.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [MCP 2026-07-28 specification](https://modelcontextprotocol.io/specification/2026-07-28) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
