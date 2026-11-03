# SDE Questions

Phase 5: 10 original, company-neutral [INFERRED] exercises. Difficulty, role and timing are editorial judgments, not employer evidence. Target early-career candidates (0–2 years); Advanced exercises are stretch practice and Expert is optional. Attempt each prompt before opening the review. Score contract, mechanism, worked reasoning and limitations separately; naming a tool alone is insufficient. Sources support technical mechanisms, not these invented scenarios or interview frequency.

[Source metadata](PHASE5_SDE_ML_SOURCES.md). Code validation and commands: [questions-core](../validation/questions-core/README.md).

<a id="qb-sde-001"></a>

## QB-SDE-001 — A mutable map key disappears

| Field | Value |
|---|---|
| Category | Equality and hashing |
| Difficulty | Beginner |
| Target roles | SDE; Backend Engineer |
| Round | Language discussion |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Equality and hashing: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-002 |
| Sources | [Object contract (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html), [Map contract (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html) |

### Prompt

A Java map key contains a mutable customer ID. Its equals/hashCode use that ID. After insertion, code edits the ID and lookup fails. Explain the failure and propose a contract that also handles two separately constructed keys for the same customer.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The map chose a bucket from the hash at insertion. Changing the ID can change the lookup hash without relocating the stored entry. Even a lookup using the same object can therefore fail. Make the identity field immutable, use a stable immutable value key, and implement equals and hashCode from the same fields. Two equal keys must have equal hash codes; unequal keys may collide. Do not use reference equality for customer identity.

**Explanation:** Trace a key with ID 7 inserted under hash h(7), then mutated to 9. A search using h(9) has no obligation to scan the old bucket. Making the map concurrent does not repair this identity error. If migration is unavoidable, remove under the original identity before mutation and insert a new immutable key. A record is useful only when its identity components are themselves stable; a final reference to a mutable list is not deep immutability.

**Followups:** What happens when distinct keys collide? How would a case-insensitive ID define equality?

**Common mistakes:** Overriding equals without hashCode; assuming final makes nested state immutable; using toString as identity.

</details>

<a id="qb-sde-002"></a>

## QB-SDE-002 — Rebinding is not mutation

| Field | Value |
|---|---|
| Category | Java parameter semantics |
| Difficulty | Beginner |
| Target roles | SDE; Backend Engineer |
| Round | Language discussion |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Java parameter semantics: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-003 |
| Sources | [JLS 17 expressions](https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html), [List contract (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html) |

### Prompt

A method receives a List<String>, appends "x", then assigns its parameter to a new list. Describe exactly what the caller observes. How can an API promise a snapshot when its input is a list of mutable objects?

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Java passes the reference value by value. Initially caller and parameter refer to the same list, so the append changes an object visible to the caller, assuming the list supports mutation. Rebinding the local parameter changes only that local variable. The caller still references the original list with the appended entry. If append throws, subsequent statements do not run. A snapshot contract requires deciding whether only list membership or element state must be frozen.

**Explanation:** A shallow copied list separates additions/removals, but each mutable element can still be shared. For immutable Strings this is adequate for element state. For mutable Customer objects, copy required fields into immutable snapshot values or document shared references. Also define null handling and whether concurrent modification during copying is supported. A copy operation alone cannot promise an atomic view of arbitrary concurrently changing state. Returning an unmodifiable wrapper prevents mutation through that wrapper but does not necessarily disconnect the original backing collection.

**Followups:** How would you replace the caller value intentionally? Why can an immutable list still contain mutable elements?

**Common mistakes:** Saying objects are passed by reference; confusing an unmodifiable view with a disconnected deep snapshot.

</details>

<a id="qb-sde-003"></a>

## QB-SDE-003 — An LRU cache with explicit misses

| Field | Value |
|---|---|
| Category | Data structure contracts |
| Difficulty | Intermediate |
| Target roles | SDE; Backend Engineer |
| Round | Backend coding |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Data structure contracts: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-004 |
| Sources | [LinkedHashMap (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedHashMap.html) |

### Prompt

Implement a single-threaded integer cache with capacity c≥0. put inserts or overwrites; successful get and overwrite both refresh recency. A miss returns OptionalInt.empty because -1 is a valid value. Reject negative capacity. Do not add TTL. Libraries are allowed; explain the manual-map/list alternative. For c=2 trace put(1,-1),put(2,20),get(1),put(3,30).

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The final keys are 1 and 3: get(1) moves key 1 to most recent, so inserting 3 evicts 2. An access-ordered LinkedHashMap implements recency and supports expected constant-time lookup/update under suitable hashing assumptions; an insertion-ordered map does not implement this contract. At zero capacity, insertion is accepted but nothing is retained. No sentinel value represents absence. The implementation below exposes only the operations whose recency behavior the contract specifies.

**Explanation:** A manual implementation needs a hash map from key to doubly linked node and a consistent least/most recent order. On a hit detach and append exactly once; overwriting must not grow size. Eviction removes the same entry from both structures. Space is O(c) retained entries, with a transient c+1 entry during insertion in this library implementation; snapshot output is O(c). The cache is not thread safe: even a get changes access order. Concurrency requires an additional linearization contract and synchronization.

**Followups:** Add remove without refreshing other keys. How would weighted capacity differ from entry count?

**Common mistakes:** Using -1 for a miss; insertion ordering; failing at capacity zero; claiming worst-case O(1) for every hash-table operation.

**Validation:** The exact Java block is extracted, compiled with `--release 17`, and tested against an independent list model; see validation README. This is a teaching cache, not a concurrent production component.

<!-- phase5-code: ContractLru.java -->
```java
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.OptionalInt;

public final class ContractLru {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> entries =
        new LinkedHashMap<>(16, 0.75f, true);

    public ContractLru(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("negative capacity");
        this.capacity = capacity;
    }
    public OptionalInt get(int key) {
        Integer value = entries.get(key);
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);
    }
    public void put(int key, int value) {
        entries.put(key, value);
        if (entries.size() > capacity) {
            var it = entries.keySet().iterator();
            it.next();
            it.remove();
        }
    }
    public List<Integer> keysLeastFirst() {
        return new ArrayList<>(entries.keySet());
    }
}
```

</details>

<a id="qb-sde-004"></a>

## QB-SDE-004 — A volatile counter loses increments

| Field | Value |
|---|---|
| Category | Atomicity and visibility |
| Difficulty | Intermediate |
| Target roles | SDE; Backend Engineer |
| Round | Concurrency reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Atomicity and visibility: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-005 |
| Sources | [AtomicInteger (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html), [JLS 17 threads and locks](https://docs.oracle.com/javase/specs/jls/se17/html/jls-17.html) |

### Prompt

Two threads each run 100,000 increments of volatile int completed initially zero; the main thread joins both and reads it. Is 200,000 guaranteed? Supply a correct alternative and explain what join establishes.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** No. Increment is a read, addition and write, so two threads can read the same old value and both publish the same new value. Volatile visibility does not make that compound operation atomic. Use AtomicInteger.incrementAndGet, or synchronize every increment on the same monitor. Under this bounded exercise no integer overflow occurs. Joining both successfully terminated workers orders their completed actions before the main thread continues, but it does not restore updates already lost between workers.

**Explanation:** A two-operation witness is enough: both read 10, each computes 11, and each writes 11. Two increments have produced one net increment. With an atomic counter the read-modify-write operation has a single linearization point. If the invariant couples completed with another mutable field, independently atomic fields may still expose impossible combinations; protect the combined invariant with one protocol. Do not propose a probabilistic sleep as a synchronization primitive. An observed 200,000 in one run is compatible with an incorrect implementation.

**Followups:** What if the count can exceed Integer.MAX_VALUE? When is a contention-oriented approximate snapshot acceptable?

**Common mistakes:** Treating volatile as a lock; assuming join serializes the workers; claiming a stress test proves all interleavings.

</details>

<a id="qb-sde-005"></a>

## QB-SDE-005 — Bound the executor before an outage

| Field | Value |
|---|---|
| Category | Backpressure and cancellation |
| Difficulty | Advanced |
| Target roles | SDE; Backend Engineer |
| Round | Concurrency design |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Backpressure and cancellation: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-006 |
| Sources | [ThreadPoolExecutor (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html) |

### Prompt

A service receives 1,000 tasks/s; ten workers each finish 50 tasks/s. An unbounded executor queue grows. Propose a bounded admission contract for caller deadlines, overload and shutdown. No distributed queue is required.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The maximum steady processing rate is roughly 500 tasks/s under these assumptions, so accepting 1,000 indefinitely is unstable. A queue only absorbs a finite burst. Set a finite queue, explicit rejection behavior and an end-to-end deadline. Estimate waiting time using measured service times and queue occupancy, reject work that cannot meet its deadline, and expose overload distinctly from task failure. A queue capacity is a memory and latency budget, not extra throughput.

**Explanation:** At a 500-task/s deficit, an initially empty 1,000-slot queue fills in roughly two seconds in this simplified constant-rate model. Worker scheduling, variability and arrival bursts change that estimate. Cancellation must remove or skip stale queued tasks where possible; interrupting running code is cooperative and does not prove that external effects did not occur. During shutdown, stop admission, drain only within a bounded budget and report unfinished work. Caller-runs can apply backpressure but may block a latency-sensitive request or event-loop thread.

**Followups:** How would you measure queue delay separately from execution? What if workers wait mostly on I/O?

**Common mistakes:** Increasing queue size as the only fix; silently dropping tasks; assuming cancel undoes an effect.

</details>

<a id="qb-sde-006"></a>

## QB-SDE-006 — Composition protects a pricing invariant

| Field | Value |
|---|---|
| Category | OOP and low-level design |
| Difficulty | Intermediate |
| Target roles | SDE; Backend Engineer |
| Round | Low-level design |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | OOP and low-level design: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-007 |
| Sources | [BigDecimal (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/math/BigDecimal.html) |

### Prompt

A checkout supports fixed and percentage discounts. All discounts must return a final nonnegative amount in one currency, rounded once at checkout. Design interfaces and tests. A new discount implementation must not bypass validation.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Keep the checkout aggregate responsible for the final invariant and use a small DiscountPolicy interface to calculate a proposed adjustment. Represent money with amount plus currency and an explicit scale/rounding policy. Validate the proposal before committing the result: reject cross-currency adjustments and non-finite or out-of-contract values, cap or reject over-discounts according to the stated product rule. Do not let subclasses replace checkout validation. Composition allows policy extension while retaining a single authority for the invariant.

**Explanation:** For a 100.00 charge, a 10% policy proposes 10.00; checkout commits 90.00 only after currency and bounds checks. The order of stacked discounts matters: 10% then 5.00 differs from 5.00 then 10%. State an ordering contract and round at its agreed boundary, rather than letting each policy round independently. Test zero charge, maximum discount, multiple policies and intentionally invalid policy output. Use BigDecimal from decimal strings or integral minor units where appropriate; binary floating point is a poor default for exact decimal money.

**Followups:** Can a promotion require metadata without bloating every policy? What changes for currencies with different minor units?

**Common mistakes:** Equating inheritance with extensibility; returning a naked double; allowing a policy to mutate the order.

</details>

<a id="qb-sde-007"></a>

## QB-SDE-007 — Preserve the useful exception

| Field | Value |
|---|---|
| Category | Resource lifetime |
| Difficulty | Intermediate |
| Target roles | SDE; Backend Engineer |
| Round | Debugging discussion |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Resource lifetime: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-008 |
| Sources | [JLS 17 try statements](https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html), [AutoCloseable (Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/AutoCloseable.html) |

### Prompt

A file parser can throw while reading and again while closing. Explain the behavior of Java try-with-resources, the order of closing two resources, and what a caller should log. Then decide who owns a stream passed into a parser.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** When the body throws and close also throws, try-with-resources preserves the body exception and attaches the closing exception as suppressed. Resources are closed in reverse initialization order. If initialization of a later resource fails, already initialized resources are still closed. Log the primary exception with its cause and suppressed exceptions rather than logging only a message. Closing is a resource-lifetime obligation; it does not automatically make the whole operation transactional.

**Explanation:** For resources opened as input then decoder, decoder closes before input. If close on decoder fails, cleanup still proceeds for input, and exception composition follows the language rules. A parser that accepts a caller-owned stream should explicitly state whether it closes the stream; a common useful contract is that the opener owns closure. That convention is an API decision, not a Java mandate. Test body success/close failure, body failure/close failure and partial initialization. Avoid a finally block that returns or throws over the original exception.

**Followups:** What if close is called twice? How would a streaming response transfer ownership?

**Common mistakes:** Swallowing close failures; assuming all AutoCloseable implementations must be idempotent; closing caller resources without a contract.

</details>

<a id="qb-sde-008"></a>

## QB-SDE-008 — Retry without duplicating an invoice

| Field | Value |
|---|---|
| Category | Backend state machine |
| Difficulty | Advanced |
| Target roles | SDE; Backend Engineer |
| Round | Backend reasoning |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Backend state machine: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-009 |
| Sources | [Making retries safe with idempotent APIs](https://aws.amazon.com/builders-library/making-retries-safe-with-idempotent-APIs/) |

### Prompt

An invoice service receives a client request ID and canonical request body. The same ID may be retried after a timeout. Define behavior for concurrent duplicates, changed bodies and crashes. Assume one transactional database stores invoice and dedup record; there is no external payment call.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Bind request ID to authenticated scope and a request fingerprint. In one transaction claim the unique key, create the invoice, and persist its final result. Concurrent duplicates must converge through a unique constraint and a defined wait/retry path. After commit, the same fingerprint returns the stored invoice/result; a changed fingerprint returns a conflict. A timeout is an unknown outcome, so the client retries the same ID rather than generating a new one.

**Explanation:** A crash before commit leaves no committed invoice; a crash after commit leaves the persisted answer available for retry. An in-memory map cannot supply this crash boundary. Define canonicalization carefully: equivalent JSON formatting should not create a different semantic operation. Set retention to cover the supported retry horizon; deleting the dedup record while accepting old retries can create duplicates. This single-database contract avoids claiming exactly-once delivery. Introducing an external side effect would require a separate reconciliation/idempotency protocol and would invalidate the simple atomic proof.

**Followups:** How does the fingerprint include currency or tenant? What response represents an operation still in progress?

**Common mistakes:** Writing the dedup marker after invoice creation in a separate transaction; treating a timeout as proof of rollback.

</details>

<a id="qb-sde-009"></a>

## QB-SDE-009 — A cache retains a completed request

| Field | Value |
|---|---|
| Category | Runtime and memory diagnosis |
| Difficulty | Intermediate |
| Target roles | SDE; Backend Engineer |
| Round | Debugging discussion |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Runtime and memory diagnosis: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-010 |
| Sources | [Java 17 GC implementation](https://docs.oracle.com/en/java/javase/17/gctuning/garbage-collector-implementation.html) |

### Prompt

Heap use rises across requests even after GC. A static map stores per-request futures; completed futures still reference large result objects. Explain the retention path, a diagnostic procedure and a cleanup contract without assuming every increase is a leak.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** GC can reclaim unreachable objects, not objects still reachable from a static map. A completed future can retain its result graph even when no active caller needs it. Capture a baseline and comparable post-load heap evidence, inspect dominators and paths to GC roots, and correlate retained entries with request counts. Also inspect queue growth and legitimate caches before declaring a leak. A temporary allocation spike and sustained retained growth are different observations.

**Explanation:** Define removal on every terminal outcome, including success, exception and cancellation. If late callers need a result, replace unlimited retention with a bounded, time-scoped result store and a clear expiry response. A reference cycle by itself does not prevent tracing GC; a root path does. A weak-key map is not a general remedy when values indirectly retain keys or clients require durable retrieval. Validate cleanup with deterministic lifecycle tests and repeated bounded-load observations; one small heap snapshot cannot prove a long-lived service is leak-free.

**Followups:** What if future completion never happens? How would you distinguish high allocation rate from retention?

**Common mistakes:** Calling System.gc as a fix; looking only at RSS; assuming static means inherently wrong rather than examining ownership.

</details>

<a id="qb-sde-010"></a>

## QB-SDE-010 — A bounded queue needs two conditions

| Field | Value |
|---|---|
| Category | Concurrency invariant |
| Difficulty | Expert |
| Target roles | SDE; Backend Engineer |
| Round | Optional concurrency deep dive |
| Experience | 0–2 years; optional stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Concurrency invariant: correct mechanism and explicit assumptions |
| Related IDs | QB-SDE-001 |
| Sources | [JLS 17 threads and locks](https://docs.oracle.com/javase/specs/jls/se17/html/jls-17.html) |

### Prompt

Design a bounded blocking FIFO with capacity c>0, multiple producers/consumers and interruptible put/take. Explain state, wait predicates and linearization. No code is required; do not claim fairness unless you add a fairness contract.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Protect the ring buffer, head, tail and count with one lock. put waits while count==c; take waits while count==0. Wait in loops because waking does not guarantee the predicate still holds, and spurious wakeups are permitted. A successful put writes one item and increments count under the lock; take removes one item and decrements count. Signal the opposite condition after the state transition. Define null handling and whether interruption before mutation aborts the operation.

**Explanation:** The invariant is 0≤count≤c with FIFO order among committed insertions. The enqueue/dequeue mutation under the lock provides a linearization point. Use separate notEmpty/notFull conditions or a carefully justified notification protocol. An if around await can allow two awakened consumers to remove one item. Deadlines need remaining-time loops rather than repeatedly waiting the original timeout. Shutdown needs an extra state and clear drain behavior; it cannot be inferred from interruption alone. Fixed-size storage uses O(c) space, and uncontended queue mutations take O(1); blocking time has no finite bound without scheduling and progress assumptions.

**Followups:** How does close wake both producers and consumers? Can an interrupted thread have already completed its operation?

**Common mistakes:** Signaling outside the owning lock; using if instead of while; claiming FIFO item order proves fair thread scheduling.

</details>
