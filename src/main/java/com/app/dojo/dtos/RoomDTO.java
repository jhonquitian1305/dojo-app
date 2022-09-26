package com.app.dojo.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoomDTO {
    private Long id;
    @NotNull
    @NotEmpty
    private String roomName;

    public RoomDTO() {
    }

    public RoomDTO(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
