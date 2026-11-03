# Cloud infrastructure: compute, identity and recovery together

## What to learn

An infrastructure role connects compute placement, network reachability, identity, durable data and operations. Evaluate a complete critical journey rather than treating a managed resource inventory as a finished architecture. Every extra component adds another permission, failure and cost surface.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

For a small service define public/private entry points, runtime identity, data dependencies, egress, deployment artifacts and recovery ownership. Separate availability placement from data durability. Restrict administrative access and use audited supported access paths. A private subnet without a functioning dependency route can be secure from some exposure yet unable to perform the intended job; reachability and authority are distinct checks.

## Worked reasoning

Fixture: API replicas are spread over two availability zones, but both use one zonal dependency with no tested restore path. A zone failure preserves frontend processes but breaks all transactions. Identify the shared dependency, define RPO/RTO assumptions and compare an appropriate redundancy/restore option. Additional zones do not make stale replicas synchronously current or eliminate all correlated failures.

## Tradeoffs and failure boundaries

Cloud defaults, regions, service limits and managed responsibilities change; verify the actual selected service/tier instead of carrying a memorized number. Include backups, restore drills, observability and budget controls. No account-specific design, pricing estimate or deployment is performed in this lesson.

## Attempt first — P7-INFRA-CLOUD-INFRASTRUCTURE

A system diagram labels every box multi-AZ. What questions determine whether the user journey survives a zone failure?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Ask which datasets and control-plane dependencies cross zones, whether acknowledged state is recoverable, how routing/failover works, what credentials/network dependencies remain, and which representative failure/restore check was run. A label does not establish an actual failover or recovery bound.

**Followups:** How are recovery time and data loss different? What changes when one tenant dominates resource use?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/framework/welcome.html) — AWS;2024-11-06; Quality-attribute review; no quota/price freeze.
- [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Google SRE Workbook;2018; User indicators/error-budget alert method; no measured SLO.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
