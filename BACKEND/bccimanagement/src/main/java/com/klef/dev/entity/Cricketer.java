package com.klef.dev.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cricketer_table")
public class Cricketer {
    @Id
    @Column(name = "cricketer_id")
    private int id;
    
    @Column(name = "cricketer_name", nullable = false, length = 50)
    private String name;
    
    @Column(name = "cricketer_age", nullable = false)
    private int age;
    
    @Column(name = "cricketer_role", nullable = false, length = 20)
    private String role; // Batsman, Bowler, All-rounder, Wicket-keeper
    
    @Column(name = "cricketer_team", nullable = false, length = 30)
    private String team;
    
    @Column(name = "cricketer_batting_avg", nullable = false)
    private double battingAverage;
    
    @Column(name = "cricketer_bowling_avg", nullable = false)
    private double bowlingAverage;
    
    @Column(name = "cricketer_matches", nullable = false)
    private int matchesPlayed;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public double getBowlingAverage() {
        return bowlingAverage;
    }

    public void setBowlingAverage(double bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Override
    public String toString() {
        return "Cricketer [id=" + id + ", name=" + name + ", age=" + age + ", role=" + role + ", team=" + team
                + ", battingAverage=" + battingAverage + ", bowlingAverage=" + bowlingAverage + ", matchesPlayed="
                + matchesPlayed + "]";
    }
}