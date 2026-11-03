# Normalization: give each fact one owner

## Overview

Normalization organizes relations according to functional dependencies so one fact is not independently updated in several places. It reduces update, insertion and deletion anomalies. It does not mean every query must join many tables or that denormalization is always wrong.

A functional dependency `X → Y` means rows agreeing on X must agree on Y under the domain's rules. It is a semantic claim, not something proved by a few observed rows. Candidate keys are minimal sets of attributes that identify a row; a table can have multiple candidate keys.

## Worked model

Suppose an order-line record contains `order_id, line_no, customer_id, customer_email, product_id, current_product_name, quantity, unit_price_cents`.

Assume these dependencies:

- `(order_id, line_no)` identifies a line.
- `order_id → customer_id`.
- `customer_id → customer_email` for the current customer record.
- `product_id → current_product_name`.

Repeating current customer email in every line makes email changes a many-row update and allows inconsistent values. Removing a customer's final order line could also accidentally erase the only stored copy of their profile.

A decomposition is `customers(customer_id, email)`, `products(product_id, current_name)`, `orders(order_id, customer_id)` and `order_lines(order_id, line_no, product_id, quantity, unit_price_cents)`.

But **unit price at purchase** belongs to the order line: it is a historical fact, not necessarily the product's current price. An order may intentionally snapshot shipping address or display name too. Decide whether a value is a current entity attribute or an immutable event fact before calling duplication an error.

## Normal forms without slogans

| Form | Working definition | What to check |
|---|---|---|
| 1NF | Attributes hold values from their declared domains rather than repeating column groups | A `phone1/phone2/phone3` design often signals a missing child relation; “atomic” depends on the domain |
| 2NF | In 1NF, no non-prime attribute depends on only a proper subset of a candidate key | Especially relevant to composite keys; a prime attribute belongs to at least one candidate key |
| 3NF | For every nontrivial dependency X→A, X is a superkey or A is prime | Removing non-key-to-non-key dependencies is useful intuition, but not the full formal definition |
| BCNF | For every nontrivial dependency X→Y, X is a superkey | Stronger than 3NF; some decompositions sacrifice dependency preservation |

A decomposition should preserve information through a lossless join. Dependency preservation matters because otherwise enforcing a rule can require joining decomposed tables. Formal normal forms are not established by adding a surrogate id while leaving the original dependencies intact. [Database System Concepts, chapter 7, author slides](https://www.db-book.com/slides-dir/PDF-dir/ch7.pdf)

## Constraints make the model executable

In the shared fixture, the order's customer id references one customer. A foreign key enforces identity consistency; it does not automatically enforce all tenant, authorization or business-state rules. Required values need `NOT NULL` as well as checks. Uniqueness belongs in the database when concurrent writers must not create duplicates. [PostgreSQL constraints](https://www.postgresql.org/docs/18/ddl-constraints.html)

A schema with `tenant_id` on two tables needs care: a foreign key on only an entity id does not by itself prove the tenant ids agree. A composite key/reference or another deliberately enforced invariant can prevent cross-tenant references. This is data integrity; authorization still needs an independent decision for each caller.

## Denormalization as an owned projection

A customer spending summary may store `paid_total_cents` for faster reads. Define its owner, source rows, update mechanism, freshness target and rebuild/reconciliation path. If asynchronous, explain what a stale value means to the user. Do not use a possibly stale projection to authorize a payment or enforce inventory without a consistency design.

## Interview practice

[INFERRED] Beginner–Intermediate, backend/data, schema design.

1. **NORM-01:** Is saving the product's purchase-time name on an invoice a normalization mistake?
2. **NORM-02:** Why does adding a surrogate primary key not automatically put a table in 3NF?

<details><summary>Expected reasoning and followups</summary>

1. It can be a legitimate immutable historical fact. First decide whether the field means current product name or displayed name at purchase. Followup: what happens when a product is renamed or deleted?
2. The semantic dependencies among existing attributes still exist. A surrogate id does not remove partial/transitive dependencies of other candidate keys. Followup: identify all candidate keys and dependencies before decomposing.

Mistakes: treating all repeated values as accidental duplication, claiming joins are always slow, or denormalizing without update ownership and reconciliation. Related: [SQL grain](SQL.md), [authorization](Authorization.md).

</details>

## Checklist and last verified

- [ ] State dependencies and temporal meaning, not only column names.
- [ ] Explain an actual update anomaly and a lossless decomposition.
- [ ] Distinguish historical snapshots from mutable duplication.
- [ ] Give every projection a freshness and rebuild contract.

Last verified **2026-11-02**: conceptual review. No formal decomposition proof tool or production migration was run. Source scope is listed in [Phase 2 sources](PHASE2_SOURCES.md).
