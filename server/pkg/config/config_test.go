package config

import (
	"reflect"
	"testing"
)

func setup() {
	conf = nil
}

func TestGet(t *testing.T) {
	setup()
	t.Setenv("APPLICATION_PORT", "3000")
	t.Setenv("APPLICATION_ENV", "debug")
	tests := []struct {
		name string
		want Config
	}{
		{
			name: "Test Configuration",
			want: Config{
				APPLICATION_PORT: 3000,
				APPLICATION_ENV:  "debug",
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := Get(); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Get() = %v, want %v", got, tt.want)
			}
		})
	}
}

func TestGetDefault(t *testing.T) {
	setup()
	t.Setenv("APPLICATION_PORT", "")
	t.Setenv("APPLICATION_ENV", "")
	tests := []struct {
		name string
		want Config
	}{
		{
			name: "Test Configuration",
			want: Config{
				APPLICATION_PORT: 8000,
				APPLICATION_ENV:  "release",
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := Get(); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Get() = %v, want %v", got, tt.want)
			}
		})
	}
}
