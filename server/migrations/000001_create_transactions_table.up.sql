CREATE TABLE IF NOT EXISTS "transactions" (
  id TEXT PRIMARY KEY,
  title TEXT NOT NULL,
  amount INTEGER NOT NULL,
  type TEXT,
  notes TEXT,
  created_at INTEGER,
  updated_at INTEGER
);