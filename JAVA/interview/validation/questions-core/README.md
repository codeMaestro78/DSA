# Phase 5 core question example validation

The executable implementations live in marked blocks in the [SDE](../../12-Interview-Question-Bank/SDE_Questions.md), [ML engineering](../../12-Interview-Question-Bank/ML_Engineer_Questions.md) and [AI/ML foundations](../../12-Interview-Question-Bank/AI_ML_Questions.md) banks. The runner extracts and executes those exact blocks, so an unrelated implementation cannot supply a passing result for changed published code.

From repository root:

```sh
python3 -B interview/validation/questions-core/check_examples.py
```

Requirements: Python with NumPy, `javac` and `java` on PATH. No framework installation, model/provider invocation or network access is needed. Java source/classes are created in a temporary directory outside the repository and removed by the runner.

## Checks and recorded result

Reverified on **2026-11-03** with Python **3.12.0**, NumPy **2.4.6**, `javac 26` / Java **26**, compiling the published Java block with **`--release 17`**. This verifies Java 17 source/API compatibility under that compiler; it does not execute a Java 17 VM.

```text
Java LRU contract: 64894 checks passed
Python/NumPy question examples: 1493 checks passed
Published-code extraction: 3 blocks executed; no compiled artifacts retained in repository
```

The **66,387 assertions** include:

- LRU negative/zero capacity, valid negative/extreme integer values, misses, overwrite/hit recency and detached snapshot behavior. A seeded independent HashMap plus linear list model checks **26,000 operations** across capacities 0–12, including order and capacity after every operation. Single-threaded only.
- Threshold selection ties, boundaries, predict-none, zero costs and invalid/numeric-range inputs. **700 seeded cases** compare with an oracle enumerating every binary prediction mask, retaining only masks a threshold can express. Seeded integer costs avoid ambiguity from rounding near-equal totals; three additional float16/float32/int64 cost counterexamples verify normalization to Python float and a finite optimal result. The published float tie policy remains explicit.
- Regression real-type/shape/finite/overflow validation, including complex arrays in each argument, booleans, numeric strings and malformed arrays; **150 seeded matrix cases** compare analytic loss/gradient with scalar loops and each gradient component with central differences. Floating comparisons use stated tolerances on small well-scaled examples.
- Seven independently computed worked numbers: projection, Bayes, action cost threshold, stable cross-entropy, Bellman backup, LoRA parameter count and SVD retained energy.

## Proof boundary

The runner validates these three published blocks and included numerical examples under the recorded environment. It does not execute every conceptual answer, explore all interleavings, prove universal numerical stability, train a model, run scikit-learn or validate public-platform hidden tests. Public catalog algorithm directions are reviewed analysis, not submitted/executed solutions. The regression calculation rejects non-finite intermediates but finite arithmetic may still have ordinary rounding/cancellation error.

[Runner](check_examples.py), [Java test model](LruContractTest.java), [source register](../../12-Interview-Question-Bank/PHASE5_SDE_ML_SOURCES.md).
