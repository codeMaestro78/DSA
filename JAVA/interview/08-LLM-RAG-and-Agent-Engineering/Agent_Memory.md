# Agent memory: state, retrieval and authority

## Different meanings of memory

Conversation context is the material sent to the model now. Thread state is the application's current workflow/conversation record. Long-term memory is retained information available across interactions. Model weights are another form of learned representation, but an application cannot treat them as an auditable per-user database.

Separate authoritative facts from summaries, inferred preferences and temporary observations. A user's current billing address belongs in an authoritative application record with an update contract, not only in a model-generated summary. Stored memory should preserve source, timestamp, owner, scope and confidence where relevant.

## Worked conflict

A user once preferred concise replies, but now asks for a detailed derivation. The current request should influence the response rather than an old preference overriding it. More consequentially, an old approval to send a report does not authorize future reports or a modified recipient list. Memory supplies context, not permanent permission.

A summary can compress history but loses detail. If a tool operation is pending, retain its stable operation ID and status in structured state. A summary saying “payment succeeded” is not a substitute for the provider/database outcome.

## Storage and retrieval policy

Use thread identifiers and tenant/user boundaries appropriate to the data. Retrieval over memory must filter by current permission before assembling context. Cross-thread storage needs deliberate scope; sharing everything globally can disclose private information. Deletion must cover summaries, embeddings, caches and retained derivatives according to the application's policy.

The inspected LangGraph persistence documentation separates thread-scoped checkpointers from cross-thread stores. That distinction is useful, but the chosen backend, retention, consistency and access controls remain application responsibilities. A memory feature does not itself implement privacy or trustworthy fact resolution.

## Evaluation and failure cases

Test outdated preferences, contradictory facts, same-name users, revoked permissions, deletion and unsupported inferred facts. Measure whether the right fact is retrieved and used appropriately, not merely whether a memory was written. Track false memories and sensitive-data retention separately from task success.

Longer retention may improve continuity while increasing storage, staleness and privacy burden. Default to the smallest useful retained state and a clear update/deletion policy. Do not claim a generic memory framework guarantees accurate recollection, permanent learning or compliance.

## Interview practice — P3-AI-17

**Question:** How should an agent handle a remembered fact that conflicts with current authoritative state?

- **Category/topic:** AI engineering / Agent memory: state, retrieval and authority.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Resolve against the defined authority/version contract, retain provenance and correct/invalidate stale derived memory. Do not let a model summary override current business state or permission.

**Followup:** What must deletion invalidate beyond one conversation row?

**Common mistake:** Treating remembered instructions as permanent authorization or summaries as lossless facts.

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
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
