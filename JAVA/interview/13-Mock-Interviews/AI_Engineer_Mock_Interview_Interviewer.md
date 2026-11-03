# Incident-assistant delivery review: interviewer packet

**Spoilers — keep this file closed during the candidate attempt.** Companion to [the candidate packet](AI_Engineer_Mock_Interview.md), **MOCK-AI-01**. All scenario requirements are original [INFERRED]; this is role practice, not a real employer loop. Do not turn this reference approach into the only acceptable design.

## Interviewer instructions

Read the candidate contract first. Complete preflight outside the clock and start only when the agreed environment works. Run the four live stages in order for **75 minutes**. Ask one focused question, then allow reasoning time. Use neutral prompts such as “What assumption makes that safe?” rather than prematurely naming a technique. Give H0 factual answers freely; log H1/H2/H3 directional hints with time, wording and their effect. Offer the next hint only when the candidate requests help or is stalled after a neutral check. Preserve partial work rather than extending the clock to force completion. Do not penalize agreed accommodations or score speaking speed as technical correctness. Reserve 10–15 minutes after the clock for debrief.

## Clarification desk — H0

The application fetches source under current user authority before model submission. The helper receives trusted expected_id; there is no requested ticket-update API. Source can be empty, contain non-ASCII or contain malicious instructions. Offsets refer to the original, unnormalized Python string. Subclasses/coercion are deliberately outside the strict exercise contract. All helper validation failures use ValueError. Raw JSON duplicate-key handling, decoding and document-size limits belong before this helper and are discussion points, not unannounced coding requirements. Source and expected_id are not modified during the call. For evaluation, answerable means gold H/M/L and an abstention is excluded from answered-accuracy but included in the coverage denominator.

## Timed script and reference solution

### AI-T1 — Frame the decision and evidence boundary

**Checkpoint:** minutes 0–10.

**Ask:** “Which component has authority, and what would a successful helper still fail to prove?”

**Expected solution:** A small application authenticates the reviewer, authorizes the exact ticket, obtains trusted ID/body, asks the model for a proposal, runs structural validation, then presents proposal plus original evidence to a human. Only an explicitly authorized later action could change a ticket. Source instructions are untrusted content. A span can be in bounds yet be irrelevant or contradict the severity; human/semantic review remains necessary. The initial job is structured decision support, so an autonomous multi-tool agent adds risk without a stated need.

**Hints:** H1 ask the candidate to mark trusted versus model-controlled values. H2 ask whether a perfectly valid `high` can be wrong. H3 supply the separation between proposal validation and decision authorization.

**Branches:** If stuck, ask for one valid-but-factually-wrong proposal. If finished, ask where authorization should be rechecked when a review is delayed. Accept different component names with the same ownership boundary.

### AI-T2 — Implement the local contract

**Checkpoint:** minutes 10–40.

**Expected solution:** Check strict input types before field access or slicing, then exact keys, ticket identity, enum and interval bounds. Build a new result using the trusted ID and a slice from source. Reject booleans explicitly: Python bool is an int subtype, so `isinstance(True, int)` is too permissive for this contract. The original fixture's evidence is `API unavailable`, the 15 characters at offsets [0,15); the period is at index 15. A Unicode source `🔥 outage` uses start 0/end 1 for the fire character even though its UTF-8 representation spans more than one byte.

<!-- phase6-code: validate_incident.py -->
```python
def validate_incident(source, expected_id, payload):
    """Validate a parsed proposal; this does not prove factual severity."""
    if type(source) is not str or type(expected_id) is not str or not expected_id:
        raise ValueError("source and nonempty expected_id must be strings")
    required = {"ticket_id", "severity", "evidence_start", "evidence_end"}
    if type(payload) is not dict or set(payload) != required:
        raise ValueError("exact proposal fields required")
    if type(payload["ticket_id"]) is not str or payload["ticket_id"] != expected_id:
        raise ValueError("ticket identity mismatch")
    severity = payload["severity"]
    if type(severity) is not str or severity not in {"low", "medium", "high"}:
        raise ValueError("unknown severity")
    start, end = payload["evidence_start"], payload["evidence_end"]
    if type(start) is not int or type(end) is not int or not 0 <= start < end <= len(source):
        raise ValueError("nonempty in-bounds Python string slice required")
    return {"ticket_id": expected_id, "severity": severity,
            "evidence_start": start, "evidence_end": end,
            "evidence": source[start:end]}
```

**Correctness/complexity:** Every allowed return follows all field predicates; any failed predicate raises before a slice is returned. Fresh dict creation avoids input mutation. With n source code points and k selected code points, slice copying is O(k) time/space and output evidence O(k); validation has constant field-count overhead under ordinary string/hash accounting. Comparing ticket IDs costs up to their length. Exact-key set creation is O(m) time/space for m submitted keys, so an attacker-supplied huge dict is not O(1); schema/input-size limits should be applied upstream. No substring search or semantic entailment is claimed.

**Hints:** H1 enumerate invalid shapes before implementing. H2 ask how boolean offsets interact with int checks. H3 supply `type(start) is int` and the half-open interval condition.

**Branches:** If stuck, finish a correct positive path then write the missing guard as a clear TODO in the candidate artifact and score that limitation. If finished, add a property: successful evidence always equals source[start:end], and any extra key is rejected. A dataclass/schema implementation is acceptable if it preserves the same strictness and rejection contract; no particular library earns extra points.

### AI-T3 — Choose an evaluated release

**Checkpoint:** minutes 40–60.

**Worked solution:** There are five answerable rows. A answers rows 1,2,3,6, so coverage is 4/5=80%; three of those four match gold, giving answered accuracy 3/4=75%. B answers all five, so coverage is 5/5=100%; four match, giving 4/5=80%. On the single unsupported row A asserts H while B abstains. A's abstention on row5 is not a correct severity and must not silently disappear from coverage. Report the row6 regression as well as B's gains on rows3,4,5. Do not compare unpaired aggregate cohorts or average the two percentages.

B is a stronger candidate on this fixture, not an established production winner. Expand held-out data across class/slice, source lengths, unsupported and adversarial cases; freeze criteria before final evaluation. Track human disagreement, false-high/false-low costs, abstention and latency/cost under the same workload. Keep the human-review boundary for this initial use. No model name is necessary because no measured provider comparison is supplied.

**Hints:** H1 separate answerable and unsupported populations. H2 write coverage as answered/eligible before computing accuracy. H3 identify A's denominator as four and B's as five.

**Branches:** If stuck, score one system first. If finished, ask how a high false-positive cost could change the decision and whether evidence supports estimating rare incident failures from these six rows.

### AI-T4 — Handle a failed assumption

**Checkpoint:** minutes 60–75.

**Expected solution:** Auto-paging changes the product from review assistance to a consequential external action. A valid enum or span cannot authorize paging; the helper provides no factual proof. Explain this to the owner, propose keeping review or defining a separately authorized deterministic decision/action policy, and test malicious and benign tickets under that policy. Scope credentials and tool access so evaluated text cannot invoke actions. Do not claim prompt wording or JSON schema eliminates injection. If an action API is later added, bind approval to actual ticket/action/version, preserve idempotency and reconcile unknown effects after timeout.

**Strong indicators:** Candidate explains the request's new risk plainly, offers a useful next test, and distinguishes tested helper behavior from unimplemented controls. **Common mistakes:** “It passed JSON so it is safe,” treating all abstentions as model failures, silently editing trusted ID from model output or claiming local tests establish severity quality.

**Hints:** H1 ask what changed in the user outcome. H2 ask whether the helper checks the meaning of `high`. H3 state that action authorization must be independent of untrusted model output.

**Branches:** If stuck, ask for a one-sentence message to the rollout owner. If finished, discuss proposal expiry when ticket content changes; retain this as optional stretch, not a requirement for core completion.

## Evaluation evidence

Use the six shared dimensions in [the rubric](Interview_Evaluation_Rubric.md). The table gives **task-specific level-3 evidence**, not automatic points for a keyword. Apply 0–2 to the actual help/errors and reserve 4 for repeated demonstrated performance across variations or attempts. Mark unobserved dimensions **Not assessed**. Record a known critical defect separately; averaging cannot erase it.

| Dimension | Observable level-3 evidence | Critical blocker / reason to withhold 3 |
|---|---|---|
| CONTRACT | Separates trusted ID/body, model proposal and human-only action; resolves strict types and indices | Lets model choose ticket/action or changes the prompt contract |
| REASONING | Derives checks and distinguishes structural validity from semantic support | Treats in-bounds text as proof of severity |
| CORRECTNESS | Implements exact keys/types/identity/range; computes both metric denominators | Accepts booleans/out-of-range spans or reports selective accuracy without coverage |
| VALIDATION | Runs independent invalid/Unicode cases and identifies semantic tests still needed | Only prints one success fixture or describes unrun checks as passed |
| RISK | Keeps paging outside proposal authority and flags untrusted instructions | Pages from schema-valid high or grants source text tool authority |
| COMMUNICATION | Explains row-specific regressions and release limits clearly; labels incomplete work | Claims six examples prove production readiness or invents a model benchmark |

## Debrief and next attempt

Ask the candidate to summarize one decision, one check and one unresolved risk before providing feedback. Save task completion, H0/H1–H3, exact evidence, dimension scores and what was not assessed. For interval/type errors, reimplement against a new Unicode fixture. For evaluation gaps, reattempt with a changed abstention pattern before reading the reference. For scope errors, diagram authority at each proposed action. Do not write a hiring decision or invent a candidate result. Any example of strong behavior above is an editorial anchor, not a prefilled attempt.

## Validation, sources and limits

The exact marked helper above is extracted and tested by [the local suite](../validation/mocks-ai/README.md). The suite checks structural behavior and synthetic metric arithmetic only. It does not call a provider, grade incident semantics, test deployed authorization, implement a workflow or establish injection resistance. Technical support: [Python types](https://docs.python.org/3.12/library/stdtypes.html), [OWASP output/trust-boundary guidance](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) and [authorization guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Source register](PHASE6_AI_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
