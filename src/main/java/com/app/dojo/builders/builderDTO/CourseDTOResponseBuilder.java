package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CourseDTOResponse;
import com.app.dojo.models.*;

import java.util.Date;
import java.util.List;

public class CourseDTOResponseBuilder {
    private Long id;
    private String name;
    private Double price;
    private Date startDate;
    private Date finishDate;
    private Level level;
    private List<Teacher> teachers;
    private List<Student> students;

    public CourseDTOResponseBuilder() {
    }

    public CourseDTOResponseBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CourseDTOResponseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CourseDTOResponseBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public CourseDTOResponseBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public CourseDTOResponseBuilder setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    public CourseDTOResponseBuilder setLevel(Level level) {
        this.level = level;
        return this;
    }

    public CourseDTOResponseBuilder setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public CourseDTOResponseBuilder setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public CourseDTOResponse build(){
        return  new CourseDTOResponse(id,name,price,startDate,finishDate,level, teachers, students);
    }
}
