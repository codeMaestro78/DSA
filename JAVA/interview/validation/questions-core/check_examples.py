#!/usr/bin/env python3
"""Execute marked published blocks, not a separately maintained implementation."""
import itertools
import math
from pathlib import Path
import random
import re
import subprocess
import sys
import tempfile
import numpy as np

sys.dont_write_bytecode = True
BASE = Path(__file__).resolve().parents[2]
HERE = Path(__file__).resolve().parent
BANK = BASE / '12-Interview-Question-Bank'
checks = 0

def check(condition):
    global checks
    checks += 1
    if not condition:
        raise AssertionError(f'check {checks}')

def extract(document, name, language):
    text = (BANK / document).read_text()
    pattern = rf'<!-- phase5-code: {re.escape(name)} -->\s*```{language}\n(.*?)\n```'
    matches = re.findall(pattern, text, re.S)
    if len(matches) != 1:
        raise AssertionError(f'need exactly one {name} block')
    return matches[0] + '\n'

def invalid(fn, *args):
    try:
        fn(*args)
    except ValueError:
        check(True)
    else:
        raise AssertionError('invalid input accepted')

threshold_scope = {}
exec(compile(extract('ML_Engineer_Questions.md', 'threshold.py', 'python'), 'published-threshold', 'exec'), threshold_scope)
threshold = threshold_scope['choose_threshold']
regression_scope = {}
exec(compile(extract('AI_ML_Questions.md', 'regression.py', 'python'), 'published-regression', 'exec'), regression_scope)
regression = regression_scope['regression_loss_gradient']

# Oracle enumerates every binary mask, retaining only masks expressible by p >= t.
def threshold_oracle(y, p, fp, fn):
    choices = []
    for mask in itertools.product((0, 1), repeat=len(y)):
        t = min((s for s, bit in zip(p, mask) if bit), default=math.inf)
        if any(bool(bit) != (s >= t) for bit, s in zip(mask, p)):
            continue
        cost = sum(fp if bit and not label else fn if label and not bit else 0
                   for bit, label in zip(mask, y))
        choices.append((cost, -t, t))
    cost, _, t = min(choices)
    return t, cost

rng = random.Random(5022026)
check(threshold([0, 1], [0.8, 0.8], 1, 1) == (math.inf, 1))
check(threshold([1], [0], 2, 30) == (0, 0))
check(threshold([0], [1], 0, 0) == (math.inf, 0))
# Narrow NumPy scalar arithmetic used to overflow or wrap even though these
# values satisfy the float64-bound prompt; compare to a Python-float oracle.
for narrow_cost in [np.float16(60000), np.float32(3e38), np.int64(2**62)]:
    labels, scores = [0, 0, 1, 1, 1], [0.9, 0.8, 0.7, 0.6, 0.5]
    expected = threshold_oracle(labels, scores, float(narrow_cost), float(narrow_cost))
    check(threshold(labels, scores, narrow_cost, narrow_cost) == expected)
    check(expected[0] == 0.5 and math.isfinite(expected[1]))
for n in range(1, 8):
    for _ in range(100):
        y = [rng.randrange(2) for _ in range(n)]
        p = [rng.randrange(5) / 4 for _ in range(n)]
        fp, fn = rng.randrange(6), rng.randrange(6)
        check(threshold(y, p, fp, fn) == threshold_oracle(y, p, fp, fn))
for args in [([], [], 1, 1), ([1], [], 1, 1), ([2], [0.2], 1, 1),
             ([1], [math.nan], 1, 1), ([1], [math.inf], 1, 1),
             ([1], [-0.1], 1, 1), ([1], [1.1], 1, 1),
             ([1], [0.2], -1, 1), ([1], [0.2], 1, math.inf),
             ([1, 0], [0.2, 0.3], 1e300, 1),
             ([1], [10**1000], 1, 1), ([1], [0.2], 10**1000, 1),
             ([1], [0.2], -(10**1000), 1)]:
    invalid(threshold, *args)

num_rng = np.random.default_rng(5022026)
loss, grad = regression([[1, 0], [1, 1]], [1, 3], [0, 0])
check(loss == 2.5 and np.allclose(grad, [-2, -1.5]))
for n in range(1, 7):
    for d in range(1, 6):
        for _ in range(5):
            X, y, w = num_rng.normal(size=(n, d)), num_rng.normal(size=n), num_rng.normal(size=d)
            loss, grad = regression(X, y, w)
            residual = [sum(X[i, j] * w[j] for j in range(d)) - y[i] for i in range(n)]
            expected_loss = sum(r*r for r in residual)/(2*n)
            expected_grad = [sum(X[i, j]*residual[i] for i in range(n))/n for j in range(d)]
            check(math.isclose(loss, expected_loss, rel_tol=1e-12, abs_tol=1e-12))
            check(np.allclose(grad, expected_grad, rtol=1e-12, atol=1e-12))
            for j in range(d):
                plus, minus = w.copy(), w.copy()
                plus[j] += 1e-5; minus[j] -= 1e-5
                finite_difference = (regression(X, y, plus)[0]-regression(X, y, minus)[0])/2e-5
                check(math.isclose(grad[j], finite_difference, rel_tol=1e-7, abs_tol=1e-8))
for args in [([], [], []), ([[1]], [[1]], [1]), ([[1]], [1], [[1]]),
             ([[math.nan]], [1], [1]), ([[1]], [math.inf], [1]),
             ([[1e308]], [1], [1e308])]:
    invalid(regression, *args)
for args in [(np.array([[1+2j]]), [0], [1]),
             ([[1]], np.array([1+2j]), [1]),
             ([[1]], [0], np.array([1+2j])),
             ([[True]], [0], [1]), ([["1"]], [0], [1]),
             ([[1],[1,2]], [0], [1]), (None, [0], [1])]:
    invalid(regression, *args)

# Independently execute numerical worked examples from the prose.
u, v = np.array([1., 2.]), np.array([2., 0.])
projection = u * (u @ v) / (u @ u)
check(np.allclose(projection, [0.4, 0.8]) and abs(u @ (v-projection)) < 1e-12)
check(math.isclose(90/(90+495), 2/13))
check(math.isclose(2/(2+30), 0.0625))
z = np.array([1000., 1001., 999.])
check(math.isclose(float(np.log(np.exp(z-z.max()).sum()) + z.max()-z[1]), 0.40760596444442854, abs_tol=1e-12))
check(math.isclose(2+0.9*2, 3.8))
check(8*(4096+4096) == 65536)
check(math.isclose((5**2)/(5**2+3**2+1), 5/7))
print(f'Python/NumPy question examples: {checks} checks passed')
with tempfile.TemporaryDirectory(prefix='phase5-core-') as temp:
    target = Path(temp)
    (target/'ContractLru.java').write_text(extract('SDE_Questions.md', 'ContractLru.java', 'java'))
    subprocess.run(['javac', '--release', '17', '-d', temp, str(target/'ContractLru.java'), str(HERE/'LruContractTest.java')], check=True)
    subprocess.run(['java', '-cp', temp, 'LruContractTest'], check=True)
print('Published-code extraction: 3 blocks executed; no compiled artifacts retained in repository')
