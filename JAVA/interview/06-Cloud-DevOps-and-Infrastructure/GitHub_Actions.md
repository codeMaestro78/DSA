# GitHub Actions: event trust and job permissions

## Learning goal and scope

GitHub Actions runs workflows in response to events using runners, actions and credentials. The security question is who controls the workflow input and code being executed at each privileged step. A repository setting or green check is not proof that untrusted pull-request code cannot reach a secret.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Draw trust boundaries among contributor-controlled code/metadata, workflow definitions, third-party actions, runner environment and deployment identity. Grant job-scoped minimum token permissions; isolate build/test from privileged release. Pin third-party dependencies appropriately and review their updates. OIDC federation can provide short-lived cloud credentials, but the cloud trust conditions still need to bind the intended repository/workflow/ref/environment context.

## Worked example

Fictional trace: a privileged workflow checks out contributor-controlled code and executes its build hook. Even if the visible step only runs a build, the hook may access available credentials. Avoid privileged execution of unreviewed code; use a deliberate trust handoff between unprivileged validation and protected release. Another trace interpolates a pull-request title into shell code; treat the title as data and use a safe structured/environment boundary rather than executing it as syntax.

## Tradeoffs, failure and access boundaries

Automatic log redaction is not a guarantee: transformed secrets can escape masking. Hosted runners and self-hosted runners have different persistence and isolation concerns; untrusted jobs should not inherit a privileged persistent workspace. No workflow is installed or executed here; exact event/token defaults must be verified for the repository and current documentation.

## Attempt first — OPS7-GITHUB-ACTIONS

A fork pull request needs tests and production deployment credentials. How should these requirements be separated?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Run tests without production authority in an isolated untrusted context. A separately authorized release consumes a trusted reviewed artifact/provenance, with protected identity/ref/environment conditions and human or policy approval as required. Do not solve testing access by exposing deployment credentials to the fork.

**Followups:** What makes a reusable workflow a trust dependency? Why is a short-lived token still sensitive?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [GitHub Actions secure use](https://docs.github.com/en/actions/reference/security/secure-use) — GitHub; rolling documentation; scope: Workflow permissions and untrusted inputs; no workflow executed.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
