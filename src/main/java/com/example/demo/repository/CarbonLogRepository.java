package com.example.demo.repository;

import com.example.demo.Entity.CarbonLog;
import com.example.demo.dto.LeaderboardDTO; // Import the DTO
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CarbonLogRepository extends JpaRepository<CarbonLog, Long> {
    
    List<CarbonLog> findByUserId(Integer userId);

    // NEW: Smart Query for Leaderboard
    // It groups logs by user, sums them up, and sorts by lowest emission (ASC)
    @Query("SELECT new com.example.demo.dto.LeaderboardDTO(u.name, SUM(c.totalEmission)) " +
           "FROM CarbonLog c JOIN c.user u " +
           "GROUP BY u.name " +
           "ORDER BY SUM(c.totalEmission) ASC")
    List<LeaderboardDTO> findLeaderboard();
}