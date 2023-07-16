package repository

import (
	"application/internal/pkg/model"
	"context"

	"github.com/doug-martin/goqu/v9"
	"github.com/jmoiron/sqlx"
)

type TransactionRepository struct {
	DB *sqlx.DB
}

func (r TransactionRepository) TransactionCreate(ctx context.Context, data model.Transaction) error {
	sql, _, err := goqu.Insert(model.TRANSACTION_TABLENAME).Rows(
		data,
	).ToSQL()
	if err != nil {
		return err
	}
	_, err = r.DB.Exec(sql)
	if err != nil {
		return err
	}
	return nil
}

func (r TransactionRepository) TransactionAll(ctx context.Context, transactionType model.TransactionType, limit, offset uint) ([]model.Transaction, *uint, error) {
	stmt := goqu.From(model.TRANSACTION_TABLENAME)
	// Select Data
	sql, _, err := stmt.Where(goqu.Ex{
		"type": transactionType,
	}).Limit(limit).Offset(offset).ToSQL()
	if err != nil {
		return nil, nil, err
	}
	transactions := []model.Transaction{}
	err = r.DB.Select(&transactions, sql)
	if err != nil {
		return nil, nil, err
	}
	// Count
	var count struct {
		Total uint `db:"total"`
	}
	countSql, _, err := stmt.Select(
		goqu.COUNT("*").As("total"),
	).ToSQL()
	if err != nil {
		return nil, nil, err
	}
	err = r.DB.Get(&count, countSql)
	if err != nil {
		return nil, nil, err
	}
	return transactions, &count.Total, nil
}

func (r TransactionRepository) TransactionGet(ctx context.Context, id string) (*model.Transaction, error) {
	sql, _, err := goqu.From(model.TRANSACTION_TABLENAME).Where(
		goqu.C("id").Eq(id),
	).Limit(1).ToSQL()
	if err != nil {
		return nil, err
	}
	transaction := model.Transaction{}
	err = r.DB.Get(&transaction, sql)
	if err != nil {
		return nil, err
	}
	return &transaction, nil
}
