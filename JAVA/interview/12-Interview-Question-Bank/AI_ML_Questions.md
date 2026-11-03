# AI ML Questions

Phase 5: 10 original, company-neutral [INFERRED] exercises. Difficulty, role and timing are editorial judgments, not employer evidence. Target early-career candidates (0–2 years); Advanced exercises are stretch practice and Expert is optional. Attempt each prompt before opening the review. Score contract, mechanism, worked reasoning and limitations separately; naming a tool alone is insufficient. Sources support technical mechanisms, not these invented scenarios or interview frequency.

[Source metadata](PHASE5_SDE_ML_SOURCES.md). Code validation and commands: [questions-core](../validation/questions-core/README.md).

<a id="qb-aiml-001"></a>

## QB-AIML-001 — A projection is not an inverse

| Field | Value |
|---|---|
| Category | Linear algebra |
| Difficulty | Beginner |
| Target roles | ML Engineer; AI Engineer |
| Round | Math reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Linear algebra: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-002 |
| Sources | [D2L linear algebra](https://d2l.ai/chapter_preliminaries/linear-algebra.html), [NumPy 2.4 least squares](https://numpy.org/doc/2.4/reference/generated/numpy.linalg.lstsq.html) |

### Prompt

Let u=(1,2) and v=(2,0). Compute the projection of v onto the line spanned by u and the residual. Explain what property makes this a least-squares solution and what changes if u=0.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The scalar coefficient is (uᵀv)/(uᵀu)=2/5. The projected vector is (0.4,0.8); residual v-proj=(1.6,-0.8). Their dot product with u is 1.6-1.6=0, so the residual is perpendicular to the line. For any coefficient a, minimize ||v-au||²; differentiation gives -2uᵀv+2a uᵀu=0. Positive uᵀu gives the unique coefficient. There is no need to construct an inverse matrix.

**Explanation:** The original vector decomposes into a component on the allowed line and an orthogonal component. Adding any on-line change to the projection increases squared error by the squared norm of that change, because the cross term vanishes. If u=0, the span contains only zero, so the projection vector is zero but every scalar coefficient represents it; division by uᵀu is invalid. For several dependent columns, least-squares predictions remain well defined even when coefficients are not unique. Numerical near-dependence also matters before exact rank deficiency occurs.

**Followups:** What changes for a non-orthonormal basis? Why is forming normal equations numerically risky?

**Common mistakes:** Confusing the scalar coefficient with the projected vector; dividing by zero; assuming every design matrix has an inverse.

</details>

<a id="qb-aiml-002"></a>

## QB-AIML-002 — A rare event reverses your intuition

| Field | Value |
|---|---|
| Category | Conditional probability |
| Difficulty | Beginner |
| Target roles | ML Engineer; AI Engineer |
| Round | Probability reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Conditional probability: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-003 |
| Sources | [D2L probability](https://d2l.ai/chapter_preliminaries/probability.html) |

### Prompt

A detector catches 90% of real positives and flags 5% of negatives. Prevalence is 1%. Compute P(positive|flag), using a hypothetical population of 10,000 independent cases. No medical interpretation is intended.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** There are 100 positives, of which 90 are flagged, and 9,900 negatives, of which 495 are flagged. Thus 585 cases are flagged, and 90/585≈0.1538 are actually positive. Algebraically, Bayes gives (0.90×0.01)/(0.90×0.01+0.05×0.99). A 90% true-positive rate does not imply 90% precision because the much larger negative population contributes most flags.

**Explanation:** The population calculation is an expectation-based illustration; an observed finite sample will vary. The relevant conditional probabilities have different denominators: sensitivity conditions on actual positives, whereas precision conditions on flagged cases. If prevalence changes to 10% with conditional rates fixed, precision becomes 0.09/(0.09+0.045)=2/3. That assumption may itself fail when the new population differs. Always state which distribution each rate describes. A practical decision also needs action costs and capacity; a posterior probability alone is not a complete policy.

**Followups:** How many flags would you expect at 0.1% prevalence? Which conditional rate does specificity measure?

**Common mistakes:** Ignoring the base rate; reversing the conditional; treating expected counts as exact observations.

</details>

<a id="qb-aiml-003"></a>

## QB-AIML-003 — Derive a regression gradient and check it

| Field | Value |
|---|---|
| Category | Optimization coding |
| Difficulty | Intermediate |
| Target roles | ML Engineer; AI Engineer |
| Round | NumPy coding |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Optimization coding: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-004 |
| Sources | [D2L linear regression](https://d2l.ai/chapter_linear-regression/linear-regression.html), [D2L computational graphs](https://d2l.ai/chapter_multilayer-perceptrons/backprop.html) |

### Prompt

For real finite X of shape (n,d), y shape (n,), w shape (d,), n,d>0, derive gradient of L=||Xw-y||²/(2n). Implement loss and gradient in float64 without broadcasting mistakes; test with finite differences. Accept integer/floating numeric arrays, excluding booleans, complex values, numeric strings and object arrays. Reject malformed conversion, shape/type violations and non-finite results with ValueError. Inputs and intermediates must remain representable.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Let r=Xw-y. Differentiating the sum of squared residuals gives ∇L=Xᵀr/n, shape (d,). The factor 2 cancels the denominator 2. A single row contributes rᵢxᵢ/n. For X=[[1,0],[1,1]], y=[1,3], w=[0,0], r=[-1,-3], giving loss 2.5 and gradient [-2,-1.5]. No optimizer or automatic differentiation is needed for this derivation.

**Explanation:** A finite-difference component uses [L(w+εeⱼ)-L(w-εeⱼ)]/(2ε). Compare absolute and relative tolerances on small well-scaled inputs: too small ε causes cancellation, too large ε gives truncation error. This check diagnoses many indexing/scaling mistakes but is not a symbolic proof. The implementation costs O(nd) time; beyond converted inputs it retains O(n+d) intermediate/output space. A y of shape (n,1) must be rejected, since subtracting it from an (n,) vector silently produces an (n,n) residual. Finite input values can still overflow intermediate arithmetic.

**Followups:** Add L2 regularization with an explicit normalization. What happens when columns are linearly dependent?

**Common mistakes:** Missing 1/n; the wrong transpose; accidental broadcasting; claiming finite differences prove global optimizer behavior.

**Validation:** The exact block is extracted and executed. Seeded loops compare analytic gradients with scalar-loop residuals and central differences; input and overflow cases are included.

<!-- phase5-code: regression.py -->
```python
import numpy as np

def regression_loss_gradient(X, y, w):
    def real_array(value):
        try:
            raw = np.asarray(value)
            if raw.dtype.kind not in "iuf":
                raise ValueError("real integer/floating arrays required")
            return raw.astype(np.float64)
        except (TypeError, ValueError, OverflowError) as error:
            raise ValueError("numeric array conversion failed") from error
    X, y, w = (real_array(value) for value in (X, y, w))
    if X.ndim != 2 or 0 in X.shape:
        raise ValueError("X must be nonempty (n,d)")
    n, d = X.shape
    if y.shape != (n,) or w.shape != (d,):
        raise ValueError("y/w shapes must be (n,) and (d,)")
    if not all(np.isfinite(a).all() for a in (X, y, w)):
        raise ValueError("finite inputs required")
    with np.errstate(over="ignore", invalid="ignore"):
        residual = X @ w - y
        loss = float((residual @ residual) / (2 * n))
        gradient = (X.T @ residual) / n
    if not np.isfinite(loss) or not np.isfinite(gradient).all():
        raise ValueError("non-finite arithmetic result")
    return loss, gradient
```

</details>

<a id="qb-aiml-004"></a>

## QB-AIML-004 — An oversized learning rate oscillates

| Field | Value |
|---|---|
| Category | Gradient descent stability |
| Difficulty | Intermediate |
| Target roles | ML Engineer; AI Engineer |
| Round | Optimization reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Gradient descent stability: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-005 |
| Sources | [D2L gradient descent](https://d2l.ai/chapter_optimization/gd.html) |

### Prompt

For f(w)=aw²/2, a>0, derive the exact gradient descent recurrence and the learning rates for convergence from arbitrary w₀. Explain why feature scaling can matter for a multidimensional quadratic.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The gradient is aw, so wₜ₊₁=(1-ηa)wₜ and wₜ=(1-ηa)ᵗw₀. Convergence to zero from every finite initial value requires |1-ηa|<1, equivalent to 0<η<2/a. At η=2/a, the sign flips without decay for nonzero w₀. At η=0, there is no progress. A value above 2/a diverges in magnitude except for the special initial value w₀=0.

**Explanation:** For a symmetric positive-definite quadratic Hessian H, each eigenvector component follows its own factor 1-ηλᵢ. A shared stable step requires η<2/λmax. When eigenvalues have a wide spread, a rate safe for steep directions may move slowly in shallow directions. Rescaling features can improve conditioning, although it changes the parameter coordinate system and does not guarantee every problem becomes easy. These exact conditions apply to deterministic quadratic descent; stochastic gradients, nonconvex objectives and adaptive optimizers need different analysis. Distinguish objective decrease from parameter convergence when minimizers are nonunique.

**Followups:** What happens at η=1/a in one dimension? What if H has a zero or negative eigenvalue?

**Common mistakes:** Claiming any small positive rate is fast; using this quadratic bound as a universal deep-network guarantee.

</details>

<a id="qb-aiml-005"></a>

## QB-AIML-005 — Softmax needs both algebra and numerics

| Field | Value |
|---|---|
| Category | Probability and loss |
| Difficulty | Intermediate |
| Target roles | ML Engineer; AI Engineer |
| Round | Math reasoning |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Probability and loss: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-006 |
| Sources | [D2L softmax regression](https://d2l.ai/chapter_linear-classification/softmax-regression.html) |

### Prompt

For logits z=(1000,1001,999) and class index 1, explain stable softmax and cross-entropy computation. Derive the logit gradient and state the conditions for the formulas.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Subtract m=max(z)=1001 before exponentiation. The exponentials become (e⁻¹,1,e⁻²); divide by their sum. Subtracting a common constant leaves all probability ratios unchanged. Compute loss as m+log Σexp(zᵢ-m)-z₁, approximately 0.4076. This avoids exponentiating 1001. For one-hot target y and softmax probabilities p, differentiation of log-sum-exp gives ∂L/∂zᵢ=pᵢ-yᵢ.

**Explanation:** The gradient sums to zero because both p and y sum to one, matching invariance to shifting all logits. Finite logits and at least one class are assumed; rows consisting only of negative infinity from masking need a separate policy because subtracting negative infinity produces undefined arithmetic. Computing softmax then taking log can underflow the selected probability unnecessarily; a fused log-softmax/cross-entropy form is preferable. For a batch, the averaging convention adds a 1/n factor to gradients. Label smoothing changes y, and multilabel independent binary targets require a different loss rather than this categorical normalization.

**Followups:** Why does adding 10 to every logit preserve the loss? What changes with temperature?

**Common mistakes:** Exponentiating raw large logits; subtracting a different constant for each class; confusing sigmoid with categorical softmax.

</details>

<a id="qb-aiml-006"></a>

## QB-AIML-006 — A chain rule across a shared parameter

| Field | Value |
|---|---|
| Category | Backpropagation |
| Difficulty | Intermediate |
| Target roles | ML Engineer; AI Engineer |
| Round | Derivation |
| Experience | 0–2 years; core practice |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Backpropagation: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-007 |
| Sources | [D2L computational graphs](https://d2l.ai/chapter_multilayer-perceptrons/backprop.html) |

### Prompt

For scalar w and inputs x₁,x₂, let a=wx₁, b=wx₂, and L=(a+b-t)²/2. Derive dL/dw and explain why a shared parameter must accumulate both graph paths.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Let r=a+b-t. The partials are ∂L/∂a=r and ∂L/∂b=r. Since ∂a/∂w=x₁ and ∂b/∂w=x₂, the total derivative is r(x₁+x₂). For w=2, x₁=1, x₂=3 and t=5, r=3, so the gradient is 12. Assigning the second contribution over the first would incorrectly give 9; accumulating both gives 3+9.

**Explanation:** The graph branches forward from the same parameter and merges contributions backward. An automatic differentiation engine applies the same chain rule with local derivatives and sums at shared ancestors. Gradient accumulation across independent minibatches is a separate training decision: if buffers are not cleared, a later optimizer step includes previous contributions. Reusing a tensor is not the same as copying independent parameter values with separate gradients. A finite-difference check on w should approach 12 for suitable ε; the existence of a correct scalar check does not validate arbitrary broadcasting, masking or mixed-precision paths in a larger implementation.

**Followups:** What if L also contains λw²/2 directly? What happens when one branch is explicitly detached?

**Common mistakes:** Overwriting branch gradients; forgetting a direct path to the loss; equating two equal numbers with one shared parameter.

</details>

<a id="qb-aiml-007"></a>

## QB-AIML-007 — Attention masking is a dependency claim

| Field | Value |
|---|---|
| Category | Attention foundations |
| Difficulty | Advanced |
| Target roles | ML Engineer; AI Engineer |
| Round | Derivation and tensor reasoning |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Attention foundations: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-008 |
| Sources | [D2L attention scoring](https://d2l.ai/chapter_attention-mechanisms-and-transformers/attention-scoring-functions.html) |

### Prompt

For one attention head, Q,K have shape (T,dₖ) and V shape (T,dᵥ). Derive output shape, quadratic work/storage terms, the scaling rationale and a causal mask. Explain why masking only the final output is insufficient.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Compute scores QKᵀ/√dₖ, shape (T,T), apply the causal mask to future-key positions j>i before row softmax, then multiply probabilities by V to produce (T,dᵥ). With roughly independent zero-mean unit-variance query/key coordinates, a dot product has variance dₖ; scaling keeps its variance near one and reduces softmax saturation. The rationale relies on an initialization/statistical approximation, not an identity for arbitrary trained vectors.

**Explanation:** The standard materialized implementation takes O(T²dₖ+T²dᵥ) time and O(T²) attention-score/probability storage beyond inputs/output. Projection costs are additional. Optimized kernels can avoid materializing the whole matrix, so quadratic score storage is not universal for every implementation. Masking final outputs cannot remove future information already mixed into earlier states. A causal prefix-invariance test changes future tokens and checks earlier outputs remain unchanged in a deterministic, correctly masked computation. Padding and fully masked rows need explicit handling; a row with all scores forbidden has no ordinary probability distribution.

**Followups:** How do cross-attention shapes change for different source/target lengths? What does a KV cache change during decoding?

**Common mistakes:** Softmax on the wrong axis; using the value dimension for scaling; treating a mask as optional after mixing.

</details>

<a id="qb-aiml-008"></a>

## QB-AIML-008 — LoRA freezes weights, not all computation

| Field | Value |
|---|---|
| Category | Parameter-efficient adaptation |
| Difficulty | Advanced |
| Target roles | ML Engineer; AI Engineer |
| Round | Derivation |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Parameter-efficient adaptation: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-009 |
| Sources | [LoRA paper v2](https://arxiv.org/abs/2106.09685) |

### Prompt

For W of shape (m,n), define W′=W+(α/r)BA with B of shape (m,r), A of shape (r,n). Derive trainable parameter count and gradients when B starts at zero and A is random. State a limitation of the low-rank update.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Only r(m+n) adapter parameters are trainable for this matrix, versus mn for full tuning, assuming W is frozen and no biases or other modules are tuned. For m=n=4096 and r=8, that is 65,536 versus 16,777,216 parameters. Let G=∂L/∂W′ and s=α/r; then ∂L/∂B=sGAᵀ and ∂L/∂A=sBᵀG. Initially B=0 makes the A gradient zero, but B can receive a nonzero gradient from random A.

**Explanation:** If both factors start at zero, both gradients are zero for this branch, so that initialization prevents learning without some other change. The initial effective update is zero when only B is zero, preserving the base transformation at initialization. Rank(BA)≤r restricts a single matrix update, although effects through the full network are more complex. Freezing W removes its optimizer state/gradient storage but still requires forward computation and often backward propagation through activations. Merging BA into W can remove adapter-path inference work under suitable precision/layout assumptions; memory savings and quality remain workload-specific measurements.

**Followups:** When can the factors be merged? Why might adapting more layers matter more than increasing rank?

**Common mistakes:** Claiming the whole model has only r(m+n) parameters; zero-initializing both factors; saying frozen weights eliminate activation memory.

</details>

<a id="qb-aiml-009"></a>

## QB-AIML-009 — Bellman backups differ from a sampled target

| Field | Value |
|---|---|
| Category | Reinforcement learning |
| Difficulty | Advanced |
| Target roles | ML Engineer; AI Engineer |
| Round | Math reasoning |
| Experience | 0–2 years; guided stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | Reinforcement learning: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-010 |
| Sources | [D2L value iteration](https://d2l.ai/chapter_reinforcement-learning/value-iter.html) |

### Prompt

A one-state continuing MDP has one action, deterministic reward 2 and self-transition. Let γ=0.9. Derive its value and the first two value-iteration updates from V₀=0. Contrast this expected backup with a sampled TD update.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** The Bellman equation is V=2+0.9V, hence V=20. Value iteration gives V₁=2 and V₂=3.8. More generally Vₖ=20(1-0.9ᵏ), which approaches 20. This is a continuing discounted task; if the transition were terminal, its continuation value would be zero and the answer would instead be 2. Reward timing and terminal semantics are essential parts of the contract.

**Explanation:** A sampled TD(0) update is V(s)←V(s)+η[r+γV(s′)-V(s)], usually using one observed transition rather than a known transition expectation. In this deterministic example the sample target equals the expected target, but a partial step η<1 differs from replacing V with the full backup. For several actions, optimal value iteration maximizes over action backups, whereas policy evaluation averages under a fixed policy. Contraction of the discounted exact Bellman operator does not establish convergence for every neural function approximator, optimizer or off-policy training setup. Exploration and data coverage are separate concerns from this algebra.

**Followups:** What breaks at γ=1 in this continuing positive-reward example? How does finite-horizon indexing change the equation?

**Common mistakes:** Dropping discount; bootstrapping past terminal states; using exact-table guarantees to justify arbitrary deep RL.

</details>

<a id="qb-aiml-010"></a>

## QB-AIML-010 — A low-rank approximation has a precise objective

| Field | Value |
|---|---|
| Category | SVD and model compression |
| Difficulty | Expert |
| Target roles | ML Engineer; AI Engineer |
| Round | Optional linear algebra deep dive |
| Experience | 0–2 years; optional stretch |
| Company scope | Company-neutral; original [INFERRED] |
| Evaluates | SVD and model compression: correct mechanism and explicit assumptions |
| Related IDs | QB-AIML-001 |
| Sources | [NumPy 2.4 SVD](https://numpy.org/doc/2.4/reference/generated/numpy.linalg.svd.html) |

### Prompt

A real matrix has singular values 5,3,1. What is the best rank-1 Frobenius approximation error and retained squared-energy fraction? Explain why this is not a guarantee that compressing a trained network preserves task accuracy.

<details><summary>Expected answer and review — attempt first</summary>

**Expected answer:** Truncated SVD retains the largest singular value and its singular-vector pair. The squared Frobenius error is the sum of discarded squared singular values, 3²+1²=10, so the error norm is √10. Total squared Frobenius norm is 25+9+1=35, giving retained energy 25/35=5/7. This minimizes an unweighted matrix reconstruction objective among rank-at-most-one approximations, not every possible downstream prediction loss.

**Explanation:** A layer acts on a particular activation distribution; directions with small matrix energy may still matter for rare but important inputs. Nonlinearities, residual paths and subsequent layers can amplify or suppress reconstruction errors. If leading singular values tie, the optimum subspace choice can be nonunique, though the optimal error is unchanged. Validate shape, reconstruction, orthogonality and error against the omitted singular values in numerical code, allowing floating tolerance. Then independently measure task metrics and slices after compression. A best matrix approximation is a mathematical statement with a specific norm, not evidence about latency, hardware kernels or fairness.

**Followups:** What is the best rank-2 error? How would activation-weighted reconstruction change the objective?

**Common mistakes:** Confusing singular values with eigenvalues of any rectangular matrix; summing values instead of squares; equating retained energy with accuracy.

</details>
