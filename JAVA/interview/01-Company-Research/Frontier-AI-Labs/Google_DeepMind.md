# Google DeepMind interview dossier

## Profile and scope

[OFFICIAL] Google DeepMind's careers page describes work on AI models, products and scientific applications and lists locations including London, the Bay Area and Bangalore. Its role descriptions distinguish research engineering, software engineering and research science. These descriptions are not universal job requirements. [Careers](https://deepmind.google/careers/).

This dossier concerns DeepMind's published guidance, not an assumed copy of generic Google SWE hiring. Headquarters/legal-entity details and a complete current vacancy inventory were not investigated. For early career, establish the actual position, education/experience requirements, location and authorization before inferring fit from the employer's name.

## Official interview overview

[OFFICIAL] The page gives a **typical** overview: initial conversations, skills interviews, final interviews and a decision/offer. It describes a 30-minute recruiter introduction and two or three further skills calls, while explicitly warning that exact steps differ by role and invited candidates receive scoped preparation. Treat these numbers as the published overview, not a guaranteed loop. [Interview process](https://deepmind.google/careers/#interview-process).

The source does not establish one coding language, private rubric, universal online assessment or an AI-assistance policy for every technical role. Ask for the instructions attached to the particular interview. A Google virtual-interview document is not automatically a DeepMind-specific tool policy.

## Role distinctions and preparation

[OFFICIAL] Research engineers are described as bridging engineering, mathematics and experimentation; software engineers build and maintain reliable systems; research scientists formulate novel hypotheses and normally hold a PhD. The “normally” wording is descriptive, not an unconditional degree rule for every opening. [Role descriptions](https://deepmind.google/careers/).

**[INFERRED]** Translate those distinctions into preparation:

| Target | Main practice | What to explain |
|---|---|---|
| Software/full-stack/infra | Correct code, interfaces, tests and systems tradeoffs | A precise request/failure path and measured limits |
| Research engineer/ML | Experiment implementation, tensor shapes, optimization and baselines | What an ablation changes and what the result supports |
| Research scientist | Research question, alternatives and validity | Novelty, uncertainty and limitations of evidence |
| Applied AI or deployment | Evaluation, integration and product constraints | Why the chosen workflow meets a real requirement |
| FDE | Posting-specific customer responsibilities, if any | Do not assume this title or a standard customer round exists |

## Dated candidate accounts

[CANDIDATE-REPORTED] On the [public Glassdoor page](https://www.glassdoor.com/Interview/Google-DeepMind-Interview-Questions-E1596815.htm), a **Research Scientist**, London, reported **2026-10-31** about an **April 2026** interview, describing theory/research/project discussions. Another **Research Scientist** report dated **2026-10-19**, location/interview date unstated, described postponements and eventual role cancellation. These accounts differ materially in timing and outcome; neither supplies a population estimate.

The same page includes a **Strategy and Operations Manager** report posted in September 2026 about a **September 2022** event. It is an explicit counterexample to treating a recent publication date as a recent interview or transferring a nonengineering process to research engineering. No reported technical questions are copied into a company question bank.

## Practical preparation plan

[INFERRED] Must prepare a strong coding foundation and a truthful explanation of your role-relevant work. Important for ML/research: a reproducible baseline, valid data split, failure examples and a clear distinction between observed result and hypothesis. Optional advanced topics include distributed training and specialized mathematical areas when the requisition or recruiter confirms them.

**Original drill — GDM-P4-01, Intermediate/Advanced extension; company-neutral research-engineering discussion:** a reproduced classifier improves on one split but deteriorates on a second grouped split. Explain what you would investigate before announcing a new result.

<details><summary>Expected reasoning and followups</summary>

Check leakage, entity/time dependencies, preprocessing fit, baseline parity, seeds/configuration and metric population. Inspect uncertainty and slices. Repeated tuning on the second split can convert it into validation data. Followups: what would count as a meaningful ablation, and what result remains unsupported? Weak answer: attributing the change to model intelligence without examining the experiment.

</details>

## Behavioral and recruiter preparation

[INFERRED] Prepare a scientific disagreement, a failed experiment and a collaboration story. Explain which evidence changed your view. Ask the recruiter for role-specific competencies, permitted references/tools, language, research/paper expectations, locations/level and accommodations. Do not assume an early-career software applicant needs the same theoretical depth as a research-scientist posting.

## References

- [Official roles, locations and hiring overview](https://deepmind.google/careers/), undated current-access snapshot.
- [Public candidate reports](https://www.glassdoor.com/Interview/Google-DeepMind-Interview-Questions-E1596815.htm), dates/scopes preserved above; anonymous reports, not policy.

## Last verified

**2026-11-02**: accepted sources directly opened; publication/update dates are unstated unless explicitly recorded. This verifies public statements and the scoped reports, not every team's practice or continued vacancy. [Source metadata](PHASE4_SOURCES.md).
