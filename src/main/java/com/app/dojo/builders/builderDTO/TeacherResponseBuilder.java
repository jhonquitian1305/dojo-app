package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;

import java.util.List;

public class TeacherResponseBuilder {
    private List<TeacherDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private boolean lastOne;

    public TeacherResponseBuilder setContent(List<TeacherDTO> content) {
        this.content = content;
        return this;
    }

    public TeacherResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public TeacherResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public TeacherResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public TeacherResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public TeacherResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public TeacherResponse build(){
        return new TeacherResponse(content, numberPage, sizePage, totalElements, totalPages, lastOne);
    }
}
