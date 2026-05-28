package com.nycu_epps.gameRatingBackEnd.controller;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import com.nycu_epps.gameRatingBackEnd.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    // 對應前端的呼叫: GET /api/games?title=關鍵字
    @GetMapping
    public Page<GameRatingResponse> searchGames(
            // 加上 defaultValue = ""，確保沒有輸入關鍵字時，預設搜尋全部 (LIKE '%%')
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            // 將 defaultValue 改為 "0"，配合 Spring Data JPA 從 0 開始算第一頁的特性
            @RequestParam(value = "platform", required = false, defaultValue = "") String platform,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return gameService.searchGames(title,platform, page, size);
    }

    @GetMapping("/rank")
    public List<GameRatingResponse> getRanking(
            @RequestParam(value = "platform") String platform,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        return gameService.getPlatformRanking(platform, limit);
    }

    // 💡 新增：取得所有平台清單的 API
    @GetMapping("/platforms")
    public List<String> getAllPlatforms() {
        return gameService.getAllPlatformNames();
    }


}
