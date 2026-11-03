# Adobe: engineering interview dossier

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier separates directly inspected official material, candidate self-reports and original preparation advice. It is not a guarantee of current vacancies, eligibility or a fixed interview loop. The catalog grouping “Big-Tech” is an editorial navigation choice, not an official hiring category.

## Company profile and role context

[OFFICIAL] The hiring page describes creativity/collaboration work and links intern/graduate opportunities. An early-career program entry point does not prove that a suitable role is open for every location or background. [How we hire at Adobe](https://careers.adobe.com/us/en/hiring-process).

**Location/early-career boundary:** Use the exact requisition for office, work authorization, level and experience. Headquarters and a complete engineering-location roster were not independently researched here; no relocation, remote-work or visa promise follows from this dossier. Explicit program/posting geography above remains binding.

## Official process and technical evidence

[OFFICIAL] The broad route includes application, Talent discussion, hiring manager, role-dependent skills/team assessment and offer. Technical examples include coding, design or portfolio review; the wording is not a promise that each candidate receives all three. [How we hire at Adobe](https://careers.adobe.com/us/en/hiring-process).

[OFFICIAL] Adobe permits AI-supported refinement while requiring the candidate's own voice and transparency. It says not to use AI recording, live-scribing or conversation-prompting tools in hiring conversations unless invited. [AI and your hiring experience](https://careers.adobe.com/us/en/ai-and-your-hiring-experience).

**Scope limit:** Keep application/preparation assistance distinct from live interview permission. The inspected pages do not establish one SWE round count, standard language, difficulty distribution or exact early-career regional loop.

## Coding, design and role-specific preparation

[INFERRED] For software/full-stack roles show implementation quality, input/error handling and user lifecycle. For ML/AI explain task evaluation and model/application boundaries; for infrastructure, resource/reliability reasoning. A portfolio is supporting evidence rather than automatic proof of all these skills.

[INFERRED] A portfolio helps explain implemented work; a coding/design task asks for observable reasoning in the current session. Prepare both only when the role supports them. For AI-assisted work, retain what you changed and how you checked it rather than presenting generated output as independent expertise.

Use [the shared role comparison](../../02-Role-Based-Preparation/Role_Comparison.md) for the general taxonomy. This dossier adds employer-source scope and targeted practice; it does not assert every role title is offered or every branch is assessed in one loop.

## Behavioral and project evidence

[INFERRED] Use an authentic creativity/learning or cross-functional disagreement episode. Explain an alternative and actual feedback. Do not let an AI-generated narrative replace your own experience.

For each resume claim, retain the artifact or measurement, environment, your contribution and the limit. A local test, reference diagram or AI-generated draft is not evidence of live deployment or measured production quality. Use [the project framework](../../11-Project-Deep-Dive/Project_Explanation_Framework.md) and [practice rubrics](../../00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) to score observable behavior rather than predict an offer.

## Public candidate experiences

No candidate account was accepted for this dossier in the bounded Phase 4 pass. That is a research gap, not evidence that a particular round exists or is absent. Public anecdotes for other teams, levels and regions must not fill these unknowns. No company-specific question list or difficulty frequency is established.

## Original preparation exercises

All prompts below are **[INFERRED], company-neutral** exercises selected for this preparation path. They are not reported Adobe questions. Suggested target: early-career software/AI candidate; practice context: coding, design or project discussion, adjusted to the actual role. Difficulty: Intermediate, with advanced failure/concurrency followups optional.

1. Review a small document-processing feature with malformed input, cancellation and partial failure tests.
2. Explain one AI-assisted coding task, the defects you detected and the checks that remain incomplete.

<details><summary>Expected reasoning and review criteria — attempt first</summary>

**Exercise-specific review:**

1. A document pipeline needs explicit malformed-input, resource-lifetime and partial-output states. Cancellation intent must not label an already-published external artifact undone; gate publication on acceptance.
2. For the AI-assisted task, state your actual contribution, the generated defect and the check you performed. Preserve unexecuted or unreviewed areas as limits instead of presenting fluent generated code as verified.

Use [the first related worked question](../../12-Interview-Question-Bank/Full_Stack_AI_Questions.md) and [the second related practice](../../12-Interview-Question-Bank/Behavioral_Questions.md#qb-beh-010) for concrete mechanisms and boundaries. These links are original company-neutral practice, not evidence of employer question ownership.

Judge whether the candidate can explain the contract, reproduce the bug and substantiate the result. A polished artifact without tests or understood decisions should not be called independently validated work.

Ask a followup that changes an assumption: input size, duplicate delivery, permission revocation, concurrent update or dependency failure. The candidate should revisit the reasoning rather than patch a memorized solution. Record hints and whether code was actually executed; a plausible explanation is not a passed runtime check.

Common mistakes: skipping the contract, omitting a failing boundary, reporting unsupported metrics, or claiming the drill is a confirmed company question. Related practice: [DSA methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [design framework](../../04-System-Design/System_Design_Interview_Framework.md) and the selected [role track](../../02-Role-Based-Preparation/README.md).

</details>

## Recruiter questions and refresh checklist

Confirm which assessment/portfolio applies, time budget, supported language/editor, current AI invitation for each task and location/eligibility. Permission to refine prose is not permission to use live conversation prompting.

- [ ] Save the exact role, level, location and verification date.
- [ ] Obtain the applicable format, evaluated skills and allowed resources.
- [ ] Keep official guidance separate from self-reports and study inferences.
- [ ] Refresh tool policy and eligibility before the scheduled assessment.
- [ ] Preserve unresolved conflicts rather than inventing a universal process.

## References and source metadata

All accepted sources below were directly opened **2026-11-02**. “Not stated” is a publication-date gap, not a claim of recent authorship. Candidate reports remain unverified self-reports even when the page is readable. Full group metadata is in [the Phase 4 register](PHASE4_SOURCES.md).

| ID | Title / publisher | Publication/update | Type and reliability scope |
|---|---|---|---|
| P4-BT-AD1 | [How we hire at Adobe](https://careers.adobe.com/us/en/hiring-process) / Adobe | Not stated | Official hiring guide; High for broad stages and role-dependent assessment |
| P4-BT-AD2 | [AI and your hiring experience](https://careers.adobe.com/us/en/ai-and-your-hiring-experience) / Adobe | Not stated | Official candidate AI policy; High for stated authenticity and no uninvited live-assistance policy |

[Group index](README.md), [company index](../README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).

Editorial review: **2026-11-03** — exercise-specific review guidance added; employer-source verification dates above are unchanged.
