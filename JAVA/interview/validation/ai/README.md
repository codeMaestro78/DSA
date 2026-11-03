# Provider-neutral AI teaching checks

## What is tested

[core_examples.py](core_examples.py) contains authored standard-library helpers. [check_examples.py](check_examples.py) runs deterministic examples, boundary checks and small generated/reference checks without any network, model or external tool execution.

Re-audited on **2026-11-03**, **Python 3.12.0**: **2,920 checks passed**, fixed seed 20261102.

```bash
python3 interview/validation/ai/check_examples.py
```

The runner sets `sys.dont_write_bytecode` before importing the helper, so no `.pyc` is written in the knowledge base.

## Contracts and complexity

| Helper | Contract | Time / auxiliary space, excluding input/output where stated |
|---|---|---|
| chunks | Python code-point spans [start,end); positive integer size and0≤overlap<size; empty text returns[] | O(C+M) time and returned storage, where C is total copied output characters and M chunks; heavy overlap increases C |
| cosine | Equal nonempty coordinate sequences representable with finite float norms; zero/nonfinite/unrepresentable norms rejected | O(D) time/O(D) temporary space in this implementation |
| rrf | Iterable of list/tuple ranking branches, unique string IDs per branch, integer smoothing in0..1,000,000; one-based ranks and ID tie-break | Expected O(R+UlogU) time/O(U) auxiliary space for R entries/U unique IDs under bounded-ID hashing/comparison assumptions |
| ranking_metrics | Sliceable unique ranking IDs, label mapping with finite grades 0..4, positive cutoff; unlisted IDs treated nonrelevant | O(N+QlogQ+1) time/O(N+Q+1) space for N ranked IDs/Q labels; full input duplicates checked before cutoff |
| bounded_loop | Precomputed proposals; exact schemas, read-only sum tool, server allowlist, numeric bounds, bounded steps, cooperative cancellation | O(S) step overhead plus bounded field/hash work; O(S) records; no hard wall-clock/tool-preemption guarantee |

Metric conventions: Precision@k uses denominator k even for short rankings. Recall and reciprocal rank use positive relevance labels. NDCG uses exponential gain 2^grade − 1 and log₂(rank+1) discount. With no positive labels, recall/reciprocal-rank/NDCG return None; precision remains defined. Averaging policy is an application choice that must report excluded cases. The NDCG helper does not claim equivalence to scikit-learn's linear-gain API convention.

## Evidence covered

- 2,268 generated chunk slice/coverage/final-offset assertions, plus explicit empty/Unicode/invalid-size examples.
- Synthetic cosine geometry plus300 generated vector pairs checked against an independent direct formula and symmetry.
- Known RRF order/scores, deterministic ties, duplicate branches, unsupported one-shot branch generators and the explicit smoothing bound. The helper rejects huge smoothing before float conversion; this is a teaching implementation limit, not a mathematical restriction of RRF.
- Exact graded-metric trace, short/empty/no-gold conventions, duplicate/invalid labels and all unique permutations of a three-item ranking.
- Tool allowlist/schema/numeric validation, replay-payload conflicts, finite-step stop and cooperative pre-execution cancellation.
- Huge-integer validation cases reject before unsafe float conversion; nonfinite and boolean argument cases are explicit.
- The published Python function excerpt in [Tool Calling](../../08-LLM-RAG-and-Agent-Engineering/Tool_Calling.md) is byte-for-byte a substring of the tested module source.

## What is not established

No tokenizer, embedding model, BM25, ANN index, RAG generation, framework integration, MCP wire implementation, LLM judge, provider retry/cancellation, live authorization or deployment was executed. The tool cache is in-memory per invocation; it does not provide durable deduplication. Cancellation is checked between precomputed steps; no running external operation is interrupted. The sum tool has no external side effects. These checks do not establish production security, real retrieval quality, latency or billing behavior.

## Navigation and last verified

[Canonical lessons](../../08-LLM-RAG-and-Agent-Engineering/README.md), [source metadata](../../08-LLM-RAG-and-Agent-Engineering/PHASE3_SOURCES.md).

2026-11-03:2,920 focused checks passed; no hosted/model calls.
