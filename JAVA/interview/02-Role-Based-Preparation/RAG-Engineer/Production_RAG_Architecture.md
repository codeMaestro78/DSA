# Production RAG lifecycle and permission boundaries

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Keep source authority and derived index state explicit. Ingestion owns versioned extraction/chunk provenance; an index holds searchable derivatives; authorization owns the current permission decision; generation consumes a bounded authorized evidence set. A database filter is useful only when trusted policy derives it and all retrieval branches enforce it.

Design refresh/deletion, cache invalidation, failed ingestion and model/index migration. A tombstone can block serving while asynchronous cleanup proceeds, if every read path checks the authoritative rule. Physical deletion from all replicas/backups needs its own retention/process contract. Do not promise instantaneous deletion everywhere from a single index delete call.

## Worked role scenario

A document is revoked during a long run. Check the chosen policy before delivering content/citations or executing tools, and prevent cached/search-derived material from bypassing it. Historical answers, summaries and traces may already contain the information, so define their visibility and retention too.

## Interview practice: RAGE-P3-12

- **Category / difficulty:** Production RAG lifecycle and permission boundaries / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What does a complete revocation design cover?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Source access, index candidates, reranker/context, generated/cached answers, memory, citation fetches, tool results and logs. State the consistency window, authoritative checks and cleanup evidence instead of assuming one filter is sufficient.

**Followup:** How do you migrate embeddings while preserving rollback and deletion?

**Common mistake:** Calling an index backup or metadata field a complete tenant-isolation guarantee.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Identify source/index/policy owners.
- [ ] Trace updates/revocation through every derivative.
- [ ] Define recovery, versioning and quality gates.

## Canonical lessons and references

- [Canonical production RAG](../../08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md).
- [Multi-tenant AI](../Full-Stack-AI-Engineer/Multi_Tenant_Architecture.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).
- [Pinecone: metadata filters](https://docs.pinecone.io/guides/search/filter-by-metadata).
- [Qdrant: filtering](https://qdrant.tech/documentation/search/filtering/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
