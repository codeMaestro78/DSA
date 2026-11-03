"""Deterministic checks for the illustrative system-design calculations.

Run from the workspace root:
    python3 interview/validation/design/check_capacity.py
These checks validate arithmetic, not workload realism or service throughput.
"""
from decimal import Decimal, getcontext

getcontext().prec = 40
D = Decimal
checks = 0


def check(condition: bool, label: str) -> None:
    global checks
    if not condition:
        raise AssertionError(label)
    checks += 1


def drain_seconds(backlog: int, arrivals_per_second: int,
                  completions_per_second: int) -> Decimal | None:
    """Return drain time under constant rates, or None if it cannot drain."""
    if min(backlog, arrivals_per_second, completions_per_second) < 0:
        raise ValueError("backlog and rates must be nonnegative")
    if backlog == 0:
        return D(0)
    spare = completions_per_second - arrivals_per_second
    return D(backlog) / D(spare) if spare > 0 else None


seconds_per_day = D(86400)
create_rate = D(10_000_000) / seconds_per_day
redirect_rate = D(100_000_000) / seconds_per_day
peak = redirect_rate * D(10)
raw_bytes = D(10_000_000) * D(365) * D(500)
check(round(create_rate, 2) == D("115.74"), "mean create rate")
check(round(redirect_rate, 2) == D("1157.41"), "mean redirect rate")
check(round(peak, 2) == D("11574.07"), "peak redirect rate")
check(raw_bytes / D(10**12) == D("1.825"), "raw TB")
check(raw_bytes * D(3) / D(10**12) == D("5.475"), "three raw copies TB")
check(round(peak * D("0.020"), 2) == D("231.48"), "mean concurrency")
check(round(peak * D(800) / D(10**6), 2) == D("9.26"), "response MB/s")
check(round(peak * D("0.05"), 2) == D("578.70"), "95 percent cache scenario")
check(drain_seconds(120_000, 100, 300) == D(600), "600 second drain")
check(drain_seconds(60_000, 500, 700) == D(300), "300 second drain")
check(drain_seconds(60_000, 500, 450) is None, "growing backlog")
check(drain_seconds(1, 100, 100) is None, "zero spare capacity")
check(drain_seconds(0, 500, 450) == D(0), "empty initial backlog")
check(4 * 4000 == 16000 and 3 * 4000 == 12000, "failure capacity")
check(round(D(12000) - peak) == D(426), "remaining failure headroom")
check(62**8 == 218_340_105_584_896, "eight-character base62 namespace")
notification_peak = D(1_000_000) / seconds_per_day * D(20)
check(round(notification_peak, 3) == D("231.481"), "notification peak")
check(D(5 * 50) >= notification_peak and D(4 * 50) < notification_peak,
      "five worker scenario loses peak capacity after one failure")
check(D(5 * 50) >= notification_peak,
      "six worker scenario retains peak capacity after one failure")
check((D(1) - D("0.999")) * D(1_000_000) == D(1000), "request error budget")
check(3**5 == 243, "nested retry amplification")
try:
    drain_seconds(-1, 0, 1)
except ValueError:
    check(True, "negative input rejected")
else:
    raise AssertionError("negative backlog was accepted")

print(f"PASS: {checks} arithmetic, scenario and input checks; no load test performed.")
