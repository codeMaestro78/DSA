# Phase 6 quality and validation evidence

Phase 6 checks cover published reference implementations, synthetic calculations, packet bookkeeping and bounded content review. They do not prove every answer correct, every system implemented, a validated hiring scale or candidate readiness. All dates here are **2026-11-03**; company dossiers retain their own source dates.

## Executed local reference checks

| Suite | Observed result | Scope |
|---|---|---|
| [Core mocks](validation/mocks-core/README.md) | **24,395 checks passed**: Java 20,822; Python/NumPy 3,573 | Three exact marked published blocks extracted/executed; 20,000 seeded Java primary operations against a scan oracle; 400 customer-MAE fixtures against scalar/Fraction aggregation; 100 stable masked-loss fixtures plus finite differences and boundaries |
| [AI mocks](validation/mocks-ai/README.md) | **1,802 checks passed** | Exact published incident validator, finite/seeded Unicode spans, strict types/keys/identity and synthetic paired metrics, retrieval denominators and event folding |
| [Design mocks](validation/mocks-design/README.md) | **18 checks passed** | Three marked candidate JSON fixtures parsed directly: booking/queue rates, routing selective correctness and raw export size |

Observed environment: **Python 3.12.0**, **NumPy 2.4.6**, **JDK/compiler 26**. Java compilation uses **`--release 17`** and temporary directories outside the repository; it checks source/API compatibility under that compiler, not execution on a Java17 VM. No extra package, training framework or server was installed. No compiled artifact is retained under `interview/`.

The four marked reference implementations are the single-threaded rolling-user window, equal-customer MAE, stable masked token NLL and strict parsed incident-proposal validator. Exact code extraction avoids validating an unrelated copy. Randomized fixtures use fixed seeds and independent bounded oracles; they are not exhaustive proofs. The derivative check validates expected numerical behavior on small well-scaled arrays, not an autograd implementation or GPU kernel. Synthetic event folding is a test calculation, not a deployed frontend reducer.

## Content corrections and review

- Candidate and companion task IDs/timings are paired; all nine sessions have four stages. Preflight and debrief are excluded consistently. Clarification is H0, while H1/H2/H3 help remains recorded rather than silently counted as independent work.
- RAG answerability coverage explicitly means eligible-answer queries divided by all queries. Recall@2 excludes empty-gold queries. Raw-entry and query exposure rates have separate denominators; citation presence is not treated as entailment proof.
- Real-valued ML helpers now reject complex arrays before conversion, avoiding silent loss of imaginary components. Conversion failures normalize to ValueError; six extra rejection fixtures cover complex/malformed values. Input bounds, masking, mutation and numerical limitations are explicit.
- The booking answer distinguishes a unique authoritative allocation from a stale availability read, transactional all-slot allocation from partial writes, and stable creation identity from later cancellation. No exactly-once email promise is inferred from an outbox.
- Queue arithmetic uses net drain after the burst: 6,000 jobs divided by 15 jobs/s gives 400 seconds. Nominal generation utilization is not a tail-latency guarantee. Raw CSV bytes do not establish actual memory, encoded size or throughput.
- The customer-routing fixture gives 150/200 proposal coverage and 138/150 selective correctness, with four Billing→Technical errors. No representative population claim or actual pilot is inferred.
- Payment uncertainty, current authorization, revision-bound updates, replay/snapshot races, rollback/deletion and model-output authority are addressed in the reference reasoning. No local key, prompt delimiter or schema is claimed to establish remote deduplication or semantic truth.
- Rubric ratings preserve critical findings, assistance and Not assessed. Level 4 requires repeated independent evidence. Hypothetical roleplay is not credited as observed personal ownership/customer impact, and no uncalibrated total becomes a hiring decision.

This is an editorial review of the authored scope, not formal verification. Source inspection supports concepts but is not proof of the invented scenarios or the custom scoring scale. Alternative solutions preserving the stated contract remain valid.

## Packet, source and library bookkeeping

[The mock checker](validation/check_mocks.py) passes **nine candidate/interviewer pairs, 36 task mappings, 54 task-specific scoring rows, metadata and four-stage timing arithmetic**, totaling **645 live practice minutes**. It checks generated JSON parity and explicit packet conventions. Its spoiler-marker check is structural; it cannot prove there is no semantic clue, enforce access control or render Markdown.

Four source registers record **20 directly inspected external contexts**, **14 reused aliases** and **six new central entries**. The central registry has **278 accepted URL/scopes**. The research checker validates earlier scoped citation bookkeeping against that expanded registry; it does not fetch pages or corroborate interview events. Technical/framework sources are referenced for mechanisms only; none was executed unless explicitly named in the local-suite scope.

Final structural result: **PASS — 406 Markdown files, 69 README-covered directories, 3,674 local links, 410 requested paths, ten Python blocks syntax-parsed and nine Mermaid blocks located**. All new Python validation sources also passed AST parsing.

The original **410 requested paths** reconcile to **291 delivered and 119 planned**. There are **406 Markdown files**, including **31 new Phase 6 documents**. Directory READMEs, local file paths, code fences/disclosures, Python-fence syntax, duplicate documents and compiled-artifact absence are checked by [the document checker](validation/check_documents.py). Explicit Phase 5 question anchors/index parity remain checked by [the question checker](validation/check_questions.py). General external URLs, arbitrary heading fragments and Mermaid rendering are outside these checks.

The final comparison with the pre-phase SHA-256 snapshot confirms **205 Java files outside `interview/` are unchanged**. New Java reference/test code lives only in mock documentation and isolated validation artifacts. No commit, push, release or deployment was performed.

## Reproduce

From repository root:

```bash
python3 -B interview/validation/mocks-core/check_examples.py
python3 -B interview/validation/mocks-ai/check_examples.py
python3 -B interview/validation/mocks-design/check_fixtures.py
python3 interview/validation/check_mocks.py
python3 interview/validation/check_questions.py
python3 interview/validation/check_research.py
python3 interview/validation/check_documents.py
```

Use `python3 interview/validation/check_mocks.py --write-index` only after intentional metadata changes, then rerun read-only. These scripts contain spoilers. Prior unchanged DSA/AI/ML suites were not rerun or counted as new Phase 6 validation. The earlier Phase 5 SQL startup limitation remains unchanged; no SQL execution is claimed for this phase.

## Limits and next step

No real candidate session, reviewer reliability study, browser/accessibility inspection, deployed authorization, native database concurrency, queue recovery, hosted model evaluation, training/checkpoint recovery, payment/refund, customer connector or production benchmark was run. Candidate/interviewer separation is a file convention; no practice application or live interviewer agent is implemented. Current employer rules must be confirmed from the actual posting/invitation rather than a mock title.

Phase 7 remains the full-library content/freshness/rendering review. Unwritten technical chapters remain planned. [Delivery report](PHASE_6_REPORT.md) · [Study guide](PHASE_6_STUDY_GUIDE.md) · [Mock directory](13-Mock-Interviews/README.md).

Last integrated: **2026-11-03 IST**.
