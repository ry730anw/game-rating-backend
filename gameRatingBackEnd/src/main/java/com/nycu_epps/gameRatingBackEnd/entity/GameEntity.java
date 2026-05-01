package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Game")
@Data
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 設定為自動遞增 (AUTO_INCREMENT)
    @Column(name = "game_id")
    private Integer gameId;

    private String title;
}
