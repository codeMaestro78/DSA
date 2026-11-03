# Linear algebra

## Shapes and geometry

A:(m, n) maps x:(n,) to y:(m,). Multiplication is not generally commutative. For row batches X:(B, in), W:(out, in), the dense map is X@W.T+b:(B, out), with b:(out,). A shape assertion prevents accidental broadcasting from changing the computation.

Dot product measures alignment and magnitude; L2 norm is sqrt(x·x). Cosine divides by both nonzero norms, requiring an explicit zero-vector policy. Normalized embedding geometry does not guarantee semantic relevance.

## Rank and conditioning

Rank counts independent directions. Perfectly collinear features can make coefficients unidentifiable from predictions alone. Ill conditioning can amplify numerical perturbations. Least squares is not a reason to explicitly form a matrix inverse: suitable factorizations/solvers are generally more stable.

For reduced SVD of A:(m, n), U:(m, k), s:(k,), Vh:(k, n), k=min(m, n), and A≈U diag(s)Vh numerically. Truncating singular directions yields a low-rank representation under its approximation criterion, not automatically improved task quality. PCA centers training data and finds variance directions; fitting it on train+test leaks held-out statistics in an inductive evaluation.

## Worked example and cost

X=[[1, 2],[3, 4]], W=[[1, 0],[0, 1],[1, 1]] gives output[[1, 2, 3],[3, 4, 7]]. Output width is 3, so a per-output bias has length 3. A naive (m, n)@(n, p) product uses O(mnp) arithmetic and O(mp) output storage; optimized kernels/hardware determine measured time.

## Interview exercise — attempt first

**ID:** ML3-LINEAR_ALGEBRA. **Category:** ML/DL / Linear algebra. **Difficulty:** Beginner. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Count parameters and shapes for input width 4, output width 3.

<details><summary>Expected reasoning, followup and common mistake</summary>

W:(3, 4), b:(3,) give 15 parameters. A batch (B, 4) gives (B, 3). Parameter count is distinct from activation storage and runtime.

**Followup:** How would a zero embedding affect cosine similarity?

**Common mistake:** Using the wrong transpose or claiming every matrix has an ordinary inverse.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 2 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/linear_algebra.html).
- [numpy.linalg.svd / NumPy](https://numpy.org/doc/stable/reference/generated/numpy.linalg.svd.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
