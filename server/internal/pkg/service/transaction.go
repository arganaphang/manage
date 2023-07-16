package service

import (
	"application/internal/pkg/model"
	"application/internal/pkg/repository"
	"context"
)

type TransactionService struct {
	Repository repository.Repository
}

func (r TransactionService) TransactionCreate(ctx context.Context, data model.Transaction) error {
	return r.Repository.Transaction.TransactionCreate(ctx, data)
}

func (r TransactionService) TransactionAll(ctx context.Context, transactionType model.TransactionType, limit, offset uint) ([]model.Transaction, *uint, error) {
	return r.Repository.Transaction.TransactionAll(ctx, transactionType, limit, offset)
}

func (r TransactionService) TransactionGet(ctx context.Context, id string) (*model.Transaction, error) {
	return r.Repository.Transaction.TransactionGet(ctx, id)
}
