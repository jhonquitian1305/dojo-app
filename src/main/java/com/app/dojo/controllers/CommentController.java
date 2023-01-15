package com.app.dojo.controllers;

import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentDTOResponse;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.services.Interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_COMMENTS;

@RestController
@RequestMapping(ENDPOINT_COMMENTS)
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MapperComment mapperComment;

    @PostMapping
    public ResponseEntity<CommentDTOResponse> createOne(@RequestBody CommentDTO commentDTO) throws Exception {
        CommentDTOResponse commentCreated = this.mapperComment.mapCommentDTOResponse(this.commentService.createOne(commentDTO));
        return new ResponseEntity<>(commentCreated, HttpStatus.CREATED);
    }
}
