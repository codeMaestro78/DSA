# TensorFlow training-loop reasoning

## Recorded gradients

GradientTape records differentiable operations and computes gradients for the requested trainable variables. A missing gradient is not the same as a valid zero derivative; investigate dependency and recording instead of silently declaring learning successful.

## Small example

This educational snippet was parsed for syntax but **not executed** because TensorFlow is unavailable. It uses TensorFlow's Keras interface. Check exact TensorFlow/Keras compatibility in the environment where it will run.

```python
import tensorflow as tf

tf.random.set_seed(7)
x = tf.constant([[0.0, 0.0], [0.0, 1.0], [1.0, 0.0], [1.0, 1.0]])
y = tf.constant([[0.0], [0.0], [0.0], [1.0]])
model = tf.keras.Sequential([tf.keras.layers.Input(shape=(2,)),
                             tf.keras.layers.Dense(1)])
optimizer = tf.keras.optimizers.SGD(learning_rate=0.1)
criterion = tf.keras.losses.BinaryCrossentropy(from_logits=True)
for _ in range(20):
    with tf.GradientTape() as tape:
        loss = criterion(y, model(x, training=True))
    gradients = tape.gradient(loss, model.trainable_variables)
    if any(g is None for g in gradients):
        raise RuntimeError('A trainable variable has no gradient')
    optimizer.apply_gradients(zip(gradients, model.trainable_variables))
probabilities = tf.math.sigmoid(model(x, training=False))
print(probabilities)
```

The toy data illustrates a loop, not generalization or guaranteed convergence. Predictions/targets both have shape (4, 1). A logits-based loss receives raw outputs; sigmoid converts only the final inspection output.

## Execution and portability

Graph tracing can change the relationship between Python side effects and tensor execution. Keep variable creation/state/shapes deliberate before optimizing. A runnable graph does not prove the intended objective or split is correct.

Between frameworks, compare reductions, initialization, layout, optimizer semantics and serialization, not only layer names. Deploying a model still requires validation, preprocessing/version compatibility, monitoring and rollback. No local TensorFlow API execution or device behavior is established by the syntax check.

## Interview exercise — attempt first

**ID:** ML3-TENSORFLOW. **Category:** ML/DL / TensorFlow training-loop reasoning. **Difficulty:** Intermediate. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** What would you inspect when GradientTape returns None?

<details><summary>Expected reasoning, followup and common mistake</summary>

Check loss dependency, recorded differentiable operations, variable creation/watch behavior and whether a NumPy/Python conversion severed the graph. Compare to a true zero gradient.

**Followup:** Why are None and a valid zero gradient different?

**Common mistake:** Treating a severed dependency as successful training or assuming a syntax check validates APIs.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Writing a training loop from scratch / François Chollet, TensorFlow](https://www.tensorflow.org/guide/keras/writing_a_training_loop_from_scratch).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
