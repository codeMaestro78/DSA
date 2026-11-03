# Retrieval strategies and query processing

## Match the evidence task

Lexical retrieval finds term/phrase matches and is useful for identifiers, names and exact wording. Dense retrieval uses embedding geometry and can recover semantic matches without shared terms. Metadata lookup can be the correct solution for exact structured constraints. Choose from the query's required evidence rather than assuming one method dominates.

BM25 combines term-frequency saturation, inverse document frequency and document-length normalization. Its implementation details and parameters vary; the cited Elasticsearch documentation establishes that product's BM25 settings, not a universal cross-engine score scale. A raw term-overlap count is not BM25. This phase implements no BM25 engine.

## Query transformations

Rewriting can resolve conversational references; expansion can add synonyms; decomposition can split a multi-part question. Preserve the original intent and hard filters. A rewritten query that drops a date or negation can retrieve convincing wrong evidence. Store original/transformed queries and the transformation version for diagnosis while controlling sensitive logs.

Worked example: “Does policy P-104 cover contractors in India after July 2026?” needs exact policy identity, worker type, region and effective date. A semantic query alone may retrieve the employee policy. Use structured filters where authoritative metadata exists, lexical identity matching and semantic candidates only where they improve coverage.

## Candidate and evidence budgets

Retrieve a bounded number of candidates, then use fusion/reranking and context selection. Candidate size affects recall and latency; final context size affects generation. A larger candidate pool is not the same as a larger model context. Use per-stage measurements to decide which limit to increase.

Filters must preserve authorization regardless of the search strategy. Product support for filter-aware ANN differs; measure recall under realistic selectivity. A metadata predicate can reduce candidate count so sharply that an approximate index returns too few useful results even though authorized evidence exists.

## Baseline comparison

Compare lexical-only, dense-only and hybrid on the same labeled dataset. Include exact IDs, synonyms, multilingual text, dates, tables and unanswerable queries. Report recall/rank quality, source coverage, latency and the permission policy. Do not infer current interview frequency or a vendor's global superiority from a small local sample.

## Failure case

Query expansion adds “full-time employee” to a contractor question because it is common in training examples. Retrieval improves superficial similarity while changing scope. The fix is to preserve critical constraints and validate transformed intent, not simply increase top-k.

## Interview practice — P3-AI-08

**Question:** How would you retrieve a policy with an exact identifier and an uncertain natural-language description?

- **Category/topic:** AI engineering / Retrieval strategies and query processing.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Use identifier/metadata constraints and lexical matching, optionally dense candidates for semantic description. Preserve region/date/subject filters and evaluate recall/ranking with known relevant evidence.

**Followup:** How could a rewrite improve relevance while making the answer incorrect?

**Common mistake:** Labeling a custom overlap score BM25 or dropping hard constraints during query rewriting.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Elastic: similarity settings](https://www.elastic.co/docs/reference/elasticsearch/index-settings/similarity) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Lewis et al.: Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [pgvector official README](https://github.com/pgvector/pgvector) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
