# Phase 5 review calculation fixture

[check_reasoning.py](check_reasoning.py) checks the explicit nearest-rank convention and correlated/disjoint stage examples in [QB-ADV-010](../../12-Interview-Question-Bank/Advanced_Questions.md). Run from the workspace root:

```bash
python3 interview/validation/questions-review/check_reasoning.py
```

2026-11-02, Python3.12: **17 checks passed**. These include identical stage marginals with different end-to-end p95, order invariance, decimal-fraction rounding, large integer values, endpoints and invalid input. Inputs are bounded synthetic numeric fixtures. The convention is sorted value at `ceil(fraction*n)-1`; the helper interprets the supplied fraction by its decimal spelling to avoid binary-float ceiling artifacts. Other percentile estimators can differ on finite samples.

The calculation does not validate a live latency distribution, sampling process, tracing system or production SLO. Other advanced/behavioral/project answers are reviewed reasoning rather than executed system implementations.
