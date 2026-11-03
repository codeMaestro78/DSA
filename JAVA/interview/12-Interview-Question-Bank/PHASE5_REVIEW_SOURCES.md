# Phase 5 behavioral, project and advanced sources

All sources below were directly opened on **2026-11-02**. They support general concepts or published communication guidance. The question prompts, numerical fixtures and answer structures are original **[INFERRED]**, company-neutral material; no employer question attribution follows from a citation. Most sources reuse existing central records.

| ID | Title / author or publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P5-REV-01 | Interview tips for all roles / Microsoft | [Source](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) | Not stated | 2026-11-02 | Official hiring guidance / high for published framework | Experience/STAR reflection, not these original questions or scorecards |
| P5-REV-02 | Monitoring Distributed Systems / Google SRE book | [Source](https://sre.google/sre-book/monitoring-distributed-systems/) | 2016 book | 2026-11-02 | First-party engineering book / high for method | Diagnostic signals and distributions, not a live latency benchmark |
| P5-REV-03 | Alerting on SLOs / Steven Thurgood et al., Google SRE | [Source](https://sre.google/workbook/alerting-on-slos/) | 2018 workbook | 2026-11-02 | First-party engineering book / high for method | User-impact/error-budget interpretation, not a particular deployment's SLO |
| P5-REV-04 | LLM Prompt Injection Prevention / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) | Not stated; rolling guidance | 2026-11-02 | Primary security guidance / high for stated threats/controls | Untrusted evaluation text and layered controls; not an immunity proof |
| P5-REV-05 | Log-Shipping Standby Servers / PostgreSQL Global Development Group | [Source](https://www.postgresql.org/docs/18/warm-standby.html) | Version18; page date not stated | 2026-11-02 | Official technical documentation / high for documented engine | Replication/failover scope; no local regional recovery test performed |
| P5-REV-06 | Authorization Cheat Sheet / OWASP | [Source](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) | Not stated; rolling guidance | 2026-11-02 | Primary security guidance / high for recommendations | Current resource authority and failure boundaries; no live security audit |
| P5-REV-07 | Common pitfalls and recommended practices / scikit-learn | [Source](https://scikit-learn.org/stable/common_pitfalls.html) | Not stated; rolling stable docs | 2026-11-02 | Official technical documentation / high for described pitfalls | Leakage/selection concerns; no scikit-learn runtime in this track |
| P5-REV-08 | Rules of Machine Learning / Martin Zinkevich, Google | [Source](https://developers.google.com/machine-learning/guides/rules-of-ml) | Page publication/update not established | 2026-11-02 | First-party engineering guide / high for recommendations | Feature/serving availability and objective alignment, not employer assessment |
| P5-REV-09 | Timeouts, retries and backoff with jitter / Marc Brooker, AWS | [Source](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/) | 2019-12-05; current URL redirects to AWS Builder Center | 2026-11-02 | First-party engineering article / high for failure reasoning | Timeout/uncertain effects, not a tested provider cancellation or billing contract |
| P5-REV-10 | Handling Overload / Google SRE book | [Source](https://sre.google/sre-book/handling-overload/) | 2016 book | 2026-11-02 | First-party engineering book / high for method | Admission/resource reasoning, not proof of a global quota implementation |

## Verification boundary

[The review fixture](../validation/questions-review/README.md) checks only the synthetic quantile example. Other behavioral/project/advanced answers are reviewed reasoning. No actual personal story, confidential data, employer prompt or internal hiring score is supplied.

## Last verified

2026-11-02: source scope and original answer guidance reviewed. Later code/framework or deployment decisions need their own validation.
