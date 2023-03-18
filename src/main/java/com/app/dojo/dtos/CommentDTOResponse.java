package com.app.dojo.dtos;

import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;

import java.time.LocalDate;

public class CommentDTOResponse {
    private Long id;
    private LocalDate dateCommment;
    private String comment;
    private Course course;
    private Teacher teacher;
    private Student student;

    public CommentDTOResponse() {
    }

    public CommentDTOResponse(Long id, LocalDate dateComment, String comment, Course course, Teacher teacher, Student student) {
        this.id = id;
        this.dateCommment = dateComment;
        this.comment = comment;
        this.course = course;
        this.teacher = teacher;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCommment() {
        return dateCommment;
    }

    public void setDateCommment(LocalDate date) {
        this.dateCommment = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
