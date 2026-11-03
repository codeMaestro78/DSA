# Graph RAG and corpus-level questions

## The problem graph structure may solve

Some questions require relationships across sources or a corpus-level summary rather than one matching passage. Graph-based retrieval can represent entities, relations and communities, then retrieve or summarize along those structures. The cited GraphRAG paper studies query-focused summarization using a graph-derived approach. It does not prove every graph-augmented pipeline outperforms vector retrieval.

A knowledge graph and a graph index over text are related but different artifacts. Extracted relations can be uncertain or wrong. Entity resolution, relation direction, source provenance, time and permission scope must be explicit; a graph edge is not automatically a verified fact.

## Worked comparison

Question 1: “What is the timeout in policy P-104?” A targeted lexical/dense lookup may be sufficient. Question 2: “Which recurring obstacles appear across this quarter's project reports?” A global summarization task may benefit from graph/community summaries or another structured aggregation approach. Choose based on task coverage and evaluation, not the popularity of the term GraphRAG.

If reports mention “Atlas” as both a product and a team, naïve entity merging can invent connections. Keep identity disambiguation and supporting source spans. A relation should retain where it came from and whether it was extracted or validated.

## Indexing and query flow

Parse/source versions → extract entities/relations → resolve identities → construct graph and optional summaries → authorize eligible graph/source subset → retrieve paths/communities → assemble grounded context → generate and verify support. Graph construction and summary generation add model calls, storage and freshness work.

Deleting a document may require removing or recomputing dependent edges, communities and summaries. If a summary mixes multiple tenants or access groups, retrieving only its public-looking text can leak protected information. Permission labels must cover derived artifacts, not just original passages.

## Cost and correctness tradeoffs

Graph structures can help explicit relationship traversal, but extraction errors propagate. Broad summaries may compress away minority views or source qualifiers. Incremental updates can be harder than replacing independent document vectors. Benchmark task-specific answer support, provenance, update cost and permission behavior against a simple baseline.

## Readiness question

Be able to explain why a graph is needed, which relation invariants matter and how a wrong merge affects an answer. If the task is a simple passage lookup, defend a simpler design. The local examples in this phase do not construct or benchmark a graph index; this chapter is a reasoned design lesson.

## Interview practice — P3-AI-13

**Question:** A derived community summary includes content from a revoked source. Is deleting only that source vector sufficient?

- **Category/topic:** AI engineering / Graph RAG and corpus-level questions.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** No. Trace derived edges, summaries, caches and generated artifacts; recompute or invalidate as required. Permissions and deletion apply to derivations as well as original records.

**Followup:** What baseline would you use for a corpus-wide themes task?

**Common mistake:** Claiming a graph guarantees factual relations or improves every retrieval task.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Edge et al.: GraphRAG](https://arxiv.org/abs/2404.16130) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
