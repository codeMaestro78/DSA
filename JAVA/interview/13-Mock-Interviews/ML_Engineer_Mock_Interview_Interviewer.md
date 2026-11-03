# ML engineer mock — interviewer and reference solution

**Spoilers: interviewer/reviewer material.** Open the [candidate packet](ML_Engineer_Mock_Interview.md) first. This companion supplies original [INFERRED] reference reasoning for **MOCK-ML-01**, not a required employer answer. Accept alternatives that preserve the contract and justify their tradeoffs.

## Interviewer instructions

Read the candidate contract before the session. Preflight/runtime setup and a 10–15 minute debrief are outside the 75-minute duration. Follow the four stage budgets (15 + 25 + 20 + 15 minutes); announce transitions without turning a slower answer into a personal judgment. Do not supply the solution at the first pause. Ask one neutral question, then use the smallest useful hint if requested. Stop optional depth when core correctness needs time.

Use **H0** for clarification of the stated contract; it does not reduce independence. Log **H1** (focus attention), **H2** (suggest a representation/mechanism), and **H3** (give a key step). Record when and how a hint changed the attempt; do not apply an automatic score deduction to every dimension. The candidate may make a different valid design choice. Keep the final artifacts, counterexamples and actual statements as scoring evidence.

## Task review and expected solutions

### ML-T1 — Define the prediction and evaluation unit

**Checkpoint, minute 15:** Ask which deployment population the candidate is measuring. H0 answers: prediction occurs at the Monday decision timestamp; labels require a complete seven-day horizon; use only feature values available to the serving path at that timestamp. The recomputed-today health field needs an as-of history or exclusion, not an assumption that its old business date makes it available historically.

**Expected solution:** For future weeks of existing customers, use chronologically ordered training/development/final periods, mature labels, and fit transforms only on training data. Ensure training outcome windows do not overlap the final decision boundary in a way that leaks unavailable labels; explain the horizon/availability cutoff. For unseen customers, construct a separate group-held-out assessment with no customer overlap, also respecting the future deployment question when feasible. A random row split does not answer either question reliably by itself. Forty customers limit generalization and uncertainty estimates, especially subgroup/new-customer inference; many rows from the same customer are not many independent customers. A chronological group-held-out test may be small, so report coverage and uncertainty rather than inventing precision. [Grouped and temporal evaluation guidance](https://scikit-learn.org/stable/modules/cross_validation.html) supports these mechanisms, not a universal split percentage.

**Hints:** H1: “Is the next week from an existing customer the same target as a new customer?” H2: separate temporal holdout and customer holdout. H3: suggest an as-of availability join and a distinct unseen-customer slice. **Branches:** if fast, ask how new-customer contract tiers absent from training are represented; if stuck, sketch one Monday and its following seven days. **Common mistakes:** treating incomplete labels as zero; fitting preprocessing globally; claiming group split alone tests future shift. **Strong indicators:** the candidate states the estimand (what population/quantity the evaluation measures) before choosing a splitter.

### ML-T2 — Implement a customer-weighted error report

**Checkpoint, minute 28:** Ask for one customer result and the final aggregation. **Minute 40:** require a test and numeric/shape boundary even if optimization remains. The fixture gives A errors [0,0,0,4], mean1; B error6, mean6; macro mean3.5. Ordinary row MAE is (4+6)/5=2. They answer different weighting questions. Equal customer weighting is stipulated here, not a universal fairness or business-optimal metric.

<!-- phase6-code: customer_mae.py -->
```python
import math
import numpy as np


def customer_mae(y_true, y_pred, customers):
    try:
        true_raw, pred_raw = np.asarray(y_true), np.asarray(y_pred)
        if true_raw.dtype.kind not in "iuf" or pred_raw.dtype.kind not in "iuf":
            raise ValueError("real numeric values required")
        true = true_raw.astype(np.float64)
        pred = pred_raw.astype(np.float64)
        names = list(customers)
    except (TypeError, ValueError, OverflowError) as error:
        raise ValueError("numeric sequences and customer sequence required") from error
    if true.ndim != 1 or pred.shape != true.shape or not 0 < true.size <= 1_000_000:
        raise ValueError("equal nonempty one-dimensional numeric sequences required")
    if len(names) != true.size or any(type(name) is not str or not name for name in names):
        raise ValueError("one nonempty string customer per row required")
    if not (np.isfinite(true).all() and np.isfinite(pred).all()):
        raise ValueError("finite values required")
    if np.any(np.abs(true) > 1e6) or np.any(np.abs(pred) > 1e6):
        raise ValueError("values outside teaching numeric bound")
    buckets = {}
    for actual, estimate, name in zip(true, pred, names):
        buckets.setdefault(name, []).append(abs(float(actual) - float(estimate)))
    per_customer = {name: math.fsum(buckets[name]) / len(buckets[name])
                    for name in sorted(buckets)}
    macro_mae = math.fsum(per_customer.values()) / len(per_customer)
    return per_customer, macro_mae
```

**Correctness and costs:** Each row contributes exactly one absolute error to its named customer's bucket. Dividing each sum by that bucket's count computes its MAE; the final mean assigns each present customer one equal weight. No missing customer is fabricated. Bound inputs keep total magnitudes far below float overflow; ordinary floating rounding remains. `math.fsum` improves summation accuracy but is not exact real arithmetic. With n rows/g distinct bounded-length names, expected O(n+g log g) time (including key sorting) and O(n+g) additional storage, including converted numeric arrays and stored errors. Variable-length string comparisons/hashing add their own costs. A running compensated aggregate per customer can reduce bucket storage to O(g), while converted arrays may still occupy O(n); explain rather than claiming O(g) for this implementation. [MAE definition](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.mean_absolute_error.html) supports the base error measure, not this original customer-weighting choice.

**Alternatives:** A pandas/groupby implementation or streaming per-customer totals is acceptable if the same numeric/shape contract, stable key order and weighting are met. A row mean is not a substitute. Tests should include a single customer, equal errors, permutation, a duplicated row within one customer, nonfinite inputs, mismatched lengths and empty input. Duplicating a row does not universally leave that customer's mean unchanged; duplicating all of that customer's rows does.

**Hints:** H1: “Which denominator belongs to which customer?” H2: use per-customer sum/count or buckets, then another mean. H3: show A's denominator 4 and B's denominator 1; require the candidate to finish the macro aggregation. **Branches:** if fast, ask for a streaming representation and its summation tradeoff; if stuck, implement the two-customer fixture transparently. **Common mistakes:** dividing all errors by group count; averaging only large customers; silently dropping NaNs; assuming sorting is free. **Strong indicators:** an independent oracle or hand calculation and input-mutation check.

**Validation:** The exact block is extracted and executed against separate scalar/Fraction oracles on bounded fixtures. No scikit-learn training/runtime, model calibration or population-quality claim follows; see [the fixture record](../validation/mocks-core/README.md).

### ML-T3 — Investigate a release claim

**Checkpoint, minute 60:** Require both averages before discussing deployment. Old row and macro MAE are1. New row MAE=(90×0.5+10×4)/100=0.85, while equal-customer MAE=(0.5+4)/2=2.25. The dashboard's number is mathematically correct for its row weighting; its unqualified “better” claim is incomplete. Decide which deployment objective is intended and expose affected customer slices. Neither average alone establishes acceptable expensive-error behavior or benefit to unseen customers.

**Expected recommendation:** Do not make a universal release decision from two stipulated group means. Check representative future/customer coverage, label maturity, within-customer dependence, error tails/costs and valid uncertainty. Compare matched rows and customer-level outcomes; a resampling procedure must respect its sampling unit and cannot conjure 40 independent customers out of many repeated rows. Define acceptance/stop criteria before examining more variants, verify compatible feature transformations/model artifacts, shadow for computation checks, and use a controlled rollout only when justified. Retain the compatible prior bundle and a rollback owner; shadow prediction alone is not causal evidence of customer benefit. A legitimate row-volume objective may prefer the new model but still needs a declared customer-impact policy.

**Hints:** H1: compute separately for A and B. H2: identify row versus customer as the averaging unit. H3: provide the row formula and ask the candidate to derive the macro formula. **Branches:** if fast, ask about groups with one observed row or changing traffic mix; if stuck, list the 100 errors conceptually. **Common mistakes:** declaring macro universally fair; reporting only the winning aggregate; treating repeated weekly rows as independent replicates. **Strong indicators:** a reasoned objective and a next check that could reverse the recommendation.

### ML-T4 — Communicate a bounded recommendation

**Checkpoint, minute 72:** Leave the last three minutes for a concise recommendation and candidate question. A strong update states that aggregate improvement and customer-level regression coexist, explains the weighting in plain language, identifies the harmed slice without claiming unsupported causality, and names the acceptance decision owner and next evidence. A real feedback example needs actual personal action and learning; the hypothetical mock reflection supplies communication evidence only.

**Hints:** H1: “What would you tell the colleague first?” H2: propose decision → evidence → gap → next check as a communication structure. **Branches:** if fast, ask what fact would change their recommendation; if stuck, ask for one sentence distinguishing row and customer averages. **Common mistakes:** jargon without the decision, fabricated deployed impact, declaring successful unit tests a successful model. **Strong indicators:** uncertainty linked to a concrete check, not vague hedging.

## Task-specific scorecard

Use [the shared rubric](Interview_Evaluation_Rubric.md), version/date and score scale. Enter **Not assessed** when a dimension was not observed. Level 3 below requires independent evidence; 0–2 depends on errors and help, while 4 requires repeated strength across variations/attempts, not one polished answer. Keep critical defects explicit rather than averaging them away.

| Dimension | Observable evidence for level 3 | Critical blocker / next exercise |
|---|---|---|
| CONTRACT | ML-T1 defines initial future-existing-customer and separate unseen-customer targets; T2 states weighting | Random split claimed universally valid; redraw the deployment timeline |
| REASONING | T1 links availability/maturity to split; T2 derives nested aggregation; T3 explains metric reversal | Metric selected after result without objective; derive both denominators |
| CORRECTNESS | T2 computes per-customer and macro values correctly; T3 obtains 0.85 versus2.25 | Row-weighted answer substituted for macro; fix a two-group counterexample |
| VALIDATION | T2 independent oracle/boundary/mutation tests; T3 distinguishes code tests and model evidence | Claims quality from helper tests; specify a held-out customer/time assessment |
| RISK | T3 covers impacted slices, leakage, compatibility, uncertainty and rollback scope | Unqualified release claim or unsupported fairness guarantee; write an explicit acceptance contract |
| COMMUNICATION | T4 explains a decision, actual evidence and next check clearly; attributes real experience honestly | Invented deployment results or opaque recommendation; deliver a three-minute evidence-bounded update |

For the actual attempt, record `dimension; score/Not assessed; task and artifact; hint history; unresolved error; next exercise`. Do not prefill candidate scores or infer an offer. A bounded hypothetical reflection can demonstrate reasoning/communication, but cannot demonstrate a real past accomplishment or production ownership.

## Debrief and reattempt

Ask the candidate to identify one corrected belief, one still-open limitation and a small next experiment. Choose a changed fixture or assumption for the reattempt; repeating the same memorized answer gives weaker transfer evidence. Use the [retrospective template](../14-Interview-Preparation-Templates/Interview_Retrospective_Template.md) and [practice ledger](../PROGRESS_TRACKER.md).

## Sources and verification boundary

[Primary source register](PHASE6_CORE_SOURCES.md) scopes mechanisms separately from these fictional scenarios and original scoring anchors. [Executable fixture record](../validation/mocks-core/README.md) identifies exact extracted code and observed tests. Conceptual discussion, interview behavior, deployment and model outcomes are not established by those tests.

[Candidate packet](ML_Engineer_Mock_Interview.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
