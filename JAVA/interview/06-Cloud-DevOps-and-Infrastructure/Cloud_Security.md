# Cloud security: identity, data and control-plane paths

## Learning goal and scope

Cloud security is a set of enforceable boundaries around assets and actions, not a final checklist attached to a diagram. Identify the principal, target resource, operation, tenant and context for each request. Network placement can reduce exposure but does not replace application resource authorization.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Separate authentication from authorization, deployment/control-plane identity from runtime/data-plane identity, and application tenant checks from cloud-account policy. Use least privilege and short-lived identity where supported. Explicitly model untrusted input, metadata/credential paths, cross-account access and logs/artifacts. Encryption protects selected storage/transport boundaries; it does not prevent an overprivileged legitimate reader from extracting plaintext.

## Worked example

Fictional trace: an API correctly authenticates tenant A but trusts a client-supplied object key naming tenant B. The cloud role is allowed to read both prefixes, so storage authorizes the application identity and returns B data. The missing check is resource-level tenant authority in the application, not failed login. Another trace uses a broad deployment role at runtime; a request exploit now gains infrastructure change authority unnecessarily.

## Tradeoffs, failure and access boundaries

Policy evaluation can combine identity, resource and organization policies with important exceptions; avoid a universal claim that every allow or boundary behaves identically. Verify the actual principal/resource context and applicable explicit denies. Audit useful decisions without retaining unnecessary private content. Use staged tests for cross-tenant denial and credential exposure paths; no live security audit is claimed here.

## Attempt first — OPS7-CLOUD-SECURITY

An object is private in storage but a public API returns it for any guessed ID. Is private storage sufficient?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

No. The trusted application can be a confused deputy. Authenticate the caller, bind lookup to the permitted tenant/resource, check current authority before returning data/credentials and use nonguessability only as defense in depth. Test an authorized and unauthorized principal against the same object; private storage alone does not prove the API boundary.

**Followups:** How does a signed download URL change revocation? Which identity should a build job never inherit?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [AWS IAM policy evaluation](https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_evaluation-logic.html) — AWS; rolling documentation; scope: Policy evaluation boundaries; no policy simulator or account audit.
- [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/) — Kubernetes; rolling documentation; scope: Secret access/storage considerations; no cluster hardening proof.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
