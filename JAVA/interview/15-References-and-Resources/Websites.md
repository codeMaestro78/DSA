# Websites: primary references versus preparation commentary

Use official documentation for specific language/protocol/product semantics and tutorials for learning sequence. A familiar-looking search result or a preparation blog is not an independent specification. Track the claim and version rather than collecting links with no purpose.

## Curated routes and limits

| Site | Appropriate use | Limit |
|---|---|---|
| [PostgreSQL18 docs](https://www.postgresql.org/docs/18/) | SQL/transaction mechanism reference | Version18 documentation does not prove execution on that version |
| [Python3.12 tutorial](https://docs.python.org/3.12/tutorial/datastructures.html) | Collection/object basics | Other Python implementations/versions can differ in runtime details |
| [TypeScript handbook](https://www.typescriptlang.org/docs/handbook/2/narrowing.html) | Static narrowing and modeling | Does not validate external JSON or resource authority |
| [MIT OCW6.006](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/) | Algorithm course notes and exercises | A historical course is not a current employer question bank |


## Use the material actively

Before using advice, identify whether it is normative semantics, a tutorial simplification, a benchmark or an author’s opinion. Reproduce a small example with the chosen runtime when execution matters. An access date is not a page update date. Use [the central registry](../SOURCE_INDEX.md) for provenance and preserve inaccessible/rejected leads separately.

## Practice — RESOURCE-P7-03

A tutorial calls dictionary access O(1). What should your interview answer add?

<details><summary>Expected reasoning — attempt first</summary>

State the operation, key/hash cost and expected-versus-worst-case model. Collisions, key length and resizing can change the cost or individual latency. A language-wide slogan is less precise than the mechanism needed for the actual input contract.

Followup: name one artifact you would retain and one unsupported claim you would refuse to make. Choose the smallest exercise that tests transfer instead of collecting another unrelated resource.

</details>

## Provenance

[Scoped register](PHASE7_SOURCES.md) records inspected pages and explicit access limits. Most original teaching and assessment claims are **[INFERRED]**; references establish their stated content, not your performance. [Research methodology](../RESEARCH_METHODOLOGY.md) · [Parent index](README.md).

Last reviewed: **2026-11-03**. No purchase, enrollment, platform submission or external account action was performed.
