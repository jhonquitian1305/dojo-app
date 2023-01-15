package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class CommentBuilder {
    private Long id;
    private LocalDate dateComment;
    private String comment;
    private Course course;
    private Teacher teacher;
    private Student student;

    public CommentBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder setDateComment(LocalDate dateComment) {
        this.dateComment = dateComment;
        return this;
    }

    public CommentBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public CommentBuilder setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public CommentBuilder setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Comment build() {
        return new Comment(id, dateComment, comment, course, teacher, student);
    }
}
