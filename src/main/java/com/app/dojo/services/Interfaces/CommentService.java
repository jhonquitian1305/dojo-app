package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.models.Comment;

public interface CommentService {
    Comment createOne(CommentDTO commentDTO) throws Exception;
}
