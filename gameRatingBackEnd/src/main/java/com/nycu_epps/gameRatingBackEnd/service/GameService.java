package com.nycu_epps.gameRatingBackEnd.service;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GameService {
    Page<GameRatingResponse> searchGames(String title,String platform,String genre, String year, Integer minScore,String sortBy,  int page, int size);
    List<GameRatingResponse> getPlatformRanking(String platformName, int limit);
    // 💡 新增：取得所有平台名稱的方法
    List<String> getAllPlatformNames();
    // 💡 方法簽章加上 String sortBy
}
