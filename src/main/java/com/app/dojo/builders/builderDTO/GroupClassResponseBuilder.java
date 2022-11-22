package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.models.GroupClass;

import java.util.List;

public class GroupClassResponseBuilder {
    private List<GroupClass> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public GroupClassResponseBuilder() {
    }

    public GroupClassResponseBuilder setContent(List<GroupClass> content) {
        this.content = content;
        return this;
    }

    public GroupClassResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public GroupClassResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public GroupClassResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public GroupClassResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public GroupClassResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public GroupClassResponse build(){
        return  new GroupClassResponse(content,numberPage,sizePage,totalElements,totalPages,lastOne);
    }
}
