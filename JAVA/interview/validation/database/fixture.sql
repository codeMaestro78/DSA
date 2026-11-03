-- Fictional teaching data. Load only into the disposable validation schema.
CREATE TABLE customers (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);
CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  customer_id INTEGER NOT NULL REFERENCES customers(id),
  amount_cents BIGINT NOT NULL CHECK (amount_cents >= 0),
  status TEXT NOT NULL CHECK (status IN ('paid', 'pending', 'cancelled')),
  created_at TIMESTAMPTZ NOT NULL
);
INSERT INTO customers VALUES (1, 'Ada'), (2, 'Lin'), (3, 'Mira'), (4, 'NoOrders');
INSERT INTO orders VALUES
  (101, 1, 1200, 'paid', '2026-01-01 00:00:00+00'),
  (102, 1, 800, 'paid', '2026-01-02 00:00:00+00'),
  (103, 1, 400, 'cancelled', '2026-01-03 00:00:00+00'),
  (104, 2, 2000, 'paid', '2026-01-02 00:00:00+00'),
  (105, 2, 700, 'pending', '2026-01-04 00:00:00+00'),
  (106, 3, 500, 'paid', '2026-01-02 00:00:00+00');
CREATE TABLE inventory (
  product_id INTEGER PRIMARY KEY,
  stock INTEGER NOT NULL CHECK (stock >= 0)
);
INSERT INTO inventory VALUES (1, 1);
CREATE TABLE accounts (
  id INTEGER PRIMARY KEY,
  balance INTEGER NOT NULL CHECK (balance >= 0)
);
INSERT INTO accounts VALUES (1, 100);
CREATE TABLE doctors (
  id INTEGER PRIMARY KEY,
  on_call BOOLEAN NOT NULL
);
INSERT INTO doctors VALUES (1, TRUE), (2, TRUE);
