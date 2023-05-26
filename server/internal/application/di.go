package application

import (
	"application/internal/pkg/repository"
	"application/internal/pkg/service"

	"github.com/jmoiron/sqlx"
	_ "github.com/mattn/go-sqlite3"
)

func setupDatabase() (*sqlx.DB, error) {
	return sqlx.Connect("sqlite3", "./database.db")
}

func setupRepository(db *sqlx.DB) repository.Repository {
	return repository.Repository{
		Transaction: repository.TransactionRepository{
			DB: db,
		},
	}
}

func setupService(repository repository.Repository) service.Service {
	return service.Service{
		Transaction: service.TransactionService{
			Repository: repository,
		},
	}
}
