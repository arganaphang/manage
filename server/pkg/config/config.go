package config

import (
	"os"
	"strconv"
)

type Config struct {
	APPLICATION_PORT uint
	APPLICATION_ENV  string
}

var conf *Config = nil

// start to load configuration from environment variable
func start() {
	port, err := strconv.Atoi(os.Getenv("APPLICATION_PORT"))
	if err != nil {
		port = 8000
	}
	env := os.Getenv("APPLICATION_ENV")
	if env == "" {
		env = "release"
	}
	conf = &Config{
		APPLICATION_PORT: uint(port),
		APPLICATION_ENV:  env,
	}
}

func Get() Config {
	if conf == nil {
		start()
	}
	return *conf
}
