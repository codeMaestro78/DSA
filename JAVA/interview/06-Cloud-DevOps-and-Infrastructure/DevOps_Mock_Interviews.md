# DevOps mock — the tested artifact and the deployed artifact

This is a complete focused **[INFERRED]**, company-neutral variant for approximately 0–2 years of experience. All facts and numbers are fictional teaching fixtures. It supplements the [Phase 6 integrated session](../13-Mock-Interviews/Startup_Mock_Interview.md); it is not an employer assessment or permission to perform a real action. No implementation, provider, customer, host, cluster or deployment is exercised here.

## Candidate packet — attempt first

**Mock ID:** OPS7-MOCK-01. **Duration:** 60 live minutes; preflight and 10–15-minute debrief are additional. Use notes, a diagram and calculator. No real system access or generative assistance is needed for the default independent attempt. Agree tools and format first. Keep the disclosure below closed. Read [the prerequisite](CI_CD.md) before the session if needed.

### Stipulated scenario

A fictional small team has a web application, a relational database and a CI pipeline. CI tests image digest A for source revision R. The release stage rebuilds R against a moving base tag and deploys digest B. A privileged release runner also runs a contributor-controlled script. During a later rollout, old and new application replicas coexist while a migration removes a field still read by old replicas.

No actual repository workflow, cloud account or cluster is available. The deadline is a review milestone, not permission to change production. Service objectives, release authority, credential scope and data-recovery evidence need clarification. An HTTP200 health response exists as a hypothetical observation only; no complete user journey was tested.

### Four-stage timeline and questions

| Stage | Minutes | Candidate task |
|---|---|---|
| 1 | 10 | Identify the user-level acceptance contract, release identity and trust boundaries. Prioritize missing facts that would change the recommendation. |
| 2 | 20 | Design a build-once/promotion flow with isolated untrusted tests, artifact provenance and scoped release authority. State which evidence applies to which digest. |
| 3 | 20 | Trace the mixed-version migration failure and a failed rollout. Propose a compatible change sequence, containment/rollback or forward repair, and required data/restore checks. |
| 4 | 10 | Give a two-minute go/limited-go/no-go recommendation with a bounded verification plan, owner, stop conditions and one tradeoff. State what was not executed. |

Save assumptions, calculations, flows and uncertainty before reading the review. Asking for a stipulated clarification is H0 and does not reduce independence. Record supplied direction as H1/H2/H3, then assess its actual effect. A new attempt after reading the answer is guided retention evidence; use a changed case later to test transfer.

<details><summary>Interviewer packet and worked review — reveal only after attempting</summary>

## Interviewer administration

Ask one focused question at a time and use the four-stage budget. Offer a neutral clarification before a hint. Do not silently add advanced requirements or reward an unsupported production promise. Keep optional extensions after a sound core. Record tools, pauses and exact hint content.

H0: A and B have different recorded digests. No evidence establishes their contents as equivalent. The contributor controls the script but should not receive production authority. No irreversible migration has actually been run in this exercise; describe safe reasoning without issuing deployment or database commands.

### Stage 1 review — minutes 0–10

Separate source identity from artifact identity. Passing checks for A establish nothing about an unexplained B beyond the available provenance. Authentication/process health is not user-level acceptance. Ask which journeys, permissions, schema versions and recovery objectives matter, who approves release and what credentials are exposed to untrusted code.

### Stage 2 review — minutes 10–30

Build a recorded immutable artifact from controlled inputs, attach source/dependency/toolchain/test provenance, then promote that same digest through environments. Treat environment configuration as separately versioned input. Untrusted tests run without release credentials in isolated execution; a reviewed release consumes trusted artifacts under a narrowly scoped identity/ref/environment policy. Short-lived credentials are still sensitive. Verify artifact signatures/provenance against a policy rather than assuming a filename or green badge proves trust.

### Stage 3 review — minutes 30–50

Use expand/compatible-read-write/migrate/contract sequencing when applicable, with verified rollout order and owners. Do not remove a field while old readers still require it. Reverting application code does not recreate removed data or make a destructive schema change reversible. Stop incompatible promotion, determine whether data repair or a forward-compatible version is required and use demonstrated backups/reconciliation under an owner. Test mixed versions and response-loss/retry cases; a deployment controller rollback alone does not restore application/data consistency.

### Stage 4 review — minutes 50–60

A defensible recommendation is no production promotion until artifact identity, privileged execution and compatibility gaps are addressed. A limited isolated demonstration can proceed without production secrets/data. Require evidence for the selected digest, a representative authorized journey, cross-tenant denial, mixed-version compatibility and recovery/rollback checks. Name monitoring/stop owner and keep unknown SLO/capacity explicit. No actual deployment, exploit, migration or rollback is demonstrated by this plan.

### Hints and followup branches

**H1:** ask which requirement or fact changes the current answer. **H2:** point to the decisive identity, version, denominator or ownership boundary, without finishing the trace. **H3:** supply one worked transition or numerator/denominator and ask the candidate to finish independently. Log what was supplied; do not apply a universal score deduction to unrelated evidence.

**If stuck:** reduce to one record, one request or one failure point and retain partial correct work. **If finished:** change the failure point, introduce an out-of-order/duplicate transition or remove an assumed guarantee. Ask what observation would refute the preferred explanation. The reference approach is not the only valid implementation: accept a simpler batch/polling/manual-review design when it meets the stated contract and explains its costs.

### Task-specific evaluation

Use the [shared six-dimension rubric](../13-Mock-Interviews/Interview_Evaluation_Rubric.md). These are level 3 evidence anchors, not a pass threshold. Use 0–2 according to actual errors/help; leave unobserved dimensions Not assessed. Level 4 requires repeated independent evidence across variations, not one fluent mock.

| Dimension | Observable level 3 evidence | Blocker or next remediation |
|---|---|---|
| CONTRACT | Defines artifact/user acceptance and release authority | Calls the deadline or health200 launch approval |
| REASONING | Connects provenance and compatibility gates to concrete failures | Adds tools without closing the original gaps |
| CORRECTNESS | Promotes the tested digest and preserves mixed-version schema contract | Treats commit equality as artifact equivalence |
| VALIDATION | Specifies trusted evidence and representative failure checks | Claims a hypothetical green pipeline is executed proof |
| RISK | Separates untrusted code from privileged release | Exposes production credentials or assumes schema rollback is automatic |
| COMMUNICATION | Gives bounded decision, owner and missing evidence | Invents a successful release or conceals destructive uncertainty |

**Common mistakes:** converting a proposed test into an executed claim, hiding a known contract violation behind a favorable metric, assuming a local state change cancels a remote effect, or inventing past ownership. **Strong indicators:** a concrete counterexample, a bounded next check, honest source/authority limits and an alternative with explicit consequences.

</details>

## Debrief and proof boundary

Spend 10–15 minutes recording task evidence, hints and one or two improvements. No candidate score, customer endorsement or observed system result is prefilled. Worked arithmetic and traces are reviewed reasoning only; no new executable suite is claimed. The linked canonical chapters and [source register](PHASE7_SOURCES.md) support the mechanisms; they do not establish the fictional outcomes.

[Parent index](README.md). Last reviewed: **2026-11-03**.
