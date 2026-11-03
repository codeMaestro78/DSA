# ML numerical validation

## Reproduce

From the workspace root, with Python and NumPy installed:

```bash
python3 interview/validation/ml/check_ml.py
```

The runner sets `sys.dont_write_bytecode = True` before importing the local implementation, so this invocation does not create compiled artifacts in the knowledge base. It uses no network, downloaded model, torch, TensorFlow, scikit-learn, GPU or external service.

## Observed execution

**2026-11-03: 306 checks passed** on **Python 3.12.0 / NumPy 2.4.6**, seed `20261102`.

- 25 generated float64 logistic fixtures: three weight derivatives and one bias derivative compared with central finite differences, then a small update checked against the same fixture loss.
- Stable loss for large wrong logits, invalid label/shape/nonfinite input, and rejection of a nonscalar bias and derived loss/gradient overflow.
- Fresh audit adds30 boundary checks: complex/numeric-string/boolean feature-weight-bias data and malformed inputs are rejected before lossy conversion; boolean binary logistic labels remain accepted. The derived attention-output boundary also accepts only a finite result or explicit rejection on an extreme finite-value fixture; backend rounding can differ. The original2026-11-02 run had276 checks.
- Confusion counts, ordinary metrics and explicit undefined-denominator policies; empty/mismatched/nonbinary inputs rejected.
- Training-only standardization, held-out transformation, constant-feature policy, a demonstration that fitting held-out rows changes statistics, and rejection of nonfinite derived statistics despite finite huge input.
- Causal attention at lengths 1 through 12: normalized rows, zero forbidden weights, independent per-query numerical reference and future-key/value perturbation independence for earlier outputs.
- Attention rejects all-masked rows, nonboolean masks and incompatible shapes.
- One float64 LoRA merged/unmerged algebra fixture and adapter parameter count; one bounded symmetric int8 rounding-error fixture.
- Convolution width/parameter count, a small dense-network count, base-rate precision, effective batch count, log-loss examples and discounted-return arithmetic.
- Both actual published framework snippets are extracted from Markdown and parsed with Python AST. This validates syntax only.

## Files and contracts

- [Implementation](ml_examples.py): finite nonempty matrix validation, mean logistic loss/gradient, binary metrics, training-fit standardizer and one materialized attention head.
- [Checks](check_ml.py): deterministic contracts, independent numerical comparisons and source-snippet extraction.
- [Domain chapters](../../07-AI-ML-Deep-Learning/README.md) and [ML role drills](../../02-Role-Based-Preparation/ML-Engineer/README.md).

The logistic routine accepts `X:(n,d)`, binary `y:(n,)`, `w:(d,)` and a scalar finite bias. Feature, weight, bias, standardizer and attention data require real integer/unsigned/float dtypes; complex, object, numeric-string and boolean numeric data are rejected with ValueError rather than silently coerced. Logistic labels may also use boolean dtype. It uses float64 and rejects nonfinite logits, derived loss and gradients; finite inputs can still exceed the representable range of intermediate reductions. Its computation costs O(nd) work with O(n+d) principal intermediates, excluding input conversion/storage as applicable.

Metrics accept nonempty equal-length binary label vectors, not probability scores. Undefined precision, recall or F1 is represented by `None`. This is an explicit teaching contract, not a claim that every library uses this convention. Metrics take O(n) work and vectorized intermediate storage.

The standardizer fits population statistics only to the provided training matrix. Zero standard deviation becomes scale one. The caller owns valid splitting and transformation of future data; the function cannot determine whether a row should have been held out. Computation costs O(nd), and returned statistics occupy O(d); vectorized intermediates/input conversion may occupy O(nd).

Attention uses query/key width agreement, key/value length agreement and boolean **True = allowed**. Every query must have an allowed key. It materializes the score matrix, costing O(Lq·Lk·dk + Lq·Lk·dv) arithmetic and O(Lq·Lk) score/weight storage plus output. This is not a memory-optimized Transformer implementation.

## Limits

Finite-difference checks cover small smooth fixtures with numerical tolerances; they do not prove every valid input or all training algorithms. NumPy execution does not validate PyTorch/TensorFlow APIs. Those packages were unavailable, so their chapters explicitly mark the snippets as syntax-only and unexecuted. No convergence, generalization, framework training, distributed collective, packed quantization kernel, GPU, throughput, checkpoint recovery or production serving behavior was tested.

The causal perturbation test concerns the isolated attention routine, not every path through a full model. Quantization checks concern nearest rounding without clipping on a bounded fixture; they do not establish task-quality preservation. The leakage example demonstrates a changed fitted transform, not a scikit-learn pipeline runtime.

## References and last verified

[Primary source metadata](../../07-AI-ML-Deep-Learning/PHASE3_SOURCES.md). NumPy documentation's rolling version is not asserted to match the installed 2.4.6 runtime; only the basic operations actually executed above are claimed.

2026-11-03:306 checks passed after strengthening real-numeric conversion. [NumPy ComplexWarning documentation](https://numpy.org/doc/stable/reference/generated/numpy.exceptions.ComplexWarning.html), directly inspected2026-11-03, confirms that complex-to-real casting can discard the imaginary component; rolling documentation is not asserted to match the installed patch version. The helper is tested against the installed NumPy2.4.6 behavior.
