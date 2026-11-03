# Phase 3 study guide

## Goal and entry requirements

Learn to build and explain AI systems whose quality, permissions, failure behavior and cost can be assessed. This is an original [INFERRED] study sequence for an early-career engineer; it is not a guaranteed interview syllabus or an employer's hidden rubric.

Begin after you can implement/test a small function, explain an API/database transaction, reason about a timeout and distinguish authentication from authorization. If these are uncertain, revisit [Phase 2](PHASE_2_STUDY_GUIDE.md). You do not need to master distributed training or GPU kernels before learning applied AI engineering.

## First session

1. Read [model evaluation](07-AI-ML-Deep-Learning/Model_Evaluation.md) and [RAG architecture](08-LLM-RAG-and-Agent-Engineering/RAG_Architecture.md).
2. Specify five small answerable questions and two genuinely unanswerable questions about a permitted document set. These are a learning fixture, not a production benchmark.
3. Write what counts as a relevant source, a supported answer, an aligned citation and a valid abstention. Explain how these can fail separately.
4. Trace an authenticated request through retrieval, model context and answer validation. Mark every place where private text could leave its authorized scope.
5. Record what you reasoned through and what you actually executed. Open answer disclosures only after attempting the chapter exercise.

## Applied-AI progression

| Step | Read/practice | Evidence to produce |
|---|---|---|
| 1 | Probability, data splits, loss and model evaluation | Explain a metric denominator, a leakage example and a held-out boundary |
| 2 | Neural nets, attention, transformers and tokenization | Trace tensor shapes, causal masking and token versus character accounting |
| 3 | Embeddings, chunking, lexical/dense/vector retrieval | Preserve source offsets and explain a similarity/relevance mismatch |
| 4 | Hybrid search, reranking and RAG evaluation | Work one rank-fusion/metric example and diagnose missing candidates |
| 5 | Context budget, prompting and structured outputs | Separate schema validity, task correctness and authority |
| 6 | Tool calling, deterministic workflows and bounded agents | Validate a proposed action; trace timeout, duplicate, cancel and budget exhaustion |
| 7 | Security, source permissions and memory | Explain prompt-injection limits, revoked access and deletion propagation |
| 8 | Observability, inference and cost | Distinguish first useful output, completion latency, actual usage and hypothetical estimates |
| 9 | AI system-design exercises | Draw one normal flow and one crash/recovery path with state ownership |
| 10 | Role-specific practice and project explanation | Defend a real decision using implementation/evaluation evidence and its limits |

Use one or more 60–90 minute sessions per step, as needed. These are suggested study budgets, not company durations. Keep two weekly DSA sessions or an equivalent amount of retrieval practice while advancing the AI track. Repeat weak prerequisites rather than treating a completed reading as a passed gate.

## Choose a role branch

- **AI engineer:** [roadmap](02-Role-Based-Preparation/AI-Engineer/AI_Engineer_Interview_Roadmap.md), backend contracts, evaluation and reliable tool/workflow execution.
- **RAG engineer:** [roadmap](02-Role-Based-Preparation/RAG-Engineer/RAG_Interview_Roadmap.md), ingestion/relevance/permissions, ranking and evidence-backed answers.
- **Full-stack AI:** [roadmap](02-Role-Based-Preparation/Full-Stack-AI-Engineer/Full_Stack_AI_Roadmap.md), UI state, streaming/final status, session/resource authorization and server recovery.
- **FDE:** [roadmap](02-Role-Based-Preparation/Forward-Deployed-Engineer/FDE_Interview_Roadmap.md), discovery, bounded prototypes, integrations and customer explanation. An FDE title alone does not establish early-career eligibility; inspect the actual posting.
- **ML engineer:** [roadmap](02-Role-Based-Preparation/ML-Engineer/ML_Engineer_Interview_Roadmap.md), mathematics, valid experiments, training and serving. Add deeper optimization/distributed/GPU topics when the target role requires them.

[Role comparison](02-Role-Based-Preparation/Role_Comparison.md) distinguishes applied AI, ML, research/applied-scientist work and customer engineering without assuming every employer uses identical titles. Pick one primary branch and one adjacent branch.

## Research/training extension

After basic evaluation and linear algebra, study calculus/optimization, classical models, backpropagation, architecture choices and a reproducible experiment. Then add fine-tuning, PEFT, quantization, distributed training and RL where relevant. Understand each method's objective, data assumptions and failure modes; do not assume that knowing an API is equivalent to understanding training.

PyTorch and TensorFlow chapters include framework-oriented learning examples, but the current local validation environment lacks those frameworks. [ML validation](validation/ml/README.md) states which NumPy examples actually ran and which snippets received only syntax/static review. Do not report unexecuted framework training as completed experimentation.

## Practice and evidence

Use [the AI design template](14-Interview-Preparation-Templates/AI_System_Design_Template.md), [project followups](11-Project-Deep-Dive/AI_Project_Interview.md), [scorecards](00-Interview-Fundamentals/Interview_Evaluation_Rubrics.md) and [progress tracker](PROGRESS_TRACKER.md). Each attempt should state assumptions, evidence, a counterexample/failure path and the next check.

The [validation index](validation/README.md) separates provider-neutral control/metric tests, NumPy numerical tests and AI architecture arithmetic. These checks do not establish a real model's answer quality, a framework integration, security of a live application or GPU throughput.

## Exit gate and remaining phases

You can independently explain one evaluated AI request, one permission change, one failed tool action, one metric edge case and one architecture tradeoff. You can identify what your project has not proved. Use repeated unfamiliar prompts and evidence rather than a fixed chapter count.

Full employer dossiers, larger company/role question banks, complete role mocks and final whole-library QA remain phases 4–7. This phase supplies teaching and focused exercises; it does not mark those later deliverables complete.

## References and last verified

2026-11-02: editorial sequence matched to Phase 3 content and source registers. Topic-level primary references are collected in [SOURCE_INDEX.md](SOURCE_INDEX.md). Candidate performance remains unassessed.
