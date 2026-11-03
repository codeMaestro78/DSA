# Secrets management: reduce exposure and rotation ambiguity

## Learning goal and scope

A secret is an authority-bearing value such as a credential or signing key. Managing it means controlling creation, distribution, access, use, rotation, revocation and recovery. Moving a string from source code to a special object is only one step; the runtime and operators may still expose it.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Prefer workload identity/short-lived credentials where supported, or retrieve a narrowly scoped secret through an authenticated channel. Restrict readers, namespace/resource scope and logs. Base 64 encoding is representation, not encryption. For Kubernetes, verify encryption-at-rest configuration and RBAC/Pod creation authority rather than assuming every Secret is protected by its type. A process with legitimate use may still read the value in memory or mounted files.

## Worked example

Fictional trace: database password v1 is rotated to v2, but a long-lived worker connection pool still uses v1. New connections fail while old sessions continue, creating intermittent errors. Plan credential rollout, overlap/revocation policy, pool reload and health checks together. If a secret appears in a build artifact or repository history, deleting the visible line does not establish all copies are gone; classify exposure and rotate under the actual incident process.

## Tradeoffs, failure and access boundaries

Frequent rotation without tested consumer reload can cause outages. Long overlap can extend exposure. Log only nonsecret credential version/identity metadata when needed. Backup and recovery must preserve access to encrypted data without making decryption keys broadly accessible. No actual secret is created, read or rotated here.

## Attempt first — OPS7-SECRETS-MANAGEMENT

A developer says a Kubernetes Secret is safe because its YAML value is base 64. Explain the missing controls.

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Base 64 can be decoded and supplies no confidentiality. Require narrowly scoped API/workload access, appropriate storage encryption and key management, protected transport, safe mounting/use, log discipline and a tested rotation/revocation process. Do not claim these are configured merely because the Secret exists.

**Followups:** Who can create a Pod that mounts the secret? What happens to existing sessions after revocation?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/) — Kubernetes; rolling documentation; scope: Secret access/storage considerations; no cluster hardening proof.
- [Terraform sensitive data](https://developer.hashicorp.com/terraform/language/manage-sensitive-data) — HashiCorp; rolling documentation; scope: Sensitive values/state/plan boundaries; no Terraform plan executed.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
