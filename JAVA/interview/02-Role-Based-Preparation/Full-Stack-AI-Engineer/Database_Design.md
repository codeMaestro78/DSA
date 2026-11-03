# Data design for AI products

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Model business records, execution records and evidence separately. A conversation owns messages; a run references its input/configuration and status; retrieved evidence references source/version/offset; tool attempts record stable operation IDs and outcomes. Do not infer authority from a foreign key alone: relationships still require current actor/resource permission.

Store the minimum sensitive text needed for the product and debugging policy. Retention/deletion must cover derived chunks, embeddings, summaries, caches and logs as well as raw files. Versioning is useful for reproducibility, but is not a reason to retain forbidden data indefinitely.

## Worked role scenario

An answer cites chunk 42. The source PDF is replaced, and chunk 42 now identifies different text. Stable source-version identifiers and offsets avoid silently attaching the old answer to new evidence. If the source must be deleted, define what remains visible in the historical answer.

## Interview practice: FSAI-P3-09

- **Category / difficulty:** Data design for AI products / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you preserve citation meaning without violating deletion policy?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Use immutable source-version/provenance references within permitted retention, resolve access at read time and represent deleted/unavailable evidence explicitly. Do not substitute new content under an old citation ID.

**Followup:** Which records must be committed with an accepted run?

**Common mistake:** Treating vector storage as the only system of record.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Name canonical and derived data owners.
- [ ] Define versioned citation identity.
- [ ] Trace deletion through derived copies.

## Canonical lessons and references

- [Normalization](../../05-Database-and-Backend/Normalization.md).
- [RAG ingestion](../RAG-Engineer/Document_Ingestion.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
