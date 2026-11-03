# GitHub repositories as inspectable learning artifacts

A repository can supply code, examples, tests, history and licenses. Stars and README claims do not prove correctness, maintenance quality or production readiness. Inspect the specific artifact/version you rely on and avoid running unreviewed setup scripts against credentials.

## Curated routes and limits

| Repository | Purpose | Evidence boundary |
|---|---|---|
| [pytorch/tutorials](https://github.com/pytorch/tutorials) | First-party training/tutorial source | Browse exact tutorial and supported versions; no tutorials installed/executed in this curation |
| [ossu/computer-science](https://github.com/ossu/computer-science) | Maintainer-owned learning roadmap | Community curriculum, not official hiring guidance or a required full program |
| [C++ Core Guidelines](https://github.com/isocpp/CppCoreGuidelines) | Guideline source/history | Living guidance; page rendering was inspected through its official site |


## Use the material actively

For a selected example record commit/tag, runtime, dependencies, input contract, license and tests actually run. Check whether a published benchmark uses the same workload and environment as your task. An abandoned example can still be educational with explicit limits; do not call it current based on today’s access date. Keep a local practice fork isolated from user solutions.

## Practice — RESOURCE-P7-05

A README reports high accuracy, but only a demo notebook is supplied. What can you claim?

<details><summary>Expected reasoning — attempt first</summary>

That the author reports a result under whatever scope is documented. Reproducibility needs dataset/split, preprocessing, model/configuration, code/dependencies and evaluation procedure. A runnable notebook alone does not establish the reported metric or deployment benefit. Retain the gap instead of copying the claim into your project story.

Followup: name one artifact you would retain and one unsupported claim you would refuse to make. Choose the smallest exercise that tests transfer instead of collecting another unrelated resource.

</details>

## Provenance

[Scoped register](PHASE7_SOURCES.md) records inspected pages and explicit access limits. Most original teaching and assessment claims are **[INFERRED]**; references establish their stated content, not your performance. [Research methodology](../RESEARCH_METHODOLOGY.md) · [Parent index](README.md).

Last reviewed: **2026-11-03**. No purchase, enrollment, platform submission or external account action was performed.
