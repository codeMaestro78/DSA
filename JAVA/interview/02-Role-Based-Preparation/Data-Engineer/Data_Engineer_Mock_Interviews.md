# Data engineer mock — corrected daily facts

This is a complete focused **[INFERRED]**, company-neutral variant for approximately 0–2 years of experience. All facts and numbers are fictional teaching fixtures. It supplements the [Phase 6 integrated session](../../13-Mock-Interviews/System_Design_Mock_Interview.md); it is not an employer assessment or permission to perform a real action. No implementation, provider, customer, host, cluster or deployment is exercised here.

## Candidate packet — attempt first

**Mock ID:** DATA7-01. **Duration:** 60 live minutes; preflight and 10–15-minute debrief are additional. Use notes, a diagram and calculator. No real system access or generative assistance is needed for the default independent attempt. Agree tools and format first. Keep the disclosure below closed. Read [the prerequisite](Data_Engineering_Roadmap.md) before the session if needed.

### Stipulated scenario

A fictional team publishes a tenant’s daily order-value total. The source owns order events with `(tenant, event_id, revision, business_date, amount, deleted, source_position)`. Within an event, a higher revision replaces the previous revision, rather than adding another order. Re-delivery of the same revision is possible. A revision’s payload is immutable; a conflicting payload for the same identity is an explicit source error. Use integer amount units and a declared nonnegative bound. Your exercise processes authorized synthetic records only; no real source connection or SQL execution is needed.

For one date and tenant, the arrival sequence is E1/rev1/10, E2/rev1/5, E1/rev1/10 again, then E1/rev2/12. A later accepted E2/rev2 tombstone removes E2 from the current total. Another order for that business date arrives tomorrow. No finality/allowed-lateness policy or reporting deadline has been agreed. Source positions are supplied for a snapshot boundary; they are not event times.

### Four-stage timeline and questions

| Stage | Minutes | Candidate task |
|---|---|---|
| 1 | 10 | Clarify the grain, current-state versus historical reporting, duplicate/revision rules, publication owner and missing lateness/retention decisions. |
| 2 | 20 | Trace the four arrivals and tombstone. Design a raw-history, selected-current-state and aggregate publication flow. Explain the identity/transaction/checkpoint contract, quarantined input and tenant isolation. |
| 3 | 20 | Design tomorrow’s late-order correction and a backfill that cannot silently overwrite a newer published result. Define reconciliation/quality checks, deletion propagation and an atomic publication boundary. |
| 4 | 10 | Give a two-minute report explaining what is known, the next check, one failure risk and a bounded readiness decision. Explain a real prior data bug only if it actually happened; otherwise use this hypothetical. |

Save assumptions, calculations, flows and uncertainty before reading the review. Asking for a stipulated clarification is H0 and does not reduce independence. Record supplied direction as H1/H2/H3, then assess its actual effect. A new attempt after reading the answer is guided retention evidence; use a changed case later to test transfer.

<details><summary>Interviewer packet and worked review — reveal only after attempting</summary>

## Interviewer administration

Ask one focused question at a time and use the four-stage budget. Offer a neutral clarification before a hint. Do not silently add advanced requirements or reward an unsupported production promise. Keep optional extensions after a sound core. Record tools, pauses and exact hint content.

H0: the consumer must select the highest accepted revision for each `(tenant,event_id)` at a declared source-position cutoff. The exercise asks for both reproducible cutoff reports and a current corrected report; they are separate versions. Tombstones remove current contribution but remain as permitted lineage. No fixed late-arrival cutoff may be invented without recording it as a proposed policy.

### Stage 1 review — minutes 0–10

Name the source grain before SQL/tools. One event can have several revisions; selected current rows are not the same grain as raw deliveries. Ask whether consumers need an “as published” snapshot, corrected business-date truth or both, who accepts restatements, how private data may be retained, and what deadline matters. A simple bounded batch can be correct without adopting a streaming platform.

### Stage 2 review — minutes 10–30

The first arrivals give 10, then 15, still 15 on duplicate E1/rev1, then 17 after replacing10 with 12. The E2 tombstone makes the current total 12. Raw summation would incorrectly double count; DISTINCT amount would also fail for legitimate equal amounts. Persist raw identities/positions within approved retention, choose latest revision at the cutoff and aggregate live selected rows. Enforce duplicate identity/immutable payload rules in the actual write mechanism. Coordinate durable output/consumer position or make replayed writes idempotent; a saved offset alone is not an end-to-end exactly-once proof.

### Stage 3 review — minutes 30–50

Keep business time and availability/ingestion time separate. Tomorrow’s legitimate order can trigger a new corrected report version, while an earlier snapshot remains reproducible under retention policy. Version the backfill’s source cutoff, code/schema and target publication. Compute in isolation, reconcile source counts/selected keys/totals and publish an accepted generation atomically; prevent an old generation from replacing a newer one using an authoritative conditional update. A moving watermark does not prove all sources can no longer send old records. Deletion/revocation must also reach derived stores/caches/exports according to actual policy; historical reproducibility does not grant indefinite access.

### Stage 4 review — minutes 50–60

Report the17 then 12 worked totals as synthetic reasoning, not observed production output. The next useful check injects duplicate, conflicting revision, out-of-order older revision, tombstone and restart at the checkpoint/publication boundary, then compares with a recomputation oracle. Name owner, failed-state visibility and rollback to a compatible accepted report. Data freshness, quality and permission checks remain separate. A correct diagram does not establish a deployed connector or data-loss guarantee.

### Hints and followup branches

**H1:** ask which requirement or fact changes the current answer. **H2:** point to the decisive identity, version, denominator or ownership boundary, without finishing the trace. **H3:** supply one worked transition or numerator/denominator and ask the candidate to finish independently. Log what was supplied; do not apply a universal score deduction to unrelated evidence.

**If stuck:** reduce to one record, one request or one failure point and retain partial correct work. **If finished:** change the failure point, introduce an out-of-order/duplicate transition or remove an assumed guarantee. Ask what observation would refute the preferred explanation. The reference approach is not the only valid implementation: accept a simpler batch/polling/manual-review design when it meets the stated contract and explains its costs.

### Task-specific evaluation

Use the [shared six-dimension rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). These are level 3 evidence anchors, not a pass threshold. Use 0–2 according to actual errors/help; leave unobserved dimensions Not assessed. Level 4 requires repeated independent evidence across variations, not one fluent mock.

| Dimension | Observable level 3 evidence | Blocker or next remediation |
|---|---|---|
| CONTRACT | Distinguishes raw delivery, selected event and versioned report grains | Treats each revision as a new order |
| REASONING | Chooses a bounded processing/publication method from the stated needs | Adds streaming/sharding without a reason |
| CORRECTNESS | Derives17 then 12 and preserves identity/revision semantics | Raw sum, amount DISTINCT or stale generation overwrite |
| VALIDATION | Specifies independent recomputation and restart/revision cases | Calls a proposed backfill test executed |
| RISK | Keeps tenant authority, deletion and checkpoint ownership explicit | Retains private history by assumption or loses accepted work silently |
| COMMUNICATION | Separates cutoff truth, corrected truth and missing finality policy | Invents a source guarantee or personal delivery result |

**Common mistakes:** converting a proposed test into an executed claim, hiding a known contract violation behind a favorable metric, assuming a local state change cancels a remote effect, or inventing past ownership. **Strong indicators:** a concrete counterexample, a bounded next check, honest source/authority limits and an alternative with explicit consequences.

</details>

## Debrief and proof boundary

Spend 10–15 minutes recording task evidence, hints and one or two improvements. No candidate score, customer endorsement or observed system result is prefilled. Worked arithmetic and traces are reviewed reasoning only; no new executable suite is claimed. The linked canonical chapters and [source register](PHASE7_SOURCES.md) support the mechanisms; they do not establish the fictional outcomes.

[Parent index](README.md). Last reviewed: **2026-11-03**.
