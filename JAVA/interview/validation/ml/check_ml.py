"""Deterministic numerical checks. Run with PYTHONDONTWRITEBYTECODE=1.

No torch, TensorFlow, scikit-learn, GPU, model download or external API is used.
"""
import ast
from pathlib import Path
import re
import sys
sys.dont_write_bytecode = True
import numpy as np
from ml_examples import logistic_loss_gradient, binary_metrics, fit_standardizer, attention

checks = 0

def check(ok, message):
    global checks
    if not ok:
        raise AssertionError(message)
    checks += 1

def near(actual, expected, message, atol=1e-8):
    check(np.allclose(actual, expected, atol=atol, rtol=1e-6), message)

def rejected(call, message):
    try:
        call()
    except ValueError:
        check(True, message)
    else:
        raise AssertionError(message)

rng = np.random.default_rng(20261102)
for trial in range(25):
    x = rng.normal(size=(7, 3))
    y = rng.integers(0, 2, size=7)
    w, b = rng.normal(size=3), float(rng.normal())
    loss, dw, db = logistic_loss_gradient(x, y, w, b)
    eps = 1e-6
    for j in range(3):
        step = np.zeros(3); step[j] = eps
        numerical = (logistic_loss_gradient(x, y, w+step, b)[0]
                     - logistic_loss_gradient(x, y, w-step, b)[0]) / (2*eps)
        near(dw[j], numerical, 'weight finite-difference gradient')
    numerical_b = (logistic_loss_gradient(x, y, w, b+eps)[0]
                   - logistic_loss_gradient(x, y, w, b-eps)[0]) / (2*eps)
    near(db, numerical_b, 'bias finite-difference gradient')
    improved = logistic_loss_gradient(x, y, w-.01*dw, b-.01*db)[0]
    check(improved <= loss, 'small gradient step reduces this fixture loss')
near(logistic_loss_gradient([[1], [-1]], [0, 1], [1000], 0)[0], 1000.,
     'stable loss handles large wrong logits')
rejected(lambda: logistic_loss_gradient([[1]], [2], [0], 0), 'invalid label')
rejected(lambda: logistic_loss_gradient([[1]], [0], [0, 1], 0), 'invalid shape')
rejected(lambda: logistic_loss_gradient([[np.nan]], [0], [0], 0), 'nonfinite input')
m = binary_metrics([1, 1, 0, 0], [1, 0, 1, 0])
check([m[k] for k in ['tp','fp','fn','tn']] == [1,1,1,1], 'confusion counts')
near([m[k] for k in ['precision','recall','f1','accuracy']], [.5]*4, 'basic metrics')
check(binary_metrics([0,0], [0,0])['precision'] is None, 'undefined precision')
check(binary_metrics([0,0], [1,0])['recall'] is None, 'undefined recall')
check(binary_metrics([0,0], [0,0])['f1'] is None, 'all-negative undefined f1 policy')
near(binary_metrics([1], [0])['f1'], 0., 'false negative f1 zero')
rejected(lambda: binary_metrics([], []), 'empty metrics rejected')
rejected(lambda: binary_metrics([1], [1,0]), 'metric length mismatch')
rejected(lambda: binary_metrics([1], [.2]), 'scores are not class labels')
mean, scale = fit_standardizer([[0,7],[2,7]])
near(mean, [1,7], 'training means')
near(scale, [1,1], 'constant feature policy')
training = np.array([[0,7],[2,7]], dtype=float)
near(((training-mean)/scale).mean(axis=0), [0,0], 'training transform centered')
heldout = np.array([[100,7]], dtype=float)
near((heldout-mean)/scale, [[99,0]], 'heldout uses training statistics')
leaky_mean, _ = fit_standardizer(np.vstack([training, heldout]))
check(not np.allclose(mean, leaky_mean), 'fitting all rows changes learned preprocessing')
rejected(lambda: fit_standardizer([]), 'empty fit rejected')
rejected(lambda: fit_standardizer([[1e308],[1e308]]), 'derived overflow rejected')
rejected(lambda: logistic_loss_gradient([[1]], [0], [0], np.array([0.])), 'nonscalar bias rejected')
rejected(lambda: logistic_loss_gradient([[1e308]]*4, [1]*4, [0], -1000), 'derived gradient overflow rejected')
rejected(lambda: logistic_loss_gradient([[1e308]]*4, [0]*4, [1], 0), 'derived loss reduction overflow rejected')
# Real-valued numerical contracts reject coercions that would silently lose data.
for x, y, w, bias in [
    (np.array([[1+2j]]), [0], [0], 0),
    ([[1]], np.array([1+2j]), [0], 0),
    ([[1]], [0], np.array([1+2j]), 0),
    ([[1]], [0], [0], 1+2j),
    ([["1"]], [0], [0], 0),
    ([[1]], ["0"], [0], 0),
    ([[1]], [0], ["0"], 0),
    ([[1]], [0], [0], "0"),
    ([[True]], [0], [0], 0),
    ([[1]], [0], [False], 0),
    ([[1]], [0], [0], False),
    (None, [0], [0], 0),
    ([[1]], None, [0], 0),
    ([[1]], [0], None, 0),
    ([[1]], [0], [0], None),
]:
    rejected(lambda x=x,y=y,w=w,bias=bias: logistic_loss_gradient(x,y,w,bias),
             'non-real/coerced/malformed logistic input rejected')
for value in [np.array([[1+2j]]), [["1"]], [[True]], [[1,2],[3]]]:
    rejected(lambda value=value: fit_standardizer(value),
             'non-real/coerced/ragged standardizer input rejected')
near(logistic_loss_gradient([[1]], [True], [0], 0)[0], np.log(2),
     'boolean binary label remains accepted')
for position in range(3):
    for bad in [np.array([[1+2j]]), [["1"]], [[True]]]:
        args = [[[1]], [[1]], [[2]], [[True]]]
        args[position] = bad
        rejected(lambda args=args: attention(*args),
                 'non-real/coerced attention matrix rejected')
for length in range(1, 13):
    q,k,v = rng.normal(size=(length,4)),rng.normal(size=(length,4)),rng.normal(size=(length,3))
    allowed = np.tril(np.ones((length,length),dtype=bool))
    out, weights = attention(q,k,v,allowed)
    near(weights.sum(axis=1), np.ones(length), 'row normalized attention')
    near(weights[~allowed], 0., 'causal mask zero weights')
    for row in range(length):
        logits = (q[row] @ k[:row+1].T) / 2.
        ex = np.exp(logits-logits.max()); ex /= ex.sum()
        near(out[row], ex @ v[:row+1], 'attention agrees independent per-query reference')
    # Future value/key changes cannot affect earlier outputs under causal masking.
    if length > 1:
        changed_k, changed_v = k.copy(),v.copy()
        changed_k[-1] += 20; changed_v[-1] += 30
        changed, _ = attention(q,changed_k,changed_v,allowed)
        near(out[:-1], changed[:-1], 'future perturbation leaves earlier outputs unchanged')
rejected(lambda: attention([[1]],[[1]],[[2]],[[False]]), 'all-masked row rejected')
rejected(lambda: attention([[1]],[[1]],[[2]],[[1]]), 'nonboolean mask rejected')
rejected(lambda: attention([[1,2]],[[1]],[[2]],[[True]]), 'attention dimensions rejected')
# A rounded weighted reduction at the float64 limit may overflow even though
# its exact convex combination is finite. Different BLAS backends can differ;
# either a finite result or explicit rejection satisfies the teaching contract.
try:
    extreme, _ = attention([[0]], np.zeros((11,1)),
                           np.full((11,1), np.finfo(float).max),
                           np.ones((1,11),dtype=bool))
except ValueError:
    check(True, 'derived attention overflow explicitly rejected')
else:
    check(np.isfinite(extreme).all(), 'extreme attention result must be finite')
# LoRA orientation and operation equivalence: W:(out,in), B:(out,r), A:(r,in).
x,w,a,b = rng.normal(size=(5,4)),rng.normal(size=(3,4)),rng.normal(size=(2,4)),rng.normal(size=(3,2))
near(x@(w+b@a).T, x@w.T+(x@a.T)@b.T, 'LoRA merged/unmerged equivalence')
check(a.size+b.size == 2*(4+3), 'adapter parameter count')
# Symmetric pedagogic int8 quantization; no saturation for this bounded fixture.
v = np.array([-1.,-.4,0.,.3,1.]); scale=1/127
quantized=np.clip(np.rint(v/scale),-127,127).astype(np.int8)
check(np.max(np.abs(v-quantized.astype(float)*scale)) <= scale/2+1e-12,
      'nearest rounding bound away from clipping')
check((32+2*1-1*(3-1)-1)//2+1 == 16, 'convolution output-width example')
check(8*3*3*3+8 == 224, 'convolution parameter count')
check(2*3+3+3*1+1 == 13, 'two-layer parameter count')
near(90/(90+495), .15384615384615385, 'base-rate precision example')
check(4*32*2 == 256, 'distributed effective batch example')
near(-np.log(.9), .10536051565782628, 'correct probability point nine log loss')
near(-np.log(.1), 2.3025850929940455, 'correct probability point one log loss')
# Expected discounted return of deterministic rewards, not a sampled RL benchmark.
near(1+.9*2+.9**2*3, 5.23, 'discounted return')
# Framework code is parsed for syntax only; dependency absence is reported.
chapter_root=Path(__file__).resolve().parents[2]/'07-AI-ML-Deep-Learning'
parsed=0
for name in ['PyTorch.md','TensorFlow.md']:
    path=chapter_root/name
    if path.exists():
        for block in re.findall(r'```python\n(.*?)```',path.read_text(),re.S):
            ast.parse(block); parsed += 1
check(parsed == 2, 'both published framework snippets parse')
print(f'PASS: {checks} ML numerical/contract/syntax checks; NumPy {np.__version__}; Python {sys.version.split()[0]}; seed=20261102')
print('Framework snippets: AST syntax only; torch/TensorFlow unavailable; no GPU or training framework run.')
