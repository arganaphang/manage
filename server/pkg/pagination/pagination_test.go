package pagination

import (
	"fmt"
	"net/url"
	"reflect"
	"testing"
)

func TestTransform(t *testing.T) {
	tests := []struct {
		got     url.Values
		want    *Pagination
		wantErr bool
	}{
		{
			got: url.Values{
				"page":     []string{"a"},
				"per_page": []string{"10"},
			},
			want:    nil,
			wantErr: true,
		},
		{
			got: url.Values{
				"page":     []string{"1"},
				"per_page": []string{"a"},
			},
			want:    nil,
			wantErr: true,
		},
		{
			got: url.Values{
				"page":     []string{""},
				"per_page": []string{""},
			},
			want: &Pagination{
				Page:    1,
				PerPage: 10,
				Total:   0,
				Limit:   10,
				Offset:  0,
			},
			wantErr: false,
		},
	}
	for idx, tt := range tests {
		t.Run(fmt.Sprintf("Test %d", idx), func(t *testing.T) {
			got, err := Transform(tt.got)
			if (err != nil) != tt.wantErr {
				t.Errorf("Transform() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Transform() = %v, want %v", got, tt.want)
			}
		})
	}
}
