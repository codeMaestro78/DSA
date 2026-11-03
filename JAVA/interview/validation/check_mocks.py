"""Check Phase 6 mock packet pairing, task IDs, timing and index parity.

Default is read-only. --write-index refreshes a derived JSON inventory; no code is executed or external URL fetched by this checker.
"""
from pathlib import Path
import json
import re
import sys

ROOT = Path(__file__).resolve().parents[1]
FOLDER = ROOT / '13-Mock-Interviews'
SPECS = [
    ('SDE', 'SDE_Mock_Interview.md', 'MOCK-SDE-01'),
    ('AI', 'AI_Engineer_Mock_Interview.md', 'MOCK-AI-01'),
    ('ML', 'ML_Engineer_Mock_Interview.md', 'MOCK-ML-01'),
    ('FS', 'Full_Stack_AI_Mock_Interview.md', 'MOCK-FS-01'),
    ('FDE', 'FDE_Mock_Interview.md', 'MOCK-FDE-01'),
    ('RAG', 'RAG_Engineer_Mock_Interview.md', 'MOCK-RAG-01'),
    ('SYS', 'System_Design_Mock_Interview.md', 'MOCK-SYS-01'),
    ('LAB', 'Frontier_Lab_Mock_Interview.md', 'MOCK-LAB-01'),
    ('START', 'Startup_Mock_Interview.md', 'MOCK-START-01'),
]
FIELDS = ['Mock ID', 'Target role', 'Level', 'Difficulty', 'Duration minutes',
          'Company scope', 'Tools', 'Tasks', 'Validation']
DIMENSIONS = ['CONTRACT', 'REASONING', 'CORRECTNESS', 'VALIDATION', 'RISK', 'COMMUNICATION']
WRITE = sys.argv[1:] == ['--write-index']
if sys.argv[1:] and not WRITE:
    raise SystemExit('Usage: check_mocks.py [--write-index]')
errors, records = [], []
for prefix, name, identifier in SPECS:
    candidate = FOLDER / name
    interviewer = candidate.with_name(candidate.stem + '_Interviewer.md')
    if not candidate.exists() or not interviewer.exists():
        errors.append(f'Missing packet pair: {name}')
        continue
    text, guide = candidate.read_text(), interviewer.read_text()
    fields = dict(re.findall(r'^\| ([^|]+?) \| ([^|]+?) \|$', text, re.M))
    for field in FIELDS:
        if not fields.get(field, '').strip():
            errors.append(f'{identifier}: missing metadata {field}')
    if fields.get('Mock ID') != identifier:
        errors.append(f'{identifier}: wrong Mock ID')
    if fields.get('Difficulty') not in {'Beginner', 'Intermediate', 'Advanced', 'Expert'}:
        errors.append(f'{identifier}: invalid difficulty')
    if 'company-neutral' not in fields.get('Company scope', '').lower() or '[INFERRED]' not in fields.get('Company scope', ''):
        errors.append(f'{identifier}: missing original/company-neutral scope')
    duration = fields.get('Duration minutes', '')
    if not duration.isdecimal() or int(duration) <= 0:
        errors.append(f'{identifier}: duration must be a positive integer')
    stages = re.findall(r'^\| ([1-4]) \| (\d+) \| ([^|]+) \|$', text, re.M)
    if [stage for stage, _, _ in stages] != ['1', '2', '3', '4']:
        errors.append(f'{identifier}: expected exactly four ordered numeric timeline rows')
    elif any(int(minutes) <= 0 for _, minutes, _ in stages):
        errors.append(f'{identifier}: nonpositive stage duration')
    elif duration.isdecimal() and sum(int(minutes) for _, minutes, _ in stages) != int(duration):
        errors.append(f'{identifier}: stage minutes do not sum to duration')
    expected_tasks = [f'{prefix}-T{i}' for i in range(1, 5)]
    task_pattern = rf'^### ({prefix}-T[1-4]) — .+$'
    if re.findall(task_pattern, text, re.M) != expected_tasks:
        errors.append(f'{identifier}: candidate task headings/order mismatch')
    if re.findall(task_pattern, guide, re.M) != expected_tasks:
        errors.append(f'{identifier}: interviewer task headings/order mismatch')
    if re.findall(rf'{prefix}-T[1-4]', fields.get('Tasks', '')) != expected_tasks:
        errors.append(f'{identifier}: metadata task list mismatch')
    for i, stage in enumerate(stages):
        if expected_tasks[i] not in stage[2]:
            errors.append(f'{identifier}: timeline does not identify stage task')
    if '<details>' in text or re.search(r'Expected solution|Expected answer|Reference implementation', text, re.I):
        errors.append(f'{identifier}: candidate packet contains an answer/disclosure marker')
    if interviewer.name not in text or candidate.name not in guide:
        errors.append(f'{identifier}: paired packet navigation missing')
    for dimension in DIMENSIONS:
        if not re.search(rf'^\| {dimension} \|', guide, re.M):
            errors.append(f'{identifier}: missing scoring row {dimension}')
    if 'Interview_Evaluation_Rubric.md' not in guide:
        errors.append(f'{identifier}: shared rubric link missing')
    for label in ['H0', 'H1', 'H2', 'H3']:
        if label not in guide:
            errors.append(f'{identifier}: {label} administration guidance missing')
    for phrase in ['common mistakes', 'strong', 'validation']:
        if phrase not in guide.lower():
            errors.append(f'{identifier}: expected interviewer guidance {phrase}')
    if not re.search(r'follow.?up|branches|branch', guide, re.I):
        errors.append(f'{identifier}: followup branches missing')
    if not re.search(r'alternative|acceptable|accept different', guide, re.I):
        errors.append(f'{identifier}: alternatives missing')
    records.append({'id': identifier, 'candidate': name, 'interviewer': interviewer.name,
                    **{field: fields.get(field, '') for field in FIELDS},
                    'stage_minutes': [int(item[1]) for item in stages]})
if len(records) != 9:
    errors.append(f'Expected 9 complete sessions, found {len(records)}')
index_text = json.dumps(records, indent=2, ensure_ascii=False) + '\n'
index_path = FOLDER / 'mock_index.json'
if WRITE and not errors:
    index_path.write_text(index_text)
elif not index_path.exists() or index_path.read_text() != index_text:
    errors.append('Derived mock_index.json drift; rebuild with --write-index')
# Exact file/fence/link checks are provided by check_documents.py. This checker
# tests explicit packet conventions rather than attempting to infer answer truth.
if errors:
    raise SystemExit('\n'.join(errors))
minutes = sum(int(item['Duration minutes']) for item in records)
print(f'PASS: 9 candidate/interviewer pairs, 36 mapped tasks, six scoring dimensions per guide, '
      f'{minutes} total practice minutes, timing/metadata/index parity. '
      'No answer truth, actual candidate performance, rendering or live system proved.')
