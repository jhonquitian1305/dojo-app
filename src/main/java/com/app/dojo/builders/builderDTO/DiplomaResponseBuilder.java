package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.DiplomaResponse;
import com.app.dojo.models.Diploma;

import java.util.List;

public class DiplomaResponseBuilder {
    private List<Diploma> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private boolean lastOne;

    public DiplomaResponseBuilder setContent(List<Diploma> content) {
        this.content = content;
        return this;
    }

    public DiplomaResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public DiplomaResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public DiplomaResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public DiplomaResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public DiplomaResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public DiplomaResponse build(){
        return new DiplomaResponse(content, numberPage, sizePage, totalElements, totalPages, lastOne);
    }
}
