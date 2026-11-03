# Explain an AI project under technical followup

## Scope

Use a real project and preserve the boundary between implementation, local testing, measured quality and operation. This is original [INFERRED], company-neutral practice. Do not claim production deployment, model training or benchmark improvement unless the evidence supports it.

## Prepare an evidence map

| Layer | What you should explain | Evidence and limit |
|---|---|---|
| Product | User problem and why AI was useful | Actual use/feedback versus a proposed use case |
| Model | Selection, prompt/tokenizer/configuration | Evaluation against a baseline, not model reputation |
| Retrieval | Ingestion, chunks, ranking, permissions and source versions | Labeled relevance/citation checks; corpus and principal scope |
| Tools/workflow | State ownership, allowed actions, timeout and recovery | Actual contracts/tests versus a diagram alone |
| Frontend/backend | Streaming/final status, auth, storage and cancellation | Implemented lifecycle and failure cases |
| Operation | Telemetry, latency/cost and rollback | Workload, environment and observed period |

## Three pitches

Thirty seconds: user problem, your contribution and one supported result. Two minutes: add the primary flow and one tradeoff. Five minutes: explain evaluation, failure/recovery, permissions and what you would change. These are rehearsal budgets, not employer timing.

A useful form is: “I built [actual component] for [task]. We chose [approach] because [constraint]. I checked it using [method/dataset]. The result was [observed finding], and this does not establish [unmeasured property].” Fill with true facts; it is not an experience to adopt.

## Followup chains

“Why RAG?” → “Which questions needed external evidence?” → “How did you label retrieval relevance?” → “Could the answer cite the wrong chunk?” → “What happened after permission revocation?” → “What is actually tested?”

“Why agents?” → “Which choice could not be a fixed workflow?” → “Who owned durable state?” → “What if a tool completed but timed out?” → “Could a restarted worker repeat it?” → “Which mechanism prevents or reconciles the duplicate?”

“Which model?” → “Which baseline and task mix?” → “Were prompts selected using the test set?” → “What changed in latency/cost and under what workload?” → “Would a fallback preserve quality?”

## Diagnose your own weak claim

If you say “the system prevents hallucinations,” replace the absolute claim with the actual measured property, such as evidence support on a defined labeled set with known failures. If you only have a screenshot, explain the demonstrated flow and propose the missing evaluation rather than reporting an accuracy score.

## Practice question

A reviewer asks whether your project is production ready. Give a truthful answer that distinguishes a working path from permissions, capacity, restore/rollback, observability and evaluation evidence.

<details><summary>Expected reasoning</summary>

Name what exists and was checked, then the material gaps and next verification. A repository, successful demo or passing unit suite cannot establish all production properties. A clear gap is more useful than an unsupported readiness claim.

</details>

## Checklist

- [ ] My contribution, AI assistance and team responsibilities are accurate.
- [ ] Quality/latency/cost claims have reproducible scope.
- [ ] I can trace one failure and one permission change.
- [ ] Hypothetical improvements are not presented as implemented.

## References and last verified

[Project framework](Project_Explanation_Framework.md), [AI evaluation platform](../04-System-Design/AI-System-Design/AI_Evaluation_Platform.md), [AI architecture framework](../04-System-Design/AI-System-Design/AI_System_Design_Framework.md).

2026-11-02: original practice guidance reviewed; no candidate project audited.
