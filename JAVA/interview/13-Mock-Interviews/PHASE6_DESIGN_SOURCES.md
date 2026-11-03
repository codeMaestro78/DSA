# Phase 6 design source scope

Primary pages below were directly opened and relevant mechanisms reviewed on **2026-11-03**. Questions, workloads, sample outcomes, durations, hint policy and score anchors are original **[INFERRED]**, company-neutral teaching inputs. Sources support concepts, not employer question ownership or candidate performance. Publication dates are not access dates.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P6-DESIGN-01 | HTTP Semantics / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9110.html) | June2022 | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Conditional requests and method/retry semantics; custom provider contracts remain unknown |
| P6-DESIGN-02 | Constraints / PostgreSQL | [Source](https://www.postgresql.org/docs/18/ddl-constraints.html) | Version18; page date unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Unique/not-null/foreign-key mechanisms; no native SQL or concurrency run |
| P6-DESIGN-03 | Explicit locking / PostgreSQL | [Source](https://www.postgresql.org/docs/18/explicit-locking.html) | Version18; page date unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Transaction locking/deadlock considerations; no booking service executed |
| P6-DESIGN-04 | Transactional outbox / AWS | [Source](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) | Date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Local transaction/outbox and duplicate dispatch limits; not external exactly-once proof |
| P6-DESIGN-05 | Authorization Cheat Sheet / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) | Date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Least privilege/current resource checks; no security audit of a live service |
| P6-DESIGN-06 | Rules of ML / Martin Zinkevich, Google | [Source](https://developers.google.com/machine-learning/guides/rules-of-ml) | Page date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Objectives/baselines and pipeline evidence; no customer pilot results |
| P6-DESIGN-07 | Managing Incidents / Andrew Stribblehill, Google SRE | [Source](https://sre.google/sre-book/managing-incidents/) | 2016 book; page update unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Coordination, containment and communication; hypothetical incident exercises only |

## Validation boundary

[Local arithmetic checks](../validation/mocks-design/README.md) parse supplied candidate fixtures only. API/schema/failure/recovery guidance is reviewed reasoning; no database, payment/refund, connector, queue or customer action was executed.

[Mock directory](README.md) · [Methodology](../RESEARCH_METHODOLOGY.md). Last reviewed: **2026-11-03**.
