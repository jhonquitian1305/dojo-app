package com.app.dojo.services.strategyComments;

import com.app.dojo.models.Comment;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class CommentsStudent implements CommentsStrategy {

    private final StudentService studentService;
    private final CommentRepository commentRepository;

    public CommentsStudent(StudentService studentService, CommentRepository commentRepository) {
        this.studentService = studentService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findComments(Pageable pageable, Long idCondition) throws Exception {
        Student studentFound = this.studentService.getStudentById(idCondition);
        return this.commentRepository.findByStudent(studentFound, pageable);
    }
}
