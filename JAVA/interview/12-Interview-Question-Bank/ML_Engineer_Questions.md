# ML Engineer Questions

Phase 5: 10 original, company-neutral [INFERRED] exercises. Difficulty, role and timing are editorial judgments, not employer evidence. Target early-career candidates (0–2 years); Advanced exercises are stretch practice and Expert is optional. Attempt each prompt before opening the review. Score contract, mechanism, worked reasoning and limitations separately; naming a tool alone is insufficient. Sources support technical mechanisms, not these invented scenarios or interview frequency.

[Source metadata](PHASE5_SDE_ML_SOURCES.md). Code validation and commands: [questions-core](../validation/questions-core/README.md).

<a id="qb-ml-001"></a>

## QB-ML-001 — A future feature leaks into training

| Field | Value |
|---|---|
| Category | Point-in-time data |
| Difficulty | Beginner |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML case discussion |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Point-in-time data: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-002 |
| Sources | [scikit-learn common pitfalls](https://scikit-learn.org/stable/common_pitfalls.html), [scikit-learn cross-validation](https://scikit-learn.org/stable/modules/cross_validation.html) |

### Prompt

Predict whether a support ticket escalates within seven days of creation. The dataset includes resolution text, final owner and a customer history table recomputed today. Define a valid feature join and evaluation split.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Choose the decision timestamp first: ticket creation. Resolution text and final owner are unavailable then, so exclude them unless the feature is explicitly a historical value already known at creation. Join customer history as of that timestamp, using only events actually available to the serving system then. Event time alone is insufficient when ingestion is delayed. Define the seven-day label window and do not include recent tickets whose outcome is still censored.

**Explanation:** A temporal holdout models future deployment more closely than a random ticket split when behavior changes over time. If repeat customers create dependence, also inspect group overlap and report both returning- and new-customer slices. Fit imputation, vocabulary and scaling on training folds only, then reuse the fitted transforms. A field that predicts perfectly may be a post-outcome artifact rather than signal. Check raw examples against source timestamps before celebrating the score. This split is part of the prediction contract, not a universally correct split for every task.

**Followups:** What embargo prevents overlapping label windows? How would you reconstruct late-arriving events?

**Common mistakes:** Using all historical rows regardless of availability; fitting preprocessing before splitting; labeling unresolved tickets negative.

</details>

<a id="qb-ml-002"></a>

## QB-ML-002 — A ranking score hides a poor decision

| Field | Value |
|---|---|
| Category | Threshold and business costs |
| Difficulty | Intermediate |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML applied reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Threshold and business costs: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-003 |
| Sources | [scikit-learn threshold tuning](https://scikit-learn.org/stable/modules/classification_threshold.html), [scikit-learn calibration](https://scikit-learn.org/stable/modules/calibration.html) |

### Prompt

A calibrated binary risk model yields p=0.08. A false positive costs 2 units and a false negative costs 30; correct decisions cost zero. Derive the cost-minimizing action and explain how a daily review cap changes the rule.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Predict positive when expected positive cost 2(1-p) is less than expected negative cost 30p. The crossing is p=2/32=0.0625, so p=0.08 should be positive under these assumptions. At that probability the costs are 1.84 versus 2.40. A default threshold of 0.5 is not justified by these asymmetric costs. The derivation assumes probabilities are calibrated for the deployment population and that those costs describe the actual action.

**Explanation:** With a fixed review cap, rank by expected incremental benefit, 30p-2(1-p), and select positive-benefit cases within capacity. Equal costs across cases make this equivalent to descending p; heterogeneous costs can break that equivalence. Tune operational choices on held-out development data, freeze them, and assess on untouched evaluation data. Include throughput and per-slice false-negative burden, not just aggregate accuracy. If review changes outcomes, the labels observed after deployment may differ from the original untreated risk and need a separate evaluation design.

**Followups:** What if calibration shifts? What if manual review detects positives only 80% of the time?

**Common mistakes:** Optimizing accuracy by default; selecting the threshold on test labels; confusing ranking quality with calibration.

</details>

<a id="qb-ml-003"></a>

## QB-ML-003 — Choose a threshold with an exact tie rule

| Field | Value |
|---|---|
| Category | Practical ML coding |
| Difficulty | Intermediate |
| Target roles | ML Engineer; MLOps Engineer |
| Round | Python coding |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Practical ML coding: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-004 |
| Sources | [scikit-learn threshold tuning](https://scikit-learn.org/stable/modules/classification_threshold.html) |

### Prompt

Implement choose_threshold(y,p,fp_cost,fn_cost). Inputs are nonempty finite one-dimensional numeric sequences, binary y, p in [0,1], and finite nonnegative costs, each at most 1e300/len(y). Use Python int/float values or equivalent NumPy real scalars; strings, complex values and nested arrays are outside this exercise contract. Predict positive iff p≥t. Minimize empirical total cost over distinct p and +infinity (predict none), choosing the largest t on ties. This is validation-data selection, not final test evaluation.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Process probabilities from highest to lowest, including all tied scores together. Initially t=+infinity predicts no positives, so all positive labels incur false-negative cost. Moving a tied group to positive adds false-positive cost for its negatives and removes false-negative cost for its positives. Evaluate after the complete group; splitting ties would describe an impossible threshold. Keep the larger threshold when cost ties. The teaching implementation below instead enumerates candidates transparently for easier review.

**Explanation:** For y=[0,1] and p=[0.8,0.8], possible predictions are neither positive or both positive; predicting just the second row is not expressible by a shared threshold. Candidate enumeration costs O(n²) time and O(n) peak temporary space; an ordered grouped sweep improves to O(n log n) time with O(n) storage. Costs are normalized to Python float before arithmetic: retaining a NumPy float16/float32 or fixed-width integer scalar could overflow its narrower dtype even under the float64 teaching bound. Exact comparison of the resulting Python floats defines ties, so near-equal rounded totals are not treated as mathematical ties. Extremely large costs that could overflow the total are rejected by the published input bound.

**Followups:** Implement the grouped sweep and compare against this oracle. How does a capacity cap handle ties?

**Common mistakes:** Using p>t instead of p≥t; ignoring the predict-none candidate; testing each tied row as a separate threshold.

**Validation:** The exact Python block is executed from this document and compared against independently enumerated label masks on seeded inputs; no scikit-learn runtime is used.

<!-- phase5-code: threshold.py -->
```python
import math
from numbers import Real

def choose_threshold(y, p, fp_cost, fn_cost):
    y, p = list(y), list(p)
    if not y or len(y) != len(p):
        raise ValueError("nonempty equal lengths required")
    if any(v not in (0, 1) for v in y):
        raise ValueError("binary labels required")
    if any(not 0 <= v <= 1 or not math.isfinite(v) for v in p):
        raise ValueError("probabilities must be finite in [0,1]")
    if any(not isinstance(v, Real) for v in (fp_cost, fn_cost)):
        raise ValueError("real numeric costs required")
    try:
        fp_cost, fn_cost = float(fp_cost), float(fn_cost)
    except (TypeError, ValueError, OverflowError) as error:
        raise ValueError("costs must be representable Python floats") from error
    if any(v < 0 or v > 1e300 / len(y) or not math.isfinite(v) for v in (fp_cost, fn_cost)):
        raise ValueError("costs must be finite, nonnegative and within the teaching bound")
    candidates = sorted(set(p), reverse=True)
    best_t, best_cost = math.inf, sum(y) * fn_cost
    for t in candidates:
        fp = sum(label == 0 and score >= t for label, score in zip(y, p))
        fn = sum(label == 1 and score < t for label, score in zip(y, p))
        cost = fp * fp_cost + fn * fn_cost
        if cost < best_cost:
            best_t, best_cost = t, cost
    return best_t, best_cost
```

</details>

<a id="qb-ml-004"></a>

## QB-ML-004 — An offline win fails after deployment

| Field | Value |
|---|---|
| Category | Training-serving consistency |
| Difficulty | Intermediate |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML debugging |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Training-serving consistency: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-005 |
| Sources | [Google Rules of ML](https://developers.google.com/machine-learning/guides/rules-of-ml), [Hidden Technical Debt paper](https://proceedings.nips.cc/paper/2015/file/86df7dcfd896fcaf2674f757a2463eba-Paper.pdf) |

### Prompt

A churn model improves offline ROC-AUC from 0.78 to 0.84 but online performance drops. Its service computes account age in hours; training computed days. Outline the first investigation and a release contract.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Reproduce predictions for the same raw records in training and serving, comparing each transformed feature before comparing final scores. An account aged 10 days should not become 240 in an identically named feature. Version the schema, units, missing-value rules and transform artifact together with model weights. Add golden records that run through both feature paths. Fix the unit mismatch, re-evaluate the same frozen dataset, and use shadow/canary evidence before claiming the offline gain transfers.

**Explanation:** Do not stop at the obvious unit bug: investigate traffic composition, stale joins, label timing, fallback behavior and the chosen action threshold. Shadow inference compares computation without changing decisions; it does not establish user benefit. An offline ROC-AUC change also needs uncertainty and slice analysis and may not improve the actual decision cost. Release a compatible feature/model bundle atomically, with a previous working bundle available for rollback. Monitoring feature ranges would likely flag the factor-of-24 shift earlier, but a range alarm is evidence to investigate rather than automatic proof of cause.

**Followups:** How would a timezone error evade unit checks? What if the old transform is unavailable at rollback?

**Common mistakes:** Deploying weights without preprocessing; treating shadow mode as an online causal experiment; only checking column names.

</details>

<a id="qb-ml-005"></a>

## QB-ML-005 — An online experiment needs an estimand

| Field | Value |
|---|---|
| Category | Experiment design |
| Difficulty | Advanced |
| Target roles | ML Engineer; MLOps Engineer |
| Round | Experiment discussion |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Experiment design: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-006 |
| Sources | [Controlled experiments survey](https://www.exp-platform.com/Documents/controlledExperimentDMKD.pdf) |

### Prompt

Two recommendation models are assigned randomly by user. Treatment users make 20% more sessions. The team compares click rate per observed session and declares user engagement improved. Explain the estimand problem and design a review.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Randomization is by user, but the metric conditions on sessions, whose number treatment changes. A per-session ratio measures a different quantity from engagement per assigned user. Predefine a user-level estimand, such as clicks per randomized eligible user over a fixed window, including users with zero sessions. Preserve assignment for returning users and analyze at the randomization unit or account for within-user dependence. Report session counts and ratio components alongside the chosen primary metric.

**Explanation:** For example, treatment may create many low-click sessions while increasing total clicks per user; the ratio can decrease despite the intended benefit. Conversely a ratio can improve by removing low-engagement sessions. Neither direction alone establishes the product objective. Check allocation integrity, missing logs, eligibility and interference before interpreting a lift. Plan sample size, minimum detectable effect, stopping rules and guardrail metrics before looking at results. Randomization supports causal interpretation under its design assumptions; it does not excuse biased exclusions or guarantee that a statistically detectable change is worthwhile.

**Followups:** What if users share recommendations across arms? How would repeated peeking alter error control?

**Common mistakes:** Counting sessions as independent randomized units; excluding zero-activity users after assignment; equating significance with practical value.

</details>

<a id="qb-ml-006"></a>

## QB-ML-006 — Accuracy conceals a failed minority slice

| Field | Value |
|---|---|
| Category | Evaluation and reporting |
| Difficulty | Beginner |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML evaluation |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Evaluation and reporting: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-007 |
| Sources | [scikit-learn evaluation](https://scikit-learn.org/stable/modules/model_evaluation.html), [Model Cards (Google Research)](https://research.google/pubs/model-cards-for-model-reporting/) |

### Prompt

A classifier has 99% accuracy on 10,000 rows, of which only 100 are positive. Show a trivial model achieving this, then specify a useful evaluation report when missing positives is costly.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Always predicting negative is correct on 9,900 rows, giving 99% accuracy, with recall zero for positives. Its apparent success says nothing about detecting the minority class. Report the confusion matrix, precision, recall, and a decision-cost or constraint-based metric at a stated threshold. Precision for no predicted positives is undefined mathematically; document the reporting convention instead of silently treating it as an excellent score. Use a no-skill baseline and retain class counts.

**Explanation:** Inspect precision-recall tradeoffs across thresholds and confidence intervals or appropriate resampling at the independent unit. Report relevant subgroups with their sample sizes; a high aggregate score can hide a small subgroup failure. If probabilities drive actions, examine calibration separately from discrimination. Keep the final test set untouched by feature/threshold selection and document its population/time range. A model card should state intended use, excluded uses and known weak slices. One hundred positives may yield considerable uncertainty for per-slice recall, even with many total rows.

**Followups:** What happens to precision if prevalence changes but conditional error rates do not? Which cases should a reviewer inspect first?

**Common mistakes:** Only reporting accuracy; omitting denominators; presenting a noisy small-slice point estimate as certainty.

</details>

<a id="qb-ml-007"></a>

## QB-ML-007 — Delayed labels complicate drift alarms

| Field | Value |
|---|---|
| Category | Monitoring and retraining |
| Difficulty | Advanced |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML operations |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Monitoring and retraining: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-008 |
| Sources | [ML Test Score (Google Research)](https://research.google/pubs/the-ml-test-score-a-rubric-for-ml-production-readiness-and-technical-debt-reduction/), [Hidden Technical Debt paper](https://proceedings.nips.cc/paper/2015/file/86df7dcfd896fcaf2674f757a2463eba-Paper.pdf) |

### Prompt

A fraud system receives chargeback labels after 30 days. This morning feature distributions shift and predicted fraud doubles. Decide what can be concluded now and when retraining is justified.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The system shows input/prediction change, not yet a measured accuracy drop. Validate schema, instrumentation, traffic mix, missing values and upstream incidents immediately. Compare stable slices and known reference data. A prediction shift may reflect a real change in risk, a pipeline bug or altered population composition. Use service-health guardrails and a documented fallback if exposure is unacceptable, while preserving evidence for diagnosis. Do not retrain automatically on labels that have not matured.

**Explanation:** Build cohort-based evaluation by prediction date and label maturity, tracking missing/censored outcomes. When labels arrive, measure decision performance and calibration using a consistent horizon. Retraining requires a tested dataset, a plausible improvement mechanism and evaluation against the current model; it can amplify a broken feed. Account for selective labels: blocked transactions may never produce comparable chargebacks. Store model/feature versions so later errors can be attributed to the decision-time pipeline. Monitoring thresholds should have an owner and an action, because frequent unactionable alarms quickly become ignored.

**Followups:** How would a logging outage resemble drift? What data would a safe rollback preserve?

**Common mistakes:** Equating covariate drift with concept drift; training on incomplete outcomes; comparing cohorts with different observation windows.

</details>

<a id="qb-ml-008"></a>

## QB-ML-008 — Package a reproducible model release

| Field | Value |
|---|---|
| Category | Artifacts and rollout |
| Difficulty | Intermediate |
| Target roles | ML Engineer; MLOps Engineer |
| Round | ML deployment |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Artifacts and rollout: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-009 |
| Sources | [ML Test Score (Google Research)](https://research.google/pubs/the-ml-test-score-a-rubric-for-ml-production-readiness-and-technical-debt-reduction/), [Model Cards (Google Research)](https://research.google/pubs/model-cards-for-model-reporting/) |

### Prompt

List the minimal release manifest for a tabular classifier and propose a staging gate. A random seed is available; no GPU training is required for this question. Explain what reproducibility can and cannot promise.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Pin the code revision, dependency environment, immutable data/version pointers, feature schema and transform artifact, fitted model, label definition, split IDs, hyperparameters, thresholds and evaluation report. Record seeds and relevant hardware/runtime details where numeric behavior matters. The release ID should resolve to the whole compatible bundle. Staging checks should verify input contracts, golden-record predictions, finite outputs, latency/resource bounds under representative load and rollback compatibility.

**Explanation:** A seed controls only some randomness; different parallel reductions or libraries may still differ. Specify whether the goal is bitwise identity, numerically close predictions or statistically comparable metrics. A manifest supports replay only if referenced artifacts remain available and access is controlled. Keep evaluation data separate from tuning decisions and record any exceptions. Roll out gradually with model-specific and service guardrails and a concrete stop condition. A passed staging gate is evidence about the tested environment and traffic assumptions, not proof of future quality across every population.

**Followups:** How do you revoke a release that used disallowed data? What if feature schema migration breaks the previous bundle?

**Common mistakes:** Saving only weights; calling a seed full reproducibility; claiming local throughput is a deployment SLO.

</details>

<a id="qb-ml-009"></a>

## QB-ML-009 — Batching trades waiting for throughput

| Field | Value |
|---|---|
| Category | Inference performance |
| Difficulty | Advanced |
| Target roles | ML Engineer; MLOps Engineer |
| Round | Performance case |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Inference performance: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-010 |
| Sources | [ML Test Score (Google Research)](https://research.google/pubs/the-ml-test-score-a-rubric-for-ml-production-readiness-and-technical-debt-reduction/) |

### Prompt

An accelerator takes 8 ms for batch 1 and 20 ms for batch 16, excluding queue/transport time. The request SLO is p95<80 ms. Derive ideal batch throughput and explain why those measurements cannot choose a batching timeout alone.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Batch-1 ideal throughput is 1/0.008=125 items/s; batch-16 throughput is 16/0.020=800 items/s. These are isolated compute figures, not end-to-end service capacity guarantees. Requests also wait for a batch, queue behind other batches, cross transport and execute preprocessing/postprocessing. A batching timeout must fit the residual deadline under the observed arrival distribution and concurrency limits. Low arrival rate can make waiting to fill 16 dominate latency.

**Explanation:** Instrument queue time, batch formation delay, compute time and response time separately. Benchmark distributions under realistic input sizes and bursts, not just mean latency. A 20 ms compute run leaves less than 60 ms for all other p95 components, but adding independent p95s is not generally the p95 of their sum; measure the joint end-to-end distribution. Bound pending work and reject expired requests. Compare microbatch limits, dynamic batching and separate lanes for large inputs. Quality-affecting optimizations such as quantization require a new task evaluation as well as latency measurement.

**Followups:** What if one large input pads an entire batch? How do cold starts affect the benchmark?

**Common mistakes:** Comparing batch latency directly with per-item throughput; assuming 800 requests/s at every workload; ignoring queue growth.

</details>

<a id="qb-ml-010"></a>

## QB-ML-010 — A feedback loop invalidates naive replay

| Field | Value |
|---|---|
| Category | Selective labels and causal limits |
| Difficulty | Expert |
| Target roles | ML Engineer; MLOps Engineer |
| Round | Optional ML systems deep dive |
| Experience | 0–2 years; optional stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Selective labels and causal limits: correct mechanism and explicit assumptions |
| Related IDs | QB-ML-001 |
| Sources | [Counterfactual Risk Minimization](https://proceedings.mlr.press/v37/swaminathan15.html), [Hidden Technical Debt paper](https://proceedings.nips.cc/paper/2015/file/86df7dcfd896fcaf2674f757a2463eba-Paper.pdf) |

### Prompt

A recommender trains only on clicked items that its previous model displayed. An offline candidate appears better on those logs. Explain the support problem, what propensity logging can enable, and one reason inverse propensity weighting can still fail.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The log represents outcomes under the old display policy, not outcomes for arbitrary candidates. Unshown items have missing outcomes, not negative labels. To evaluate an alternative policy by inverse propensity weighting, logged actions need known selection probabilities and nonzero probability for actions the new policy would take. Also require consistent outcome definitions and appropriate assumptions about confounding and interference. A deterministic old policy may provide no support for much of the candidate policy.

**Explanation:** If an action appears with probability 0.001, its inverse weight is 1,000, creating high variance and sensitivity to bad propensity estimates. Clipping stabilizes variance at the cost of bias and must be reported. Logged propensity must describe the real randomized decision, including eligibility/filtering, not a reconstructed softmax unrelated to serving. A safely designed randomized exploration or online experiment may be needed. Off-policy estimates are evidence under assumptions; a high offline score cannot identify outcomes of never-observed actions. Monitor longer-term user effects that a short click label does not capture.

**Followups:** What changes for a ranked slate rather than one action? How would you diagnose poor effective sample size?

**Common mistakes:** Treating unexposed items as dislikes; using unknown/zero propensities; claiming reweighting removes all causal uncertainty.

</details>
