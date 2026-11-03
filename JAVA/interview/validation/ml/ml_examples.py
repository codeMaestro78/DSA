"""Small CPU teaching routines, not a training/serving framework.

Feature, weight, bias, standardizer and attention inputs require real numeric
dtypes and convert to finite float64. Logistic binary labels may also be boolean;
other boolean, numeric-string, complex and object numeric inputs are rejected.
The separate metrics function checks its documented binary-value contract. Inputs are small
fixtures; shapes and invalid cases are checked instead of broadcasting silently.
"""
import numpy as np


def real_array(value, name, *, allow_bool=False):
    """Reject lossy complex/string coercion before converting real data."""
    try:
        raw = np.asarray(value)
        if raw.dtype.kind not in ('biuf' if allow_bool else 'iuf'):
            raise ValueError(name + ' must have a real numeric dtype')
        with np.errstate(over='ignore', invalid='ignore'):
            result = raw.astype(np.float64)
    except (TypeError, ValueError, OverflowError) as error:
        raise ValueError(name + ' must be a real numeric array') from error
    if not np.isfinite(result).all():
        raise ValueError(name + ' must be finite')
    return result


def matrix(value, name):
    result = real_array(value, name)
    if result.ndim != 2 or 0 in result.shape:
        raise ValueError(name + ' must be a finite nonempty matrix')
    return result


def logistic_loss_gradient(x, y, w, bias):
    """Mean binary log loss and gradients for X:(n,d), y:(n,), w:(d,)."""
    x = matrix(x, 'x')
    y = real_array(y, 'y', allow_bool=True)
    w = real_array(w, 'w')
    bias_value = real_array(bias, 'bias')
    if y.shape != (x.shape[0],) or not np.isin(y, [0., 1.]).all():
        raise ValueError('y must have one binary label per row')
    if w.shape != (x.shape[1],) or bias_value.ndim != 0:
        raise ValueError('invalid weights or bias')
    bias = float(bias_value)
    with np.errstate(over='ignore', invalid='ignore'):
        logits = x @ w + bias
    if not np.isfinite(logits).all():
        raise ValueError('nonfinite logits')
    probabilities = 0.5 * (1.0 + np.tanh(logits / 2.0))
    with np.errstate(over='ignore', invalid='ignore'):
        loss = np.mean(np.logaddexp(0.0, logits) - y * logits)
        residual = probabilities - y
        dw = x.T @ residual / len(y)
        db = float(residual.mean())
    if not np.isfinite(loss) or not np.isfinite(dw).all() or not np.isfinite(db):
        raise ValueError('nonfinite loss or gradient; rescale input magnitudes')
    return float(loss), dw, db


def binary_metrics(y, prediction):
    """Return confusion counts and metrics; undefined ratios are None."""
    y, prediction = np.asarray(y), np.asarray(prediction)
    if (y.ndim != 1 or not len(y) or prediction.shape != y.shape
            or not np.isin(y, [0, 1]).all()
            or not np.isin(prediction, [0, 1]).all()):
        raise ValueError('nonempty equal-length binary vectors required')
    tp = int(np.sum((y == 1) & (prediction == 1)))
    fp = int(np.sum((y == 0) & (prediction == 1)))
    fn = int(np.sum((y == 1) & (prediction == 0)))
    tn = int(np.sum((y == 0) & (prediction == 0)))
    return {
        'tp': tp, 'fp': fp, 'fn': fn, 'tn': tn,
        'precision': tp / (tp + fp) if tp + fp else None,
        'recall': tp / (tp + fn) if tp + fn else None,
        'f1': 2 * tp / (2 * tp + fp + fn) if 2 * tp + fp + fn else None,
        'accuracy': (tp + tn) / len(y),
    }


def fit_standardizer(training_x):
    """Fit population mean/std to training rows only; constants get scale one."""
    x = matrix(training_x, 'training_x')
    with np.errstate(over='ignore', invalid='ignore'):
        mean, scale = x.mean(axis=0), x.std(axis=0)
    if not np.isfinite(mean).all() or not np.isfinite(scale).all():
        raise ValueError('nonfinite fitted statistics; rescale input magnitudes')
    return mean, np.where(scale == 0., 1., scale)


def attention(q, k, v, allowed):
    """Single-head scaled attention; True=allowed; all-masked rows rejected.

    q:(queries,d), k:(keys,d), v:(keys,value_dim), allowed:(queries,keys).
    The score matrix is materialized: O(queries*keys) extra score storage.
    """
    q, k, v = matrix(q, 'q'), matrix(k, 'k'), matrix(v, 'v')
    allowed = np.asarray(allowed)
    if k.shape[1] != q.shape[1] or k.shape[0] != v.shape[0]:
        raise ValueError('incompatible attention shapes')
    if allowed.dtype != np.bool_ or allowed.shape != (len(q), len(k)):
        raise ValueError('mask must be a correctly shaped boolean matrix')
    if not allowed.any(axis=1).all():
        raise ValueError('every query needs at least one allowed key')
    with np.errstate(over='ignore', invalid='ignore'):
        scores = q @ k.T / np.sqrt(q.shape[1])
    if not np.isfinite(scores).all():
        raise ValueError('nonfinite scores')
    scores = np.where(allowed, scores, -np.inf)
    weights = np.exp(scores - scores.max(axis=1, keepdims=True))
    weights /= weights.sum(axis=1, keepdims=True)
    with np.errstate(over='ignore', invalid='ignore'):
        output = weights @ v
    if not np.isfinite(output).all():
        raise ValueError('nonfinite attention output; rescale value magnitudes')
    return output, weights
