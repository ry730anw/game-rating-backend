package com.nycu_epps.gameRatingBackEnd.repository;

import com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse;
import com.nycu_epps.gameRatingBackEnd.entity.RatingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {

    @Query("SELECT r FROM RatingEntity r " +
            "JOIN r.gameRelease gr " +
            "JOIN gr.game g " +
            "JOIN gr.platform p " +
            // 注意！這裡不要寫 LEFT JOIN g.genres gen
            "WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND (:platform = '' OR p.platformName = :platform) " +
            "AND (:year = '' OR CAST(gr.releaseDate AS string) LIKE CONCAT(:year, '%')) " +
            "AND (:minScore IS NULL OR r.metascore >= :minScore) " +
            // 💡 關鍵魔法：用 EXISTS 來取代 JOIN
            "AND (:genre = '' OR EXISTS (SELECT 1 FROM g.genres gen WHERE LOWER(gen.genreName) LIKE LOWER(CONCAT('%', :genre, '%'))))")

    Page<RatingEntity> searchGamesByTitleAndPlatform(
            @Param("keyword") String keyword,
            @Param("platform") String platform,
            @Param("genre") String genre,
            @Param("year") String year,
            @Param("minScore") Integer minScore,
            Pageable pageable);

    @Query("SELECT r FROM RatingEntity r " +
            "JOIN r.gameRelease gr " +
            "JOIN gr.platform p " +
            "WHERE p.platformName = :platformName " +
            "ORDER BY r.metascore DESC")
    List<RatingEntity> findTopGamesByPlatform(@Param("platformName") String platformName, Pageable pageable);
}
