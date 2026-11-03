"""Validate original question records and the generated metadata index.

Run with --write-index to refresh explicit anchors and the two derived indexes.
Default mode is read-only. No answer truth, external URL, or Markdown rendering
is established by this checker.
"""
from pathlib import Path
from collections import Counter
import json
import re
import sys

ROOT = Path(__file__).resolve().parents[1]
BANK = ROOT / '12-Interview-Question-Bank'
PATHS = sorted(BANK.glob('*_Questions.md')) + [ROOT / '11-Project-Deep-Dive/Project_Question_Bank.md']
FIELDS = ['Category', 'Difficulty', 'Target roles', 'Round', 'Experience', 'Company scope', 'Evaluates', 'Related IDs', 'Sources']
HEADING = re.compile(r'^## (QB-[A-Z]+-\d{3}) — (.+)$', re.M)
WRITE = sys.argv[1:] == ['--write-index']
if sys.argv[1:] and not WRITE:
    raise SystemExit('Usage: check_questions.py [--write-index]')
errors, records, prompts = [], [], []
for path in PATHS:
    text = path.read_text()
    if WRITE:
        text = re.sub(r'<a id="qb-[a-z]+-\d{3}"></a>\n\n', '', text)
        text = HEADING.sub(lambda m: f'<a id="{m[1].lower()}"></a>\n\n{m[0]}', text)
        path.write_text(text)
    matches = list(HEADING.finditer(text))
    for i, match in enumerate(matches):
        key, title = match.groups()
        end = matches[i + 1].start() if i + 1 < len(matches) else len(text)
        block = text[match.end():end]
        fields = dict(re.findall(r'^\| ([^|]+?) \| ([^|]+?) \|$', block, re.M))
        for field in FIELDS:
            if not fields.get(field, '').strip():
                errors.append(f'{key}: missing {field}')
        if fields.get('Difficulty') not in {'Beginner', 'Intermediate', 'Advanced', 'Expert'}:
            errors.append(f'{key}: invalid difficulty')
        if 'Company-neutral' not in fields.get('Company scope', '') or '[INFERRED]' not in fields.get('Company scope', ''):
            errors.append(f'{key}: company/original scope missing')
        if text[:match.start()].rstrip().splitlines()[-1] != f'<a id="{key.lower()}"></a>':
            errors.append(f'{key}: explicit anchor missing')
        prompt = re.search(r'### Prompt\s*\n(.*?)\n<details>', block, re.S)
        if not prompt or not prompt[1].strip():
            errors.append(f'{key}: missing attempt-first prompt')
        else:
            prompts.append((' '.join(prompt[1].split()), key))
        for label in ['Expected answer', 'Explanation', 'Followups', 'Common mistakes']:
            if f'**{label}:**' not in block:
                errors.append(f'{key}: missing {label}')
        if block.count('<details>') != 1 or block.count('</details>') != 1 or 'attempt first</summary>' not in block:
            errors.append(f'{key}: invalid answer disclosure')
        if not re.search(r'\[[^\]]+\]\([^)]+\)', fields.get('Sources', '')):
            errors.append(f'{key}: sources missing link')
        records.append({'id': key, 'title': title, 'path': path.relative_to(ROOT).as_posix(), 'anchor': key.lower(), **{field: fields.get(field, '') for field in FIELDS}})
ids = [item['id'] for item in records]
for key, count in Counter(ids).items():
    if count != 1:
        errors.append(f'Duplicate ID: {key}')
for prompt, count in Counter(p for p, _ in prompts).items():
    if count > 1:
        errors.append('Duplicate prompt: ' + ', '.join(key for p, key in prompts if p == prompt))
for item in records:
    for related in re.findall(r'QB-[A-Z]+-\d{3}', item['Related IDs']):
        if related not in ids:
            errors.append(f'{item["id"]}: unknown related ID {related}')
# Validate every explicit QB anchor link in the library, including study routes.
for path in ROOT.rglob('*.md'):
    for destination, fragment in re.findall(r'\]\(([^)#]*)#(qb-[a-z]+-\d{3})\)', path.read_text()):
        target = path.parent / destination if destination else path
        if not target.is_file() or f'<a id="{fragment}"></a>' not in target.read_text():
            errors.append(f'Broken question anchor: {path.relative_to(ROOT)} -> {destination}#{fragment}')
records.sort(key=lambda item: item['id'])
index_json = json.dumps(records, indent=2, ensure_ascii=False) + '\n'
counts = Counter(item['Difficulty'] for item in records)
intro = f'''# Practical question-bank index

**{len(records)} original company-neutral questions** across 13 banks, including project review. Difficulty is editorial **[INFERRED]**: ''' + ', '.join(f'{counts[d]} {d}' for d in ['Beginner', 'Intermediate', 'Advanced', 'Expert']) + '''. These counts exclude the 22 public problems and the 32 earlier core DSA exercises. A cited concept or employer guide does not establish that an employer asked an original question.

Start with the [study guide](../PHASE_5_STUDY_GUIDE.md). Use your editor's search over the metadata columns or the [machine-readable metadata](question_index.json); answers stay in their linked banks. No interactive filtering application is supplied. Expert items are optional specialist practice, not a universal early-career gate.

## All original questions

| ID / prompt | Category | Difficulty | Target roles | Suggested round | Experience |
|---|---|---|---|---|---|
'''
for item in records:
    rel = Path(item['path'])
    dest = rel.name if rel.parent.name == BANK.name else '../' + rel.as_posix()
    intro += f'| [{item["id"]} — {item["title"]}]({dest}#{item["anchor"]}) | {item["Category"]} | {item["Difficulty"]} | {item["Target roles"]} | {item["Round"]} | {item["Experience"]} |\n'
intro += '''
## Other practice and evidence

- [Public problem catalog](../03-DSA-and-Coding/PUBLIC_PROBLEM_CATALOG.md): 22 externally hosted statements, separately scoped platform difficulty.
- [Core DSA exercise index](../03-DSA-and-Coding/DSA_Question_Index.md): 32 original Phase 2 exercises.
- [Company preparation map](Company_Preparation_Map.md): conditional study routes, no original-question attribution.
- [Validation](../PHASE_5_QA_REPORT.md): executable examples versus reasoned answers.

## Rebuild and verification

Run `python3 interview/validation/check_questions.py --write-index` after changing metadata, then run without arguments to compare the derived indexes. This checks schema, IDs, explicit anchors and index parity; it does not assess technical truth or renderer behavior.

Last verified: **2026-11-02**.
'''
for path, expected in [(BANK / 'question_index.json', index_json), (BANK / 'Question_Bank_Index.md', intro)]:
    if WRITE and not errors:
        path.write_text(expected)
    elif not path.exists() or path.read_text() != expected:
        errors.append(f'Index drift: {path.name}; rebuild with --write-index')
if errors:
    raise SystemExit('\n'.join(errors))
print(f'PASS: {len(records)} original records, {len(PATHS)} banks, metadata/IDs/related IDs/disclosures/anchors/index parity; difficulty {dict(counts)}. No technical or rendered-output proof.')
