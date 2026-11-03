# Knowledge-base validation

These checks establish specific local properties of the examples and documents. They do not prove the entire curriculum error-free, production-ready or interview-ready.

## Final whole-library pass

Final observed result: **18/18 local suite/compilation steps pass**; [exact outputs and exclusions](PHASE7_RESULTS.json).

Run all available local suites from the workspace root:

```bash
python3 -B interview/validation/run_checks.py --output /tmp/interview-final-checks.json
```

Requires the existing Python/NumPy and Java tools; no dependencies are downloaded. Native SQL and browser checks are explicitly excluded from the pass count and retain their separate blocked/historical status. See [final QA](../PHASE_7_QA_REPORT.md) and [the review ledger](PHASE7_REVIEW_LEDGER.md) for content coverage and corrections.

[Anchor regression contracts](check_anchor_contract.py) exercise the actual local slug helper against12 heading/explicit-anchor/fenced-example cases; this is a declared renderer subset, not universal compatibility.

[The library checker](check_library.py) adds requested-path completion, local Markdown fragments, extension coverage, source IDs and stale-authoring checks. [The manifest builder](rebuild_manifest.py) reads the preserved [original410-path inventory](requested_paths.json); it is bookkeeping, not a teaching-content generator.

[Static GFM conversion](check_render.mjs) accepts an installed Marked ESM module and a temporary output directory:

```bash
node interview/validation/check_render.mjs --marked /path/to/marked.esm.js --output /tmp/interview-render
```

No browser or Mermaid SVG rendering is implied by static conversion. The installed parser was used without installing or upgrading packages. Chromium local socket setup was blocked, and a separate Mermaid module could not initialize because of an incomplete environment dependency.

## Checks and reproducibility

| Area | Artifact | Observed result and scope |
|---|---|---|
| Java algorithms | [DSA instructions](dsa/README.md) | 15,082 checks; deterministic boundaries, random differential cases and list identity/order/cycle checks |
| Design calculations | [Design instructions](design/README.md) | 22 checks of arithmetic, queue scenarios and input boundaries |
| PostgreSQL | [Database instructions](database/README.md) | 28 native checks; nine marked published SQL blocks and selected concurrent schedules |
| AI helpers | [AI instructions](ai/README.md) | 2,920 provider-neutral control, ranking and boundary checks; no hosted model calls |
| ML numerical examples | [ML instructions](ml/README.md) | NumPy finite-difference/attention/metric checks; framework snippets syntax-only |
| AI design arithmetic | [AI design instructions](ai-design/README.md) | 21 arithmetic, boundary and consistency checks; no GPU/model tests |
| Company research bookkeeping | [Research checker](check_research.py) | Manifest coverage, source IDs, access-date fields and accepted-versus-rejected citation bookkeeping; no web/claim verification |
| Phase 5 coding/numerical examples | [Core questions](questions-core/README.md) | 66,387 checks; three exact published blocks executed (Java/Python/NumPy); no hidden-platform submissions |
| Phase 5 AI coding | [AI questions](questions-ai/README.md) | 13 unittest methods, exact-snippet parity and deterministic injected-operation boundaries |
| Phase 5 SQL | [System/database questions](questions-system/README.md) | Four marked published blocks and a disposable-fixture runner supplied; new SQL execution blocked by sandbox startup, not native-validated |
| Phase 5 quantile reasoning | [Review fixture](questions-review/README.md) | 17 synthetic arithmetic/boundary checks; no service benchmark |
| Question records | [Question checker](check_questions.py) | Schema, unique IDs, related IDs, attempt-first disclosure, explicit QB anchors and generated index parity; no technical-truth proof |
| Phase 6 core mock code | [Core reference fixtures](mocks-core/README.md) | 24,395 checks: Java rolling window and two extracted NumPy references; no training/service proof |
| Phase 6 AI mock examples | [AI fixtures](mocks-ai/README.md) | 1,802 checks: exact proposal helper plus synthetic metrics/replay folding; no provider/browser/auth execution |
| Phase 6 design arithmetic | [Design fixtures](mocks-design/README.md) | 18 checks of three extracted candidate fixtures; no database/queue/customer/payment execution |
| Mock packet integrity | [Mock checker](check_mocks.py) | Nine paired packets, 36 tasks, metadata/timing and JSON parity; no scoring validity or answer truth proof |
| Documents | [Integrity checker](check_documents.py) | Local paths, inventory status, README coverage, titles, disclosures, fences, Python syntax and duplicates |

Run the document checker from the workspace root:

```bash
python3 interview/validation/check_documents.py
```

Java instructions compile to `/tmp`, with `--release 17` on installed JDK 26. This checks compile-time compatibility; runtime execution occurred on JDK 26. Each of the eight published Java snippets was also independently compiled and its method body compared with the tested implementation.

The design script uses Python 3.12 in the observed run. PostgreSQL tests used a disposable private-socket cluster on 16.15; sources describe version18 and explicitly identify untested version-specific capabilities. The SQL runner requires a separate disposable server setup and refuses other server data-directory layouts.

## Limits

The document checker parses Python fences without executing them. It does not fetch external pages, validate heading fragments, render Markdown/Mermaid or assess technical truth. Mermaid diagrams were inspected as text against their explanations. No load test, cloud deployment, provider integration, live authentication implementation or production durability test was run. Not every practice solution has executable code; the chapters distinguish tested examples from reasoning exercises.

## Navigation and last verified

[Phase 2 report](../PHASE_2_REPORT.md), [Phase 2 QA](../PHASE_2_QA_REPORT.md), [Phase 3 report](../PHASE_3_REPORT.md), [Phase 3 QA](../PHASE_3_QA_REPORT.md), [AI learner sequence](../PHASE_3_STUDY_GUIDE.md), [Phase 4 research report](../PHASE_4_REPORT.md) [Phase 4 QA](../PHASE_4_QA_REPORT.md), [Phase 5 guide](../PHASE_5_STUDY_GUIDE.md) [Phase 5 QA](../PHASE_5_QA_REPORT.md), [Phase 6 study guide](../PHASE_6_STUDY_GUIDE.md) and [Phase 6 QA](../PHASE_6_QA_REPORT.md).

2026-11-03: Phase7 local reruns and bounded whole-library review recorded.
