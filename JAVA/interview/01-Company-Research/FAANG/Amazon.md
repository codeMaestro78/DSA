# Amazon: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “FAANG” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] The SDE preparation page frames software engineering around applying CS fundamentals, including data structures, algorithms, databases, distributed computing and general ML/AI. [Software development interview topics](https://www.amazon.jobs/content/en/how-we-hire/interview-prep/software-development-topics).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[OFFICIAL] The SDE guide describes an online coding assessment and says most technical interviews involve coding and design work; it explicitly directs candidates to the recruiter for applicable subjects. [Software development interview topics](https://www.amazon.jobs/content/en/how-we-hire/interview-prep/software-development-topics).

[OFFICIAL] Amazon publishes Leadership Principles including Ownership and Customer Obsession. The values page is not an internal interviewer scorecard. [Leadership Principles](https://amazon.jobs/content/en/our-workplace/leadership-principles).

**Scope limit:** The broad topics page does not establish the exact university, experienced, applied-scientist or India sequence. Its link for roles needing at least two years of experience is not a reason to impose that route on all graduates.

## Coding, design and role-specific preparation

[INFERRED] Keep Java/DSA fluency and correct executable solutions as the common core. Add object modeling, SQL/races and a simple service design for a software target. ML/research depth depends on the actual scientist or engineering posting rather than the Amazon brand.

[INFERRED] Separate undergraduate/university SDE, experienced SDE and scientist preparation. For a software role, a clear object/API contract can be more useful preparation than advanced model training; a scientist posting may justify a different allocation. Ask before borrowing another level's rubric.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Map real ownership, customer-impact, disagreement and failure episodes to the published principles without inventing metrics. Explain what you personally did and what the team verified.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

[CANDIDATE-REPORTED] A **2025-05-01** university SDE1 account describes an assessment followed by mixed coding, values and design conversations. The author later identifies Toronto/Vancouver and Vancouver in comments; exact interview dates are not given beyond January/April chronology. [Amazon SDE1](https://www.reddit.com/r/leetcode/comments/1kchshp/amazon_sde1/).

[CANDIDATE-REPORTED] Lakshman's **2025-04-18** article recounts an original **September 2019** undergraduate SDE-I process and says a **2025 PhD SDE-II** application felt similar. These levels and historical periods must not be merged into one 2026 route. [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/).

The reports show different routes, not a representative sample. No company questions, score cutoffs or frequency estimates are extracted.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Amazon questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. Implement a bounded LRU-style cache contract and test eviction, updates and zero/invalid capacity policy.
2. Specify an idempotent inventory reservation and explain why a retry cannot blindly repeat a debit.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. For the cache, track key/value ownership separately from recency; a hit and overwrite move that key to most-recent, and a new insertion over capacity evicts exactly the least-recent key. Declare zero and negative capacity behavior.
2. For reservations, an authoritative guarded decrement/claim and a stable operation identity must agree transactionally. A lost response does not justify a second debit; replay a matching request or reconcile unknown effects.

Use [the first related worked question](../../12-Interview-Question-Bank/SDE_Questions.md#qb-sde-003) and [the second related practice](../../12-Interview-Question-Bank/Database_Questions.md#qb-db-005) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

Evidence should include contract clarification, a correct implementation or trace, meaningful tests, complexity and an explicit concurrency/side-effect boundary. The drill is original and is not asserted as an Amazon question.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Confirm university versus experienced hiring, assessment sections, design depth, behavioral format and AI/documentation permission. A working sample does not reveal an internal pass threshold.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](../Big-Tech/PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-AM1 | [Software development interview topics](https://www.amazon.jobs/content/en/how-we-hire/interview-prep/software-development-topics) / Amazon | Not stated | Official preparation guide; High for SDE guidance; consult role recruiter |
| P4-BT-AM2 | [Leadership Principles](https://amazon.jobs/content/en/our-workplace/leadership-principles) / Amazon | Not stated | Official values; High for published principles; not hidden scoring |
| P4-BT-CA1 | [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/) / Aidan Lakshman | 2025-04-18 | Candidate personal account; Self-report; identity/context named; independent corroboration absent |
| P4-BT-CA3 | [Amazon SDE1](https://www.reddit.com/r/leetcode/comments/1kchshp/amazon_sde1/) / u/django0911 / Reddit | 2025-05-01 public post date | Candidate community account; Unverified self-report; university/Canada context, not universal |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
