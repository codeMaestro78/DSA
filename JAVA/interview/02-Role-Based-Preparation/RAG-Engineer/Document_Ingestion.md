# Document ingestion, extraction and provenance

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career RAG Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Ingestion turns source material into versioned retrievable evidence. Preserve source identity/version, extraction method, page/section/offset mapping, timestamps, permissions and deletion state. Make retries idempotent for the same source version; otherwise repeated uploads can create duplicate evidence and skew ranking.

PDFs can contain digital text, scanned images, tables and nontrivial reading order. OCR is not a perfect conversion. Tesseract's official quality guide discusses image processing and segmentation factors; inspect representative pages rather than assuming a parser's success status implies usable text. Quarantine failures and expose ingestion state instead of silently indexing empty output.

## Worked role scenario

A two-column PDF is extracted in alternating lines, merging unrelated sentences. The model produces a plausible but unsupported rule. Inspect source-to-text alignment, detect suspect layout, choose an appropriate extraction/OCR path and preserve provenance so citations can be checked against the original page.

## Interview practice: RAGE-P3-03

- **Category / difficulty:** Document ingestion, extraction and provenance / Intermediate.
- **Target role / experience:** RAG Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What metadata and checks make ingestion auditable?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Source/version/checksum, provenance offsets/pages, extraction status/method, permission scope, lifecycle timestamps and content-quality checks. Test duplicate uploads, malformed files, updates and deletions.

**Followup:** How do you delete all derived records after a source revocation?

**Common mistake:** Calling successfully parsed bytes correct document content.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Preserve versioned source provenance.
- [ ] Inspect layout/OCR failures.
- [ ] Handle retry/update/delete lifecycle.

## Canonical lessons and references

- [Canonical RAG flow](../../08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md).
- [Chunking](Chunking_Strategies.md).
- [Tesseract: input quality](https://tesseract-ocr.github.io/tessdoc/ImproveQuality.html).
- [Lewis et al.: original RAG paper](https://arxiv.org/abs/2005.11401).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
