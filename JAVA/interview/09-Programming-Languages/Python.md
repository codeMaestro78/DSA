# Python: objects, collections and resource ownership

This chapter gives early-career interview foundations and original **[INFERRED]** examples. It is a focused lesson, not the whole language specification. Read the worked trace before attempting the exercise. No new language implementation or benchmark is claimed here.

## Concept and worked trace

Names refer to objects; assignment does not copy an object. Mutating a shared list changes the object visible through every alias, while rebinding one name changes only that binding. Equality and identity answer different questions. Use an immutable, hash-stable key in dictionaries; mutating fields used by a custom hash breaks lookup assumptions.

Suppose a and b refer to the list [1]. Appending 2 through b makes both names observe [1,2]. Assigning b to a new empty list leaves a observing [1,2]. A shallow outer copy still shares inner mutable lists; copying the outer container is not a recursive ownership transfer.

## What to explain and implement

Practice lists, dicts, sets, deque, generators, exceptions and context managers. List insertion/removal near the front moves elements; use deque for frequent queue endpoints. Dictionary lookup is expected constant-time under suitable hashing, not a universal worst-case bound. Generator iteration is lazy but retained references and downstream materialization still use memory. Resource cleanup should have an explicit context-manager/finally boundary; a garbage collector is not an external transaction manager.

## Failure boundaries and tradeoffs

A default list argument is created when the function definition executes, so calls can accidentally share state. Use a sentinel/default None and create a new list when needed. An async function still blocks its event loop if it performs blocking work on that thread; async syntax does not make CPU work parallel. Explain cancellation and cleanup separately.

## Practice — LANG-01

Two requests call a helper with one mutable default result list. The second response includes the first request’s values. Explain cause, repair and a test.

<details><summary>Expected reasoning and followups — attempt first</summary>

The calls shared the same default object. Allocate a list for each invocation when no list is supplied, and decide explicitly whether a caller-supplied list may be mutated. Call twice with different inputs and assert separate results; add a deliberate shared-list case if mutation is part of the API. Test nested elements separately if copy isolation is promised. No observed leak in this repository is implied.

Followups: change one ownership or concurrency assumption and trace a failing case; identify a check that would establish the correction and one property it would not prove. Explain input, output and auxiliary-space costs for the chosen operation rather than assigning one complexity to an entire language.

</details>

## Further practice and sources

[Primary reference](https://docs.python.org/3.12/tutorial/datastructures.html) supports the relevant language/runtime concepts. This chapter’s trace and recommendations are original synthesis; no runtime benchmark or employer language policy follows. [Scoped source register](PHASE7_SOURCES.md), [coding practice](../12-Interview-Question-Bank/SDE_Questions.md), [role comparison](../02-Role-Based-Preparation/Role_Comparison.md) and [parent directory](README.md).

Last reviewed: **2026-11-03**. Traces are reviewed reasoning; existing executable examples retain their separately documented validation.
