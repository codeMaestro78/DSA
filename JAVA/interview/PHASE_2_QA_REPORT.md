# Phase 2 quality assurance

Checked **2026-11-02**. The checks below support the delivered foundation subset. They do not certify a completely error-free library or predict interview outcomes.

## Executable evidence

| Suite | Observed result | Scope and runtime |
|---|---|---|
| Java | 15,082 passing checks | Eight methods; JDK26 compilation with `--release 17`, execution on JDK26; seed20261102 |
| Java guide excerpts | Eight compile successfully and match tested method bodies | Independent compilation, not a separate runtime per snippet |
| PostgreSQL | 28 passing checks | PostgreSQL16.15; nine marked Markdown SQL blocks and selected concurrent schedules |
| Design arithmetic | 22 passing checks | Python3.12; formulas/scenarios and invalid-input check, not a load test |

Reproduction instructions and source are in [validation/](validation/README.md). No compiled artifacts are stored in `interview/`. Existing Java practice files outside this directory were not edited or claimed as validated.

## Document integrity

The [standard-library checker](validation/check_documents.py) verifies:

- All Markdown documents are nonempty and have one top-level title outside code fences.
- Every directory has a README.
- All local Markdown link destinations exist; planned paths are not broken links.
- All 410 originally requested manifest paths have accurate delivered/planned status.
- No byte-identical duplicate documents, unclosed fences or unbalanced details tags.
- READMEs using the standard planned-documents section do not list an already-created file as planned.
- No `.class` or `.pyc` artifacts are present in the knowledge base.

Final inventory: **161 Markdown files**, **59 directories with READMEs**, **99 requested paths present** and **311 planned**. The checker reports the final local-link count at execution rather than freezing a count that changes when this report adds links. Six Mermaid blocks were located and inspected as text; syntax/rendering was not tested by a Mermaid engine.

## Editorial checks and corrections

Reviewed contracts, complexity assumptions, source scope, numeric units, API/data ownership, concurrent schedules, side-effect gaps, assessment labels and phase status. Corrections included the guard-row snapshot caveat, coin-DP amount-zero validation cost and consistent treatment of unassessed scores. New question prompts and answer scaffolds are explicitly company-neutral and do not invent the candidate's personal history.

The source registry preserves first-party scope and publication/access distinctions. Reused URLs are deduplicated, with track aliases retained. Runtime differences between documentation and execution are explicit rather than treated as equivalent validation.

## Limits and outstanding validation

- Passing random and boundary cases is not a proof over every input. Reasoning and stated preconditions remain necessary.
- Not every variant, data structure or practice answer has executable tests.
- PostgreSQL tests do not validate version18-specific features, production plans, replica behavior, power-loss durability, HTTP endpoints or full tenant isolation.
- Authentication/authorization chapters are educational guidance, not a live security audit.
- Arithmetic checks do not establish throughput, cost or availability.
- External URLs and heading anchors were not exhaustively rechecked after writing.
- Markdown/Mermaid rendering and Obsidian import remain unverified.
- Phase 1's [QA report](QA_REPORT.md) remains a historical snapshot; this report describes Phase 2.

## References and last verified

[Delivery report](PHASE_2_REPORT.md), [manifest](STRUCTURE_MANIFEST.md), [methodology](RESEARCH_METHODOLOGY.md), [source index](SOURCE_INDEX.md).

2026-11-02: observed local validation and editorial review recorded, with limits above.
