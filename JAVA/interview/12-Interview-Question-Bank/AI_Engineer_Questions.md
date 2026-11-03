# AI engineer practical questions

These ten original **[INFERRED]**, company-neutral exercises are focused practice, not employer questions or encounter-frequency claims. Start with Beginner/Intermediate questions for 0–2 years; Advanced questions are extensions. These are individual exercises; complete role simulations belong to Phase 6. Attempt the prompt before opening its answer. Alternatives that preserve the stated contract can be valid.

Read the linked canonical lesson when a mechanism is unfamiliar. Each “Round” is a suggested practice format, not a company process. Difficulty is an editorial judgment, not a measured pass rate. For design/diagnosis questions, the answer is a reviewed reference approach, not a live integration result.

Last verified: **2026-11-02** — prompt contracts, answer reasoning, local links and question records reviewed. Only QB-AI-001/002 include locally executed code; other scenarios are reasoning exercises. [Source scope](PHASE5_AI_SOURCES.md). [Question-bank directory](README.md).

<a id="qb-ai-001"></a>

## QB-AI-001 — A client that must always close

| Field | Value |
|---|---|
| Category | Python resource lifetime |
| Difficulty | Beginner |
| Target roles | AI Engineer |
| Round | Suggested coding and review practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Python resource lifetime; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-002 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/AI-Engineer/AI_Production_Engineering.md); [Python context managers](https://docs.python.org/3.12/library/contextlib.html); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Implement a synchronous context manager around injected acquire() and release(client). Acquire exactly once. If acquisition succeeds, release exactly once after the body, including when the body raises. Do not suppress exceptions. If acquisition fails, release must not run. Assume release succeeds except in the explicit cleanup-failure followup. No real provider client is required.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Acquire before entering the try/finally that owns cleanup. Yield the acquired object inside that try and release that same object in finally. This distinguishes failure to obtain a resource from failure while using it. The success trace is acquire → body → release; the body-failure trace is acquire → body raises → release → original exception propagates. An acquisition exception bypasses both the body and cleanup because ownership was never established. The generator must yield exactly once on a successful entry. Do not catch the body exception merely to print it: swallowing it makes a failed operation appear successful to the caller. If cleanup itself raises while another exception is active, Python exposes the cleanup exception with the earlier exception as context; choosing a different cleanup-error policy requires an explicit contract. This helper manages lifetime, not timeouts or remote cancellation.

```python
from contextlib import contextmanager


@contextmanager
def managed_client(acquire, release):
    """Acquire once; release once after entry, including body exceptions."""
    client = acquire()
    try:
        yield client
    finally:
        release(client)
```


**Explanation:** The position of acquire determines which failures enter cleanup. A finally block is a local ownership rule, not proof that a remote resource was successfully reclaimed.

**Followups:** How would asynchronous acquisition change the protocol? How should a cleanup failure be reported without losing the body failure?

**Common mistakes:** Putting acquisition inside a try with an uninitialized client; yielding twice; returning from an exception handler and hiding failure.

**Validation:** The exact excerpt above is checked against [core_examples.py](../validation/questions-ai/core_examples.py). Focused [tests](../validation/questions-ai/check_examples.py) cover the prompt’s success, failure and boundary contracts with no network or actual sleep; see the [validation record](../validation/questions-ai/README.md). This does not validate a provider client, retry policy or deployed service.

</details>

<a id="qb-ai-002"></a>

## QB-AI-002 — Retry only the declared safe operation

| Field | Value |
|---|---|
| Category | Python retries and failure classification |
| Difficulty | Intermediate |
| Target roles | AI Engineer |
| Round | Suggested coding and review practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Python retries and failure classification; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-003 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/AI-Engineer/AI_Production_Engineering.md); [HTTP retry semantics](https://www.rfc-editor.org/rfc/rfc9110.html#section-9.2.2); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Implement retry_read(operation, sleep, attempts=3). The injected operation is a caller-declared safe read and either returns a value or raises. Retry only the synthetic TransientError, for 1–5 total attempts inclusive; reject booleans and other invalid attempt values before doing work. Before retry n+1 sleep 2**(n-1) logical seconds. Do not sleep after the final failure. Use injected sleep, with no actual network or waiting.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Validate attempts first, then run a bounded loop that returns the operation result immediately on success, even when it is a falsey value. Catch only TransientError. If another attempt is available, invoke sleep with 1, 2, 4 or 8 seconds according to the failed attempt index. On exhaustion, use a bare raise so the final operation exception remains visible. For three attempts, two transient failures followed by success make three calls and waits [1, 2]; three transient failures make the same calls and waits but propagate the third error. PermissionError is a single-call failure with no wait. The read-safety assertion is a precondition supplied by the caller: this wrapper cannot infer that a payment, model generation or tool effect is safe to repeat. In production, deadline, jitter, provider classification and any retry hints need separate explicit policies.

```python
class TransientError(Exception):
    """Synthetic failure explicitly eligible for retry in this exercise."""


def retry_read(operation, sleep, *, attempts=3):
    """Retry a caller-declared safe read; injected sleep uses logical seconds."""
    if type(attempts) is not int or not 1 <= attempts <= 5:
        raise ValueError("attempts must be an integer from 1 through 5")
    for index in range(attempts):
        try:
            return operation()
        except TransientError:
            if index + 1 == attempts:
                raise
            sleep(2 ** index)
```


**Explanation:** Total attempts and retries differ by one. Catching every Exception conflates a temporary dependency issue with invalid credentials, coding errors and uncertain side effects.

**Followups:** What if an operation finishes remotely but its response times out? How would you combine a total deadline and provider retry hint?

**Common mistakes:** Retrying arbitrary writes; attempting attempts+1 calls; sleeping after exhaustion; testing with real sleeps or a live provider.

**Validation:** The exact excerpt above is checked against [core_examples.py](../validation/questions-ai/core_examples.py). Focused [tests](../validation/questions-ai/check_examples.py) cover the prompt’s success, failure and boundary contracts with no network or actual sleep; see the [validation record](../validation/questions-ai/README.md). This does not validate a provider client, retry policy or deployed service.

</details>

<a id="qb-ai-003"></a>

## QB-AI-003 — A valid proposal is still not a permitted action

| Field | Value |
|---|---|
| Category | Tool execution policy |
| Difficulty | Intermediate |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Tool execution policy; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-004 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md); [OWASP authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A model proposes {tool: "change_shipping_address", order_id: "O7", address: "A"}. All fields pass a strict JSON schema. The authenticated user owns O8, while O7 belongs to someone else. Describe the server decision path and the result returned to the model. No actual address change should be made.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Treat the object as a proposal. Resolve the authenticated actor from trusted request state and load the target order through an authorization-aware path. Validate that the actor may change this particular order, that the order is still editable, and that the address satisfies the domain constraints. The ownership check fails here, so the server must reject the action before mutation. Return a bounded failure suitable for the workflow without revealing another customer’s order details. Preserve an internal decision record with actor, action identifier and policy outcome, excluding unnecessary address data. A schema can establish field shape; it cannot establish ownership or current business state. A model response saying the customer approved also cannot grant authority. If a permitted proposal later needs confirmation, bind that confirmation to the exact order, address and version, then recheck mutable conditions at execution.

**Explanation:** The decisive boundary is the server executor acting on another person’s order. The hypothetical scenario establishes the failure condition; it is not a finding about a deployed application.

**Followups:** What changes if the order ships between review and execution? Which denial information can the user safely see?

**Common mistakes:** Trusting model-generated owner_id; checking permission only in the UI; treating schema success as business approval.

</details>

<a id="qb-ai-004"></a>

## QB-AI-004 — Stop a read-only agent that keeps searching

| Field | Value |
|---|---|
| Category | Bounded agent control |
| Difficulty | Intermediate |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Bounded agent control; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-005 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Agent_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A read-only troubleshooting assistant may call search_logs and read_runbook. It has a maximum of four tool executions, a 20-second overall deadline, and a per-tool timeout of at most five seconds or remaining time, whichever is smaller. It repeats the same unsuccessful search. Propose a state machine, stopping behavior and test cases. No live tool implementation is required.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Represent running, completed, stopped_for_budget, cancelled and failed outcomes explicitly. Before each tool dispatch, validate the allowlisted name and arguments, check cancellation, check remaining deadline and reserve one execution from the four-call budget. Give the dispatcher the smaller timeout and record the result or timeout as an observation. Repeated identical unsuccessful searches should trigger a progress policy, such as requesting one changed query or stopping with available evidence, rather than automatically consuming the remaining budget. At stop, return what was learned, what remains unknown and the reason, without claiming the issue was diagnosed. A cooperative loop cannot preempt a tool that ignores its timeout; the executor and transport need enforcement. Test the fourth-call boundary, a deadline expiring before dispatch, a hanging tool, duplicate proposals and cancellation arriving between observation and the next decision.

**Explanation:** Step limits, time limits and progress checks bound different failure modes. Counting only successful tools permits an unlimited failure loop.

**Followups:** Should a denied proposal consume the execution budget? What additional policy would be required for a write tool?

**Common mistakes:** Bounding model turns but leaving multiple calls per turn unlimited; assuming a prompt instruction enforces a deadline.

</details>

<a id="qb-ai-005"></a>

## QB-AI-005 — Choose a workflow before adding autonomy

| Field | Value |
|---|---|
| Category | Architecture selection |
| Difficulty | Beginner |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Architecture selection; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-006 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/AI-Engineer/Agentic_Workflows.md); [Anthropic workflow patterns](https://www.anthropic.com/research/building-effective-agents); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A support process always classifies a message into one of five queues, extracts an account identifier and drafts a response from a known template. The account identifier must be verified against a trusted service; no automatic customer-facing sending is required. Propose the smallest useful architecture and identify one condition that would justify model-controlled planning.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Start with a fixed pipeline: validate input, classify into a bounded category set, extract a candidate identifier, verify it with the trusted service, retrieve the approved template and draft for review. Keep unknown or ambiguous classifications explicit instead of forcing every case into a confident queue. Store stage results and versions so failures can be reproduced and retried at a defined boundary. The model is useful for interpreting variable language, but the process order and verification rules are already known, so a planner adds extra choices without an established benefit. A new requirement to investigate heterogeneous incidents using different read-only evidence sources might justify bounded planning if fixed routes miss useful cases. Compare that alternative on task success, review effort, latency and cost using held-out cases. Template selection alone does not establish that the generated response is correct or ready to send.

**Explanation:** The exercise asks for a justified capability boundary. It does not assume a framework is necessary or that agent autonomy is inherently more capable.

**Followups:** How would you handle a message with two queue intents? What evaluation would demonstrate that adding a planner helps?

**Common mistakes:** Giving a planner every available tool; allowing extraction to bypass account verification; choosing architecture from terminology alone.

</details>

<a id="qb-ai-006"></a>

## QB-AI-006 — A better average hides a failing slice

| Field | Value |
|---|---|
| Category | Evaluation diagnosis |
| Difficulty | Intermediate |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Evaluation diagnosis; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-007 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/AI_Evaluation.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Version A passes 90/100 common cases and 6/20 rare-format cases. Version B passes 96/100 common cases and 2/20 rare-format cases. Cases are the same across versions. Calculate aggregate and per-slice pass rates. Decide whether these counts alone justify release when rare-format mistakes can block customer onboarding.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** A passes 96/120 = 80% overall; B passes 98/120, approximately 81.67%. The common slice improves from 90% to 96%, while the rare-format slice falls from 30% to 10%. The aggregate therefore cannot establish that B is acceptable for onboarding. Inspect the paired cases to determine which errors changed, review label consistency and identify whether rare-format cases represent a production-critical segment. Define a minimum slice criterion and failure-severity policy before selecting a release. Counts alone do not quantify uncertainty or prove the production mixture matches this test set. A candidate can propose keeping A for the rare-format route, adding a parser fix or collecting more reviewed examples, but must test the chosen remedy on held-out cases. Repeatedly tuning against these same 120 items turns them into development data rather than an untouched final test.

**Explanation:** The denominators make the tradeoff visible. A release decision also needs the relative consequences of errors and the intended population, not just arithmetic.

**Followups:** What paired-case data are missing? How would you monitor an uncommon high-cost failure after release?

**Common mistakes:** Reporting only the two-item aggregate gain; averaging 90% and 30% without weights; calling 20 cases definitive.

</details>

<a id="qb-ai-007"></a>

## QB-AI-007 — Find the latency stage that actually grew

| Field | Value |
|---|---|
| Category | AI observability |
| Difficulty | Intermediate |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | AI observability; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-008 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/LLM_Observability.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A sequential request trace records auth 20 ms, retrieval 180 ms, reranking 100 ms, model time to first token 900 ms, and model streaming after the first token 2,800 ms. Ignore transport/rendering overhead. Compute first-token and completion latency. Product feedback says “the answer starts too late.” Choose the next measurement before optimizing.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** First-token latency is 20 + 180 + 100 + 900 = 1,200 ms. Completion is 1,200 + 2,800 = 4,000 ms. Streaming improves how progress is presented but does not remove the 1.2-second wait to first content. Since the model prefill/queue-to-first-token stage is the largest contribution in this example, inspect its distribution and inputs before optimizing retrieval. Record provider queue time if exposed, input size, model/version, region and concurrent load, while measuring application first-visible content separately from server first-token receipt. Compare representative p50 and tail behavior rather than treating this one trace as a benchmark. Check whether a loading indicator is being confused with answer content. Parallelizing genuinely independent preprocessing might help later, but these measurements do not show that retrieval and reranking are independent or that their removal preserves answer quality.

**Explanation:** The numbers describe one sequential hypothetical trace. Summing stage p95 values would not produce an end-to-end p95 because the slow events need not occur together.

**Followups:** What if retrieval and another stage run in parallel? How would you separate provider delay from a buffering proxy?

**Common mistakes:** Calling total latency model latency; claiming streaming fixes time to first token; optimizing the smallest stage first.

</details>

<a id="qb-ai-008"></a>

## QB-AI-008 — Price a bounded generation policy

| Field | Value |
|---|---|
| Category | AI cost accounting |
| Difficulty | Beginner |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | AI cost accounting; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-009 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/AI_Cost_Optimization.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Use hypothetical prices of $2 per million input tokens and $8 per million output tokens. Each request uses 3,000 input and 500 output tokens. Calculate cost per request and for 10,000 identical requests. A quality-preserving prompt change reduces input by 1,000 tokens; calculate savings. Exclude retries, caching discounts, storage and other charges explicitly.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The input component is 3,000/1,000,000 × $2 = $0.006 and output is 500/1,000,000 × $8 = $0.004, giving $0.010 per request. Ten thousand requests cost $100 under the stated assumptions. Removing 1,000 input tokens saves $0.002 per request or $20 for the same volume, leaving $80. These are hypothetical unit prices and fixed request usage, not current provider pricing. A production forecast should separately account for actual token distributions, repeated attempts, retrieval and infrastructure costs, provider billing units and any negotiated discounts. Confirm the prompt change preserves task behavior on held-out cases, including long or unusual inputs. A hard output cap can constrain worst-case spend but may truncate the useful answer, so cost should be evaluated alongside completion quality, latency and unsuccessful-request rate rather than optimized by token count alone.

**Explanation:** Multiplying tokens by a per-million price requires dividing by one million. Fixed output assumptions matter because output contributes 40% of this example’s generation bill.

**Followups:** What happens if 10% of requests require one complete retry? When might shortening context increase total cost?

**Common mistakes:** Treating hypothetical rates as live prices; mixing tokens and requests; ignoring output and retry costs.

</details>

<a id="qb-ai-009"></a>

## QB-AI-009 — Make conversation memory correctable

| Field | Value |
|---|---|
| Category | Memory provenance and lifecycle |
| Difficulty | Advanced |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Memory provenance and lifecycle; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-010 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Agent_Memory.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A travel assistant remembers “prefers aisle seats” from a conversation. The user now says “only for daytime flights,” and later deletes the original preference. Design a small memory record and update/delete behavior. No booking action is authorized by this scenario.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Store a typed preference with scope, value, provenance, creation/update time, version and lifecycle state. The correction should narrow the predicate to daytime flights rather than append a contradictory second universal preference. Preserve only the audit information the product actually needs, and make the active interpretation inspectable by the user. A subsequent deletion must remove or deactivate the preference and invalidate derived summaries, embeddings and caches that can reintroduce it; document any separately retained audit record and its access rules. A retrieval result should identify the memory version so stale context can be detected before use. This preference guides recommendations; it does not authorize a booking or override a flight-specific user choice. Tests should exercise correction, deletion, stale cache retrieval, conflicting newer conversation and migration from an older record schema. Define how long-running sessions refresh memory after a user edits it.

**Explanation:** Memory is mutable application data with provenance. A summary without a source/version makes corrections difficult to propagate and explain.

**Followups:** How would you distinguish a hypothetical statement from a true preference? What should happen when the user cannot see a stored inference?

**Common mistakes:** Appending every sentence permanently; allowing a deleted summary to recreate the preference; treating preference as action consent.

</details>

<a id="qb-ai-010"></a>

## QB-AI-010 — Repair structured extraction without inventing facts

| Field | Value |
|---|---|
| Category | Extraction reliability |
| Difficulty | Advanced |
| Target roles | AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Extraction reliability; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-AI-001 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/AI-Engineer/Prompt_Engineering.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

An invoice extraction schema requires invoice_id, currency and total. A scan clearly shows invoice_id X19 and total 145.00, but the currency symbol is unreadable. A downstream system requires an explicit supported currency. Design extraction, validation and repair outcomes without invoking a particular provider API.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Keep observed evidence separate from validated business fields. The extraction stage can return X19, the visible numeric amount and an explicit missing/uncertain currency with source coordinates. The application should reject submission to the downstream system until a supported currency is established through another authoritative field, a better scan or human review. A bounded repair attempt may rerun OCR on the relevant region or ask for clarification, but must not select USD merely because it is common. Preserve the extraction version and the evidence used for each field so a reviewer can verify the result. Schema compliance alone does not make a guessed currency true; if the target schema cannot represent unknown values, use a separate error/review result type instead of forcing a success object. Test unreadable, conflicting, unsupported and fully legible cases, including total/currency combinations that superficially look plausible.

**Explanation:** The repair path must add evidence, not merely transform an invalid answer into a syntactically valid guess. Submission and extraction are different states.

**Followups:** What if two currency symbols disagree? Which fields can be normalized without changing their meaning?

**Common mistakes:** Defaulting an unreadable field; retrying indefinitely; discarding the source image once JSON parses.

</details>
