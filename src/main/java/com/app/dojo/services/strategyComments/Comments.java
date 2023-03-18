package com.app.dojo.services.strategyComments;

import com.app.dojo.models.Comment;
import com.app.dojo.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class Comments implements CommentsStrategy {

    private final CommentRepository commentRepository;

    public Comments(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findComments(Pageable pageable, Long idCondition) {
        return this.commentRepository.findAll(pageable);
    }
}
