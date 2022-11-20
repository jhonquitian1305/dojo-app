package com.app.dojo.dtos;

import com.app.dojo.constants.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoomDTO {
    private Long id;
    @NotNull(message = Message.FIELD_NULL)
    @NotEmpty(message = Message.FIELD_EMPTY)
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
