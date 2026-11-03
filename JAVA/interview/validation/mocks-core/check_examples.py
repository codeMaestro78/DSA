"""Execute exact marked Phase6 core references; no live services or model training."""
from fractions import Fraction
import math
from pathlib import Path
import random
import re
import subprocess
import sys
import tempfile
import numpy as np

sys.dont_write_bytecode = True
HERE = Path(__file__).resolve().parent
BANK = HERE.parents[1] / '13-Mock-Interviews'
checks = 0

def check(condition, label):
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1

def rejects(function, *args):
    try:
        function(*args)
    except ValueError:
        check(True, 'invalid input rejected')
    else:
        raise AssertionError('invalid input accepted')

def extract(document, name, language):
    matches = re.findall(rf'<!-- phase6-code: {re.escape(name)} -->\s*```{language}\n(.*?)\n```',
                         (BANK / document).read_text(), re.S)
    if len(matches) != 1:
        raise AssertionError('expected one marked block: ' + name)
    return matches[0] + '\n'

metric_scope = {}
exec(compile(extract('ML_Engineer_Mock_Interview_Interviewer.md', 'customer_mae.py', 'python'),
             'published-customer-mae', 'exec'), metric_scope)
metric = metric_scope['customer_mae']
loss_scope = {}
exec(compile(extract('Frontier_Lab_Mock_Interview_Interviewer.md', 'masked_token_nll.py', 'python'),
             'published-masked-nll', 'exec'), loss_scope)
loss = loss_scope['masked_token_nll']

check(metric([0]*5,[0,0,0,4,6],['A']*4+['B']) == ({'A':1.,'B':6.},3.5), 'ML fixture macro')
check(sum([0,0,0,4,6])/5 == 2, 'ML fixture row mean')
check(math.isclose((90*.5+10*4)/100,.85) and (.5+4)/2 == 2.25, 'ML metric conflict')
check(metric([5],[5],['one']) == ({'one':0.},0.), 'single zero error')
check(metric([0,0],[1,1],['z','a']) == ({'a':1.,'z':1.},1.), 'equal errors')
check(list(metric([0,0],[1,1],['z','a'])[0]) == ['a','z'], 'stable sorted keys')
for args in [([],[],[]),([1],[1,2],['A']),([[1]],[[1]],['A']),([1],[1],[]),
             ([1],[1],['']),([1],[1],[None]),([math.nan],[1],['A']),([1],[math.inf],['A']),
             ([1e6+1],[0],['A']),([0],[-1e6-1],['A'])]:
    rejects(metric,*args)
for args in [([1+2j],[1],['A']), ([1],[1+2j],['A']), ([1],[1],None)]:
    rejects(metric,*args)
rng = random.Random(6032026)
for trial in range(400):
    n = rng.randrange(1,30)
    actual = [rng.randrange(-100,101) for _ in range(n)]
    predicted = [rng.randrange(-100,101) for _ in range(n)]
    names = [rng.choice(['A','B','C','D']) for _ in range(n)]
    original = (actual.copy(),predicted.copy(),names.copy())
    expected = {}
    for name in sorted(set(names)):
        errors = [abs(Fraction(a)-Fraction(p)) for a,p,c in zip(actual,predicted,names) if c == name]
        expected[name] = sum(errors)/len(errors)
    expected_macro = sum(expected.values())/len(expected)
    per_customer, macro = metric(actual,predicted,names)
    check(all(math.isclose(per_customer[k],float(v),rel_tol=1e-14,abs_tol=1e-14) for k,v in expected.items()) and set(per_customer)==set(expected), 'independent Fraction per-customer oracle')
    check(math.isclose(macro,float(expected_macro),rel_tol=1e-14,abs_tol=1e-14), 'independent Fraction macro oracle')
    check((actual,predicted,names)==original,'metric input mutation')
    order = list(range(n)); rng.shuffle(order)
    shuffled = metric([actual[i] for i in order],[predicted[i] for i in order],[names[i] for i in order])
    check(shuffled[0]==per_customer and shuffled[1]==macro,'metric permutation invariance')

zero = np.zeros((2,2,2))
target = np.array([[0,-1],[1,0]])
mask = np.array([[True,False],[True,True]])
check(math.isclose(loss(zero,target,mask),math.log(2),rel_tol=1e-14),'LAB zero logits')
check(loss([[[1000.,-1000.]]],[[1]],[[True]]) == 2000., 'LAB stable low-probability target')
check(loss([[[1e6,-1e6]]],[[1]],[[True]]) == 2e6, 'LAB numeric bound')
check((1+3)/2==2 and (1+3+3+3)/4==2.5,'LAB sequence/token means')
for args in [(np.zeros((1,1,2)),[[0]],[[False]]),
             (np.zeros((1,1,1)),[[0]],[[True]]),
             (np.zeros((1,1,2)),[[0.]],[[True]]),
             (np.zeros((1,1,2)),[[True]],[[True]]),
             (np.zeros((1,1,2)),[[2]],[[True]]),
             (np.zeros((1,1,2)),[[-1]],[[True]]),
             (np.zeros((1,1,2)),[[0]],[[1]]),
             (np.zeros((1,1,2)),[[0]],[True]),
             (np.zeros((1,1,2)),[0],[[True]]),
             (np.zeros((0,1,2)),np.zeros((0,1),dtype=int),np.zeros((0,1),dtype=bool)),
             ([[[math.nan,0]]],[[0]],[[True]]),
             ([[[0,math.inf]]],[[0]],[[True]]),
             ([[[1e6+1,0]]],[[0]],[[True]])]:
    rejects(loss,*args)
for args in [([[[1+2j,0]]],[[0]],[[True]]), ([[["1","0"]]],[[0]],[[True]]), ([[[1,0],[1]]],[[0]],[[True]])]:
    rejects(loss,*args)
num_rng = np.random.default_rng(6032026)
for trial in range(100):
    B,T,V = int(num_rng.integers(1,4)),int(num_rng.integers(1,4)),int(num_rng.integers(2,6))
    z = num_rng.normal(size=(B,T,V))
    y = num_rng.integers(0,V,size=(B,T))
    keep = num_rng.random((B,T)) > .4
    keep[0,0] = True
    y[~keep] = -1
    before = (z.copy(),y.copy(),keep.copy())
    scalar = []
    for b in range(B):
        for t in range(T):
            if keep[b,t]:
                maximum = max(float(v) for v in z[b,t])
                scalar.append(math.log(math.fsum(math.exp(float(v)-maximum) for v in z[b,t]))-(float(z[b,t,y[b,t]])-maximum))
    expected = math.fsum(scalar)/len(scalar)
    observed = loss(z,y,keep)
    check(math.isclose(observed,expected,rel_tol=1e-13,abs_tol=1e-13),'independent scalar stable-loss oracle')
    check(math.isclose(loss(z+1000,y,keep),observed,rel_tol=1e-12,abs_tol=1e-12),'per-row common shift invariance')
    changed = y.copy(); changed[~keep] = -999
    check(loss(z,changed,keep)==observed,'masked targets ignored')
    changed_logits = z.copy(); changed_logits[~keep] = 9999
    check(loss(changed_logits,y,keep)==observed,'masked logits ignored after finite validation')
    check(all(np.array_equal(a,b) for a,b in zip(before,(z,y,keep))),'loss inputs not mutated')
    active_count = int(keep.sum())
    for b in range(B):
        for t in range(T):
            softmax = np.exp(z[b,t]-z[b,t].max()); softmax /= softmax.sum()
            for v in range(V):
                plus,minus=z.copy(),z.copy(); plus[b,t,v]+=1e-5; minus[b,t,v]-=1e-5
                numerical=(loss(plus,y,keep)-loss(minus,y,keep))/(2e-5)
                analytic=(softmax[v]-int(v==y[b,t]))/active_count if keep[b,t] else 0.
                check(math.isclose(numerical,analytic,rel_tol=2e-6,abs_tol=2e-8),'finite difference active/masked derivative')
print(f'Python/NumPy mock references: {checks} checks passed')
with tempfile.TemporaryDirectory(prefix='phase6-core-') as temp:
    source = Path(temp)/'RecentUsers.java'
    source.write_text(extract('SDE_Mock_Interview_Interviewer.md','RecentUsers.java','java'))
    subprocess.run(['javac','--release','17','-d',temp,str(source),str(HERE/'RecentUsersTest.java')],check=True)
    subprocess.run(['java','-cp',temp,'RecentUsersTest'],check=True)
print('Published-code extraction: 3 blocks executed; no service/model/hidden-test claim')
