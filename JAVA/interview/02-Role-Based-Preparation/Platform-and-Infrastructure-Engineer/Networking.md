# Infrastructure networking: locate the failed boundary

## What to learn

Networking diagnosis requires matching symptoms to name resolution, route, transport, TLS and application boundaries. The fastest useful question is often “what completed successfully?” A service being reachable from one source does not prove reachability from every workload or authorized caller.

Target the 0–2-year core first; deeper distributed/runtime work is optional until a role requires it. All scenarios below are original **[INFERRED]**, company-neutral teaching fixtures.

## Method and mechanism

Draw source identity/network, destination name/address/port, intermediaries and return path. Distinguish refused, timeout, reset, TLS failure and HTTP status. Source/destination security policy, route and name mapping all matter. Load balancing changes destination selection, while application state and authorization need their own contracts. TCP preserves byte order within a connection but not an application transaction acknowledgment.

## Worked reasoning

Fixture: a backend works from an operator laptop through an approved access path but times out from a worker subnet. Compare worker DNS resolution, selected address, route/egress rules, destination policy and return path. Opening the service to the entire internet is not a targeted diagnostic repair. If TCP succeeds and HTTP 403 arrives, focus on the request/identity policy rather than describing it as an unreachable network.

## Tradeoffs and failure boundaries

Traces and packet captures can expose tokens/private content, so limit collection and access. Retries can amplify load during congestion and may repeat effects; use an end-to-end budget with a safe retry contract. Avoid claiming a DNS TTL forces all clients to refresh exactly on that deadline.

## Attempt first — P7-INFRA-NETWORKING

A client sees an HTTP 504 after submitting a durable job. Should it create a new job identity?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Not merely because of the gateway timeout. Query/retry the same logical operation identity according to the API contract and reconcile accepted state. The upstream may have committed before the response path failed. A new identity can create duplicate work/effects.

**Followups:** Which observation proves TLS completed? Why can a connection pool retain an old destination longer than DNS cache?

**Evidence to retain:** the stated contract, a concrete trace/counterexample and one next check. Distinguish an executed artifact from a proposed experiment or a hypothetical past experience.

</details>

## Sources and validation scope

- [Linux TCP manual](https://man7.org/linux/man-pages/man7/tcp.7.html) — Linux man-pages; date not established; Ordered byte-stream/socket concepts; no network experiment.
- [HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html) — IETF RFC9110;June2022; HTTP method/retry contracts; no automatic external-effect guarantee.

Primary pages directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). No SQL engine, scheduler, distributed runner, registry, model, serving system, host, cluster or production deployment was executed here. The examples are reviewed reasoning; product versions/adapters/defaults need confirmation before implementation.

[Role index](README.md) · [Role comparison](../Role_Comparison.md).
