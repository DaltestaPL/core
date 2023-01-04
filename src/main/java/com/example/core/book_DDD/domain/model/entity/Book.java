package com.example.core.book_DDD.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id;

    @Column(name = "release_year")
    private Integer releaseYear;

    private String title;
}
