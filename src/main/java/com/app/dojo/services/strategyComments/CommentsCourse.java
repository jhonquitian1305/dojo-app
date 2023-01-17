package com.app.dojo.services.strategyComments;

import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommentsCourse implements CommentsStrategy {

    private final CourseService courseService;
    private final CommentRepository commentRepository;

    public CommentsCourse(CourseService courseService, CommentRepository commentRepository) {
        this.courseService = courseService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findComments(Pageable pageable, Long idCondition) {
        Course courseFound = this.courseService.getOne(idCondition);
        return this.commentRepository.findByCourse(courseFound, pageable);
    }
}
