package com.nycu_epps.gameRatingBackEnd.service;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingRequest;
import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface GameService {
    Page<GameRatingResponse> searchGames(String title, int page, int size);
}
