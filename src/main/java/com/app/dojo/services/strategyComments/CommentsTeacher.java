package com.app.dojo.services.strategyComments;

import com.app.dojo.models.Comment;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommentsTeacher implements CommentsStrategy {

    private final TeacherService teacherService;
    private final CommentRepository commentRepository;

    public CommentsTeacher(TeacherService teacherService, CommentRepository commentRepository) {
        this.teacherService = teacherService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findComments(Pageable pageable, Long idCondition) {
        Teacher teacherFound = this.teacherService.getById(idCondition);
        return this.commentRepository.findByTeacher(teacherFound, pageable);
    }
}
