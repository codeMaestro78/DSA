# Infrastructure as code: ownership and reversible evolution

## Learning goal and scope

Infrastructure as code makes intended infrastructure reviewable and repeatable. Its deeper value is explicit ownership, dependency and change history, rather than replacing every console action with a large template. A declaration cannot guarantee actual service health, data recovery or a correct policy.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Separate desired configuration, current provider state, recorded tool state and observed runtime behavior. Choose module boundaries by independently owned lifecycle, not by making one giant reusable template. Record versioned inputs, identity/secret references and apply authority. Handle adoption/import, drift and emergency changes intentionally. A preview should expose replacement/destruction and policy changes before execution.

## Worked example

Fictional trace: team A changes a shared network module to tighten egress. Team B service loses a required dependency during its next apply. The module was syntactically valid but its consumer contract changed. Version interfaces, identify affected owners and test representative network/identity paths before rollout. An emergency exception must later be reconciled with code; otherwise the next apply may silently undo the repair.

## Tradeoffs, failure and access boundaries

An abstraction can reduce repeated work yet hide a destructive resource replacement. Prefer a small number of understandable interfaces with explicit outputs and migration notes. State and plans can contain secrets; treat them as sensitive artifacts. Rollback can require data restoration or a forward-compatible repair, not simply reverting a commit.

## Attempt first — OPS7-INFRASTRUCTURE-AS-CODE

Two teams both believe they own the same database resource in different state files. What must be resolved before either applies?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Establish one authoritative owner and supported adoption/migration of the resource mapping, preserve backups/identity and inspect both plans. Two independent controllers can fight over settings or destroy a shared object. Locking one state file does not coordinate a second unrelated state file.

**Followups:** What constitutes drift evidence? Which dependencies deserve separate lifecycle boundaries?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Terraform sensitive data](https://developer.hashicorp.com/terraform/language/manage-sensitive-data) — HashiCorp; rolling documentation; scope: Sensitive values/state/plan boundaries; no Terraform plan executed.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
