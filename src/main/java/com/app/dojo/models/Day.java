package com.app.dojo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    @ManyToMany(mappedBy = "days")
    private Set<Schedule> schedules;

    public Day(Long id, String day) {
        this.id = id;
        this.day = day;
    }

    public Day() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
