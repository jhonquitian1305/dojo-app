package com.app.dojo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
    private List<Hour> hours;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
    private List<Day> days;

    public Schedule() {
    }

    public Schedule(Long id, List<Hour> hours, List<Day> days) {
        this.id = id;
        this.hours = hours;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
