# Incident management: containment, evidence and ownership

## Learning goal and scope

An incident is a user-impacting situation requiring coordinated response. The first useful output is often containment and an honest scope statement, not a confident root-cause story. Separate operational command, technical investigation and stakeholder communication so concurrent helpful changes do not make the situation worse.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Confirm the symptom/population and assign an incident owner. Keep a timeline of evidence, hypotheses, actions and observed effects. Choose reversible containment when possible, preserve relevant artifacts and identify an update point. Inspect recent changes without assuming temporal correlation proves cause. Recovery means the user journey and necessary data consistency are verified, not just that a process restarted.

## Worked example

Fictional trace: error rate rises after a release. One responder rolls back code while another changes a database setting without coordination. Latency improves but errors continue; attribution is now difficult. Establish one change log/owner, record both transitions and compare endpoints/versions. If a timeout could have left external effects unknown, include reconciliation before replaying jobs. Do not purge logs merely to reduce noise during investigation.

## Tradeoffs, failure and access boundaries

A rollback can be incompatible with migrated data, and pausing all traffic can create backlogs that overload recovery. Define stop/restart gates and a staged return. Public/customer updates should state confirmed impact, uncertainty, containment and next update without exposing private records or inventing an ETA. A post-incident review focuses on contributing conditions and owned improvements, not blame.

## Attempt first — OPS7-INCIDENT-MANAGEMENT

A payment timeout incident is declared resolved because the API is healthy. What remains to establish?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Classify uncertain effects using authoritative provider/local evidence, reconcile duplicates/missing records under an owner, verify the corrected retry path and monitor affected cohorts. Healthy new requests do not settle historical outcomes. State unavailable evidence and keep unresolved reconciliation visible rather than silently declaring all data repaired.

**Followups:** What makes a mitigation different from a root-cause fix? How do you test the runbook before the next event?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Managing Incidents](https://sre.google/sre-book/managing-incidents/) — Google SRE book;2016; scope: Response coordination and communication; no actual incident.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
