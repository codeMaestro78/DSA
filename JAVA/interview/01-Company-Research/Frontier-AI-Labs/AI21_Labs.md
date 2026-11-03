# AI21 Labs / AI21 interview dossier

## Profile and verified role scope

[OFFICIAL] The inspected AI21 careers page lists Tel Aviv-Yafo technical work. Its **Deep Learning Engineer** requisition concerns training infrastructure, model efficiency, serving and post-training experiments; it requests **3+ years of production software experience**, strong Python and relevant degree/background. This is a specific experienced role, not a universal company minimum or an entry-level opening. [Careers](https://www.ai21.com/careers/), [Deep Learning Engineer](https://www.ai21.com/careers/deep-learning-engineer/CC.372/).

[OFFICIAL] The posting distinguishes distributed training, post-training/RL, inference systems and efficiency as areas of expertise. These are responsibilities/qualifications; they do not prove a separate interview round for each specialty. [Posting](https://www.ai21.com/careers/deep-learning-engineer/CC.372/).

## Interview-process proof boundary

[UNVERIFIED] The inspected official pages did not establish a complete engineering hiring loop, fixed coding language for assessments, take-home duration, AI-tool policy or hiring-committee procedure. Job requirements must not be rewritten as stage counts. Ask the recruiting contact for the actual assessment outline.

For a 0–2 year candidate, use these responsibilities to understand a possible longer-term training/inference route, while separately verifying internships or junior roles. This dossier does not promise a current suitable vacancy or a waiver of the posted experience requirement.

## Candidate experiences with dates preserved

[CANDIDATE-REPORTED] On the [public Glassdoor page](https://www.glassdoor.com/Interview/AI21-Labs-Interview-Questions-E5832469.htm), an **ML/CV/DL Engineering** account posted **2026-09-20**, location/level/interview date unstated, described recruiter, coding/ML and practical work. A **Senior DevOps Engineer** report posted **2026-06-10** explicitly concerned an interview in **July 2023**, Gdańsk, and discussed infrastructure/observability. These are anonymous and not independently corroborated.

The second report is historically scoped despite its recent posting date. The first lacks enough level/location detail to apply to every engineer. Neither establishes a current universal technical exercise, timing or question list; alleged task details are not copied here. Different specialties and event dates plausibly explain differences, but this is not evidence that one process replaced another.

## Role-specific preparation interpretation

The following is **[INFERRED]**:

| Target | Prepare | Boundary |
|---|---|---|
| SDE/backend | Python fluency, testing, profiling and reading unfamiliar code | Posted production responsibilities do not prove an algorithm-only loop |
| ML/research engineer | Training data/loss/gradients, tensor shapes and reproducibility | Running an API is not training infrastructure experience |
| Inference/infra | Batching, memory, latency/throughput and failure diagnosis | Capacity arithmetic is not a hardware benchmark |
| Applied AI/full-stack | Evaluated requests, APIs and authorized data flows | No standard full-stack role/loop verified in this snapshot |
| Research scientist/FDE | Actual requisition requirements first | Titles/assessment formats not inferred from the deep-learning role |

## Preparation plan and original drill

[INFERRED] Must prepare correct implementation and a truthful project/experiment walkthrough. Important for the inspected technical direction: model-training mechanics, numerical reasoning and debugging. Optional advanced extensions include distributed collectives, kernel efficiency and RL rollout/trainer interaction when the target job calls for them. A small valid experiment is better preparation evidence than claiming cluster expertise without a run record.

**Original drill — AI21-P4-01, Advanced extension; company-neutral training-systems discussion:** a model resumes from a checkpoint and its loss suddenly diverges. Explain an investigation before changing the optimizer arbitrarily.

<details><summary>Expected reasoning and followups</summary>

Verify data/split/preprocessing, model and optimizer/scheduler states, progress/seed, dtype/reduction and configuration. Compare a controlled small step with recorded parameters and gradients. Distinguish numerical failure, corrupted/incomplete artifact and changed input distribution. Followups: what constitutes a complete checkpoint, and how would you prove the proposed fix? Weak answer: assuming loaded weights alone guarantee an identical resumed training procedure.

</details>

## Behavioral preparation and recruiter questions

[INFERRED] Prepare a failed experiment, a cross-team debugging decision and an example of improving code reliability. Do not claim company values or a dedicated culture stage absent primary evidence. Ask which current role/level/location is involved, which training versus serving competencies apply, format/language, allowed references/AI assistance, take-home scope and how any practical exercise will be evaluated.

## References

- [Official careers](https://www.ai21.com/careers/), undated current snapshot.
- [Specific Deep Learning Engineer posting](https://www.ai21.com/careers/deep-learning-engineer/CC.372/), undated.
- [Dated candidate reports](https://www.glassdoor.com/Interview/AI21-Labs-Interview-Questions-E5832469.htm), report/event distinction above; anonymous accounts.

## Last verified

**2026-11-02**: accepted sources directly opened; publication/update dates are unstated unless explicitly recorded. This verifies public statements and the scoped reports, not every team's practice or continued vacancy. [Source metadata](PHASE4_SOURCES.md).
