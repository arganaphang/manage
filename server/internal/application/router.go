package application

import (
	"application/internal/pkg/model"
	"application/internal/pkg/service"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	gonanoid "github.com/matoous/go-nanoid/v2"
)

type router struct {
	Services service.Service
}

// setupRouter function to register all of your endpoint
func setupRouter(app *gin.Engine, service service.Service) {
	r := router{service}
	app.GET("/healtz", r.health_get)
	app.GET("/transactions", r.transaction_get)
	app.POST("/transactions", r.transaction_post)
}

func (r router) health_get(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, map[string]any{
		"message": "OK",
	})
}

func (r router) transaction_get(ctx *gin.Context) {
	results, err := r.Services.Transaction.TransactionAll(ctx)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, map[string]any{
			"message": "Failed to get Transaction",
		})
		return
	}
	ctx.JSON(http.StatusOK, map[string]any{
		"message": "Get Transaction",
		"data":    results,
	})
}

func (r router) transaction_post(ctx *gin.Context) {
	var body model.Transaction
	if ctx.Bind(&body) != nil {
		ctx.JSON(http.StatusInternalServerError, map[string]any{
			"message": "Failed to parse request body",
		})
		return
	}
	id, _ := gonanoid.New(5)
	now := time.Now().UTC().UnixMilli()
	data := model.Transaction{
		ID:        id,
		Title:     body.Title,
		Amount:    body.Amount,
		Notes:     body.Notes,
		Type:      body.Type,
		CreatedAt: now,
		UpdatedAt: now,
	}
	err := r.Services.Transaction.TransactionCreate(ctx, data)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, map[string]any{
			"message": "Failed to create a Transaction",
		})
		return
	}
	ctx.JSON(http.StatusOK, map[string]any{
		"message": "Transaction Created",
		"data":    data,
	})
}
