package com.elec5620.portal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String authors;

    @Column(nullable = false)
    private Double averageRating;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String isbn13;

    @Column(nullable = false)
    private String languageCode;

    @Column(nullable = false)
    private Integer numPages;

    @Column(nullable = false)
    private Integer ratingsCount;

    @Column(nullable = false)
    private Integer textReviewsCount;

    private String publicationDate;

    @Column(nullable = false)
    private String publisher;
}
