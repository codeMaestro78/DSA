# Google: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “FAANG” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] The company overview links Search, Workspace and AI work. These establish broad product context, not the team or location of an individual requisition. [About Google](https://about.google/).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[OFFICIAL] The virtual guide describes coordinator-provided schedules and Meet links, equipment preparation, confidentiality and a prohibition on AI assistance during the interview. [Guidelines for virtual interviews at Google](https://services.google.com/fh/files/misc/general_virtual_interviews_candidate_resource.pdf).

[OFFICIAL] A separate guide discusses Google Drawings for some design questions. It does not say every new graduate receives a systems-design round. [Technical Virtual Interviews with Google Drawings](https://services.google.com/fh/files/misc/technical_virtual_interviews_candidate_resource.pdf).

**Scope limit:** The general hiring-process URL exposed navigation rather than a readable process body. Exact screens, round counts, hiring-committee rules, team matching, language limits and India variants were not officially established by that page in this pass.

## Coding, design and role-specific preparation

[INFERRED] For a general SWE target, prioritize problem clarification, data-structure invariants and clear followup reasoning. For a confirmed AI/ML posting, add evaluation, data splits and reproducibility; for infrastructure, add concurrency and failure diagnosis. These are preparation choices, not verified Google-wide rounds.

[INFERRED] Keep the Google SWE route distinct from research and from the PhD-specific account below. A search-product drill can exercise retrieval and permissions, but it does not establish that your interviewer will ask search architecture or ML theory.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Prepare a genuine example of revising your approach after evidence, and a disagreement where you represented the other view fairly. Do not treat a candidate label such as “Googliness” as a disclosed internal scorecard.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

[CANDIDATE-REPORTED] Aidan Lakshman's **2025-04-18** account concerns a **new-grad PhD SWE** route at Google and describes an accelerated process after another offer. Exact interview dates and location are not established here; it is not a conventional undergraduate new-grad loop. [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/).

[CANDIDATE-REPORTED] A **2025-06-01** Reddit account describes a new-grad application in September/October 2024, interviews in early December and later team conversations. Region is not established; the interview year is inferred from chronology rather than explicitly stamped. [Google New Grad SWE Interview Experience](https://www.reddit.com/r/leetcode/comments/1l0xqqd/google_new_grad_swe_interview_experience/).

The accounts both discuss technical and behavioral work, but their screening paths differ. Two self-selected reports do not establish frequency, difficulty distribution or a universal timeline. No private prompts are reproduced.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Google questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. An ambiguous stream-processing task: agree on late events, duplicates and output before selecting a structure.
2. A small search service: define the index source of truth and what changes when permission is revoked.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. Choose event-time versus ingestion-time semantics, allowed lateness, duplicate identity and whether a result is provisional before selecting a window structure. Ordered local input does not establish a global distributed count.
2. The source/index manifest owns content versions while current policy owns exposure authority. A revoked document cannot become eligible merely because an older content snapshot or cached answer retained it.

Use [the first related worked question](../../12-Interview-Question-Bank/Advanced_Questions.md#qb-adv-007) and [the second related practice](../../12-Interview-Question-Bank/RAG_Questions.md#qb-rag-010) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

A strong attempt preserves the agreed contract, explains why updates are safe, tests the followup and names the limits of any local measurement.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Obtain the exact role/level, coding environment, design expectations and current tool instructions. The official AI rule should not be weakened by a community report about preparation tools.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](../Big-Tech/PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-GO1 | [Guidelines for virtual interviews at Google](https://services.google.com/fh/files/misc/general_virtual_interviews_candidate_resource.pdf) / Google | Not stated | Official candidate PDF; High for stated environment/policy; not a full loop |
| P4-BT-GO2 | [Technical Virtual Interviews with Google Drawings](https://services.google.com/fh/files/misc/technical_virtual_interviews_candidate_resource.pdf) / Google | Not stated | Official candidate PDF; High for design-tool guidance when applicable |
| P4-BT-GO3 | [About Google](https://about.google/) / Google | Not stated; rolling page | Official company profile; High for profile links; no hiring requirements |
| P4-BT-CA1 | [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/) / Aidan Lakshman | 2025-04-18 | Candidate personal account; Self-report; identity/context named; independent corroboration absent |
| P4-BT-CA2 | [Google New Grad SWE Interview Experience](https://www.reddit.com/r/leetcode/comments/1l0xqqd/google_new_grad_swe_interview_experience/) / u/Opposite-March2698 / Reddit | 2025-06-01 public post date | Candidate community account; Anonymous/pseudonymous unverified self-report; region not established |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
