# OOP and design patterns with explicit contracts

## Purpose and prerequisites

Encapsulation protects valid state transitions; polymorphism substitutes behavior under a shared contract. Prefer a small interface at a real variation point rather than building a pattern hierarchy speculatively. Inheritance must preserve observable promises, not just method signatures.

This is original **[INFERRED]**, company-neutral preparation for approximately 0–2 years. Practice sequence and difficulty are editorial, not employer assessment rules. Read the linked [canonical material](../../09-Programming-Languages/OOP.md) when a mechanism is unfamiliar.

## Working method

Use immutable values for stable identities and an injected policy for a changing calculation. Strategy fits interchangeable calculations; an adapter isolates an external interface; a factory controls construction when callers should not choose invalid combinations. Each has cost in indirection and testing.

## Concrete reasoning example

A delivery-price policy accepts a nonnegative package weight and returns a nonnegative minor-unit price. A new policy that mutates the package or returns negative values breaks clients even though its return type matches. An adapter converting grams to kilograms must preserve units before delegating.

## Tradeoffs and failure boundaries

Equality/hashCode must agree: equal objects require equal hash codes. Mutable fields used in map-key equality may change lookup behavior after insertion. A record is shallowly immutable: a referenced mutable list can still change unless copied/protected. Do not infer deep immutability from final fields.

## Practice — attempt first

### SDE-P7-06-A — Choose composition

**Difficulty:** Intermediate. **Prompt:** A subclass overrides add() but inherited addAll() invokes add internally, double-counting calls in an instrumented set. How can composition help?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Wrap the underlying collection and define counting at the intended public operation boundary, instead of depending on superclass internal self-calls. State whether count means attempted items, successful insertions or API invocations. Followup: even a wrapper must decide duplicate semantics.

</details>

### SDE-P7-06-B — Preserve equality

**Difficulty:** Intermediate with optional stretch. **Prompt:** Two tenant-scoped user IDs share the same local user number. May equality compare only that number?

<details><summary>Expected reasoning and followup — reveal after attempting</summary>

Only if the domain defines global identity that way; otherwise include tenant identity in equals/hashCode. Collapsing distinct tenant keys can return another tenant’s value. Followup: hash collisions do not imply equality, but an incorrect equals contract does.

</details>

## Review and source boundary

Assess contract, derivation, correctness, boundary checks, relevant risk and communication using the [common rubric](../../13-Mock-Interviews/Interview_Evaluation_Rubric.md). Keep real experience truthful, hints visible and unobserved dimensions Not assessed. Review one unfamiliar variation rather than memorizing the example.

[Primary support](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html) informs the relevant language/API/protocol or public evaluation concept; it does not establish employer questions or validate these original scenarios. [Source register](PHASE7_SOURCES.md) records access/scope. The traces are reviewed reasoning, not compiled Java, live concurrency or deployed backend evidence. [SDE directory](README.md).

Last reviewed: **2026-11-03**.
