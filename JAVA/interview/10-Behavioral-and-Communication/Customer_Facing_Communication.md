# Customer-facing communication

## Translate the decision

Explain how a technical problem affects a user's workflow, what is known, what is uncertain and what happens next. Avoid hiding uncertainty behind jargon or promising an outcome outside your control. This is original [INFERRED] practice for customer-facing engineering.

## Discovery sequence

Ask who performs the task, what success looks like, what fails today, which data and integrations are available, what restrictions apply and who accepts the result. Repeat the agreed scope in plain language. “An AI assistant” is a proposed solution; the actual need might be faster lookup, consistent triage or reduced manual entry.

## Two explanations of the same issue

Technical: “The provider timed out after submission, so the operation's outcome is unknown. We will reconcile by the request identifier before retrying.”

User-facing: “We have not yet confirmed whether the request completed. We are checking its status before trying again to avoid creating a duplicate. We will update you at [agreed time].”

This is a hypothetical wording example, not an account of an actual incident. Avoid substituting an invented completion time for a clear update commitment.

## Handle disagreement and scope change

Acknowledge the requested outcome, explain the constraint and offer a concrete option with its tradeoff. For example, if write access to a customer system is unavailable, propose a read-only recommendation flow with human execution and explicit acceptance criteria. Do not claim a demo has permission to perform actions merely because the model can request them.

## Practice

Explain a real technical tradeoff first to an engineer, then to a nontechnical stakeholder. Keep the facts and risks consistent while changing vocabulary and level of detail.

<details><summary>Review criteria</summary>

The listener can state the impact, current status, choice and next update. The explanation does not misrepresent an uncertain outcome as a failed or successful operation, and does not bury a material limitation.

</details>

## Checklist

- [ ] Outcome and acceptance criteria agreed.
- [ ] Known facts separated from hypotheses.
- [ ] Next action, owner and update time clear.

## References

- [Microsoft interview tips](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — STAR(R) and role-specific guidance.
- [Research methodology](../RESEARCH_METHODOLOGY.md).
- [FDE role evidence](../01-Company-Research/PHASE1_AI_LAB_FINDINGS.md) — responsibilities differ by requisition.

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
