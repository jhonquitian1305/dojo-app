package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.models.Comment;

public interface CommentService {
    Comment createOne(CommentDTO commentDTO) throws Exception;
    CommentResponse getAllByCondition(int numberPage, int pageSize, String sortBy, String sortDir, String model, Long idCondition) throws Exception;
    Comment getById(Long id);
    Comment updateOne(Long id, CommentDTO commentDTO) throws Exception;
}
