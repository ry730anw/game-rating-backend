package com.nycu_epps.gameRatingBackEnd.controller;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import com.nycu_epps.gameRatingBackEnd.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    // 對應前端的呼叫: GET /api/games?title=關鍵字
    @GetMapping
    public Page<GameRatingResponse> searchGames(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return gameService.searchGames(title, page, size);
    }

}
