# DSA example validation

## What is included

[CoreAlgorithms.java](CoreAlgorithms.java) contains eight educational algorithms: maximum nonempty subarray, prefix-count subarray sum, sorted pair sum, longest distinct UTF-16-unit window, lower bound, in-place list reorder, unweighted directed BFS distances and minimum unlimited positive coins. The guide excerpts use those method bodies; no existing repository solution is changed.

[CoreAlgorithmsTest.java](CoreAlgorithmsTest.java) is a dependency-free harness. It uses explicit AssertionError checks rather than Java `assert`, so no `-ea` option is needed. The examples use Java 17-compatible syntax/APIs. The installed runtime during verification was Oracle JDK 26; `--release 17` verifies target compatibility at compile time, not execution on a Java 17 runtime.

## Run from the repository root

```bash
javac --release 17 -d /tmp/interview-dsa-classes interview/validation/dsa/CoreAlgorithms.java interview/validation/dsa/CoreAlgorithmsTest.java
java -cp /tmp/interview-dsa-classes CoreAlgorithmsTest
```

Compiled output stays in `/tmp`. Do not commit `.class` files to the knowledge base.

## Observed result

2026-11-02: **15,082 checks passed**, seed `20261102`:

- Boundary/invalid-input checks, all-negative maximum subarray, arithmetic overflow traps, duplicate prefixes, empty inputs and the `abba` window bug.
- 2,000 generated array/string trials. Maximum sum and prefix counts compare against quadratic enumerators; sorted pair validity/existence against all pairs; lower bound against a linear scan; distinct-window length against a start/end set baseline.
- 400 generated directed graphs compare BFS distances against unit-weight Bellman-Ford relaxation, including generated cycles/self-loops/disconnected regions.
- 400 small coin instances compare tabulation with exhaustive recursive choice.
- List sizes 0–60 verify exact node identity/order, absence of duplicate visits and null termination after reorder.

Separately, all eight Java-fenced guide examples compiled independently with `javac --release 17`, and an extraction check confirmed their method bodies exactly match the tested implementation.

These checks validate the included implementations over the observed cases; they do not prove every possible input, all guide exercises or production performance. Reasoned invariants are documented in the corresponding guides. Random sizes are deliberately small so independent brute-force oracles remain tractable.

## Contracts and limitations

Null sequences fail fast; null list head represents empty. Sorted-pair/lower-bound examples assume sorted input without checking it. List reorder assumes an acyclic list with exclusive mutation ownership. Text windows count UTF-16 units, not visible grapheme clusters. Coin DP scales with numeric amount and can exceed available memory even when the integer is valid. The examples are single-threaded and make no concurrency claim.

[DSA module](../../03-DSA-and-Coding/README.md).
