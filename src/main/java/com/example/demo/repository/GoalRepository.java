package com.example.demo.repository;

import com.example.demo.Entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    
    // CHANGED: "Long userId" -> "Integer userId" to match your User entity
    Optional<Goal> findByUserIdAndMonthAndYear(Integer userId, int month, int year);
}