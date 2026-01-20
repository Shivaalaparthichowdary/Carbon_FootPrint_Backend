package com.example.demo.service;

import com.example.demo.dto.SurveyRequest;
import com.example.demo.Entity.CarbonLog;
import com.example.demo.Entity.Survey;
import com.example.demo.Entity.User;
import com.example.demo.repository.CarbonLogRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarbonCalculatorService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private CarbonLogRepository carbonLogRepository;

    @Autowired
    private UserRepository userRepository;

    public CarbonLog calculateAndSave(SurveyRequest request, String userEmail) {
        // 1. Find the user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Save the raw Survey data
        Survey survey = new Survey();
        survey.setUser(user);
        survey.setTransportMode(request.getTransportMode());
        survey.setDietType(request.getDietType());
        survey.setEnergyUsage(request.getEnergyUsage());
        // For simplicity, we assume daily frequency for now
        survey.setFrequency("{\"mode\": \"daily\"}"); 
        surveyRepository.save(survey);

        // 3. Perform Calculations
        double transportEmission = calculateTransport(request.getTransportMode(), request.getKilometers());
        double dietEmission = calculateDiet(request.getDietType());
        double energyEmission = calculateEnergy(request.getEnergyUsage());
        double total = transportEmission + dietEmission + energyEmission;

        // 4. Save the Result (CarbonLog)
        CarbonLog log = new CarbonLog();
        log.setUser(user);
        log.setDate(LocalDate.now());
        log.setTransportEmission(transportEmission);
        log.setFoodEmission(dietEmission);
        log.setEnergyEmission(energyEmission);
        log.setTotalEmission(total);

        return carbonLogRepository.save(log);
    }

    // Helper Methods for Math
    private double calculateTransport(String mode, Double km) {
        if (km == null) return 0.0;
        switch (mode.toLowerCase()) {
            case "car": return km * 0.21;
            case "bike": return km * 0.0; // Bicycle is 0! (Motorbike would be ~0.1)
            case "motorbike": return km * 0.1;
            case "public": return km * 0.05;
            default: return 0.0;
        }
    }

    private double calculateDiet(String type) {
        if (type == null) return 0.0;
        switch (type.toLowerCase()) {
            case "meat": return 3.3; // High impact
            case "vegetarian": return 1.7; // Medium impact
            case "vegan": return 1.5; // Low impact
            default: return 0.0;
        }
    }

    private double calculateEnergy(Double kwh) {
        if (kwh == null) return 0.0;
        return kwh * 0.5; // Avg grid factor
    }

    public List<CarbonLog> getHistory(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return carbonLogRepository.findByUserId(user.getId());
    }
}