# Phase 6 ai source scope

Primary pages below were directly opened and relevant mechanisms reviewed on **2026-11-03**. Questions, workloads, sample outcomes, durations, hint policy and score anchors are original **[INFERRED]**, company-neutral teaching inputs. Sources support concepts, not employer question ownership or candidate performance. Publication dates are not access dates.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P6-AI-01 | Built-in Types / Python | [Source](https://docs.python.org/3.12/library/stdtypes.html) | Version3.12; page date unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Types, strings, mappings and slicing; local exercise adds exact-type checks |
| P6-AI-02 | Authorization Cheat Sheet / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) | Rolling guidance; date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Current resource authorization; no deployed authorization implementation tested |
| P6-AI-03 | LLM Prompt Injection Prevention / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) | Rolling guidance; date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Untrusted model/retrieved text and layered controls; no immunity guarantee |
| P6-AI-04 | Server-sent events / WHATWG | [Source](https://html.spec.whatwg.org/multipage/server-sent-events.html) | Living Standard displayed2026-10-30 | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | EventSource/reconnection mechanisms; application replay protocol is original and no browser tested |
| P6-AI-05 | HTTP Semantics / IETF | [Source](https://www.rfc-editor.org/rfc/rfc9110.html) | June2022 | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | HTTP retry/conditional semantics; no provider-specific effect guarantee |
| P6-AI-06 | Transactional outbox / AWS | [Source](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) | Date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Atomic local intent/outbox and duplicate delivery caveats; no worker implementation |

## Validation boundary

[Local checks](../validation/mocks-ai/README.md) execute one marked helper and synthetic fixture arithmetic/event folding. They do not validate retrieval quality, semantic incident labels, browser/server streaming, authentication or a model/provider.

[Mock directory](README.md) · [Methodology](../RESEARCH_METHODOLOGY.md). Last reviewed: **2026-11-03**.
