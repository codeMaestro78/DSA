# Reinforcement learning

## Task definition

An agent acts, receives rewards and affects future state. An MDP specifies states, actions, transition/reward behavior and horizon/discount assumptions. A policy selects actions; reward is the optimized signal, not automatically the true user objective.

## Return and values

For a finite reward sequence, G_t=r_t+gamma*r_(t+1)+gamma²*r_(t+2)+… . Rewards 1, 2, 3 with gamma 0.9 give 1+1.8+2.43=5.23. A state value is expected return under a policy; an action value additionally conditions on an action. Bellman equations relate current values to immediate reward and future expectation.

A Q-learning target commonly uses r+gamma*max_a Q(s_next, a) for a nonterminal transition; a genuine terminal transition omits continuation. Artificial time truncation may need a different bootstrap decision. State the task/environment contract.

## Families and tradeoffs

Value methods estimate values; policy gradients optimize policy parameters using sampled return/advantage information; actor-critic combines policy/value estimates. On/off-policy concerns the relationship between the learning target and behavior generating data. Exploration, credit assignment, support and sample efficiency matter.

## Failure cases

A reward proxy can be gamed. Offline logs may not cover proposed actions, so extrapolation can be unjustified. A high simulation score does not establish real-world transfer. A tool-calling loop is not necessarily RL-trained merely because it acts repeatedly.

For application roles, understand return/policy/value/reward mismatch first. Research/training roles may need deeper derivations and experiments; do not assume every AI role requires PPO. The local suite checks only the illustrative return arithmetic, not an RL environment or learning run.

## Interview exercise — attempt first

**ID:** ML3-REINFORCEMENT_LEARNING. **Category:** ML/DL / Reinforcement learning. **Difficulty:** Advanced. **Target:** early-career ML/AI/research-engineering preparation; technical/coding discussion. **Scope:** original [INFERRED], company-neutral; advanced topics are extensions, not requirements for every role. **Evaluates:** mechanism, assumptions and evidence. **Related:** [roadmap](AI_ML_DL_Interview_Roadmap.md) and linked prerequisites.

**Question:** Why distinguish terminal from truncated transitions?

<details><summary>Expected reasoning, followup and common mistake</summary>

A true terminal state has no continuation under the task. A time limit can end observation without ending the underlying process. Bootstrap according to that distinction and the algorithm contract.

**Followup:** What could go wrong if the reward is only a proxy for the user outcome?

**Common mistake:** Bootstrapping through a genuine terminal state or calling every tool loop RL-trained.

</details>

## Preparation checklist

- [ ] Explain the worked example and its assumptions without reading the answer.
- [ ] State one failure/counterexample and relevant shape/cost.
- [ ] Record an independent attempt, hints and actual checks.

## References

- [Key concepts in RL / OpenAI Spinning Up](https://spinningup.openai.com/en/latest/spinningup/rl_intro.html).
- [Source metadata](PHASE3_SOURCES.md).
- [Numerical validation](../validation/ml/README.md): only explicitly listed routines were executed.

## Last verified

2026-11-02: technical explanation and source scope reviewed. Exercises are editorial; no company frequency, production performance or unreported framework execution is implied.
