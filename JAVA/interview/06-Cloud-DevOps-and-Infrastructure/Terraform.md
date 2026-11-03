# Terraform: state, plans and controlled change

## Learning goal and scope

Infrastructure as code declares resources and their relationships; Terraform compares configuration, recorded state and provider observations to plan changes. The plan is a proposed change, not an execution result or a complete security proof. State maps logical resource identities to real objects and may contain sensitive values.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Keep configuration, provider/module versions and dependency locks reviewable. Use access-controlled state storage and supported locking to coordinate writers. Review create/update/destroy/replace actions and their data consequences. Imports/adoption and drift reconciliation need explicit ownership; deleting state metadata is not the same as safely deleting or retaining the real resource. A sensitive flag usually controls display rather than making the underlying value secret-free.

## Worked example

Fictional trace: a resource rename is interpreted as destroy/create because the identity mapping was not preserved. If it owns data, a visually small code diff can be a destructive plan. Review the plan, use the supported resource-move mechanism where appropriate and verify backup/restore and downstream references before applying. No plan or apply is run in this chapter. Another trace leaks a password through an unprotected saved plan even though terminal output was redacted.

## Tradeoffs, failure and access boundaries

Out-of-band console changes can be legitimate emergency work, but they create drift that needs intentional reconciliation. Do not blindly apply an older configuration during an incident. Separate review identity from apply authority, protect credentials and state, and use a recoverable change sequence. Pinning providers reduces surprise but requires a deliberate update/security process.

## Attempt first — OPS7-TERRAFORM

A colleague says a sensitive variable can safely be committed because Terraform masks it. What must be checked?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Masking does not authorize storing a real secret in Git or unprotected state/plan files. Remove secret material from source, rotate if exposure occurred under the actual incident process, restrict backend/plan access and use supported secret retrieval patterns. Confirm what the chosen provider/resource stores; do not promise that every ephemeral or write-only feature exists in every version.

**Followups:** What is the difference between drift and an intended code change? Why can rollback require forward repair?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Terraform sensitive data](https://developer.hashicorp.com/terraform/language/manage-sensitive-data) — HashiCorp; rolling documentation; scope: Sensitive values/state/plan boundaries; no Terraform plan executed.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
