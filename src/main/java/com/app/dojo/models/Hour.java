package com.app.dojo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hour;

    @ManyToMany(mappedBy = "hours")
    private Set<Schedule> schedules;

    public Hour() {
    }

    public Hour(Long id, String hour) {
        this.id = id;
        this.hour = hour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
