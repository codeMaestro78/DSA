# Capacity estimation

## Why estimate

Use arithmetic to identify likely constraints, not to decorate a diagram with large numbers. Every value below is an illustrative workload assumption. Decimal units are used: 1 GB = 10⁹ bytes and 1 TB = 10¹² bytes. Binary GiB/TiB are different units.

## Worked short-link workload

Assume 10 million new links/day, 100 million redirects/day, 365-day retention, 500 stored bytes/link before indexes, three data copies, a peak factor of 10, and 20 ms mean service time at the redirect API boundary.

| Quantity | Formula | Result |
|---|---|---:|
| Mean creates/s | 10,000,000 / 86,400 | 115.74/s |
| Mean redirects/s | 100,000,000 / 86,400 | 1,157.41/s |
| Illustrative peak redirects/s | 1,157.407… × 10 | 11,574.07/s |
| Raw one-year link bytes | 10,000,000 × 365 × 500 | 1.825 TB |
| Three raw copies | 1.825 × 3 | 5.475 TB |
| Mean concurrent work at peak | 11,574.074…/s × 0.020 s | 231.48 requests |
| Response body+headers at 800 bytes | 11,574.074…/s × 800 bytes | 9.26 MB/s |

The storage model excludes indexes, write-ahead logs, free space, backups, allocator overhead and metadata growth. The network model excludes request bytes, TLS/TCP framing and internal hops. Do not present either as a full provisioned capacity requirement.

The concurrency estimate uses average residence time at the chosen boundary under an approximately stable workload. It is an average relationship, not a tail-concurrency guarantee or a safe worker count. Burstiness and saturation invalidate treating it as a fixed upper bound.

## Sensitivity matters more than precision

A tenfold peak factor is an assumption. Recalculate for 3× and 30×. If the architecture decision changes, demand needs better evidence. A cache hit ratio is not derivable from dataset size: locality, key skew, expiry and invalidation determine it. Model 0%, 80% and 95% hits as scenarios, not promises. At the 95% scenario, peak backing-store reads would be about 578.70/s if every miss causes one read; stampedes and retries can increase it.

## Queue drain example

A backlog contains 120,000 jobs. New arrivals are 100 jobs/s and sustainable completions are 300 jobs/s. Net drain is 200 jobs/s, so draining the backlog takes 120,000 / 200 = **600 seconds**, assuming rates remain constant. Dividing by 300 gives 400 seconds and wrongly ignores new arrivals. If completions do not exceed arrivals, the backlog does not drain.

## Failure capacity

If four API instances each safely handle 4,000 requests/s under a representative test, total capacity is 16,000/s. Losing one leaves 12,000/s. An illustrative 11,574/s peak leaves only about 426/s spare after that failure. Autoscaling is delayed and cannot fix a saturated shared database. The 4,000/s value must come from measurement before it is used for a real deployment.

## Practice question

**SD-CAP-01 — Intermediate; backend/platform; design; 0–2 years.** A queue has 60,000 jobs, arrivals 500/s and service 700/s. How long does it take to empty? What if service falls to 450/s?

<details><summary>Expected answer</summary>

At constant rates, 60,000/(700−500) = 300 seconds. At 450/s the backlog grows by 50/s and never empties while those rates persist. Followup: include retries and multiple jobs created per input. Mistake: using average incoming requests instead of the actual job arrival rate.

</details>

## Validation

The arithmetic above is independently asserted in [capacity checks](../validation/design/check_capacity.py). Run `python3 interview/validation/design/check_capacity.py` from the Java workspace root. Passing assertions checks these formulas and values, not the realism of the assumptions or throughput of a service.

## References

- [Google SRE — handling overload](https://sre.google/sre-book/handling-overload/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
