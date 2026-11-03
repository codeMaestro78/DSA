# SDE interview roadmap

## Purpose and prerequisites

Prepare to independently specify, implement, test and explain a small program, then extend the same contract into an API or concurrent component. Role titles and topic lists do not establish a universal interview loop. Choose one target posting and confirm level, assessment format, language and assistance policy.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../03-DSA-and-Coding/DSA_Master_Roadmap.md) when a mechanism is unfamiliar.

## Working method

Use three dependency gates: (1) language/collections and complexity, (2) arrays/hash/list/tree/graph problem solving with SQL/API contracts, (3) low-level design, concurrency and small-service failure reasoning. Review one real project and collaboration episode throughout; do not postpone communication until the last week.

## Concrete reasoning example

An initial diagnostic might find a correct BFS but an incorrect SQL aggregate after a one-to-many join. The next session should repair row grain, not add advanced graph algorithms. Reattempt the query with equal-valued invoices and missing tags; then return to a mixed coding/backend mock. This is a hypothetical learning decision, not an assessment already performed.

## Tradeoffs and failure boundaries

A topic is not mastered because its answer looks familiar. Require an independent unfamiliar variation and a concrete failing-case explanation. Adjust the weekly budget to your available time; no problem count guarantees readiness. Advanced distributed design is optional until the actual role needs it.

## Practice — attempt first

### SDE-P7-01-A — Choose the next prerequisite

**Difficulty:** Intermediate. **Prompt:** You solve a familiar binary-search problem but cannot explain why the loop terminates. Do you advance to a harder problem?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

First derive the interval invariant and progress measure; trace empty, absent and equal-boundary cases. Then solve a changed bound query. More difficulty without a proof repairs neither understanding nor reliability. Followup: explain why a right boundary update differs between closed and half-open intervals.

</details>

### SDE-P7-01-B — Plan a mixed rehearsal

**Difficulty:** Intermediate with optional stretch. **Prompt:** You have a 75-minute mock and only one hour today. How do you use it without falsely recording a completed timed attempt?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Choose one declared partial stage or schedule a full session later. Record active time, omitted tasks and assistance; leave unobserved score dimensions Not assessed. Completing an untimed reference review is learning evidence, not a full independent session. Followup: select the smallest new variation for tomorrow.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://careers.microsoft.com/v2/global/en/hiring-tips/technical-interviewing) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
