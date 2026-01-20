package com.example.demo.service;

import com.example.demo.Entity.CarbonLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    public List<String> generateRecommendations(CarbonLog log) {
        List<String> tips = new ArrayList<>();

        // 1. Analyze Transport
        if (log.getTransportEmission() > 15.0) {
            tips.add("🚗 High Transport Emissions: Consider carpooling, using public transport, or combining trips.");
            tips.add("🚲 For short distances (under 5km), try biking or walking.");
        }

        // 2. Analyze Food
        if (log.getFoodEmission() > 3.0) {
            tips.add("🥩 High Diet Impact: Try 'Meat-Free Mondays' to reduce your footprint.");
            tips.add("🥗 Consider swapping one red meat meal per week for chicken or fish.");
        }

        // 3. Analyze Energy
        if (log.getEnergyEmission() > 20.0) {
            tips.add("💡 High Energy Usage: Switch to LED bulbs if you haven't already.");
            tips.add("🔌 Unplug electronics when not in use to stop 'vampire power'.");
        }

        // 4. Positive Reinforcement (if total is low)
        if (log.getTotalEmission() < 20.0) {
            tips.add("🌟 Outstanding job! Your carbon footprint is below the average.");
            tips.add("✅ Keep up the sustainable habits!");
        } else if (tips.isEmpty()) {
            // Fallback if no specific category was high, but total isn't "low" either
            tips.add("📉 You are doing okay, but there is always room for improvement!");
        }

        return tips;
    }
}