# System design foundations

The original requested design catalog is delivered: Phase2 foundations, Phase3 AI-system designs and Phase7 advanced topics/classic cases. [AI-system designs](AI-System-Design/README.md) retain their own scoped validation. This folder teaches how to justify architecture from requirements rather than memorize product diagrams.

## Read in this order

1. [Roadmap](System_Design_Roadmap.md) and [interview framework](System_Design_Interview_Framework.md).
2. [Requirements](Functional_and_Non_Functional_Requirements.md), [API contracts](API_Design.md), [database choice](Database_Selection.md) and [capacity](Capacity_Estimation.md).
3. [Architecture diagrams](Architecture_Diagrams.md) and [monolith/service boundaries](Monolith_vs_Microservices.md).
4. [Caching](Caching.md), [load balancing](Load_Balancing.md) and [message queues](Message_Queues.md).
5. [Consistency/replication](Consistency_and_Replication.md), [fault tolerance](Fault_Tolerance.md), [observability](Observability.md) and [security/privacy](Security_and_Privacy.md).
6. Attempt [URL shortener](Classic-System-Design/URL_Shortener.md), then [notifications](Classic-System-Design/Notification_System.md), before opening their reference discussions.

## How to study

For each chapter, explain the mechanism, trace the worked example, try the question with the answer closed, and name a counterexample or failure case. The exercises and practice budgets are [INFERRED], company-neutral teaching choices. Every numeric workload is illustrative unless explicitly stated otherwise; no service benchmark was performed.

## Complete Phase 2 documents

- [API Design](API_Design.md)
- [Architecture Diagrams](Architecture_Diagrams.md)
- [Caching](Caching.md)
- [Capacity Estimation](Capacity_Estimation.md)
- [Consistency and Replication](Consistency_and_Replication.md)
- [Database Selection](Database_Selection.md)
- [Fault Tolerance](Fault_Tolerance.md)
- [Functional and Non Functional Requirements](Functional_and_Non_Functional_Requirements.md)
- [Load Balancing](Load_Balancing.md)
- [Message Queues](Message_Queues.md)
- [Monolith vs Microservices](Monolith_vs_Microservices.md)
- [Observability](Observability.md)
- [Security and Privacy](Security_and_Privacy.md)
- [System Design Interview Framework](System_Design_Interview_Framework.md)
- [System Design Roadmap](System_Design_Roadmap.md)

## Further sections

- [Classic-system case studies](Classic-System-Design/README.md).
- [AI-system designs](AI-System-Design/README.md) — 14 Phase 3 teaching designs, including hypothetical product architectures.
- [Primary source register](PHASE2_SOURCES.md).
- [Executable capacity assertions](../validation/design/check_capacity.py).

## Phase7 additions

- [Database Scaling](Database_Scaling.md).
- [Distributed Systems](Distributed_Systems.md).
- [Event Driven Architecture](Event_Driven_Architecture.md).
- [Rate Limiting](Rate_Limiting.md).
- [Microservices](Microservices.md).
- [Multi Tenant Architecture](Multi_Tenant_Architecture.md).
- [Cost Optimization](Cost_Optimization.md).
- [System Design Mock Interviews](System_Design_Mock_Interviews.md).
- [Phase7 source register](PHASE7_SOURCES.md).

These are reviewed teaching mechanisms and hypothetical designs, not newly executed services. Every classic case states the product-class scope, assumed workload, API/data ownership, normal flow, failure trace and tradeoff. No product name establishes actual private company architecture.

## Readiness gate

You can state a requirement, define an API/data invariant, justify one simple architecture, trace concurrency and partial failure, and explain recovery/security/cost tradeoffs. A memorized diagram or passing arithmetic script is not evidence of this performance; attempt a fresh prompt and record the result in [the tracker](../PROGRESS_TRACKER.md).

## Last verified

2026-11-03: requested topic/case coverage, current-authority cache-hit clarification and navigation reviewed. Diagram/rendering results belong to the final QA report; no production/load validation is claimed.
