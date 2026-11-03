# Caching

## What a cache solves

A cache reuses a previously computed or fetched result to reduce work or latency. It introduces another copy that can be stale, missing or unavailable. Cache only after identifying a repeated expensive read or computation and a tolerable freshness policy.

Application caches and HTTP caches have different contracts. HTTP cache directives are defined in RFC 9111; an application's Redis TTL does not automatically set browser behavior.

## Cache-aside read flow

1. Build a key containing every dimension that changes the result: resource, version, tenant and possibly authorization scope.
2. Revalidate current resource/action authority wherever the policy requires it, on hits as well as misses. On an eligible hit, return the cached result. A cached content snapshot cannot grant permanent access after revocation.
3. On a miss, read the authoritative source, then populate the cache with an explicit expiry policy.
4. Limit concurrent miss work and fail gracefully if the cache is unavailable.

For a public short code, a key might be `link:v3:code`. For a private user's dashboard, omitting user/tenant scope can leak data. A shared cache is not an authorization boundary.

## A subtle stale-fill race

At time 1 a reader misses and reads old value A. At time 2 a writer commits B and deletes the cache key. At time 3 the slow reader caches A. Invalidation happened, but stale A has returned. Depending on the requirement, use bounded TTL, versioned values/keys, coordinated population or a stronger read path. “Delete after write” by itself is not proof of zero staleness.

Write-through updates the backing store and cache through a controlled path; it still needs a failure policy for partial updates. Write-behind acknowledges cached changes before persistence and risks loss unless separately protected. Choose based on durability and consistency requirements, not terminology.

## Failure modes and tradeoffs

| Failure | Detection | Mitigation and limit |
|---|---|---|
| Many keys expire together | Backing-store traffic and latency spike | TTL jitter; it spreads refreshes but does not reduce total work |
| One hot key expires | Concurrent identical misses | Single-flight/refresh coordination, stale serving only if permitted |
| Cache outage | Errors and miss-rate jump | Bounded fallback; avoid sending unlimited load to the database |
| Negative lookup cached | New object still looks absent | Short negative TTL and invalidation on creation |
| Revoked object still cached | Post-revocation access test | Explicit invalidation/freshness policy; bypass/fail closed where required |

A high hit ratio alone does not prove benefit; measure end-to-end latency, cache costs and backing-store impact. A few expensive misses can dominate a tail percentile.

## Practice question

**SD-CACHE-01 — Intermediate; backend/platform; design; 0–2 years.** A public link may be revoked within five seconds. Is a one-hour TTL safe?

<details><summary>Expected answer and followups</summary>

Not by itself. A healthy cache can continue serving a revoked value for almost an hour. Use a bounded freshness mechanism that also covers invalidation failure, or change the requirement. Followup: can browsers cache the redirect longer than the server cache? Yes; end-to-end revocation needs HTTP caching policy as well. Mistake: equating cache deletion with guaranteed deletion of all copies.

</details>

## Checklist

- [ ] Key scope prevents cross-user/tenant reuse.
- [ ] A stale value and a total cache outage have explicit behavior.
- [ ] Concurrent fill/invalidation races are considered.
- [ ] HTTP/browser caching and application caching are distinguished.

## References

- [RFC 9111 — HTTP caching](https://www.rfc-editor.org/rfc/rfc9111.html)
- [Google SRE — handling overload](https://sre.google/sre-book/handling-overload/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
