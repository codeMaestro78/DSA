"""Check only supplied synthetic mock arithmetic; no architecture execution."""
from fractions import Fraction
import json
from pathlib import Path
import re

MOCKS = Path(__file__).resolve().parents[2] / '13-Mock-Interviews'
checks = 0

def check(condition, label):
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1

def fixture(name, marker):
    matches = re.findall(rf'<!-- phase6-fixture: {marker} -->\s*```json\n(.*?)\n```',
                         (MOCKS/name).read_text(), re.S)
    check(len(matches) == 1, marker + ' exact fixture')
    return json.loads(matches[0])

s = fixture('System_Design_Mock_Interview.md','SYS-capacity')
check(Fraction(s['bookings_per_day'],s['seconds_per_day']) == Fraction(25,108),'daily average rate')
check(s['peak_bookings_per_second']*s['slots_per_booking'] == 120,'peak slot claim inserts')
backlog = (s['peak_bookings_per_second']-s['notification_service_per_second'])*s['peak_seconds']
check(backlog == 6000,'burst backlog')
net = s['notification_service_per_second']-s['after_peak_arrivals_per_second']
check(net == 15,'net service')
check(Fraction(backlog,net) == 400,'drain seconds')
check(400 < 10*60,'modeled ten minute target')
f = fixture('FDE_Mock_Interview.md','FDE-routing')
rows = f['rows'];columns=f['columns']
check(columns == ['Billing','Technical','Review'],'fixed action meanings')
check([sum(rows[k]) for k in ['Billing','Technical','Other']] == [60,100,40],'reference populations')
total = sum(sum(row) for row in rows.values())
proposed = sum(sum(row[:2]) for row in rows.values())
correct = rows['Billing'][0]+rows['Technical'][1]
check(total == 200 and proposed == 150 and correct == 138,'coverage counts')
check(Fraction(proposed,total) == Fraction(3,4),'proposal coverage')
check(Fraction(correct,proposed) == Fraction(23,25),'selective correctness')
check(rows['Billing'][1] == 4,'costly error count')
check(Fraction(rows['Billing'][1],sum(rows['Billing'])) == Fraction(1,15),'costly-error denominator')
t = fixture('Startup_Mock_Interview.md','START-size')
size = t['maximum_rows']*t['assumed_bytes_per_row']
check(size == 20_000_000 and Fraction(size,1_000_000) == 20,'decimal raw MB')
check(size*t['simultaneous_exports'] == 200_000_000,'simultaneous raw bytes')
print(f'PASS: {checks} candidate-fixture arithmetic checks; no database, queue, customer or payment execution.')
