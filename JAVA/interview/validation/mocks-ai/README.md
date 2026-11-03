# Phase 6 AI mock fixture validation

Run with the Python standard library:

```bash
python3 -B interview/validation/mocks-ai/check_examples.py
```

**2026-11-03, Python 3.12.0: 1,802 checks passed.** The runner extracts the exact marked `validate_incident` implementation from [the AI interviewer companion](../../13-Mock-Interviews/AI_Engineer_Mock_Interview_Interviewer.md), executes it, and parses synthetic fixture tables from all three candidate packets. These files contain spoilers.

Checks include exact types/keys/identity, boolean rejection, Unicode code-point intervals, invalid and huge offsets, no mutation, fresh output, 500 seeded spans and acceptance of a structurally valid but semantically wrong severity. That last check demonstrates the helper's deliberate limit, not acceptable incident semantics. A finite interval enumeration supplies an independent accepted-span oracle.

Synthetic arithmetic checks cover paired answerability/answered accuracy, RAG eligible Recall@2/exposure denominators, mean offered generation concurrency, nominal slot utilization and duplicate-event folding to a failed terminal state. The event-fold fixture is a local test calculation, **not a published client reducer or browser implementation**. It cannot establish real EventSource reconnect, authorization, retention, concurrency or worker behavior.

No model, provider, HTTP server, search index, browser, database or deployed security mechanism was run. Fixtures do not measure incident-label quality, production RAG quality or a live migration. Bytecode writing is disabled; no credentials or network are needed.

[Runner](check_examples.py) · [Source scope](../../13-Mock-Interviews/PHASE6_AI_SOURCES.md) · [Validation directory](../README.md).
