# RAG engineer preparation roadmap

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Learn the retrieval pipeline before adding agentic search. Start with authorized source ingestion and a small labeled query set. Establish a lexical or dense baseline, inspect missing relevant evidence, then test chunking, hybrid candidates and reranking one decision at a time. Generation quality cannot repair a relevant source that never enters context.

Prepare both quality and lifecycle reasoning: extraction/OCR, metadata, versioning, filtering, citation alignment, refresh and deletion. Advanced GraphRAG and multimodal retrieval belong after a baseline reveals a need. A vector-store brand alone does not explain relevance, permission safety or operational behavior.

## Worked role scenario

A policy assistant answers old rules. First determine whether the new source was ingested, indexed, authorized and retrieved. If the correct passage was present but ignored, investigate context/generation. Replacing the embedding model without identifying the stage can waste time and hide the actual stale-data issue.

## Interview practice: RAGE-P3-01

- **Category / difficulty:** RAG engineer preparation roadmap / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What evidence would you collect before optimizing a RAG system?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** A versioned corpus/query set, relevance labels, source permissions, candidate ranks, generation support checks, stage timings and freshness/deletion behavior. Separate offline and live evidence.

**Followup:** What would justify GraphRAG over ordinary retrieval?

**Common mistake:** Calling a few fluent answers a retrieval evaluation.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Trace ingestion through answer citations.
- [ ] Separate retrieval and generation errors.
- [ ] Use a baseline before advanced methods.

## Canonical lessons and references

- [RAG foundations](RAG_Fundamentals.md).
- [RAG evaluation](RAG_Evaluation.md).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
