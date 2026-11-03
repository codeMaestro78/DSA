# System design roadmap

## What you are learning

System design connects a user's need to components, contracts and operating behavior. A diagram is useful only when you can explain how a request succeeds, what data is authoritative, and what happens when a component fails. For an early-career candidate, reason clearly about a modest service before adding global distribution.

This sequence and its practice budgets are [INFERRED] teaching recommendations. They are not a company's rubric or a guarantee of interview readiness.

## Sequence and mastery gates

| Step | Study | Produce | Gate |
|---|---|---|---|
| 1 | [Requirements](Functional_and_Non_Functional_Requirements.md) | Actors, three essential operations, exclusions and provisional targets | Explain what changes if freshness matters more than latency |
| 2 | [API](API_Design.md) and [data choice](Database_Selection.md) | Request/response contracts, entities, access patterns and invariants | Explain who owns each write and how conflicts are rejected |
| 3 | [Capacity](Capacity_Estimation.md) | Demand, storage, network and concurrency estimates with units | Identify which estimate actually changes a component choice |
| 4 | [Architecture](Architecture_Diagrams.md) | Simple baseline plus read/write traces | Follow a request without inventing an unexplained arrow |
| 5 | [Cache](Caching.md), [balancing](Load_Balancing.md), [queues](Message_Queues.md) | One justified optimization, rejected alternatives | Explain the new failure mode introduced by that optimization |
| 6 | [Consistency](Consistency_and_Replication.md), [recovery](Fault_Tolerance.md) | Stale-read policy, timeout cases and recovery plan | Distinguish unknown outcome from definite failure |
| 7 | [Observability](Observability.md) and [security](Security_and_Privacy.md) | User-facing indicators, access boundaries and incident checks | Explain how a violation is detected and contained |
| 8 | [Service boundaries](Monolith_vs_Microservices.md) | Evolution trigger and migration tradeoff | Defend the simplest design meeting the stated constraints |

## Two practice systems

Start with [URL shortening](Classic-System-Design/URL_Shortener.md): small records, a uniqueness invariant, read-heavy traffic, expiry and caching. Then study [notifications](Classic-System-Design/Notification_System.md): durable intent, asynchronous delivery, duplicate risk and external providers. These are hypothetical systems, not reconstructions of named companies.

Attempt each prompt first. Answers and followups in the case studies are inside expandable blocks. Try one 45-minute session, then one untimed correction session. A useful rough practice allocation is 5 minutes for scope, 8 for contracts and estimates, 12 for architecture/flow, 15 for failure/security/deep dive and 5 for tradeoffs. Adapt when the interviewer asks for different depth.

## What to postpone

Multi-region consensus, advanced partitioning, service meshes and complex stream processing should follow a demonstrated need. [AI systems](AI-System-Design/README.md) add model quality/evaluation concerns and are delivered. Phase7 now completes advanced general topics and [classic cases](Classic-System-Design/README.md); study them when the role and prerequisite gates justify the depth, rather than adding every mechanism to every design.

## Self-check

- [ ] I can restate the problem without listing technologies.
- [ ] I can enforce one invariant under concurrent requests.
- [ ] I can explain a cache miss, duplicate message and timeout.
- [ ] I can compare two architectures against the same requirements.
- [ ] I can name an assumption that would change my recommendation.

## References

- [Google SRE workbook — implementing SLOs](https://sre.google/workbook/implementing-slos/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
