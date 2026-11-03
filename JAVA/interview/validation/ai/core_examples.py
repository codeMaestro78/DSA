"""Small provider-neutral teaching functions; no model, network or vector DB calls."""
from itertools import islice
import math


def chunks(text, size, overlap=0):
    """Return (start,end,text) using Python code-point offsets; not model tokens."""
    if not isinstance(text, str):
        raise TypeError('text must be a string')
    if type(size) is not int or type(overlap) is not int:
        raise TypeError('size and overlap must be integers')
    if size <= 0 or overlap < 0 or overlap >= size:
        raise ValueError('require size>0 and 0<=overlap<size')
    result = []
    start = 0
    while start < len(text):
        end = min(start + size, len(text))
        result.append((start, end, text[start:end]))
        if end == len(text):
            break
        start += size - overlap
    return result


def cosine(a, b):
    """Finite nonempty equal dimensions, nonzero vectors; O(d) time/space."""
    if len(a) == 0 or len(a) != len(b):
        raise ValueError('equal nonempty dimensions required')
    try:
        if not all(math.isfinite(x) for x in (*a, *b)):
            raise ValueError('nonfinite coordinate')
        na, nb = math.hypot(*a), math.hypot(*b)
    except (OverflowError, TypeError) as error:
        raise ValueError('coordinates must have finite float representation') from error
    if na == 0 or nb == 0 or not math.isfinite(na) or not math.isfinite(nb):
        raise ValueError('finite nonzero norm required')
    score = math.fsum((x / na) * (y / nb) for x, y in zip(a, b))
    return max(-1.0, min(1.0, score))


def rrf(rankings, smoothing=60):
    """Distinct string IDs per list/tuple; rank starts1; smoothing in0..1e6."""
    if type(smoothing) is not int or not 0 <= smoothing <= 1_000_000:
        raise ValueError('integer smoothing in 0..1,000,000 required')
    scores = {}
    for ranking in rankings:
        if not isinstance(ranking, (list, tuple)):
            raise TypeError('each ranking branch must be a list or tuple')
        if any(not isinstance(item, str) for item in ranking):
            raise TypeError('IDs must be strings')
        if len(set(ranking)) != len(ranking):
            raise ValueError('duplicate ID inside one ranking')
        for rank, item in enumerate(ranking, 1):
            scores[item] = scores.get(item, 0.0) + 1.0 / (smoothing + rank)
    return sorted(scores.items(), key=lambda pair: (-pair[1], pair[0]))


def ranking_metrics(ranking, grades, k):
    """P@k denominator k; binary recall/MRR; exponential-gain NDCG, grades0..4.

    Unlisted IDs are nonrelevant. None denotes undefined recall/MRR/NDCG for
    queries without positive labels. P@k remains 0 for no relevant matches.
    """
    if type(k) is not int or k <= 0:
        raise ValueError('positive integer k required')
    if len(set(ranking)) != len(ranking):
        raise ValueError('duplicate ranked IDs')
    if any(type(g) not in (int, float) or not 0 <= g <= 4 or not math.isfinite(g)
           for g in grades.values()):
        raise ValueError('grades must be finite numeric values in 0..4')
    top = ranking[:k]
    relevant = {item for item, grade in grades.items() if grade > 0}
    hits = sum(item in relevant for item in top)
    precision = hits / k
    if not relevant:
        return {'precision': precision, 'recall': None, 'rr': None, 'ndcg': None}
    recall = hits / len(relevant)
    rr = next((1.0 / rank for rank, item in enumerate(top, 1) if item in relevant), 0.0)
    dcg = sum((2.0 ** grades.get(item, 0) - 1.0) / math.log2(rank + 1)
              for rank, item in enumerate(top, 1))
    ideal = sorted(grades.values(), reverse=True)[:k]
    idcg = sum((2.0 ** grade - 1.0) / math.log2(rank + 1)
               for rank, grade in enumerate(ideal, 1))
    return {'precision': precision, 'recall': recall, 'rr': rr, 'ndcg': dcg / idcg}


def bounded_loop(proposals, allowed, max_steps=4, cancelled=lambda: False):
    """Simulated read-only sum tool; proposals are precomputed, not model output.

    Exact dict keys/schema, allowlist and call-ID replay checks. Cooperative
    cancellation only between steps. Cache is in-memory, not durable dedup.
    """
    if type(max_steps) is not int or max_steps <= 0:
        raise ValueError('positive step budget required')
    seen, results = {}, []
    for proposal in islice(proposals, max_steps):
        if cancelled():
            return {'status': 'cancelled', 'results': results}
        if not isinstance(proposal, dict):
            raise ValueError('proposal must be a dict')
        if proposal.get('type') == 'final':
            if set(proposal) != {'type', 'text'} or not isinstance(proposal['text'], str):
                raise ValueError('invalid final result')
            if len(proposal['text']) > 4096:
                raise ValueError('final result too long')
            return {'status': 'complete', 'results': results, 'text': proposal['text']}
        if set(proposal) != {'type', 'id', 'tool', 'args'} or proposal['type'] != 'call':
            raise ValueError('invalid call fields')
        call_id, tool, args = proposal['id'], proposal['tool'], proposal['args']
        if not isinstance(call_id, str) or not call_id or len(call_id) > 128:
            raise ValueError('invalid call ID')
        if not isinstance(tool, str) or tool not in allowed or tool != 'sum':
            raise PermissionError('tool not authorized')
        if not isinstance(args, dict) or set(args) != {'x', 'y'}:
            raise ValueError('invalid argument keys')
        x, y = args['x'], args['y']
        if any(type(v) not in (int, float) or abs(v) > 1e6 or not math.isfinite(v)
               for v in (x, y)):
            raise ValueError('invalid numeric argument')
        payload = (tool, x, y)
        if call_id in seen:
            if seen[call_id][0] != payload:
                raise ValueError('call ID reused with different payload')
            value = seen[call_id][1]
        else:
            value = x + y
            seen[call_id] = (payload, value)
        results.append({'id': call_id, 'value': value})
    return {'status': 'budget_or_input_exhausted', 'results': results}
