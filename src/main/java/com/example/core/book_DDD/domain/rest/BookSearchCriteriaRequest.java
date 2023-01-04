package com.example.core.book_DDD.domain.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookSearchCriteriaRequest {

    private Integer releaseYear;

    private String title;
}
