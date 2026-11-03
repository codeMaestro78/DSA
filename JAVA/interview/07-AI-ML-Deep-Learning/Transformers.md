# Transformers

## Block structure

A Transformer combines attention, position-wise feedforward computation, normalization, residual connections and positional information. The original encoder-decoder paper is one design; encoder-only and decoder-only variants have different masks/tasks. It is not a blueprint for every current LLM.

## Dimension trace

Token IDs(B, T) map to embeddings (B, T, dmodel). Heads project query/key/value, mix allowed positions, then combine head output. Feedforward layers transform each position. Residual additions need matching shapes; normalization acts over explicitly configured dimensions.

An autoregressive head gives logits (B, T, vocabulary). Training shifts targets so a position predicts the next token with a causal mask. Padding labels need a declared exclusion/reduction policy. Allowing future targets creates a deceptively easy objective unlike generation.

## Order and generation

Attention without position-related structure lacks the intended ordering information. Absolute, relative and rotary mechanisms differ; having a position mechanism does not guarantee extrapolation to arbitrary lengths. Teacher-forced training processes positions in parallel under the mask; autoregressive decoding generates sequentially. KV caching reuses past projections, trading storage against repeated work; cached state must be scoped to request/model/version.

## Complexity and limitations

For standard dense self attention, the materialized score matrix is O(T²) per head; score/mixing arithmetic depends on widths. Projection/feedforward work also matters and can dominate in some regimes. Faster kernels or sparsity change implementation/assumptions; do not claim quadratic cost for every architecture without specifying full dense attention.

Wrong shifts, masks, padding and mode behavior are high-value debugging cases. A correct attention routine does not validate tokenization, training, sampling or factual accuracy. Evaluate the actual intended task rather than equating low teacher-forced loss with product quality.

## Interview exercise — attempt first

**ID:** ML3-TRANSFORMERS. **Category:** ML/DL / Transformers. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why might low teacher-forced training loss coexist with bad generation?

<details><summary>Expected reasoning, followup and common mistake</summary>

Check leakage/masking/target shift, then overfitting, task mismatch and generation settings. Training conditions on true prior tokens; generation uses its own previous outputs.

**Followup:** Which training mistake lets future targets leak into current predictions?

**Common mistake:** Assuming every Transformer has the original encoder-decoder architecture.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Attention Is All You Need / Vaswani et al.](https://arxiv.org/abs/1706.03762).
- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
