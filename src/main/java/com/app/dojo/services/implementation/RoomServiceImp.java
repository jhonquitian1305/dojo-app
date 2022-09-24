package com.app.dojo.services.implementation;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.mappers.MapperRoom;
import com.app.dojo.mappers.MapperRoomDTO;
import com.app.dojo.models.Room;
import com.app.dojo.repositories.RoomRepository;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDTO create(RoomDTO roomDTO){
        Room roomCreated=this.roomRepository.save(MapperRoom.mapperRoom(roomDTO));
        return MapperRoomDTO.mapperRoomDTO(roomCreated);
    }
}
