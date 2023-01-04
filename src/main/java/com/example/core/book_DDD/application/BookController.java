package com.example.core.book_DDD.application;

import com.example.core.book_DDD.domain.model.entity.Book;
import com.example.core.book_DDD.domain.rest.BookSearchCriteriaRequest;
import com.example.core.book_DDD.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/search")
    public Page<Book> searchBooks(@RequestBody BookSearchCriteriaRequest request, Pageable pageable) {
        return bookService.search(request, pageable);
    }
}
