# CAP theorem: state the failure, then the tradeoff

## Overview

CAP concerns an impossibility under a network partition in a distributed read/write system. It is not a general instruction to pick any two database features or a shortcut for choosing SQL versus NoSQL.

In the theorem's model, consistency is an atomic/linearizable view of operations, availability requires requests to nonfailed nodes eventually receive the required response, and a partition can prevent communication between parts of the system. Formal availability is stronger and differently scoped than a production uptime percentage. [Gilbert and Lynch, perspectives on CAP](https://groups.csail.mit.edu/tds/papers/Gilbert/Brewer2.pdf)

## Two replicas and one key

Initial value of `seat_available` is true at replicas A and B. A network partition prevents the replicas communicating. A client at A reserves the seat and receives success. Another client at B asks whether it can reserve that same seat.

If B independently promises success from its stale state, two clients can own the one seat, violating the required single authoritative behavior. If B waits for unreachable coordination or rejects service to preserve the rule, it cannot guarantee successful available operation on that side. Returning an error for every request is not the theorem's useful availability guarantee.

The choice concerns behavior **during the partition**, for the particular operation and invariant. A production system may reject reservation writes while still serving a cached event description. Different operations can deliberately offer different contracts.

## Do not mix consistency meanings

- ACID consistency means preserving declared/business invariants through correct transactions.
- Isolation concerns concurrent transactions and their histories.
- Linearizability includes real-time ordering of individual operations: an operation appears to take effect between invocation and completion.
- Serializability means committed transactions have an equivalent serial order, without necessarily requiring that order to match real time.
- Eventual consistency concerns convergence under suitable assumptions after updates cease/communication recovers; it does not specify a fixed staleness bound by itself.

An eventually consistent replica is not automatically safe for a current balance/permission decision. Conversely, eventual staleness may be acceptable for a noncritical count. State which data and operation need which guarantee.

## Practical interview reasoning

1. Name the invariant: one reservation per seat, no duplicate payout, or monotonic document version.
2. Identify the partition/failure: which nodes cannot communicate, and which can still serve clients?
3. Specify each operation's behavior: coordinate, reject, queue provisionally, or return an explicitly stale view.
4. Explain client-visible errors/timeouts and the reconciliation/compensation path.
5. Discuss normal-operation latency and cost separately; CAP alone does not predict performance.

A queued provisional reservation is not a confirmed seat. If conflicts are later resolved, identify who can lose and how that is communicated. Avoid hand-waving “merge later” for an invariant whose violation has already caused an irreversible effect.

## Interview practice

[INFERRED] Intermediate, backend/platform/SDE, architecture round.

1. **CAP-01:** Why is “choose CA because our network is reliable” an inadequate argument for a replicated service?
2. **CAP-02:** Can a service deny balance-changing writes during a partition but keep serving public descriptions?

<details><summary>Expected reasoning and followups</summary>

1. The failure model must acknowledge communication loss if it can occur; a preference does not remove a partition. Specify what happens when it occurs. A single-node system changes the scope and failure modes rather than proving a distributed impossibility false.
2. Yes, different operations/invariants can have different availability/staleness policies. Clearly label stale reads and preserve correctness for critical writes. Followup: what does the client do after a timeout when it cannot tell whether a write committed?

Mistakes: equating CAP consistency with the C in ACID, treating availability as merely responding with an error, classifying an entire product forever as CP/AP without configuration/operation scope, or using CAP as a latency benchmark. Related: [isolation](Isolation_Levels.md), [system-design consistency](../04-System-Design/Consistency_and_Replication.md).

</details>

## Checklist and last verified

- [ ] Name the formal consistency and availability meanings.
- [ ] Describe one concrete partition and operation contract.
- [ ] Separate theorem, product configuration and business invariant.

Last verified **2026-11-02**: original-author theoretical source reviewed; examples are original hypothetical teaching scenarios. No distributed failure experiment was performed. [Source metadata](PHASE2_SOURCES.md).
