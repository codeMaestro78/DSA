# AI architecture calculation checks

[check_architecture.py](check_architecture.py) validates illustrative token allocation, raw KV storage with variable sequence lengths, embedding/weight units, average concurrency and a simple critical path. It uses Python's standard library and makes no external calls.

Run from the workspace root:

```bash
python3 interview/validation/ai-design/check_architecture.py
```

2026-11-02, Python3.12: **21 checks passed**. Boundary cases include exact budget exhaustion, over-allocation, negative/noninteger inputs, no active sequences and additive variable-length cache storage. The grouped-query example uses KV heads, not query heads.

The formulas deliberately exclude model-specific layouts, padding, metadata, buffers and hardware overhead. Passing these checks is not proof of a real tokenizer's accounting, GPU capacity, scheduler behavior, provider billing or measured throughput. A context provider may impose different shared/separate limits; use its actual contract before applying the arithmetic.

Related: [AI design framework](../../04-System-Design/AI-System-Design/AI_System_Design_Framework.md), [embedding service](../../04-System-Design/AI-System-Design/Embedding_Service.md), [GPU infrastructure](../../04-System-Design/AI-System-Design/GPU_Inference_Infrastructure.md), [validation index](../README.md).
