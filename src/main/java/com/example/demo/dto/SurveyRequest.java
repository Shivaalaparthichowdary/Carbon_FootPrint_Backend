package com.example.demo.dto;

public class SurveyRequest {
    private String transportMode; // "car", "bike", "public"
    private Double kilometers;    // Distance traveled
    private String dietType;      // "meat", "vegetarian", "vegan"
    private Double energyUsage;   // in kWh

    // Constructor
    public SurveyRequest() {}

    public SurveyRequest(String transportMode, Double kilometers, String dietType, Double energyUsage) {
        this.transportMode = transportMode;
        this.kilometers = kilometers;
        this.dietType = dietType;
        this.energyUsage = energyUsage;
    }

    // Getters and Setters
    public String getTransportMode() { return transportMode; }
    public void setTransportMode(String transportMode) { this.transportMode = transportMode; }

    public Double getKilometers() { return kilometers; }
    public void setKilometers(Double kilometers) { this.kilometers = kilometers; }

    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }

    public Double getEnergyUsage() { return energyUsage; }
    public void setEnergyUsage(Double energyUsage) { this.energyUsage = energyUsage; }
}