package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "rating")
@Data
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer ratingId;

    // 關聯到發行版本
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private GameReleaseEntity gameRelease;

    @Column(name = "media_review_count")
    private Integer mediaReviewCount;

    @Column(name = "user_review_count")
    private Integer userReviewCount;

    @Column(name = "metascore")
    private Integer metascore;

    @Column(name = "user_score")
    private Float userScore;
}

