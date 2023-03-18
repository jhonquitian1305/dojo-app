package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.models.Room;
import org.springframework.stereotype.Component;

@Component
public class MapperRoom {
    public  Room mapperRoom(RoomDTO roomDTO){
        return  new RoomBuilder()
                .setId(roomDTO.getId())
                .setRoomName(roomDTO.getRoomName())
                .build();
    }

    public  RoomDTO mapperRoomDTO(Room room){
        return  new RoomDTOBuilder()
                .setId(room.getId())
                .setRoomName(room.getRoomName())
                .build();
    }
}
