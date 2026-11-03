# SDE mock — interviewer and reference solution

**Spoilers: interviewer/reviewer material.** Open the [candidate packet](SDE_Mock_Interview.md) first. This companion supplies original [INFERRED] reference reasoning for **MOCK-SDE-01**, not a required employer answer. Accept alternatives that preserve the contract and justify their tradeoffs.

## Interviewer instructions

Read the candidate contract before the session. Preflight/runtime setup and a 10–15 minute debrief are outside the 75-minute duration. Follow the four stage budgets (10 + 30 + 20 + 15 minutes); announce transitions without turning a slower answer into a personal judgment. Do not supply the solution at the first pause. Ask one neutral question, then use the smallest useful hint if requested. Stop optional depth when core correctness needs time.

Use **H0** for clarification of the stated contract; it does not reduce independence. Log **H1** (focus attention), **H2** (suggest a representation/mechanism), and **H3** (give a key step). Record when and how a hint changed the attempt; do not apply an automatic score deduction to every dimension. The candidate may make a different valid design choice. Keep the final artifacts, counterexamples and actual statements as scoring evidence.

## Task review and expected solutions

### SDE-T1 — Establish the contract

**Checkpoint, minute 10:** Ask, “Which event exactly leaves the window at its left boundary?” Expected clarification: an event at `timestamp − window` is excluded. Equal timestamps and repeated users are valid; no event ID is provided to deduplicate. An invalid call changes nothing. State that no timer-driven/query-only API is required: expiration is observed at the next accepted record call. The count is exact for the accepted prefix, not every real-world event.

**Expected worked reasoning:** For window 10 and the supplied four calls, return [1,2,2,2]. At time10 the A event at0 expires, but the new A at10 restores its membership; B at5 remains. At15, B at5 expires and C enters while A at10 remains. With `(4,A),(4,A),(5,B)` and window1, return [1,1,1]; both A events leave together at5. A set alone cannot tell whether expiring one of several A events should remove A.

**Hints:** H1: “Can the same user have both an old and a still-valid event?” H2: “What would let you remove expired events in chronological order while retaining per-user multiplicity?” H3: suggest a FIFO of events and a frequency map; let the candidate supply the expiration comparison. Do not label a clarification as a hint.

**Branches:** If finished, ask why a monotonic input contract is useful. If stuck, reduce to two users and three events, then continue to the coding stage. Strong evidence is a self-created boundary counterexample; a critical mistake is using a closed left boundary or deleting a user while another active event exists.

### SDE-T2 — Implement and verify

**Checkpoint, minute 25:** Request a runnable base path and one worked expiration. **Minute 40:** ask for tests/invariant even if implementation is incomplete. Each retained event should be in the current open-left window; the map records exactly its multiplicity. Because timestamps are nondecreasing, expired events form a prefix. Remove that prefix, decrementing counts and removing zero-count keys, then append the accepted event. Validate all record arguments before changing state. For nonnegative `timestamp` and positive long `window`, `timestamp-window` lies between `-Long.MAX_VALUE` and `Long.MAX_VALUE-1`, so this subtraction cannot overflow; unrestricted signed times would need another contract.

<!-- phase6-code: RecentUsers.java -->
```java
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public final class RecentUsers {
    private record Event(long timestamp, String user) {}
    private final long window;
    private final ArrayDeque<Event> events = new ArrayDeque<>();
    private final Map<String, Integer> frequencies = new HashMap<>();
    private long lastTimestamp = -1;

    public RecentUsers(long window) {
        if (window <= 0) throw new IllegalArgumentException("positive window required");
        this.window = window;
    }

    public int record(long timestamp, String user) {
        if (timestamp < 0 || timestamp < lastTimestamp || user == null || user.isEmpty()) {
            throw new IllegalArgumentException("nondecreasing nonnegative time and nonempty user required");
        }
        long cutoff = timestamp - window;
        while (!events.isEmpty() && events.peekFirst().timestamp() <= cutoff) {
            Event old = events.removeFirst();
            int remaining = frequencies.get(old.user()) - 1;
            if (remaining == 0) frequencies.remove(old.user());
            else frequencies.put(old.user(), remaining);
        }
        events.addLast(new Event(timestamp, user));
        frequencies.merge(user, 1, Integer::sum);
        lastTimestamp = timestamp;
        return frequencies.size();
    }
}
```

**Correctness and costs:** Induct on accepted calls. Before insertion, prefix removal deletes exactly the no-longer-valid events; each map decrement mirrors one removal. Insertion adds exactly one accepted event and one occurrence, leaving map keys equal to distinct active users. Invalid calls bypass the transition. Over n accepted calls, each event is enqueued and dequeued at most once: expected O(n) total work with appropriate hashing and fixed/bounded-cost user identifiers, amortized O(1) per call; one call may remove O(k) active events. Retained space is O(k+u) references/records for k active events and u active users, excluding caller string storage. If hashing/equality of variable-length identifiers costs L, include those costs; this is not a hard real-time bound. `ArrayDeque` operations and thread-safety limits are described in [Java17 docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayDeque.html).

**Alternative:** Scanning all retained events and rebuilding a set after every call is a valid simpler baseline if its O(n²) total worst-case work is stated. Keeping only each user's latest timestamp can also work for this distinct-user contract with a suitable expiration structure and stale-entry handling; do not reject it merely because it differs from this event-frequency implementation. The chosen implementation favors a transparent invariant.

**Hints:** H1: trace the exact cutoff. H2: connect one expired event to one frequency decrement. H3: show removal of a zero-frequency key, then require a repeat-user test. **Branches:** if fast, ask how memory could be capped under overload without silently breaking exactness. If slow, test a correct baseline rather than inventing an optimized solution. **Common mistakes:** O(1) worst-case-per-call claims; `timestamp+window` overflow; modifying state before validation; no test after an invalid call. **Strong indicators:** a passing boundary plus invalid-state test and a credible oracle comparison.

**Validation:** The exact block is extracted and compiled with `--release 17`; focused and seeded differential tests run on the installed JVM. It is single-threaded fixture evidence, not a distributed service or execution on a Java17 VM. See the [record](../validation/mocks-core/README.md).

### SDE-T3 — Turn the component into a service

**Checkpoint, minute 60:** Ask for one duplicate, one late event and one restart trace. The base contract depended on one ordered caller and volatile state. Three workers need shared/partitioned ownership, a stated ordering/time policy, replay/deduplication identity and recovery. A bounded first solution can route a product/tenant stream to one owner, persist accepted event identities with the state/replay boundary, and explicitly choose ingestion-time versus event-time windows. A single owner does not make a global cross-owner count automatic. Per-partition distinct counts cannot simply be summed when the same user spans partitions.

For event-time results, define allowed lateness and when a result is provisional/final, with a watermark policy justified by source assumptions rather than a magic clock. A durable log/checkpoint needs replay identity and compatible window state. A timeout does not prove a record was not accepted. Explain what the UI receives while catching up, and restrict tenant access. Exactness, bounded memory and arbitrary late arrivals cannot all be silently promised without a retention/finality contract. No SQL/schema or service implementation is tested here.

**Hints:** H1: “Which original precondition did the late event violate?” H2: distinguish input time from event time and owner from replica. H3: walk a crash after acceptance but before response. **Branches:** if finished, ask about a user present on two partitions; if stuck, keep one durable owner and explain its throughput/failover limit. **Common mistakes:** summing local distinct counts; assuming at-least-once delivery is deduplicated; treating a synchronized Java method as distributed coordination. **Strong indicators:** a precise scope for exactness and an honest provisional result.

### SDE-T4 — Explain evidence and finish

**Checkpoint, minute 72:** Reserve the last three minutes for the candidate summary and one unresolved question. Ask what was actually observed, how a failing input isolated cause and what changed. Accept a small truthful episode; do not require a production incident or heroic outcome from an early-career candidate. If the episode is hypothetical, assess reasoning without crediting personal operational history.

**Expected response:** Context → personal action → check/result → limitation → changed practice. A useful close states the open-left boundary, ordered-input invariant, amortized versus worst-call cost and the fact that local tests do not establish distributed exactness. H1 can ask, “What is the strongest artifact for that claim?” A stronger followup changes an assumption (late event or capped memory); a supportive branch asks the candidate to show one failing test they would add. **Common mistakes:** claiming unobserved throughput or an invented ownership story. **Strong indicators:** clear attribution, a corrected misconception and a specific next check.

## Task-specific scorecard

Use [the shared rubric](Interview_Evaluation_Rubric.md), version/date and score scale. Enter **Not assessed** when a dimension was not observed. Level 3 below requires independent evidence; 0–2 depends on errors and help, while 4 requires repeated strength across variations/attempts, not one polished answer. Keep critical defects explicit rather than averaging them away.

| Dimension | Observable evidence for level 3 | Critical blocker / next exercise |
|---|---|---|
| CONTRACT | SDE-T1 restates open-left/nondecreasing/failure-atomic contracts and resolves H0 questions | Wrong interval or changed invalid-call state; reattempt an equality fixture |
| REASONING | SDE-T2 derives retained-state invariant and amortized accounting; T3 identifies broken assumptions | Pattern name without an argument; explain one repeat-user expiration |
| CORRECTNESS | Runnable T2 meets stated contract and handles repeat users, equal timestamps and large times | Known failing boundary remains; minimize and fix that case |
| VALIDATION | T2 adds independent expected cases and a model/scan comparison rather than only the sample | Only happy path checked; build an invalid-call-between-valid-calls test |
| RISK | T3 scopes duplicate/late/restart behavior and avoids summing overlapping distinct counts | Promises unsupported global exactness; trace a two-partition overlap |
| COMMUNICATION | T4 separates observed result/personal contribution from inference; concise close | Invented history or no evidence for a claim; give a bounded artifact-backed account |

For the actual attempt, record `dimension; score/Not assessed; task and artifact; hint history; unresolved error; next exercise`. Do not prefill candidate scores or infer an offer. A bounded hypothetical reflection can demonstrate reasoning/communication, but cannot demonstrate a real past accomplishment or production ownership.

## Debrief and reattempt

Ask the candidate to identify one corrected belief, one still-open limitation and a small next experiment. Choose a changed fixture or assumption for the reattempt; repeating the same memorized answer gives weaker transfer evidence. Use the [retrospective template](../14-Interview-Preparation-Templates/Interview_Retrospective_Template.md) and [practice ledger](../PROGRESS_TRACKER.md).

## Sources and verification boundary

[Primary source register](PHASE6_CORE_SOURCES.md) scopes mechanisms separately from these fictional scenarios and original scoring anchors. [Executable fixture record](../validation/mocks-core/README.md) identifies exact extracted code and observed tests. Conceptual discussion, interview behavior, deployment and model outcomes are not established by those tests.

[Candidate packet](SDE_Mock_Interview.md) · [Mock directory](README.md). Last reviewed: **2026-11-03**.
