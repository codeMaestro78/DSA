# Project explanation framework

## Start with a claim ledger

For each real project, record user/problem, personal contribution, architecture, constraints, rejected alternative, verification evidence and limitations. Separate a local prototype from an operated production service. This is an original [INFERRED] framework, not a claim about a particular employer's interview.

## Three explanation lengths

| Length | Include | Leave for followup |
|---|---|---|
| 30 seconds | User problem, what you built, your role and one result | Component inventory and implementation details |
| 2 minutes | Add main data flow, one design choice and evidence | Every endpoint or dependency |
| 5 minutes | Add alternatives, failure/security behavior and a lesson | Unmeasured claims about hypothetical scale |

These are rehearsal budgets. Use the interviewer's question to decide what matters.

## Architecture walkthrough

Identify the request entry point, authentication/authorization, authoritative data, synchronous work, asynchronous work and external dependencies. Trace a normal request, then a failure. For an AI project, separate model inference, retrieval, tool execution and evaluation; explain which part you implemented. A framework name does not describe persistence, replay or recovery.

## A realistic followup chain

“Why PostgreSQL?” → “Which access pattern or invariant mattered?” → “What happens with two concurrent requests?” → “Which mechanism enforces the invariant?” → “What if the transaction commits but the client times out?” → “How do you prevent a duplicate effect?” → “Which parts were tested, and under what environment?”

For an AI project: “Why this model?” → “What baseline?” → “Which examples failed?” → “Is it retrieval or generation?” → “Are citations actually supported?” → “How do permissions and deletion work?” Do not claim to have measured these if you only designed a possible evaluation.

## Resume proof table

| Claim | Personal contribution | Evidence | Environment/workload | Limit |
|---|---|---|---|---|
| [Actual bullet] | [Specific action] | [Artifact or observation] | [Local/test/deployed] | [What it does not show] |

## Production-readiness questions

Who operates it? How are failures detected? What is the rollback/restore path? Are secrets and tenant data protected? What load is actually known? What happens when an external provider is slow? A prototype may legitimately lack these; explain the next step rather than saying it is production ready.

## Checklist

- [ ] Short and long pitches preserve the same facts.
- [ ] Diagram and implementation agree within the stated scope.
- [ ] One hard tradeoff and failure are explainable.
- [ ] Every performance/quality claim has a method and limit.

## References

- [Microsoft interview tips](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — experience examples and reflection.
- [Project behavioral practice](../10-Behavioral-and-Communication/Project_Deep_Dive.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
