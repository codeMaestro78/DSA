# Linux for service debugging

## Learning goal and scope

A service process is scheduled by the operating system and competes for CPU, memory, I/O and descriptors. Start with the user symptom and the process/resource boundary rather than treating every slowdown as insufficient RAM. A process is an address-space/resource container; threads share much of that process state but have independently scheduled execution. Blocking, runnable and CPU-consuming work have different remedies.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Build a hypothesis ledger: request latency, runnable work, CPU time, memory pressure, I/O wait, descriptor/socket state and recent change. A cgroup is a resource-control/accounting boundary, not another virtual machine. A CPU quota can throttle a process even when other host CPUs are idle. Memory limits and application heap limits are different; non-heap/native memory and page cache accounting can matter. Examine the matching cgroup/process scope before comparing numbers.

## Worked example

Fictional trace: an API has stable request volume but p95 doubles after its CPU quota is halved. Host-wide CPU appears 30% busy, while the application reports increased throttled time and queue depth. This evidence supports quota pressure as a candidate cause; it does not rule out a simultaneous database regression. Compare per-request CPU and throttling across the change, then test a bounded rollback of the resource policy in a safe environment. No host observation was collected for this guide.

## Tradeoffs, failure and access boundaries

Raising limits can postpone failure while increasing shared-host interference. Restarting may remove the evidence and can replay work; preserve relevant process/version/resource records first. Do not log private payloads or inspect unrelated processes without authority. A low load average or free memory alone is not a complete diagnosis.

## Attempt first — OPS7-LINUX

A worker times out while host CPU is low. What three observations distinguish CPU throttling, blocked I/O and an exhausted connection pool?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Inspect cgroup CPU usage/throttling, blocked-wait/latency evidence for the I/O path, and pool active/waiting counts with request traces. Use the same time window. Propose one discriminating check for each hypothesis; neither low host utilization nor a timeout establishes the cause. Record which observations are unavailable.

**Followups:** How would an out-of-memory kill differ from an application exception? Which process state survives a restart?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Linux cgroup v2](https://docs.kernel.org/admin-guide/cgroup-v2.html) — Kernel project; living docs; original interface date2015; scope: Resource accounting and controller mechanisms; not a tested host.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
