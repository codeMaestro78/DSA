# Interviewer guide — paid data exports

## Restricted preparation

**Contains spoilers.** Use [the candidate packet](Startup_Mock_Interview.md), MOCK-START-01. This is an original 60-minute [INFERRED] product/engineering simulation for a supported early-career engineer, not a universal startup hiring format. Preflight and a 10–15-minute debrief are additional. Read [the common rubric](Interview_Evaluation_Rubric.md).

Use the given incident facts without embellishing their scope. Ask one question at a time. H0 factual clarification is not a direction hint; log H1/H2/H3 hints and evaluate actual independence rather than applying a mechanical deduction. No actual charge/refund, private record or real founder conversation is authorized or needed.

## Fixed clarification answers — H0

- The two-day request is a **demo/review milestone**, not a promised production release. One senior engineer can review the change. Product owns scope; support and the payment owner participate in incident/correction decisions.
- The first useful outcome can be a private test-mode end-to-end demonstration. Production charging waits for authorization, reconciliation, limits, tests and review. The adapter exists, but its idempotency retention, reference lookup and timeout/cancellation contracts are not documented in the packet; obtain authoritative documentation before relying on them.
- Only one fixed export price/currency is in scope. No discounts, recurring billing, tax calculation or general ledger implementation is required. Do not let those exclusions waive payment/accounting reconciliation.
- The desired user contract is one intended purchase per durable operation identity. Download requires ready artifact, confirmed payment and current resource access. Whether payment precedes generation is a tradeoff; require explicit compensation/reconciliation for whichever order is chosen.
- Stipulated maximum-size estimate excludes headers/escaping and object overhead. User-visible latency and sustained peak arrival rate are unknown. Streaming may bound application memory but does not prove throughput or total encoded-size bounds.
- The provider reconciliation fixture deliberately establishes **two successful charges** for the same intended purchase. The aggregate scope of affected purchases, whether any correction already happened, and current customer access remain unknown.

## Timed script and reference reasoning

### START-T1 — Bound the first useful release

**0–10 minutes.** Ask for a useful outcome rather than a feature list. At minute 6 request the size calculation; at minute 8 ask what could invalidate the plan.

**Expected solution:** Propose a bounded test-mode flow for one tenant/user, fixed price, capped CSV export, visible durable status and authorized download; explicitly label test charges and missing production evidence. Confirm data ownership, exact export columns/encoding/escaping, provider contract, acceptance owner, support/failure experience and available review. Exclude summaries, recurring payments and broad customization until there is a correct base flow. Estimate only after inspecting existing integration and dependencies; “two days” is not evidence that production readiness is achievable.

100,000×200=20,000,000 bytes=20 MB decimal per raw maximum-size fixture. Ten fully materialized raw payloads represent 200,000,000 bytes=200 MB decimal. Actual application memory includes objects/buffers/library overhead; final CSV includes variable content/escaping. Bounded streaming, encoded-byte caps and representative measurement are sensible next steps, not a proven memory/latency guarantee.

**Hints:** H1 “What is the smallest user outcome?” H2 distinguish demo from launch. H3 supply the multiplication and ask what was excluded. **Stuck branch:** ask for one exclusion and one missing dependency. **Finished branch:** ask how a row-count cap alone could still allow a huge export if fields have unbounded length.

### START-T2 — Trace one purchase and export

**10–30 minutes.** Ask for a lifecycle drawing and what survives a browser disconnect. At minute 22 ask about an unknown payment result.

**Expected solution:** Create a durable operation scoped to tenant/principal and an idempotent request identity/fingerprint including normalized export parameters and server-owned amount/currency. Reusing the identity for different parameters conflicts. Authorize the export query and status/artifact access; bind artifact to the operation and tenant. A client-supplied tenant/amount is not authority. Reconnect queries the existing operation rather than implicitly creating another purchase.

Represent export state and payment state separately because their facts can differ. For example, export is queued/running/ready/failed; payment is not_started/pending/unknown/confirmed/failed, with explicit reconciliation/correction records. A locally recorded “pending” is not provider confirmation. One acceptable ordering generates the private artifact first, then initiates payment and grants download only after authoritative success. If generation fails first, no charge should have been attempted. This incurs generation cost for an unpaid export; bound abandoned artifacts/retention. Charging first is also valid if failed generation triggers an owned, reconcilable correction process and the UI does not call the purchase fulfilled prematurely.

A local transaction may atomically record the intended attempt and an outbox dispatch record. It cannot commit the payment provider atomically. Persist stable per-operation/attempt identities and distinguish permitted retries of the same effect from a genuinely new attempt. Verify the provider's idempotency scope, retention, parameter matching and reconciliation support before defining automatic retry. Without those guarantees, leave unknown outcomes pending for authoritative reconciliation instead of charging with a new identity. Local uniqueness cannot make an external provider deduplicate by magic.

Download condition is ready artifact **and** confirmed payment **and** current access; a stale paid browser flag is insufficient. Keep artifact handles private/scoped, bound any signed access credential appropriately and disclose revocation limitations. A disconnect does not cancel durable work automatically. Define cancellation/recovery separately; a remote action may already have completed.

**Hints:** H1 “Which state is local versus external?” H2 “Can a private file be ready while payment is unknown?” H3 offer two state axes and ask for the download predicate. **Stuck branch:** trace only create → status → ready/paid download. **Finished branch:** after a provider idempotency window expires, ask why blindly reusing or replacing a key without reconciliation may violate the intended-purchase contract.

### START-T3 — Respond to the duplicate-charge report

**30–50 minutes.** Show the incident table. At minute 36 ask for known/unknown state; at minute 44 ask for a restart gate.

**Expected solution:** At 10:01 the application knows only that the response timed out. It cannot conclude the first charge failed. At 10:05 the supplied authoritative fixture establishes C1 and C2 both succeeded for the same intended purchase. K99 replaced the intended-effect identity on retry, enabling a second effect; the fixture does not prove how many other customers were affected or whether a correction is already underway.

Contain by disabling the unsafe new-charge/retry path or limiting it to reviewed test mode, while preserving unrelated application work and existing correctly authorized downloads where safe. Involve the senior reviewer, payment owner and support. Preserve operation, provider reference, attempt/version and reconciliation evidence with appropriate privacy/access controls. Compare authoritative provider and local records; identify the affected time/version/cohort and classify known-success, known-failure and unknown effects. Do not replay all unknown jobs or initiate blanket refunds from incomplete logs.

A controlled correction plan assigns an owner to determine the legitimate intended purchase and whether a correction already exists, then uses the provider's verified correction contract with stable correction identity and reconciliation. The exercise requires the plan, not performing a financial action. Communicate confirmed affected cases and unresolved scope, the containment taken and an agreed next update; avoid claiming “no money lost” or a complete root cause without evidence.

Repair the durable identity/retry/reconciliation boundary. Test response loss after provider success, duplicate queue delivery, two concurrent attempts, parameter mismatch, stale retry after retention expiry, generation failure, replay of a correction and cross-tenant status/download. Restart only after the unsafe path is disabled/fixed, a reviewer accepts the evidence, affected work is reconciled under an owner, limits/monitoring exist and a small staged rollout has explicit stop conditions. A local fake provider can test state-machine logic but cannot establish the real provider's contract or financial reconciliation.

**Hints:** H1 “What did the timeout prove?” H2 compare K42 with K99. H3 draw provider success → response loss → new-key request. **Stuck branch:** ask for one safe containment and one authoritative fact. **Finished branch:** remove the 10:05 fixture and ask how the recommendation changes when the first outcome remains unknown.

### START-T4 — Negotiate a changed priority

**50–60 minutes.** Play the founder requesting summaries and keep the last two minutes for the candidate's recommendation. Do not reward an unsupported promise merely for sounding agreeable.

**Expected solution:** Explain that correctness of purchase identity and customer trust is a prerequisite to a broader paid flow. Propose finishing a bounded test-mode export/reconciliation demonstration and separately scoping summaries using permitted data after the incident boundary is addressed. Identify summary acceptance, privacy/model-processing permission, review/quality/cost requirements and a decision owner; the request itself is not authority to upload customer records. Give a concrete tradeoff: adding summaries now displaces a named validation/recovery task, so product/senior review must choose with the risk visible.

A concise response can say: “The current evidence shows two successful charges for one intended purchase in the fixture, while broader scope is unresolved. I recommend keeping unsafe charging paused, prioritizing reconciliation and the corrected export flow for the review milestone, and proposing summaries as a separate scoped experiment. I need the payment owner and reviewer to accept the restart evidence; I will bring the remaining dependency/effort estimate at that review.” Exact wording is optional. Require truthful attribution in any personal story and a hypothetical label when none is being offered.

**Hints:** H1 “What customer harm must be addressed first?” H2 ask which existing task would be displaced. H3 offer a bounded test-mode milestone and ask for the decision owner. **Stuck branch:** request decision/risk/next step only. **Finished branch:** founder accepts reduced scope; ask for a one-paragraph handoff with acceptance owner, evidence, exclusions and rollback point.

## Task-specific score evidence

Apply [the shared scale](Interview_Evaluation_Rubric.md) per dimension; Not assessed for unobserved dimensions. Level 4 requires repeated strong evidence across variations/multiple attempts, not one polished conversation.

| Dimension | Observable level-3 evidence | Critical blocker / remediation |
|---|---|---|
| CONTRACT | Defines intended purchase, permitted data, fixed price and bounded milestone | Treats deadline as launch approval or trusts client price; repeat START-T1/2 |
| REASONING | Explains lifecycle order, scope tradeoff and size assumptions | Adds components/features without user purpose; revisit START-T1/2 |
| CORRECTNESS | Separates payment/export facts and stable identities across uncertainty | Assumes timeout means no charge or issues a fresh key blindly; trace START-T2/3 |
| VALIDATION | Proposes response-loss/duplicate/cross-tenant cases and restart evidence | Claims mocks establish provider guarantees; rework START-T3 |
| RISK | Contains unsafe effects, preserves evidence and assigns reconciliation owner | Blanket replay/refund or unnecessary private logs; revisit START-T3 |
| COMMUNICATION | Gives honest known/unknown scope and negotiates a concrete next decision | Invented accomplishment, root cause certainty or unsupported ETA; repeat START-T4 |

**Common mistakes:** using one “success” flag for file/payment/access; undoing external effects by updating local status; hiding test-mode operation; making an unreviewed payment retry a throughput optimization; claiming a universal startup culture. **Strong indicators:** small useful scope, concrete failure trace, server-owned business parameters, bounded uncertainty and respectful disagreement with a named alternative.

## Debrief and provenance

After the session spend 10–15 minutes comparing the lifecycle and incident facts, reviewing assistance and selecting one recovery test plus one communication improvement. Record evidence and a new scenario to attempt, not an offer prediction.

[Source register](PHASE6_DESIGN_SOURCES.md): HTTP retry semantics, outbox limits, authorization and incident coordination. [Arithmetic checks](../validation/mocks-design/README.md) verify raw-size fixture arithmetic only. No payment, refund, provider retry contract, deployment or incident response was executed. Reviewed **2026-11-03**.
