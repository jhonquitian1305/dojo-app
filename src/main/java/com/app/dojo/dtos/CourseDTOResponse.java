package com.app.dojo.dtos;

import com.app.dojo.models.*;

import java.util.Date;
import java.util.List;

public class CourseDTOResponse {
    private Long id;
    private String name;
    private Double price;
    private Date startDate;
    private Date finishDate;
    private Level level;
    private List<Teacher> teachers;
    private List<Student> students;

    public CourseDTOResponse(Long id, String name, Double price, Date startDate, Date finishDate, Level level, List<Teacher> teachers, List<Student> students) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.level = level;
        this.teachers = teachers;
        this.students = students;
    }

    public CourseDTOResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
