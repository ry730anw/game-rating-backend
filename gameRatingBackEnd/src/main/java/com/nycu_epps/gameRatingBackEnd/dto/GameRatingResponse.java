package com.nycu_epps.gameRatingBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data // Lombok 自動產生 Getter, Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingResponse {
    private String title;
    private String platformName;
    private Integer metascore;  // 配合 Entity 改為 Integer
    private Float userScore;    // 配合 Entity 改為 Float
    private String summary;           // 遊戲簡介
    private LocalDate releaseDate;    // 發售日期
    private Integer mediaReviewCount; // 媒體評審人數
    private Integer userReviewCount;  // 玩家評審人數
    private String developer;         // 開發廠商 (會將多個合併成字串)
    private String genre;             // 遊戲類型 (會將多個合併成字串)
}
