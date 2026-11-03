# Authentication: establish identity and manage its lifetime

## Overview

Authentication establishes who or what is presenting a credential. Authorization decides whether that principal may perform an action on a resource. A valid login does not authorize every order, tenant or administrative function. See [authorization](Authorization.md) after this chapter.

For interviews, explain the request flow and trust boundaries before naming token libraries. Examples below are conceptual designs; no identity provider or credential-processing implementation is introduced here.

## Sessions and bearer tokens

| Approach | Where authentication state lives | Main design questions |
|---|---|---|
| Opaque session cookie | Server stores the session; browser sends an unguessable identifier | Expiry, revocation, session rotation, CSRF and cookie protections |
| Signed access token | Resource server validates the token and claims under an agreed policy | Trusted issuer/key/algorithm, audience, expiry, revocation and scope |

A signed JWT is not encrypted merely because it is signed, and decoding it does not verify it. Validate the expected issuer, audience, permitted algorithm, signature and time constraints using a maintained library and trusted key source. Key rotation and replay/revocation policy belong in the design. Do not place secrets in readable claims. [JWT best current practices](https://www.rfc-editor.org/rfc/rfc8725.html)

Cookies and JWTs are not opposing categories: a cookie can transport a token. Discuss transport/storage separately from token format. Cookie flags `Secure`, `HttpOnly` and appropriate `SameSite` mitigate different risks; none alone solves every CSRF or XSS problem. Rotate session identifiers after authentication or privilege changes and define server-side invalidation. [Session management](https://cheatsheetseries.owasp.org/cheatsheets/Session_Management_Cheat_Sheet.html)

## Federated login: OAuth versus OIDC

OAuth is a delegated authorization framework; OpenID Connect adds an identity layer. A typical web login uses an authorization-code flow and validates the returned identity according to OIDC, including issuer/audience and applicable nonce checks. An ID token is intended for the client; do not assume it is a resource API's access token. [OIDC Core](https://openid.net/specs/openid-connect-core-1_0.html)

Use the provider's supported modern flow and libraries. Current OAuth security guidance requires protection against code interception/injection, including PKCE for public clients; it explicitly says the resource-owner-password credentials grant must not be used. Redirect URI, state/session binding and token handling need deliberate validation; PKCE does not eliminate every attack. [OAuth security BCP, including §2.4](https://www.rfc-editor.org/rfc/rfc9700.html)

## Passwords, recovery and abuse

If the application owns password authentication, store a suitable password hash via a maintained password-hashing library, not reversible encryption or a fast unsalted digest. Use TLS, login throttling and appropriate multifactor/re-authentication controls for sensitive actions. Recovery and email-change flows must preserve identity assurance; a weak reset endpoint can bypass a strong login. Avoid user enumeration through unnecessarily distinct responses. [OWASP authentication guidance](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)

Do not implement cryptography in an interview example unless that is the explicit exercise. Describe libraries, trust configuration and tests instead. Rate limiting and account lockout must balance abuse resistance with denial-of-service risks to legitimate users.

## Worked failure trace

1. A user signs in successfully and obtains a session.
2. An administrator revokes their tenant membership.
3. The session remains valid until expiry.
4. A document API trusts only the original token's tenant claim and returns a newly forbidden document.

The authentication can still be valid while authorization is stale. Define whether sensitive permissions are looked up, versioned, short-lived or invalidated, and what revocation latency is acceptable. Do not claim stateless tokens provide instantaneous revocation without an additional mechanism.

## Interview practice

[INFERRED] Beginner–Intermediate, backend/full-stack, technical/design round.

1. **AUTHN-01:** Why is decoding a JWT insufficient? What configuration must a verifier trust?
2. **AUTHN-02:** A logout deletes a browser cookie. Does that revoke a copied credential?

<details><summary>Expected reasoning and followups</summary>

1. Decoding only parses claims. The server must verify cryptographic authenticity and policy: expected issuer, audience, algorithm and time validity from trusted configuration/key distribution. Followup: what happens during key rotation or when the identity provider is unavailable?
2. Deleting one local copy does not invalidate a stolen session/token. Server-side session invalidation or a deliberately designed token-revocation/expiry mechanism is needed. Followup: what revocation guarantee does the API promise?

Mistakes: calling OAuth itself a login protocol without OIDC or provider-specific identity semantics; treating HttpOnly as an XSS cure; assuming a valid token authorizes a requested tenant. Related: [authorization](Authorization.md), [API contracts](API_Design.md).

</details>

## Checklist and last verified

- [ ] Separate principal identity, credential transport and permission decisions.
- [ ] Explain issuance, verification, expiry, rotation and revocation.
- [ ] Include recovery and sensitive-action reauthentication.
- [ ] State what is conceptual versus implemented/tested.

Last verified **2026-11-03**: RFC 9700 rechecked for its password-grant prohibition; other primary standard and OWASP source scopes retain their recorded dates. No auth runtime, penetration test or implementation-security claim is made. [Source metadata](PHASE2_SOURCES.md).
