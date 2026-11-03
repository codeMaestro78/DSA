# Java interview preparation by observable behavior

## Purpose and prerequisites

Use a stable language/API baseline: these exercises target Java17-compatible source, without assuming every employer uses that runtime. Be fluent with arrays/strings, collections, generics, exceptions, equality and basic concurrency. Explain what a program does before naming JVM internals.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../12-Interview-Question-Bank/SDE_Questions.md) when a mechanism is unfamiliar.

## Working method

Java passes argument values; an object-reference value can be copied to a parameter. Rebinding the local parameter does not rebind the caller’s variable, while mutating the referred object may be visible through both references. String indexing uses UTF-16 code units, not arbitrary user-perceived characters.

## Concrete reasoning example

A caller holds list L=[1]. A method appends2 through its parameter, then assigns that parameter a new list[9]. The caller still holds L=[1,2]. Likewise, changing a key field after inserting into HashMap can make lookup fail because hash/equality assumptions no longer match insertion placement.

## Tradeoffs and failure boundaries

Use Integer.compare rather than subtracting int comparator keys; subtraction can overflow. HashMap gives no iteration-order guarantee and needs synchronization for concurrent structural access. Null/empty/error behavior is part of each method contract. Resource cleanup and suppressed exceptions matter when both work and close fail.

## Practice — attempt first

### SDE-P7-07-A — Avoid comparator overflow

**Difficulty:** Intermediate. **Prompt:** Compare Integer.MIN_VALUE against Integer.MAX_VALUE with a subtraction comparator. Explain the failure and repair.

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

The subtraction wraps and can reverse ordering. Use Integer.compare(a,b), then an explicit secondary comparator for ties. Followup: comparator consistency/transitivity affects sorted structures; merely returning nonzero for every pair is invalid.

</details>

### SDE-P7-07-B — Analyze shared mutation

**Difficulty:** Intermediate with optional stretch. **Prompt:** A method receives int[] a, sets a[0]=7, then a=new int[]{8}. What remains visible to caller?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

The original array’s first cell is7; rebinding the local reference does not replace the caller’s array variable. A zero-length input would throw on the first operation unless handled by contract. Followup: returning a new array is the explicit way to supply a replacement.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
