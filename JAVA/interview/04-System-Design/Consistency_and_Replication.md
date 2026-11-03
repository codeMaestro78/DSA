# Consistency and replication

## Three concepts that are easy to mix up

Replication maintains copies of state. Consistency describes which observations clients can make as operations overlap and propagate. Transaction isolation constrains interactions among transactions. A replicated database can offer several consistency modes; a single database can offer several isolation levels. Do not infer either from “SQL” or “NoSQL.”

For isolation in one concrete engine, PostgreSQL documents snapshot and anomaly behavior per level. Serializable does not mean “no transaction can fail”; conflicting execution can require a retry. See the database curriculum for engine-specific examples.

## Read-after-write example

A creator updates a short link on the primary. The update commits. Their next request is routed to a lagging asynchronous replica and sees the old destination. The write was durable under its contract, but this read did not provide read-your-writes freshness.

Possible responses include routing that session to the primary, tracking a replication position before allowing replica reads, or accepting and explaining a bounded/unspecified stale-read policy. Each requires a concrete mechanism. Merely saying “eventually consistent” does not define a time bound.

## Synchronous versus asynchronous replication

With asynchronous propagation, acknowledgment can precede a replica receiving the update. Failover may lose acknowledged updates if the selected replica lacks them, depending on the database's durability/failover policy. Synchronous acknowledgment can wait for configured remote progress, trading latency/availability for stronger durability at that boundary. It is not a blanket guarantee against every correlated failure or operator mistake.

Define recovery point objective (RPO: tolerable data loss) and recovery time objective (RTO: tolerable restoration duration). Values are requirements, not facts inferred from a replication diagram. Replicas can copy accidental deletions; backups and restore procedures address a different risk.

## Invariants need a coordination boundary

Two API instances both check that alias `news` does not exist, then insert it. Local checks race. A uniqueness constraint or conditional insert at the authoritative store enforces the invariant even when the row did not previously exist. Cross-shard invariants require a deliberate coordination or redesign choice. A cache lock alone can be lost or expire and is not automatically a substitute.

CAP concerns what guarantees can be maintained during a network partition under its formal model. It is not a database shopping rule saying any system freely picks two independent features. For interviews, describe the exact partition, affected operation, consistency requirement and whether requests are rejected or can return stale/conflicting data.

## Practice question

**SD-CONS-01 — Intermediate; backend/platform; design; 0–2 years.** Why might adding a read replica break an account-management page without changing its SQL?

<details><summary>Expected reasoning and common mistake</summary>

A read immediately after a write can be routed to a replica that has not applied it. The page may display an old email or permission. Identify which reads need freshness and route/wait accordingly. Followup: can a fixed sleep guarantee catch-up? No, lag can exceed the sleep; use a real guarantee or admit a probabilistic workaround. Mistake: confusing replication with synchronous shared memory.

</details>

## Checklist

- [ ] Each operation's freshness requirement is named.
- [ ] Acknowledgment and failover durability are scoped.
- [ ] Uniqueness/conflict enforcement is concrete under concurrency.
- [ ] Restore and replication are treated as different mechanisms.

## References

- [PostgreSQL18 — standby/replication contracts](https://www.postgresql.org/docs/18/warm-standby.html).
- [PostgreSQL — transaction isolation](https://www.postgresql.org/docs/current/transaction-iso.html)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
