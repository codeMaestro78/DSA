# Vector search and approximate indexes

## Exact baseline

Given N vectors of D coordinates, a brute-force similarity scan computes O(ND) arithmetic work before top-k selection. Full sorting adds O(N log(N+1)); a heap can reduce selection overhead to O(N log(k+1)) for a suitable implementation with k≥1. The +1 keeps the stated bound meaningful at k=1. This is a useful correctness/recall reference on bounded data, not a production scaling prescription.

Approximate nearest-neighbor search trades search work, memory, build cost and recall. Recall here concerns recovery of the exact nearest neighbors under the chosen distance, which is distinct from relevance recall against human labels. Better ANN recall cannot repair embeddings that rank irrelevant text highly.

## HNSW and IVF mental models

HNSW uses a hierarchy of proximity graphs to navigate toward candidates. Graph connectivity/build/search parameters affect recall, memory and latency; a simple universal worst-case logarithmic guarantee is inappropriate. IVF partitions space into coarse cells and searches selected lists. Increasing probes can improve exact-neighbor recovery while doing more work. Quantization can compress representation with distortion and additional tradeoffs.

The Faiss documentation describes index families and concrete storage/capability differences. It is not a managed database: durability, tenant permissions, updates, replicas and operational ownership may need separate components.

## Worked diagnostic

An authorized query expects 10 results. The ANN scan finds mostly other tenants' vectors and a later filter leaves 2. This does not prove only 2 relevant authorized records exist. Inspect filter placement, candidate/search settings and exact filtered baseline. Product-specific iterative scans or alternative plans may help; preserve authorization regardless of tuning.

For pgvector, read the actual version's filtering/index behavior before promising recall or performance. An index name alone does not define how selective filters interact with search. Some systems integrate prefilters differently; compare documented semantics and measured workload rather than asserting all databases work alike.

## Selection criteria

Evaluate dimension/metric support, updates/deletes, filtering, index build memory, recovery, tenant isolation, backup, deployment constraints and total cost. Compare pgvector, managed services or dedicated engines only against those requirements. No fastest vector database is established by this chapter.

## Validation plan

Freeze the vector/query set, metric and filter predicate. Compute exact neighbors, then vary ANN settings and measure recall@k, latency distribution and memory under comparable conditions. Include update/delete churn and selective filters. The local cosine tests validate synthetic arithmetic only; no ANN library or vector database was benchmarked here.

## Interview practice — P3-AI-09

**Question:** Why can high exact-neighbor recall coexist with poor answer relevance?

- **Category/topic:** AI engineering / Vector search and approximate indexes.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** ANN can accurately recover vectors nearest under the embedding metric while that metric or corpus poorly represents the task. Separate ANN recovery, labeled relevance, permissions and generation quality.

**Followup:** How would you test a selective-filter recall failure?

**Common mistake:** Claiming guaranteed O(logN) HNSW behavior or treating exact-neighbor recall as answer accuracy.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Malkov and Yashunin: HNSW](https://arxiv.org/abs/1603.09320) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [Faiss index documentation](https://github.com/facebookresearch/faiss/wiki/Faiss-indexes) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [pgvector official README](https://github.com/pgvector/pgvector) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
