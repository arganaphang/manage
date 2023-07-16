package pagination

import (
	"errors"
	"net/url"
	"strconv"
)

type Pagination struct {
	Page    uint `json:"page"`
	PerPage uint `json:"per_page"`
	Total   uint `json:"total"`
	Limit   uint `json:"-"`
	Offset  uint `json:"-"`
}

func Transform(query url.Values) (*Pagination, error) {
	pageQuery := query.Get("page")
	if pageQuery == "" {
		pageQuery = "1"
	}
	perPageQuery := query.Get("per_page")
	if perPageQuery == "" {
		perPageQuery = "10"
	}
	page, err := strconv.ParseUint(pageQuery, 10, 32)
	if err != nil {
		return nil, errors.New("page is not a number")
	}
	perPage, err := strconv.ParseUint(perPageQuery, 10, 32)
	if err != nil {
		return nil, errors.New("per_page is not a number")
	}
	return &Pagination{
		Page:    uint(page),
		PerPage: uint(perPage),
		Limit:   uint(perPage),
		Offset:  uint((page - 1) * perPage),
	}, nil
}
