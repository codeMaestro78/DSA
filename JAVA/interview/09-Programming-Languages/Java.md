# Java: contracts before implementation

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Java passes argument values; for an object argument the value is a reference. Rebinding a parameter cannot rebind the caller’s variable, but mutating the referenced object can be visible to the caller. Define equality/hashCode consistently and avoid mutating key identity while it is stored in a hash table.

For a list parameter, append changes the caller-visible list. Assigning the local parameter to a new list does not replace the caller’s list. A final reference cannot be reassigned after initialization; it does not make the referenced collection immutable. An unmodifiable view can still reflect mutations through another alias.

## What to explain and implement

Use Java17 as this library’s example source/API baseline; newer runtime features need their own version declaration. Know primitive overflow, boxed null unboxing, generics invariance, equals versus reference identity, collection iteration and exception cleanup. HashMap is not a concurrency primitive. Keep numeric contracts explicit: an int multiplication can overflow before assignment to a long unless an operand is widened first.

## Failure boundaries and tradeoffs

Visibility is distinct from compound atomicity. volatile publication has memory-ordering semantics, but counter++ still combines read, arithmetic and write. Use a lock or suitable atomic operation for that invariant; several atomics do not automatically form one multi-field transaction. A successful compilation does not explore concurrent interleavings.

## Practice — LANG-02

A final Map stores mutable lists and a volatile int counts completed tasks. Which safety claims are justified?

<details><summary>Expected reasoning and followups — attempt first</summary>

final protects the field binding, not map/list mutation. The count’s reads/writes have volatile semantics but concurrent increments can lose updates. Choose ownership, a lock or an atomic counter as appropriate, and separately protect the collection invariant. Test a sequential reference case and specify controlled concurrent schedules; do not infer race freedom from one successful run.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://docs.oracle.com/javase/specs/jls/se17/html/jls-17.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
