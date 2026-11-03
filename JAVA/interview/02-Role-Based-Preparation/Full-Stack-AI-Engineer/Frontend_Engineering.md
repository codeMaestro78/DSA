# Frontend engineering for AI products

## Role focus and prerequisites

This chapter gives original **[INFERRED]**, company-neutral preparation for an early-career Full Stack AI Engineer. It does not establish a company's interview format or imply eligibility for a particular posting. Read the linked canonical lesson for the mechanism, then use this chapter to practice the role's decisions and evidence.

## Understand the decision

Model UI state around user intent and request identity. Keep derived values derived rather than duplicating them in effects; use effects for synchronizing external systems, with appropriate cleanup. React documents this distinction. In Next.js App Router, server/client boundaries determine where interactive state and browser APIs belong; secrets must remain behind server-side authorization and storage boundaries.

For streaming, batch rendering where useful, preserve keyboard focus, expose status accessibly and distinguish partial text from completed output. Render model-supplied Markdown/HTML through a deliberate sanitization policy. A citation click must resolve the source version the answer actually used.

## Worked role scenario

Request A begins, then the user starts request B. A delayed A callback arrives last. Guard updates with request identity or cancel/ignore the obsolete subscription; a single global `isLoading` flag cannot express the two lifecycles correctly.

## Interview practice: FSAI-P3-02

- **Category / difficulty:** Frontend engineering for AI products / Intermediate.
- **Target role / experience:** Full Stack AI Engineer; approximately 0–2 years, with advanced followups optional.
- **Round / company:** Suggested technical, project or design practice; company-neutral.
- **Evaluates:** Whether you can apply the chapter's mechanism, identify a failure boundary and justify a decision using evidence.
- **Prompt:** How would you prevent stale stream updates and duplicated derived state?
- **Related:** The canonical lesson and adjacent chapters linked below.

<details><summary>Expected reasoning, followup and common mistake — open after attempting</summary>

**Expected answer:** Keep an authoritative active request ID, reject events for obsolete requests, clean up subscriptions and derive display summaries from canonical state. Do not mutate a finished message with unrelated events.

**Followup:** What changes when the user navigates away and returns?

**Common mistake:** Assuming effect cleanup proves the remote job was cancelled.

**Evidence boundary:** State what you personally implemented or checked. A plausible design or passing local fixture is not evidence of live production behavior.

</details>

## Preparation checklist

- [ ] Separate local, server and derived state.
- [ ] Test stale responses and unmount cleanup.
- [ ] Preserve accessibility and safe rendering.

## Canonical lessons and references

- [AI integration boundaries](AI_Integration.md).
- [Realtime lifecycle](Real_Time_Applications.md).
- [React: effects and derived state](https://react.dev/learn/you-might-not-need-an-effect).
- [Next.js: Server and Client Components](https://nextjs.org/docs/app/getting-started/server-and-client-components).

## Last verified

2026-11-02: source scope and teaching reasoning reviewed. No live provider/store, browser workflow or production deployment was exercised for this role chapter.
