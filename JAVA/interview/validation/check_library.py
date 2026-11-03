"""Coverage, local fragments, placeholder and source-register checks.

A bounded static review supplements check_documents.py; it does not determine
technical truth or assert freshness of external URLs. Phase reports are historic.
"""
from pathlib import Path
from collections import Counter
from urllib.parse import unquote, urlparse
import json
import html
import re
import sys
import unicodedata

ROOT = Path(__file__).resolve().parents[1]
errors = []
files = sorted(ROOT.rglob('*.md'))

# This slug model covers repository Markdown headings plus explicit HTML anchors.
# A different renderer may use different punctuation/Unicode normalization.
def anchors(text):
    result = set()
    counts = Counter()
    fence = None
    fence_length = 0
    for line in text.splitlines():
        fence_match = re.match(r'^ {0,3}(`{3,}|~{3,})', line)
        if fence_match:
            marker = fence_match[1]
            if fence is None:
                fence, fence_length = marker[0], len(marker)
            elif marker[0] == fence and len(marker) >= fence_length and not line[fence_match.end():].strip():
                fence = None
            continue
        if fence is not None:
            continue
        result.update(re.findall(r'<(?:a|h[1-6])\s+[^>]*id=[\"\']([^\"\']+)[\"\']', line))
        match = re.match(r'^#{1,6}\s+(.+?)\s*#*$', line)
        if not match:
            continue
        title = re.sub(r'\[([^\]]+)\]\([^)]+\)', r'\1', match[1])
        title = re.sub(r'<[^>]*>', '', title)
        # Preserve underscores inside code/identifiers; remove paired emphasis
        # markers only. GitHub heading IDs retain identifier underscores.
        code = []
        def protect_code(found):
            code.append(found[1])
            return f'INLINECODETOKEN{len(code)-1}ENDTOKEN'
        title = re.sub(r'`([^`]+)`', protect_code, title)
        title = re.sub(r'(?<!\w)__([^_]+)__(?!\w)', r'\1', title)
        title = re.sub(r'(?<!\w)_([^_]+)_(?!\w)', r'\1', title).replace('*', '')
        for index, value in enumerate(code):
            title = title.replace(f'INLINECODETOKEN{index}ENDTOKEN', value)
        title = html.unescape(title).lower().strip()
        title = ''.join(c for c in title if c in '-_ ' or unicodedata.category(c)[0] in 'LNM')
        slug = title.replace(' ', '-')
        suffix = counts[slug]
        counts[slug] += 1
        result.add(slug + (f'-{suffix}' if suffix else ''))
    return result

contents = {path: path.read_text() for path in files}
fragments = 0
for path, text in contents.items():
    for target in re.findall(r'\[[^\]\n]+\]\(([^)\n]+)\)', text):
        target = target.strip('<>')
        if urlparse(target).scheme or '#' not in target:
            continue
        destination, fragment = target.split('#', 1)
        if not fragment:
            continue
        other = (path.parent / unquote(destination)).resolve() if destination else path.resolve()
        if other.suffix != '.md' or not other.exists():
            continue  # Missing destinations are checked by check_documents.py.
        fragments += 1
        if unquote(fragment) not in anchors(other.read_text()):
            errors.append(f'Local fragment: {path.relative_to(ROOT)} -> {target}')
    if path.name == 'README.md':
        for phrase in ['No completed teaching chapters', '**Phase 1 status:**', 'Next authoring task']:
            if phrase in text:
                errors.append(f'Stale authoring status: {path.relative_to(ROOT)}: {phrase}')
    if re.search(r'^\s*(TODO|TBD|FIXME|Content coming soon|Integration result will be recorded)\s*[:.]?\s*$', text, re.M | re.I):
        errors.append(f'Unfilled placeholder: {path.relative_to(ROOT)}')
manifest = (ROOT / 'STRUCTURE_MANIFEST.md').read_text()
rows = re.findall(r'^\| (.+?) \| (.+?) \|$', manifest, re.M)
requested = [(cell, status) for cell, status in rows if '.md' in cell]
original = json.loads((ROOT/'validation/requested_paths.json').read_text())
manifest_paths = []
for cell, _ in requested:
    match = re.search(r'\]\(([^)]+)\)', cell) or re.search(r'`([^`]+)`', cell)
    if match:
        manifest_paths.append(match[1])
if manifest_paths != original:
    errors.append('Manifest differs from preserved original requested inventory')
if len(requested) != 410 or any('Planned' in status for _, status in requested):
    errors.append('Original410 requested paths are not all delivered')
for area in ['16-Tries','18-Bit-Manipulation','19-Math-and-Number-Theory',
             '20-Advanced-Algorithms','21-Concurrency-and-Threading','22-SQL-and-Data-Structures']:
    folder = ROOT/'03-DSA-and-Coding'/area
    guides = [p for p in folder.glob('*.md') if p.name != 'README.md']
    if not guides:
        errors.append(f'Missing extension teaching guide: {area}')
new_registers = sorted(ROOT.rglob('PHASE7_SOURCES.md'))
source_records = 0
ids = []
for path in new_registers:
    for line in path.read_text().splitlines():
        match = re.match(r'^\| (P7-[A-Z]+-\d+) \|', line)
        if not match:
            continue
        source_records += 1
        ids.append(match[1])
        if '2026-11-03' not in line or not re.search(r'\]\(https://[^)]+\)', line):
            errors.append(f'Incomplete Phase7 source record: {match[1]}')
for key, count in Counter(ids).items():
    if count != 1:
        errors.append(f'Duplicate Phase7 source ID: {key}')
if errors:
    raise SystemExit('\n'.join(errors))
print(f'PASS:410 requested files delivered; six extension areas taught; {fragments} local '
      f'Markdown fragments; {len(new_registers)} Phase7 registers/{source_records} unique scoped records; '
      'no stale README authoring markers or standalone placeholders. '
      'No external freshness, technical truth or renderer equivalence proved.')
