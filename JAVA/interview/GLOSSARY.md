# Working glossary

Definitions below are concise study aids. Technical sources appear in [the source index](SOURCE_INDEX.md); these definitions do not describe a company's interview rubric.

| Term | Meaning | Interview distinction to explain |
|---|---|---|
| Invariant | Property preserved across algorithm steps or state transitions | State why initialization and each transition preserve it |
| Big O | Asymptotic upper bound on growth | State input variables, operation model and worst/average/amortized scope |
| Amortized cost | Cost per operation bounded across a sequence | Does not require a probability distribution over inputs |
| Idempotence | Repeating an operation has the same intended effect as performing it once | Repeated responses or incidental logs need not be identical |
| Transaction | Group of database operations with declared atomicity and isolation behavior | A transaction alone does not specify the isolation level |
| Isolation | Constraints on effects of concurrent transactions | Name engine and level before promising behavior |
| Replication | Maintaining copies of data | Replication alone is not an independent backup |
| Partitioning/sharding | Dividing data into subsets; sharding distributes them across nodes | Explain routing, ownership and cross-shard work |
| SLI / SLO | Service-level indicator / target for that indicator | Tie to user outcomes and a measurement window |
| p95 latency | Value at or below which roughly 95% of sampled latencies fall | State population, window and failures included |
| Backpressure | Signaling or restricting demand when a downstream stage is overloaded | Distinguish bounded queues from silently accumulating work |
| RAG | Retrieval-augmented generation: condition generation on retrieved information | Retrieval and generation need separate evaluation |
| Embedding | Learned numeric representation of an input | Similarity is model- and task-dependent |
| ANN | Approximate nearest-neighbor search | Measure recall/latency under realistic filtering and load |
| BM25 | Lexical ranking family using term statistics and length normalization | Lexical matching complements semantic retrieval |
| Reranker | Model or rule that reorders a candidate set | Cannot recover a relevant item missing from that set |
| Recall@k | Fraction of known relevant items retrieved among the first k | Requires relevance labels and a defined denominator |
| MRR | Mean reciprocal rank of the first relevant result | Focuses on the earliest relevant result |
| NDCG | Normalized discounted cumulative gain | Supports graded relevance and position discount |
| Faithfulness | Degree to which an answer is supported by supplied evidence | Does not by itself establish external factual truth |
| Data leakage | Information unavailable at prediction time enters model building/evaluation | Distinguish from private data exfiltration |
| Precision/recall | True positives divided by predicted positives / actual positives | State threshold, class and averaging method |
| Overfitting | Fitting training-specific patterns that generalize poorly | Diagnose using a suitable held-out evaluation |
| Agent | A system where model decisions help choose actions over a sequence | Framework choice does not prove reliability |
| MCP | Model Context Protocol for connecting model applications to tools/context | Protocol integration does not replace application authorization |
| Prompt injection | Untrusted content attempts to redirect a model application's behavior | Treat retrieved content/tool output as untrusted |
| FDE | Forward deployed engineer | Confirm the company's actual title and responsibilities |
| STAR | Situation, task, action, result | Use real experience and distinguish personal/team contributions |

## Last verified

2026-11-02: editorial glossary; deeper derivations and examples are planned in topic documents.
