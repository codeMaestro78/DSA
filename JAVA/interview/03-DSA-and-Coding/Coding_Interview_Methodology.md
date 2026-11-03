# Coding interview methodology

## The output is a defensible solution

[INFERRED] A useful practice performance consists of understanding, approach selection, correctness, implementation, complexity, testing and communication. This chapter is a preparation method, not a universal employer rubric or format. Ask the recruiter which editor, language, execution tools and assistance policy apply.

## A 45-minute practice session

| Budget | Action | Observable result |
|---|---|---|
| 0–5 minutes | Restate input/output; ask material questions | A contract and two concrete examples |
| 5–12 | Give brute force, identify repeated work, propose optimization | An invariant/recurrence and complexity estimate |
| 12–30 | Implement in small coherent steps | Working code whose control flow matches the explanation |
| 30–40 | Trace tests, run if permitted, fix root causes | Evidence for typical, boundary and adversarial cases |
| 40–45 | Explain final bounds and followups | Honest result with assumptions and limitations |

This is an adjustable practice allocation. If a clarification or proof needs longer, communicate the tradeoff rather than hiding uncertainty. Actual interview durations differ.

## Clarifications that change the algorithm

Ask whether order must be preserved; values can be negative; duplicate values count separately; mutation is allowed; indices or values are returned; input is sorted; the result must be exact; empty/null is valid; string units mean bytes, UTF-16 units, code points or graphemes. Avoid asking questions already answered by the prompt.

Example: “Return a pair summing to target.” Clarify whether the pair means two different indices, whether original indices are required and whether any pair is acceptable. Those answers determine whether sorting is allowed and what metadata must survive it.

## Explain before typing

State a simple baseline and its bottleneck. Then describe the new state and invariant: “The map counts prefixes strictly before the current endpoint, so looking up currentPrefix−target counts exactly the nonempty subarrays ending here.” This is more useful than “I will use a hash map.” Trace a small case to catch an incorrect update order before coding.

When stuck, make the obstruction explicit: “I can enumerate all pairs, but I have not yet justified discarding one pointer.” Try a small counterexample or a stronger representation. Do not bluff an optimum or silently change the specification.

## Implement for inspection

Use names that reflect the invariant (`left`, `firstEligible`, `prefixCount`). Keep the boundary convention consistent. Save pointers before rewiring. Initialize state from the actual contract: a nonempty all-negative maximum subarray cannot start with best=0.

Use known library contracts. A priority queue is not sorted iteration; HashMap does not imply thread safety; Arrays.binarySearch is not a lower-bound function. Consider numeric promotion before addition and safe comparators.

## Test a claim, not only a sample

Use one smallest valid case, one normal case, one case exposing the algorithm’s main invariant, one no-solution case when allowed and one numeric/duplicate boundary. For lists, test identity, reachability and cycles, not only printed values. For graphs, include a cycle and an unreachable vertex. If execution is unavailable, give a precise manual trace and say it is manual reasoning.

After a bug, show the failing input and first wrong state. Correct the invariant or update, then rerun the case and at least one previously passing case. Avoid making unexplained random edits.

## Review

Record the first incorrect decision, hints used, correctness evidence and a new variation in [the progress tracker](../PROGRESS_TRACKER.md). Code accepted after reading the solution is a study result, not an independent solve.

## References and last verified

[Solving framework](Problem_Solving_Framework.md), [complexity](Complexity_Analysis.md), [primary source metadata](PHASE2_SOURCES.md). The teaching method is editorial [INFERRED]; no company-specific assessment claim is made.

Last verified: 2026-11-02; method and example contracts reviewed.
