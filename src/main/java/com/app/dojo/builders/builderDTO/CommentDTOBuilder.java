package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CommentDTO;

import java.time.LocalDate;

public class CommentDTOBuilder {
    private Long id;
    private LocalDate dateComment;
    private String comment;
    private Long course;
    private Long teacher;
    private Long student;

    public CommentDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CommentDTOBuilder setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
        return this;
    }

    public CommentDTOBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentDTOBuilder setCourse(Long course) {
        this.course = course;
        return this;
    }

    public CommentDTOBuilder setTeacher(Long teacher) {
        this.teacher = teacher;
        return this;
    }

    public CommentDTOBuilder setStudent(Long student) {
        this.student = student;
        return this;
    }

    public CommentDTO build(){
        return new CommentDTO(id, dateComment, comment, course, teacher, student);
    }
}
