package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CommentDTOResponse;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;

import java.time.LocalDate;

public class CommentDTOResponseBuilder {
    private Long id;
    private LocalDate dateComment;
    private String comment;
    private Course course;
    private Teacher teacher;
    private Student student;

    public CommentDTOResponseBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CommentDTOResponseBuilder setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
        return this;
    }

    public CommentDTOResponseBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentDTOResponseBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public CommentDTOResponseBuilder setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public CommentDTOResponseBuilder setStudent(Student student) {
        this.student = student;
        return this;
    }

    public CommentDTOResponse build(){
        return new CommentDTOResponse(id, dateComment, comment, course, teacher, student);
    }
}
