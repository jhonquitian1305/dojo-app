package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.models.Comment;

import java.util.List;

public class CommentResponseBuilder {
    private List<CommentDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private boolean lastOne;

    public CommentResponseBuilder setContent(List<CommentDTO> content) {
        this.content = content;
        return this;
    }

    public CommentResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public CommentResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public CommentResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public CommentResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public CommentResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public CommentResponse build(){
        return new CommentResponse(content, numberPage, sizePage, totalElements, totalPages, lastOne);
    }
}
