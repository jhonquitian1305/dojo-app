package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.CommentDTOBuilder;
import com.app.dojo.builders.builderDTO.CommentDTOResponseBuilder;
import com.app.dojo.builders.builderModels.CommentBuilder;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentDTOResponse;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
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

    public Comment createComment(CommentDTO commentDTO, Course course, Teacher teacher, Student student){
        return new CommentBuilder()
                .setDateComment(commentDTO.getDateComment())
                .setComment(commentDTO.getComment())
                .setCourse(course)
                .setTeacher(teacher)
                .setStudent(student)
                .build();
    }

    public CommentDTO mapCommentDTO(Comment comment){
        return new CommentDTOBuilder()
                .setId(comment.getId())
                .setDateComment(comment.getDateComment())
                .setComment(comment.getComment())
                .setTeacher(comment.getTeacher().getId())
                .setStudent(comment.getStudent().getId())
                .build();
    }
}
