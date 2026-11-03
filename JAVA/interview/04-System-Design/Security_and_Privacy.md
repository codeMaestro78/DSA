# Security and privacy in system design

## Put trust boundaries in the flow

Authentication establishes an identity; authorization decides whether that identity may perform an operation on a resource. A valid token does not imply access to every resource ID. OWASP's authorization guidance recommends least privilege, denial by default and checks on each request.

For a multi-tenant API, derive tenant context from trusted authenticated membership, not an arbitrary client-provided header. Query/authorize the object within that context. A hard-to-guess identifier reduces enumeration but is not permission enforcement.

## Worked short-link boundary

A creator can revoke only their tenant's link. A public visitor can redirect an active public code. These are different policies and endpoints. Internal statistics may reveal browsing information and need separate access control. If links are intended as bearer secrets, state that model explicitly and do not call them private authenticated resources.

Validate destinations with a URL parser, permit only supported schemes such as HTTP/HTTPS, and define length/canonicalization policy. A redirect service can enable phishing or abuse, so define creation limits, reporting and disabling procedures. If it fetches URLs for previews or safety checks, the server performs network requests: that creates SSRF risk. Isolate the fetcher, restrict network destinations/redirects and recheck resolution; string-prefix validation alone is not sufficient.

## Threat and failure model

| Asset / boundary | Abuse or failure | Control and verification |
|---|---|---|
| Private notification status | Another tenant guesses an ID | Server-side object authorization; cross-tenant negative tests |
| Provider credentials | Logs or an exposed config leak keys | Secret store, scoped credentials, redaction and rotation |
| User-controlled template data | Injected executable markup or unsafe link | Context-appropriate output handling and validated template system |
| Administrative revoke action | Stolen session changes links | Strong admin auth, least privilege, audit and session controls |
| Backup or search copy | Deleted data survives unexpectedly | Retention policy, deletion propagation and controlled backup lifecycle |
| Shared worker capacity | One tenant floods all channels | Per-tenant admission/rate/concurrency budgets |

These are design considerations, not a full security audit or an assurance of regulatory compliance. Confirm legal/data-residency requirements with the appropriate owners rather than inventing them.

## Privacy and operational access

Collect only necessary fields. Classify identifiers, content and credentials. Specify retention and deletion for primary records, derived indexes, caches, logs and backups. Immediate removal from every immutable backup may conflict with backup retention; explain the actual deletion policy rather than promising impossible instant erasure.

Encrypt sensitive traffic and storage as appropriate, but encryption does not stop an authorized application from leaking plaintext. Restrict support/admin access, record privileged actions and use safe telemetry. Never paste real customer data or secrets into an interview example.

## Practice question

**SD-SEC-01 — Intermediate; backend/AI/full-stack; design; 0–2 years.** A backend filters by tenant but caches by resource ID only. Is tenant isolation proven?

<details><summary>Expected answer and followups</summary>

No. A cache hit may bypass the protected database query and return another tenant's result. Scope cache keys and authorization decisions correctly and test hit/miss paths. Followup: how does permission revocation affect cached results? Define freshness and invalidation behavior. Mistake: reviewing only the database path while ignoring caches, exports, logs and workers.

</details>

## Checklist

- [ ] Identity and object authorization are separate checks.
- [ ] Data and credentials have explicit trust boundaries.
- [ ] Abuse, cross-tenant access and revoked permissions are tested.
- [ ] Retention/deletion includes derived and operational copies.

## References

- [OWASP — authorization guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authorization_Cheat_Sheet.html)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
