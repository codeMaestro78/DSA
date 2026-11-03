# Message queues

## What asynchronous delivery changes

A queue decouples acceptance from execution. It can absorb a bounded burst and let workers run independently, but waiting work consumes storage and adds latency. A queue does not make insufficient sustained processing capacity disappear.

Distinguish a work queue, where one logical job should be completed by a worker, from pub/sub, where independent subscriptions may each need an event. Ordering scope, retention, acknowledgments and replay depend on the broker and configuration.

## Acknowledgments and duplicate work

If a worker acknowledges before completing the durable effect, a crash can lose work. If it completes the effect then crashes before acknowledgment, redelivery can duplicate work. Amazon SQS standard queues explicitly document possible duplicate delivery; this is product-specific evidence, not a complete statement about every queue.

Design the consumer for the delivery contract. For a database-only effect, a unique event ID and the business update can be recorded in one local transaction. On redelivery, the existing ID prevents applying that local effect twice. That transaction cannot atomically include an unrelated email provider unless the provider offers a suitable coordination/idempotency contract.

## The database-to-queue gap

Suppose a request writes `notification=accepted`, then tries to publish. If publishing fails after the write, the work can disappear from the queue. A transactional outbox stores the accepted record and an event in one database transaction. A relay publishes outbox entries. A crash after publish but before marking sent creates a duplicate, so consumers still need deduplication. The outbox trades immediate atomic dispatch for eventual publication with a recoverable record.

A bounded retry policy should classify temporary versus permanent failure, limit attempts or total age, use backoff with jitter, and retain failure details. A dead-letter queue is an investigation/replay path, not automatic recovery. Replaying without fixing the cause can repeat damage.

## Backpressure and ordering

Monitor oldest-job age and completion rate, not just queue depth. Use tenant/channel concurrency limits and provider rate budgets. If input exceeds service forever, reject, defer or reduce work according to policy. Global ordering reduces parallelism; per-recipient ordering may be enough, but a poison job can then block that recipient's later work. State the policy explicitly.

## Practice question

**SD-QUEUE-01 — Advanced; backend/AI/platform; design; 0–2+ years.** A worker sends an email, crashes, then gets the same job again. Can a unique local `processed_events` table alone guarantee one email?

<details><summary>Expected answer and followups</summary>

No. Marking before the send risks losing the email; marking after risks another send after a crash. The external boundary needs a provider idempotency key or reconciliation/duplicate policy. Followup: how does the outbox help? It prevents accepted database intent from being silently omitted from eventual publication; it does not solve the external side-effect atomicity gap. Mistake: claiming exactly-once business delivery from a broker setting.

</details>

## Checklist

- [ ] Acceptance, publication, processing and provider acknowledgment are separate states.
- [ ] Ack timing and crash interleavings are traced.
- [ ] Retry age, dead-letter handling and replay ownership are explicit.
- [ ] Ordering and backlog recovery are bounded and observable.

## References

- [Amazon SQS — standard queue at-least-once delivery](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/standard-queues-at-least-once-delivery.html)
- [AWS Builders Library — timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)

## Last verified

2026-11-02: teaching explanation and source scope reviewed. All numerical workloads and product designs are illustrative, not measured production facts.
