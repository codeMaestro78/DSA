# Phase 2 system-design source register

All sources below were directly opened on **2026-11-02**. They support technical principles, not claims about a company's interviews. Publication dates are recorded only where identifiable; access does not imply recent authorship. Some sources were already indexed in Phase 1: retain their existing central IDs when consolidating rather than counting one URL twice.

| ID | Title | Author / organization | URL | Publication/update | Accessed | Type | Reliability and supported scope |
|---|---|---|---|---|---|---|---|
| SD2-01 | HTTP Semantics | Roy Fielding, Mark Nottingham, Julian Reschke / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9110.html) | June 2022 | 2026-11-02 | Internet standard | High for method/status semantics; route and workload examples are original |
| SD2-02 | HTTP Caching | Roy Fielding, Mark Nottingham, Julian Reschke / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9111.html) | June 2022 | 2026-11-02 | Internet standard | High for HTTP cache semantics; does not define application-cache freshness |
| SD2-03 | Implementing SLOs | Steven Thurgood and David Ferguson / Google SRE workbook | [Source](https://sre.google/workbook/implementing-slos/) | 2018 workbook | 2026-11-02 | First-party engineering book | High for SLI/SLO method; example targets here are hypothetical |
| SD2-04 | Timeouts, retries, and backoff with jitter | Marc Brooker / AWS Builders Library | [Source](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/) | 2019-12-05 | 2026-11-02 | First-party engineering article | High for described retry/failure reasoning; not evidence of a company interview question |
| SD2-05 | Transaction Isolation | PostgreSQL Global Development Group | [Source](https://www.postgresql.org/docs/current/transaction-iso.html) | Not stated; rolling current documentation | 2026-11-02 | Official technical documentation | High for documented PostgreSQL isolation; not universal SQL behavior |
| SD2-06 | Indexes | PostgreSQL Global Development Group | [Source](https://www.postgresql.org/docs/current/indexes.html) | Not stated; rolling current documentation | 2026-11-02 | Official technical documentation | High for documented index role/overhead; no service-capacity benchmark |
| SD2-07 | Amazon SQS at-least-once delivery | AWS / Amazon SQS | [Source](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/standard-queues-at-least-once-delivery.html) | Not stated; rolling documentation | 2026-11-02 | Official service documentation | High for standard-queue duplicate delivery; no universal broker semantics |
| SD2-08 | Authorization Cheat Sheet | OWASP Cheat Sheet Series contributors | [Source](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) | Not stated; rolling documentation | 2026-11-02 | Primary security guidance | High for authorization principles; not a full security/compliance audit |
| SD2-09 | Handling Overload | Google SRE book contributors | [Source](https://sre.google/sre-book/handling-overload/) | 2016 book | 2026-11-02 | First-party engineering book | High for overload reasoning; illustrative capacity values are not measurements |

## Teaching and verification boundary

All systems, workload values, code-allocation choices, API route layouts and practice prompts in this subtree are original illustrative exercises or [INFERRED] preparation guidance. They are not production architecture disclosures, reported employer questions or benchmark results. The explanations cite primary references locally. No cloud prices, service quotas or throughput guarantees are asserted.

[Capacity checks](../validation/design/check_capacity.py) validate the arithmetic, not operational feasibility. Mermaid graphs were checked structurally as text and inspected against the prose; no rendering or deployed service was validated by this research track.

## Last verified

2026-11-02: nine primary sources opened and scoped.
