# Domain coverage and dependencies

This map began in Phase 1. [Phase 2](PHASE_2_REPORT.md) and [Phase 3](PHASE_3_REPORT.md) reports identify delivered core/AI teaching content; [Phase 4](PHASE_4_REPORT.md) adds scoped company research. [Phase 7](PHASE_7_REPORT.md) completes all requested specialist topics and adds the six DSA extension guides. “Core” reflects the candidate's stated goals; it is not a universal hiring requirement. Each suggested exercise is [INFERRED], company-neutral, and has the individual execution/review scope stated in its chapter.

| Domain | Prerequisite | Core learning boundary | First meaningful exercise | Reference anchor |
|---|---|---|---|---|
| Interview fundamentals | None | Clarification, reasoning, evidence and format differences | Explain an ambiguous prompt and propose tests | Company hiring briefs |
| DSA | Language/complexity | Arrays → patterns → trees/graphs → DP; SQL/concurrency as adjacent tasks | Compare brute force and an invariant-based optimization | MIT 6.006 |
| Backend/database | HTTP/SQL | Contracts, access patterns, indexes, transactions and authorization | Trace two concurrent updates violating an invariant | PostgreSQL; RFC 9110 |
| System design | Backend/data model | Requirements, simple architecture, scaling, recovery and cost | Design a notification service including duplicate delivery | Google SRE workbook |
| Cloud/infrastructure | Linux/processes/networking | Containers, desired state, telemetry, deployment and rollback | Explain why a container restart differs from replacing a Pod | Kubernetes documentation |
| ML/deep learning | Algebra/probability/basic Python | Leakage-free splits, baselines, loss/optimization and generalization | Detect preprocessing leakage and propose a valid split | scikit-learn; PyTorch |
| LLM/AI engineering | Backend plus ML evaluation | Token budgets, retrieval, tool contracts, evaluation, latency and cost | Compare retrieval quality and answer support separately | RAG paper |
| RAG/search | Embeddings, ranking, labeled data | Ingestion through permissions, ANN, hybrid search, reranking and deletion | Diagnose low recall after a selective metadata filter | pgvector documentation |
| Agent systems | AI plus state machines | Bounded action loops, trust boundaries, persistence, retries and recovery | Handle timeout after a side effect without duplicating it | MCP specification; RFC 9110 |
| Full-stack AI | TypeScript/HTTP/backend | State ownership, streaming, auth and resilient user experience | Specify cancel/reconnect behavior for a chat request | React documentation |
| Data/MLOps | SQL/ML/operations | Backfills, data quality, artifacts, reproducibility and serving | Specify training/serving feature consistency checks | scikit-learn; PyTorch |
| FDE | Software delivery/communication | Discovery, integration, constraints and acceptance criteria | Turn a vague customer need into a bounded demo and rollout plan | AI lab/Palantir brief |
| Projects/behavior | Real experience | Personal contribution, tradeoffs, evidence and reflection | Record a project pitch and an honest failure story | Company briefs and candidate evidence |

## Practice and assessment coverage

The delivered question banks include Beginner, Intermediate, Advanced and Expert prompts with metadata for role, round, experience level and related questions. Foundational candidates should not need Expert scores in every domain. Nine full role/design mocks are delivered, plus focused domain variants. Keep solutions separate from candidate prompts.

For an ML experiment, teach split choice and leakage before optimization. For RAG, teach a lexical/dense baseline and labeled retrieval evaluation before GraphRAG. For infrastructure, teach a single-node request path before clusters. For design, explain one critical flow before scaling to hypothetical global traffic.

## Technical anchors and limitations

- [MIT notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/) establish an algorithms foundation, not interview-frequency rankings.
- [PostgreSQL isolation](https://www.postgresql.org/docs/current/transaction-iso.html) documents one engine's behavior; avoid attributing it to all SQL databases.
- [SRE workbook](https://sre.google/workbook/implementing-slos/) motivates user-facing reliability measurement; its age does not establish current cloud feature availability.
- [scikit-learn pitfalls](https://scikit-learn.org/stable/common_pitfalls.html) explains leakage prevention; [PyTorch quickstart](https://docs.pytorch.org/tutorials/beginner/basics/quickstart_tutorial.html) introduces an executable training workflow.
- [Original RAG paper](https://arxiv.org/abs/2005.11401) defines a research architecture, not a guarantee of factual output in every modern application.
- [Kubernetes Pods](https://kubernetes.io/docs/concepts/workloads/pods/), [React](https://react.dev/learn/thinking-in-react), [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html), [pgvector](https://github.com/pgvector/pgvector) and [MCP](https://modelcontextprotocol.io/specification/2026-07-28) are primary technical references. Recheck exact versions before coding.

## Last verified

2026-11-02: anchor sources inspected; proposed exercises have not been implemented or tested.

## Phase 5 assessment coverage

[134 original questions](12-Interview-Question-Bank/Question_Bank_Index.md) cover coding/low-level design, architecture, databases, cloud/operations, AI/ML/deep learning, LLM/RAG/agents, behavior and project evidence. [22 public problems](03-DSA-and-Coding/PUBLIC_PROBLEM_CATALOG.md) add platform practice across all 22 DSA areas; Phase 7 now authors the specialist lessons; public problem directions still do not establish hidden-test correctness. All four editorial difficulties occur across the original set. Company attribution remains an explicit evidence gap; [Phase 6 timed role mocks](13-Mock-Interviews/README.md) are now delivered.

## Phase 6 integrated assessment

[Nine mock sessions](13-Mock-Interviews/README.md) combine 36 connected tasks with separate interviewer scripts, worked answers, hint branches and task-specific anchors. The [shared rubric](13-Mock-Interviews/Interview_Evaluation_Rubric.md) maps coding, design, AI and behavioral dimensions to the same score meanings. [QA](PHASE_6_QA_REPORT.md) distinguishes executable fixture evidence from proposed systems and experiments. Mocks do not establish candidate readiness. Phase 7 separately authors the specialist lessons and records the completed bounded library review.
