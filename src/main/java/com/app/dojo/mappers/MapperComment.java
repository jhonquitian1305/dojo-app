package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.CommentDTOResponseBuilder;
import com.app.dojo.dtos.CommentDTOResponse;
import com.app.dojo.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class MapperComment {
    public CommentDTOResponse mapCommentDTOResponse(Comment comment){
        return new CommentDTOResponseBuilder()
                .setId(comment.getId())
                .setDateComment(comment.getDateComment())
                .setComment(comment.getComment())
                .setCourse(comment.getCourse())
                .setTeacher(comment.getTeacher())
                .setStudent(comment.getStudent())
                .build();
    }
}
