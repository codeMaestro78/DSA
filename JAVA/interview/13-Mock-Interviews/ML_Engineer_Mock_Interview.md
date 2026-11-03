# ML engineer mock — evaluating customer forecasts

This is original **[INFERRED]**, company-neutral practice for an early-career candidate. It is not a company's interview, policy, hiring bar or timing. All datasets, workloads and product requirements here are fictional teaching fixtures. This candidate packet contains the tasks only; keep the [interviewer companion](ML_Engineer_Mock_Interview_Interviewer.md) closed until the attempt ends.

## Metadata

| Field | Value |
|---|---|
| Mock ID | MOCK-ML-01 |
| Target role | ML Engineer / applied ML engineer |
| Level | 0–2 years; optional advanced followups |
| Difficulty | Intermediate |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Tools | Python with NumPy; local tests and a calculator; no training framework required |
| Tasks | ML-T1, ML-T2, ML-T3, ML-T4 |
| Validation | Exact metric block extracted and tested; splitting/deployment decisions are reviewed reasoning |

## Before starting

Preflight is outside the 75-minute session: verify the chosen runtime works, prepare a scratch file and timer, and choose a partner or solo mode. For a strict independent attempt, do not use generative assistance or read the companion; this is an exercise rule, not an employer policy. Documentation lookup is allowed if recorded. A partner gives one task at a time and records questions/hints. Solo candidates write assumptions, stop at each checkpoint and open the companion only after the timed session. Never invent real project outcomes.

## Timeline

| Stage | Minutes | Task |
|---|---|---|
| 1 | 15 | ML-T1 — prediction and evaluation contract |
| 2 | 25 | ML-T2 — metric coding and tests |
| 3 | 20 | ML-T3 — release diagnosis |
| 4 | 15 | ML-T4 — communication and closing |

## Candidate tasks

### ML-T1 — Define the prediction and evaluation unit

A support product predicts the number of tickets a customer will open in the next seven days, once per customer each Monday. There are 18 months of records from 40 customers; each customer has many weekly rows. Deployment initially serves existing customers next month, while a future expansion may serve unseen customers. Some outcomes are not complete until the seven-day window ends. Features include prior-week ticket count, contract tier and an account-health field recomputed today.

State the decision timestamp, label maturity rule and feature availability rule. Specify a training/development/final evaluation design for the initial deployment and a separate assessment for unseen customers. What can neither design establish from only 40 customers?

### ML-T2 — Implement a customer-weighted error report

Implement `customer_mae(y_true, y_pred, customers)` returning `(per_customer, macro_mae)`. For each customer, compute the mean absolute prediction error over its rows; then average those customer means with **equal customer weight**. `per_customer` must have keys in sorted lexical order. Inputs have equal nonzero length and at most 1,000,000 rows. Targets and predictions are real numeric one-dimensional sequences, finite and in [−1e6, 1e6]. `customers` is a sequence of nonempty exact built-in Python strings. Numeric booleans and nonnumeric dtypes are not accepted. Reject violations with `ValueError`; do not mutate input. No need to accept strings as numbers, complex values or nested numeric arrays. Empty groups are not synthesized.

Use the fixture `y_true=[0,0,0,0,0]`, `y_pred=[0,0,0,4,6]`, `customers=["A","A","A","A","B"]` and calculate both your report and ordinary row-weighted mean absolute error. Explain what population each average represents. Give time/space bounds and boundary tests.

### ML-T3 — Investigate a release claim

On a held-out set of 100 rows, customer A contributes 90 and customer B contributes 10. The old model has absolute error 1 on every row. The new model has error 0.5 on every A row and 4 on every B row. A dashboard reports only row-weighted MAE and calls the new model better.

Calculate row-weighted and equal-customer MAE for both models. Decide what additional evidence is needed before rollout. Address the intended customer population, uncertainty, expensive errors, a compatible feature/model bundle and rollback. The numbers are stipulated outcomes, not a real online experiment.

### ML-T4 — Communicate a bounded recommendation

Give a three-minute update to a product colleague: the decision being evaluated, the metric conflict, your release recommendation and the next discriminating check. Use a real example of receiving corrective feedback if available; otherwise label your reflection on this mock as hypothetical. Close with one thing the local metric tests do not establish.

## After the attempt

Spend 10–15 minutes outside the session comparing with the [companion](ML_Engineer_Mock_Interview_Interviewer.md). Record elapsed time, artifacts, clarifications, hints H1/H2/H3, unresolved errors and the six dimension scores in the [blank scorecard](Mock_Scorecard.md). H0 clarification is not a directional hint. Mark unobserved dimensions **Not assessed**. Pick one specific reattempt; do not infer a hiring decision from the score.

## Prerequisites and sources

[Model evaluation](../07-AI-ML-Deep-Learning/Model_Evaluation.md), [feature engineering](../07-AI-ML-Deep-Learning/Feature_Engineering.md), [ML coding](../02-Role-Based-Preparation/ML-Engineer/ML_Interview_Coding.md) and [ML question bank](../12-Interview-Question-Bank/ML_Engineer_Questions.md).

[Source scope](PHASE6_CORE_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
