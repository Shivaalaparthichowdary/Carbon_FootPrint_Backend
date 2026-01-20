package com.example.demo.dto;

public class GoalStatusDTO {
    private Double target;
    private Double actual;

    // Default Constructor
    public GoalStatusDTO() {}

    // Manual Constructor (Fixes the "new GoalStatusDTO(...)" error)
    public GoalStatusDTO(Double target, Double actual) {
        this.target = target;
        this.actual = actual;
    }

    // Getters and Setters
    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }
}