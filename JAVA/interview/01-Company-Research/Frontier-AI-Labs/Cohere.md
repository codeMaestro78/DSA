# Cohere interview dossier

## Identity and profile

[OFFICIAL] This dossier concerns **cohere.com**, whose careers page describes foundation models and secure enterprise AI, including named model/platform families. The page lists offices across Toronto, London, New York, San Francisco, Montreal, Paris and Seoul. Product and office descriptions are context, not interview requirements. [Careers](https://cohere.com/careers).

Do not merge Cohere with **Cohere Health**, **Cohere Technologies** or an unrelated cohere.io customer-service company. Search results can mix employers. A current headquarters/legal-entity history and all live openings were not independently cataloged; check the actual posting rather than infer location eligibility from an office list.

## Official hiring outline

[OFFICIAL] The FAQ describes application review, recruiter conversation, a **possible** take-home, hiring-manager meeting, final team conversations and an offer if aligned. Video interviews and scheduling flexibility are mentioned. The outline supplies no fixed technical panel size, duration or universal language. [Hiring FAQ](https://cohere.com/careers).

“May” does not make a take-home mandatory. No broad permission to use coding agents during assessments was verified. Confirm resources separately for each stage. A research role and a product role need not instantiate the outline identically.

## Posting access and qualification boundary

[UNVERIFIED] A **Search Applications** posting surfaced in discovery, but direct access returned **404**. Another **Security Agents** posting returned JavaScript-only content. Their indexed responsibilities were not promoted to current verified qualifications or interview stages. [Search lead](https://jobs.ashbyhq.com/cohere/64fb905c-b3b4-4fcf-9e1c-a806c9c40068?employmentType=FullTime), [security lead](https://jobs.ashbyhq.com/cohere/1b909aeb-1221-476f-88fe-8300a2065975/).

For a 0–2 year candidate, current graduate/intern eligibility remains unestablished here. Do not interpret a general remote culture or an inclusive application statement as unrestricted location/work-authorization eligibility.

## Role-specific preparation interpretation

All recommendations are **[INFERRED]**, not confirmed questions:

| Target | Practical emphasis | Evidence to prepare |
|---|---|---|
| Backend/full-stack | Data contracts, permission-aware APIs and failure handling | Tested component and a clear ownership boundary |
| AI/RAG | Ingestion, ranking, citations, evaluation and source lifecycle | Labeled relevance/support checks with limits |
| ML/research | Baselines, training/evaluation validity and model tradeoffs | Reproducible small experiment, not only model terminology |
| Infrastructure | Serving, queues, telemetry, resource budgets and recovery | A concrete timeout/overload investigation |
| FDE/solutions | Specific posting's customer/integration responsibilities | Honest discovery and acceptance evidence; do not invent a loop from a title |

## Candidate experiences and uncertainty

[UNVERIFIED] No fully contextualized primary candidate account was accepted for this dossier. Search discovery included requests about upcoming interviews and third-party aggregations. A prospective round described by someone's friend is weaker than an account of a completed assessment and cannot establish company-wide policy. No recurring trend or numerical frequency is asserted.

This is an initial scoped evidence synthesis, not proof that candidates never receive additional rounds. When a reliable report becomes available, preserve role, level, location, interview date and report date; compare it with the official outline instead of replacing the outline with a guessed fixed loop.

## Preparation strategy

[INFERRED] Must prepare coding/tests and a defensible project. For enterprise-AI interests, prioritize authorization, data freshness, quality measurement and failure recovery. Advanced model-training or GPU performance content belongs to the relevant job, not every Cohere applicant. Use [the RAG role curriculum](../../02-Role-Based-Preparation/RAG-Engineer/README.md) for mechanisms.

**Original drill — COH-P4-01, Intermediate; company-neutral enterprise-search design:** a retrieval update improves average relevance but surfaces a deleted document from a cache. Explain why the rollout cannot be judged only by relevance.

<details><summary>Expected reasoning and followups</summary>

Separate quality from authorization/deletion invariants. Identify the authoritative catalog, index/cache generation and tombstone checks before private text reaches a reranker/model. Stop or roll back the unsafe path, preserve evidence and test propagation under stale workers/caches. Followups: outage behavior when policy authority is unavailable, selective filters and version-safe migration. Weak answer: trading a permission violation against a higher aggregate relevance score.

</details>

## Behavioral and recruiter questions

[OFFICIAL] The careers page names momentum, openness and autonomy as values. These are stated culture themes, not evidence of a published scoring scale. [Careers](https://cohere.com/careers).

[INFERRED] Prepare truthful examples of independent work, feedback and choosing a bounded experiment. Ask exact level/team, current vacancy/location terms, take-home scope, language/tools, whether AI use is permitted, which product/research competencies apply and how success is assessed.

## References

- [Official careers, process and values](https://cohere.com/careers), undated.
- ATS URLs above remain access-limited leads, not accepted current role evidence.

## Last verified

**2026-11-02**: accepted sources directly opened; publication/update dates are unstated unless explicitly recorded. This verifies public statements and the scoped reports, not every team's practice or continued vacancy. [Source metadata](PHASE4_SOURCES.md).
