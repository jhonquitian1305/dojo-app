package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentDTOResponse;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.services.Interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_COMMENTS;
import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_ID;

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

    @GetMapping
    public ResponseEntity<CommentResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.commentService.getAll(numberPage, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<CommentDTOResponse> getById(@PathVariable("id") Long id){
        CommentDTOResponse commentFound = this.mapperComment.mapCommentDTOResponse(this.commentService.getById(id));
        return new ResponseEntity<>(commentFound, HttpStatus.OK);
    }
}
