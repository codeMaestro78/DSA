# Phase 5 AI Python exercise validation

The authored [helpers](core_examples.py) implement the exact local contracts for [QB-AI-001 and QB-AI-002](../../12-Interview-Question-Bank/AI_Engineer_Questions.md). Run from the repository root:

```bash
python3 interview/validation/questions-ai/check_examples.py
```

Observed **2026-11-02**, **Python 3.12.0**: **13 unittest test methods passed**, including eight invalid-attempt subcases and exact published-code source parity for both snippets. Tests use only the standard library, injected callbacks and fake waits; no network, credentials, provider, actual sleep or external side effect. The runner suppresses bytecode writing before importing the helpers.

| Helper | Contract | Tested behavior | Time / auxiliary space |
|---|---|---|---|
| managed_client | Acquire once; release once if acquisition succeeds; exceptions are visible | Success identity/lifetime, body failure, acquisition failure, cleanup failure | O(1) wrapper overhead / O(1), excluding callbacks and body |
| retry_read | Caller-declared safe read; 1–5 total attempts; only TransientError retried; injected waits 1,2,4,8 seconds | Falsey success; two failures then success; exhaustion and final exception identity; permanent failure; one/five attempt bounds; invalid type/value rejection before work; sleep failure | O(A) wrapper work / O(1) for A attempts, excluding callbacks; A ≤ 5 |

The context manager does not reclaim remote resources if release fails; a cleanup exception remains visible and may become the active exception. The retry wrapper is intentionally a small synchronous exercise, with no deadline, jitter, Retry-After parsing, provider-error classification, durable deduplication or cancellation. It cannot establish that an arbitrary operation is safe to retry. The documentation states these limits instead of presenting the helper as a production client.

No tests establish behavior for other question-bank designs, HTTP/SSE, a real browser, live authentication, prompt injection defense, retrieval quality or customer integrations. Their expected answers remain reviewed reference reasoning. [Question sources and scope](../../12-Interview-Question-Bank/PHASE5_AI_SOURCES.md).
