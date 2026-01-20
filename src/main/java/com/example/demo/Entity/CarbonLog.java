package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CarbonLogs")
public class CarbonLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate date;

    @Column(name = "transport_emission")
    private Double transportEmission;

    @Column(name = "food_emission")
    private Double foodEmission;

    @Column(name = "energy_emission")
    private Double energyEmission;

    @Column(name = "total_emission")
    private Double totalEmission;

    // --- CONSTRUCTORS ---
    public CarbonLog() {}

    // --- GETTERS AND SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getTransportEmission() { return transportEmission; }
    public void setTransportEmission(Double transportEmission) { this.transportEmission = transportEmission; }

    public Double getFoodEmission() { return foodEmission; }
    public void setFoodEmission(Double foodEmission) { this.foodEmission = foodEmission; }

    public Double getEnergyEmission() { return energyEmission; }
    public void setEnergyEmission(Double energyEmission) { this.energyEmission = energyEmission; }

    public Double getTotalEmission() { return totalEmission; }
    public void setTotalEmission(Double totalEmission) { this.totalEmission = totalEmission; }
}