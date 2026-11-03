# Replication: name the acknowledgment and read boundary

Original **[INFERRED]**, company-neutral teaching for early-career backend/data interviews. Product documentation supports stated mechanisms; examples are fictional reasoning fixtures, not a live database benchmark.

## Concept and contract

Replication copies data/state to additional nodes for availability, recovery or read work. Acknowledgment policy decides what has been confirmed when the client sees success: local persistence, remote receipt or replay/application can differ. A synchronous label without its wait condition and failure policy is incomplete.

Asynchronous replication can lose recently acknowledged primary writes on promotion if they never reached the promoted node. A replica read can be stale. The acceptable recovery point objective (RPO) and recovery time objective (RTO) must describe a failure model and be tested; they are not properties proved by drawing two database boxes.

## Worked trace

Primary commits order17, returns success, then fails before standby receives the log. Standby promotion may omit order17. If an external charge already succeeded, the database and provider facts diverge; replaying from the promoted DB alone can duplicate the charge. Fence the old primary, reconcile stable operation identities and authoritative external evidence, and explicitly disclose the deployment's data-loss contract.

## Design choices and failure checks

For read-your-writes, return the committed representation, read the writer, or wait for a replica applied-position/token with a timeout/fallback policy. A fixed stickiness timer is a heuristic unless lag is actually bounded. Replicas can spread eligible read queries but do not remove one writer's hot-row contention. Backups plus point-in-time recovery address historical loss differently from a replica that faithfully replicates a bad delete. Monitor replication positions/lag, retained logs, failover ownership and restore/reconciliation evidence.

## Attempt first — P7-DB-REPLICATION

Does a standby prove a backup is unnecessary?

<details><summary>Expected reasoning and followup — attempt first</summary>

No. A replica can reproduce corruption or deletion and shares some failure domains. Retain an appropriate independent recovery strategy and demonstrate restoration. Followup: how do you know which acknowledged writes a promoted node contains?

**Common mistake:** answering with a product label while leaving the operation's invariant or evidence unspecified. State which component owns the fact and what a timeout/empty result can actually establish.

</details>

## References and validation boundary

- [PostgreSQL standby contracts](https://www.postgresql.org/docs/18/warm-standby.html).

No native product, transaction schedule, driver/ORM, search index or GraphQL server was executed for this chapter. Prior [PostgreSQL fixture evidence](../validation/database/README.md) covers its own examples only; it does not validate these new product-specific scenarios. [Source scope](PHASE7_SOURCES.md) · [Backend index](README.md). Last reviewed: **2026-11-03**.
