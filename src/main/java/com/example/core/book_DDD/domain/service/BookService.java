package com.example.core.book_DDD.domain.service;

import com.example.core.book_DDD.domain.model.entity.Book;
import com.example.core.book_DDD.domain.rest.BookSearchCriteriaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> search(BookSearchCriteriaRequest request, Pageable pageable);
}
