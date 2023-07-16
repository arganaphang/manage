package application

import (
	"application/pkg/config"
	"fmt"

	"github.com/gin-gonic/gin"
)

// Start Web Server
func Start() error {
	// ? Set Mode
	gin.SetMode(config.Get().APPLICATION_ENV)
	// ? Spawn Gin Engine
	app := gin.Default()
	// ? Setup Proxies
	app.SetTrustedProxies([]string{"0.0.0.0"})
	// ? Setup Dependecies
	db, err := setupDatabase()
	if err != nil {
		return err
	}
	repositories := setupRepository(db)
	services := setupService(repositories)
	// ? Setup Router
	setupRouter(app, services)
	// ? Run App Server
	return app.Run(fmt.Sprintf("0.0.0.0:%d", config.Get().APPLICATION_PORT))
}
