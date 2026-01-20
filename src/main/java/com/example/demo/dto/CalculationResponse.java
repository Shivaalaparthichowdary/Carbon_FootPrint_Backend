package com.example.demo.dto;

import com.example.demo.Entity.CarbonLog;
import java.util.List;

public class CalculationResponse {
    
    private CarbonLog carbonLog;
    private List<String> recommendations;

    public CalculationResponse(CarbonLog carbonLog, List<String> recommendations) {
        this.carbonLog = carbonLog;
        this.recommendations = recommendations;
    }

    // Getters and Setters
    public CarbonLog getCarbonLog() { return carbonLog; }
    public void setCarbonLog(CarbonLog carbonLog) { this.carbonLog = carbonLog; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
}