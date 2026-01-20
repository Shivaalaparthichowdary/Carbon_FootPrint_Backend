package com.example.demo.controller;

import com.example.demo.dto.GoalStatusDTO;
import com.example.demo.Entity.Goal;
import com.example.demo.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:3000") // <--- THIS WAS MISSING
public class GoalController {

    @Autowired
    private GoalService goalService;

    // 1. Set a Goal (POST)
    @PostMapping("/set")
    public ResponseEntity<?> setGoal(@RequestBody Map<String, Double> payload) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Double target = payload.get("target");
        goalService.setGoal(email, target);
        
        // Return a simple text message instead of the complex Entity object
        return ResponseEntity.ok(Map.of("message", "Goal set successfully"));
    }

    // 2. Get Progress (GET)
    @GetMapping("/status")
    public ResponseEntity<?> getGoalStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        GoalStatusDTO status = goalService.getGoalStatus(email);
        
        if (status == null) {
            return ResponseEntity.ok("No goal set");
        }
        
        return ResponseEntity.ok(status);
    }
}