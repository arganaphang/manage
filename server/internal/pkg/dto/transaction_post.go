package dto

import (
	"application/internal/pkg/model"
)

type TransactionPost struct {
	Title  string                `json:"title"`
	Amount int64                 `json:"amount"`
	Notes  *string               `json:"notes"`
	Type   model.TransactionType `json:"type"`
}
