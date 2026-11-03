# Phase 4 quality assurance

Checked **2026-11-02** against the delivered company research. Passing these checks is not a guarantee of error-free employer knowledge, current vacancies or interview outcomes.

## Structural verification

Run from the workspace root:

```bash
python3 interview/validation/check_documents.py
python3 interview/validation/check_research.py
```

The document checker passes across **348 Markdown files** and **62 README-covered directories**. All **410 original named paths** remain tracked: **267 delivered**, **143 planned**. It checks local file destinations, empty/identical documents, titles, fences/disclosures, planned-file status, existing Python-fence syntax and absence of compiled artifacts. The executable output supplies the final local-link count after report edits.

Existing three Python blocks are syntax-parsed and nine Mermaid blocks located. The checker executes neither snippets nor diagrams and does not validate external URLs, heading fragments, semantic correctness or Markdown/Obsidian rendering.

## Citation/source bookkeeping

The research checker passes for:

| Item | Result |
|---|---|
| Requested company/startup research files plus Palantir | 46 present |
| Accepted Phase 4 track records with access date | 93 |
| Unique accepted IDs in the central registry | 204 |
| Accepted citation occurrences in those research files | 227 |
| Access-limited/rejected-lead links documented separately | 6 |

The registers comprise 78 official/context/employer-provided records and 15 candidate-report source pages before central deduplication. The central index adds 67 entries and retains 26 reused aliases. Repeated URLs/accounts are not treated as independent corroboration. Rejected leads are not counted as accepted source evidence.

The checker verifies that a citation is indexed or explicitly recorded as a rejected lead. It does **not** fetch the URL, validate its content against a claim, verify candidate identity, prove an interview happened or determine that a tool policy still applies.

## Editorial and source review

- Company-specific statements retain nearby URLs, evidence labels and source scope; original exercises remain explicitly company-neutral.
- Distinguished responsibilities/qualifications from actual assessment stages, global from regional guidance, basic from preferred requirements, and published typical processes from guarantees.
- Preserved report date versus interview date and campus/experienced/role/location context. No private prompts or company-frequency estimates were imported.
- Reopened selected high-impact official pages and performed cross-reviews of qualification, historical-event, tool-permission and eligibility interpretations.
- Preserved rate limits, redirects, blank/JavaScript bodies, changed branding and inconsistent fields as evidence limits rather than filling gaps from snippets.
- Removed repetitive generic role tables from big-tech dossiers in favor of tailored discussion and canonical role links; polished source metadata and wording.
- Confirmed all named company/regional documents and startup guide paths exist, including Palantir as a useful addition rather than a replacement.

## Limits

The corpus is a bounded public-source research pass, not a complete company profile or all-team hiring audit. Headquarters/location inventories are incomplete where not sourced. Many role-specific processes remain unknown. Candidate-report pages are self-selected and uncorroborated; some describe older or incomplete processes. No representative survey, confidential rubric, offer probability or difficulty ranking is established.

No technical runtime examples changed, so prior Java/SQL/AI/ML results were not rerun or relabeled as new Phase 4 evidence. No application was submitted, employer contacted, live interview conducted or code remotely published. Existing Java work was preserved.

External availability was checked while reading accepted sources, not through an exhaustive later HTTP sweep. The remaining question banks, role mocks and final whole-library review belong to phases 5–7.

## References and last verified

[Delivery report](PHASE_4_REPORT.md), [source registry](SOURCE_INDEX.md), [manifest](STRUCTURE_MANIFEST.md), [methodology](RESEARCH_METHODOLOGY.md), [refresh protocol](01-Company-Research/RESEARCH_REFRESH_PROTOCOL.md).

2026-11-02: observed bookkeeping, structural review and source/evidence limits recorded.
