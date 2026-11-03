# Razorpay: India interview research

## Scope and evidence status

**Last verified: 2026-11-02.** This dossier is a source-bounded preparation record for an approximately 0–2 year candidate, not a guaranteed hiring loop, live-vacancy list or offer prediction. Publication dates are unstated unless explicitly recorded below or in [the source register](PHASE4_SOURCES.md). Access today does not make an undated page newly authored.

## Company profile and role context

[OFFICIAL] Razorpay’s careers page describes payments/product engineering and assessment themes around role fit, collaboration/working style and integrating AI into work. These headings are themes, not evidence of exactly three ordered rounds. [Careers](https://razorpay.com/careers/).

The same page’s AI emphasis is not permission to use an assistant in every assessment. A location, seniority threshold and current eligible opening must be verified from the actual requisition; this general page does not provide them for every engineering family.

## Interview process: what is and is not established

[UNVERIFIED] A universal round count, tool list, language requirement, machine-coding duration and junior/senior loop equivalence are not established. Distinguish the current careers themes from the single experienced-role account below.

A responsibility, culture statement or product description is not an interview stage. Do not infer round count from headings, code language from a team's stack, or a regional hiring rule from another country's experience. Confirm the exact requisition, level, location, work authorization and channel before tailoring practice.

## Role-specific preparation implications

**[INFERRED]** For backend/SDE, prioritize transaction boundaries, idempotent APIs, reconciliation and tests. For full-stack/AI application work, add resource authorization, workflow state and empirical evaluation. For ML/research or infrastructure roles, verify responsibilities and domain assessments separately. Knowledge of an AI coding tool does not replace independent explanation of generated code.

For all tracks, preserve the shared foundations: clarify the problem, derive a correct approach, test its boundary cases and explain complexity or operational tradeoffs. Advanced distributed/GPU/research depth is optional until the role calls for it. Your existing Java, TypeScript and Python work can supply practice evidence, but this dossier does not audit those projects or claim they meet this employer's requirements.

## Candidate experiences and uncertainty

[CANDIDATE-REPORTED] An account published 2026-08-15 is titled SDE2-or-above, reports attending machine-coding and high-level-design discussions, and describes AI use in part of coding plus independent work later. It also reports recruiter-described later stages it did not attend and uncertainty about one stage’s identity. Location, exact YOE and interview dates are unstated. [Razorpay SDE2 or above experience](https://www.reddit.com/r/leetcode/comments/1vow6f7/interview_experience_razorpay_sde_2_level_or_more/). It is not a verified complete loop or permission policy for other candidates.

No question-frequency estimate, pass threshold or company-specific question bank is established. One experience is a scoped observation, not a representative sample. Report date and interview date are distinct; missing context stays missing. Do not reproduce confidential prompts or rely on later comments as verified recruiter instructions.

## Original preparation exercise — attempt first

**ID:** IN4-RAZORPAY. **Category:** company-neutral engineering preparation. **Difficulty:** Intermediate, with optional advanced followups. **Target:** early-career SDE/backend, AI/full-stack or the selected role; suggested technical/project/design practice. This exercise is **[INFERRED]**, not attributed as an employer question.

**Prompt:** Build a small refund-request component using fictional data. Specify authorization, a stable operation key and behavior when the provider accepts but times out.

<details><summary>Expected reasoning, followups and evaluation</summary>

Record durable intent and an action identity, avoid unsafe automatic retries of uncertain effects, and use provider-supported idempotency or reconciliation. Validate input and state transitions. If AI helps generate code, review each change and test both ordinary and adversarial cases; keep the assessment’s actual permission rules.

**Followups:** How do you test an uncertain provider outcome without causing a duplicate refund? Which AI-generated change needs an independent check?

**Strong indicators:** a precise contract, a concrete correctness/authority mechanism, meaningful edge cases and a supported explanation. **Common mistakes:** naming tools without a flow, inventing throughput/quality evidence, or conflating a local test with production behavior. Related practice: [coding methodology](../../03-DSA-and-Coding/Coding_Interview_Methodology.md), [system-design framework](../../04-System-Design/System_Design_Interview_Framework.md) and [project proof](../../11-Project-Deep-Dive/Project_Explanation_Framework.md).

</details>

## Behavioral and project preparation

**[INFERRED]** Prepare one real instance where an AI-assisted output was wrong or incomplete and explain the verification. Avoid making AI usage itself the outcome; describe useful, checked engineering work.

Prepare one 30-second and one 2-minute account of real work: user/problem, your contribution, one tradeoff, what was checked and what remains uncertain. Team outcomes should not be claimed as sole personal work. Use academic or personal examples honestly when employment experience is limited.

## Recruiter confirmation and checklist

Which role/level and process apply now? Is AI required, allowed in selected parts or prohibited? Which assistant/docs are approved, and what must be completed independently? How is practical code correctness assessed?

- [ ] Verify a specific current posting and candidate eligibility.
- [ ] Confirm format, resources, language, duration and location before assuming them.
- [ ] Practice one unfamiliar coding task and one scoped project/design discussion.
- [ ] Keep official facts, candidate reports and original preparation separate.
- [ ] Refresh volatile details before an application or scheduled assessment.

## References and freshness

- [Careers](https://razorpay.com/careers/) — Razorpay; publication/update: Not stated; accessed 2026-11-02. Role fit, synergy and AI assessment themes, not three fixed rounds.
- [Razorpay SDE2 or above experience](https://www.reddit.com/r/leetcode/comments/1vow6f7/interview_experience_razorpay_sde_2_level_or_more/) — u/Winter-Belt-4512 on Reddit; publication/update: 2026-08-15 from source discovery metadata; accessed 2026-11-02. SDE2-or-above title; location/YOE/interview date unstated.

[Source metadata and rejected leads](PHASE4_SOURCES.md), [India index](README.md), [research methodology](../../RESEARCH_METHODOLOGY.md).
