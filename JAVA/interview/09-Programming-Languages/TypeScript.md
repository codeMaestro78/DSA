# TypeScript: static narrowing and runtime trust

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

TypeScript checks a program’s types during development; annotations do not validate bytes received over a network. Treat external input as unknown, check the runtime contract, then narrow it. A type assertion changes what the checker believes and does not transform or verify the value.

A response is asserted to be a successful job but actually contains state=failed. Accessing an assumed result can fail at runtime. A discriminated union with distinct states makes internal branches explicit, but the network decoder must still verify the tag and required fields before constructing that union.

## What to explain and implement

Model queued/running/succeeded/failed states with a shared discriminant and state-specific fields. Keep an exhaustive branch for internal evolution, and handle unknown external versions explicitly. Enable strict checking where appropriate, understand optional versus absent/null fields, structural assignability and readonly limitations. A readonly array type prevents mutation through that reference at compile time; it does not recursively freeze runtime objects or remove mutable aliases.

## Failure boundaries and tradeoffs

Do not replace runtime permission checks with a typed tenant_id. Types describe representations, not whether a principal may access a resource. Avoid widening everything to any after a decoder fails. TypeScript and JavaScript host scheduling remain related: adding types does not alter event-loop or cancellation guarantees. Version/compiler options can change diagnostics; record them when demonstrating a compile-time result.

## Practice — LANG-04

The code casts unknown JSON to {status:"succeeded",url:string}. Propose a safe boundary and a failure-state design.

<details><summary>Expected reasoning and followups — attempt first</summary>

Validate that the input is a non-null object, check its allowed status and fields, and return an explicit parsed union or error. Check URL policy separately from string type and authorize download server-side. Exhaustively handle each accepted state in the UI; do not show success from a partial streaming message. Tests should include null, wrong tags, missing URL and unsupported schema versions.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://www.typescriptlang.org/docs/handbook/2/narrowing.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
