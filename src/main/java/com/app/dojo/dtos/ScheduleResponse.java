package com.app.dojo.dtos;

import java.util.List;

public class ScheduleResponse {
    private List<ScheduleDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public ScheduleResponse(List<ScheduleDTO> content, int numberPage, int sizePage, Long totalElements, int totalPages, boolean lastOne) {
        this.content = content;
        this.numberPage = numberPage;
        this.sizePage = sizePage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastOne = lastOne;
    }

    public List<ScheduleDTO> getContent() {
        return content;
    }

    public ScheduleResponse setContent(List<ScheduleDTO> content) {
        this.content = content;
        return this;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public ScheduleResponse setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public int getSizePage() {
        return sizePage;
    }

    public ScheduleResponse setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public ScheduleResponse setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ScheduleResponse setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public boolean isLastOne() {
        return lastOne;
    }

    public ScheduleResponse setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }
}
