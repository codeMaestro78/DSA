# Phase 6 core source scope

Primary pages below were directly opened and relevant mechanisms reviewed on **2026-11-03**. Questions, workloads, sample outcomes, durations, hint policy and score anchors are original **[INFERRED]**, company-neutral teaching inputs. Sources support concepts, not employer question ownership or candidate performance. Publication dates are not access dates.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P6-CORE-01 | ArrayDeque / Oracle | [Source](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayDeque.html) | Java SE17; page date unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Deque operation costs and single-threaded constraints; no global service correctness |
| P6-CORE-02 | Cross-validation / scikit-learn | [Source](https://scikit-learn.org/stable/modules/cross_validation.html) | Rolling stable docs; publication not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Group/time split mechanisms; no model evaluation was run |
| P6-CORE-03 | Mean absolute error / scikit-learn | [Source](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.mean_absolute_error.html) | Rolling stable docs; publication not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Base MAE definition; equal-customer weighting is original exercise policy |
| P6-CORE-04 | logsumexp / SciPy | [Source](https://docs.scipy.org/doc/scipy/reference/generated/scipy.special.logsumexp.html) | Rolling docs; page date not established | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Stable log-sum mechanism; SciPy not executed |
| P6-CORE-05 | CrossEntropyLoss / PyTorch | [Source](https://docs.pytorch.org/docs/2.15/generated/torch.nn.CrossEntropyLoss.html) | Version2.15; page date unstated | 2026-11-03 | Primary specification/official guidance; high for documented mechanism | Class target loss/reduction semantics; PyTorch not executed; local NumPy helper has its own contract |

## Validation boundary

[Local reference checks](../validation/mocks-core/README.md) execute three marked published implementations only. Numerical stability claims are bounded by their input contracts; no training, GPU, framework, live service or employer assessment was run.

[Mock directory](README.md) · [Methodology](../RESEARCH_METHODOLOGY.md). Last reviewed: **2026-11-03**.
