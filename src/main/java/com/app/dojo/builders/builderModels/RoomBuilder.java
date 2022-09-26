package com.app.dojo.builders.builderModels;

import com.app.dojo.models.Room;

public class RoomBuilder {
    private Long id;
    private String roomName;


    public RoomBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public Room build(){
        return new Room(id, roomName);
    }
}
