package com.example.demo.controller;

import com.example.demo.dto.CalculationResponse;
import com.example.demo.dto.SurveyRequest;
import com.example.demo.Entity.CarbonLog;
import com.example.demo.service.CarbonCalculatorService;
import com.example.demo.service.RecommendationService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeaderboardDTO;
import com.example.demo.repository.CarbonLogRepository; // 1. Import this

import java.util.List;

@RestController
@RequestMapping("/api/carbon")
@CrossOrigin(origins = "http://localhost:3000") // Best practice: Specify the frontend URL
public class CarbonController {

    @Autowired
    private CarbonCalculatorService calculatorService;
    
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private CarbonLogRepository carbonLogRepository; // 2. Inject the Repository here

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestHeader("Authorization") String token, 
                                                         @RequestBody SurveyRequest request) {
        
        String email = jwtUtil.extractUsername(token.substring(7));
        
        // 1. Calculate the emissions
        CarbonLog log = calculatorService.calculateAndSave(request, email);
        
        // 2. Generate the smart advice
        List<String> tips = recommendationService.generateRecommendations(log);
        
        // 3. Return both together
        return ResponseEntity.ok(new CalculationResponse(log, tips));
    }

    @GetMapping("/history")
    public ResponseEntity<List<CarbonLog>> getHistory(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return ResponseEntity.ok(calculatorService.getHistory(email));
    }

    // NEW ENDPOINT: Get Leaderboard
    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderboardDTO>> getLeaderboard() {
        // Now this works because carbonLogRepository exists
        List<LeaderboardDTO> leaderboard = carbonLogRepository.findLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }
}