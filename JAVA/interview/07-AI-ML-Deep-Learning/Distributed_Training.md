# Distributed training

## Partitioning choices

Data parallelism repeats model computation on different data and combines gradients. Tensor/model parallelism partitions model work; pipeline parallelism partitions stages. Parameter/optimizer sharding changes memory ownership. These are distinct mechanisms, not synonyms for adding GPUs.

## Batch and reduction

With W workers, B examples per worker and K equally sized accumulation microsteps, the nominal global batch is W*B*K if each worker sees the intended samples. Four workers, 32 examples and two microsteps give 256 examples per optimizer update. Repeated sample shards reduce diversity even when the count looks correct.

Averaging gradients of equal-sized local mean losses matches the global example mean. Unequal local batches need example-count weighting; otherwise a small batch can receive excessive weight. Stateful normalization, randomness and update/schedule differences can still prevent equivalence to one large batch.

## Communication and failure

Synchronization uses interconnect bandwidth and can overlap computation; speedup is not linear by assumption. Divergent collective control flow can hang or fail a job. Distributed sampling, epoch shuffling and validation aggregation are correctness concerns.

A resumption checkpoint may need model, optimizer, schedule, step, random/data position and configuration. A model-only inference export has a narrower purpose. Writing checkpoints alone does not prove recovery from rank failure or compatible world-size changes.

## Verification boundary

The official DDP tutorial was inspected. No torch, GPU, multi-process collectives, distributed checkpoint or throughput experiment was executed. The explanation is source-based reasoning, not local distributed-runtime proof. Begin with a correct single-device reference and a tiny numerical comparison before claiming scale-out correctness.

## Interview exercise — attempt first

**ID:** ML3-DISTRIBUTED_TRAINING. **Category:** ML/DL / Distributed training. **Difficulty:** Advanced. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can equal averaging of unequal local mean gradients be incorrect?

<details><summary>Expected reasoning, followup and common mistake</summary>

It weights workers rather than examples. Match the intended global objective using the declared reduction/count protocol; inspect last batches, padding and accumulation.

**Followup:** What happens when one worker takes a different collective control path?

**Common mistake:** Assuming linear speedup or equal worker averaging for unequal sample counts.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Getting started with DDP / PyTorch](https://docs.pytorch.org/tutorials/intermediate/ddp_tutorial.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
