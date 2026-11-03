# Kubernetes: desired state and service health

## Learning goal and scope

Kubernetes reconciles declared workload state, schedules Pods and exposes network endpoints. A Pod is a scheduling/runtime grouping, not a durable user session. A Deployment manages replaceable replicas; business correctness still belongs to the application and data layer. Learn these responsibilities before memorizing manifests.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Distinguish startup, readiness and liveness. Startup allows initialization before other probe checks begin. Readiness normally controls eligibility for readiness-aware routing through matching Services; it does not repair the process. It is not a firewall or authorization boundary: direct Pod access, existing connections and configurations that publish not-ready endpoints need their own behavior/access policy. Repeated liveness failures can restart a container. A shared dependency outage is often unsuitable as a liveness trigger because restarting every replica cannot repair that dependency. Requests/limits affect scheduling and resource behavior; a replica count is not a throughput guarantee.

## Worked example

Fictional trace: database latency rises. Every API Pod has a liveness probe that synchronously queries the database; probes fail and all replicas restart, producing reconnect storms. A better plan uses an appropriate local liveness check, a deliberate readiness/degraded-service policy, bounded connection work and database recovery. Readiness removal also reduces serving capacity, so test that decision rather than removing every Pod mechanically.

## Tradeoffs, failure and access boundaries

A rolling update can temporarily run old/new versions together; schemas and APIs must remain compatible. A healthy Pod is not proof of a correct user transaction. Secrets, network policy and service accounts require configured least privilege; default existence of a Secret object is not proof of encryption or safe access.

## Attempt first — OPS7-KUBERNETES

An application takes 90 seconds to warm up and a liveness probe kills it after 30 seconds. What is the intended probe separation?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Use a startup policy whose demonstrated budget accommodates normal warm-up, then run a liveness check for irrecoverable local failure and readiness for serving eligibility. Do not merely increase every timeout without measuring initialization. A rollout needs an upper bound/alert for startup that never completes.

**Followups:** When can readiness removal amplify an outage? Why does a Deployment rollback not undo a database migration?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Kubernetes probes](https://kubernetes.io/docs/concepts/workloads/pods/probes/) — Kubernetes; rolling documentation; scope: Startup/readiness/liveness mechanisms; no cluster tested.
- [Kubernetes Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) — Kubernetes; rolling documentation; scope: Declarative rollout and revision mechanics; no rollout executed.
- [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/) — Kubernetes; rolling documentation; scope: Secret access/storage considerations; no cluster hardening proof.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
