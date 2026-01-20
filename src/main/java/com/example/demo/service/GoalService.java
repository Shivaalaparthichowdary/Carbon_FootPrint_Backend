package com.example.demo.service;

import com.example.demo.Entity.CarbonLog; 
import com.example.demo.Entity.Goal;      
import com.example.demo.Entity.User;      
import com.example.demo.repository.CarbonLogRepository;
import com.example.demo.repository.GoalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.GoalStatusDTO; // Importing the separate file

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private CarbonLogRepository carbonLogRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Set or Update a Goal for the current month
    public Goal setGoal(String email, Double target) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        // Check if goal already exists for this month
        Goal goal = goalRepository.findByUserIdAndMonthAndYear(user.getId(), currentMonth, currentYear)
                .orElse(new Goal());

        goal.setUser(user);
        goal.setMonth(currentMonth);
        goal.setYear(currentYear);
        goal.setTargetEmission(target);

        return goalRepository.save(goal);
    }

    // 2. Get Goal Status (Target vs. Actual Usage)
    public GoalStatusDTO getGoalStatus(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        // Get the goal (default to 0 if not set)
        Goal goal = goalRepository.findByUserIdAndMonthAndYear(user.getId(), month, year).orElse(null);
        
        if (goal == null) {
            return null; // No goal set yet
        }

        // Calculate total emissions for this month
        List<CarbonLog> logs = carbonLogRepository.findByUserId(user.getId());
        
        // Filter logs for current month only
        double totalActual = logs.stream()
                .filter(log -> log.getDate().getMonthValue() == month && log.getDate().getYear() == year)
                .mapToDouble(CarbonLog::getTotalEmission)
                .sum();

        return new GoalStatusDTO(goal.getTargetEmission(), totalActual);
    }
}