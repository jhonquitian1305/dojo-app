package com.app.dojo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dayName;
    private String hoursClass;

    public Schedule() {
    }

    public Schedule(Long id, String dayName, String hoursClass) {
        this.id = id;
        this.dayName = dayName;
        this.hoursClass = hoursClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getHoursClass() {
        return hoursClass;
    }

    public void setHoursClass(String hoursClass) {
        this.hoursClass = hoursClass;
    }
}
