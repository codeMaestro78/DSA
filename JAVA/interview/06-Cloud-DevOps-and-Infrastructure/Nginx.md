# NGINX: proxy boundaries, buffering and deadlines

## Learning goal and scope

A reverse proxy accepts client traffic and forwards requests to upstream services. It can terminate TLS, route, buffer and enforce selected limits. Proxy success and application success are different facts: a 502 indicates a gateway/upstream problem class, but not a complete root cause or proof an application transaction failed.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Trace client → proxy → upstream with explicit host/path/header handling and trust boundaries. Forwarded identity/address headers should be accepted only from trusted proxy paths. Define connect, send and read timeout meanings against a total request budget; an inactivity timeout is not automatically a wall-clock deadline. Buffering can protect an upstream from slow clients but delay token/event delivery, so streaming routes need an intentional tested policy.

## Worked example

Fictional trace: the application logs token production every second, but the client sees a burst after completion. Inspect proxy/application buffering, compression and flush behavior before blaming model inference time. Another case has a proxy read timeout after the upstream committed a job: the user sees an error but creation may have succeeded. Retry requires stable application identity; changing a proxy timeout alone cannot enforce deduplication.

## Tradeoffs, failure and access boundaries

Caching private responses must preserve authorization and tenant identity; a cache-key omission can expose one tenant to another. Do not apply a global buffering/retry change merely to fix one endpoint. Bound request/body/connection resources, preserve useful correlation IDs and avoid logging authorization headers or private payloads.

## Attempt first — OPS7-NGINX

An upload endpoint works but an event stream appears frozen. Which measurements and route-specific decisions would you request?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

Compare upstream emission timestamps, proxy receipt/flush and client arrival under the same request ID. Check buffering and any compression/intermediary layer, then test a route-specific streaming policy with disconnect/backpressure behavior. A header alone does not prove all intermediaries flush; no live configuration is validated by this reasoning.

**Followups:** How can an upstream retry duplicate work? Which source may set trusted forwarded headers?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [NGINX HTTP proxy module](https://nginx.org/en/docs/http/ngx_http_proxy_module.html) — NGINX; rolling documentation; scope: Proxy buffering/timeouts and directives; no config loaded.
- [HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html) — IETF RFC9110;June2022; scope: HTTP methods/retry semantics; no provider-specific cancellation guarantee.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
