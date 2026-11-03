# Interview mistakes and corrective practice

## Diagnose the first wrong decision

A weak result can come from different causes. Treat the category as a guide to the next exercise rather than a judgment about ability. These are [INFERRED] practice diagnostics.

| Failure | Why it fails | Corrective exercise |
|---|---|---|
| Coding before agreeing on output | Correct code may solve a different problem | Restate three ambiguous prompts without implementing |
| Pattern chosen from keywords | Preconditions may not hold | Find a counterexample before using the pattern |
| Complexity stated without model | Hashing, recursion or output costs disappear | Name variables and count repeated work |
| Only sample tested | Samples may miss boundary behavior | Derive tests from the contract and invariant |
| Optimizing before a baseline | Harder to debug both approach and implementation | Explain the simplest correct algorithm first |
| Components named without flow | A diagram hides consistency and failure gaps | Trace one request and one crash in order |
| Metrics invented for a project | Followups expose unsupported claims | Build a claim/evidence/limit ledger |
| Behavioral answer has only “we” | Personal contribution is unobservable | Separate personal decision from team result |
| Every failure blamed externally | No learning or accountability is visible | State your controllable decision and later change |
| Tool policy guessed | Practice habits may violate assessment rules | Record policy per stage before the session |

## Worked error-log entry

Observation: “I used a variable window for a sum problem with negative numbers.” First wrong decision: assumed sum changed monotonically as the window grew. Counterexample: adding a negative value can reduce the sum. Correction: re-derive an approach under the actual input domain. Next attempt: a fresh prefix-sum problem, then a nonnegative window problem to explain the contrast. Do not record only “practice more sliding window.”

## When time runs short

Prioritize a coherent correct subset and state the remaining work. If the code has a known defect, name it and the next test. If the design omits deletion or recovery, identify that boundary. A confident claim of completion does not repair an incomplete solution.

## Questions for a reviewer

Did I solve the agreed problem? Was any claimed property unsupported? Could you see why I chose the approach? What is the smallest exercise that would expose the weakness again? Focus feedback on observable decisions, not personality labels.

<details><summary>Readiness gate</summary>

After reviewing a mistake, solve a different task requiring the same underlying idea without the same prompt cues. Explain the corrected precondition and test it. Repeating the original code from memory alone is insufficient evidence of transfer.

</details>

## Checklist

- [ ] Error log names a decision and counterexample.
- [ ] Next exercise targets that cause.
- [ ] Revised claims match the evidence.

## References

- [Microsoft interview tips](https://careers.microsoft.com/v2/global/en/hiring-tips/interview-tips) — first-party guidance; role-specific variation.
- [Methodology](../RESEARCH_METHODOLOGY.md) — evidence labels and limitations.
- [Daily template](../14-Interview-Preparation-Templates/Daily_Preparation_Template.md).

## Last verified

2026-11-02: source scope and editorial guidance reviewed. Practice budgets and scoring are original recommendations, not employer policy.
