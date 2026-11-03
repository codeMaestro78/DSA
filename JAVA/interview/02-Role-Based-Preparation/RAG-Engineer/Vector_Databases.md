# Choosing vector storage without unsupported rankings

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Select by workload and operational constraints, then validate on the actual corpus. The table records documented feature scope and [INFERRED] fit questions, not performance rankings or a claim that every edition/deployment exposes identical features.

| Technology | Inspected documented capability | A useful fit question |
|---|---|---|
| PostgreSQL+pgvector | Exact and approximate vector search, including HNSW/IVFFlat and filtering considerations | Can existing relational ownership/transactions simplify operations while meeting measured recall/latency? |
| Pinecone | Metadata-filtered vector search | Does a managed vector service fit data/access, lifecycle and operational constraints? |
| Weaviate | Hybrid keyword/vector search | Are its documented retrieval/fusion and data-model behaviors appropriate for the corpus? |
| Qdrant | Payload filtering over vector-search records | How do filter/index configuration and tenant scope affect candidate quality and operations? |
| Milvus | Standard and iterative filtered search paths | Which supported index/filter path works for the workload and deployment capabilities? |
| Elasticsearch | Combined full-text/vector retrieval and rank fusion | Can an existing text-search platform meet the desired hybrid retrieval contract? |
| OpenSearch | Hybrid queries using search pipelines with score/rank combination | Which version/processors and pipeline configuration are available in the intended deployment? |

These capabilities come from the references below. Do not infer an independent speed, cost or recall comparison from vendor documentation. Verify exact versions, dimensions, metric support, filter semantics, ingestion/deletion behavior, backups and access controls before committing.

## Worked role scenario

A corpus has 2 million vectors and highly selective tenant filters. Test exact-reference recall, p50/p95 latency under concurrency, update/delete visibility, resource usage and failure recovery using the same data/configuration budget. A benchmark without filter selectivity or recall targets is insufficient to choose a store.

## Interview practice: RAGE-P3-06

- **Category / difficulty:** Choosing vector storage without unsupported rankings / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you compare two stores fairly?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Fix dataset/embeddings/metric, query and filter distribution, relevance/exact reference, recall target, resource/service configuration, ingestion state and concurrent load. Record versions and uncertainty; evaluate operational requirements separately.

**Followup:** Why can adding an ANN index reduce returned filtered results in some execution paths?

**Common mistake:** Declaring one store fastest from unrelated vendor benchmarks.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Define workload and recall target.
- [ ] Verify filter and permission behavior.
- [ ] Include lifecycle/restore and operator costs.

## Canonical lessons and references

- [Canonical ANN search](../../08-LLM-RAG-and-Agent-Engineering/Vector_Search.md).
- [Retrieval optimization](Retrieval_Optimization.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).
- [Pinecone: metadata filters](https://docs.pinecone.io/guides/search/filter-by-metadata).
- [Weaviate: hybrid search](https://docs.weaviate.io/weaviate/search/hybrid).
- [Qdrant: filtering](https://qdrant.tech/documentation/search/filtering/).
- [Milvus: filtered search](https://milvus.io/docs/filtered-search.md).
- [Elasticsearch: hybrid search](https://www.elastic.co/docs/solutions/search/hybrid-search).
- [OpenSearch: hybrid search](https://docs.opensearch.org/latest/vector-search/ai-search/hybrid-search/index/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
