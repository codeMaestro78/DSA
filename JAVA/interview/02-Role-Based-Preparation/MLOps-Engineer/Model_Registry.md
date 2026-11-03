# Model registry: immutable versions and controlled references

## What to learn

A model registry catalogs artifact versions, provenance and lifecycle metadata. A version identifies an artifact contract; a mutable alias is a pointer used for controlled selection. Neither an alias named `production` nor a registered artifact proves every serving replica has loaded it or that quality is acceptable.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Record model/artifact digest, compatible runtime, preprocessing/schema, training/evaluation lineage, owner and approval evidence. Treat promotion as an authorized state change tied to an exact version and acceptance record. Use aliases/references according to the selected registry's supported API rather than assuming deprecated stage semantics. Deployment should report resolved version/digest; rollback targets must remain compatible and permitted.

## Worked reasoning

Fixture: alias `champion` moves from M7 to M8. Replica R1 resolves M8 but R2 keeps M7 in memory. Registry metadata is correct while traffic sees mixed behavior. Track desired versus loaded version and define rollout/stop conditions. If a rollback changes the alias but caches/servers never reload, no actual recovery has occurred. A mutable alias in an experiment record also fails to identify the artifact used later.

## Tradeoffs and failure boundaries

Registry permissions protect promotion and artifact access; training jobs should not automatically gain production-release authority. Retention/deletion can invalidate a rollback target, especially when artifacts or training data have policy constraints. Copying weights without environment/preprocessing may produce a loadable but wrong model.

## Attempt first — P7-MLOPS-MODEL-REGISTRY

A deployment records only the alias `serving`. What is needed to reproduce one prediction a month later?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Record the alias resolution at that time to an immutable model version/digest, preprocessing/input contract, serving runtime/config and permitted request/features or a shareable reproduction. Preserve access/retention boundaries. Do not retain private inputs indefinitely merely to promise exact replay; state what cannot be reconstructed.

**Followups:** How does an artifact digest differ from a registry alias? Who can approve emergency rollback?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [MLflow Model Registry](https://mlflow.org/docs/latest/ml/model-registry/) — MLflow; rolling docs; Version/alias/lineage concepts; no registry created.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
