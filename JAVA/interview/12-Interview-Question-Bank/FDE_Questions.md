# Forward Deployed Engineer practical questions

These ten original **[INFERRED]**, company-neutral exercises are focused practice, not employer questions or encounter-frequency claims. Start with Beginner/Intermediate questions for 0–2 years; Advanced questions are extensions. These are individual exercises; complete role simulations belong to Phase 6. Attempt the prompt before opening its answer. Alternatives that preserve the stated contract can be valid.

Read the linked canonical lesson when a mechanism is unfamiliar. Each “Round” is a suggested practice format, not a company process. Difficulty is an editorial judgment, not a measured pass rate. For design/diagnosis questions, the answer is a reviewed reference approach, not a live integration result.

Last verified: **2026-11-02** — prompt contracts, answer reasoning, local links and question records reviewed. Only QB-AI-001/002 include locally executed code; other scenarios are reasoning exercises. [Source scope](PHASE5_AI_SOURCES.md). [Question-bank directory](README.md).

<a id="qb-fde-001"></a>

## QB-FDE-001 — Turn a vague automation request into a decision

| Field | Value |
|---|---|
| Category | Customer discovery |
| Difficulty | Beginner |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Customer discovery; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-002 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Technical_Discovery.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A warehouse manager says “we need an agent to reduce shipment delays.” You have 20 minutes with the manager and no data access yet. Give an ordered conversation plan and a concrete end-of-meeting artifact. Do not promise an automated dispatch system.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Start with one recent delayed shipment and reconstruct who noticed the problem, what information was missing, which decision was delayed and what action followed. Ask how delays are measured, their operational cost and who owns the current workflow. Identify candidate data sources and permission owners, then distinguish detecting a delay from recommending a response or taking an operational action. Confirm the smallest valuable decision the prototype could support, such as helping a coordinator prioritize a review queue. End with a short fact/assumption/question ledger, a measurable baseline, a proposed read-only pilot scope and owners for data access and operational acceptance. If the manager cannot provide baseline delay data, propose collecting a small reviewed sample before claiming improvement. The word agent is a requested solution; the discovery should establish the underlying workflow and whether model judgment is needed.

**Explanation:** A recent concrete incident exposes missing joins, human exceptions and decision owners more effectively than an unbounded feature wish list.

**Followups:** Which answer would make you abandon an AI prototype? How would you include the coordinator who does the work?

**Common mistakes:** Beginning with model selection; promising delay reduction without a baseline; treating data access as already granted.

</details>

<a id="qb-fde-002"></a>

## QB-FDE-002 — Negotiate a pilot with an honest acceptance rule

| Field | Value |
|---|---|
| Category | Pilot measurement |
| Difficulty | Intermediate |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Pilot measurement; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-003 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Customer_Engineering.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A customer handles 200 tickets per week and wants a drafting assistant. Their stated target is “80% automation.” Current handling time is unmeasured and every draft must be approved. Define a two-week pilot that can produce an honest decision without claiming full automation.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Clarify whether the target means draft coverage, accepted drafts, reduced review time or tickets sent without review. Under the approval requirement, call this assisted drafting and measure human work explicitly. Establish a baseline from a reviewed ticket sample, with handling-time measurement rules, issue categories and quality criteria. Freeze a pilot scope such as two low-risk categories, log whether a usable draft was produced, measure correction/review time and count harmful or unsupported suggestions. Compare against a suitable baseline while accounting for case difficulty and reviewer differences; do not assume 400 tickets all fall within scope. Agree in advance on quality floors, maximum review burden, escalation behavior and a go/no-go owner. End with a decision to continue, change scope or stop, plus limitations. Two weeks can reveal feasibility and workflow problems without proving stable long-term savings or unattended operation.

**Explanation:** The denominator defines what “80%” means. Approval work is part of the outcome, so draft generation rate alone can hide a negative productivity result.

**Followups:** How would you prevent reviewers from over-trusting drafts? What if only 30 tickets fit the pilot categories?

**Common mistakes:** Calling every produced draft automated resolution; excluding correction time; setting acceptance thresholds after seeing results.

</details>

<a id="qb-fde-003"></a>

## QB-FDE-003 — Specify a connector before coding it

| Field | Value |
|---|---|
| Category | Integration contracts |
| Difficulty | Intermediate |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Integration contracts; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-004 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/AI_Deployment_and_Integration.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A customer can export CRM rows nightly. Each row has customer_id, updated_at and notes. Deleted records disappear from the next export. The prototype needs current customer notes and must remove deleted records. Design ingestion and identify what cannot be inferred from this feed.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Treat each complete successful export as a named snapshot with an extraction timestamp and documented coverage. Stage it, validate schema and uniqueness, then compare its customer IDs with the last accepted complete snapshot to identify upserts and deletions. Do not infer deletion from a partial or failed export; the connector needs a completeness signal and failure policy. Commit a new active snapshot only after validation, then update dependent retrieval indexes and caches with tracked progress. Define timezone, identifier stability and what updated_at actually means, because it may describe a CRM edit rather than export time. Nightly exports cannot support a truthful live-freshness claim, and absent rows cannot distinguish deletion from filtered access unless the feed contract says so. Test a full snapshot, repeated snapshot, duplicate IDs, missing column, truncated file and disappearing record before demonstrating deletion behavior to the customer.

**Explanation:** Disappearance implies deletion only under a complete and stable snapshot contract. Integration correctness depends on source semantics, not just parsing rows.

**Followups:** What if the customer changes export permissions? How would you support a later move to a change-data feed?

**Common mistakes:** Deleting everything absent from a failed file; trusting updated_at without its semantics; advertising real-time freshness.

</details>

<a id="qb-fde-004"></a>

## QB-FDE-004 — Keep a prototype demo truthful under missing data

| Field | Value |
|---|---|
| Category | Rapid prototyping and evidence |
| Difficulty | Beginner |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Rapid prototyping and evidence; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-005 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Rapid_Prototyping.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

The demo is tomorrow. You have working ingestion and retrieval on synthetic invoices but no customer credentials. The sponsor expects to see the full customer workflow. Propose a useful demonstration, visible labels and a clear next-step request.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Demonstrate the implemented path with clearly labeled synthetic invoices and explain which components execute locally. Show ingestion, evidence retrieval and a reviewed output, including one failure or missing-field path, rather than presenting a perfect prerecorded result as live integration. Mark the customer connector and production workflow as unvalidated dependencies, with a small interface contract or stub whose status is explicit. Tell the sponsor what the demonstration establishes: feasibility of these local transformations under controlled fixtures, not customer data quality, credentials, load or deployment readiness. End with a concrete request for a scoped sandbox account or approved sample export, a data owner and acceptance criteria for the next validation. A useful demo can still be polished and focused on the business decision, but it must preserve the distinction between implemented behavior, simulation and proposed work.

**Explanation:** The deadline changes demonstration scope, not the evidence standard. A visible boundary prevents a sponsor from making deployment decisions based on an assumed integration.

**Followups:** What if the sponsor asks to remove the synthetic-data label? Which one failure case best reveals the remaining risk?

**Common mistakes:** Calling mocked results live; hiding the absent connector; replacing the entire workflow with slides despite working local behavior.

</details>

<a id="qb-fde-005"></a>

## QB-FDE-005 — Triage a customer incident with a first hypothesis

| Field | Value |
|---|---|
| Category | Production debugging communication |
| Difficulty | Intermediate |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Production debugging communication; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-006 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Debugging_Production_Systems.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A customer reports that document answers became empty after yesterday’s connector update. Web login works and old cached answers still appear. You can inspect redacted ingestion and retrieval traces but cannot query production directly. Give the first investigation, a containment option and a customer update.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Treat the connector update as a hypothesis, not an established cause. Ask for affected timestamps, representative document/query IDs and the expected source version, then compare the last successful and first failing ingestion traces. Check schema changes, rejected documents, authorization metadata and index publication before inspecting generation. Working login does not prove document retrieval is healthy, and old cached answers can conceal an ingestion failure. A containment option is pausing the new connector rollout or serving a clearly dated previously approved snapshot if policy and freshness needs permit; obtain the operational owner’s decision for customer-impacting changes. Give a concise update naming observed impact, confirmed evidence, current hypothesis, next check and next update time. Do not claim recovery until a permitted validation shows newly ingested, authorized evidence reaches a representative answer and the customer agrees the expected workflow is restored.

**Explanation:** The available evidence supports staged diagnosis only. Missing direct access should narrow claims and guide an owner-run check rather than encourage invented production certainty.

**Followups:** What would disprove the connector hypothesis? Which evidence distinguishes stale cache from current retrieval?

**Common mistakes:** Calling temporal correlation root cause; assuming login covers data permissions; declaring recovery from an old cached answer.

</details>

<a id="qb-fde-006"></a>

## QB-FDE-006 — Resolve two stakeholders asking for incompatible outcomes

| Field | Value |
|---|---|
| Category | Stakeholder alignment |
| Difficulty | Intermediate |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Stakeholder alignment; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-007 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Communication_and_Stakeholder_Management.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Operations wants every generated recommendation sent immediately. Compliance requires human approval for recommendations affecting refunds. The pilot deadline is Friday. Explain how to reach a decision, document the scope and avoid silently choosing one stakeholder’s preference.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Clarify which recommendations actually affect refunds and which are informational, then identify the accountable policy owner and the sponsor who can accept pilot scope. Present a concrete flow: draft recommendations, route refund-impacting cases to a named approver, and permit any automatically delivered informational output only under an explicitly agreed category rule. Record unresolved ambiguities and default them to review within the pilot instead of letting the model invent the category boundary. Show the deadline tradeoff: a narrower reviewed pilot can demonstrate usefulness while policy decisions remain open, whereas immediate sending changes the risk and acceptance criteria. Document who approved the flow, what is excluded, review turnaround expectations and the escalation path. The engineer should make the decision concrete and testable; they should not manufacture consent or claim a technical feature flag resolves organizational authority.

**Explanation:** A policy disagreement needs a responsible decision owner and a bounded proposal. Escalating an abstract conflict without a workable option leaves implementation blocked.

**Followups:** What if approval queues make the pilot unusable? How would you demonstrate the excluded automation honestly?

**Common mistakes:** Treating the loudest stakeholder as policy owner; hiding a review bypass in the demo; assuming all recommendations have equal consequence.

</details>

<a id="qb-fde-007"></a>

## QB-FDE-007 — Translate a business complaint into an observable metric

| Field | Value |
|---|---|
| Category | Operational measurement |
| Difficulty | Beginner |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Operational measurement; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-008 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Customer_Facing_System_Design.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A sales team says the assistant “wastes time.” Interviews reveal users repeatedly open citations that do not support the displayed claim. Propose one primary outcome metric, diagnostic measures and a small evaluation sample. Avoid treating click count alone as value.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use a task outcome such as time to a correctly supported decision or the proportion of tasks completed with verified supporting evidence. Define start/end events and what counts as a correct supported decision with the customer’s reviewer. Measure citation support failure rate, correction effort and abandonment as diagnostics, and segment by query intent and source type. Build a small reviewed sample of realistic tasks with authorized source snapshots and explicit support judgments. Observe users completing the tasks with the current experience and a proposed fix, recording where they re-open sources or repair answers. Citation clicks alone are ambiguous: fewer clicks could mean better support, hidden citations or unsafe over-trust. Keep quality and access constraints as guardrails, and avoid reporting saved minutes until the baseline and comparison use consistent measurement rules and comparable tasks.

**Explanation:** The business complaint points to verification effort. Measuring answer speed alone would miss why a fast assistant still consumes more human time.

**Followups:** What if users stop checking citations because the UI looks confident? How would you separate poor retrieval from misleading citation placement?

**Common mistakes:** Equating fewer clicks with success; measuring only token latency; judging support from whether a URL opens.

</details>

<a id="qb-fde-008"></a>

## QB-FDE-008 — Work safely while a data dependency is blocked

| Field | Value |
|---|---|
| Category | Execution planning |
| Difficulty | Intermediate |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Execution planning; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-009 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/FDE_Interview_Roadmap.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A customer’s security review delays data access by five business days. The delivery date remains unchanged. You own the prototype, and no permission to bypass the review exists. Produce a dependency-aware plan and a sponsor update that keeps useful work moving.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Separate tasks requiring customer data from tasks that can proceed with approved fixtures: connector schema contracts, redacted sample validation rules, interface wiring, failure states, local tests and the evaluation rubric. Identify the specific access decision, its owner and the latest date required for a meaningful customer-data validation. Replan a bounded deliverable around that dependency, with an explicit integration/quality gate rather than marking simulated results complete. Give the sponsor options such as reducing the pilot workflow, extending validation or delivering a labeled prototype by the original date. Explain which outcome each option can actually establish and request a decision from the accountable owner. Maintain a short risk/assumption ledger and scheduled check-in. The objective is useful progress with visible uncertainty, not pressuring someone to export data through an unapproved channel or silently absorbing an impossible validation commitment.

**Explanation:** A blocked dependency does not block every task. The plan should preserve a real acceptance gate while making the deadline tradeoff reviewable.

**Followups:** What if only a schema can be shared? How do you stop synthetic fixture assumptions from becoming production requirements?

**Common mistakes:** Treating review delay as permission to bypass; promising full validation on inaccessible data; stopping all independent work.

</details>

<a id="qb-fde-009"></a>

## QB-FDE-009 — Make one customer customization maintainable

| Field | Value |
|---|---|
| Category | Product versus bespoke integration |
| Difficulty | Advanced |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Product versus bespoke integration; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-010 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Customer_Engineering.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

One customer needs an extra approval step for orders above a configurable threshold. Another customer may need similar behavior, but no second requirement is confirmed. Decide where the change belongs and what to avoid generalizing before evidence exists.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Represent the known rule through a narrow, typed policy/configuration boundary if the product already supports an approval transition: currency/amount interpretation, threshold, approver role and fallback behavior should be explicit. Keep customer-specific mapping from their order fields in the connector layer, while the workflow consumes the normalized domain value. Add tests for below, equal and above threshold, missing amount and rule updates while a request is awaiting review. Do not build a general visual workflow language or arbitrary scripting engine merely because a second customer might eventually ask. Record which assumptions are specific and which small interface can support future variation. If the current architecture cannot express the rule cleanly, compare a contained extension with a documented temporary customization, including ownership and removal/migration plans. Maintainability depends on stable boundaries and support responsibility, not on maximizing abstraction count.

**Explanation:** A single concrete requirement can justify a small extension without justifying a platform. Equality and missing-data behavior are product decisions that must be specified.

**Followups:** What happens when the threshold changes after approval? When would repeated customer variation justify a broader policy model?

**Common mistakes:** Embedding tenant IDs throughout business logic; creating an unrestricted rules engine; leaving temporary customization without an owner.

</details>

<a id="qb-fde-010"></a>

## QB-FDE-010 — Handoff a pilot with a recoverable operating model

| Field | Value |
|---|---|
| Category | Operational readiness and handoff |
| Difficulty | Advanced |
| Target roles | Forward Deployed Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Operational readiness and handoff; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-FDE-001 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/Forward-Deployed-Engineer/AI_Deployment_and_Integration.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A read-only document assistant has passed its agreed pilot tasks. The customer asks their operations team to own it next month. Specify the handoff package and one recovery exercise. Do not claim the pilot proves production scale or every permission case.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Provide an architecture and data-flow map, source/connector ownership, configuration and version inventory, access model, retention policy and the exact pilot acceptance evidence with limitations. Add runbooks for failed ingestion, stale index, provider errors and rollback, including alerts, dashboards and escalation contacts. Define who approves changes, reviews quality samples and pays or monitors usage. Transfer operational access through approved identity processes rather than sharing personal credentials. Run a bounded recovery exercise, such as a failed snapshot import: an operator detects the alert, follows the runbook, preserves the last accepted snapshot, retries safely and verifies freshness on agreed sample queries. Record timing and unclear steps, then improve the runbook. A successful handoff means named people can operate and recover the defined scope; it does not turn a small pilot into evidence of production load capacity or comprehensive security testing.

**Explanation:** Documentation plus an owner-run recovery exercise reveals gaps that a developer-led demo can hide. The acceptance boundary remains the pilot’s actual scope.

**Followups:** Which metrics need an operational threshold and owner? How should model/prompt changes be reviewed after handoff?

**Common mistakes:** Handing over only a repository URL; leaving personal credentials in jobs; presenting pilot task success as full production readiness.

</details>
