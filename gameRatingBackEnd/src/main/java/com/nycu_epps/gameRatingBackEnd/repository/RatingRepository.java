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
            "JOIN gr.platform p " +  // 💡 把 platform JOIN 進來
            "WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND (:platform = '' OR p.platformName = :platform)") // 💡 神奇的選填條件寫法
    Page<RatingEntity> searchGamesByTitleAndPlatform(
            @Param("keyword") String keyword,
            @Param("platform") String platform,
            Pageable pageable);

    @Query("SELECT r FROM RatingEntity r " +
            "JOIN r.gameRelease gr " +
            "JOIN gr.platform p " +
            "WHERE p.platformName = :platformName " +
            "ORDER BY r.metascore DESC")
    List<RatingEntity> findTopGamesByPlatform(@Param("platformName") String platformName, Pageable pageable);
}
