# Phase 3 ML sources

Every accepted source below was directly opened on **2026-11-02**. Reliability is high for the documented/derived mechanism, not universal quality, benchmark rankings or employer requirements. Dates identify publication where known; access is not recent authorship. Teaching examples and prompts are original [INFERRED] synthesis.

| ID | Title / author or organization | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| ML3-S01 | Common pitfalls / scikit-learn | [Source](https://scikit-learn.org/stable/common_pitfalls.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Leakage and preprocessing |
| ML3-S02 | Metrics and scoring / scikit-learn | [Source](https://scikit-learn.org/stable/modules/model_evaluation.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Metric definitions |
| ML3-S03 | Probability calibration / scikit-learn | [Source](https://scikit-learn.org/stable/modules/calibration.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Probability reliability |
| ML3-S04 | Linear models / scikit-learn | [Source](https://scikit-learn.org/stable/modules/linear_model.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Linear/logistic objectives |
| ML3-S05 | Decision trees / scikit-learn | [Source](https://scikit-learn.org/stable/modules/tree.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Tree split mechanisms |
| ML3-S06 | Ensembles / scikit-learn | [Source](https://scikit-learn.org/stable/modules/ensemble.html) | Rolling docs; date unstated | 2026-11-02 | Official docs / high for stated mechanism | Bagging/boosting |
| ML3-S07 | Deep Learning chapter 2 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/linear_algebra.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Shapes/rank foundations |
| ML3-S08 | Deep Learning chapter 3 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/prob.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Probability foundations |
| ML3-S09 | Deep Learning chapter 4 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/numerical.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Gradients/numerical stability |
| ML3-S10 | Deep Learning chapter 5 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/ml.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Statistical learning |
| ML3-S11 | Deep Learning chapter 6 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/mlp.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Feedforward nets/chain rule |
| ML3-S12 | Deep Learning chapter 7 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/regularization.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Regularization |
| ML3-S13 | Deep Learning chapter 8 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/optimization.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Optimization |
| ML3-S14 | Deep Learning chapter 9 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/convnets.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Convolution mechanisms |
| ML3-S15 | Deep Learning chapter 10 / Goodfellow, Bengio and Courville | [Source](https://www.deeplearningbook.org/contents/rnn.html) | 2016 book | 2026-11-02 | Author-hosted textbook / high for stated mechanism | Recurrent mechanisms |
| ML3-S16 | Attention Is All You Need / Vaswani et al. | [Source](https://arxiv.org/abs/1706.03762) | 2017-06-12; inspected revision 2023-08-02 | 2026-11-02 | Original paper / high for stated mechanism | Original Transformer; not every LLM |
| ML3-S17 | LoRA / Hu et al. | [Source](https://arxiv.org/abs/2106.09685) | 2021-06-17; revision 2021-10-16 | 2026-11-02 | Original paper / high for stated mechanism | Low-rank adaptation |
| ML3-S18 | QLoRA / Dettmers et al. | [Source](https://arxiv.org/abs/2305.14314) | 2023-05-23 | 2026-11-02 | Original paper / high for stated mechanism | Frozen quantized backbone plus adapters; historical results |
| ML3-S19 | Key concepts in RL / OpenAI Spinning Up | [Source](https://spinningup.openai.com/en/latest/spinningup/rl_intro.html) | Page date unstated; historical education | 2026-11-02 | Primary educational docs / high for stated mechanism | MDP/returns/values; not a current API |
| ML3-S20 | Getting started with DDP / PyTorch | [Source](https://docs.pytorch.org/tutorials/intermediate/ddp_tutorial.html) | Rolling tutorial; date unstated | 2026-11-02 | Official tutorial / high for stated mechanism | Distributed training concepts; unexecuted locally |
| ML3-S21 | Optimizing model parameters / PyTorch | [Source](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html) | Rolling tutorial; date unstated | 2026-11-02 | Official tutorial / high for stated mechanism | Loop/autograd; unexecuted locally |
| ML3-S22 | Writing a training loop from scratch / François Chollet, TensorFlow | [Source](https://www.tensorflow.org/guide/keras/writing_a_training_loop_from_scratch) | Date unstated | 2026-11-02 | Official tutorial / high for stated mechanism | GradientTape loop; unexecuted locally |
| ML3-S23 | numpy.linalg.svd / NumPy | [Source](https://numpy.org/doc/stable/reference/generated/numpy.linalg.svd.html) | Rolling docs; date unstated | 2026-11-02 | Official API docs / high for stated mechanism | SVD shapes/convention |

## Access and execution limits

A current DDP API redirect target was inaccessible; it was not treated as inspected. The accessible official tutorial supports the distributed-training explanation. The Sutton/Barto book site could not be fetched; the accessible primary Spinning Up educational page supports the RL introduction instead.

NumPy is installed. PyTorch, TensorFlow and scikit-learn are unavailable locally; no installation or framework training was performed. Framework snippets receive Python AST syntax checks only. The NumPy routines use basic APIs, with runtime version recorded in [validation](../validation/ml/README.md); rolling docs are not asserted to match that version.

## Navigation and last verified

[Domain index](README.md), [ML role](../02-Role-Based-Preparation/ML-Engineer/README.md), [central registry](../SOURCE_INDEX.md).

2026-11-02: 23 primary sources inspected; reused URLs should be deduplicated centrally.
