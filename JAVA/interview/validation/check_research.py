"""Check Phase 4 file and citation bookkeeping; no network or truth verification.

Run: python3 interview/validation/check_research.py
Accepted citations and explicitly documented rejected leads are counted separately.
"""
from pathlib import Path
from urllib.parse import urlsplit, urlunsplit
import re
import sys

ROOT = Path(__file__).resolve().parents[1]
COMPANIES = ROOT / '01-Company-Research'
errors = []


def normalize(url):
    parsed = urlsplit(url)
    path = parsed.path.rstrip('/')
    return urlunsplit((parsed.scheme, parsed.netloc, path, parsed.query, ''))


def external_links(text):
    return re.findall(r'\]\((https?://[^)\s]+)\)', text)


accepted = {}
source_ids = set()
for line in (ROOT / 'SOURCE_INDEX.md').read_text().splitlines():
    if not line.startswith('| '):
        continue
    cells = [cell.strip() for cell in line.strip().strip('|').split('|')]
    if not re.fullmatch(r'[A-Z0-9]+(?:-[A-Z0-9]+)*', cells[0]):
        continue
    urls = external_links(line)
    if not urls:
        continue
    if cells[0] in source_ids:
        errors.append('Duplicate accepted central ID: ' + cells[0])
    source_ids.add(cells[0])
    accepted[normalize(urls[0])] = cells[0]

registers = [COMPANIES / group / 'PHASE4_SOURCES.md' for group in
             ['Big-Tech', 'Frontier-AI-Labs', 'India', 'Startups']]
track_ids = set()
rejected = set()
for register in registers:
    if not register.is_file():
        errors.append('Missing register: ' + str(register.relative_to(ROOT)))
        continue
    text = register.read_text()
    registered_urls = set()
    for line in text.splitlines():
        if not line.startswith('| '):
            continue
        cells = [cell.strip() for cell in line.strip().strip('|').split('|')]
        urls = external_links(line)
        if not urls or cells[0] == 'ID':
            continue
        if cells[0] in track_ids:
            errors.append('Duplicate Phase 4 track ID: ' + cells[0])
        track_ids.add(cells[0])
        registered_urls.update(normalize(url) for url in urls)
        if '2026-11-02' not in line:
            errors.append('Missing Phase 4 access date: ' + cells[0])
        for url in urls:
            if normalize(url) not in accepted:
                errors.append('Track source missing centrally: ' + url)
    # Non-table URLs occur in the documented access-limited/rejected lead notes.
    note_urls = re.findall(r'https?://[^\s`)>]+', '\n'.join(
        line for line in text.splitlines() if not line.startswith('|')))
    rejected.update(normalize(url.rstrip('.,;')) for url in note_urls
                    if normalize(url.rstrip('.,;')) not in registered_urls)

paths = []
for line in (ROOT / 'STRUCTURE_MANIFEST.md').read_text().splitlines():
    if not line.startswith('| ') or '01-Company-Research/' not in line:
        continue
    match = re.search(r'\]\((01-Company-Research/[^)]+\.md)\)', line)
    if not match:
        match = re.search(r'`(01-Company-Research/[^`]+\.md)`', line)
    if match and not match.group(1).endswith('/README.md'):
        paths.append(ROOT / match.group(1))
paths.append(COMPANIES / 'Big-Tech' / 'Palantir.md')
accepted_citations = rejected_citations = 0
for path in sorted(set(paths)):
    if not path.is_file():
        errors.append('Missing Phase 4 requested research: ' + str(path.relative_to(ROOT)))
        continue
    text = path.read_text()
    if '2026-11-02' not in text:
        errors.append('Missing verification date: ' + str(path.relative_to(ROOT)))
    if not any(label in text for label in ['[OFFICIAL]', '[INFERRED]', '[UNVERIFIED]']):
        errors.append('Missing evidence labels: ' + str(path.relative_to(ROOT)))
    for url in external_links(text):
        normalized = normalize(url)
        if normalized in accepted:
            accepted_citations += 1
        elif normalized in rejected:
            rejected_citations += 1
        else:
            errors.append(f'Unindexed citation: {path.relative_to(ROOT)} -> {url}')
if errors:
    print('\n'.join(errors))
    sys.exit(1)
print(f'PASS: {len(set(paths))} requested/additional research files; '
      f'{len(track_ids)} Phase 4 source records; {len(source_ids)} central IDs; '
      f'{accepted_citations} accepted citation occurrences; '
      f'{rejected_citations} documented rejected-lead links. '
      'No URLs fetched, applicant events corroborated or claim truth proven.')
