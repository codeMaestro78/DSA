# Advanced cross-domain question bank

## Scope and use

These are original **[INFERRED], company-neutral** practice questions, not reports of employer questions or an unpublished rubric. Attempt the prompt before opening the answer. Difficulty is editorial: Beginner establishes a foundation, Intermediate applies it, Advanced adds interacting failure/measurement constraints, and Expert is an optional specialist extension for this early-career curriculum.

The guidance describes expected reasoning and valid alternatives rather than a single required script. Use real personal evidence for experience questions, or explicitly label a hypothetical answer. No candidate history, metric or production claim is supplied for you to adopt.

<a id="qb-adv-001"></a>

## QB-ADV-001 — Acknowledged writes after regional failover

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Regional recovery |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Durability scope and business reconciliation. |
| Related IDs | QB-ADV-002 |
| Sources | [PostgreSQL standby replication](https://www.postgresql.org/docs/18/warm-standby.html); [fault tolerance](../04-System-Design/Fault_Tolerance.md) |

### Prompt

Region A acknowledges a write. Replication to B is asynchronous. A becomes unavailable before the write reaches B, and B is promoted. Explain what can and cannot be promised about the acknowledged operation and a retried external action.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The acknowledged database write may be absent in B under this failure sequence. State the configured durability/replication contract and actual RPO; an asynchronous replica does not imply zero data loss. Fence the old writer before accepting conflicting writes, and reconcile missing/unknown business operations using stable identities and authoritative evidence. An external action may have completed even if the promoted database lacks its local record.

**Explanation:** A valid design can choose synchronous acknowledgment constraints or accept an explicit loss/recovery policy, but must account for unavailable peers and latency. The operation’s database state and external effect are separate facts. Retrying with a new identity risks duplication; replaying a missing local idempotency record cannot prove the provider did nothing. No failover mechanism is implemented by this answer.

**Followups:** What if A returns after B accepts writes? Which records must survive independently for reconciliation?

**Common mistakes:** Calling replication a backup, promising zero loss from asynchronous replication or treating missing state as proof of no effect.

</details>

<a id="qb-adv-002"></a>

## QB-ADV-002 — Snapshot consistency with permission revocation

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Authority lifecycle |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Conflicting lifecycle requirements. |
| Related IDs | QB-ADV-003 |
| Sources | [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html); [enterprise RAG design](../04-System-Design/AI-System-Design/Enterprise_RAG_Platform.md) |

### Prompt

A long-running answer job uses a frozen document version and permission snapshot. Access is revoked before the answer is shown. Compare reproducibility, current authorization and the limits of data already sent to a model.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Keep content/version provenance for reproducibility only under the permitted retention policy, while checking current authority at the specified exposure/action boundaries. A frozen evaluation snapshot cannot grant a user permanent access. Define how revocation invalidates queued work, retrieval/text caches and output publication. Already transmitted or displayed information cannot be retracted by a later database check.

**Explanation:** The system needs an explicit exposure contract and propagation bound rather than the slogan “snapshot isolation handles it.” Versioned source data and authorization have different owners and lifecycles. If policy authority is unavailable, choose a declared safe failure/degraded behavior rather than silently serving private cached text. Retention and audit requirements need an organizational policy; this answer does not assert a universal legal retention rule.

**Followups:** How would a paused human approval be invalidated? What must an evaluation artifact retain or delete?

**Common mistakes:** Treating immutable provenance as immutable permission or promising retrospective removal from a provider without its contract.

</details>

<a id="qb-adv-003"></a>

## QB-ADV-003 — Hedged inference under a strict budget

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Speculative execution |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Admission, uncertain usage and speculative work. |
| Related IDs | QB-ADV-004 |
| Sources | [AWS retry/timeout guidance](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/); [AI cost contracts](../08-LLM-RAG-and-Agent-Engineering/AI_Cost_Optimization.md) |

### Prompt

A service races two model requests and returns the first acceptable answer. Client cancellation does not guarantee provider cancellation. Explain how to enforce a job budget and report cost without double-counting or assuming the losing request is free.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Reserve a conservative budget for every admitted attempt, track independent attempt IDs and keep the root job’s aggregate limit authoritative. Validate answer acceptability before declaring a winner; request cancellation of losers but reconcile their actual usage/status when available. Unreleased uncertain reservations may reduce availability until reconciled. Charge or attribute known usage once under the billing contract, distinguishing estimates from actual provider reports.

**Explanation:** A response race changes latency opportunity, not the total work already admitted. Releasing a loser’s full reservation merely because the client disconnected can allow overspending. A stronger hard bound requires enforceable provider limits and conservative admission assumptions; if those are absent, describe an estimate/alert policy rather than an absolute guarantee. No real billing/cancellation API is exercised here.

**Followups:** What if both responses arrive together? Which quality gate can reject the fastest answer?

**Common mistakes:** Assuming first response is best, transport close stops compute, or all providers expose exact live cost.

</details>

<a id="qb-adv-004"></a>

## QB-ADV-004 — Adaptive tuning exhausts the test set

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Evaluation selection |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Selection bias and uncertainty. |
| Related IDs | QB-ADV-005 |
| Sources | [Evaluation pitfalls](https://scikit-learn.org/stable/common_pitfalls.html); [evaluation platform](../04-System-Design/AI-System-Design/AI_Evaluation_Platform.md) |

### Prompt

You compare 100 prompt variants on the same 40 labeled tasks and publish the best variant’s score as an unbiased estimate. What has gone wrong and how would you evaluate the selected procedure?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The 40 tasks became tuning/selection data. Evaluate the selected configuration or selection procedure using genuinely held-out representative data, or an appropriately designed nested evaluation when data permits. Record all choices, judge/model versions and task dependence; inspect paired outcomes and slices. Do not attach ordinary uncertainty language that ignores selection or correlated examples.

**Explanation:** Even unchanged labels do not preserve a test set’s independence after its results guide choices. More prompt variants create more opportunity to fit quirks or noise. A held-out score still depends on sampling/label quality and should not be described as a universal production guarantee. A fresh evaluation should also include failures and unanswerable/permission-sensitive tasks relevant to deployment.

**Followups:** What if budget permits only ten new tasks? How would you report limitations rather than invent confidence?

**Common mistakes:** Renaming tuning data “test,” reporting only the winning score or treating model-judge agreement as ground truth.

</details>

<a id="qb-adv-005"></a>

## QB-ADV-005 — A judge follows the evaluated answer

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Evaluator security |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Untrusted evaluation inputs and calibration. |
| Related IDs | QB-ADV-006 |
| Sources | [OWASP prompt-injection guidance](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html); [AI evaluation](../08-LLM-RAG-and-Agent-Engineering/AI_Evaluation.md) |

### Prompt

An answer being graded contains instructions telling the model judge to award full marks. The judge receives that answer and the reference in one context. Design controls and a validation plan.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Treat evaluated text as data, constrain the grading task/interface and keep it separate from privileged tools. Validate the judge’s structured output and calibrate against reviewed benign/adversarial cases. Use deterministic checks where possible, blind/reorder comparisons as appropriate and inspect disagreement or suspicious score shifts. No prompt delimiter or schema alone proves injection immunity.

**Explanation:** The judge can produce valid JSON while following the wrong instruction. Controls reduce risk but need task-specific adversarial evaluation and human review for consequential decisions. Preserve judge/prompt versions and label provenance. A second judge sharing the same failure mode is not independent proof. Do not give evaluated content access to credentials or actions merely to score it.

**Followups:** What examples would falsify the proposed control? Which metrics should remain separate from judge preference?

**Common mistakes:** Treating schema validity as honest grading or claiming a single refusal phrase blocks every injection.

</details>

<a id="qb-adv-006"></a>

## QB-ADV-006 — Late labels and a misleading quality dashboard

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Delayed evaluation |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Missingness, denominator and delayed evidence. |
| Related IDs | QB-ADV-007 |
| Sources | [Model evaluation](../07-AI-ML-Deep-Learning/Model_Evaluation.md); [evaluation platform](../04-System-Design/AI-System-Design/AI_Evaluation_Platform.md) |

### Prompt

Only fast-successful requests receive labels promptly; slow failures are labeled days later. Today’s dashboard excludes unlabeled runs and shows improvement. Explain a more faithful report.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Report total eligible runs, labeled/unlabeled/failed counts, label age and selection policy. Compare cohorts with comparable label maturity or use a justified delayed-label analysis. Keep operational failures and quality outcomes distinct without dropping inconvenient cases. Inspect whether labeling depends on the outcome or latency, since the observed subset may not represent the target population.

**Explanation:** A high score on selected labeled cases can coexist with worse overall behavior. Assigning an arbitrary zero to every unlabeled case is also a modeling choice, not automatic truth. Show coverage and bounds or uncertainty justified by assumptions, retain stable run IDs for later joins and revise historical cohorts as labels mature under a declared policy.

**Followups:** How would you compare two deployments with different labeling delay? What can be reported immediately?

**Common mistakes:** Silently shrinking the denominator, calling missing labels successes or treating label arrival as random without evidence.

</details>

<a id="qb-adv-007"></a>

## QB-ADV-007 — Point-in-time features with late-arriving facts

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Feature availability |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Availability time and bitemporal reasoning. |
| Related IDs | QB-ADV-008 |
| Sources | [Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml); [feature engineering](../07-AI-ML-Deep-Learning/Feature_Engineering.md) |

### Prompt

A training row predicts a purchase at time T. A feature’s business event time is before T, but your system learned that fact after T. Can the historical row use it? Explain the deployment question.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** For a model intended to reproduce decisions made at T, use information available to the serving system then, not merely events whose business timestamps are earlier. Track event time and ingestion/availability or revision time as needed. Reconstruct a point-in-time join, prevent future corrections from leaking into historical features and specify how late data updates later decisions.

**Explanation:** A source event can be old while its knowledge is new. The correct dataset depends on the prediction/deployment contract; a retrospective research task may intentionally ask a different question, which must be stated. Test a tiny fixture with one late fact and inspect training/serving parity instead of declaring a generic time split sufficient.

**Followups:** Which timestamp belongs in a cache key? How do backfilled aggregates change reproducibility?

**Common mistakes:** Filtering only event_time <= T or using a present-day aggregate for every historical row.

</details>

<a id="qb-adv-008"></a>

## QB-ADV-008 — Two simultaneous resource budgets

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Admission liveness |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Resource ownership and liveness. |
| Related IDs | QB-ADV-009 |
| Sources | [SRE overload guidance](https://sre.google/sre-book/handling-overload/); [agent platform](../04-System-Design/AI-System-Design/AI_Agent_Platform.md) |

### Prompt

A worker must reserve both tenant tokens and provider concurrency. One implementation holds a tenant reservation while waiting indefinitely for provider capacity. Explain starvation/deadlock risks and a bounded admission design.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Define one admission owner or a consistent acquisition protocol with bounded waits, cancellation and release/expiry rules. Avoid holding one scarce resource indefinitely while waiting for another. Track reservation identity/version so retries and cleanup cannot double-release or leak capacity. Enforce tenant fairness and provider limits at their actual scopes, then distinguish bounded admission from a guarantee that external work completes.

**Explanation:** Holding one resource can cause capacity hoarding or starvation; deadlock additionally needs a cyclic dependency, such as another path holding provider capacity while waiting for tenant tokens. A timeout must leave ownership clear: did admission fail, or did work start? Expiring a reservation while external work still runs can oversubscribe the real provider. Conservative reconciliation may reduce capacity but preserves the declared accounting model. A local lock order alone does not enforce a distributed provider quota without a shared mechanism.

**Followups:** What if the worker crashes after admission? Which resource can be safely reclaimed before provider completion?

**Common mistakes:** Assuming timeouts cancel all work, using independent per-process counters for a global limit or leaking partial reservations.

</details>

<a id="qb-adv-009"></a>

## QB-ADV-009 — Replay after a tool-schema migration

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Workflow evolution |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Semantic versioning and recovery. |
| Related IDs | QB-ADV-010 |
| Sources | [API contracts](../04-System-Design/API_Design.md); [agent state ownership](../04-System-Design/AI-System-Design/AI_Agent_Platform.md) |

### Prompt

A durable job stored a tool proposal under contract v1. After deployment, v2 changes the meaning of one amount field while keeping its numeric shape. What happens when the job resumes?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Persist the contract/version and normalized meaning of accepted proposals. On resume, either route to a compatible v1 adapter, apply an explicit validated migration or stop for review. Reauthorize current resources and bind any approval to the actual action meaning. Do not send an old numeric payload to a new adapter merely because JSON validation passes.

**Explanation:** Schema compatibility is more than field types. Units, defaults, enumerations and business interpretation can change without a shape error. Evaluation and rollout need mixed-version/in-flight cases and a rollback strategy; external idempotency keys must identify the intended action rather than accidentally reuse an identifier for changed semantics.

**Followups:** How would you migrate pending approvals? What if rollback restores code but not stored state?

**Common mistakes:** Equating valid JSON with compatible meaning or silently rewriting already approved arguments.

</details>

<a id="qb-adv-010"></a>

## QB-ADV-010 — Tail latency from correlated stages

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Latency statistics |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Quantiles, correlation and units. |
| Related IDs | QB-ADV-011 |
| Sources | [SRE monitoring](https://sre.google/sre-book/monitoring-distributed-systems/); [numerical fixture](../validation/questions-review/README.md) |

### Prompt

For 100 requests, stage A takes 1 ms on 95 and 100 ms on five; B has the same distribution. Define empirical p95 as sorted value at ceil(0.95*n)-1. Compare aligned versus disjoint slow-request sets. Can you add stage p95 values?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Each stage’s empirical p95 is 1 ms. With the same five slow requests, total latency is 2 ms on 95 and 200 ms on five, so total p95 is 2 ms. With disjoint slow sets, total is 2 ms on 90 and 101 ms on ten, so total p95 is 101 ms. The marginal stage summaries are identical, but the end-to-end tail differs.

**Explanation:** Percentiles generally do not add. Measure complete request durations over the intended population and use correlated spans to diagnose the distribution. This fixture assumes serial nonoverlapping stages and no other work; concurrent stages, omitted errors or unmatched populations change the calculation. The local reasoning suite checks the stated synthetic values, not a live service.

**Followups:** How do retries or excluded failures alter the population? What if stages overlap?

**Common mistakes:** Adding p95 values, mixing different cohorts or calling synthetic arithmetic a production benchmark.

</details>

<a id="qb-adv-011"></a>

## QB-ADV-011 — Deletion versus reproducible evaluation artifacts

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Artifact lifecycle |
| Difficulty | Expert |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Retention, authorization and evidence limits. |
| Related IDs | QB-ADV-012 |
| Sources | [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html); [RAG lifecycle](../04-System-Design/AI-System-Design/Enterprise_RAG_Platform.md) |

### Prompt

An evaluation run retained private document chunks to reproduce scores. A later policy change requires those chunks to become inaccessible. Explain a design that preserves useful audit evidence without claiming every old result remains exactly reproducible.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Identify artifact owners, retention/access policy and all copies, including caches, exports and logs. Apply revocation/deletion according to the actual policy, retain only permitted metadata or aggregates and record that exact content reproduction is no longer available. Keep lineage/version identifiers where permitted and prevent old artifacts from being treated as current retrieval authority.

**Explanation:** Reproducibility is valuable but does not itself authorize indefinite retention or access. Hashes/embeddings may still be sensitive and do not automatically make data anonymous. The organization must choose and document an acceptable policy; this answer makes no universal legal erasure or backup guarantee. Future evaluations can use permitted synthetic/public fixtures while acknowledging the changed evidence boundary.

**Followups:** Which claim survives when source text is removed? How would expired artifacts appear in a report?

**Common mistakes:** Keeping every copy for convenience, promising removal from third parties without a contract or claiming identical reruns after deleting inputs.

</details>

<a id="qb-adv-012"></a>

## QB-ADV-012 — Diagnose with incomplete telemetry

| Field | Value |
|---|---|
| Category | Architecture and evaluation / Observability tradeoffs |
| Difficulty | Advanced |
| Target roles | AI, ML, backend, RAG and platform engineers |
| Round | Design/technical deep dive |
| Experience | Advanced/Expert extensions; optional beyond the shared 0–2-year core |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Evaluates | Investigation under privacy constraints. |
| Related IDs | QB-ADV-001 |
| Sources | [SRE monitoring](https://sre.google/sre-book/monitoring-distributed-systems/); [AI observability](../04-System-Design/AI-System-Design/AI_Observability_Platform.md) |

### Prompt

A privacy-preserving service logs metadata but not raw prompts. One customer reports a wrong answer that cannot be replayed exactly. Explain a useful investigation and how you would improve observability without logging all private inputs.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Correlate the permitted request/run ID with model/prompt/index/tool versions, timing, status and policy decisions. Ask for a shareable reproduction or consented bounded sample through the appropriate process, and test representative synthetic cases for the suspected mechanism. State which hypotheses cannot yet be distinguished. Improve redacted/structured signals, controlled sampling and retention/access policy rather than defaulting to indiscriminate content logging.

**Explanation:** An inability to replay exactly is an evidence limit, not proof the user is wrong or the model is at fault. Metrics can reveal a version-correlated regression without disclosing content, while source/citation identifiers may support authorized review. The next check should discriminate causes. No telemetry design removes all privacy and diagnostic tradeoffs.

**Followups:** What if only one tenant is affected? Which additional field would provide the most evidence for the least exposure?

**Common mistakes:** Demanding private data, asserting a root cause without evidence or treating a trace as the durable job record.

</details>

## Verification and practice record

Answers were reviewed as reasoning guidance. Unless a record links executable evidence, no runtime, live interview, security audit or production validation is implied. Record the ID, assumptions, hints, evidence and next variation in the progress tracker. Related questions connect decisions; they are not duplicates or guaranteed employer followups.

**Last verified:** 2026-11-02. Primary sources support the underlying concepts; prompts and answer structures are original. [Complete timed role mocks](../13-Mock-Interviews/README.md) are delivered in Phase 6. Editorial status rechecked **2026-11-03**; the source-inspection date above is unchanged.
