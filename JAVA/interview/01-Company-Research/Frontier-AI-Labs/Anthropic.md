# Anthropic interview dossier

## Scope and company profile

[OFFICIAL] Anthropic's careers page emphasizes its mission and engineering/research overlap. It describes independent research and open-source work as useful application evidence, not a substitute for every posting's requirements. [Careers](https://www.anthropic.com/careers).

[OFFICIAL] The inspected **Forward Deployed Engineer, Paris** posting covers customer AI applications and deployment. It asks for **8+ years in a technical customer-facing context**, native French and fluent English, plus production LLM experience. This does not establish eligibility for a 0–2 year candidate or requirements for other locations. [Paris posting](https://job-boards.greenhouse.io/anthropic/jobs/5391021008).

## Published process and assistance rules

[OFFICIAL] The careers guide describes Google Meet interviews, live technical tools such as Colab/CodeSignal, permitted reference lookup, and discussion of experience/motivation. It supplies no universal technical loop count. [Careers](https://www.anthropic.com/careers).

[OFFICIAL] The separate AI policy, **updated 2025-07-10**, allows preparation and refinement of a candidate's own application, but says take-homes and live interviews must be completed without AI unless explicitly allowed. Lookup permission is therefore distinct from generative assistance. [Candidate AI guidance](https://www.anthropic.com/candidate-ai-guidance).

[UNVERIFIED] A fixed screening platform, mandatory research-paper round for every engineer, company-wide degree requirement and standard question distribution are not established. Follow the current invitation when it specifies a format.

## Role-specific interpretation

**[INFERRED]** Separate a software/application route from research/training and customer deployment:

| Route | Preparation emphasis | Evidence boundary |
|---|---|---|
| SDE/infrastructure | Data structures, debugging, concurrency and durable services | A working local program is not production operation |
| AI/full-stack | Context/tools, evaluation, streaming, auth and recovery | Schema-valid output is not automatically correct or authorized |
| ML/research engineer | Tensor shapes, training loss, baselines and experiment controls | A paper summary is not a reproduced result |
| Research scientist | Hypothesis, methodology and limitations | Qualifications depend on the actual position |
| FDE | Discovery, integration and customer communication | Senior Paris requirements must not be transferred to every role |

## Candidate experience evidence

[CANDIDATE-REPORTED] The correctly identified [Anthropic Glassdoor page](https://www.glassdoor.co.uk/Interview/Anthropic-Interview-Questions-E8109027.htm) supplied two readable accounts: **Front End Engineer**, reported **2026-10-21**, location/level/interview date unstated, described a recruiter conversation and expected followup; **Member of Technical Staff**, reported **2026-10-15**, San Francisco, interview **August 2026**, described a challenging but positive technical experience after a referral. Neither specifies a reproducible complete engineering loop.

Both are anonymous, self-selected and uncorroborated. They establish reports, not independently verified events or a cultural diagnosis. A different search result named “Anthropic Software”; it was not silently merged into this employer. No question text or alleged private assessment is reproduced.

## Preparation strategy

[INFERRED] Must prepare: independent live coding, reading/debugging a small program, precise project claims and a thoughtful explanation of motivation. Important: a valid evaluation split, failure analysis and clear safety/permission boundaries. Advanced research mathematics, distributed kernels or a paper presentation belong only when the role calls for them.

**Original drill — ANT-P4-01, Intermediate; company-neutral technical/project practice:** a document assistant cites a permitted source that does not support its answer. Diagnose without immediately changing the model.

<details><summary>Expected reasoning and followups</summary>

Separate ingestion, retrieval relevance, context selection, claim support and citation alignment. Check document version and access rights before exposing text. Design a small labeled regression set, with unsupported and unanswerable cases. Followups: which metric localizes the fault, can an LLM judge itself be wrong, and how would a rollback be chosen? Weak answer: treating fluent output or a citation URL as correctness proof.

</details>

## Behavioral preparation and recruiter clarification

[OFFICIAL] Careers describes low-ego communication and mission-oriented collaboration as stated values. This is company context, not proof of a hidden per-value scoring rubric. [Careers](https://www.anthropic.com/careers).

[INFERRED] Prepare a truthful disagreement, a changed belief and an example of checking an AI-assisted result. Ask which competencies, language/tool, current AI permissions, take-home scope, assessment accommodations and level apply. Confirm whether you are being assessed for application engineering, training/research or customer work before allocating all preparation to one domain.

## References

- [Careers](https://www.anthropic.com/careers), undated.
- [Candidate AI policy](https://www.anthropic.com/candidate-ai-guidance), updated 2025-07-10.
- [Paris FDE](https://job-boards.greenhouse.io/anthropic/jobs/5391021008), undated and requisition-specific.
- [Candidate reports](https://www.glassdoor.co.uk/Interview/Anthropic-Interview-Questions-E8109027.htm), report/interview dates above.

## Last verified

**2026-11-02**: accepted sources directly opened; publication/update dates are unstated unless explicitly recorded. This verifies public statements and the scoped reports, not every team's practice or continued vacancy. [Source metadata](PHASE4_SOURCES.md).
