# Embeddings and similarity

## Representation and purpose

An embedding maps an input to a vector whose geometry reflects the training objective. Text embeddings can support semantic retrieval, clustering and similarity features; they are not a guarantee of truth, relevance or permission. A query and document must use compatible embedding spaces and the intended query/document formatting.

A document collection with N vectors of dimension D has an [N,D] representation; the query has [D]. A raw float32 matrix uses approximately 4ND bytes before identifiers, metadata, index structures, allocator overhead and replicas. For N=1,000,000 and D=768, that is 3,072,000,000 bytes, about 3.072 decimal GB, not a full vector-database capacity requirement.

## Similarity mechanics

Dot product is Σqᵢdᵢ. Cosine divides by the product of Euclidean norms. For unit-normalized vectors, dot product equals cosine, and squared Euclidean distance equals 2−2·dot. Without normalization those rankings need not agree. A zero vector has undefined cosine; do not silently return a meaningful-looking similarity.

Worked example: q=[1,0], a=[1,0], b=[0,1], c=[−1,0]. Cosines are 1, 0, −1 respectively. Scaling a by a positive factor preserves cosine, but changing model version, pooling or normalization can change the meaning of comparisons. The [local helper/tests](../validation/ai/README.md) check this geometry with synthetic vectors; no real embedding model was evaluated.

## Index and migration contract

Record model/version, dimensions, normalization, input preprocessing and source version. Mixing vectors from different models in the same nearest-neighbor space is generally invalid unless compatibility is established. A migration needs re-embedding, a versioned index and a controlled comparison before switching reads. Partial migration must not silently combine incompatible vectors.

## Failures and evaluation

A semantically similar paragraph can be out of date, from another tenant or opposite in meaning because of negation. Numeric IDs and exact product codes can be poor semantic matches. Combine metadata permissions with retrieval evaluation; use lexical search where exact tokens matter.

Changing embedding models can improve one task and harm another. Compare using the same authorized corpus, queries, relevance labels and latency/cost environment. Report per-slice results and uncertainty. No universal best embedding dimension, model or similarity threshold is established here; a threshold requires calibration against the actual relevance task.

## Interview practice — P3-AI-03

**Question:** When do cosine and Euclidean search produce the same ordering, and what breaks that equivalence?

- **Category/topic:** AI engineering / Embeddings and similarity.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** For vectors normalized to unit length, squared L2=2−2cosine, giving equivalent ranking. Missing normalization, incompatible spaces or zero vectors breaks the premise.

**Followup:** What metadata would you retain to re-embed a document safely?

**Common mistake:** Comparing incompatible model versions or interpreting similarity as authorization or a calibrated probability.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Faiss index documentation](https://github.com/facebookresearch/faiss/wiki/Faiss-indexes) — scope and metadata in [the register](PHASE3_SOURCES.md).
- [pgvector official README](https://github.com/pgvector/pgvector) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
