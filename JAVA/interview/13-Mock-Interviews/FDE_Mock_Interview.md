# FDE mock — a support-routing pilot

## Candidate packet

This is an original **[INFERRED]**, company-neutral customer-engineering roleplay for approximately 0–2 years of experience. The customer, facts and measurements are fictional fixtures. You are practicing discovery, integration decisions, evidence and communication; you are not implementing a connector or speaking to a real customer.

| Field | Value |
|---|---|
| Mock ID | MOCK-FDE-01 |
| Target role | Forward Deployed Engineer; customer engineer |
| Level | 0–2 years; supported pilot ownership |
| Difficulty | Intermediate |
| Duration minutes | 75 |
| Company scope | Company-neutral; original [INFERRED] |
| Tools | Notes, calculator, diagram editor; no external data uploads or customer systems; agree reference/AI rules before starting |
| Tasks | FDE-T1, FDE-T2, FDE-T3, FDE-T4 |
| Validation | Roleplay/design reasoning; only supplied evaluation arithmetic has local fixture checks |

## Preflight and timing

Agree tools and whether this is guided practice or an independent assessment. Preflight and the 10–15-minute debrief are outside the live duration. The interviewer plays the customer and answers factual questions as H0 clarifications. Ask one decision-changing question at a time. Record unknowns rather than filling them with invented facts.

| Stage | Minutes | Task |
|---|---|---|
| 1 | 15 | FDE-T1 — discover the real decision |
| 2 | 20 | FDE-T2 — specify a bounded integration |
| 3 | 20 | FDE-T3 — evaluate and negotiate release |
| 4 | 20 | FDE-T4 — handoff and communicate uncertainty |

## Scenario and available material

A fictional customer support manager says, “We get 1,000 tickets a day. Make an AI agent route every ticket automatically by Friday.” You have an existing ticket system, a sanitized offline export and a customer IT contact. You do not yet have permission to write routing changes, send ticket contents to an external model or assume the proposed deadline is feasible.

Categories in this pilot are Billing, Technical and Other. “Review” is an abstention: a human decides. The following frozen 200-ticket diagnostic fixture is supplied for arithmetic, not as evidence that a model or pilot was run. Rows are reference labels, columns are the proposed action; each ticket appears once. The reference labels are treated as correct for this exercise, while label quality in a real pilot remains a question.

<!-- phase6-fixture: FDE-routing -->
```json
{"columns":["Billing","Technical","Review"],"rows":{"Billing":[46,4,10],"Technical":[2,92,6],"Other":[2,4,34]}}
```

An “Other” ticket must not be silently discarded. A proposed action and a persisted ticket-system update are different artifacts. No real customer credentials or private examples are required for any answer.

## Live questions

### FDE-T1 — Discover the decision behind the request

Run a short discovery conversation. Identify users, workflow, costly mistakes, data authority/access, success criteria, dependencies and who can approve a scope change. Prioritize questions whose answers could change your recommendation. Conclude with a fact/assumption/unknown ledger and a bounded next step, not a promise to automate everything.

### FDE-T2 — Specify the pilot interface

Design a safe initial integration given the access constraints. Specify stable input identity/version, output state, duplicate/revised-record handling, permissions, human review and what happens if a ticket changes after a proposal is created. Explain the system of record and what you would demonstrate if live connector access never arrives. Choose an AI or non-AI baseline with reasons; no particular model/framework is required.

### FDE-T3 — Interpret evidence and negotiate a release

Compute proposal coverage, correctness among non-review proposals and the number of reference Billing tickets proposed to Technical. Explain what the fixture does and does not show. Ask the customer for an acceptance decision rule, then recommend whether to proceed, restrict scope or stop. Handle the challenge: “The demo looks good; why not turn on automatic routing now?”

### FDE-T4 — Handoff under a changed constraint

The customer reports that a proposal for an old ticket was applied after that ticket's category changed. Outline immediate containment, evidence gathering, ownership and a reproducible next check. Give a two-minute customer update with knowns, unknowns, next action and update point. Finish with a pilot handoff: acceptance owner, monitoring, fallback, runbook and one outstanding dependency. If asked for past experience, use a truthful event or explicitly keep the response hypothetical.

## Submission and debrief

Save the discovery ledger, integration contract, calculations, acceptance recommendation, incident plan and customer update. Score only observed behavior using the [shared rubric](Interview_Evaluation_Rubric.md); mark unattempted dimensions Not assessed.

**Spoiler boundary:** the [interviewer companion](FDE_Mock_Interview_Interviewer.md) has scripted customer answers, hints and reference solutions. Give it to the interviewer or open after attempting.

## Prerequisites and provenance

[Technical discovery](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Technical_Discovery.md), [customer communication](../02-Role-Based-Preparation/Forward-Deployed-Engineer/Communication_and_Stakeholder_Management.md), [AI evaluation](../08-LLM-RAG-and-Agent-Engineering/README.md), [FDE transfer questions](../12-Interview-Question-Bank/FDE_Questions.md).

[Source scope](PHASE6_DESIGN_SOURCES.md). Last reviewed: **2026-11-03**. No model quality, connector permissions, customer engagement or deployment was observed.
