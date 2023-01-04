package com.example.core.book_DDD.infrastructure;

import com.example.core.book_DDD.domain.model.entity.Book;
import com.example.core.book_DDD.domain.rest.BookSearchCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class BookSpecification implements Specification<Book> {

    private final BookSearchCriteriaRequest criteria;

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                advertisementNameLike(root, criteriaBuilder),
                seniorityNameEquals(root, criteriaBuilder));
    }

    private Predicate advertisementNameLike(Root<Book> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getTitle()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + criteria.getTitle().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate seniorityNameEquals(Root<Book> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getReleaseYear()) ?
                criteriaBuilder.equal(root.get("releaseYear"), criteria.getReleaseYear()) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
