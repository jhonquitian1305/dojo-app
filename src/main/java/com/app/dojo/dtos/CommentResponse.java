package com.app.dojo.dtos;

import com.app.dojo.models.Comment;

import java.util.List;

public class CommentResponse {
    private List<CommentDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private boolean lastOne;

    public CommentResponse() {
    }

    public CommentResponse(List<CommentDTO> content, int numberPage, int sizePage, Long totalElements, int totalPages, boolean lastOne) {
        this.content = content;
        this.numberPage = numberPage;
        this.sizePage = sizePage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastOne = lastOne;
    }

    public List<CommentDTO> getContent() {
        return content;
    }

    public void setContent(List<CommentDTO> content) {
        this.content = content;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastOne() {
        return lastOne;
    }

    public void setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
    }
}
