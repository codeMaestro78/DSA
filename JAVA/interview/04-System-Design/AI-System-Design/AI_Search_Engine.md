# Design an AI-assisted search engine

## Candidate prompt

**AI-SD-07 — Intermediate/Advanced; RAG/search/AI; design; company-neutral.** Search a permitted corpus and optionally summarize results with citations. Begin with a ranked search interface; answer generation is a separate product decision.

<details><summary>Reference architecture</summary>

### Data and contracts

The catalog owns documents, versions and current permissions. Ingestion derives searchable fields, lexical/dense indexes and source previews. The query API accepts text, bounded filters, principal and cursor; the ranking service owns a versioned ranking configuration. A generation service may consume a bounded selected evidence set, but cannot expand access.

Flow: authenticate → normalize/validate query → enforce tenant/filter policy → retrieve candidates → verify current eligibility before exposing candidate text to a reranker → rerank permitted candidates → revalidate at the declared publication boundary → return ranked evidence → optionally generate and validate a cited answer. A stale index's tenant metadata is not sufficient authority to send private text to an external or otherwise separately trusted reranker. Define the revocation consistency/propagation contract; an independent late check alone does not make exposure atomic with concurrent policy changes. Preserve the distinction between a matching result, a useful ranking and a supported answer. The index is not the sole authority for deletion or permission changes.

### Query adaptation and diagnostics

Query rewriting can improve coverage but can also drop identifiers or change intent. Keep original-query candidates and evaluate the combination on labeled queries. A reranker only reorders its input set; it cannot recover a relevant document absent from all candidate generators. Hybrid fusion requires a defined ranking/score method, not arbitrary addition of incomparable raw scales.

For a user asking about an exact error code, lexical matching may be important. For a paraphrased concept, dense candidates may help. These are reasons to test coverage, not guarantees that either method wins. Evaluate Recall@k, ranking quality, eligible-result rate, answer support and latency/cost separately.

### Caches, pagination and freshness

Cache keys include normalized query, authorized scope/policy version and ranking/index generation as needed. An unsafe shared query cache can leak private results even when the uncached path filters correctly. A stable cursor needs deterministic ordering and a defined index generation or an explicit best-effort traversal contract. New documents and changed scores can otherwise create skips or repeats.

### Failure and tradeoff

If generation fails, returning authorized ranked sources can be a useful degraded mode if the UI clearly distinguishes it. If policy verification fails, serving private cached results is unsafe without a justified contract. Index lag needs a freshness indicator and operator metric. A broad web crawler also needs fetch isolation, crawl policy, parser hardening and content provenance; those concerns are out of scope for the private fixed-corpus baseline.

</details>

## Practice question

A summarizer sounds correct, but the search page omits the relevant document. Which fixes should be tested first?

<details><summary>Expected reasoning</summary>

Inspect ingestion, access eligibility, query interpretation and candidate recall before changing generation prompts. Compare the relevant document's lexical/dense rank under the user's actual policy. Preserve legitimate permission exclusions rather than bypassing them to raise recall.

</details>

## Checklist

- [ ] Candidate generation, ranking and answering are separate stages.
- [ ] Caches/pagination preserve authorization and generation scope.
- [ ] Degraded search behavior is explicit.

## References

- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).
- [OWASP: authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html).
- [Phase 3 source metadata](PHASE3_SOURCES.md).

## Last verified

2026-11-03: pre-reranker eligibility and publication-boundary reasoning reviewed; primary-source dates remain in the source register. Architecture and workload assumptions are original [INFERRED] teaching material, not a company disclosure or measured deployment.
