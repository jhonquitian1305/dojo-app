package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;

import java.util.List;

public class RoomResponseBuilder {
    private List<RoomDTO> content;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public RoomResponseBuilder setContent(List<RoomDTO> content) {
        this.content = content;
        return this;
    }

    public RoomResponseBuilder setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public RoomResponseBuilder setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public RoomResponseBuilder setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public RoomResponseBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public RoomResponseBuilder setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }

    public RoomResponse build(){
        return  new RoomResponse(content,numberPage,sizePage,totalElements,totalPages,lastOne);

    }
}
