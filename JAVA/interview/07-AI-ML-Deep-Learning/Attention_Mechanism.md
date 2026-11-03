# Attention mechanisms

## Shapes and formula

Scaled dot-product attention is softmax(QK.T/sqrt(dk)+mask)V. Q:(Lq, dk), K:(Lk, dk), V:(Lk, dv) give scores/weights (Lq, Lk) and output (Lq, dv). Value width may differ from key width. Scaling controls score magnitude under common variance assumptions, not a universal quality guarantee.

## Worked causal mask

For three positions, query 0 uses key 0; query 1 uses keys 0–1; query 2 uses keys 0–2. Mask forbidden scores before normalization. The local implementation uses boolean True=allowed, rejects an all-masked query row and normalizes each valid row to 1. Framework mask conventions can differ; inspect the exact API.

Changing the last key/value should leave earlier outputs unchanged in this isolated causal computation. The numerical suite tests that property. It does not prove causality of an entire network containing other paths.

## Stable computation

Subtract the largest allowed score in each row before exponentiation. All-masked rows have no valid normalized distribution under this contract; blindly computing softmax can yield NaNs. Masking after softmax without renormalization generally changes row sums.

## Variants and interpretation

Self attention derives query/key/value from the same sequence; cross attention can query a different source. Multiple heads use distinct learned projections and combine outputs. A mixing weight is not automatically a faithful causal explanation of the final prediction.

## Costs

Dense materialized attention costs O(Lq*Lk*dk+Lq*Lk*dv) arithmetic and O(Lq*Lk) score storage. Full self attention therefore has a quadratic score matrix in length. Optimized exact kernels can avoid some materialization/memory traffic without removing every cost. [The tested CPU routine](../validation/ml/ml_examples.py) is a single head, not an optimized full Transformer.

## Interview exercise — attempt first

**ID:** ML3-ATTENTION_MECHANISM. **Category:** ML/DL / Attention mechanisms. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Q:(5, 4), K:(7, 4), V:(7, 3): what output and mask shapes?

<details><summary>Expected reasoning, followup and common mistake</summary>

Output(5, 3), mask (5, 7), with at least one allowed key per query under this routine. State True=allowed and explain all-masked rejection rather than assuming another API's convention.

**Followup:** Why is masking after softmax without renormalizing incorrect?

**Common mistake:** Reversing mask semantics or accepting an all-masked NaN row.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Attention Is All You Need / Vaswani et al.](https://arxiv.org/abs/1706.03762).
- [Deep Learning chapter 4 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/numerical.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
