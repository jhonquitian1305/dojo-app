package com.app.dojo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dayName;
    private String hoursClass;
    @ManyToMany(mappedBy = "schedules", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GroupClass> groups;

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

    public List<GroupClass> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupClass> groupClasses) {
        this.groups = groupClasses;
    }
}
