# Interviewer guide — support-routing pilot

## Restricted preparation

**Contains spoilers.** Use [the candidate packet](FDE_Mock_Interview.md), MOCK-FDE-01. The 75-minute session is an original [INFERRED] customer-engineering simulation, not an employer's FDE loop. Play the customer consistently; do not withhold a decisive fact merely to trick the candidate. Preflight and the 10–15-minute debrief are additional.

Ask one focused question at a time. H0 supplies facts without penalizing curiosity. Log H1/H2/H3 direction hints and their actual effect, not an automatic point deduction. [Common scoring](Interview_Evaluation_Rubric.md) applies; the candidate need not choose an LLM to perform well. They have no authority to upload data, enable a connector or apply customer changes during this exercise.

## Fixed customer clarification script — H0

| Candidate asks about | Consistent customer answer |
|---|---|
| User pain and decision | Agents currently spend time opening the wrong queue; the useful first outcome is a reviewed routing suggestion, not autonomous replies/refunds |
| Costliest mistake | Billing tickets incorrectly sent to Technical can miss a priority response; Other still needs manual triage |
| Friday and stakeholders | Friday is a demo, not a contractual production date. Support owns acceptance; customer IT owns ticket-system credentials; a privacy owner must approve data processing |
| Access and data | One tenant's sanitized export is approved for offline processing inside the customer-approved environment. No external-model transfer or write credential is approved. Sanitization still needs review; absence of emails does not prove anonymous text |
| Baseline and labels | A keyword/rule baseline exists but has not been evaluated on this frozen set. Labels were supplied by support; the 200-ticket fixture is small and selected, not a random population sample |
| Acceptance | The manager proposes ≥95% correctness among non-review proposals, ≥60% coverage, and no observed Billing→Technical errors on a separately reviewed release set. These are proposed gates, not population guarantees; candidate should make their scope/uncertainty explicit |
| Ticket updates | Tickets have stable tenant/ticket ID and monotonically increasing revision. A proposal may be applied only if its source revision is still current and an authorized reviewer accepts it |
| Human capacity | Review capacity and per-ticket time are unknown; measure rather than promising that abstention is free |

## Timed script and expected solutions

### FDE-T1 — Discover the decision behind the request

**0–15 minutes.** Start with the quoted customer request. At minute 8 ask, “What do you need before choosing a model?” At minute 13 ask for a bounded recommendation.

**Expected solution:** Ask which agents/queues are affected, what decision improves their work, what errors cost, who owns reference labels and acceptance, what data may be processed, which system is authoritative, available read/write interfaces, review capacity and whether Friday means demo or production. Prioritize permission, costly-error and acceptance questions over framework preference. Summarize facts (offline export, no write permission, demo date), assumptions (routing taxonomy usable), unknowns (review capacity, representative data, baseline performance) and named owners.

A suitable next step is an offline proposal-and-review demo with the rule baseline and an alternative only if it adds measurable benefit under approved processing. Do not claim that a demo deadline implies permission or production readiness. A strong early-career response identifies whom to involve instead of assuming responsibility for privacy/IT decisions alone.

**Hints:** H1 “What user decision changes?” H2 “Which wrong route is costly, and who accepts the pilot?” H3 state the lack of write/external-processing permission and ask for a useful deliverable anyway. **Stuck branch:** ask them to produce three questions changing feasibility. **Finished branch:** customer requests a new category mid-pilot; ask how taxonomy/labels and acceptance must change together.

### FDE-T2 — Specify the pilot interface

**15–35 minutes.** Ask for a sample input/output contract and a replay story, not a vendor stack. At minute 27, reveal that exports may repeat a ticket with a newer revision.

**Expected solution:** Input carries approved tenant ID, ticket ID, revision, export/batch identity, approved text fields and schema version. Output proposal carries the same identity/revision, category or Review, reason/evidence appropriate to the processor, processor/version, creation time and state such as proposed/accepted/rejected/stale. Confidence is not automatically calibrated probability; a reason string does not establish correct routing. Validate taxonomy and schema; unsupported input goes to explicit review/error rather than a guessed category.

Use `(tenant,ticket,revision,processor version)` to identify a deterministic proposal attempt/result policy; repeated input does not create repeated side effects. New revisions supersede old proposals visibly. The ticket system remains authoritative; offline state is proposal/evaluation history, not a second mutable source of ticket truth. In an eventual live integration, an authorized human accepts a proposal; the adapter compares the expected ticket revision and conditionally applies the update, then records the authoritative outcome. A GET followed by an unconditional write is racy. If the actual API cannot atomically enforce the revision condition, keep manual review/application or obtain a proven adapter mechanism before enabling automatic writes.

Scope credentials and tenant checks at input, proposal viewing, acceptance and write. Revalidate current authority at action time; an old export or approval cannot permanently authorize future changes. Retain permitted logs/lineage with bounded access and deletion policy. For Friday, a frozen synthetic/approved export and visible dry-run proposal list can demonstrate the flow honestly, with a conspicuous “not connected; no updates applied” status. A simple rules baseline is valid; no hosted provider integration is required.

**Hints:** H1 “Which identity survives re-export?” H2 “What if the source changed between proposal and acceptance?” H3 introduce an expected-revision conditional update and ask what failure means. **Stuck branch:** trace one ticket from export to proposed to stale. **Finished branch:** retry an acceptance after response loss; ask how the adapter reconciles uncertain action identity without applying to a newer revision.

### FDE-T3 — Interpret evidence and negotiate a release

**35–55 minutes.** Ask for calculations first, then supply the acceptance rule if not already requested. At minute 48 use the “demo looks good” challenge.

**Expected solution:** The fixture has 60 Billing, 100 Technical and 40 Other tickets, 200 total. Non-review proposals are 50 Billing plus 100 Technical =150; coverage is 150/200=75%. Correct non-review proposals are 46+92=138, so conditional correctness is 138/150=92%. Billing→Technical errors are four, or 4/60≈6.67% of reference Billing tickets in this selected fixture. Neither 138/200=69% nor overall accuracy with Review counted as a correct class is the requested selective correctness metric. Fifty abstained tickets still need human capacity; “Other” sent to a named queue can also be wrong.

At the proposed ≥95% correctness and zero observed Billing→Technical gate, this diagnostic fixture fails despite meeting ≥60% coverage. The release set is separately reviewed: do not tune repeatedly on it and still call it untouched evaluation. Compare with the rule baseline on the same held-out population, inspect costly errors, test rare/new categories and label disagreements, then consider restricted suggestion-only scope. Zero observed errors on a finite set cannot prove zero population error; report coverage, denominators, selection and uncertainty. The 1,000/day workload is not a license to project selected-fixture rates as production forecasts.

Recommend **no automatic routing now**: write/processing authority is missing and supplied quality evidence is insufficient. A reviewed offline demo can proceed within approved scope. Explain the user's benefit and the next discriminating experiment rather than saying “AI is too risky” or hiding behind a percentage.

**Hints:** H1 “How many proposals avoid review?” H2 “Conditional on proposing, which two diagonal cells are correct?” H3 supply numerator/denominator sets and have them calculate. **Stuck branch:** ask for coverage and one costly error count only. **Finished branch:** raise the abstention threshold; ask how correctness/coverage/human workload may trade off and why the new policy requires fresh evaluation rather than a guaranteed gain.

### FDE-T4 — Handoff under a changed constraint

**55–75 minutes.** Give the stale-ticket report as a hypothetical failure in a future connected pilot, not as a contradiction of the current offline permissions. At minute 65, ask for the customer update; finish with handoff owners.

**Expected solution:** Stop the affected application path or keep it suggestion-only while preserving unrelated support work. Record batch/proposal/action IDs, source and current revisions, authorization decisions and authoritative ticket history in the approved environment. Distinguish a stale displayed suggestion from an actual stale applied write. Reproduce the proposed update after a source revision change and assert rejection/no mutation. Inspect atomic conditional-write support and whether retry/adapter behavior bypasses it. Reconcile known effects before replaying a failed batch; do not bulk overwrite the latest categories from old exports.

A useful two-minute update: “We have a report that one older proposal affected a changed ticket. We have not yet established how many applied updates are affected. We have paused that application path and kept manual routing available. IT and the pilot owner are comparing proposal/source revisions and ticket history. We will share the scoped impact and the conditional-update check at the agreed next checkpoint.” The exact interval can be agreed; do not fabricate an ETA or confirmed root cause.

Handoff names support acceptance owner, IT connector owner and pilot operator; includes approved data scope, schema/version, review workflow, stale/error queues, monitoring of coverage/error slices/revision conflicts, rollback to manual routing, a runbook and a stop/restart decision owner. Training/retention changes require their own approval process. Human review is an operating dependency, not a magical guarantee. If discussing personal experience, require truthful attribution or an explicit hypothetical label.

**Hints:** H1 “Which path can you pause while support continues?” H2 “What evidence distinguishes stale display from stale write?” H3 offer a revision-change test and ask for an assertion. **Stuck branch:** request knowns/unknowns/next action only. **Finished branch:** propose a restart gate based on the demonstrated fix, affected-record reconciliation and customer acceptance; no blanket guarantee from one passing example.

## Task-specific score evidence

Use [the shared scale](Interview_Evaluation_Rubric.md), with Not assessed for unobserved dimensions. One mock cannot establish the repeated evidence required for level 4.

| Dimension | Observable level-3 evidence | Critical blocker / remediation |
|---|---|---|
| CONTRACT | Discovers approved outcome, costly error, authority and acceptance owners | Promises autonomous Friday launch before clarification; repeat FDE-T1 |
| REASONING | Selects a bounded baseline/pilot and explains integration decisions | Chooses a fashionable agent without workflow reasons; revisit FDE-T2 |
| CORRECTNESS | Uses revision-bound proposal/application and correct metric denominators | Unconditional stale write or incorrect 92% calculation; trace FDE-T2/3 |
| VALIDATION | Separates diagnostic data, untouched evaluation and proposed gates | Claims fixture is a live/representative benchmark; design a fresh evaluation in FDE-T3 |
| RISK | Respects processing/write permission, current authority and manual capacity | Uploads private data by assumption or hides uncertain effects; rework FDE-T2/4 |
| COMMUNICATION | Gives honest customer update, owners and bounded next step | Invented root cause, personal story or ETA; repeat FDE-T4 |

**Common mistakes:** interpreting a job title as solo authority; multiplying fixture rates by 1,000/day as a forecast; treating confidence as calibrated accuracy; silent category changes; making offline success a live-write claim. **Strong indicators:** prioritized discovery, honest no-go/limited-go decision, exact denominators, stable identities and a recoverable handoff.

## Debrief and provenance

After the session spend 10–15 minutes comparing the candidate's ledger with the fixed facts, one failed update trace and evidence for each score. Choose one discovery question and one integration/evaluation gap for the next attempt. Do not give a fictional customer endorsement or hiring prediction.

[Source register](PHASE6_DESIGN_SOURCES.md): current resource authorization, HTTP conditional semantics, Google ML objective/evaluation engineering and incident coordination. [Local fixture checks](../validation/mocks-design/README.md) verify only the supplied confusion-matrix arithmetic. No model, connector, ticket update or customer engagement was executed. Reviewed **2026-11-03**.
