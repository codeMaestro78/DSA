# Customer-facing system design

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Forward Deployed Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

A customer design must fit existing identity, networking, data ownership and operations. Start from a critical workflow, then identify integration boundaries and restrictions. Explain a simple viable design plus one credible alternative. An enterprise diagram full of products is not a substitute for a traceable write/read flow.

Describe the same tradeoff at two levels: engineering details and user/business consequence. If private data cannot leave the environment, that constraint changes candidate deployment/provider choices; do not assert a vendor meets it without a current contract/configuration review.

## Worked role scenario

The customer permits read access to a CRM but no writes. Propose retrieval/recommendation with a human copying the result, or an approved staging workflow, rather than designing an unauthorized write tool. A future write path requires a new permission, idempotency and rollback/reconciliation plan.

## Interview practice: FDE-P3-05

- **Category / difficulty:** Customer-facing system design / Intermediate.
- **Target role / experience:** Forward Deployed Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you defend a read-only first release to a stakeholder asking for autonomy?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Connect scope to a measurable useful outcome, explain the current permission/risk boundary, define reviewer acceptance and name the evidence/approval needed for controlled expansion.

**Followup:** What integration condition would invalidate your proposed architecture?

**Common mistake:** Claiming an agent can overcome an unavailable API or missing permission.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Tie components to customer constraints.
- [ ] Trace normal and failed integration flows.
- [ ] Explain alternatives in plain language.

## Canonical lessons and references

- [Design framework](../../04-System-Design/System_Design_Interview_Framework.md).
- [Tool permissions](../AI-Engineer/Tool_Use_and_Function_Calling.md).
- [Databricks: London Forward Deployed Engineer](https://www.databricks.com/company/careers/professional-services-operations/forward-deployed-engineer-8921476002).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
