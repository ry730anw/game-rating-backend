package com.nycu_epps.gameRatingBackEnd.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor // 增加無參數建構子，確保 JSON 反序列化正常運作
public class GameRatingRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String gameName; // 首字母改為小寫，符合 Java 慣例

    private int page;

    private int size;
}
