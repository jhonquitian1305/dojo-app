package com.app.dojo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<String> hours=new ArrayList<String>();
    @ElementCollection
    private List<String> days = new ArrayList<String>();

    public Schedule() {
    }

    public Schedule(Long id, List<String> hours, List<String> days) {
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

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
