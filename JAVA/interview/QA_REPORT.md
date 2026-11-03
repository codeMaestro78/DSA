# Phase 1 quality assurance

Checked **2026-11-02** against the files delivered in this workspace.

## Automated local checks

| Check | Result |
|---|---|
| Markdown files | 71 |
| Directories / directories with README | 55 / 55 |
| Requested named Markdown paths accounted for | 410 / 410 |
| Requested paths present / planned | 36 / 374 |
| Local Markdown link destinations | 390 checked; 0 missing |
| Empty files | 0 |
| Byte-identical duplicate documents | 0 |
| Unbalanced fenced blocks | 0 |
| Documents with a missing or multiple top-level title | 0 |
| Unique source IDs in central registry | 34 |

The link check resolves relative file paths, including URL-encoded paths. It does not check web availability, fragment anchors, semantic correctness or Markdown rendering. Planned filenames are plain code text and are intentionally absent as files.

## Editorial review

- Reviewed three company briefs for evidence labels, source dates, role/location limits and uncertainty. Each has a source register; its accepted sources are consolidated in [the index](SOURCE_INDEX.md).
- Independently reopened Anthropic's AI-use guidance, Razorpay's assessment themes and NVIDIA's hiring guidance as selected checks. Preserved the observed scope limits rather than inventing a universal loop.
- Reviewed roadmap dependencies and corrected the evaluation-before-RAG sequencing issue.
- Checked the requested tree against the supplied prompt. Added READMEs where the tree omitted them, and recorded Palantir as a needed later dossier.
- Inspected the roadmap's Mermaid graph for direction and dependencies. No Mermaid renderer, Obsidian import or browser preview was run.
- No runnable code examples were added. No algorithm, API, production or benchmark validation is claimed.

## Remaining limitations

This validates Phase 1 navigation and inventory, not the finished curriculum. No systematic candidate-report sample, complete company dossiers, technical question banks or mocks exist yet. External sources were read during research; an exhaustive HTTP link sweep was not performed. Access failures remain explicit in the briefs and registry.

## References

[Delivery report](PHASE_1_REPORT.md), [manifest](STRUCTURE_MANIFEST.md), [methodology](RESEARCH_METHODOLOGY.md).
