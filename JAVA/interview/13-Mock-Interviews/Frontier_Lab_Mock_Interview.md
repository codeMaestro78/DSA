# Frontier research-engineering mock — a changed training loss

This is original **[INFERRED]**, company-neutral practice for an early-career candidate. It is not a company's interview, policy, hiring bar or timing. All datasets, workloads and product requirements here are fictional teaching fixtures. This candidate packet contains the tasks only; keep the [interviewer companion](Frontier_Lab_Mock_Interview_Interviewer.md) closed until the attempt ends.

## Metadata

| Field | Value |
|---|---|
| Mock ID | MOCK-LAB-01 |
| Target role | Research Engineer / ML infrastructure engineer |
| Level | 0–2 years; optional advanced followups |
| Difficulty | Advanced |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED]; no employer attribution |
| Tools | Python with NumPy; calculator and local tests; no GPU or training framework required |
| Tasks | LAB-T1, LAB-T2, LAB-T3, LAB-T4 |
| Validation | Exact masked-loss block extracted and tested; no model training, benchmark or employer loop reproduced |

## Before starting

Preflight is outside the 75-minute session: verify the chosen runtime works, prepare a scratch file and timer, and choose a partner or solo mode. For a strict independent attempt, do not use generative assistance or read the companion; this is an exercise rule, not an employer policy. Documentation lookup is allowed if recorded. A partner gives one task at a time and records questions/hints. Solo candidates write assumptions, stop at each checkpoint and open the companion only after the timed session. Never invent real project outcomes.

## Timeline

| Stage | Minutes | Task |
|---|---|---|
| 1 | 15 | LAB-T1 — hypothesis and comparison contract |
| 2 | 25 | LAB-T2 — numerical coding and checks |
| 3 | 20 | LAB-T3 — experiment and recovery plan |
| 4 | 15 | LAB-T4 — research communication and closing |

## Candidate tasks

### LAB-T1 — Interpret the evidence

A language-model training change reports “validation loss improved from 2.1 to 1.9.” The old run averages each sequence's mean valid-token loss equally. The new run averages over all valid tokens. Sequence lengths differ, and the new preprocessing also removes empty sequences. No matched per-example results or training-budget record is supplied.

State which comparisons are missing before attributing a model improvement. Define a falsifiable hypothesis, one primary comparison and at least two artifacts needed to reproduce it. Explain which concerns are about metric definition versus model/data changes. This is a generic research-engineering simulation; no frontier employer's process, internal training system or hiring bar is implied.

### LAB-T2 — Implement the stated numerical contract

Implement `masked_token_nll(logits, targets, mask)` returning the mean negative log probability of the target over valid positions. `logits` is a real numeric, finite NumPy-compatible array (excluding booleans, complex values and numeric strings) of shape (B,T,V), B,T>0, V≥2, at most 1,000,000 elements, with every absolute value ≤1e6. `targets` has shape (B,T) and an integer dtype excluding bool. `mask` has that shape and boolean dtype; True means included. Active targets must lie in [0,V); masked target values are ignored and may be −1. Reject malformed inputs or an all-False mask with `ValueError`; do not mutate inputs. Do not apply softmax before passing logits to the function. Explain numerical stability and time/space accounting.

For a worked fixture, let B=2,T=2,V=2, all logits equal zero, targets `[[0,-1],[1,0]]`, mask `[[True,False],[True,True]]`. Construct a second fixture with very large finite logits and a low-probability active target. What checks would distinguish a masking bug from an unstable exponential?

### LAB-T3 — Design the smallest useful experiment

Two sequences have valid-token losses [1] and [3,3,3]. Compute the equal-sequence mean and token mean. Design a comparison that isolates a proposed optimizer change while keeping the evaluation definition fixed. A training job later resumes from weights but omits optimizer state; explain whether this is the same continuation and what must be recorded. Include one stop condition and one rollback/recovery decision. No actual training or checkpoint recovery is required.

### LAB-T4 — Defend and revise the claim

Present a three-minute research update with hypothesis, evidence, confounders and next check. The interviewer challenges your favorite explanation: describe what observation would change your mind. If discussing prior research or open-source work, distinguish reading, implementing, reproducing and independently extending a result. Finish with one limitation of the local numerical fixture.

## After the attempt

Spend 10–15 minutes outside the session comparing with the [companion](Frontier_Lab_Mock_Interview_Interviewer.md). Record elapsed time, artifacts, clarifications, hints H1/H2/H3, unresolved errors and the six dimension scores in the [blank scorecard](Mock_Scorecard.md). H0 clarification is not a directional hint. Mark unobserved dimensions **Not assessed**. Pick one specific reattempt; do not infer a hiring decision from the score.

## Prerequisites and sources

[Loss functions](../07-AI-ML-Deep-Learning/Loss_Functions.md), [training/backpropagation](../07-AI-ML-Deep-Learning/Training_and_Backpropagation.md), [model evaluation](../07-AI-ML-Deep-Learning/Model_Evaluation.md), [AI/ML questions](../12-Interview-Question-Bank/AI_ML_Questions.md) and [advanced evidence questions](../12-Interview-Question-Bank/Advanced_Questions.md).

[Source scope](PHASE6_CORE_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
