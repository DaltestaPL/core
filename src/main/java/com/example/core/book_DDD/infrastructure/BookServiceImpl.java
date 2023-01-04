package com.example.core.book_DDD.infrastructure;

import com.example.core.book_DDD.domain.model.entity.Book;
import com.example.core.book_DDD.domain.rest.BookSearchCriteriaRequest;
import com.example.core.book_DDD.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Override
    public Page<Book> search(BookSearchCriteriaRequest request, Pageable pageable) {
        var specification = new BookSpecification(request);
        bookRepository.findAll(specification, pageable);
        return bookRepository.findAll(specification, pageable);
    }
}
