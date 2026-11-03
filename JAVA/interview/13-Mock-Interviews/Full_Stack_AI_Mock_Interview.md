# Draft-report workspace recovery: candidate packet

This is original **[INFERRED], company-neutral** practice for an early-career candidate. It is not an employer's assessment, timing or eligibility rule. Keep this packet open; keep the interviewer companion closed until the session ends. All data, requirements and workloads below are stipulated synthetic inputs.

## Metadata

| Field | Value |
|---|---|
| Mock ID | MOCK-FS-01 |
| Target role | Full Stack AI Engineer |
| Level | 0–2 years; optional stretch branches |
| Difficulty | Intermediate |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED] |
| Tools | Paper/editor; pseudocode or TypeScript interface sketches; no browser/server required |
| Tasks | FS-T1, FS-T2, FS-T3, FS-T4 |
| Validation | Only synthetic capacity/replay arithmetic checked locally; API/browser/auth/workers are design reasoning |

## Before the clock

Allow a separate preflight to check the editor/runtime, permitted tools and accessibility needs. No generative assistance during this default independent attempt; a supported-learning attempt is valid when labeled and logged. No accounts, private data, network calls or deployment are required. Paper/pseudocode is acceptable when agreed before starting; record unexecuted code accordingly. Tell the interviewer what changes your approach. Factual clarifications are H0; directional hints are logged H1–H3. The clock covers the four stages below; reserve a separate 10–15 minutes afterward for review.

Prerequisites: [full-stack AI API design](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/API_Design.md), [realtime lifecycle](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Real_Time_Applications.md) and [backend contracts](../02-Role-Based-Preparation/Full-Stack-AI-Engineer/Backend_Engineering.md).

## Scenario and contract

A tenant workspace generates draft reports asynchronously. A user can leave and return while a job runs. Draft text may stream before validation, but export/share must wait for successful validation and current authorization. Creating a report job is the only action in scope; no actual external email/share integration must be implemented. Design the API, state ownership and client recovery protocol.

Stipulated workload: 200 jobs/hour average, each with 12 seconds mean active generation time, eight available generation slots. A burst is eight accepted jobs simultaneously. Generation time varies; these means are capacity inputs, not a deadline guarantee. Use a stable client-generated operation key scoped to tenant/user/request meaning for safe creation retries. A changed request with the same key must be rejected as a conflict.

Every persisted job event has a strictly increasing sequence **within that job**. State and relevant durable events must agree. The following is a synthetic server record, not proof that a browser received an event:

| Job sequence | Event | Meaning |
|---|---|---|
| 1 | queued | Accepted durably |
| 2 | running | Worker admitted |
| 3 | draft_delta | `Revenue ` appended provisionally |
| 4 | draft_delta | `rose 8%.` appended provisionally |
| 5 | validating | Draft checks started |
| 6 | failed | Source support check failed |

The client received events 1,2,3, disconnected, and later receives 3,4,5,6 on reconnect. All events for this exercise retain their original payload. A second variant reconnects after those events have been pruned; a durable current-state snapshot remains. The UI may retain failed draft text for this exercise, clearly labeled unusable for export/share.

## Timeline

| Stage | Minutes | Task |
|---|---|---|
| 1 | 10 | FS-T1 |
| 2 | 25 | FS-T2 |
| 3 | 25 | FS-T3 |
| 4 | 15 | FS-T4 |

## Candidate tasks

### FS-T1 — Frame the user contract

Define the user journey, minimal components, API/data ownership and observable job states. List the invariants that prevent a preview from becoming an accepted report. State a simple deployment choice and why it suffices.

### FS-T2 — Specify and trace recovery

Sketch creation/status/event/export interfaces and their authorization. Walk through a lost creation response, duplicate worker delivery, the reconnect sequence and pruned-event variant. Specify how the client reconstructs state without duplicating text or missing terminal state.

### FS-T3 — Handle capacity and failure

Calculate average active concurrency and nominal slot utilization under the stipulated means. Trace cancellation during generation, worker crash after draft persistence, duplicate completion and the final failed validation. Describe bounded admission and unknown external/provider effects without promising impossible cancellation.

### FS-T4 — Review the product behavior

Explain how the UI presents provisional, validating, failed, disconnected and unauthorized states accessibly. Give a minimal verification plan and a two-minute handoff identifying implemented versus reasoned evidence. Answer one followup chosen by the interviewer.

## Stop and review

Stop at 75 minutes even if incomplete; mark the exact unfinished boundary. Preserve your notes/code and test outputs before opening [the interviewer packet](Full_Stack_AI_Mock_Interview_Interviewer.md) (**spoilers**). A second attempt after reading the solution is a supported reattempt, not independent evidence. Use [the shared rubric](Interview_Evaluation_Rubric.md); one attempt cannot establish repeated level-4 performance or predict an offer.

[Mock directory](README.md) · [Source scope](PHASE6_AI_SOURCES.md). Last reviewed: **2026-11-03**.
