package com.app.dojo.dtos;

import java.util.List;

public class StudentResponse {
    private List<StudentDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public StudentResponse() {
    }

    public StudentResponse(List<StudentDTO> content, int numberPage, int sizePage, Long totalElements, int totalPages, boolean lastOne) {
        this.content = content;
        this.numberPage = numberPage;
        this.sizePage = sizePage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastOne = lastOne;
    }

    public List<StudentDTO> getContent() {
        return content;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public int getSizePage() {
        return sizePage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLastOne() {
        return lastOne;
    }
}
