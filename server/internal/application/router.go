package application

import (
	"application/internal/pkg/model"
	"application/internal/pkg/service"
	"application/pkg/pagination"
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
	app.GET("/healthz", r.health_get)
	app.GET("/transactions", r.transaction_get_all)
	app.GET("/transactions/:id", r.transaction_get_detail)
	app.POST("/transactions", r.transaction_post)
}

func (r router) health_get(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": "OK",
	})
}

func (r router) transaction_get_all(ctx *gin.Context) {
	var trxQuery interface{} = ctx.Request.URL.Query().Get("type")
	trx, ok := trxQuery.(model.TransactionType)
	if !ok {
		ctx.JSON(http.StatusBadRequest, gin.H{
			"message": "Transaction type not correct",
		})
		return
	}
	pgtn, err := pagination.Transform(ctx.Request.URL.Query())
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"message": err.Error(),
		})
		return
	}
	results, total, err := r.Services.Transaction.TransactionAll(ctx, trx, pgtn.Limit, pgtn.Offset)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"message": "Failed to get Transaction",
		})
		return
	}
	pgtn.Total = *total
	ctx.JSON(http.StatusOK, gin.H{
		"message":    "Get Transaction",
		"data":       results,
		"pagination": pgtn,
	})
}

func (r router) transaction_get_detail(ctx *gin.Context) {
	id := ctx.Param("id")
	results, err := r.Services.Transaction.TransactionGet(ctx, id)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"message": "Failed to get Transaction",
		})
		return
	}
	ctx.JSON(http.StatusOK, gin.H{
		"message": "Get Transaction",
		"data":    results,
	})
}

func (r router) transaction_post(ctx *gin.Context) {
	var body model.Transaction
	if ctx.Bind(&body) != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{
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
		ctx.JSON(http.StatusInternalServerError, gin.H{
			"message": "Failed to create a Transaction",
			"error":   err.Error(),
		})
		return
	}
	ctx.JSON(http.StatusOK, gin.H{
		"message": "Transaction Created",
		"data":    data,
	})
}
