"""Check local knowledge-base integrity with Python's standard library.

Run: python3 interview/validation/check_documents.py
No web access, code execution, or Mermaid rendering is performed by this script.
"""
from pathlib import Path
from collections import defaultdict
from urllib.parse import unquote, urlparse
import hashlib
import ast
import re
import sys

ROOT = Path(__file__).resolve().parents[1]
errors = []
files = sorted(ROOT.rglob('*.md'))
directories = [ROOT] + sorted(p for p in ROOT.rglob('*') if p.is_dir())
links = 0
mermaid = 0
python_snippets = 0
hashes = defaultdict(list)
for directory in directories:
    if not (directory / 'README.md').is_file():
        errors.append(f'Missing README: {directory.relative_to(ROOT)}')
for path in files:
    text = path.read_text(encoding='utf-8')
    for number, snippet in enumerate(re.findall(r'^```python\s*\n(.*?)^```\s*$', text, re.M | re.S), 1):
        python_snippets += 1
        try:
            ast.parse(snippet, filename=f'{path}:python-block-{number}')
        except SyntaxError as error:
            errors.append(f'Python snippet syntax error: {path.relative_to(ROOT)}: {error}')
    relative = path.relative_to(ROOT)
    if not text.strip():
        errors.append(f'Empty file: {relative}')
    hashes[hashlib.sha256(text.encode()).hexdigest()].append(str(relative))
    # Ignore code contents when checking headings and HTML disclosure tags.
    in_fence = False
    prose = []
    for line in text.splitlines():
        if line.startswith('```'):
            if not in_fence and line.strip() == '```mermaid':
                mermaid += 1
            in_fence = not in_fence
        elif not in_fence:
            prose.append(line)
    if in_fence:
        errors.append(f'Unclosed code fence: {relative}')
    body = '\n'.join(prose)
    if len(re.findall(r'^# ', body, re.M)) != 1:
        errors.append(f'Expected one top-level title: {relative}')
    if len(re.findall(r'<details(?:\s[^>]*)?>', body)) != len(re.findall(r'</details>', body)):
        errors.append(f'Unbalanced details tags: {relative}')
    for target in re.findall(r'\[[^\]\n]+\]\(([^)\n]+)\)', text):
        target = target.strip('<>')
        if urlparse(target).scheme or target.startswith('#'):
            continue
        destination = unquote(target.split('#', 1)[0])
        links += 1
        if not (path.parent / destination).exists():
            errors.append(f'Broken local link: {relative} -> {target}')
    planned = re.search(r'## Planned documents\n(.*?)(?=\n## |\Z)', body, re.S)
    if planned:
        for name in re.findall(r'^- `([^`]+\.md)`', planned.group(1), re.M):
            if (path.parent / name).exists():
                errors.append(f'Already authored but listed planned: {relative} -> {name}')
for group in hashes.values():
    if len(group) > 1:
        errors.append('Identical documents: ' + ', '.join(group))
manifest = (ROOT / 'STRUCTURE_MANIFEST.md').read_text()
requested_rows = re.findall(r'^\| (.+?) \| (.+?) \|$', manifest, re.M)
requested_rows = [(p, status) for p, status in requested_rows if '.md' in p]
if len(requested_rows) != 410:
    errors.append(f'Expected 410 requested manifest paths, found {len(requested_rows)}')
for cell, status in requested_rows:
    match = re.search(r'\]\(([^)]+)\)', cell) or re.search(r'`([^`]+)`', cell)
    if not match:
        errors.append(f'Unrecognized manifest cell: {cell}')
        continue
    exists = (ROOT / match.group(1)).is_file()
    if ('Planned' in status) == exists:
        errors.append(f'Manifest status mismatch: {cell}')
if list(ROOT.rglob('*.class')) or list(ROOT.rglob('*.pyc')):
    errors.append('Compiled artifacts found inside interview/')
if errors:
    print('\n'.join(errors))
    sys.exit(1)
print(f'PASS: {len(files)} Markdown files, {len(directories)} README-covered directories, '
      f'{links} local links, 410 requested paths, {python_snippets} Python blocks syntax-parsed, {mermaid} Mermaid blocks located; '
      'no structural/syntax errors. Python blocks were not executed by this checker; Mermaid was not rendered; external links/anchors were not checked.')
