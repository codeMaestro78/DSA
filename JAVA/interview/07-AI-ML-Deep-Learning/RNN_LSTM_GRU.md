# RNN, LSTM and GRU

## Shared transition

A simple RNN updates h_t=tanh(Wx*x_t+Wh*h_(t-1)+b). x_t:(d,), h_t:(h,), Wx:(h, d), Wh:(h, h). Parameters are reused over time. Backprop through the unfolded sequence repeatedly multiplies Jacobians, which can shrink or amplify gradients. Truncation limits the gradient horizon; it does not necessarily reset the semantic state.

LSTM maintains a gated cell state; GRU uses update/reset mechanisms with a different state structure. Gates can improve dependency learning in some regimes, but do not guarantee perfect memory. State reset between independent sequences is part of correctness.

## Worked batch

Embeddings(B, T, d) produce hidden states (B, T, h). For sequence classification, select each example's last valid state rather than blindly the final padded position. Token classification needs a mask for padded targets. A bidirectional model uses future tokens and cannot be substituted unchanged for strictly causal online prediction.

## State boundaries

Persisting hidden state across users without a reset/ownership policy can let one user's data influence another's output. Future information in an offline bidirectional model can invalidate a claimed online evaluation. These are task-boundary failures, not solved by choosing a different cell name.

## Complexity

A simple dense RNN costs O(T*(dh+h²)) per sequence, excluding batch scaling; gates change constants. Training retains time-step state. Recurrence supports incremental state, while Transformers parallelize training differently. Compare workload and hardware rather than declaring either universally faster.

## Interview exercise — attempt first

**ID:** ML3-RNN_LSTM_GRU. **Category:** ML/DL / RNN, LSTM and GRU. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why can gathering the last padded position be incorrect?

<details><summary>Expected reasoning, followup and common mistake</summary>

It may represent padding, not the final valid token. Use lengths/packing or correct gathering and ensure padding does not contaminate state under the implementation.

**Followup:** How would a state-reset mistake affect independent users?

**Common mistake:** Reading a padded final state or allowing future information in an online task.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Deep Learning chapter 10 / Goodfellow, Bengio and Courville](https://www.deeplearningbook.org/contents/rnn.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
