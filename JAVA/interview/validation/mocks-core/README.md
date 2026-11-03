# Phase 6 core mock reference validation

Run from the workspace root:

```bash
python3 -B interview/validation/mocks-core/check_examples.py
```

Requires Python with NumPy, `javac` and `java` on PATH. The runner extracts the **three exact marked reference blocks** from the SDE, ML and frontier research-engineering interviewer companions. Those files and this runner contain spoilers. Java compilation uses a temporary directory outside the repository, removed on completion; existing Java solutions are never imported or edited.

## Observed result

**2026-11-03: PASS**, Python 3.12.0, NumPy 2.4.6, compiler/JVM 26 with `javac --release 17`:

```text
Java recent-user window: 20822 checks passed (20,000 seeded primary operations)
Python/NumPy mock references: 3573 checks passed
Published-code extraction: 3 blocks executed; no service/model/hidden-test claim
```

**24,395 checks** cover open-left window expiration, equal/large timestamps, invalid-call state preservation and an independent scan-based Java oracle; equal-customer MAE against scalar/Fraction oracles on 400 seeded cases, stable order and permutation; masked token NLL against a scalar stable oracle on 100 seeded fixtures, target/mask/shape/numeric boundaries, input preservation, common-shift invariance and finite-difference derivatives. Additional rejection checks prevent silent complex-to-real conversion and normalize malformed conversion failures.

Java17 source/API compatibility was checked by the installed Java26 compiler; no Java17 VM was used. NumPy helpers do not execute scikit-learn, SciPy, PyTorch, training, GPU code or distributed reduction. Small well-scaled finite-difference checks are not proof of universal floating-point accuracy. A local single-threaded implementation does not establish a durable multi-worker service.

[Runner](check_examples.py) · [Java test model](RecentUsersTest.java) · [Core source scope](../../13-Mock-Interviews/PHASE6_CORE_SOURCES.md) · [Validation directory](../README.md).
