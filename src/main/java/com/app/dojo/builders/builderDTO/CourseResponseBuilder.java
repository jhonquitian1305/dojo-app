package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.models.Course;

import java.util.List;

public class CourseResponseBuilder {
    private List<Course> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public CourseResponseBuilder() {
    }

    public CourseResponseBuilder setContent(List<Course> content) {
        this.content = content;
        return this;
    }

    public CourseResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public CourseResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public CourseResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public CourseResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public CourseResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public CourseResponse build(){
        return  new CourseResponse(content,numberPage,sizePage,totalElements,totalPages,lastOne);
    }


}
