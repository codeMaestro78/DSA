# Reranking candidates

## Separate candidate generation from final order

A retriever searches the large corpus efficiently. A reranker spends more computation comparing a smaller candidate set with the query. A cross-encoder jointly processes query and document; a bi-encoder can precompute document embeddings. Other rerankers include learned feature models, late-interaction designs and explicit rules. The word reranking does not imply one particular architecture.

Candidate recall bounds the benefit: if relevant evidence is absent, a perfect reranker cannot promote it. Evaluate retrieval and reranking separately so an improvement claim identifies the correct stage.

## Worked example

The initial candidates are a current contractor policy, an obsolete employee policy and a generic benefits overview. Dense similarity places the generic overview first. A reranker may promote the contractor passage if it sees the query's worker type and effective date. However, if ingestion removed the effective-date table or retrieval excluded the contractor policy, changing reranker weights does not restore that evidence.

A content score must not override hard constraints. Tenant permissions, explicit date eligibility and document status should be enforced through authoritative rules where required. A highly scored unauthorized result remains unauthorized.

## Input and output contract

Retain query, stable candidate ID, source version/span and the content actually scored. Bound input length and define truncation. A reranker that sees only a chunk's first 256 tokens may miss the answer near the end; either choose appropriate chunks, include relevant structural context or test another policy. Do not silently treat its score as covering the whole document.

Output is an ordered candidate list with versioned scoring metadata. Scores are usually model/configuration-dependent; avoid universal thresholds without calibration. Preserve deterministic tie handling for reproducible evaluation.

## Cost and quality tradeoff

Increasing candidates increases work and can improve ranking only if useful candidates are added. A cross-encoder's inference cost depends on candidate count and sequence length. Batch scheduling affects throughput and latency. No fixed per-candidate latency or “always better” model hierarchy is established here.

Compare relevance metrics before/after on the same candidates, then evaluate end-to-end answer support. A better NDCG does not necessarily improve a multi-document synthesis task if diverse evidence is lost. Add source diversity, contradiction coverage and citation needs where the task requires them.

## Failure case

A reranker promotes persuasive but stale guidance because it lacks temporal labels. Diagnose missing features/data and the policy constraint. More expensive ranking without the right evidence model can make the wrong answer look more consistent.

## Interview practice — P3-AI-11

**Question:** How would you distinguish poor reranking from missing candidate recall?

- **Category/topic:** AI engineering / Reranking candidates.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Check whether labeled relevant chunks appear before reranking, then inspect their scores/order and the text actually seen. Evaluate stages separately and keep hard permission/freshness rules outside unconstrained scores.

**Followup:** Can a higher NDCG reduce useful evidence diversity?

**Common mistake:** Assuming a reranker can retrieve missing documents or that its score is a calibrated probability.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Microsoft Learn: reciprocal rank fusion](https://learn.microsoft.com/en-us/azure/search/hybrid-search-ranking) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
