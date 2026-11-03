# A hypothetical ChatGPT-like product architecture

## Proof boundary

This filename is retained from the requested tree. **This is a hypothetical conversational AI product design, not a reconstruction of ChatGPT's internal systems.** No model topology, routing rule, database, deployment, private safety pipeline or actual traffic figure is asserted for OpenAI. User-visible behavior cannot establish internal implementation.

## Candidate prompt

**AI-SD-03 — Advanced extension; AI/platform; design; company-neutral.** Extend the [bounded chatbot](AI_Chatbot.md) to optional retrieval and tools while preserving permission boundaries. Clarify task types, permitted actions, conversation retention and acceptable errors before drawing components.

<details><summary>Hypothetical reference discussion</summary>

### Separate control and inference

The application control plane owns identity, entitlement, model configuration, tool registry and rollout policy. The request execution path loads that policy, constructs a bounded context and invokes inference. A model response may contain an answer or proposed tool arguments; it never edits the authorization policy. Deterministic validators mediate execution.

A routing layer can choose among model configurations using task requirements, measured quality, latency and budget. The route and fallback are versioned so evaluation can reproduce the decision. A cheaper model is not automatically an equivalent fallback; if quality or tool behavior is unverified, return an explicit degraded state or ask for review.

### Sources and tools

Retrieval returns authorized versioned chunks and evidence IDs. Tool calls go through narrow server-side adapters with typed validation, current resource permissions, deadlines and stable action IDs. Reads and writes have different consequences. Sensitive actions can require product-defined approval and an execution-time policy check. Approval must bind the actual arguments; changing an amount or recipient invalidates the approved action.

The conversation record stores user-visible messages and run status. Tool execution/audit records are distinct from untrusted model prose. Do not assume hidden model reasoning is a reliable or necessary audit trail; record observable inputs, decisions and effects with appropriate privacy controls.

### Deployment and evaluation

Version model selection, prompts, retrieval and tool contracts together as an evaluation configuration. Offline tasks cover direct answers, missing evidence, incorrect tool arguments and hostile retrieved text. A limited rollout measures task outcomes and budget use, with a tested route back to a known configuration. A model alias change can alter behavior even if application code is unchanged.

### Unknowns deliberately retained

This design does not establish training data, actual model architecture, internal safety systems or serving capacity of a named product. [LLM architecture](../../08-LLM-RAG-and-Agent-Engineering/LLM_Architecture.md) teaches general model mechanisms; [inference infrastructure](GPU_Inference_Infrastructure.md) teaches hypothetical resource budgeting. Keep those levels separate.

</details>

## Followups

Can a polished UI prove a particular orchestration design? No. What would establish a company-specific architectural claim? A scoped first-party disclosure, with its date and limitations. What makes the hypothetical design useful? Explicit contracts and failure reasoning that transfer to a system you actually own.

## Checklist

- [ ] Every named-product internal detail remains unknown unless sourced.
- [ ] The hypothetical control plane owns policy; inference cannot grant authority.
- [ ] Fallback quality and action approval are evaluated explicitly.

## References

- [Anthropic: Building effective agents](https://www.anthropic.com/research/building-effective-agents).
- [OWASP: authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [AWS Builders Library: timeouts and retries](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-02: source scope, illustrative flows and reasoning reviewed. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
