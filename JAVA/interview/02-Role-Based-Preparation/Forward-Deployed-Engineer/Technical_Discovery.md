# Technical discovery under ambiguity

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Forward Deployed Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Discovery reduces uncertainty before implementation. Ask about the current workflow, user decision, failure cost, inputs, outputs, systems of record, data permissions and acceptance process. Separate the requested solution from the actual problem: “an agent” may be a proposed implementation for faster document lookup.

Maintain a fact/assumption/question ledger. Prioritize questions whose answers change architecture, scope or feasibility. A well-run discovery session ends with a bounded next step and named owners for unresolved dependencies, not a long unprioritized list of possible features.

## Worked role scenario

The customer asks for accurate policy answers but can only export scanned PDFs weekly. This changes extraction quality, freshness and source-citation expectations. Confirm how urgent changes are distributed and whether “current” means latest export or a live authoritative policy.

## Interview practice: FDE-P3-03

- **Category / difficulty:** Technical discovery under ambiguity / Intermediate.
- **Target role / experience:** Forward Deployed Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** Which five questions most change the design of this policy assistant?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Who uses it and for what decision; which source is authoritative; how changes/deletions propagate; who may access each document; and how acceptable/unsupported answers are reviewed. Follow with integration and operational constraints.

**Followup:** What would make you stop prototyping and resolve access first?

**Common mistake:** Accepting “accurate” or “real-time” as fully specified requirements.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Identify workflow and authoritative source.
- [ ] Record assumptions separately from facts.
- [ ] End with a bounded decision and owner.

## Canonical lessons and references

- [Requirements](../../04-System-Design/Functional_and_Non_Functional_Requirements.md).
- [Document ingestion](../RAG-Engineer/Document_Ingestion.md).
- [Databricks: London Forward Deployed Engineer](https://www.databricks.com/company/careers/professional-services-operations/forward-deployed-engineer-8921476002).
- [Tesseract: input quality](https://tesseract-ocr.github.io/tessdoc/ImproveQuality.html).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
