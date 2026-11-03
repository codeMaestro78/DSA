# Networking: trace a request before changing the system

## Learning goal and scope

A remote API call depends on name resolution, routing, transport, TLS and application semantics. Separate connection establishment from request processing and response transfer. TCP offers an ordered byte stream within a connection, not application record boundaries or proof that a remote business operation committed.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Describe the path in order: resolve hostname, select route/address, establish a TCP connection, negotiate TLS when used, send HTTP and wait for application output. Reuse/pooling can eliminate some steps for subsequent requests. A connection timeout, refused connection, certificate failure, HTTP error and slow body are evidence at different boundaries. Keep client and server clocks/correlation uncertainty explicit.

## Worked example

Fictional trace: a new endpoint connects by TCP but TLS fails only for the public hostname. Backend health checks pass because they probe a different internal address without that certificate path. Compare requested hostname, certificate identity/trust, endpoint and termination layer. Changing database indexes cannot repair a certificate mismatch. Another fixture has a server commit before the response is lost: reliable transport does not establish to the client whether the commit occurred.

## Tradeoffs, failure and access boundaries

Network retries can multiply load or duplicate an effect. An end-to-end timeout budget must cover connection and application work; a per-read inactivity timeout is not automatically a total deadline. Avoid weakening TLS verification to hide a name/trust error. Restrict diagnostic captures and redact headers/tokens.

## Attempt first — OPS7-NETWORKING

A request has 20 ms DNS, 30 ms connect, 40 ms TLS and 200 ms server/body time. What is the serial total, and what can a reused connection change?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

The stipulated serial total is 290 ms. A warm reused connection can avoid a new DNS/connect/TLS sequence subject to actual client behavior, but it cannot remove the 200 ms application/body work by definition. This arithmetic is a fixture, not a measured percentile; parallelism, caching and retries can change an actual trace.

**Followups:** Why can a timeout leave an external effect unknown? How would you distinguish a reset from an HTTP 503?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Linux TCP manual](https://man7.org/linux/man-pages/man7/tcp.7.html) — Linux man-pages; page date not established; scope: Stream transport/socket semantics; no network experiment.
- [HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html) — IETF RFC9110;June2022; scope: HTTP methods/retry semantics; no provider-specific cancellation guarantee.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
