package com.example.core.book_DDD.domain.model.dto;

import com.example.core.book_DDD.domain.model.entity.Book;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Integer releaseYear;
    private String title;

    public static BookDto from(Book source) {
        return BookDto.builder()
                .id(source.getId())
                .releaseYear(source.getReleaseYear())
                .title(source.getTitle())
                .build();
    }
}
