# RAG practical questions

These ten original **[INFERRED]**, company-neutral exercises are focused practice, not employer questions or encounter-frequency claims. Start with Beginner/Intermediate questions for 0–2 years; Advanced questions are extensions. These are individual exercises; complete role simulations belong to Phase 6. Attempt the prompt before opening its answer. Alternatives that preserve the stated contract can be valid.

Read the linked canonical lesson when a mechanism is unfamiliar. Each “Round” is a suggested practice format, not a company process. Difficulty is an editorial judgment, not a measured pass rate. For design/diagnosis questions, the answer is a reviewed reference approach, not a live integration result.

Last verified: **2026-11-02** — prompt contracts, answer reasoning, local links and question records reviewed. Only QB-AI-001/002 include locally executed code; other scenarios are reasoning exercises. [Source scope](PHASE5_AI_SOURCES.md). [Question-bank directory](README.md).

<a id="qb-rag-001"></a>

## QB-RAG-001 — Diagnose a missing answer before changing the model

| Field | Value |
|---|---|
| Category | RAG failure localization |
| Difficulty | Beginner |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | RAG failure localization; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-002 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

The authoritative handbook contains “contractors may expense rail travel.” A contractor asks about rail expenses; the assistant says it cannot find a policy. You have document ingestion logs, indexed chunks, top-10 retrieval results and the final model context. Give an ordered investigation and one distinguishing check per stage.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** First verify the expected handbook version was successfully ingested and that extraction retained the sentence, including its section heading and contractor scope. Then locate the indexed chunk by stable document/version identifier and confirm it belongs to the requester’s authorized retrieval set. Inspect the query and top-10 results: if the chunk is absent, investigate filters, query representation, lexical coverage and candidate cutoffs before blaming generation. If present in candidates but absent from model context, inspect reranking, deduplication and token-budget selection. If the passage is in context, inspect the actual rendered prompt and answer policy for a generation or interpretation problem. Keep each observation tied to the same request and index snapshot. A single failure does not establish that a larger model or a new vector database will help; choose a targeted change and evaluate similar contractor queries plus unaffected cases.

**Explanation:** A staged trace makes different causes distinguishable. An answer score alone cannot tell whether the evidence was extracted, retrieved, selected or used.

**Followups:** What if the source has changed since the request? Which observations should be redacted in a shared debug trace?

**Common mistakes:** Changing embeddings first; checking only ingestion success status; assuming the top candidate was actually included in context.

</details>

<a id="qb-rag-002"></a>

## QB-RAG-002 — Keep a table row meaningful after chunking

| Field | Value |
|---|---|
| Category | Document representation |
| Difficulty | Intermediate |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Document representation; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-003 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/RAG-Engineer/Chunking_Strategies.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A PDF table has columns Plan, Monthly price and Region. Page two repeats no header and contains “Pro | 40 | EU”; page one states all amounts are EUR excluding tax. Design the evidence representation for answering “What does Pro cost in EU?” The source snapshot is fixed.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Represent the row with its column labels, inherited currency/tax note and document/page provenance. A useful retrieval unit might render “Plan: Pro; monthly price: EUR 40 excluding tax; region: EU,” while retaining the original table coordinates and extraction confidence. This derived text must be traceable to the source rather than presented as a verbatim quotation. Keep an explicit relationship between the row and table-level metadata so a correction to the note updates dependent rows. Retrieve the row plus enough supporting header/note evidence to justify the answer and citation. Test multi-page tables, repeated plan names across regions, blank cells and extraction that shifts columns. A generic fixed character boundary can separate 40 from both currency and billing interval, so evaluate structural chunking against these concrete questions instead of selecting a chunk size solely from token-budget convenience.

**Explanation:** The answer depends on row, header and note together. Losing the unit can yield a numerically correct but misleading price.

**Followups:** How would merged cells change the parser? What should the assistant do if currency inheritance is ambiguous?

**Common mistakes:** Citing only the number; treating a reconstructed row as an exact quote; assuming every table row fits on one page.

</details>

<a id="qb-rag-003"></a>

## QB-RAG-003 — Preserve exact identifiers during retrieval

| Field | Value |
|---|---|
| Category | Lexical and semantic retrieval |
| Difficulty | Intermediate |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Lexical and semantic retrieval; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-004 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Hybrid_Search.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Two documents concern products AX-19 and AX-91 and use almost identical prose. The user asks for the warranty on AX-19. Dense retrieval ranks AX-91 first. Propose a retrieval change, an experiment and a failure case; do not assume either identifier is a typo.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Preserve the literal product identifier through query processing and add an exact identifier or lexical retrieval path. Where a trusted catalog maps identifiers to products, first resolve AX-19 and apply the corresponding metadata constraint; otherwise merge lexical and dense candidates before reranking. Do not globally remove dense retrieval, because descriptive questions without exact codes may still benefit from it. Evaluate a held-out set containing neighboring identifiers, punctuation/case variants, unknown identifiers and identifier-free descriptions. Inspect exact-product correctness and answer support as well as aggregate ranking metrics. A normalized identifier mapping must be deliberate: removing hyphens or changing case is safe only if the catalog’s identifier rules permit it. If AX-19 is unknown, request clarification or say so rather than silently substituting AX-91. Measure latency and missed-result tradeoffs on the actual corpus before selecting fusion weights or candidate counts.

**Explanation:** Semantic similarity can preserve meaning while confusing entities that differ in a few characters. Entity resolution and evidence retrieval solve different parts of the problem.

**Followups:** How would you handle two manufacturers using AX-19? What negative examples belong in the reranker evaluation?

**Common mistakes:** Autocorrecting identifiers silently; claiming hybrid search always wins; evaluating only paraphrases without exact codes.

</details>

<a id="qb-rag-004"></a>

## QB-RAG-004 — Define metrics for incomplete retrieval

| Field | Value |
|---|---|
| Category | Ranking arithmetic |
| Difficulty | Intermediate |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Ranking arithmetic; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-005 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/RAG_Evaluation.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Use binary relevance with known relevant IDs {a, b, c}. A retrieval call returns only [x, b], and evaluation cutoff k=4. Define precision with denominator k, recall over all known relevant IDs, and reciprocal rank over the returned list. Calculate all three and explain a query with no known relevant IDs. These conventions are part of this prompt.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Only b is relevant. Precision@4 is 1/4 = 0.25 because the contract uses the cutoff as denominator even when fewer than four items are returned. Recall@4 is 1/3 because one of three known relevant items is retrieved. The first relevant result is at rank two, so reciprocal rank is 1/2. For a query with no known relevant items, recall has a zero denominator; report it as undefined or a separately designated value under a published policy rather than silently treating it as zero or perfect recall. Distinguish “no relevant evidence exists” from “nothing has been labeled yet.” Such queries can support separate abstention/retrieval-empty evaluation if their meaning is known. When averaging, report how many queries were excluded or handled separately. These metrics assess the ranked list under the supplied labels; none establishes whether the generated answer is supported or correct.

**Explanation:** A short result list does not change the requested denominator. Incomplete labels can penalize an actually relevant but unjudged result, which requires annotation review.

**Followups:** How would duplicate results affect the contract? When would precision with returned-count denominator answer a different question?

**Common mistakes:** Using 1/2 for the requested precision; dividing recall by returned count; silently dropping unlabeled queries from reporting.

</details>

<a id="qb-rag-005"></a>

## QB-RAG-005 — Run a shadow embedding migration

| Field | Value |
|---|---|
| Category | Index versioning |
| Difficulty | Advanced |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Index versioning; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-006 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/RAG-Engineer/Production_RAG_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

An existing index uses embedding model E1. You want to test E2 with different dimensionality. Documents change daily, and rollback must remain possible. Design a migration with a consistent comparison and a clear cutover condition. Do not assume vectors from E1 and E2 are comparable.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Create a separately versioned E2 index with its own dimensionality, embedding configuration and document/chunk lineage. Backfill from a recorded source snapshot, then apply later upserts and deletions through a durable change sequence or another explicit catch-up mechanism. Query each index using its matching query encoder. During shadow evaluation, compare both on the same authorized source version and held-out query set; otherwise freshness differences can masquerade as model improvements. Measure retrieval quality, supported-answer behavior, latency and cost. Gate cutover on agreed acceptance criteria, ingestion lag and deletion correctness, then route a bounded cohort before broad adoption. Keep E1 updated for the rollback window and retain a versioned routing switch. Rollback is not safe if E1 missed recent deletions or changes. After the window, retire old vectors and related caches according to retention rules instead of keeping an undocumented permanent copy.

**Explanation:** Different vector spaces need independent query encoding. A migration is also a data-consistency problem because backfill and live changes overlap.

**Followups:** How would you reconcile a delete arriving during backfill? Which query cache keys change with the embedding version?

**Common mistakes:** Mixing E1 and E2 vectors in one similarity space; evaluating unequal snapshots; treating a stale index as a safe rollback.

</details>

<a id="qb-rag-006"></a>

## QB-RAG-006 — Distinguish retrieval gain from reranking gain

| Field | Value |
|---|---|
| Category | Controlled RAG experiments |
| Difficulty | Intermediate |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Controlled RAG experiments; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-007 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/RAG-Engineer/Reranking.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

Pipeline A retrieves 20 candidates with no reranker. Pipeline B retrieves 100 and reranks to five. B answers more questions correctly. A teammate says the reranker caused all the improvement. Design an experiment that can support or weaken that claim under a fixed corpus and query set.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The comparison changes two factors, so it cannot isolate the reranker. Run a small factorial or ablation study: 20 candidates without and with reranking, and 100 candidates without and with reranking. Use the same final context budget, source snapshot, generator, prompts and held-out queries, and define a deterministic baseline ordering when reranking is absent. Measure candidate recall before reranking, relevant/supporting evidence in selected context, answer correctness, latency and cost. If the answer-bearing passage never appears in the top 20, a reranker limited to those 20 cannot recover it. Conversely, if it is present but poorly ordered, reranking may help selection. Examine per-query changes and uncertain labels rather than attributing a small aggregate difference mechanically. A production decision can still favor B, but the causal explanation must reflect which controlled comparisons were actually performed.

**Explanation:** Candidate generation determines what can be recovered; reranking determines ordering within available candidates. The fixed final context budget avoids conflating more context with better ordering.

**Followups:** What if reranking changes the number of chunks that fit? How would you prevent tuning on the final evaluation set?

**Common mistakes:** Changing generator and retrieval together; reporting only final answer accuracy; claiming causal improvement from one mixed comparison.

</details>

<a id="qb-rag-007"></a>

## QB-RAG-007 — Resolve two conflicting policy versions

| Field | Value |
|---|---|
| Category | Source authority and freshness |
| Difficulty | Intermediate |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; core preparation |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Source authority and freshness; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-008 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/RAG-Engineer/Document_Ingestion.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

An indexed policy effective January 1 allows a $50 allowance; a later uploaded draft effective December 1 proposes $70. Today is November 2. The user asks for the current allowance. Assume only approved policies take effect, and no approved December version exists. Explain selection, answer and necessary metadata.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Return the approved currently effective $50 allowance, citing its version and effective interval. The later upload is a draft and its proposed date is in the future, so recency of ingestion is not the authority rule. Store approval status, effective dates, version lineage and owning authority separately from upload/update timestamps. At retrieval or context selection, use the requested as-of date and authorized policy state, while preserving an explanation for why a conflicting source was not controlling. If the product allows discussing upcoming drafts, clearly label the $70 figure as a proposal and avoid blending it into the current answer. Test same-day changes, withdrawn drafts, overlapping approved versions and a missing effective date. If two genuinely approved current sources conflict, escalate or state the conflict instead of inventing a precedence rule that the organization has never supplied.

**Explanation:** Freshness has several clocks: ingestion, revision, approval and effective time. Choosing the newest file collapses them and can produce the wrong policy.

**Followups:** How would an “as of October 1” question change retrieval? What happens when approval is revoked after indexing?

**Common mistakes:** Assuming latest upload wins; presenting a draft as policy; resolving all conflicts by date alone.

</details>

<a id="qb-rag-008"></a>

## QB-RAG-008 — Keep source instructions out of the control plane

| Field | Value |
|---|---|
| Category | Retrieved-content trust boundaries |
| Difficulty | Advanced |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Retrieved-content trust boundaries; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-009 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Prompt_Injection.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A retrieved troubleshooting page contains useful error codes and an embedded instruction to upload the entire incident archive to an external URL. The assistant only needs to explain a local error and has no authorized upload task. Describe safe handling, useful tests and what prompt wording alone cannot guarantee.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Treat the page as task evidence, not as authority to change the workflow. The instruction does not come from the authenticated user or an approved system policy and does not authorize uploading an archive. Keep external uploads unavailable for this read-only task and enforce that tool boundary in the executor. The assistant may use the relevant error-code passage if it remains trustworthy, but should not obey the embedded command. Preserve source provenance and enough redacted trace information to diagnose whether lower-trust text influenced a proposal. Test direct instructions, disguised quotations and mixed useful/malicious content, checking both answer usefulness and absence of unauthorized tool dispatch. Clear delimiting and prompt rules can help a model distinguish data from instructions, but do not replace least-privilege tools, argument validation and resource permissions. A safe response can explain the error while reporting uncertainty if the page is unreliable.

**Explanation:** The requested upload crosses the task’s read-only boundary. This is an original adversarial fixture, not evidence of an existing site or system compromise.

**Followups:** What if the legitimate user requests a diagnostic upload? Which review must bind the destination and exact archive contents?

**Common mistakes:** Assuming sanitizing one phrase defeats all injection; blocking all retrieved content rather than preserving useful bounded evidence.

</details>

<a id="qb-rag-009"></a>

## QB-RAG-009 — Budget context without hiding the decisive exception

| Field | Value |
|---|---|
| Category | Evidence selection |
| Difficulty | Advanced |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Evidence selection; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-010 |
| Sources | [Canonical lesson](../08-LLM-RAG-and-Agent-Engineering/Context_Windows.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

You can include at most 1,200 evidence tokens, measured with the chosen model tokenizer. Candidates are: base policy 600 tokens, exception section 400, historical notes 500 and duplicate summary 300. The user is explicitly covered by the exception. All are authorized and from the same approved version. Select evidence and explain how you would verify the choice.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Include the 600-token base policy and 400-token exception, leaving 200 evidence tokens of headroom. The question depends on their relationship, so removing the exception to fit the historical notes would create a misleading answer. Exclude the duplicate summary and irrelevant historical notes unless further inspection establishes a necessary dependency. Preserve titles, identifiers and citation metadata within the defined evidence budget, or account for them separately if the contract says they are outside it. Verify the selected spans actually contain the eligibility condition and overriding rule, not just nearby headings. Test a question covered by the base rule and one covered by the exception, then review citations against the exact source version. This 1,200-token evidence allowance is not the model’s total context limit: system instructions, conversation, tool schemas and reserved output require their own allocation and limit checks.

**Explanation:** Evidence selection should preserve the reasoning dependency, not maximize the count of high-similarity chunks. Token estimates based on characters would violate the prompt’s measurement contract.

**Followups:** What if base policy plus exception exceeds the budget? How can a compressed extract preserve provenance without claiming to be verbatim?

**Common mistakes:** Dropping exceptions as low-similarity text; filling every token with duplicates; confusing evidence budget with total context.

</details>

<a id="qb-rag-010"></a>

## QB-RAG-010 — Cache answers with document-version awareness

| Field | Value |
|---|---|
| Category | RAG cache invalidation |
| Difficulty | Advanced |
| Target roles | RAG Engineer; AI Engineer |
| Round | Suggested scenario discussion and applied reasoning practice |
| Experience | 0–2 years; optional extension |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | RAG cache invalidation; contract reasoning, failure boundaries and evidence limits |
| Related IDs | QB-RAG-001 |
| Sources | [Canonical lesson](../02-Role-Based-Preparation/RAG-Engineer/Production_RAG_Architecture.md); [source register](PHASE5_AI_SOURCES.md) |

### Prompt

A public FAQ answer is cached by normalized question. The underlying refund policy changes from version 3 to version 4. A paraphrase still hits the old semantic cache. Design a cache validity contract for these public documents; tenant authorization is outside this particular prompt.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Associate cached answers with the source document versions and pipeline configuration that produced them, not just a question embedding. On a policy update, invalidate dependent entries through a document-to-cache dependency map or make lookup compare their dependency versions against current approved versions. If dependency coverage is incomplete, use a conservative namespace/version change and recompute rather than trusting stale entries. A semantic similarity match proposes reuse; it does not prove the question has identical scope, effective date or policy intent. Keep an explicit as-of date and language/locale when those affect the answer. Test a paraphrase before and after the version transition, partial ingestion of v4, rollback and a question with superficially similar wording but a different exception. Define whether the service blocks reuse during an inconsistent update or serves a clearly dated answer under a documented freshness contract.

**Explanation:** Even with public documents, stale answers can be wrong. Cache validity depends on evidence lineage and meaning, not solely on expiration time or embedding distance.

**Followups:** How would scheduled future policies affect the key? When is a short TTL sufficient and what guarantee does it actually provide?

**Common mistakes:** Treating semantic similarity as correctness; relying on TTL for immediate invalidation; omitting pipeline or source version changes.

</details>
