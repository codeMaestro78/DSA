# Stakeholder communication and delivery decisions

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Forward Deployed Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Make the next decision easy to understand. State the outcome, evidence, remaining uncertainty, options and recommendation. Adapt vocabulary to the audience without changing facts. Keep a decision record with owner, scope, rationale and revisit condition; a meeting without an agreed next action leaves ambiguity intact.

When requirements change, describe the effect on scope, timeline and verification. Ask which constraint should move rather than silently absorbing incompatible commitments. Escalate when the decision exceeds your authority or a material risk remains unresolved; escalation is useful when it brings a concrete choice to the correct owner.

## Worked role scenario

The customer asks to add automatic CRM updates immediately before a demo. Explain that the current read-only flow was evaluated for recommendations, not writes. Offer a labeled simulation or a later controlled write pilot with permission, idempotency and acceptance checks. Do not disguise the simulation as a live integration.

## Interview practice: FDE-P3-08

- **Category / difficulty:** Stakeholder communication and delivery decisions / Intermediate.
- **Target role / experience:** Forward Deployed Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you communicate an uncertain integration failure?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** State what users can observe, what is known and unknown, the containment/check underway, the owner and next update time. Do not claim that a timed-out action definitely failed.

**Followup:** How do you handle conflicting acceptance criteria from two stakeholders?

**Common mistake:** Promising a completion time to sound reassuring when evidence is insufficient.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Name decision and acceptance owners.
- [ ] Keep simulated and live evidence distinct.
- [ ] Record tradeoffs and update commitments.

## Canonical lessons and references

- [Customer communication](../../10-Behavioral-and-Communication/Customer_Facing_Communication.md).
- [Discovery](Technical_Discovery.md).
- [Anthropic: Paris Forward Deployed Engineer](https://job-boards.greenhouse.io/anthropic/jobs/5391021008).
- [Databricks: London Forward Deployed Engineer](https://www.databricks.com/company/careers/professional-services-operations/forward-deployed-engineer-8921476002).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
