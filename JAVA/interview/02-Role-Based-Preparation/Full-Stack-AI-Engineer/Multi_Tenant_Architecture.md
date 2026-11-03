# Multi-tenant AI architecture

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Tenant isolation is an invariant across storage, retrieval, queues, caches, tools and observability. Pick an isolation model deliberately: shared tables with enforced scope, separate schemas/databases/indexes, or a hybrid. Each changes operational cost and blast radius; no arrangement removes the need for authorization.

Include tenant and relevant permission/version scope in cache and idempotency keys. A globally shared semantic cache can leak a prior tenant's answer. Enforce quotas and concurrency fairness so one large ingestion or generation workload does not monopolize shared resources. Avoid passing tenant authority through untrusted model arguments.

## Worked role scenario

Two tenants submit identical queries, but their document policies differ. Reusing a cached generated answer by query text alone is unsafe even if the text is identical. Scope reuse to the authorized evidence/policy context, or disable that reuse until its correctness can be demonstrated.

## Interview practice: FSAI-P3-07

- **Category / difficulty:** Multi-tenant AI architecture / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** What belongs in an answer-cache key and access check?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Tenant/actor or validated permission-equivalence scope, corpus/index/model/prompt versions and query/configuration as needed. The key design must correspond to the contract; keying alone does not implement current permission checks.

**Followup:** What if a document is deleted while an answer remains cached?

**Common mistake:** Testing only table isolation while forgetting retrieval and tool results.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] State the isolation invariant.
- [ ] Scope caches/jobs/events and tools.
- [ ] Define deletion and noisy-neighbor controls.

## Canonical lessons and references

- [RAG permissions](../RAG-Engineer/Production_RAG_Architecture.md).
- [Authorization](../../05-Database-and-Backend/Authorization.md).
- [pgvector official repository](https://github.com/pgvector/pgvector).
- [Pinecone: metadata filters](https://docs.pinecone.io/guides/search/filter-by-metadata).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
