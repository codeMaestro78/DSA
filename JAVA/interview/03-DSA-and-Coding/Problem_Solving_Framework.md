# Problem-solving framework

## 1. Write the contract

Describe the mathematical task, representation, valid input, required output, mutation and failure behavior. For “count target subarrays,” explicitly require nonempty contiguous subarrays; permit signed ints; return a count that may exceed int. A different contract may need a different algorithm.

## 2. Construct a correct baseline

Enumerate every candidate and test the definition directly. For subarrays, choose a start and extend the end while maintaining a running sum: O(n²), not O(n³), because each extension costs constant work. The baseline clarifies correctness and becomes a small-input test oracle.

## 3. Identify repeated information

Ask what earlier state would make a new endpoint easy to answer. With prefix sums P, sum `[i,j)` is P[j]−P[i]. Therefore the needed earlier state is the count of P[i]=P[j]−target, not the full earlier subarray contents. This algebra derives the map key instead of relying on a memorized label.

## 4. State the invariant

Before processing endpoint j, the frequency map contains exactly prefixes before that endpoint, starting with the empty prefix. Query first, then insert the new prefix. Inserting first would allow the current prefix to match itself for target zero, incorrectly counting an empty subarray.

## 5. Prove coverage, soundness and termination

Coverage: every nonempty subarray has one endpoint and one earlier prefix. Soundness: every counted prefix difference equals target and uses an earlier position. No double counting: each start/end pair is counted once. Termination: the scan advances once per element. These three checks transfer to pointer elimination, graph traversal and recursive search.

## 6. Analyze actual cost

Each element performs a bounded number of expected constant-time map operations: expected O(n) time, O(n) entries. Prefix arithmetic and count use long. Hashing assumptions matter. State output cost and check whether temporary copies or recursion change auxiliary space.

## 7. Try to disprove the algorithm

Use empty input, zeros, repeated prefixes, all negatives, no solution and extreme ints. Compare the optimized version against the baseline for many small fixed-seed random inputs. Differential agreement is useful evidence but cannot prove correctness for every possible input; the invariant supplies the general argument.

## 8. Change one constraint

If the task becomes “longest subarray” rather than “count,” a prefix map should store the earliest index, not a frequency. If values are constrained nonnegative, a window may become valid for some objectives. If updates are frequent, immutable prefixes may no longer suffice. State what breaks before proposing a replacement.

## Practical worksheet

- Input/output and exceptions:
- Small example and boundary case:
- Brute force and bottleneck:
- Information retained and why sufficient:
- Invariant/recurrence:
- Why every valid answer is covered exactly as required:
- Time/space with assumptions:
- Counterexample and test oracle:
- Constraint change and followup:

## References and last verified

[Hashing guide](02-Hashing/Guide.md), [complexity](Complexity_Analysis.md), [MIT notes](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-notes/), [validation suite](../validation/dsa/README.md).

Last verified: 2026-11-02; worked reasoning reviewed and the linked prefix-count implementation differentially tested.
