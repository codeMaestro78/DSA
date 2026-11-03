# Research papers: separate mechanism, evidence and extension

Read a paper to understand its question, proposed mechanism, experimental controls and limits. An abstract establishes what its authors claim; it does not establish that you reproduced a result or that the method dominates every modern workload.

## Curated routes and limits

| Primary paper | Learn | Questions to ask |
|---|---|---|
| [Attention Is All You Need](https://arxiv.org/abs/1706.03762) — Vaswani et al. | Transformer attention architecture | What are tensor shapes, masking and computation costs? Which training/evaluation setting supports each result? |
| [Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401) — Lewis et al. | Combining retrieved nonparametric context with generation | Which retriever/index, dataset and conditioning formulation are evaluated? How does an application pipeline differ? |
| [Raft extended paper](https://raft.github.io/raft.pdf) — Ongaro and Ousterhout | Replicated log/consensus mechanisms | What safety invariant survives a leader change? Which failures/majorities are assumed? |


## Use the material actively

Write a one-page record: hypothesis, baseline, data/split, algorithm, implementation prerequisites, metric, confounders and open questions. Reading, implementing a toy component, reproducing the reported experiment and extending the method are different claims. The author/version dates remain in the source register; no current benchmark ranking or proprietary-lab process is inferred.

## Practice — RESOURCE-P7-07

You implement a small attention function and match a NumPy oracle. Have you reproduced the Transformer paper?

<details><summary>Expected reasoning — attempt first</summary>

No. That establishes a bounded component check. Reproducing reported results would require the relevant model, data/preprocessing, training budget, optimizer/configuration and evaluation protocol, with comparability and resource limits stated. The small implementation remains valuable evidence when described precisely.

Followup: name one artifact you would retain and one unsupported claim you would refuse to make. Choose the smallest exercise that tests transfer instead of collecting another unrelated resource.

</details>

## Provenance

[Scoped register](PHASE7_SOURCES.md) records inspected pages and explicit access limits. Most original teaching and assessment claims are **[INFERRED]**; references establish their stated content, not your performance. [Research methodology](../RESEARCH_METHODOLOGY.md) · [Parent index](README.md).

Last reviewed: **2026-11-03**. No purchase, enrollment, platform submission or external account action was performed.
