# C++: lifetime, ownership and invalidation

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Object lifetime and pointer validity are central correctness contracts. Automatic storage duration is not the same as dynamic allocation, and copying an owning raw pointer does not create independent ownership. Prefer RAII: an owning object acquires a resource and releases it when its lifetime ends, including stack unwinding.

A vector grows after code keeps a pointer to one element. Reallocation can invalidate that pointer; keeping a raw address is not a stable identity guarantee. A reference to a local object also becomes invalid after that object’s lifetime ends. Explain the exact invalidation rule of the selected container rather than saying every insertion always preserves or always invalidates all references.

## What to explain and implement

Practice value semantics, const correctness, references, move/copy behavior, iterator categories, templates and exception safety. unique_ptr expresses exclusive ownership; shared_ptr counts shared ownership but can leak cycles without an ownership break such as weak_ptr. Moving a value does not universally mean copying zero bytes or leaving every type empty; rely on the type’s contract.

## Failure boundaries and tradeoffs

Use wide numeric contracts and avoid signed-overflow assumptions. A data race can invoke undefined behavior; volatile is not a replacement for synchronization. Exception safety levels distinguish preserving invariants from rolling back every effect. Resource release cannot undo an already committed remote action. These are conceptual interview lessons; no C++ compiler run is claimed here.

## Practice — LANG-05

Two objects own shared_ptr references to each other. Why can leaving scope fail to reclaim them, and how would you redesign ownership?

<details><summary>Expected reasoning and followups — attempt first</summary>

The cycle keeps each reference count above zero even after external owners disappear. Make the ownership graph directional and express a nonowning relationship with weak_ptr or another justified lifetime mechanism. A weak reference must be checked/locked before use; it is not a promise the object still exists. Test destruction/lifetime in an isolated program before claiming the actual implementation is fixed.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://isocpp.github.io/CppCoreGuidelines/CppCoreGuidelines) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
