# AI-native startup preparation

## Define the work rather than relying on a label

AI-native is not a standardized hiring or seniority category. For preparation, inspect whether the role builds a model-dependent product, retrieval/evaluation infrastructure, developer tools, model training or customer deployments. These require different evidence even when the titles sound similar. The distinctions here are [INFERRED] curriculum guidance.

[OFFICIAL] Cursor's careers page describes its software-building product context, while LangChain's careers page describes its agent-engineering focus and operating principles. These pages provide employer context; the inspected material does not establish a current role-specific technical loop. [Cursor](https://cursor.com/careers), [LangChain](https://www.langchain.com/careers), undated, accessed 2026-11-02. Detailed evidence limits are in the [catalog](Startup_Interview_Research_Catalog.md).

## Prepare the full engineering boundary

| Work | Evidence to practice |
|---|---|
| Model integration | Valid input/output contracts, errors, budgets and evaluation |
| Retrieval | Ingestion quality, ranking labels, permissions and citation support |
| Agents/tools | Typed proposals, server-side authorization, bounded state and unknown side effects |
| AI product UI | Partial/final status, cancellation, reconnect, accessible error states |
| ML training | Valid splits, baselines, reproducible experiments and deployment compatibility |
| Customer deployment | Discovery, integration constraints, acceptance and handoff |

A framework dependency or a chat screenshot does not establish all of these. Pick the responsibilities relevant to the posting and explain your contribution with its evidence limits.

## AI assistance during assessment

Do not infer permission from the employer building AI tools or using them internally. Obtain stage-specific instructions. If assistance is permitted, explain generated code, test it and disclose its role according to the instructions. If it is restricted, practice independently. A general “AI-friendly” culture statement does not override a specific assessment policy.

## Hypothetical exercise

Implement or design a bounded read-only tool loop with exact argument validation, an allowlist, deadline/step budget and explicit terminal states. Trace malformed output, a denied resource, repeated call ID with changed arguments, cancellation and tool timeout. The [local educational example](../../validation/ai/README.md) tests a small simulation; it does not prove a real model's tool quality or production recovery.

## Practice — STAGE-P4-05

**Intermediate; AI/full-stack/RAG; company-neutral technical discussion; 0–2 years.** How would you demonstrate that the model feature improved the user task rather than merely producing fluent output?

<details><summary>Expected reasoning, followup and common mistake</summary>

Define the task and baseline, use representative labeled examples with a held-out boundary, separate retrieval/answer/tool failures and inspect latency/cost/permission slices. Followup: how could a model judge be wrong? Calibrate it and inspect disagreements. Common mistake: treating a successful API response or polished demo as task-quality proof.

</details>

## Checklist

- [ ] Actual product/role responsibilities drive study choices.
- [ ] Tool permissions are confirmed per assessment.
- [ ] Quality, authority and operational correctness have separate evidence.

## References and verification

- [Cursor careers](https://cursor.com/careers).
- [LangChain careers](https://www.langchain.com/careers).
- [AI study sequence](../../PHASE_3_STUDY_GUIDE.md).

[Source metadata](PHASE4_SOURCES.md), [catalog](Startup_Interview_Research_Catalog.md), [methodology](../../RESEARCH_METHODOLOGY.md).

**Last verified:** 2026-11-02. Company facts are limited to the linked public sources; scenarios and preparation guidance are original [INFERRED]. No live vacancy, eligibility or interview outcome is guaranteed.
