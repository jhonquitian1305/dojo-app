package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;

import java.util.List;

public class LevelResponseBuilder {
    private List<LevelDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public LevelResponseBuilder() {
    }

    public LevelResponseBuilder setContent(List<LevelDTO> content) {
        this.content = content;
        return this;
    }

    public LevelResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public LevelResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public LevelResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public LevelResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public LevelResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public LevelResponse build(){
        return  new LevelResponse(content,numberPage,sizePage,totalElements,totalPages,lastOne);
    }
}
