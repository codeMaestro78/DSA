"""Validate illustrative AI architecture budgets, not model/provider performance.

Run: python3 interview/validation/ai-design/check_architecture.py
Only the Python standard library is required. No network or GPU access.
"""
from decimal import Decimal


def nonnegative_integer(value, name):
    if type(value) is not int or value < 0:
        raise ValueError(f'{name} must be a nonnegative integer')


def remaining_context(limit, reservations):
    """Return remaining units under one shared accounting limit; reject overflow."""
    nonnegative_integer(limit, 'limit')
    total = 0
    for amount in reservations:
        nonnegative_integer(amount, 'reservation')
        total += amount
    if total > limit:
        raise ValueError('reservations exceed the shared context limit')
    return limit - total


def raw_kv_bytes(layers, kv_heads, head_dim, token_lengths, bytes_per_element):
    """Raw K+V tensor bytes with potentially different lengths per sequence.

    Omits block padding, buffers, metadata, replication and model-specific layouts.
    It is not a GPU admission-control implementation.
    """
    for name, value in [('layers', layers), ('kv_heads', kv_heads),
                        ('head_dim', head_dim), ('bytes', bytes_per_element)]:
        if type(value) is not int or value <= 0:
            raise ValueError(f'{name} must be a positive integer')
    tokens = 0
    for length in token_lengths:
        nonnegative_integer(length, 'sequence token count')
        tokens += length
    return 2 * layers * kv_heads * head_dim * tokens * bytes_per_element


checks = 0


def check(condition, description):
    global checks
    if not condition:
        raise AssertionError(description)
    checks += 1


def rejects(function, *args):
    try:
        function(*args)
    except ValueError:
        check(True, 'invalid input rejected')
    else:
        raise AssertionError('expected ValueError')


check(remaining_context(8192, [1024, 512, 1536, 512]) == 4608,
      'worked context budget')
check(remaining_context(3, [1, 2]) == 0, 'exactly filled context')
check(remaining_context(7, []) == 7, 'no reservations')
check(remaining_context(0, []) == 0, 'zero context')
rejects(remaining_context, 3, [4])
rejects(remaining_context, 4, [-1])
rejects(remaining_context, 4, [True])
rejects(remaining_context, -1, [])
check(raw_kv_bytes(32, 8, 128, [4096] * 4, 2) == 2**31,
      'worked grouped-query cache equals two GiB')
check(raw_kv_bytes(32, 4, 128, [4096] * 4, 2) == 2**30,
      'halving KV heads halves this raw component')
check(raw_kv_bytes(2, 3, 4, [5, 7], 2) ==
      raw_kv_bytes(2, 3, 4, [5], 2) + raw_kv_bytes(2, 3, 4, [7], 2),
      'variable-length sequences sum storage')
check(raw_kv_bytes(2, 3, 4, [], 2) == 0, 'no active sequences')
check(raw_kv_bytes(2, 3, 4, [0], 2) == 0, 'empty sequence raw storage')
rejects(raw_kv_bytes, 0, 8, 128, [1], 2)
rejects(raw_kv_bytes, 32, 8, 128, [-1], 2)
rejects(raw_kv_bytes, 32, 8, 128, [1], 0)
check(1_000_000 * 768 * 4 == 3_072_000_000, 'raw float32 embedding bytes')
check(Decimal(3_072_000_000) / Decimal(10**9) == Decimal('3.072'),
      'embedding decimal GB')
check(7_000_000_000 * 2 == 14_000_000_000, 'weight bytes without overhead')
check(Decimal(40) * Decimal('0.25') == Decimal(10),
      'stable-boundary mean concurrency')
check(max(2, 3) + 4 == 7, 'parallel branches plus dependent synthesis')
print(f'PASS: {checks} AI architecture arithmetic, boundary and consistency checks; '
      'no provider, tokenizer, GPU or load test performed.')
