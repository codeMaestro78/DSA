# Policy-search incident and migration: interviewer packet

**Spoilers — keep this file closed during the candidate attempt.** Companion to [the candidate packet](RAG_Engineer_Mock_Interview.md), **MOCK-RAG-01**. All scenario requirements are original [INFERRED]; this is role practice, not a real employer loop. Do not turn this reference approach into the only acceptable design.

## Interviewer instructions

Read the candidate contract first. Complete preflight outside the clock and start only when the agreed environment works. Run the four live stages in order for **60 minutes**. Ask one focused question, then allow reasoning time. Use neutral prompts such as “What assumption makes that safe?” rather than prematurely naming a technique. Give H0 factual answers freely; log H1/H2/H3 directional hints with time, wording and their effect. Offer the next hint only when the candidate requests help or is stalled after a neutral check. Preserve partial work rather than extending the clock to force completion. Do not penalize agreed accommodations or score speaking speed as technical correctness. Reserve 10–15 minutes after the clock for debrief.

## Clarification desk — H0

The query meanings and eligible gold IDs are fixed fixture labels. A2 is the sole eligible gold for q1, not A1. q3 has no eligible answer and requires abstention. Public P is visible to either tenant. Raw candidate exposure to the answering component counts as a failure before user publication; a final citation filter cannot undo it. Recall excludes q3's empty gold set rather than dividing by zero. The migration is hypothetical: no embedding dimensions, vendor, cloud service, actual timing or price is supplied. A candidate should state assumptions instead of inventing those facts.

## Timed script and reference solution

### RAG-T1 — Define valid evidence

**Checkpoint:** minutes 0–10.

**Ask:** “Which checks establish permitted evidence before generation, and which checks remain afterward?”

**Expected solution:** Ingestion records stable document identity, tenant/visibility, source version, current/obsolete status, effective-time semantics if needed, and chunk-to-document mapping. Query execution uses trusted tenant identity and current policy to constrain eligible retrieval; filter/currentness semantics must hold for every search path and cache. Recheck eligibility before evidence leaves its protected boundary and before publication under an explicit revocation contract. Then assess whether the answer's claims are actually supported by that eligible evidence. A citation ID alone proves neither relevance nor support. For q1, a faithful quote of the old policy may be entailed by A1 but is still unacceptable for a current-policy question.

**Hints:** H1 separate relevance, currentness and permission. H2 ask whether A1 can be a good semantic match but wrong authority. H3 trace tenant identity from request to candidate selection.

**Branches:** If stuck, compare A1 versus A2 for q1. If finished, ask whether effective-date policy versions might both be valid for different question dates. Accept a simple filtered lexical baseline; no vector database is mandatory.

### RAG-T2 — Diagnose the fixture

**Checkpoint:** minutes 10–30.

**Worked solution:** Three of four queries have an eligible answer (answerability coverage 3/4=75%). q1 Recall@2 is 0/1; q2 is 1/1; q4 is 1/1. Macro mean is (0+1+1)/3=2/3. q3 has an empty gold set and is excluded, not scored zero or one by default. There are eight raw returned candidate entries; B1 at q1 is the one cross-tenant entry, giving 1/8 raw-entry exposure rate on this tiny fixture. Also report **one of four queries** has a cross-tenant exposure; the two denominators answer different questions. Obsolete A1 appears at q1. q1's cited A1 is wrong for current policy; q3 should abstain and its A2 citation is unsupported under the stipulated label. q2/q4 cite their stipulated correct eligible document, but absent actual text we cannot independently verify their claim wording/entailment.

A reranker constrained to q1's two raw candidates cannot recover absent A2. Fix eligibility/currentness and candidate generation, then evaluate ranking. Filtering the current top-2 after retrieval removes bad items but can leave no answer; it does not establish the recall of a properly filtered index. Access controls must operate before exposure, not after text is handed to the model.

**Hints:** H1 write the gold set beside each list. H2 ask how to divide when the gold set is empty. H3 identify q1's missing A2 and ask whether reordering can create it.

**Branches:** If stuck, compute q1 only and retain that progress. If finished, ask for confidence limits or more data before interpreting 1/8 as a production security rate; no statistical extrapolation is justified here.

### RAG-T3 — Plan a safe corpus change

**Checkpoint:** minutes 30–50.

**Expected solution:** Build a new immutable corpus/index generation from authorized source versions and a manifest containing document/chunk identities, embedding model/configuration, chunking and policy metadata. Do not mix incompatible vector spaces or quietly overwrite an active index. Evaluate the old and new systems on the same frozen, representative labeled queries with retrieval, eligible evidence support, abstention and policy tests separated. Include deletes, version swaps, literal policy identifiers and adversarial content; inspect regressions by tenant/document slice.

A small deployment can use an atomic active-generation pointer and compatible reader contract. Tag requests with their content generation and retain a rollback target only while its sources remain permitted/current; rollback must not resurrect deleted or revoked content. Coordinate incremental updates between snapshot and activation with a declared cutoff/replay process. Cache identity includes tenant/authority context, source/index version and query settings; current access checks remain required. A new index need not imply a new model if fixing metadata/lexical matching suffices.

For mid-generation revocation, cancel or suppress queued/unpublished work and recheck authority at the actual release/action boundary under the declared policy. Current authorization is not frozen by the content snapshot. Data already transmitted to a provider cannot be retracted; a strict “nothing after revocation commits” promise requires a coordinated authorization/publication protocol and a stated consistency model, not just a late independent check. State the remaining race/propagation bound if that stronger mechanism is absent.

**Hints:** H1 distinguish content generation from current permission. H2 ask how an in-flight request identifies its source version. H3 provide the active-generation manifest concept, leaving rollout criteria to the candidate.

**Branches:** If stuck, ask for the smallest safe two-generation rollout. If finished, ask about a deletion arriving between snapshot and activation, or a rollback whose old source is no longer permitted. These are optional stretch; no specific vendor API is required.

### RAG-T4 — Communicate and defend

**Checkpoint:** minutes 50–60.

**Expected solution:** An update distinguishes fixture-confirmed cross-tenant exposure, stale-policy citation, missing eligible candidate and unsupported answer from hypotheses about indexing or filters. Contain the exposure path, investigate policy enforcement and preserve appropriately redacted version/run evidence; do not promise the migration alone fixes a security boundary. Assign a concrete check such as tracing q1's trusted tenant filter and manifest before changing a model.

Replace “100% grounded” with scoped acceptance criteria: every release candidate must pass the enumerated policy/version fixtures; measure eligible retrieval, supported claim precision, abstention and incident/unknown coverage on a frozen suite with reviewed labels. Zero failures on a finite suite is a necessary local gate, not a universal guarantee. Expand the suite and monitor sampled deployment outcomes before claiming live benefit. A human reviewer or second model can also err; judge configuration and disagreements need evidence.

**Strong indicators:** Candidate reports two exposure denominators without confusing them, refuses reranker magic, uses provenance and separates current permission from a content snapshot. **Common mistakes:** including empty-gold q3 in recall without a rule, calling citations correctness, inferring a model regression without evidence or treating rollback as permission restoration.

**Hints:** H1 ask for one confirmed fact and one hypothesis. H2 ask what finite test could falsify the proposed guarantee. H3 supply the wording “passes this suite” versus “always grounded.”

**Branches:** If stuck, ask for a concise user-impact statement. If finished, ask how the owner would detect previously unseen unanswerable questions without collecting every private prompt.

## Evaluation evidence

Use the six shared dimensions in [the rubric](Interview_Evaluation_Rubric.md). The table gives **task-specific level-3 evidence**, not automatic points for a keyword. Apply 0–2 to the actual help/errors and reserve 4 for repeated demonstrated performance across variations or attempts. Mark unobserved dimensions **Not assessed**. Record a known critical defect separately; averaging cannot erase it.

| Dimension | Observable level-3 evidence | Critical blocker / reason to withhold 3 |
|---|---|---|
| CONTRACT | States eligible-current evidence and q3 abstention; defines denominators | Counts any semantically similar or cited document as correct |
| REASONING | Localizes generation versus ranking versus support failures | Claims reranking can recover an absent document |
| CORRECTNESS | Derives 2/3 recall, 3/4 answerability and scoped exposure counts | Divides by empty gold set or conceals cross-tenant exposure |
| VALIDATION | Plans paired frozen evaluation plus deletes/currentness/adversarial cases | Uses aggregate similarity scores or a demo as correctness evidence |
| RISK | Maintains policy checks across versions, rollback and publication | Exposes unauthorized content then relies on citation filtering |
| COMMUNICATION | Separates confirmed facts/hypotheses and finite-suite acceptance | Promises universal groundedness or claims a real migration was run |

## Debrief and next attempt

Ask the candidate to summarize one decision, one check and one unresolved risk before providing feedback. Save task completion, H0/H1–H3, exact evidence, dimension scores and what was not assessed. For metric errors, change q4 to an empty gold set and recompute with a written policy. For migration gaps, replay one delete through snapshot, publication and rollback before reattempting a new scenario. Do not write a hiring decision or invent a candidate result. Any example of strong behavior above is an editorial anchor, not a prefilled attempt.

## Validation, sources and limits

Only the synthetic metric arithmetic is exercised by [the local suite](../validation/mocks-ai/README.md); no search index, embedding migration, model judge or authorization service was executed. Expected designs are reviewed reasoning. [OWASP authorization](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html) supports request-level authority checks; [OWASP prompt-injection guidance](https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html) supports treating retrieved instructions as untrusted. Canonical lessons supply their existing scoped retrieval/evaluation sources.

[Source register](PHASE6_AI_SOURCES.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
