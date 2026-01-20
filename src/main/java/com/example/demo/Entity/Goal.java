package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double targetEmission;
    private int month;
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // --- MANUAL GETTERS AND SETTERS (Since Lombok is off) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTargetEmission() {
        return targetEmission;
    }

    public void setTargetEmission(Double targetEmission) {
        this.targetEmission = targetEmission;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}