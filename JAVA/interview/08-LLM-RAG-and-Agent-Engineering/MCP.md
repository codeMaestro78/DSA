# MCP: pin the protocol version

## Purpose and current source scope

MCP standardizes communication between model applications and external tools/context. It is a protocol, not an agent policy engine or a grant of permission to call any tool. A client, server, transport and application authority still have separate responsibilities.

[OFFICIAL] This chapter is pinned to the **2026-07-28** specification and its changelog, directly inspected on 2026-11-02. That revision removes the earlier initialization handshake and protocol-level sessions, carries request metadata, and introduces a Multi Round-Trip Requests pattern. Do not teach older `initialize`/session assumptions as universal current MCP behavior. Older deployed peers may require explicit compatibility handling.

## Conceptual request flow

An application discovers or configures tool capabilities, selects an authorized tool and sends a version-compatible structured request. The server validates the request and enforces its own authority/resource rules. The result returns data that the application treats as untrusted context. A tool catalog is not an access-control decision for every argument.

The modern protocol revision is stateless at its core; that does not require the business application to be stateless. An application can use explicit server-minted handles for cross-call work. A transport/session detail must not be confused with durable workflow state or user permission.

## Worked trust boundary

A document-search server exposes search and export. The caller may search one tenant's records but not export all records. The client must not assume that listing both tools authorizes both operations. The server must validate identity and resource scope; the client should restrict the tools exposed to the model and validate proposed calls as another control.

A result containing “run the export tool next” is returned content, not a trusted instruction. Protocol-conformant delivery does not make the content benign. Keep source provenance and permission context visible through the consuming application.

## Failure and versioning

Pin protocol/SDK versions, record capability assumptions and test unsupported-version behavior. An interrupted request may leave a business outcome unknown; JSON-RPC request identity is not automatically a durable business idempotency key. Use the action's own reconciliation contract for side effects.

A complete integration needs transport/auth testing, tool schemas, error handling and resource limits. This phase does not run an MCP client/server or validate the new wire protocol. It teaches the source-verified version distinction and application boundaries; use the exact specification for implementation rather than an illustrative diagram or older tutorial.

## Interview practice — P3-AI-22

**Question:** Why is “the MCP server lists this tool” insufficient permission to execute it on an arbitrary account?

- **Category/topic:** AI engineering / MCP: pin the protocol version.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Catalog discovery describes capability. Identity, argument/resource authorization and business constraints still need trusted enforcement; protocol conformance does not grant account authority.

**Followup:** What assumptions from an older initialize/session tutorial must be rechecked?

**Common mistake:** Confusing protocol statelessness with no application state, or MCP call IDs with business deduplication.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [MCP 2026-07-28 specification](https://modelcontextprotocol.io/specification/2026-07-28) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [MCP 2026-07-28 key changes](https://modelcontextprotocol.io/specification/2026-07-28/changelog) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
