# Phase 7 DSA extensions primary source scope

Sources below were directly opened **2026-11-03**. All new lesson examples, local mock variants, exercise ratings and practice timing are original **[INFERRED]**, company-neutral synthesis. References support specific mechanisms, not employer question ownership, internal hiring scores or observed candidate outcomes.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P7-DSA-01 | TrieSET / Sedgewick and Wayne, algs4 author repository | [Source](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/TrieSET.java) | Rolling source; page publication not established | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Trie terminal/path mechanism; source alphabet256 differs from our explicit26-letter fixture |
| P7-DSA-02 | FenwickTree / Ricardo Pacheco, algs4 repository | [Source](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/FenwickTree.java) | Rolling source; source header date ambiguous | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | One-based block/update/query mechanism; prose rederives intervals rather than copying off-by-one comments |
| P7-DSA-03 | Java17 expressions / Oracle JLS | [Source](https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html) | Java17 edition | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Fixed-width shifts, bitwise and overflow behavior |
| P7-DSA-04 | Java17 BigInteger / Oracle | [Source](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/math/BigInteger.html) | Java17 edition | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Arbitrary precision/gcd/modular API boundaries; not constant-time arithmetic |
| P7-DSA-05 | BlockingQueue / Oracle | [Source](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/BlockingQueue.html) | Java17 edition | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Handoff semantics and shutdown absence; no live concurrency run |
| P7-DSA-06 | PostgreSQL18 window functions / PostgreSQL project | [Source](https://www.postgresql.org/docs/18/functions-window.html) | Version18; page date unstated | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Rank/row-number/peer/frame behavior; no new native SQL |
| P7-DSA-07 | PostgreSQL18 table expressions / PostgreSQL project | [Source](https://www.postgresql.org/docs/18/queries-table-expressions.html) | Version18; page date unstated | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Join/filter/aggregation grain; no query-plan performance guarantee |
| P7-DSA-08 | Java17 threads and locks / Oracle JLS | [Source](https://docs.oracle.com/javase/specs/jls/se17/html/jls-17.html) | Java17 edition | 2026-11-03 | Primary specification, author implementation or official course/guidance; high for documented mechanism | Atomicity/order/conditional waiting; not fair scheduling proof |

| P7-DSA-09 | UF / Sedgewick and Wayne, algs4 author repository | [Source](https://raw.githubusercontent.com/kevin-wayne/algs4/master/src/main/java/edu/princeton/cs/algs4/UF.java) | Rolling source; publication not established | 2026-11-03 | Primary author implementation; high for documented mechanism | Union by rank/path compression and amortized bounds; no local UF execution |

## Verification boundary

New examples are reviewed traces, not newly executed algorithms/SQL/concurrency or live backend/model integration. Earlier suites retain their exact documented scope. The public Princeton chapter/source website returned403; only the readable official author-repository URLs above are accepted for the new trie/Fenwick citations. An unrelated public explanation was not substituted as primary evidence.

Last reviewed: **2026-11-03**.
