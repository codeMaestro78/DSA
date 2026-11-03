# Practical database questions

Ten scenarios emphasize exact result semantics, database enforcement and transaction schedules. SQL uses PostgreSQL syntax; conceptual alternatives must preserve the same invariants. Native fixture validation status is linked per executable example; the attempted server start was blocked and no native pass is claimed.

All prompts and example workloads are original, company-neutral **[INFERRED]** practice. Difficulty, role and round labels are editorial guidance, not employer ratings. Sources support underlying mechanisms, not the invented scenario or a unique correct architecture. Attempt the prompt before opening the review.

<a id="qb-db-001"></a>

## QB-DB-001 — Joining tags doubles the invoice total

| Field | Value |
|---|---|
| Category | SQL result grain and joins |
| Difficulty | Beginner |
| Target roles | Backend; SDE; data |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | SQL result grain and joins: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-002, QB-DB-003 |
| Sources | [JOIN: Table expressions](https://www.postgresql.org/docs/18/queries-table-expressions.html) |

### Prompt

Use invoices(id, tenant_id, cents), tags(invoice_id, label). Tenant 1 has invoice (10,1,100) with two tag rows and invoice (11,1,100) with none. Return tenant 1’s total invoice cents and number of invoices that have any tag, without double counting. Invoice cents are non-null integers. Explain why SUM(DISTINCT cents) is incorrect.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Aggregate at invoice grain and use an existence test rather than multiplying invoices by their tags:

<!-- phase5-sql: QB-DB-001 -->
```sql
SELECT COALESCE(SUM(i.cents), 0) AS total_cents,
       COUNT(*) FILTER (WHERE EXISTS (
         SELECT 1 FROM tags t WHERE t.invoice_id = i.id
       )) AS tagged_invoices
FROM invoices i
WHERE i.tenant_id = 1;
```

The fixture returns `(200, 1)`. With no tenant-1 invoices it returns `(0, 0)`. Invoice IDs are globally unique in this exercise; a composite tenant-local identity would also require tenant scope in the tag relationship.

**Explanation:** Invoice 10 contributes 100 once, regardless of two matching tags. Invoice 11 contributes 100 and zero to tagged count. A direct join produces two copies of 10, while an inner join loses 11. SUM(DISTINCT cents) would collapse legitimate separate invoices with equal amounts and incorrectly return 100. A separately grouped tag relation is another valid solution if its grain is one row per invoice.

**Followups:** How do you filter for a specific label? What if invoice IDs are only unique within a tenant? Which foreign key enforces that relationship?

**Common mistakes:** Fixing join multiplication with DISTINCT over money; assuming COUNT(*) after a left join counts children; omitting tenant identity from local IDs.

**Validation:** The runner extracts this marked query; expected results are reasoned. Native execution remains blocked; see [validation status](../validation/questions-system/README.md).

</details>

<a id="qb-db-002"></a>

## QB-DB-002 — Choose one latest device event deterministically

| Field | Value |
|---|---|
| Category | SQL windows and tie policy |
| Difficulty | Intermediate |
| Target roles | Backend; data; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | SQL windows and tie policy: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-003, QB-DB-004 |
| Sources | [WINDOW: Window functions](https://www.postgresql.org/docs/18/functions-window.html) |

### Prompt

Use device_events(id, device_id, observed_at, state). Times are NOT NULL. Device A has IDs 1 at 10:00 and 2,3 at 10:01; device B has ID4 at 10:02. Select exactly one latest event per device, breaking equal timestamps with the highest event ID. IDs are unique.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Rank each device’s events with the complete tie-breaking order, then keep position one:

<!-- phase5-sql: QB-DB-002 -->
```sql
SELECT device_id, id, state
FROM (
  SELECT device_id, id, state,
         ROW_NUMBER() OVER (
           PARTITION BY device_id
           ORDER BY observed_at DESC, id DESC
         ) AS pos
  FROM device_events
) ranked
WHERE pos = 1
ORDER BY device_id;
```

The fixture selects A/3 and B/4. The final ordering makes the output deterministic across devices, independently of the ordering used inside each window.

**Explanation:** ROW_NUMBER selects one row; RANK could select multiple tied rows if the tie-breaker were omitted. MAX(observed_at) alone does not identify the corresponding state and can rejoin several rows. The ID tie-breaker is an explicit editorial policy, not proof that a higher ID represents later real-world observation. If late-arriving events can revise history, define whether observed time or ingestion sequence controls “latest.” An index might help access but does not replace the selection semantics.

**Followups:** What changes if ties should all be returned? How would missing timestamps be handled? How does pagination differ from latest-per-group?

**Common mistakes:** Selecting an arbitrary state next to MAX(time); omitting the tie-breaker; assuming outer output order follows the window order.

**Validation:** Runner prepared for tie, single-event and empty input fixtures; native execution blocked, no observed pass. See [validation status](../validation/questions-system/README.md).

</details>

<a id="qb-db-003"></a>

## QB-DB-003 — A nullable flag makes a filter disappear

| Field | Value |
|---|---|
| Category | NULL and three-valued logic |
| Difficulty | Beginner |
| Target roles | Backend; SDE; full-stack |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | NULL and three-valued logic: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-004, QB-DB-005 |
| Sources | [NULL: Comparison functions and operators](https://www.postgresql.org/docs/18/functions-comparison.html) |

### Prompt

Use reviews(id, approved), with values (1,true), (2,false), (3,NULL). Return every review not explicitly approved; unknown approval should remain in the queue. Explain why approved <> true loses a row, and state whether NULL means false in general.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Express the queue policy directly:

<!-- phase5-sql: QB-DB-003 -->
```sql
SELECT id
FROM reviews
WHERE approved IS NOT TRUE
ORDER BY id;
```

The result is IDs 2 and 3. In contrast, approved <> true evaluates to unknown for row 3; WHERE retains only true expressions, so that predicate returns only ID 2.

**Explanation:** NULL records an absent/unknown value here. It is not universally equivalent to false: application policy chooses to include unknown decisions in this particular queue. IS NOT TRUE is true for both false and unknown. If the system needs distinct pending, rejected and approved states, an explicit status with a permitted-value constraint may communicate the domain better than an overloaded nullable boolean. COALESCE can encode the same queue policy, but replacing NULL with false everywhere changes other queries and potentially historical meaning.

**Followups:** How would you return only undecided reviews? What changes for COUNT(approved)? How should an API expose missing versus rejected?

**Common mistakes:** Comparing approved = NULL; assuming NULL is false in every expression; changing the storage default without resolving existing unknown values.

**Validation:** Runner prepared for true/false/NULL, all-NULL and empty fixtures; native execution blocked, no observed pass. See [validation status](../validation/questions-system/README.md).

</details>

<a id="qb-db-004"></a>

## QB-DB-004 — Only one active alias per tenant

| Field | Value |
|---|---|
| Category | Constraints and partial unique indexes |
| Difficulty | Intermediate |
| Target roles | Backend; data; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Constraints and partial unique indexes: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-005, QB-DB-006 |
| Sources | [PARTIAL: Partial indexes](https://www.postgresql.org/docs/18/indexes-partial.html); [CONSTRAINT: Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html) |

### Prompt

Use aliases(id primary key, tenant_id NOT NULL, alias NOT NULL, active boolean NOT NULL). Historical inactive aliases may share a name; active aliases must be unique within a tenant using deterministic case-sensitive exact text equality (the fixture declares alias text COLLATE "C"). Propose database enforcement that handles concurrent inserts.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** A partial unique index expresses the active subset:

<!-- phase5-sql: QB-DB-004 -->
```sql
CREATE UNIQUE INDEX one_active_alias
ON aliases (tenant_id, alias)
WHERE active;
```

Two active rows for tenant 1 and alias `help` cannot both commit. An inactive historical row with that key is permitted, and tenant 2 may independently use `help`. Activating an old row is checked against the same index.

**Explanation:** A read-then-insert application check races when two transactions both observe no active alias. The index makes uniqueness part of the write decision. Its predicate depends only on the row, so no scheduled cleanup is required to determine which rows participate. Names use the prompt’s exact equality semantics; a case-insensitive policy would need a deliberately chosen normalized representation/collation and corresponding unique index. Catch the uniqueness error and translate it to a useful conflict response; do not blindly retry an impossible same-name insert.

**Followups:** What if active can be NULL? How do you atomically retire one alias and activate another? What if active were based on a moving clock?

**Common mistakes:** Assuming a preliminary SELECT prevents duplicates; silently changing case sensitivity; using a time-dependent predicate as if index membership updates itself.

**Validation:** Runner prepared to test duplicate active insert, conflicting activation, inactive and other-tenant inserts. Native execution blocked, no observed pass. See [validation status](../validation/questions-system/README.md).

</details>

<a id="qb-db-005"></a>

## QB-DB-005 — Concurrent reservations exceed the room capacity

| Field | Value |
|---|---|
| Category | Atomic guarded updates |
| Difficulty | Intermediate |
| Target roles | Backend; SDE; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Atomic guarded updates: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-006, QB-DB-007 |
| Sources | [ISO: Transaction isolation](https://www.postgresql.org/docs/18/transaction-iso.html); [LOCK: Explicit locking](https://www.postgresql.org/docs/18/explicit-locking.html) |

### Prompt

A room row stores remaining_seats=1. Two Read Committed transactions each need one seat. Describe a single conditional update plus reservation write so at most one request succeeds. The room exists and all reservation writers use this protocol; no external calls belong inside the transaction.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Within one transaction, decrement remaining_seats by one only where the room ID matches and remaining_seats is at least one; return the updated row. Create the reservation only if that update returned a row, then commit both changes together. Add a nonnegative database check as defense against other invalid writes. On a zero-row result, report sold out; on transaction rollback, neither decrement nor reservation persists. Use a request identity to prevent retrying a successful logical reservation as a second purchase.

**Explanation:** Both requests contend on the same existing row. After the first commits the counter at zero, PostgreSQL Read Committed reevaluates the second updater’s condition against that updated row, so it cannot also decrement successfully. A separate earlier SELECT followed by a stale constant assignment does not provide the same safety. A counter makes this invariant local but becomes a hot contention point; measure wait times before choosing a more complex allocation design.

**Followups:** How do cancellations restore capacity exactly once? What if one reservation needs multiple rooms? Where is retry identity recorded?

**Common mistakes:** Checking stock outside the transaction; performing a slow provider call while holding the room lock; adding a reservation after a zero-row update.

**Validation:** Reasoned concurrency protocol; this new room schedule is not executed. The separate Phase 2 inventory checks do not constitute execution of this prompt.

</details>

<a id="qb-db-006"></a>

## QB-DB-006 — Two independent checks break a shared invariant

| Field | Value |
|---|---|
| Category | Write skew and isolation |
| Difficulty | Advanced |
| Target roles | Backend; SDE; data |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Write skew and isolation: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-007, QB-DB-008 |
| Sources | [ISO: Transaction isolation](https://www.postgresql.org/docs/18/transaction-iso.html); [CONSTRAINT: Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html) |

### Prompt

Two administrators manage backup retention. At least one of policies A/B must remain enabled. Initially both are enabled. At PostgreSQL Repeatable Read, T1 reads both and disables A; T2 reads both and disables B; they update different rows. Explain the outcome and two sound enforcement approaches.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Both transactions can commit at PostgreSQL Repeatable Read because each writes a different row while both decisions used the initial snapshot. The final state has no enabled policy. Serializable transactions can detect this dangerous dependency structure and abort one participant; the application must retry the complete transaction, including reads and the decision, after a serialization failure. Alternatively serialize policy changes through a common existing guard row or a single aggregate row representing this invariant.

**Explanation:** For a guard-row protocol at Read Committed, acquire the same guard lock before issuing a separate fresh statement that checks enabled policies, then perform the change while holding that lock. Merely acquiring a guard at Repeatable Read does not refresh an already-established stale snapshot. Every writer must follow the chosen protocol. A row CHECK constraint cannot safely assert an arbitrary cross-row count. The scenario demonstrates that “repeatable” observations are not equivalent to serial execution.

**Followups:** What happens if policies can be inserted/deleted? How do full-transaction retries handle an external side effect? What must every write path enforce?

**Common mistakes:** Retrying COMMIT alone; claiming disjoint row locks enforce a shared predicate; assuming Repeatable Read universally prevents write skew.

**Validation:** New retention schedule is reasoned, not executed; the existing Phase 2 doctor schedule is separate evidence.

</details>

<a id="qb-db-007"></a>

## QB-DB-007 — A plan estimates ten rows and reads a million

| Field | Value |
|---|---|
| Category | Query-plan diagnosis |
| Difficulty | Advanced |
| Target roles | Backend; data; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Query-plan diagnosis: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-008, QB-DB-009 |
| Sources | [PLAN: Using EXPLAIN](https://www.postgresql.org/docs/18/using-explain.html); [INDEX: Multicolumn indexes](https://www.postgresql.org/docs/18/indexes-multicolumn.html) |

### Prompt

A tenant-filtered event query became slow after a few tenants grew rapidly. EXPLAIN estimates ten matching rows but EXPLAIN ANALYZE shows one million; the plan uses a nested loop. Give an investigation sequence and explain why forcing an index is not yet a justified fix.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Check query text, parameter values, data distribution, statistics freshness and the actual versus estimated rows at the earliest diverging node. Then examine loop counts, buffer reads, sort spills, locks and elapsed time. A nested loop with a severely underestimated outer relation can repeat an inner lookup far more often than expected. Update or improve statistics when justified, consider whether tenant and filter columns are correlated, and compare representative large and small tenants rather than one lucky execution.

**Explanation:** An index can reduce a selective lookup yet still be inefficient when a query reads most of the table. Evaluate a concrete composite index aligned to filters and ordering, including write/storage costs. EXPLAIN ANALYZE actually executes its statement; use a controlled representative environment, especially for writes, whose triggers or external effects may survive simplistic rollback assumptions. Compare repeated measurements with cache state and parameter-plan behavior recorded. A tiny fixture cannot establish a production speedup.

**Followups:** Could a generic prepared plan hurt large tenants? When would extended statistics help? Which result would make a sequential scan reasonable?

**Common mistakes:** Calling any sequential scan a bug; adding indexes without the access pattern; reading one estimated total as a latency guarantee.

</details>

<a id="qb-db-008"></a>

## QB-DB-008 — A feed changes between pages

| Field | Value |
|---|---|
| Category | Keyset pagination under mutation |
| Difficulty | Intermediate |
| Target roles | Backend; full-stack; SDE |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Keyset pagination under mutation: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-009, QB-DB-010 |
| Sources | [INDEX: Multicolumn indexes](https://www.postgresql.org/docs/18/indexes-multicolumn.html); [HTTP: HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110.html) |

### Prompt

An activity feed is ordered by immutable created_at descending, then unique id descending; both are NOT NULL. New rows can arrive between pages and old rows can be deleted. Explain a cursor contract for forward pagination and whether it provides a repeatable historical snapshot.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Put the last returned (created_at,id) pair in an integrity-protected cursor with tenant, filter and expiry context. For the next descending page, select rows whose pair is lexicographically smaller than that pair and use the same complete ordering and page limit. Bind authorization on every request. This avoids shifting offsets when a new row is inserted ahead of the cursor; deleted rows simply disappear. Never replace the cursor boundary by “the row’s current position.”

**Explanation:** For a page ending at (10:00,50), a new (10:05,90) does not belong after that cursor, while (09:59,80) does. If created_at is mutable, an existing row can move across the boundary and be missed or repeated; the prompt excludes that case. Even with immutable ordering, later backdated inserts can appear on later pages, and deletions change results. A consistent historical export needs a snapshot/materialization or explicit versioned as-of contract, not just a cursor.

**Followups:** How do you support reverse pages? What does a tampered cursor reveal? What extra predicate bounds the feed to an initial high watermark?

**Common mistakes:** Claiming keysets freeze all data; ordering on a nonunique timestamp only; accepting a tenant ID from the cursor as permission.

</details>

<a id="qb-db-009"></a>

## QB-DB-009 — Deleting a tenant leaves derived data behind

| Field | Value |
|---|---|
| Category | Foreign keys and deletion lifecycle |
| Difficulty | Intermediate |
| Target roles | Backend; FDE; data |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Foreign keys and deletion lifecycle: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-010, QB-DB-001 |
| Sources | [CONSTRAINT: Constraints](https://www.postgresql.org/docs/18/ddl-constraints.html); [OUTBOX: Transactional outbox pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) |

### Prompt

A tenant owns projects; projects own tasks. A search service and object store also hold copies. Explain relational delete options and an observable deletion workflow. A successful “deletion requested” response may precede full erasure; legal retention obligations are outside this invented scenario.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Use foreign keys to prevent orphan project/task references. Choose RESTRICT when an accidental parent deletion must fail until an explicit workflow handles dependents, or CASCADE when dependent ownership and deletion semantics genuinely match. Keep tenant identity consistent in composite relationships where IDs are tenant-local. Begin deletion by marking the tenant unavailable for new work and creating a durable deletion job; immediately deny normal access according to the product contract.

**Explanation:** A database cascade covers its defined relational rows, not search indexes, caches, queued jobs, object versions or backups. Track each derived system’s cleanup status with retryable, idempotent steps and a reconciliation report. Block or reject late workers that would recreate copies after deletion begins. Declare completion only for the documented scope; backup expiry and prior downloads need separate handling. For large tenants, batch work to bound transaction size, lock duration and replica lag while keeping the tenant inaccessible.

**Followups:** What if cleanup fails permanently? How does a restored backup avoid resurrecting deleted tenants? How are deletion completion and retention communicated?

**Common mistakes:** Equating a foreign-key cascade with universal erasure; deleting the job ledger before reconciliation; leaving active workers able to republish removed data.

</details>

<a id="qb-db-010"></a>

## QB-DB-010 — Two transfer requests deadlock

| Field | Value |
|---|---|
| Category | Lock order and transaction retry |
| Difficulty | Advanced |
| Target roles | Backend; SDE; platform |
| Round | Technical scenario / practical discussion |
| Experience | 0–2 years; depth scales with followups |
| Company scope | Company-neutral [INFERRED]; no encounter-frequency claim |
| Evaluates | Lock order and transaction retry: correctness, assumptions, failure reasoning |
| Related IDs | QB-DB-001, QB-DB-002 |
| Sources | [LOCK: Explicit locking](https://www.postgresql.org/docs/18/explicit-locking.html); [OUTBOX: Transactional outbox pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html) |

### Prompt

Transfer T1 locks account 10 then wants account 20. T2 locks 20 then wants 10. Amounts are integer cents; balances and a transfer ledger live in one PostgreSQL database. Explain deadlock resolution, prevention, and a safe retry boundary.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** This forms a wait cycle. PostgreSQL detects deadlocks and aborts a participant; do not depend on which transaction loses. Acquire account locks in a consistent total order, such as ascending account ID, across every transfer path before validating balances and applying relative debit/credit updates. Keep the ledger entry in the same transaction and use a unique logical transfer identity so a lost success response cannot cause a second transfer.

**Explanation:** On a deadlock error, roll back and retry the complete transaction with a bounded attempt/deadline policy, re-reading balances and redoing decisions. If a connection fails during commit, the outcome is ambiguous: query the durable transfer identity rather than assuming rollback. Deterministic lock order reduces this specific cycle but does not prove that every trigger, secondary index or other resource in a larger application is deadlock-free. External notifications should occur through a durable asynchronous handoff after the financial state is committed.

**Followups:** How do self-transfers behave? Which retries are not transient? How does a lock timeout differ from deadlock detection?

**Common mistakes:** Retrying just the last update; writing a ledger outside the balance transaction; assuming a network error proves that no transfer committed.

</details>

## References and verification

[Phase 5 system source register](PHASE5_SYSTEM_SOURCES.md). [Question-bank navigation](README.md).

Last verified: **2026-11-02**. Primary sources inspected for the stated mechanisms. Scenario reasoning reviewed; this is not a production design, benchmark, cloud deployment or candidate assessment.
