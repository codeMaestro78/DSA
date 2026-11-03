# Phase 5 quality and validation evidence

This report distinguishes executed local examples, inspected sources, reviewed reasoning and bookkeeping. It does not guarantee that every answer is error-free or that the full curriculum is complete. Source review occurred on 2026-11-02; final local integration continued on 2026-11-03 IST.

## Executed examples

| Area | Observed result | What it establishes |
|---|---|---|
| [Published core examples](validation/questions-core/README.md) | **66,374 assertions passed**: Java LRU 64,894; Python/NumPy 1,480 | Three exact marked published blocks extracted/executed; 26,000 seeded LRU operations against a separate model, threshold selection against mask enumeration, small regression gradients against scalar loops/finite differences and seven worked numbers |
| [AI coding helpers](validation/questions-ai/README.md) | **13 unittest methods passed**, including invalid-input subcases and source parity | Acquisition/release ownership and bounded synthetic retry behavior with injected operations/sleep; two published snippets match the exercised helpers |
| [SQL questions](validation/questions-system/README.md) | **Not executed**: sandbox cluster startup failed; escalation did not complete | Four marked blocks and a disposable-fixture runner supplied. SQL outputs/constraints and new concurrent schedules remain reviewed reasoning, not new native proof |
| [Quantile fixture](validation/questions-review/README.md) | **17 checks passed** | Synthetic correlated/disjoint nearest-rank p95, decimal fraction convention, endpoints, invalid inputs and large integer boundaries; no service latency measurement |

Python execution used **3.12.0**, NumPy **2.4.6**, Java/compiler **26**, with the published Java block compiled using **`--release 17`**. This checks Java 17 source/API compatibility under that compiler, not execution on a Java 17 VM. The new PostgreSQL runner did not complete a native execution; its setup failure is recorded separately. No extra framework was installed or model trained for these examples. Tests compile outside the repository; no `.class` or `.pyc` artifacts are retained in the library.

## Corrections made during cross-review

- A queue-drain heading conflicted with its correct arithmetic: 36,000 queued jobs at 100 jobs/s net drain take **360 seconds**, or six minutes, after the burst. The heading now agrees.
- A read-replica answer needed an explicit freshness gate/fallback to support the stipulated five-second public-data tolerance. Naming “bounded staleness” alone is insufficient.
- The alias uniqueness contract now identifies its comparison/collation assumptions rather than relying on an unspecified database configuration.
- The quantile helper initially converted an arbitrarily large invalid integer fraction through `math.isfinite`, causing `OverflowError`. Bounds now precede that conversion; large positive and negative invalid fractions have rejection checks. Decimal fractions use exact rational ranking to avoid binary-float ceiling artifacts.
- Threshold selection now states its numeric cost bound in the prompt and rejects huge out-of-range integer probabilities/costs before conversion; three extra checks cover these cases.
- Resource hoarding/starvation and deadlock are distinguished: deadlock requires a cyclic dependency. Reservation expiry is not claimed to stop remote work.

Additional review covered SQL NULL/ties, aggregation grain, guarded updates, write skew/deadlock reasoning, retry ownership, uncertain external effects, partition guarantees, numerical dimensions and source/proof boundaries. This is a bounded review of authored material, not formal verification.

## Index, sources and document checks

[The question checker](validation/check_questions.py) validates **134 records in 13 banks**: metadata, unique IDs, related IDs, nonduplicate prompt text, closed attempt-first disclosures, explicit question anchors and generated Markdown/JSON parity. It also checks explicit `qb-*` anchor links across the library. Difficulty counts are **25 Beginner / 60 Intermediate / 39 Advanced / 10 Expert**. It does not assess answer truth, approximate semantic duplication or Markdown rendering.

[The research checker](validation/check_research.py) passes the earlier **46 research-file**, **93 Phase 4 source-record** and accepted/rejected citation checks against the expanded **272 central IDs**. It fetches no URLs and does not corroborate candidate events. Four Phase 5 registers retain **99 external records**, **31 aliases** and **68 new central entries**; reuse does not supply independent corroboration.

[The document checker](validation/check_documents.py) reconciles the original **410 requested paths**, README coverage, local file destinations, disclosure/fence balance, Python-fence syntax and identical-document detection. The final library has **375 Markdown files**; **281 requested files are delivered, 129 planned**. Final document result: **PASS — 375 Markdown files, 66 README-covered directories, 3,347 local links, 410 requested paths, seven Python blocks syntax-parsed and nine Mermaid blocks located**. The checker does not execute Python fences, render diagrams, fetch external links or generally validate heading fragments. The question checker covers only the explicit QB fragments described above. A separate inventory check confirmed PUB-01–PUB-22 map one-to-one to the 22 catalog areas and carry a platform difficulty; this checks catalog structure, not platform hidden tests. The supplied SQL runner and question checker also parsed successfully as Python source.

## Reproduce focused checks

From the repository root:

```bash
python3 -B interview/validation/questions-core/check_examples.py
python3 -B interview/validation/questions-ai/check_examples.py
python3 -B interview/validation/questions-review/check_reasoning.py
python3 interview/validation/check_questions.py
python3 interview/validation/check_research.py
python3 interview/validation/check_documents.py
```

The supplied SQL runner was not successfully executed in this phase. It requires the disposable private-socket PostgreSQL setup documented in [its README](validation/questions-system/README.md). It refuses a data directory outside that temporary fixture layout. Do not point it at a live/shared service. New code checks were run for this phase; earlier unchanged algorithm/AI/design suites were not rerun or counted as new Phase 5 execution.

## Boundaries and next review

System design, cloud operations, most AI/RAG scenarios, new concurrent database schedules, behavior and project answers are reviewed guidance under stipulated assumptions. No cluster rollout, live authentication/SSE/connector, provider cancellation/billing, model-quality evaluation, security audit of a deployed app or universal numerical-stability guarantee is established. The 22 public problems have checked public statements/ratings and reviewed solution directions; no submissions or hidden tests were run. Markdown answer disclosure/anchor rendering was not browser-tested.

Original question authorship is [INFERRED]; exact employer-question attribution and frequency remain unsupported. Behavioral/project guidance supplies no fictional personal accomplishments. Existing Java files outside `interview/` were untouched; the isolated LRU example is separate from the user's solution.

Full role mocks remain Phase 6. Whole-library content/freshness/rendering review remains Phase 7. [Delivery report](PHASE_5_REPORT.md) · [Study guide](PHASE_5_STUDY_GUIDE.md) · [Progress tracker](PROGRESS_TRACKER.md).

Last integrated: **2026-11-03 IST**.
