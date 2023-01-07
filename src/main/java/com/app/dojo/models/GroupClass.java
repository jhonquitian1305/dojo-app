package com.app.dojo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class GroupClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nameClass;
    private Long totalHours;
    private Long weeks;
    private Long hoursPerWeek;
    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "groups_rooms",
            joinColumns = @JoinColumn(name ="group_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;
    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "groups_schedules",
            joinColumns = @JoinColumn(name ="group_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedule> schedules;

    public GroupClass(Long id, String code, String nameClass, Long totalHours, Long weeks, Long hoursPerWeek, Course course, List<Room> rooms, List<Schedule> schedules) {
        this.id = id;
        this.code = code;
        this.nameClass = nameClass;
        this.totalHours = totalHours;
        this.weeks = weeks;
        this.hoursPerWeek = hoursPerWeek;
        this.course = course;
        this.rooms = rooms;
        this.schedules = schedules;
    }

    public GroupClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    public Long getWeeks() {
        return weeks;
    }

    public void setWeeks(Long weeks) {
        this.weeks = weeks;
    }

    public Long getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Long hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
