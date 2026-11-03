# Data Structures, Algorithms & Interview Preparation

A personal Java practice repository and a structured software-engineering and AI interview knowledge base. The code covers algorithms, data structures, competitive programming and object-oriented design; the interview library adds explanations, worked exercises and mock sessions.

## Start here

- [Interview preparation library](JAVA/interview/README.md)
- [Completed-library study guide](JAVA/interview/COMPLETION_GUIDE.md)
- [Master interview roadmap](JAVA/interview/MASTER_INTERVIEW_ROADMAP.md)
- [Java practice](JAVA/)
- [Competitive-programming runner](JAVA/CP_RUNNER.md)

## Interview preparation

The library contains all **410 originally requested Markdown paths**, with supporting material bringing it to **549 Markdown documents**. It covers interview fundamentals, company research, nine role tracks, DSA, system design, databases, cloud operations, ML/deep learning, LLM/RAG/agents, programming languages, behavioral practice and project discussions.

| Material | Entry point |
|---|---|
| DSA: 22 teaching areas and 44 original exercises | [DSA and coding](JAVA/interview/03-DSA-and-Coding/README.md) |
| 22 public coding, SQL and concurrency problems | [Public problem catalog](JAVA/interview/03-DSA-and-Coding/PUBLIC_PROBLEM_CATALOG.md) |
| 134 original questions with metadata and reviewed answers | [Question-bank index](JAVA/interview/12-Interview-Question-Bank/Question_Bank_Index.md) |
| Nine full mock sessions with separate candidate/interviewer packets | [Mock interviews](JAVA/interview/13-Mock-Interviews/README.md) |
| System design and hypothetical product-class cases | [System design](JAVA/interview/04-System-Design/README.md) |
| Role-specific learning sequences and focused mock variants | [Role preparation](JAVA/interview/02-Role-Based-Preparation/README.md) |
| Scoped company and regional evidence | [Company research](JAVA/interview/01-Company-Research/Company_Comparison.md) |
| Source provenance, delivery coverage and verification limits | [Source index](JAVA/interview/SOURCE_INDEX.md), [manifest](JAVA/interview/STRUCTURE_MANIFEST.md), [QA report](JAVA/interview/PHASE_7_QA_REPORT.md) |

Attempt exercises before opening their answers. Record assumptions, hints, tests actually run and the next improvement in the [progress tracker](JAVA/interview/PROGRESS_TRACKER.md). Original practice questions are company-neutral unless a source explicitly supports a narrower attribution; they are not leaked employer questions or hiring predictions.

## Java practice layout

| Path | Contents |
|---|---|
| [JAVA/DataStructure](JAVA/DataStructure/) | Data-structure implementations and exercises |
| [JAVA/Searching](JAVA/Searching/), [JAVA/Sorting](JAVA/Sorting/) | Searching and sorting practice |
| [JAVA/Codeforces](JAVA/Codeforces/) | Competitive-programming solutions |
| [JAVA/Leetcode_Quest](JAVA/Leetcode_Quest/), [JAVA/Leetcode_Solve](JAVA/Leetcode_Solve/) | Coding-problem practice |
| [JAVA/StriverDsaSheet](JAVA/StriverDsaSheet/), [JAVA/StriverLecture](JAVA/StriverLecture/) | DSA study exercises |
| [JAVA/OOPS](JAVA/OOPS/), [JAVA/solid_ecommerce](JAVA/solid_ecommerce/) | Object-oriented design practice |
| [Neetcode](Neetcode/), [roadmap](roadmap/) | Additional practice and study material |

These are learning solutions with individual contracts and histories. The interview-library validation does not audit every existing solution in the repository.

## Run a Java exercise

From the repository root, with a suitable JDK installed:

```bash
cd JAVA
bash cp-run.sh Codeforces/Q200B_Drinks.java
```

Put the test input in `JAVA/input.txt` before running. See [the runner guide](JAVA/CP_RUNNER.md) for custom inputs, interactive input and test folders. The runner compiles the selected source into its local build directory; the repository is not presented as one application with a single build target.

## Validate the interview examples

From the repository root:

```bash
cd JAVA
python3 -B interview/validation/run_checks.py --output /tmp/interview-checks.json
```

Requires Python with NumPy and Java compilation/runtime tools already installed. The runner performs no dependency downloads or live provider calls. See [validation instructions](JAVA/interview/validation/README.md) for individual commands and environment details.

The latest recorded local pass has **18/18 suite and compilation steps passing**. This establishes the documented fixture and structural properties, not universal correctness or production readiness. Browser/Mermaid rendering and native SQL reruns remain unverified; their limits are explicit in the [QA report](JAVA/interview/PHASE_7_QA_REPORT.md). Company facts and tool policies must be refreshed for the actual opportunity.

## Scope and provenance

The interview library was completed and reviewed on **3 November 2026**; individual sources retain their own access/version dates. Worked workloads and customer scenarios are labeled as hypothetical. No personal accomplishment, deployment, benchmark or candidate score is supplied where it was not observed.
