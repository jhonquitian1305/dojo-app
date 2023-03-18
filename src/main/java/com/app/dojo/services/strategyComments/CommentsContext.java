package com.app.dojo.services.strategyComments;

import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentsContext {

    private CommentsStrategy commentsStrategy;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    public CommentsStrategy loadStrategy(String modelCondition){
        switch (modelCondition){
            case "COURSE":
                return new CommentsCourse(this.courseService, this.commentRepository);
            case "TEACHER":
                return new CommentsTeacher(this.teacherService, this.commentRepository);
            case "STUDENT":
                return new CommentsStudent(this.studentService, this.commentRepository);
            default:
                return new Comments(this.commentRepository);
        }
    }
}
