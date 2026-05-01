package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Rating")
@Data
public class RatingEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ratingId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "game_id")
        private GameEntity game;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "platform_id")
        private PlatformEntity platform;

        private BigDecimal metascore;
        @Column(name = "user_score")
        private BigDecimal userScore;
    }

