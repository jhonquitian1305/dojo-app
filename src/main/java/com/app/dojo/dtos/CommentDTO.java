package com.app.dojo.dtos;

import com.app.dojo.constants.Message;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CommentDTO {
    private Long id;

    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private LocalDate dateComment;

    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private String comment;

    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private Long course;

    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private Long teacher;

    @NotNull(message = Message.FIELD_NULL)
    @NotNull(message = Message.FIELD_EMPTY)
    private Long student;

    public CommentDTO(Long id, LocalDate dateComment, String comment, Long course, Long teacher, Long student) {
        this.id = id;
        this.dateComment = dateComment;
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

    public LocalDate getDateComment() {
        return dateComment;
    }

    public void setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }
}
