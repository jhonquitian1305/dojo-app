package com.app.dojo.mappers;

import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.models.Room;

public class MapperRoom {
    public static Room mapperRoom(RoomDTO roomDTO){
        return  new RoomBuilder()
                .setId(roomDTO.getId())
                .setRoomName(roomDTO.getRoomName())
                .build();
    }
}
