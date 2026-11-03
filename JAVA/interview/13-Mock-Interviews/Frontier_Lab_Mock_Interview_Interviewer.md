# Frontier research-engineering mock — interviewer and reference solution

**Spoilers: interviewer/reviewer material.** Open the [candidate packet](Frontier_Lab_Mock_Interview.md) first. This companion supplies original [INFERRED] reference reasoning for **MOCK-LAB-01**, not a required employer answer. Accept alternatives that preserve the contract and justify their tradeoffs.

## Interviewer instructions

Read the candidate contract before the session. Preflight/runtime setup and a 10–15 minute debrief are outside the 75-minute duration. Follow the four stage budgets (15 + 25 + 20 + 15 minutes); announce transitions without turning a slower answer into a personal judgment. Do not supply the solution at the first pause. Ask one neutral question, then use the smallest useful hint if requested. Stop optional depth when core correctness needs time.

Use **H0** for clarification of the stated contract; it does not reduce independence. Log **H1** (focus attention), **H2** (suggest a representation/mechanism), and **H3** (give a key step). Record when and how a hint changed the attempt; do not apply an automatic score deduction to every dimension. The candidate may make a different valid design choice. Keep the final artifacts, counterexamples and actual statements as scoring evidence.

## Task review and expected solutions

### LAB-T1 — Interpret the evidence

**Checkpoint, minute 15:** Ask, “Did the target quantity stay the same?” The two loss values use different weighting and potentially different evaluation rows, so their subtraction does not isolate model improvement. Define a frozen evaluation population, valid-token mask and one reduction/normalization before comparing matched checkpoints or optimizer variants. Separate the claim “we changed the objective we report” from “predictions improved on the same objective.”

**Expected experiment frame:** Hypothesis: under fixed data/tokenization, evaluation reduction and training budget, optimizer variant B reduces the declared validation objective compared with A. Record data IDs/version/split, tokenizer and preprocessing, masks/targets, loss reduction, model/config, seed policy, code revision, optimizer/scheduler settings and resource budget. A budget may mean tokens, updates or wall time; these are different comparisons, so choose the one answering the research question and report the others. Keeping a seed alone does not establish full determinism. Empty-sequence removal changes the population and may need a documented exclusion rule.

**Hints:** H1: “What is one row contributing to each reported average?” H2: freeze population and reduction before testing the optimizer. H3: give the example of one short and one long sequence with different loss. **Branches:** if fast, ask why equal compute and equal updates answer different questions; if stuck, ask for a manifest with three fields needed to rerun evaluation. **Common mistakes:** attributing the whole 0.2 difference to the optimizer; treating a lower scalar as a reproduced research result. **Strong indicators:** a falsifiable claim and the exact confounder it excludes.

### LAB-T2 — Implement the stated numerical contract

**Checkpoint, minute 28:** Ask for shape/mask semantics and one stable per-token expression. **Minute 40:** require a boundary and a masking counterexample. For each active position with logits z and target y, NLL is `log(sum(exp(z-max(z)))) - (z[y]-max(z))`. The common shift prevents exponentiating large positive numbers. Select active positions before target gathering so masked −1 is never accidentally used as a real last-class index. Reject all-masked input because the mean denominator would be zero. This exercise has integer-class targets, no class weighting and no label smoothing; different framework options change reduction semantics.

<!-- phase6-code: masked_token_nll.py -->
```python
import numpy as np


def masked_token_nll(logits, targets, mask):
    try:
        raw = np.asarray(logits)
        if raw.dtype.kind not in "iuf":
            raise ValueError("real numeric logits required")
        z = raw.astype(np.float64)
        y = np.asarray(targets)
        keep = np.asarray(mask)
    except (TypeError, ValueError, OverflowError) as error:
        raise ValueError("array conversion failed") from error
    if z.ndim != 3 or any(size == 0 for size in z.shape) or z.shape[2] < 2 or z.size > 1_000_000:
        raise ValueError("bounded nonempty (B,T,V) logits with V >= 2 required")
    if y.shape != z.shape[:2] or y.dtype.kind not in "iu":
        raise ValueError("integer targets must have shape (B,T)")
    if keep.shape != y.shape or keep.dtype.kind != "b":
        raise ValueError("boolean mask must have shape (B,T)")
    if not np.isfinite(z).all() or np.any(np.abs(z) > 1e6):
        raise ValueError("finite bounded logits required")
    if not keep.any():
        raise ValueError("at least one active token required")
    active = z[keep]
    chosen = y[keep]
    if np.any(chosen < 0) or np.any(chosen >= z.shape[2]):
        raise ValueError("active target outside vocabulary")
    shifted = active - active.max(axis=1, keepdims=True)
    loss = np.log(np.exp(shifted).sum(axis=1)) - shifted[np.arange(len(chosen)), chosen]
    return float(loss.mean())
```

**Expected worked values:** All-zero two-class logits give log2 for each active target, so the fixture mean is log2≈0.69314718056, with the masked −1 ignored. For logits `[1000,-1000]`, active target 1 has NLL 2000; computing `log(softmax(...)[1])` naively may instead encounter log0. The shifted log-sum expression keeps the low-probability target loss finite. It does not make every floating-point operation exact, and the stated finite bounds matter. [SciPy logsumexp](https://docs.scipy.org/doc/scipy/reference/generated/scipy.special.logsumexp.html) and [PyTorch cross-entropy semantics](https://docs.pytorch.org/docs/2.15/generated/torch.nn.CrossEntropyLoss.html) support these general mechanisms; neither library is executed by this NumPy helper.

**Correctness and costs:** Boolean selection produces exactly the K active token rows. The integer target bounds ensure each gather selects its stipulated class. The stable identity computes each row's negative log softmax; the final mean divides by K, so padding contributes neither numerator nor denominator. With N=B×T×V and K active positions, validation/conversion cost O(N), active reduction O(KV); overall O(N) time and O(N+KV) additional storage as an upper bound (including potential conversion plus selected/shifted/exp temporaries). This is O(N), not O(1) memory; no autograd graph is returned. A chunked sum/count can reduce temporary memory with a matching reduction contract.

**Hints:** H1: “What happens to exp(1000)?” H2: subtract each row's maximum and gather only after mask selection. H3: supply the shifted identity and require the candidate to finish averaging/shape checks. **Branches:** if fast, derive active-logit gradient `(softmax-one_hot)/K` with zero gradient at masked positions and compare a finite difference; if stuck, implement scalar loops for the zero-logit fixture first. **Common mistakes:** dividing by B×T including padding; using integer masks as fancy indices; gathering masked −1; applying softmax twice; reporting a token mean as a sequence mean. **Strong indicators:** invariance to a common per-row shift, a masked-target perturbation check and a small scalar oracle.

**Validation:** The exact block is executed on small NumPy fixtures against a scalar stable oracle; finite differences check the expected derivative on well-scaled random logits. This is not a gradient implementation, GPU kernel check or model training benchmark; see [the fixture record](../validation/mocks-core/README.md).

### LAB-T3 — Design the smallest useful experiment

**Checkpoint, minute 60:** Require numerical reduction before infrastructure discussion. The two sequence means are1 and3; equal-sequence mean 2; token mean(1+3+3+3)/4=2.5. Either can be an intended objective, but compare like with like. Aggregate loss **sums and valid-token counts** over batches before dividing for a dataset token mean; averaging batch means with unequal valid-token counts changes weights. In distributed evaluation, reduce numerator/count consistently, accounting for any sampler padding/duplicates and failure policy. No distributed runtime is exercised here.

For an optimizer test, freeze preprocessing, split, objective/reduction and evaluation artifact; predeclare the comparison budget, stopping criterion and seed/replicate plan. Isolate the optimizer change before interpreting a combined optimizer+data change. Record optimizer moments, scheduler step, gradient-scaler state if used, step/token counters, random-generator/data-loader/sampling position and compatible configuration as applicable. Loading weights alone is generally a new warm start, not an identical optimizer-state continuation. Exact reproducibility still depends on execution/hardware/determinism and data ordering. Do not promise bitwise replay from a checkpoint file alone.

A reasonable stop condition is nonfinite loss/gradient or a predeclared unacceptable regression on the fixed evaluation contract. Preserve the last verified compatible checkpoint and manifest, halt the affected experiment, and label missing state rather than silently resuming under the same run identity. A restart may be a new branch with its own metadata. These are research-operation proposals, not an executed restore drill.

**Hints:** H1: “How many tokens contributed to each mean?” H2: keep sum/count rather than mean-of-means. H3: ask whether Adam's first update from zero moments matches continuation with existing moments. **Branches:** if fast, ask about a partially failed evaluation shard; if stuck, retain a single-process frozen fixture and versioned manifest. **Common mistakes:** comparing unequal reductions; assuming identical seed repairs omitted optimizer state; hiding failed/empty runs. **Strong indicators:** a small discriminating experiment and an explicit limit on its conclusion.

### LAB-T4 — Defend and revise the claim

**Checkpoint, minute 72:** Ask for a concise research update and a condition that would change the recommendation. Strong structure: hypothesis → matched comparison → result or missing result → confounder → next experiment. If a candidate changes their mind after finding the reduction issue, treat that as useful evidence of reasoning, not a failure of confidence. Ask whether prior work was read, implemented, reproduced or extended; do not equate a paper summary or local fixture with a reproduced training result.

**Hints:** H1: “Which observation would refute your current explanation?” H2: distinguish metric redefinition from model improvement. **Branches:** if fast, ask how to report a negative result; if stuck, ask for one precise claim the current evidence permits. **Common mistakes:** inventing research impact, confident scaling claims without evidence, hiding an unsuccessful reproduction. **Strong indicators:** an evidence-linked revision and a smaller next test that separates competing explanations.

## Task-specific scorecard

Use [the shared rubric](Interview_Evaluation_Rubric.md), version/date and score scale. Enter **Not assessed** when a dimension was not observed. Level 3 below requires independent evidence; 0–2 depends on errors and help, while 4 requires repeated strength across variations/attempts, not one polished answer. Keep critical defects explicit rather than averaging them away.

| Dimension | Observable evidence for level 3 | Critical blocker / next exercise |
|---|---|---|
| CONTRACT | LAB-T1 fixes population/reduction/budget and T2 states mask/target semantics | Compares different objectives as a model gain; write a matched evaluation contract |
| REASONING | T2 derives stable NLL; T3 distinguishes token/sequence and sum/count weighting | Formula recitation without denominator reasoning; work the [1] versus[3,3,3] fixture |
| CORRECTNESS | T2 handles active targets/padding and stable large logits; T3 derives2 versus2.5 | Known padding/index/reduction bug remains; minimize it and repair the reference check |
| VALIDATION | T2 uses independent scalar/shift/mask checks and bounded finite differences; T3 proposes matched experiments | Local helper pass called model reproduction; state exact evidence boundaries |
| RISK | T3 records state/compatibility, stop condition and restart limitations | Weights-only resume called identical continuation; list missing optimizer/data state |
| COMMUNICATION | T4 gives a falsifiable evidence-bounded update and revises a belief when warranted | Invented research accomplishment or unfalsifiable claim; give a precise permitted conclusion |

For the actual attempt, record `dimension; score/Not assessed; task and artifact; hint history; unresolved error; next exercise`. Do not prefill candidate scores or infer an offer. A bounded hypothetical reflection can demonstrate reasoning/communication, but cannot demonstrate a real past accomplishment or production ownership.

## Debrief and reattempt

Ask the candidate to identify one corrected belief, one still-open limitation and a small next experiment. Choose a changed fixture or assumption for the reattempt; repeating the same memorized answer gives weaker transfer evidence. Use the [retrospective template](../14-Interview-Preparation-Templates/Interview_Retrospective_Template.md) and [practice ledger](../PROGRESS_TRACKER.md).

## Sources and verification boundary

[Primary source register](PHASE6_CORE_SOURCES.md) scopes mechanisms separately from these fictional scenarios and original scoring anchors. [Executable fixture record](../validation/mocks-core/README.md) identifies exact extracted code and observed tests. Conceptual discussion, interview behavior, deployment and model outcomes are not established by those tests.

[Candidate packet](Frontier_Lab_Mock_Interview.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
