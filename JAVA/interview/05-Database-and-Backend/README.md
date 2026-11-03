# Database and backend foundations

## Purpose and Phase 2 scope

Learn to produce the correct rows, preserve business invariants and explain failures across a backend/database boundary. The eleven core chapters below are Phase2 teaching material. Phase7 completes the requested specialized products, replication/sharding, ORM/API comparison and domain mock routes.

Read in the order below. Use the same small fictional customer/order dataset to avoid relearning a schema for every concept. The dataset is educational, not a production payment model. Practice questions are original, company-neutral [INFERRED] exercises.

## Learning sequence

- [1. Result grain, joins, NULL, aggregation and windows](SQL.md)
- [2. Facts, dependencies and historical snapshots](Normalization.md)
- [3. Storage/runtime mental model and maintenance](PostgreSQL.md)
- [4. Atomicity, invariants and external side effects](Transactions_and_ACID.md)
- [5. Snapshot behavior, write skew and retry boundaries](Isolation_Levels.md)
- [6. Query-shaped index design](Database_Indexing.md)
- [7. Measurement and plan diagnosis](Query_Optimization.md)
- [8. Idempotency, pagination and concurrency contracts](API_Design.md)
- [9. Identity, credentials and their lifetime](Authentication.md)
- [10. Resource/action permissions and tenant boundaries](Authorization.md)
- [11. Distributed consistency failure reasoning](CAP_Theorem.md)

## How to practice

First answer each chapter's questions without expanding its solution block. State assumptions, draw a tiny row set or transaction schedule, and give one counterexample. Then compare your answer with the expected reasoning and attempt a variation. A correct query result alone does not prove concurrency safety or a speedup.

The [shared fixture](../validation/database/fixture.sql) and [native PostgreSQL validation](../validation/database/README.md) establish which SQL/results and two-session schedules were actually checked. Authentication/authorization prose is conceptual guidance; no application authentication implementation or security audit was performed.

## Core readiness gate

- [ ] Explain a left join that retains customers with zero orders and why a naïve count is wrong.
- [ ] Select a stable top row using a window function and explain tie policy.
- [ ] Defend an index from a concrete filter/order pattern without inventing a speedup.
- [ ] Demonstrate a transaction race and choose a correct enforcement mechanism.
- [ ] Explain a lost acknowledgement and durable idempotency/reconciliation.
- [ ] Separate valid identity from current resource permission.
- [ ] Describe a partition's effect on one critical write operation.

## Question record convention

All IDs below are company-neutral, target 0–2 years, and use the corresponding chapter's references. Round is a suggested practice context, not a verified employer format. Prompts, expected answer/explanation, followups, common mistakes and related chapters appear with each question group. Difficulty ranges in chapters indicate introductory and extension depth; this table assigns the base task a concrete level.

| IDs | Category | Difficulty | Target role | Practice round | Evaluation target |
|---|---|---|---|---|---|
| SQL-01, SQL-02 | SQL correctness | Beginner | Backend/SDE/data | Coding | Grain, joins and three-valued logic |
| SQL-03 | SQL windows | Intermediate | Backend/SDE/data | Coding | Ranking and tie handling |
| NORM-01, NORM-02 | Data modeling | Intermediate | Backend/data | Schema design | Functional dependencies and temporal facts |
| PG-01, PG-02 | Database runtime | Intermediate | Backend/SDE | Technical deep dive | MVCC lifecycle and transaction ownership |
| TX-01, TX-02 | Transactions | Intermediate | Backend/SDE | Design/debugging | Invariants and external-side-effect boundaries |
| ISO-01, ISO-02 | Concurrency | Intermediate | Backend/SDE | Debugging | Read/write sets, anomaly and full retry boundary |
| IDX-01, IDX-02 | Access paths | Intermediate | Backend/SDE/data | Optimization | Selectivity, ordering and write cost |
| OPT-01, OPT-02 | Diagnosis | Intermediate | Backend/data | Debugging | Measurable hypotheses and plan interpretation |
| API-01, API-02 | Backend contracts | Intermediate | Backend/full-stack | Design | Concurrent duplicate requests and authorized pagination |
| AUTHN-01, AUTHN-02 | Identity | Intermediate | Backend/full-stack | Technical/design | Verification policy, credential lifetime and revocation |
| AUTHZ-01, AUTHZ-02 | Access control | Intermediate | Backend/full-stack/AI | Design/debugging | Resource authorization and effective DB role |
| CAP-01, CAP-02 | Distributed theory | Intermediate | Backend/platform/SDE | Architecture | Formal guarantees and operation-specific partition policy |

## Delivered specialized chapters

- [MySQL](MySQL.md).
- [MongoDB](MongoDB.md).
- [Redis](Redis.md).
- [DynamoDB](DynamoDB.md).
- [Cassandra](Cassandra.md).
- [Elasticsearch](Elasticsearch.md).
- [Vector Databases](Vector_Databases.md).
- [Replication](Replication.md).
- [Sharding](Sharding.md).
- [ORM Internals](ORM_Internals.md).
- [REST vs GraphQL](REST_vs_GraphQL.md).
- [Backend Mock Interviews](Backend_Mock_Interviews.md).

Product lessons use explicit access/consistency boundaries; no capability, quota or performance follows merely from a product label. New worked traces and proposed schedules are reviewed reasoning, not new native database execution. Follow the actual product version/configuration before applying implementation guidance.

## References and navigation

- [Phase 2 source register](PHASE2_SOURCES.md).
- [Phase7 specialized-source register](PHASE7_SOURCES.md).
- [Central source index](../SOURCE_INDEX.md).
- [System design](../04-System-Design/README.md).
- [Master roadmap](../MASTER_INTERVIEW_ROADMAP.md).
- [Parent index](../README.md).

## Last verified

2026-11-03: requested catalog coverage, source scope and navigation reviewed. Prior native PostgreSQL evidence retains its original fixtures/runtime; no new product runtime pass is claimed.
