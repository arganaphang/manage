package model

type TransactionType string

const (
	// ? Database Table Name
	TRANSACTION_TABLENAME string          = "transactions"
	TRANSACTION_IN        TransactionType = "TRANSACTION_IN"
	TRANSACTION_OUT       TransactionType = "TRANSACTION_OUT"
)

type Transaction struct {
	ID        string          `json:"id" db:"id"`
	Title     string          `json:"title" db:"title"`
	Amount    int64           `json:"amount" db:"amount"`
	Notes     *string         `json:"notes" db:"notes"`
	Type      TransactionType `json:"type" db:"type"`
	CreatedAt int64           `json:"created_at" db:"created_at"`
	UpdatedAt int64           `json:"updated_at" db:"updated_at"`
}
