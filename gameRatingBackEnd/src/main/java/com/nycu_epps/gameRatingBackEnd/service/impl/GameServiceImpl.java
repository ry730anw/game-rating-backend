package com.nycu_epps.gameRatingBackEnd.service.impl;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import com.nycu_epps.gameRatingBackEnd.entity.DeveloperEntity;
import com.nycu_epps.gameRatingBackEnd.entity.GenreEntity;
import com.nycu_epps.gameRatingBackEnd.entity.RatingEntity;
import com.nycu_epps.gameRatingBackEnd.repository.PlatformRepository;
import com.nycu_epps.gameRatingBackEnd.repository.RatingRepository;
import com.nycu_epps.gameRatingBackEnd.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final RatingRepository ratingRepository;
    private final PlatformRepository platformRepository; // 💡 注入新建立的 Repository
    @Override
    public Page<GameRatingResponse> searchGames(String title, String platform,int page, int size) {
        int validPage = (page > 0) ? page - 1 : 0;
        int validSize = (size > 0 && size <= 100) ? size : 10;
        String safeTitle = (title != null) ? title : "";

        String safePlatform = (platform != null) ? platform : "";
        Pageable pageable = PageRequest.of(validPage, validSize);

        Page<RatingEntity> entityPage = ratingRepository.searchGamesByTitleAndPlatform(safeTitle, safePlatform, pageable);
        return entityPage.map(this::convertToDto);
    }

    @Override
    public List<GameRatingResponse> getPlatformRanking(String platformName, int limit) {
        // 使用 PageRequest 來限制只撈取前 limit 筆資料 (Page 0, Size limit)
        Pageable topN = PageRequest.of(0, limit);

        List<RatingEntity> topEntities = ratingRepository.findTopGamesByPlatform(platformName, topN);

        // 將撈出來的 Entity 轉換成 DTO List 並回傳
        return topEntities.stream()
                .map(this::convertToDto)
                .toList(); // (Java 16+ 語法)
    }

    @Override
    public List<String> getAllPlatformNames() {
        return platformRepository.findAllPlatformNames();
    }

    private GameRatingResponse convertToDto(RatingEntity entity) {
        // 利用 Java Stream 把多個開發商/類型名稱抽出來，並用逗號連接
        String developers = entity.getGameRelease().getGame().getDevelopers().stream()
                .map(DeveloperEntity::getDeveloperName)
                .collect(Collectors.joining(", "));

        String genres = entity.getGameRelease().getGame().getGenres().stream()
                .map(GenreEntity::getGenreName)
                .collect(Collectors.joining(", "));

        return new GameRatingResponse(
                entity.getGameRelease().getGame().getTitle(),
                entity.getGameRelease().getPlatform().getPlatformName(),
                entity.getMetascore(),
                entity.getUserScore(),
                entity.getGameRelease().getGame().getSummary(),
                entity.getGameRelease().getReleaseDate(),
                entity.getMediaReviewCount(),
                entity.getUserReviewCount(),
                developers,
                genres
        );
    }


}
