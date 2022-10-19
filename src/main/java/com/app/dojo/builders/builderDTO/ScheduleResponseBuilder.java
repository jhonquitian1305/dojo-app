package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;

import java.util.List;

public class ScheduleResponseBuilder {
    private List<ScheduleDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public ScheduleResponseBuilder setContent(List<ScheduleDTO> content) {
        this.content = content;
        return this;
    }

    public ScheduleResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public ScheduleResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public ScheduleResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public ScheduleResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public ScheduleResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public ScheduleResponse build(){
        return new ScheduleResponse(content,numberPage,sizePage,totalElements,totalPages,lastOne);
    }
}
