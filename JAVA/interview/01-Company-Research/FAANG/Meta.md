# Meta: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “FAANG” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] The AI recruiting page distinguishes research-scientist and software-engineering opportunities and describes software work with research areas such as language and vision. A role-family page does not prove an opening or its eligibility. [Meta AI Join Us](https://ai.meta.com/join-us/).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[UNVERIFIED] The official SWE preparation endpoint returned HTTP 429 and the student-program endpoint was also rate-limited. This dossier therefore does not assert a refreshed official full-loop count, design format, DSA exclusion or AI-tool policy. A third-party mirror was not accepted as current first-party evidence.

**Scope limit:** Public self-reports provide narrow context below. They do not repair missing official verification or establish requirements for early-career, research, infrastructure or India hiring.

## Coding, design and role-specific preparation

[INFERRED] Practice independent coding with explicit boundary cases and concise explanation. Distinguish product architecture, infrastructure design and ML evaluation when selecting a target posting; do not assume a Product E4 experience transfers to an entry-level AI role.

[INFERRED] A Product E4 story and a PhD Systems/Infrastructure story describe different contexts. Decide whether the target is product software, infrastructure or research before selecting architecture depth; the readable AI recruiting page alone cannot settle that question.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Prepare true stories about collaboration, impact and changing your mind. A strong answer identifies personal action and evidence instead of repeating a company reputation or memorizing another candidate's story.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

[CANDIDATE-REPORTED] The **2025-09-13** Product **E4** report names the **Bay Area, US**, and describes recruiter, coding, behavioral/product-architecture and team conversations. The chronology uses relative days; exact calendar interview dates are absent. [Meta Interview Timeline and Experience — Product E4](https://www.reddit.com/r/leetcode/comments/1nfj6y1/meta_interview_timeline_experience_2025_software/).

[CANDIDATE-REPORTED] The **2025-01-30** Product E4 report describes coding, product-design and behavioral work, including a followup. Region and exact interview dates are unstated; “November” is not safely converted to a year. [Meta E4 Software Engineer Product Interview Experience](https://www.reddit.com/r/leetcode/comments/1idxid2/meta_e4_software_engineer_product_interview/).

[CANDIDATE-REPORTED] Lakshman's **2025-04-18** article labels its Meta role Research Scientist, Systems/Infrastructure, PhD, despite discussing SWE-like work. Preserve that distinction. [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/).

These independent descriptions vary in scope and followups. Their question-tag opinions are not accepted frequency data; no questions or confidential materials are reproduced.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Meta questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. Implement a paginated feed contract with stable ordering and explain inserts between pages.
2. Design an authorized notification preference update and trace a lost response followed by a retry.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. Order a feed by a deterministic tuple such as creation time and unique ID, encode the full cursor tuple and declare how later inserts/deletions are handled. Keyset pagination does not alone create a snapshot.
2. Authorize the exact preference resource under the current caller scope, preserve request meaning across retries and return a stable committed result. A stale version can conflict rather than overwrite a newer preference silently.

Use [the first related worked question](../../12-Interview-Question-Bank/Database_Questions.md#qb-db-008) and [the second related practice](../../12-Interview-Question-Bank/System_Design_Questions.md#qb-sys-002) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

Look for a correct invariant, time/space analysis, a counterexample to the first approach and a clear contract for duplicates, ordering and permission checks.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Ask for the current official candidate guide, exact level and design track, code-execution capability, language options and permission for each external tool. No DP ban or company-tag encounter probability is established.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](../Big-Tech/PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-ME1 | [Meta AI Join Us](https://ai.meta.com/join-us/) / Meta | Not stated | Official careers/team page; High for described role families; not a current vacancy guarantee |
| P4-BT-CA1 | [My SWE Interview Experiences: Amazon vs. Meta vs. Google](https://ahl27.com/posts/2025/04/faang-interviews/) / Aidan Lakshman | 2025-04-18 | Candidate personal account; Self-report; identity/context named; independent corroboration absent |
| P4-BT-CA4 | [Meta Interview Timeline and Experience — Product E4](https://www.reddit.com/r/leetcode/comments/1nfj6y1/meta_interview_timeline_experience_2025_software/) / u/Affectionate-Fun5698 / Reddit | 2025-09-13 public post date | Candidate community account; Unverified self-report; Bay Area/E4 scope |
| P4-BT-CA5 | [Meta E4 Software Engineer Product Interview Experience](https://www.reddit.com/r/leetcode/comments/1idxid2/meta_e4_software_engineer_product_interview/) / u/nocturnal_eve / Reddit | 2025-01-30 public post date | Candidate community account; Unverified self-report; region unspecified, process began in November |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
