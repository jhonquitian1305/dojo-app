package com.app.dojo.services.implementation;

import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CommentService;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MapperComment mapperComment;

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Override
    public Comment createOne(CommentDTO commentDTO) throws Exception {
        Course courseFound = this.courseService.getOne(commentDTO.getCourse());
        Teacher teacherFound = this.teacherService.getById(commentDTO.getTeacher());
        Student studentFound = this.studentService.getStudentById(commentDTO.getStudent());

        Comment commentCreated = this.commentRepository.save(this.mapperComment.createComment(commentDTO, courseFound, teacherFound, studentFound));

        return commentCreated;
    }
}
