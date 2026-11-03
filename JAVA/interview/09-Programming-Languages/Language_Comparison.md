# Compare language choices by the task contract

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Choose a language by the actual assessment/runtime and your ability to implement, explain and test the required behavior. A language family does not determine performance, hiring suitability or safety of every application. Compare equivalent workloads, libraries, warmup and deployment constraints before making quantitative claims.

For Java DSA, Python numerical work and TypeScript product code, switching to an unfamiliar language solely for a perceived speed advantage can add syntax/runtime errors. Keep the repository’s Java core, use NumPy where its array contract fits, and explain that typed frontend state still requires server-side authorization and runtime decoding.

## What to explain and implement

Java offers JVM libraries and managed memory; Python concise dynamic code and numerical ecosystems; JS/TS host-driven web integration; C++ explicit lifetime/control with substantial proof obligations; Go lightweight concurrency with synchronization needs; Rust ownership-checked safe code with explicit borrowing. These are broad qualitative dimensions, not benchmark rankings or universal allocation strategies.

## Failure boundaries and tradeoffs

Match integer width/overflow, collection behavior, string indexing, error propagation, async/task cancellation and memory ownership across implementations. Python string indices and Java UTF-16 char indices can represent different units. A port must preserve the problem’s definition of character/identity rather than merely translate loops. Pin source/API versions and compile/run the chosen environment.

## Practice — LANG-13

An algorithm uses a fixed 26-entry array for text in Java, then is ported to Python for arbitrary user names. Is language substitution enough?

<details><summary>Expected reasoning and followups — attempt first</summary>

No. The alphabet/normalization/index-unit contract changed. Define allowed text and matching semantics, choose appropriate keys/storage, and revise complexity in alphabet/character variables. Test empty, repeated and non-ASCII cases relevant to that contract. Python’s flexible strings do not make the original 26-symbol invariant valid automatically.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://www.typescriptlang.org/docs/handbook/2/narrowing.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
