# System-design arithmetic validation

Run from the Java workspace root:

```bash
python3 interview/validation/design/check_capacity.py
```

The dependency-free script uses Python `decimal.Decimal` to avoid accidental floating-point rounding in the asserted decimal examples. It requires Python 3.10 or later for union type syntax. It checks 22 numerical/scenario/input conditions: create/redirect rates, storage, concurrency, network throughput, illustrative cache misses, queue drain/growth, failure headroom, code namespace, notification worker scenarios, error budget and nested retries.

These are deterministic formula assertions, not throughput, correctness-of-architecture or production tests. The workload values are explicitly illustrative. No HTTP service, database, queue, provider integration, failure injection, load test or Mermaid renderer runs here.

Validated on 2026-11-02. [Capacity chapter](../../04-System-Design/Capacity_Estimation.md), [notification case study](../../04-System-Design/Classic-System-Design/Notification_System.md).
