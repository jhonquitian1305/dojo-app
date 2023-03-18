package com.app.dojo.dtos;

import java.util.List;

public class LevelResponse {
    private List<LevelDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public LevelResponse(List<LevelDTO> content, int numberPage, int sizePage, Long totalElements, int totalPages, boolean lastOne) {
        this.content = content;
        this.numberPage = numberPage;
        this.sizePage = sizePage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastOne = lastOne;
    }

    public LevelResponse() {
    }

    public List<LevelDTO> getContent() {
        return content;
    }

    public void setContent(List<LevelDTO> content) {
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
