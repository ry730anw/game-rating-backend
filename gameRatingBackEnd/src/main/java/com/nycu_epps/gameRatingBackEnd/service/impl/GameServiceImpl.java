package com.nycu_epps.gameRatingBackEnd.service.impl;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import com.nycu_epps.gameRatingBackEnd.repository.RatingRepository;
import com.nycu_epps.gameRatingBackEnd.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final RatingRepository ratingRepository;

    @Override
    public Page<GameRatingResponse> searchGames(String title, int pageNumber, int pageSize) {
        // --- [商業邏輯防呆區] ---
        // 1. Spring Data JPA 的分頁是從 0 開始算，但一般前端傳 1 代表第一頁，所以這裡減 1
        int page = (pageNumber > 0) ? pageNumber - 1 : 0;

        // 2. 避免前端亂傳 pageSize 導致伺服器崩潰，設定上下限 (最少 1 筆，最多 100 筆)
        int size = (pageSize > 0 && pageSize <= 100) ? pageSize : 10;

        // 3. 如果 title 是 null，就轉成空字串，這樣 LIKE '%%' 就會撈出所有資料
        String safeTitle = (title != null) ? title : "";

        // --- [產生分頁請求] ---
        Pageable pageable = PageRequest.of(page, size);

        // --- [呼叫 Repository] ---
        return ratingRepository.searchGamesByTitle(safeTitle, pageable);
    }
}
