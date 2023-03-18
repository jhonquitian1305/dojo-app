package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;

import java.util.List;

public class StudentResponseBuilder {
    private List<StudentDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private boolean lastOne;

    public StudentResponseBuilder setContent(List<StudentDTO> content) {
        this.content = content;
        return this;
    }

    public StudentResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public StudentResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public StudentResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public StudentResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public StudentResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public StudentResponse build(){
        return new StudentResponse(content, numberPage, sizePage, totalElements, totalPages, lastOne);
    }
}
