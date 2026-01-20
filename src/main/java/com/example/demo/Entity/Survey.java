package com.example.demo.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Links this survey to a specific User

    @Column(name = "transport_mode")
    private String transportMode; // e.g., "Car", "Bike", "Bus"

    @Column(name = "diet_type")
    private String dietType; // e.g., "Vegetarian", "Meat"

    @Column(name = "energy_usage")
    private Double energyUsage; // e.g., 100.5 (kWh)

    @Column(columnDefinition = "json")
    private String frequency; // We will store this as a String for simplicity

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // --- CONSTRUCTORS ---
    public Survey() {}

    // --- GETTERS AND SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTransportMode() { return transportMode; }
    public void setTransportMode(String transportMode) { this.transportMode = transportMode; }

    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }

    public Double getEnergyUsage() { return energyUsage; }
    public void setEnergyUsage(Double energyUsage) { this.energyUsage = energyUsage; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}