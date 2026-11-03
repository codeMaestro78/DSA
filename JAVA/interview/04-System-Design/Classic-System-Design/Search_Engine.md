# Incremental web/document search: hypothetical design case

## Attempt-first scope

Design crawling/ingestion, an inverted index, query retrieval and versioned publication for a bounded permitted corpus. Internet-scale crawling and secret ranking algorithms are excluded.

All requirements, workload figures and answer choices are **[INFERRED]** teaching fixtures. Target: 0–2-year software/AI candidate; core difficulty Intermediate with optional Advanced failure followups. Suggested practice: 35 minutes for requirements/API/data/flow, then 10 minutes for failure/tradeoff discussion. These are editorial budgets, not employer timings.

**Prompt:** Propose a minimal architecture for the stated scope. Define the invariant, trace one write and one authorized read, calculate the supplied workload, then test one partial failure. Keep the reference closed until you have a first design.

**Stipulated workload:** Assume 1 million documents×10 KB normalized text=10 GB decimal text before postings, stored fields and replicas. A10% daily change rate means100,000 document updates/day; update volume does not establish query capacity.

<details><summary>Reference design and followups — attempt first</summary>

## Contract and ownership

`POST /documents` accepts an authorized source ID/version; `GET /search?q=...&cursor=...` uses bounded query length and explicit pagination/version semantics. Private-source access remains separate from lexical relevance.

Source store owns permitted document identity/version/deletion. Index generations own derived terms/postings and analyzers. Stable document IDs plus generation metadata let the system reconcile updates without pretending an index is the original content authority.

## Critical flow

Fetch approved sources, parse/tokenize under a versioned analyzer, build postings and store provenance. Publish a coherent index generation or incrementally apply ordered versions. Query analysis retrieves candidates, scores by a declared baseline and filters current authority before exposure.

## Failure trace

A deletion arrives during a rebuild. A snapshot-only rebuild could resurrect it on activation. Record a cutoff and replay later changes/deletes, then verify at publication. For private data, current authorization must not be frozen by an old index snapshot.

## Tradeoffs, security and operation

An inverted index supports term lookup; vector similarity may improve semantic candidates but introduces another representation/evaluation path. Measure recall/precision on a frozen labeled set and inspect unsupported queries. Near-real-time indexing has a visibility delay and is not an immediate read-your-write guarantee.

Monitor the user-visible outcome and oldest unresolved work, not only process uptime. A launch decision still needs representative measurements, failure injection, access tests, recovery ownership and a rollback/reconciliation plan. This reference provides a coherent starting design; alternatives preserving the same contract can be valid.

**Followup:** Can rebuilding from yesterday's documents safely replace today's index?

**Expected reasoning:** Only with an explicit snapshot/change/deletion reconciliation contract. Otherwise updates and revocations can be lost. Followup: how do query cursors behave across generation activation?

**Common mistakes:** skipping the invariant, treating a cache/queue as automatic correctness, and reporting invented workload inputs as observed production evidence. **Strong evidence:** concrete identities, authoritative transitions, correct units and an explicit limit on recovery or consistency.

</details>

## References and proof boundary

No service, provider, database, media player or deployment was executed for this case. Numerical work is reviewed arithmetic; the architecture is reasoned guidance rather than benchmark or security proof.

- [Elastic visibility model](https://www.elastic.co/docs/manage-data/data-store/near-real-time-search).
- [Authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).

[Phase7 source scope](../PHASE7_SOURCES.md) · [Case directory](README.md) · [Design framework](../System_Design_Interview_Framework.md). Last reviewed: **2026-11-03**.
