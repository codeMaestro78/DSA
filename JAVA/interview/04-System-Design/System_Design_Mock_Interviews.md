# System-design mock routes and a migration variation

Use [the full 75-minute booking mock](../13-Mock-Interviews/System_Design_Mock_Interview.md) for a first complete session. Its separate interviewer companion supplies the scripted facts, hints and worked design. Use [the common rubric](../13-Mock-Interviews/Interview_Evaluation_Rubric.md) and [blank scorecard](../13-Mock-Interviews/Mock_Scorecard.md). Do not open solutions during an independent attempt.

This chapter adds an original **[INFERRED]** 35-minute transfer variation, not an employer question or an additional Phase6 session count. Preflight and 10-minute review are outside its budget. Target 0–2 years; Intermediate with optional Advanced migration followup. No service or database is executed.

## Candidate packet — attempt first

A private document service has one authoritative database and a derived search index. A new analyzer/index generation is ready. Users may edit or delete documents during rebuild. A document can also become unreadable to a tenant after permission revocation. The product requires no missing committed updates after the declared cutover point and no resurrection of deleted content from rollback. Search freshness outside cutover has no numeric deadline yet; clarify it rather than inventing a guarantee.

| Stage | Minutes | Requested artifact |
|---|---|---|
|1|8|Facts/unknowns, authority and cutover contract|
|2|12|Snapshot/change-log/index manifest and critical write/read flow|
|3|10|Delete during rebuild; lost acknowledgment; old-generation rollback traces|
|4|5|Checks, owner, stop condition and concise recommendation|

Explain the smallest credible protocol, one alternative and what evidence would justify activation. A diagram without a write/deletion race trace is incomplete. Record factual clarification as H0 and any directional help separately.

<details><summary>Interviewer reference — reveal after attempting</summary>

**H0 facts:** Source versions and deletions have a monotonic committed change sequence; the service can retain/replay changes for the rebuild window. One activation owner can atomically update the generation pointer. Current resource authority is checked at exposure time; it is not frozen by the content snapshot. If any of these mechanisms are absent, the candidate must adjust the promise or pause writes/cutover explicitly.

**Worked solution:** Snapshot at source position 100, build generation G2 with the analyzer/source manifest, then replay committed changes101 onward. DocumentD deletion at 105 must be inG2 before activation at a stated caught-up boundary. Verify copied/replayed state and keep a durable replay cursor. Coordinate activation with the authoritative change position; an uncoordinated “lag looked zero once” observation leaves a race. Keep queries pinned to a coherent generation while applying current authorization. Rollback toG1 must apply the intervening changes/deletes or be declared unavailable; simply pointing at an old snapshot may resurrectD.

**Hints:** H1 ask what happened after the snapshot. H2 ask for a committed change position and delete tombstones. H3 supply snapshot 100/delete 105/activation 110 and ask the candidate to traceD. If stuck, allow a bounded write pause with explicit availability cost. If finished, introduce expired replay retention or a deletion whose content is already transmitted to a model.

**Alternatives:** Incremental in-place indexing with source-version conditional updates may meet a weaker freshness contract, but a schema/analyzer change often benefits from a new generation. A controlled write pause can simplify cutover if business requirements permit. Neither option waives authorization/deletion checks.

**Checks:** Write/delete during snapshot and catch-up, stale source version after newer update, duplicate replay, crash before/after pointer swap, cursor expiry and old-generation rollback. These are proposed checks, not execution results.

**Scoring:** CONTRACT covers the promised cutoff and authority; REASONING the simplest protocol/alternative; CORRECTNESS the race/deletion trace; VALIDATION the discriminating checks; RISK the rollback/exposure boundary; COMMUNICATION the owner, unknowns and stop decision. Apply the shared 0–4 meanings; unobserved stays Not assessed,4 needs repeated independent variations. Critical unresolved resurrection or unauthorized exposure cannot be averaged away. Common mistake: treating an atomic pointer as proof that source updates were included. Strong evidence: an explicit cutover sequence and honest fallback.

</details>

[Source scope](PHASE7_SOURCES.md) · [Search case](Classic-System-Design/Search_Engine.md) · [Design index](README.md). Last reviewed: **2026-11-03**.
