# Tokenization and text boundaries

## What a token is

A tokenizer converts text into IDs from a model-specific vocabulary and decodes IDs back to text under its rules. Tokens may represent words, subwords, bytes or other units. A character count, word count and token count are different quantities. Token boundaries can change with whitespace, punctuation, language and the tokenizer version.

Byte-pair encoding repeatedly merges frequent units in training data to build a vocabulary. The subword paper is a primary historical explanation, not proof that every modern model uses the same tokenizer. Special conversation/control tokens and message formatting add accounting details that plain-text estimates may miss.

## Worked boundary example

Python's string `a😀b` has three Unicode code points, while a UTF-16 representation uses four code units. A tokenizer may produce yet another number of IDs. Therefore a chunker returning offsets [0,2) in Python selects `a😀`; the same integers are not automatically browser UTF-16 offsets or model-token offsets.

The [local chunk helper](../validation/ai/core_examples.py) deliberately uses Python code-point offsets. For `abcdefghij`, size 4 / overlap 1 produces [0,4), [3,7), [6,10). It preserves source slices but is **not** a production tokenizer-aware chunker. Offset representation must be part of an ingestion/citation contract.

## Budgeting correctly

Count with the exact target tokenizer or a provider-supported counting mechanism when available. Reserve output, system instructions, tool definitions, retrieved text and history. A preliminary character estimate can be useful for planning, but it cannot guarantee that a request fits. State how overflow is handled before sending a request.

For a multi-model application, count separately where tokenizers or protocol overhead differ. Cache tokenization only with the tokenizer/version and relevant text identity in the key. Changing normalization can invalidate source offsets, so retain mapping to the original document.

## Edge cases and tradeoffs

OCR introduces unusual spacing and characters; code and identifiers may fragment differently from prose. Splitting a table or sentence solely at a fixed count can remove context even when the budget fits. Conversely, preserving every paragraph intact can create oversized chunks. Combine semantic boundaries with a hard token ceiling and source-span tracking.

A tokenizer's ability to encode arbitrary text does not mean the model understands every language equally well. Do not infer multilingual quality from vocabulary coverage. Test actual tasks and slices, including punctuation, emoji, mixed scripts and long identifiers.

## Interview practice — P3-AI-02

**Question:** Why can a 4,000-character request exceed one model budget while fitting another?

- **Category/topic:** AI engineering / Tokenization and text boundaries.
- **Difficulty:** Intermediate, with advanced followups.
- **Target role/round/level:** AI, RAG, full-stack and ML engineer; technical/design discussion; early-career foundation with role-specific depth.
- **Company/evidence:** Company-neutral original exercise [INFERRED]. No employer question or frequency claim.
- **Evaluates:** Mechanism, assumptions, correctness boundary, failure reasoning and tradeoffs.
- **Related:** [RAG evaluation](RAG_Evaluation.md), [agent architecture](Agent_Architecture.md), [source register](PHASE3_SOURCES.md).

<details><summary>Expected answer, followup and mistakes — attempt first</summary>

**Expected answer and explanation:** Tokenization and message overhead are model-specific. Count the actual request, reserve output/tool/history budgets, and define truncation or rejection. Character estimates are insufficient.

**Followup:** How would you preserve citation offsets when normalizing OCR whitespace?

**Common mistake:** Treating words, Unicode code points, UTF-16 units and model tokens as interchangeable.

**Strong evidence:** A concrete trace or counterexample, a named authority/contract and a practical validation plan. Do not claim a real deployment or measurement when the example is hypothetical.

</details>

## Preparation checklist

- [ ] Explain the mechanism in plain language, then state its assumptions.
- [ ] Reproduce the worked trace or shape/budget argument from a blank page.
- [ ] Identify one failure that the mechanism does not prevent.
- [ ] Attempt the question before reading the expected reasoning.
- [ ] Separate local arithmetic/contract checks from task-quality or production claims.

## References

- [Sennrich et al.: subword tokenization](https://arxiv.org/abs/1508.07909) — scope and metadata in [the register](PHASE3_SOURCES.md).

## Last verified

2026-11-02: explanation and primary-source scope reviewed. Examples are original teaching material. Only functions explicitly listed in [local validation](../validation/ai/README.md) were executed; framework/provider integrations, real embedding quality and production behavior were not tested.
