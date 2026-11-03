# Arithmetic, divisibility and numeric contracts

## Scope and recognition

First decide whether arithmetic is exact integer, modular or approximate floating-point. A correct formula can overflow its representation. For Java int operands, widen before multiplication/negation; Math.abs(Integer.MIN_VALUE) is still negative. A long cannot represent the positive magnitude of Long.MIN_VALUE; use an explicitly bounded domain or BigInteger.

Original **[INFERRED]**, company-neutral teaching and exercises for early-career preparation. Study the [problem-solving framework](../Problem_Solving_Framework.md) first. Assumptions here are explicit exercise contracts, not employer rules.

## Worked trace

Euclid on84 and30: 84 mod30=24,30 mod24=6,24 mod6=0, so gcd=6. For nonnegative values, gcd(a,b)=gcd(b,a mod b); the remainder is smaller than the positive divisor, ensuring termination. Define gcd(0,0)=0 for this guide.

## Why the mechanism works

A common divisor of a and b also divides a−qb, and conversely, so Euclid preserves the common divisors. For a prime test, a composite positive n has some factor at most sqrt(n); testing d<=n/d avoids overflowing d*d. Zero and one are not prime.

## Baselines, complexity and tradeoffs

Euclid takes O(1+log min(a,b)) remainder steps for positive inputs, O(1) iterative state; big-integer remainder has nonconstant bit cost. Trial division costs O(sqrt n) candidates, O(1) state; a sieve is preferable for many bounded primality queries, using O(N) storage. Repeated squaring uses O(log(e+1)) multiplications plus constant setup for nonnegative exponent e, but intermediate size/overflow still matters.

## Practice — attempt before opening review

### DSA-19-01 — Least common multiple without premature overflow

**Difficulty:** Intermediate. **Evaluates:** contract, mechanism, boundary reasoning and evidence. **Prompt:** For nonnegative int a,b return exact long lcm, defining lcm(0,b)=0. Explain a=b=2,000,000,000 and distinguish arbitrary long inputs.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Compute zero case first, then ((long)a/gcd(a,b))*b. The result2,000,000,000 fits; multiplying as int first does not. For nonnegative int inputs their product fits signed long, but arbitrary long products may not; checked multiplication or BigInteger is required. Followup: normalize signed int via Math.abs((long)a), not Math.abs(a).

</details>

### DSA-19-02 — Modular exponent

**Difficulty:** Advanced extension. **Evaluates:** transfer to a changed condition, complexity and failure analysis. **Prompt:** Compute 3^13 mod17 with a nonnegative exponent and positive modulus; explain why multiplication safety needs a separate contract.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Repeated squaring gives residues3,9,13,16 for powers1,2,4,8. 13=8+4+1, so16×13×3 mod17=12. Initialize result=1 mod m so modulus1 yields0. Fixed-width multiplication of residues can overflow for a large modulus; BigInteger.modPow is one exact API option, not O(1) arithmetic. Followup: a negative exponent requires an inverse with additional existence conditions.

</details>

## Verification and next attempt

The worked traces and answers were reviewed on **2026-11-03**. They are not executed implementations, concurrency schedules, native SQL outputs or accepted public-platform submissions. For a new implementation, compare against a simple oracle and test empty/boundary/invalid cases under the stated contract. Record actual execution and hints; a successful manual trace is not compilation.

[Java17 BigInteger numeric API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/math/BigInteger.html) supports the underlying mechanism, not the invented exercise or an employer-frequency claim. [Phase 7 source scope](../PHASE7_SOURCES.md), [public practice catalog](../PUBLIC_PROBLEM_CATALOG.md), [DSA index](../DSA_Question_Index.md), [parent directory](README.md).
