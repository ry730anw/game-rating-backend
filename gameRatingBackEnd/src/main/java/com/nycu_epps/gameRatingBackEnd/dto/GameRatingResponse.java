package com.nycu_epps.gameRatingBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data // Lombok 自動產生 Getter, Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingResponse {
    private String title;
    private String platformName;
    private BigDecimal metascore;
    private BigDecimal userScore;

}
