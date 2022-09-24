package com.app.dojo.builders.builderDTO;

import com.app.dojo.dtos.RoomDTO;

public class RoomDTOBuilder {
    private Long id;
    private String roomName;

    public RoomDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomDTOBuilder setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public RoomDTO build(){
        return new RoomDTO(id, roomName);
    }
}
