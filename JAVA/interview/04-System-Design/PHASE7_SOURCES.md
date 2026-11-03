# Phase7 arch source register

All sources below were directly opened and mechanisms reviewed on **2026-11-03**. New lessons/cases are original **[INFERRED]**, company-neutral teaching. Product-named classic files describe hypothetical product classes, not private/current employer architecture. Source reliability supports documented mechanisms; it does not establish live behavior, candidate events or performance. Publication dates are distinct from access dates.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P7-ARCH-01 | HTTP Semantics / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9110.html) | June2022 | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Conditional/retry/method semantics; custom operation identity is original design |
| P7-ARCH-02 | HTTP Caching / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9111.html) | June2022 | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Cache behavior; not a tenant-revocation guarantee |
| P7-ARCH-03 | Additional HTTP Status Codes / IETF | [Source](https://www.rfc-editor.org/rfc/rfc6585.html) | April2012 | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | 429 response semantics; no specific limiter implementation |
| P7-ARCH-04 | Transactional outbox / AWS | [Source](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) | Date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Local outbox/delivery caveats; no external exactly-once proof |
| P7-ARCH-05 | Standby servers / PostgreSQL | [Source](https://www.postgresql.org/docs/18/warm-standby.html) | Version 18; page date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Acknowledgment/replay/failover scope; no cluster tested |
| P7-ARCH-06 | Table partitioning / PostgreSQL | [Source](https://www.postgresql.org/docs/18/ddl-partitioning.html) | Version 18; page date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Partition/pruning concepts; not automatic cross-server sharding |
| P7-ARCH-07 | Constraints / PostgreSQL | [Source](https://www.postgresql.org/docs/18/ddl-constraints.html) | Version 18; page date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Uniqueness and reference enforcement; no native new schema run |
| P7-ARCH-08 | Handling overload / Google SRE | [Source](https://sre.google/sre-book/handling-overload/) | 2016 book | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Admission/resource reasoning; no load test |
| P7-ARCH-09 | Authorization / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) | Rolling guidance; date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Resource/action checks; no security audit of a deployment |
| P7-ARCH-10 | Cost Optimization / AWS Well-Architected | [Source](https://docs.aws.amazon.com/wellarchitected/latest/cost-optimization-pillar/welcome.html) | 2024-06-27 | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Cost method; not current provider pricing or a measured saving |
| P7-ARCH-11 | Media Source Extensions / W3C | [Source](https://www.w3.org/TR/media-source-2/) | Working Draft 2026-08-07 | 2026-11-03 | Primary standards working draft; provisional status | Media buffering/playback concepts; draft, not browser support or named-company architecture |
| P7-ARCH-12 | Near-real-time search / Elastic | [Source](https://www.elastic.co/docs/manage-data/data-store/near-real-time-search) | Rolling docs; date unstated | 2026-11-03 | Primary official documentation/standard; high for stated mechanism | Refresh/search visibility; no search benchmark |
| P7-ARCH-13 | Monotonic clock reference / Python Software Foundation | [Source](https://docs.python.org/3.12/library/time.html#time.monotonic) | Python 3.12; page date not established | 2026-11-03 | Primary language/runtime documentation; high for described clock interface | Elapsed reference semantics motivate explicit limiter restart/migration clock policy; not a distributed clock guarantee |

## Validation boundary

New chapters contain worked traces/reasoning, not newly executed database/service/provider implementations. Native Phase2 PostgreSQL checks and other prior validation retain their original scope. No claimed new SQL pass, cluster failover, provider action, cloud deployment or named-company architecture reconstruction follows from this register.

[Local index](README.md) · [Central source registry](../SOURCE_INDEX.md). Last reviewed: **2026-11-03**.
