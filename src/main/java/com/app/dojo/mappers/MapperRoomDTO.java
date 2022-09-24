package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.models.Room;

public class MapperRoomDTO {
    public static RoomDTO mapperRoomDTO(Room room){
        return  new RoomDTOBuilder()
                .setId(room.getId())
                .setRoomName(room.getRoomName())
                .build();
    }
}
