package com.example.demo.repository;

import com.example.demo.Entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    List<Survey> findByUserId(Integer userId);
}