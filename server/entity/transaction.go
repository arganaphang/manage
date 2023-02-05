package entity

import (
	"time"

	"github.com/google/uuid"
)

type TransactionType uint8

const (
	IN TransactionType = iota
	OUT
)

type Transaction struct {
	ID        uuid.UUID       `json:"id"`
	Title     string          `json:"title"`
	Amount    string          `json:"amount"`
	Type      TransactionType `json:"type"`
	CreatedAt time.Time       `json:"created_at"`
}
