"""Synthetic nearest-rank quantile fixture for QB-ADV-010; no live latency test."""
from math import isfinite
from fractions import Fraction


def percentile(values, fraction):
    if not values or type(fraction) not in (int, float) or not 0 < fraction <= 1 or not isfinite(fraction):
        raise ValueError('nonempty values and fraction in (0,1] required')
    if any(type(v) not in (int, float) or v < 0 or (type(v) is float and not isfinite(v)) for v in values):
        raise ValueError('finite nonnegative latency values required')
    ordered = sorted(values)
    # Treat the supplied fraction's decimal spelling as the requested quantile.
    q = Fraction(str(fraction))
    rank = (q.numerator * len(ordered) + q.denominator - 1) // q.denominator
    return ordered[rank - 1]


checks = 0


def check(condition, label):
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1


first = [100] * 5 + [1] * 95
aligned = first.copy()
disjoint = [1] * 5 + [100] * 5 + [1] * 90
check(percentile(first, .95) == 1, 'stage A empirical p95')
check(percentile(aligned, .95) == percentile(disjoint, .95) == 1,
      'identical marginal stage B p95')
check(sorted(aligned) == sorted(disjoint), 'identical complete marginal distributions')
check(percentile([a+b for a,b in zip(first, aligned)], .95) == 2,
      'aligned end-to-end p95')
check(percentile([a+b for a,b in zip(first, disjoint)], .95) == 101,
      'disjoint end-to-end p95')
check(percentile(list(reversed(first)), .95) == percentile(first, .95),
      'order does not change quantile')
check(percentile([7], .95) == 7, 'singleton')
check(percentile([1,2,3], 1) == 3, 'maximum endpoint')
check(percentile(list(range(1, 101)), .14) == 14, 'decimal fraction avoids float ceil boundary')
check(percentile([10**1000], .95) == 10**1000, 'integer latencies do not require float conversion')
for values, fraction in [([], .95), ([1], 0), ([1], float('nan')), ([-1], .95), ([True], .95), ([1], 10**1000), ([1], -(10**1000))]:
    try:
        percentile(values, fraction)
    except ValueError:
        check(True, 'invalid contract rejected')
    else:
        raise AssertionError('invalid input accepted')
print(f'PASS: {checks} synthetic quantile/correlation checks; no service benchmark performed.')
