"""Reconcile the immutable original requested-path inventory with local files.

No chapter is marked delivered unless it exists. Content quality/coverage is
reviewed separately; this script is file bookkeeping, not a semantic verifier.
"""
from pathlib import Path
import json

ROOT = Path(__file__).resolve().parents[1]
requested = json.loads((ROOT/'validation/requested_paths.json').read_text())
assert len(requested) == len(set(requested)) == 410
present = sum((ROOT/p).is_file() for p in requested)
md = set(ROOT.rglob('*.md'))
s = f'''# Structure manifest

The original request names **55 directories and410 Markdown paths**. All original directories exist. **{present}/410 requested files are authored; {410-present} remain unauthored.** Supporting source registers, extension guides, interviewer companions, reports and validation artifacts are additional. Existence means delivered stated teaching/research/template scope, not exhaustive employer knowledge, candidate readiness or production proof.

All seven delivery phases are recorded in [the completion report](PHASE_7_REPORT.md); [final QA](PHASE_7_QA_REPORT.md) distinguishes local execution, static review and unexecuted boundaries. Historical reports preserve their original snapshots. [Research gaps](RESEARCH_GAPS.md) retains external facts that cannot honestly be established by authoring another file.

## Requested files

| Requested path | Status |
|---|---|
'''
for rel in requested:
    path = ROOT/rel
    if path.is_file():
        status = 'Delivered — navigation and stated scope' if path.name=='README.md' else 'Delivered — authored stated scope; validation per document'
        s+=f'| [{rel}]({rel}) | {status} |\n'
    else:
        s+=f'| `{rel}` | Planned — not authored |\n'
s+='\n## Additional delivered Markdown files\n\n'
for path in sorted(md-{ROOT/p for p in requested}):
    rel=path.relative_to(ROOT).as_posix();s+=f'- [{rel}]({rel})\n'
s+='\n## Validation and metadata source files\n\n'
for path in sorted(ROOT.rglob('*')):
    if path.is_file() and path.suffix!='.md':
        rel=path.relative_to(ROOT).as_posix();s+=f'- [{rel}]({rel})\n'
s+='\n## Rebuild and verification\n\n`python3 interview/validation/rebuild_manifest.py` reads [the original410 paths](validation/requested_paths.json) and rebuilds this inventory. Run the document/library checkers afterward. It does not rewrite teaching content.\n\nLast reconciled: **2026-11-03 IST**.\n'
(ROOT/'STRUCTURE_MANIFEST.md').write_text(s)
print(f'Manifest: {present}/410 requested paths; {len(md)} total Markdown files')
