# AI application architecture

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Keep an ordinary application boundary around probabilistic components. The API owns validation and identity; domain code owns invariants; retrieval owns authorized evidence; model adapters own provider translation; workflow code owns state and recovery. A small modular monolith can satisfy these boundaries without separate deployments.

Define data ownership before choosing frameworks. Store accepted work durably if the API promises later execution. Record enough provenance to reproduce or explain an answer within retention/privacy policy. Prefer a thin provider adapter over scattering model-specific fields throughout business code.

## Worked role scenario

A user submits a long document analysis. The API authorizes upload and persists a job; a worker parses, retrieves and generates; status/events expose progress. A disconnected browser does not delete the accepted job unless the product explicitly defines that cancellation behavior.

## Interview practice: AIE-P3-09

- **Category / difficulty:** AI application architecture / Intermediate.
- **Target role / experience:** AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you separate the browser connection from durable work?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Give the job an identity and authoritative state, expose authorized status/events and define cancellation as its own operation. The transport subscription is not the durable execution owner.

**Followup:** When is a synchronous request simpler and sufficient?

**Common mistake:** Adding a queue without defining acceptance and recovery semantics.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Name owners and contracts.
- [ ] Trace one request and one crash.
- [ ] Choose the simplest design meeting requirements.

## Canonical lessons and references

- [RAG architecture](../../08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md).
- [Monolith and services](../../04-System-Design/Monolith_vs_Microservices.md).
- [FastAPI: concurrency and async](https://fastapi.tiangolo.com/async/).
- [FastAPI: lifespan](https://fastapi.tiangolo.com/advanced/events/).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
