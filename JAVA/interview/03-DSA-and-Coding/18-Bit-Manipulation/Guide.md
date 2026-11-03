# Fixed-width bit reasoning

## Scope and recognition

Bits represent a fixed-width pattern separately from its signed numeric interpretation. Java int is 32 bits, long 64; `>>` copies the sign bit, `>>>` inserts zeroes. int shift distances use the low five bits, long the low six; shifting int by 32 therefore does not clear it. Use `1L` before shifts intended for long masks.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

For 12 (binary1100), `x & (x-1)` gives 8 (1000), then 0: two set bits. For int −1, every one of the 32 bits is set; repeated clearing terminates after 32 steps. The condition must be x!=0 rather than x>0.

## Why the mechanism works

Subtracting one flips the least-significant one to zero and lower zeroes to ones; AND retains the higher bits and clears that one. XOR pairs cancel because a^a=0 and a^0=a. These identities operate on bit patterns, not validation of the problem’s multiplicity assumptions.

## Baselines, complexity and tradeoffs

Bit clearing is O(b) for b set bits, O(1) state; fixed-width worst-case is O(32) for int. For variable-width integers, bit operations are not automatically unit cost. Mask enumeration has 2^n subsets; an int/long cannot represent arbitrarily many item flags.

## Practice — attempt before opening review

### DSA-18-01 — Count signed set bits

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** Explain a count for int −1 and Integer.MIN_VALUE without converting to a binary string.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Clear the lowest set bit until zero. Results32 and1. Java wraparound in x-1 is defined for int, so MIN_VALUE−1 wraps to MAX_VALUE and AND gives0. Followup: why would a loop guarded by x>0 fail? It skips all negative values.

</details>

### DSA-18-02 — Recover two unique values

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** Every other int occurs exactly twice; exactly two values occur once. For [2,5,2,7,9,5], recover those two values and state the promise needed.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Overall XOR is7^9=14. Isolate one differing bit using xor & -xor (here2), partition by that bit and XOR within partitions to obtain7 and9. O(n) time/O(1) fixed-width space. MIN_VALUE isolation remains valid as a bit pattern. Invalid multiplicities are not detected; followup: what if each repeated value occurs three times? This partition argument no longer follows.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[Java17 expression and shift specification](https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
