# Interview preparation strategy

## Pick a target using evidence

[INFERRED] Start with early-career backend/SDE and applied AI/full-stack AI roles. Keep a shortlist of postings with title, location, experience requirements, work authorization, responsibilities, interview guidance and date checked. Do not assume an AI lab offers an entry-level role merely because it has a careers page. Do not infer eligibility from a title alone.

For each application ask the recruiter: Which skills and round formats apply to this exact requisition? Which language and editor? Is external documentation allowed? What is the policy on AI assistance? Is there a take-home and a time budget? Does the design round concern components, distributed systems or ML? Request accommodations early if needed.

Use [the company findings](01-Company-Research/README.md) as initial evidence. They deliberately leave unsupported stages unknown.

## Allocate twelve hours

| Work | Hours/week | What counts as evidence of progress |
|---|---:|---|
| DSA and coding | 4 | Independent attempts, invariants, counterexamples and delayed re-solves |
| Backend and system design | 2.5 | API/schema decisions, failure traces and justified alternatives |
| AI/RAG/ML | 2 | A controlled experiment or evaluation design, rather than framework vocabulary |
| Cloud and debugging | 1 | Reproducible observation → hypothesis → check → mitigation reasoning |
| Projects, behavior and communication | 1.5 | Recorded project pitch, verified claim or truthful story with a clear personal contribution |
| Review and company targeting | 1 | Error-log revision, source refresh and next-week decision |

Reallocate toward a confirmed near-term loop. An ML-heavy posting needs more math/experiments; a frontend role needs UI implementation practice; an FDE loop needs discovery and customer explanation. Keep a minimum weekly coding session throughout.

## Separate study from performance

Reading is input. Readiness evidence requires an unfamiliar prompt, a time budget, and an observable answer. Log whether a hint was needed and whether the result was independently tested. An accepted answer after copying a solution is a learning session, not independent performance.

Start with two untimed attempts per unfamiliar pattern. When the invariant is clear, use a **self-imposed** 40–45 minute coding session. This is a practice budget, not a verified employer duration. Review timeouts as diagnostic data: distinguish missing concept, language friction, bugs and unclear communication.

For architecture, rehearse requirements → contracts/data → one simple design → critical flow → failure and recovery → tradeoffs. Avoid adding a queue, cache or vector database without identifying the constraint it solves.

## Project proof and storytelling

Prepare two real projects. For each, record what you implemented, an architecture diagram, one rejected alternative, one bug, one measured improvement and the limits of the evidence. Use “local test,” “illustrative workload” or “production measurement” accurately. If no benchmark exists, explain how you would measure instead of inventing a speedup.

Create 30-second, 2-minute and 5-minute explanations. Expect followups: Why this database? What invariant breaks under concurrency? What happens after a timeout? How does a revoked document disappear from retrieval? Which evaluation examples fail? What is your contribution versus the team's?

Create six truthful behavioral story outlines: ownership, disagreement, failure, ambiguous requirement, delivery tradeoff and customer/user impact. Use situation, task, personal action, result and reflection. Quantify only outcomes you can substantiate; adapt the structure to real experiences.

## Error log and feedback loop

For every weak attempt record: first incorrect assumption, missing concept, smallest counterexample, corrected explanation, next variation and review date. Keep personal assessment separate from the editorial completeness of this knowledge base in [the tracker](PROGRESS_TRACKER.md).

If two consecutive attempts fail for the same prerequisite, pause the advanced topic and practice that prerequisite. If repeated mocks expose one critical weakness, address it before broadening the company list.

## References

- [Master roadmap](MASTER_INTERVIEW_ROADMAP.md) — learning gates.
- [Methodology](RESEARCH_METHODOLOGY.md) — boundaries between evidence and inference.
- [Company source briefs](01-Company-Research/README.md) — scope-specific hiring evidence.

## Last verified

2026-11-02: personalized editorial strategy; weekly availability and proficiency have not been measured.
