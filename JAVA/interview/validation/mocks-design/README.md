# Phase 6 design mock arithmetic validation

```bash
python3 -B interview/validation/mocks-design/check_fixtures.py
```

**2026-11-03, Python 3.12.0: 18 checks passed.** The [runner](check_fixtures.py) extracts the marked JSON inputs directly from the three candidate packets. It checks booking-rate/claim/backlog/drain arithmetic; FDE proposal-coverage, selective-correctness and costly-error denominators; decimal raw export size and simultaneous payload size.

The booking fixture produces 6,000 queued notifications and a 400-second modeled drain. The routing fixture produces 150/200 proposals, 138/150 correct proposals and four Billing→Technical errors. The export fixture is 20 MB decimal per payload and 200 MB for ten raw payloads. These are stipulated arithmetic inputs, not observed throughput, representative model results or actual object-memory measurements.

No schema/transaction, concurrent schedule, queue, permission service, connector, customer pilot, payment, correction, deployment or incident was executed. API/state/recovery answers are reviewed design reasoning. A passing arithmetic check does not establish capacity or launch readiness. Reference solutions and this file contain spoilers.

[Source scope](../../13-Mock-Interviews/PHASE6_DESIGN_SOURCES.md) · [Mock directory](../../13-Mock-Interviews/README.md) · [Validation directory](../README.md).
