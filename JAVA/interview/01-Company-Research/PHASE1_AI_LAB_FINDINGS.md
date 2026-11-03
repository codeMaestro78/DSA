# Phase 1: Frontier AI labs and forward deployed engineering

## Scope and evidence boundary

Last verified: **2026-11-02**. This is an initial research brief, not a completed company dossier. It covers public hiring guidance and one specific Palantir role. The company-specific facts below are `[OFFICIAL]`; study recommendations are `[INFERRED]`. No candidate reports or purported interview-question leaks were used. Undated pages are current-access snapshots, not evidence that their content was published in 2026.

## OpenAI

### Verified hiring guidance

- `[OFFICIAL]` The published example process includes application review, introductory conversations, skills assessments, final interviews, and a decision. Assessments vary by team and can include paired coding, take-home work, or technical tests. The guide explicitly says an individual's process can differ. [Interview guide](https://openai.com/interview-guide/)
- `[OFFICIAL]` Engineering evaluation includes solution design, code quality, performance, tests, communication, and collaboration. AI-tool permission varies by assessment; candidates receive instructions and should ask the recruiter when uncertain. [Interview guide](https://openai.com/interview-guide/)
- `[OFFICIAL]` The guide describes interest in demonstrated potential as well as existing specialists and says hiring is not credential-driven. This does not establish that any particular role accepts applicants with 0–2 years of experience. [Interview guide](https://openai.com/interview-guide/)

### Preparation implications and remaining questions

`[INFERRED]` Prepare two evidence-backed project walkthroughs: one explaining implementation and tests, another explaining a difficult decision and its measured outcome. Practice both independent coding and explicitly permitted AI-assisted work. Keep the distinction visible when presenting a portfolio.

Ask the recruiter which assessments apply to the specific requisition, which tools and languages are permitted, and whether the advertised level fits your experience. A public general guide does not establish a universal ML-theory round or company-wide DSA question distribution.

## Anthropic

### Verified hiring guidance

- `[OFFICIAL]` The careers page says technical interviews use live coding tools such as Colab and CodeSignal, with reference lookup permitted. Interviews also discuss experience and motivation. Calls are conducted through Google Meet. No universal round count is stated in this page. [Careers](https://www.anthropic.com/careers)
- `[OFFICIAL]` Anthropic encourages candidates with engineering backgrounds to apply as engineers and describes overlap between research and engineering work. This is general hiring guidance rather than an assurance about qualifications for a particular opening. [Careers](https://www.anthropic.com/careers)
- `[OFFICIAL]` AI may help refine a candidate's own application and support preparation. The separate candidate policy, updated **2025-07-10**, prohibits AI assistance during live interviews and take-homes unless explicitly permitted. Reference lookup and generative assistance therefore have different permissions. [Candidate AI guidance](https://www.anthropic.com/candidate-ai-guidance)

### Preparation implications and remaining questions

`[INFERRED]` Rehearse running and debugging small programs in a shared environment while explaining tradeoffs. For an AI application project, be ready to distinguish model behavior from retrieval, orchestration, and application bugs. Discuss empirical evidence behind a claimed improvement.

Before an assessment, confirm the current tool policy and role expectations. An old cached careers page naming a different coding platform should not override current instructions. This brief does not establish a standard question bank, research-publication requirement, or the process for every team.

## Google DeepMind

### Verified hiring guidance and role distinctions

- `[OFFICIAL]` The careers page gives a typical sequence of initial, skills, and final interviews followed by a decision. It explicitly says steps vary by role and that invited candidates receive role-specific preparation information. [Careers: interview process](https://deepmind.google/careers/#interview-process)
- `[OFFICIAL]` Its role descriptions distinguish research engineers, who combine engineering, mathematics, and ML experimentation to implement and evaluate ideas, from software engineers, who build reliable software at scale. Research scientists emphasize hypotheses and novel research; the description says they normally hold a PhD. These are role descriptions, not published interview syllabi. [Careers: roles](https://deepmind.google/careers/)

### Preparation implications and remaining questions

`[INFERRED]` Choose a target role before allocating study time. A research-engineering portfolio benefits from a reproducible experiment with baselines, ablations, and a discussion of failure. A software-engineering portfolio benefits from correctness, maintainability, performance, and operational reasoning. Neither recommendation proves the existence of a particular interview round.

Ask which mathematical and ML competencies the team assesses, whether a paper discussion is involved, and which programming language is expected. Google DeepMind's overview must not be replaced with a generic Google SWE process. The roles page does not establish current early-career vacancies or visa eligibility.

## Palantir: Forward Deployed Software Engineer

### Verified role responsibilities

- `[OFFICIAL]` The inspected Chicago requisition uses the title **Forward Deployed Software Engineer (FDSE)**. Responsibilities include working directly with customers, developing tailored applications, making architecture decisions, handling data, and delivering projects through deployment. This is role evidence, not interview-process evidence. [Chicago FDSE posting](https://jobs.lever.co/palantir/7ea66860-ae93-4139-9388-b7284cb33f37)
- `[OFFICIAL]` This posting requires at least one year of relevant post-college experience and lists examples of programming languages including Python, Java, C++, and TypeScript/JavaScript. It also describes customer-site travel. These conditions belong to this requisition and must not be generalized to new-graduate, government, or other-country positions. [Chicago FDSE posting](https://jobs.lever.co/palantir/7ea66860-ae93-4139-9388-b7284cb33f37)

### Preparation implications and remaining questions

`[INFERRED]` Add customer discovery to coding preparation: clarify the user's workflow, define a measurable outcome, identify data-access constraints, propose a small useful prototype, and explain the deployment and support plan. Rehearse explaining the same architecture to an engineer and a nontechnical stakeholder.

No Palantir interview-loop details were verified in this brief. Ask the recruiter about coding, decomposition or customer exercises, permitted tools, and the actual team's travel expectations. Do not infer interview rounds from a job description or equate FDSE, deployment strategist, and infrastructure-engineering titles.

## Original practice tasks

The following are curriculum exercises, not questions attributed to these companies:

1. **Engineering:** implement a bounded retry wrapper, explain which failures are retryable, and test timeout and cancellation behavior. Strong performance separates safe retries from duplicated side effects.
2. **Research engineering:** reproduce a small classifier experiment, report the split and baseline, then investigate whether an apparent gain survives leakage checks and repeated runs.
3. **AI application:** diagnose a cited answer supported by the wrong document. Separate retrieval relevance, source permissions, citation alignment, and generation correctness.
4. **Customer engineering:** a customer requests an agent but cannot grant write access to its CRM. Discover the actual outcome needed, propose a read-only trial, and agree on success and escalation criteria.

For each, record assumptions, implementation or experiment evidence, failure modes, and one tradeoff. Score the evidence you produced rather than confidence of delivery.

## References and source metadata

All sources were directly opened on **2026-11-02**. Reliability is high for the publisher's stated guidance or the specific requisition; none proves every team's actual process. Publication/update dates were not displayed unless noted.

| ID | Title and organization | URL | Publication/update date | Type | Related topic and limitation |
|---|---|---|---|---|---|
| AI-01 | Interview guide — OpenAI | [Source](https://openai.com/interview-guide/) | Not stated | Official hiring guide | General process; team variation explicit |
| AI-02 | Careers — Anthropic | [Source](https://www.anthropic.com/careers) | Not stated | Official careers page | Technical interview environment and role guidance |
| AI-03 | How to collaborate with Claude during our hiring process — Anthropic | [Source](https://www.anthropic.com/candidate-ai-guidance) | 2025-07-10 | Official candidate policy | AI permissions; reassess before an interview |
| AI-04 | Careers at Google DeepMind — Google DeepMind | [Source](https://deepmind.google/careers/) | Not stated | Official careers and hiring page | Typical stages and distinct role descriptions |
| AI-05 | Forward Deployed Software Engineer — Palantir | [Source](https://jobs.lever.co/palantir/7ea66860-ae93-4139-9388-b7284cb33f37) | Not stated | Official employer job posting on Lever | Chicago requisition only; can expire |

## Phase 4 research backlog

- Build individual dossiers with role, level, location, and requisition scope attached to each claim.
- Verify current role descriptions for AI deployment work at OpenAI and Anthropic rather than assuming Palantir's title or responsibilities transfer directly.
- Add independently corroborated candidate experiences only when dates, role context, and provenance are available; keep them separate from official guidance.
- Extend frontier-lab coverage to xAI, Mistral AI, Cohere, AI21 Labs, Hugging Face, and Perplexity.
