# Salesforce: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “Big-Tech” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] The current Tech and Product page distinguishes software, infrastructure, security and Forward Deployed Engineers. It describes FDEs building/deploying custom agentic solutions; that establishes a role family, not an entry-level vacancy or an interview round. [Tech and Product Careers](https://www.salesforce.com/company/careers/teams/tech-and-product/).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[OFFICIAL] Current general guidance discusses experience/behavioral preparation, virtual conversations through Google Meet, and an onsite before an offer. Recruiters provide details; the page is not a published software-engineering panel rubric. [How Salesforce Hires](https://www.salesforce.com/company/careers/culture/how-we-hire/).

**Scope limit:** The former university-tech URL now redirects to a current team page. Search-indexed older HackerRank/AMTS wording and a historical Trailhead sequence are not accepted as refreshed universal guidance. A regional or early-career exception must come from the actual program/recruiter.

## Coding, design and role-specific preparation

[INFERRED] For backend/SWE, work on tenant-aware APIs, database constraints and tests. For AI, evaluate agent/tool boundaries and failure behavior; for FDE, add customer discovery and integration. ML/research and infrastructure preparation depends on an actual posting rather than assuming every engineer works on the same product layer.

[INFERRED] Multi-tenant backend design and customer-deployed agent integration share permission boundaries but differ in delivery responsibilities. A strong FDE practice artifact includes discovery and acceptance; a strong SWE artifact includes the implemented invariant and tests. The team page does not equate their interview formats.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Prepare specific true examples of collaboration, impact and learning. Link the result to evidence and distinguish individual work from a team outcome; branding around agents does not grant interview AI permission.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

No candidate account was accepted for this dossier in the bounded Phase 4 pass. That is a research gap, not evidence that a particular round exists or is absent. Public anecdotes for other teams, levels and regions must not fill these unknowns. No company-specific question list or difficulty frequency is established.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Salesforce questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. Design a multi-tenant resource endpoint and show why valid login is insufficient object authorization.
2. Translate an ambiguous customer automation request into a read-only prototype with acceptance criteria and a safe deployment plan.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. A valid session authenticates a principal, not access to every object. Resource lookup, tenant scoping, mutation and cached/replayed results must preserve current authority at the actual exposure/action boundary.
2. Choose a measurable customer outcome, a reviewed baseline and a read-only pilot within approved data boundaries. Name the acceptance and handoff owner; a demo does not imply permission to enable autonomous effects.

Use [the first related worked question](../../12-Interview-Question-Bank/AI_Engineer_Questions.md#qb-ai-003) and [the second related practice](../../12-Interview-Question-Bank/FDE_Questions.md) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

A strong solution binds current principal/tenant server-side, checks resource/action permission and explains data isolation through caches/background work. In the customer exercise, show scope and evidence without inventing customer experience.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Ask which SWE/FDE/AI track, level, location and assessment format apply; confirm the onsite rule and any accommodations, approved tools, and exact entry-level eligibility. Do not infer an AI-assisted assessment from agentic-product language.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-SF1 | [How Salesforce Hires](https://www.salesforce.com/company/careers/culture/how-we-hire/) / Salesforce | Not stated | Official hiring guide; High for general process/onsite statement; not exact technical panel |
| P4-BT-SF2 | [Tech and Product Careers](https://www.salesforce.com/company/careers/teams/tech-and-product/) / Salesforce | Not stated; replaces old university-tech route | Official team page; High for SWE/FDE/infra/security role descriptions |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
