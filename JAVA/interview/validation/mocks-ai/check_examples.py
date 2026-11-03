"""Execute the exact published AI helper and synthetic three-mock fixtures.

No provider, server, browser, database or authorization implementation is used.
Run from any directory with Python3.12; standard library only.
"""
from copy import deepcopy
from fractions import Fraction
from pathlib import Path
import random
import re
import sys

sys.dont_write_bytecode = True
ROOT = Path(__file__).resolve().parents[2]
MOCKS = ROOT / '13-Mock-Interviews'
checks = 0


def check(condition, label):
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1


def reject(fn, *args):
    try:
        fn(*args)
    except ValueError:
        check(True, 'invalid input rejected')
    else:
        raise AssertionError('invalid input accepted')


text = (MOCKS / 'AI_Engineer_Mock_Interview_Interviewer.md').read_text()
blocks = re.findall(r'<!-- phase6-code: validate_incident.py -->\s*```python\n(.*?)\n```', text, re.S)
check(len(blocks) == 1, 'one exact marked published helper')
scope = {}
exec(compile(blocks[0], 'published-validate-incident', 'exec'), scope)
validate = scope['validate_incident']
proposal = {'ticket_id': 'INC-7', 'severity': 'high', 'evidence_start': 0, 'evidence_end': 15}
original = deepcopy(proposal)
result = validate('API unavailable. Backup healthy.', 'INC-7', proposal)
check(result['evidence'] == 'API unavailable', 'worked span excludes period')
check(proposal == original and result is not proposal, 'fresh output and input unchanged')
result['severity'] = 'low'
check(proposal == original, 'output mutation does not change input')

for source in ['', 'a', 'abc', '🔥 outage', 'e\u0301']:
    # Independent accepted interval oracle: enumerate explicit position lists.
    valid_spans = {(start, end): ''.join(list(source)[start:end])
                   for start in range(len(source)) for end in range(start+1, len(source)+1)}
    for start in range(-2, len(source)+3):
        for end in range(-2, len(source)+3):
            item = {'ticket_id': 'T', 'severity': 'medium', 'evidence_start': start, 'evidence_end': end}
            before = deepcopy(item)
            if (start, end) in valid_spans:
                output = validate(source, 'T', item)
                check(output['evidence'] == valid_spans[start, end], 'interval oracle')
                check(output['evidence_start'] == start and output['evidence_end'] == end,
                      'offsets preserved')
            else:
                reject(validate, source, 'T', item)
            check(item == before, 'no mutation on success/failure')

valid = {'ticket_id': 'T', 'severity': 'low', 'evidence_start': 0, 'evidence_end': 1}
for key in list(valid):
    item = dict(valid)
    del item[key]
    reject(validate, 'a', 'T', item)
item = {**valid, 'evidence': 'invented'}
reject(validate, 'a', 'T', item)
for payload in [None, [], (), 'raw JSON', 1, True]:
    reject(validate, 'a', 'T', payload)
for field, values in [
    ('ticket_id', [None, False, 1, 'other']),
    ('severity', [None, False, 1, [], {}, 'HIGH', '', 'urgent']),
    ('evidence_start', [True, False, 0.0, None, '0', 10**1000, -(10**1000)]),
    ('evidence_end', [True, False, 1.0, None, '1', 10**1000, -(10**1000)]),
]:
    for value in values:
        reject(validate, 'a', 'T', {**valid, field: value})
for source in [None, False, b'a', ['a']]:
    reject(validate, source, 'T', valid)
for identity in [None, False, 1, '']:
    reject(validate, 'a', identity, valid)

class StrSubclass(str):
    pass

class DictSubclass(dict):
    pass

reject(validate, StrSubclass('a'), 'T', valid)
reject(validate, 'a', StrSubclass('T'), valid)
reject(validate, 'a', 'T', DictSubclass(valid))
for field in ['ticket_id', 'severity']:
    reject(validate, 'a', 'T', {**valid, field: StrSubclass(valid[field])})
# Mechanical acceptance is intentionally not semantic severity validation.
check(validate('All systems healthy.', 'T', {**valid, 'severity': 'high'})['severity'] == 'high',
      'valid enum/span does not establish severity truth')

rng = random.Random(6032026)
for _ in range(500):
    source = ''.join(rng.choice('ab🔥é') for _ in range(rng.randrange(1, 40)))
    start = rng.randrange(len(source))
    end = rng.randrange(start+1, len(source)+1)
    item = {**valid, 'severity': rng.choice(['low','medium','high']),
            'evidence_start': start, 'evidence_end': end}
    output = validate(source, 'T', item)
    check(output['evidence'] == ''.join(c for i,c in enumerate(source) if start <= i < end),
          'seeded span exactness')
    check(set(output) == set(valid) | {'evidence'}, 'no extra output fields')

# Parse candidate tables rather than copying their fixture values into the test.
ai = (MOCKS / 'AI_Engineer_Mock_Interview.md').read_text()
rows = re.findall(r'^\| [1-6] \| (H|M|L|U) \| (H|M|L|abstain) \| (H|M|L|abstain) \|$', ai, re.M)
check(len(rows) == 6, 'six paired evaluation rows')
answerable = [row for row in rows if row[0] != 'U']
for system, expected in [(1, (Fraction(4,5), Fraction(3,4), 1)),
                         (2, (Fraction(1), Fraction(4,5), 0))]:
    answered = [row for row in answerable if row[system] != 'abstain']
    coverage = Fraction(len(answered),len(answerable))
    precision = Fraction(sum(row[0] == row[system] for row in answered),len(answered))
    unsupported_assertions = sum(row[system] != 'abstain' for row in rows if row[0] == 'U')
    check((coverage, precision, unsupported_assertions) == expected, 'paired metric worked values')

rag = (MOCKS / 'RAG_Engineer_Mock_Interview.md').read_text()
rag_rows = re.findall(r'^\| q([1-4]): [^|]+ \| (A|B) \| ([^|]+?) \| ([^|]+?) \| ([^|]+?) \|$', rag, re.M)
check(len(rag_rows) == 4, 'four RAG fixture rows')
visibility = {'A1': {'A'}, 'A2': {'A'}, 'B1': {'B'}, 'P': {'A','B'}}
recalls = []
unauthorized_entries = 0
exposed_queries = 0
entry_count = 0
for _, tenant, gold, raw, citation in rag_rows:
    candidates = [item.strip() for item in raw.split(',')]
    entry_count += len(candidates)
    violations = sum(tenant not in visibility[item] for item in candidates)
    unauthorized_entries += violations
    exposed_queries += bool(violations)
    if gold.startswith('None'):
        continue
    recalls.append(Fraction(gold in candidates))
check(Fraction(len(recalls),len(rag_rows)) == Fraction(3,4), 'answerability coverage')
check(sum(recalls)/len(recalls) == Fraction(2,3), 'macro recall excludes empty gold')
check(Fraction(unauthorized_entries,entry_count) == Fraction(1,8), 'raw-entry exposure denominator')
check(Fraction(exposed_queries,len(rag_rows)) == Fraction(1,4), 'query exposure denominator')
check('A2' not in rag_rows[0][3].split(', '), 'reranking cannot recover absent gold')

fs = (MOCKS / 'Full_Stack_AI_Mock_Interview.md').read_text()
check('200 jobs/hour' in fs and '12 seconds' in fs and 'eight available' in fs,
      'capacity inputs match the candidate fixture')
concurrency = Fraction(200,3600) * 12
check(concurrency == Fraction(2,3), 'mean offered generation concurrency')
check(concurrency/8 == Fraction(1,12), 'nominal utilization')
# This is a synthetic fold of the given immutable events, not a published browser reducer.
event_rows = re.findall(r'^\| ([1-6]) \| (queued|running|draft_delta|validating|failed) \| ([^|]+) \|$', fs, re.M)
check(len(event_rows) == 6, 'six immutable server events')
by_sequence = {int(seq):(kind,body.strip()) for seq,kind,body in event_rows}
seen = set()
text_parts = []
state = None
for seq in [1,2,3,3,4,5,6]:
    if seq in seen:
        continue
    seen.add(seq)
    kind, body = by_sequence[seq]
    if kind == 'draft_delta':
        text_parts.append(re.search(r'`([^`]+)`',body)[1])
    else:
        state = kind
check(''.join(text_parts) == 'Revenue rose 8%.', 'duplicate3 not appended twice')
check(state == 'failed' and max(seen) == 6, 'terminal failed6 preserved')
print(f'PASS: {checks} checks; exact published helper plus synthetic AI/RAG/FS fixtures. No live service proof.')
