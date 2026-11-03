# Linux internals: resources behind the process

## What to learn

For infrastructure interviews, understand virtual memory, scheduling, descriptors and I/O enough to connect them to symptoms. A process's resident memory, virtual address size, application heap and cgroup usage describe different quantities. Their names should not be treated as interchangeable failure thresholds.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Explain the request path through runnable work, blocking I/O, page/cache behavior and resource limits. Threads share process memory but introduce synchronization and scheduling costs. File/socket descriptors are per-process references to kernel-managed resources; exhaustion can break new connections even when CPU is low. Memory pressure can cause reclaim or limit enforcement; an application exception and an operating-system kill leave different evidence.

## Worked reasoning

Fixture: requests fail to open outbound sockets after a long soak. CPU and database latency are stable, while open descriptors rise monotonically. Inspect connection/file lifetime and compare accepted/completed work rather than immediately increasing the descriptor limit. Another process has high virtual memory but stable resident/cgroup use; virtual size alone does not prove an imminent physical-memory outage.

## Tradeoffs and failure boundaries

Increasing thread count can increase contention and memory without improving throughput. Resource measurements must name process/container/host scope and interval. Diagnostic attachments and dumps may contain credentials/private data; use the approved minimum evidence and retention policy. No process introspection or fault injection was executed.

## Attempt first — P7-INFRA-LINUX-INTERNALS

Why might a container be throttled while the host is mostly idle?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

A cgroup CPU quota can limit the workload independently of host spare capacity. Compare quota/period, usage/throttled time and runnable/backlog behavior at the correct cgroup scope. This supports a hypothesis, not automatic proof that raising quota fixes the user symptom; correlate a safe controlled change.

**Followups:** What does a thread dump establish versus a CPU profile? Why can a heap limit fail to bound total process memory?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Linux cgroup v2](https://docs.kernel.org/admin-guide/cgroup-v2.html) — Linux kernel; living documentation; Resource-control/accounting interface; no host test.
- [Linux TCP manual](https://man7.org/linux/man-pages/man7/tcp.7.html) — Linux man-pages; date not established; Ordered byte-stream/socket concepts; no network experiment.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
