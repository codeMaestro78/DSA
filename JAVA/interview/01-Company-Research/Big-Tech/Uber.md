# Uber: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “Big-Tech” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] Uber's current hiring guide emphasizes real-world impact, collaborative problem solving and role-specific details through team pages. This is the employer's stated framing, not independent evidence of every interviewer's behavior. [How we hire](https://jobs.uber.com/en/what-moves-us/how-we-hire/).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[OFFICIAL] The broad sequence covers application, talent-team and hiring-manager discussions, technical work for technical roles, a role-dependent functional exercise, team conversations and decision. The guide explicitly says roles differ and does not establish a universal panel count. [How we hire](https://jobs.uber.com/en/what-moves-us/how-we-hire/).

**Scope limit:** The old interviewing URL redirects to the new jobs site. Use the current target rather than assuming an older engineering-round list remains valid. No India-specific campus sequence or company-wide machine-coding requirement was verified.

## Coding, design and role-specific preparation

[INFERRED] Prepare API/state/data correctness and clear collaboration for a software role. For ML, use valid experiments and ranking/evaluation; for infrastructure, investigate queueing and service failure. Full-stack and customer-facing preparation follows the actual posting rather than an assumed common loop.

[INFERRED] An event-driven service exercise tests ordering and state recovery; an ML exercise tests data/label validity and outcome measurement. They can share a product scenario while requiring different evidence. Ask which specialty the team is evaluating before assuming a mixed loop.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Prepare a real cross-team tradeoff or ambiguous requirement story. Explain how you made decisions with incomplete information and checked whether they helped the user.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

No candidate account was accepted for this dossier in the bounded Phase 4 pass. That is a research gap, not evidence that a particular round exists or is absent. Public anecdotes for other teams, levels and regions must not fill these unknowns. No company-specific question list or difficulty frequency is established.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Uber questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. Design an update stream with out-of-order events and explain the authoritative current state.
2. Diagnose a high-latency endpoint without immediately adding caches or splitting services.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. Declare whether updates are ordered by event time, ingestion order or an authoritative revision. Reject/handle stale or duplicate versions explicitly, and preserve the replay/recovery identity through failures.
2. Measure complete request latency and errors, then use correlated stage evidence to distinguish queueing, compute, database and dependency hypotheses. Percentiles do not generally add, and a cache can change freshness rather than fix the cause.

Use [the first related worked question](../../12-Interview-Question-Bank/System_Design_Questions.md#qb-sys-002) and [the second related practice](../../12-Interview-Question-Bank/Advanced_Questions.md#qb-adv-010) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

Look for ordering and identity semantics, bounded recovery, evidence-driven hypotheses and a justified next measurement. Name what your small fixture cannot prove under real load.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Ask which team/level and which functional exercise applies, what the technical discussion measures, which tools are approved and what happens after the team interview. Broad sequence headings do not prove exact timing or hiring thresholds.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-UB1 | [How we hire](https://jobs.uber.com/en/what-moves-us/how-we-hire/) / Uber | Not stated | Official hiring guide; High for broad process; role-specific variation explicit |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
