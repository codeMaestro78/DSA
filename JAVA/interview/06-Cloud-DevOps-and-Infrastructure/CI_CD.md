# CI/CD: artifact promotion and evidence gates

## Learning goal and scope

Continuous integration checks proposed changes regularly. Continuous delivery keeps a release deployable; continuous deployment can automatically release accepted changes. The names do not establish the actual approval, rollback or environment policy. An interview answer should identify exactly what artifact was checked and how that same artifact reaches users.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Use a flow such as source revision → dependency resolution/build → focused tests/static checks → immutable artifact identity → environment-specific promotion → rollout observation. Separate untrusted contribution checks from privileged deployment. Store provenance linking source, dependencies, test evidence and artifact digest. A release gate must correspond to a concrete failure risk, not just a long checklist. Data/schema changes need compatibility across old/new application versions.

## Worked example

Fictional trace: CI tests image digest A, deployment rebuilds the same commit and produces digest B with a changed dependency. Passing tests for A do not prove B. Build once and promote a recorded artifact, while keeping environment configuration versioned and separately reviewed. If migration M removes a column before old replicas stop reading it, an application rollback cannot restore compatibility by itself.

## Tradeoffs, failure and access boundaries

A fast pipeline that shares unrestricted production credentials with pull-request code is unsafe. Conversely, a large slow test suite can delay feedback without covering critical recovery paths. Choose checks based on contract, record skipped/failed stages and define a stop/rollback owner. Deployment status is separate from user-level acceptance.

## Attempt first — OPS7-CI-CD

A pipeline is green but a retry creates two paid exports. Where should the missing evidence have been attached?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Add a test of the durable operation/effect identity boundary, including response loss and duplicate delivery, against the actual release artifact or an explicitly scoped test double. Keep provider contract verification separate. A lint/unit pass establishes its own properties, not untested external effect semantics.

**Followups:** Which check belongs before versus during rollout? What happens if the monitoring gate itself is unavailable?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [GitHub Actions secure use](https://docs.github.com/en/actions/reference/security/secure-use) — GitHub; rolling documentation; scope: Workflow permissions and untrusted inputs; no workflow executed.
- [Kubernetes Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) — Kubernetes; rolling documentation; scope: Declarative rollout and revision mechanics; no rollout executed.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
