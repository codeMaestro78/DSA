# OpenAI interview dossier

## Scope and company profile

[OFFICIAL] OpenAI describes itself as an AI research and deployment company. The inspected **Forward Deployed Software Engineer – SF** posting concerns San Francisco customer/full-stack delivery, not an entry-level generalist role. It requests **7+ years of professional full-stack experience excluding internships** and customer-site travel. This is a requisition-specific example, not a minimum for every OpenAI job. [Posting](https://openai.com/careers/forward-deployed-software-engineer-sf-san-francisco/).

[OFFICIAL] The hiring guide values demonstrated potential, collaboration and communication rather than credentials alone. That philosophy does not establish eligibility for a particular opening or waive its requirements. [Interview guide](https://openai.com/interview-guide/).

## Published process and tool policy

[OFFICIAL] The guide presents an example sequence: application review, introductory discussions, skills assessments, final interviews and decision. Assessments vary by team and may include paired coding, take-homes or tests. It says the actual experience can differ. Engineering evaluation includes solution quality, performance, testing and collaboration. AI-tool permissions vary by assessment and are supplied in preparation materials. [Interview guide](https://openai.com/interview-guide/).

No fixed loop is inferred for a graduate, research scientist, FDE or infrastructure applicant. This dossier deliberately omits a universal round count, question distribution and allowed-language list. Confirm the exact invitation and any tool restrictions. Permission to use AI at work does not establish permission during a particular assessment.

## Role-specific preparation interpretation

The following is **[INFERRED]**, not a published interview syllabus:

| Target | Prioritize | Evidence to bring |
|---|---|---|
| SDE/backend | Correct coding, tests, APIs and concurrency | An implementation you can trace under edge cases |
| Applied AI/full-stack | Model/application boundaries, evaluated output, streaming and auth | A project with an explicit failure and measurement scope |
| ML/research engineering | Experiments, baseline, tensor reasoning and reproducibility | A controlled result and a credible ablation |
| Research science | Hypotheses, methods and limitations | Relevant research contribution; check posting qualifications |
| FDE | Discovery, integration and customer acceptance | True customer/team evidence; do not use the senior SF posting as proof of early-career fit |
| Infrastructure | Runtime, resource budgets, recovery and observability | A bounded incident/capacity explanation |

## Candidate reports and uncertainty

[CANDIDATE-REPORTED] Two public anonymous entries were readable on the [Glassdoor company page](https://www.glassdoor.com/Interview/OpenAI-Interview-Questions-E2210885.htm): a **Software Engineer**, reported **2026-10-29**, San Francisco, described screening followed by technical/design and team-dependent discussions; its interview date and level were unstated. A **Support Engineer**, reported **2026-10-27**, location/interview date unstated, described scheduling/communication problems. These are different roles and self-selected accounts; neither establishes a universal loop, employer intent or question frequency.

The first report broadly resembles role variation in official guidance, while the second illustrates one person's logistics experience. Do not average their timings or treat them as independent calibration of hiring difficulty. No confidential technical prompt is reproduced here.

## Practical preparation plan

[INFERRED] Must prepare: independent coding with tests; one detailed project explanation; a truthful discussion of collaboration and feedback. Important for an AI role: a labeled evaluation example separating model error from retrieval/tool/application failure. Advanced extensions depend on the requisition: distributed training, kernels, deep RL or research presentation are not assumed for every applicant.

**Original drill — OAI-P4-01, Intermediate; company-neutral coding/project discussion; early career:** implement or explain a bounded job executor that persists accepted intent and handles a lost response. State contract, invariants, retries and validation evidence before optimizing it.

<details><summary>Expected reasoning and followups</summary>

Separate accepted job identity, worker attempt and external action. A timeout does not prove an effect failed; use a supported idempotency/reconciliation mechanism. Show one edge test and one crash trace. Followups: what is durable, who owns cancellation, and which result is measured rather than hypothetical? Weak answer: claiming a model or queue supplies exactly-once business effects automatically.

</details>

## Behavioral preparation and recruiter questions

[INFERRED] Use real examples of receiving difficult feedback, testing an assumption and changing a technical decision. Connect interest to work you actually understand rather than inventing mission alignment. Ask: which requisition/level/team, which assessments, language/editor, documentation/AI rules per stage, design/domain depth, accommodations and expected timeline? Recheck the actual posting before applying.

## References

- [Official interview guide](https://openai.com/interview-guide/), undated.
- [Official SF FDSWE posting](https://openai.com/careers/forward-deployed-software-engineer-sf-san-francisco/), undated; senior example only.
- [Public candidate entries](https://www.glassdoor.com/Interview/OpenAI-Interview-Questions-E2210885.htm), report dates above; anonymous and uncorroborated.

## Last verified

**2026-11-02**: accepted sources directly opened; publication/update dates are unstated unless explicitly recorded. This verifies public statements and the scoped reports, not every team's practice or continued vacancy. [Source metadata](PHASE4_SOURCES.md).
