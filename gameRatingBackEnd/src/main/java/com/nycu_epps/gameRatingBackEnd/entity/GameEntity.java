package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "game")
@Data
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "title")
    private String title;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    // 處理 game_developer 關聯表 (多對多)
    @ManyToMany
    @JoinTable(
            name = "game_developer",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<DeveloperEntity> developers;

    // 處理 game_genre 關聯表 (多對多)
    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genres;
}
