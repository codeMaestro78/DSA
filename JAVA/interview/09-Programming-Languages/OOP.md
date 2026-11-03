# Object-oriented design: preserve behavior and invariants

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Object-oriented design groups state and behavior behind a contract. Encapsulation matters when callers cannot bypass an invariant; adding getters/setters for every field can merely expose the same mutable representation. Substitution is about preserved expectations, not only matching method signatures.

A Reservation permits cancel only while active. A public state setter lets callers jump from cancelled to active without reacquiring resources, breaking allocation invariants. Instead expose a transition method with explicit preconditions, authoritative checks and a useful failure result. Persistence/retries still need a transaction boundary beyond the local object.

## What to explain and implement

Separate identity-bearing entities from immutable value objects, and compose small behaviors with explicit interfaces. Inheritance can be useful for a stable substitutable hierarchy; composition is often easier when behavior varies independently. A strategy boundary can isolate a pricing rule without making every small function a framework. Dependency inversion should expose what the caller needs, not mirror an entire vendor SDK.

## Failure boundaries and tradeoffs

A subtype that weakens authorization or throws on an operation promised by its parent violates behavioral expectations even if it compiles. Avoid calling overridable methods from construction when subclass invariants are not established. Design tests against the shared contract and important transitions, then test implementation-specific behavior separately.

## Practice — LANG-08

A subclass of a mutable list promises to reject all additions. Is it substitutable for callers expecting the original mutable interface?

<details><summary>Expected reasoning and followups — attempt first</summary>

Not under an interface contract that promises permitted additions. Use an explicitly read-only interface/view or a separate abstraction whose operations match its guarantees. State which alias can still mutate underlying data and whether callers need a snapshot instead. The goal is an honest contract, not merely fewer inherited methods.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
