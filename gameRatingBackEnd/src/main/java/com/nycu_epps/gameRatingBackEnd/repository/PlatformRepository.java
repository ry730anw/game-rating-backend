package com.nycu_epps.gameRatingBackEnd.repository;

import com.nycu_epps.gameRatingBackEnd.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlatformRepository extends JpaRepository<PlatformEntity,Integer> {
    // 撈出所有平台的名稱，並依照英文字母排序
    @Query("SELECT p.platformName FROM PlatformEntity p ORDER BY p.platformName ASC")
    List<String> findAllPlatformNames();
}
