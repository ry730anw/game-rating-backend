package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genre")
@Data
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name")
    private String genreName;
}
