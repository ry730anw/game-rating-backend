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
    @Query("SELECT new com.nycu_epps.gameRatingBackEnd.dto.GameRatingResponse(g.title, p.platformName, r.metascore, r.userScore) " +
            "FROM RatingEntity r " +
            "JOIN r.game g " +
            "JOIN r.platform p " +
            "WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        // 1. 將 List 改成 Page，2. 參數加上 Pageable
    Page<GameRatingResponse> searchGamesByTitle(@Param("keyword") String keyword, Pageable pageable);
}
