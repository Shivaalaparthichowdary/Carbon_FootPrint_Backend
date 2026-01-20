package com.example.demo.dto;

public class LeaderboardDTO {
    private String name;
    private Double totalScore;

    public LeaderboardDTO(String name, Double totalScore) {
        this.name = name;
        this.totalScore = totalScore;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getTotalScore() { return totalScore; }
    public void setTotalScore(Double totalScore) { this.totalScore = totalScore; }
}