# Deployment strategies: compatibility and evidence during change

## Learning goal and scope

A deployment strategy controls how a changed artifact reaches users and how failure is contained. Rolling, canary and blue/green approaches trade spare capacity, routing control, observation time and rollback complexity. None makes incompatible data changes or uncertain external effects disappear.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

A rolling update replaces instances gradually; old/new versions may coexist. A canary compares a limited exposed cohort against defined acceptance/stop criteria. Blue/green keeps separate environments and switches traffic, but data, sessions and shared dependencies may remain common. Define artifact/config identities, schema compatibility, health versus user-level checks and who owns stop/rollback. Do not use a tiny quiet canary as proof of peak-load behavior.

## Worked example

Fictional trace: version 2 writes a new enum that version 1 cannot read. A traffic rollback to version 1 fails on records created during the canary. An expand/compatible-write/read strategy and staged migration can preserve rollback options; a backup is not an instant rollback of live concurrent writes. For Kubernetes, rollout status says something about the controller/Pod conditions, not all business acceptance checks.

## Tradeoffs, failure and access boundaries

Canary cohorts can differ by geography, tenant size or request type, biasing comparison. Monitor volume and representative failure slices as well as average metrics. Blue/green can double some resource costs temporarily; compare that with outage/recovery needs. Keep secrets and data access valid for both environments, then remove obsolete authority deliberately.

## Attempt first — OPS7-DEPLOYMENT-STRATEGIES

A new service passes readiness but checkout fails only for existing users. Should rollout continue?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Pause under the user-level stop criterion and inspect session/schema/backward compatibility with a representative existing-user fixture. Readiness does not override the observed critical journey failure. Roll back only after checking data/config compatibility and reconcile any in-flight/committed operations.

**Followups:** Which rollback promise needs a data migration test? How does low canary traffic change confidence?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Kubernetes Deployments](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) — Kubernetes; rolling documentation; scope: Declarative rollout and revision mechanics; no rollout executed.
- [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Google SRE Workbook;2018; scope: Error budgets and alert intent; not a supplied production SLO.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
