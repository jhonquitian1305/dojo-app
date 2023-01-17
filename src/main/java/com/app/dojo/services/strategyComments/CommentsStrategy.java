package com.app.dojo.services.strategyComments;

import com.app.dojo.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentsStrategy {
    Page<Comment> findComments(Pageable pageable, Long idCondition) throws Exception;
}
