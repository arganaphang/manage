package model

type TransactionType string

const (
	// ? Database Table Name
	TRANSACTION_TABLENAME string          = "transactions"
	TRANSACTION_IN        TransactionType = "in"
	TRANSACTION_OUT       TransactionType = "out"
)

type Transaction struct {
	ID        string          `json:"id" db:"id"`
	Title     string          `json:"title" db:"title"`
	Amount    uint64          `json:"amount" db:"amount"`
	Notes     *string         `json:"notes" db:"notes"`
	Type      TransactionType `json:"type" db:"type"`
	CreatedAt int64           `json:"created_at" db:"created_at"`
	UpdatedAt int64           `json:"updated_at" db:"updated_at"`
}
