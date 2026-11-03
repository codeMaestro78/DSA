# PyTorch training-loop reasoning

## Tensor, parameter and graph

PyTorch's automatic differentiation propagates derivatives through recorded operations. An optimizer updates registered parameters from gradients. train()/eval() control mode-dependent modules, while disabling gradient tracking controls graph recording. These are distinct controls.

## Small CPU example

This self-contained educational snippet was parsed for Python syntax but **not executed**: torch is unavailable in this workspace. The local NumPy routine separately validates the underlying logistic gradient. The snippet follows the cited loop pattern, not an asserted measured result.

```python
import torch
from torch import nn

torch.manual_seed(7)
x = torch.tensor([[0.0, 0.0], [0.0, 1.0], [1.0, 0.0], [1.0, 1.0]])
y = torch.tensor([[0.0], [0.0], [0.0], [1.0]])
model = nn.Linear(2, 1)
optimizer = torch.optim.SGD(model.parameters(), lr=0.1)
criterion = nn.BCEWithLogitsLoss()
model.train()
for _ in range(20):
    optimizer.zero_grad(set_to_none=True)
    loss = criterion(model(x), y)
    loss.backward()
    optimizer.step()
model.eval()
with torch.no_grad():
    probabilities = torch.sigmoid(model(x))
print(probabilities)
```

The four examples form a toy loop fixture without a held-out split. No output, convergence or accuracy is claimed. Labels and logits both have shape (4, 1), preventing unintended broadcasting. Raw logits enter the logits loss; sigmoid is used only to inspect probabilities.

## Debugging and artifacts

Inspect weights/gradients across one update. Save configuration, preprocessing and selected state under a versioned contract. Device/dtype mismatch, stale gradients, incorrect accumulation and mode errors are distinct failures. GPU, mixed precision, export/compile and distributed APIs need separate version-specific validation. A framework does not provide label quality, a valid split or a production serving policy automatically.

## Interview exercise — attempt first

**ID:** ML3-PYTORCH. **Category:** ML/DL / PyTorch training-loop reasoning. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** How do eval() and no_grad() differ?

<details><summary>Expected reasoning, followup and common mistake</summary>

eval changes mode-sensitive layer behavior such as dropout/batch normalization; no_grad suppresses gradient recording. Use both appropriately for ordinary inference. This simple linear model has no such mode-sensitive layers.

**Followup:** How would dropout change the significance of eval mode?

**Common mistake:** Reporting AST-parsed framework code as an executed training run.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Optimizing model parameters / PyTorch](https://docs.pytorch.org/tutorials/beginner/basics/optimization_tutorial.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
