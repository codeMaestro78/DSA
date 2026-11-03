# Tool calling: proposal, validation and execution

## A tool call is a proposal

The model chooses or emits a tool name and arguments according to an interface. Trusted application code must validate syntax, permissions, resource scope and current business state before execution. A JSON schema can constrain structure; it cannot establish that an account belongs to the caller or that a transfer is appropriate.

[OFFICIAL] OpenAI's documentation distinguishes function/tool integration from structured user-facing output. Tool outputs are correlated to the relevant call identity in that interface. This chapter's local example is provider-neutral and makes no request to OpenAI or another model service.

## Contract and worked trace

For the local read-only `sum` tool, accepted arguments are exactly numeric x and y, finite and bounded in absolute value. Booleans are rejected even though Python treats bool as an int subclass. Unknown tools or extra fields fail before execution. A repeated call ID with the same payload returns the recorded result; a changed payload under that ID fails.

Example: call ID one with x=2, y=3 returns 5. Replaying the same call returns 5; reusing one with x=4, y=3 is a conflict. The cache exists only inside one function invocation. It is not durable deduplication across worker restart, and sum is a read-only computation with no external side effect.

## Tested excerpt

The following function is extracted unchanged from the tested [core module](../validation/ai/core_examples.py). It depends on `islice` imported by that module; use the linked module/test command rather than treating the excerpt as a complete program. The suite checks exact source parity with this published function.

```python
def bounded_loop(proposals, allowed, max_steps=4, cancelled=lambda: False):
    """Simulated read-only sum tool; proposals are precomputed, not model output.

    Exact dict keys/schema, allowlist and call-ID replay checks. Cooperative
    cancellation only between steps. Cache is in-memory, not durable dedup.
    """
    if type(max_steps) is not int or max_steps <= 0:
        raise ValueError('positive step budget required')
    seen, results = {}, []
    for proposal in islice(proposals, max_steps):
        if cancelled():
            return {'status': 'cancelled', 'results': results}
        if not isinstance(proposal, dict):
            raise ValueError('proposal must be a dict')
        if proposal.get('type') == 'final':
            if set(proposal) != {'type', 'text'} or not isinstance(proposal['text'], str):
                raise ValueError('invalid final result')
            if len(proposal['text']) > 4096:
                raise ValueError('final result too long')
            return {'status': 'complete', 'results': results, 'text': proposal['text']}
        if set(proposal) != {'type', 'id', 'tool', 'args'} or proposal['type'] != 'call':
            raise ValueError('invalid call fields')
        call_id, tool, args = proposal['id'], proposal['tool'], proposal['args']
        if not isinstance(call_id, str) or not call_id or len(call_id) > 128:
            raise ValueError('invalid call ID')
        if not isinstance(tool, str) or tool not in allowed or tool != 'sum':
            raise PermissionError('tool not authorized')
        if not isinstance(args, dict) or set(args) != {'x', 'y'}:
            raise ValueError('invalid argument keys')
        x, y = args['x'], args['y']
        if any(type(v) not in (int, float) or abs(v) > 1e6 or not math.isfinite(v)
               for v in (x, y)):
            raise ValueError('invalid numeric argument')
        payload = (tool, x, y)
        if call_id in seen:
            if seen[call_id][0] != payload:
                raise ValueError('call ID reused with different payload')
            value = seen[call_id][1]
        else:
            value = x + y
            seen[call_id] = (payload, value)
        results.append({'id': call_id, 'value': value})
    return {'status': 'budget_or_input_exhausted', 'results': results}
```

## Production boundary

A real executor needs resource-level authorization, operation-specific limits, credential scoping, execution deadlines, output-size limits and safe result handling. Never turn a proposed shell command or SQL string directly into unrestricted execution. Cancellation here is cooperative between steps; it cannot interrupt a running tool or undo a committed operation.

For side effects, use stable business operation identities and define unknown-outcome reconciliation. An API call ID is not automatically the provider's idempotency key, and an allowlist of tool names is not sufficient authorization for every argument/resource.

## Interview practice — P3-AI-16

**Question:** Why can perfectly schema-valid tool arguments still be unsafe?

- **Category/topic:** AI engineering / Tool calling: proposal, validation and execution.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** The referenced resource, authority, current state and business intent may be invalid. Validate those in trusted application code; schemas and tool-name allowlists enforce only part of the contract.

**Followup:** What happens after a timeout if the external operation may already have committed?

**Common mistake:** Treating structured output as authorization or an in-memory replay cache as durable exactly-once execution.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [OpenAI: function calling](https://developers.openai.com/api/docs/guides/function-calling) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OpenAI: structured model outputs](https://developers.openai.com/api/docs/guides/structured-outputs) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [OWASP: prompt injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
