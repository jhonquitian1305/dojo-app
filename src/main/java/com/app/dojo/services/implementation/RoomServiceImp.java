package com.app.dojo.services.implementation;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperRoom;
import com.app.dojo.mappers.MapperRoomDTO;
import com.app.dojo.models.Room;
import com.app.dojo.repositories.RoomRepository;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDTO create(RoomDTO roomDTO) throws BadRequest {
        Optional<Room> roomFound=this.roomRepository.findByRoomName(roomDTO.getRoomName());
        if(roomFound.isPresent()){
            throw new BadRequest("These room already exists");
        }
        Room roomCreated=this.roomRepository.save(MapperRoom.mapperRoom(roomDTO));
        return MapperRoomDTO.mapperRoomDTO(roomCreated);
    }

    @Override
    public RoomDTO findById(Long id) throws Exception {
        Optional<Room> roomFound= this.roomRepository.findById(id);
        if(!roomFound.isPresent()){
            throw new NotFoundException("Doesn't exists a room with that id  %s".formatted(id));
        }
        return MapperRoomDTO.mapperRoomDTO(roomFound.get());
    }

    @Override
    public RoomDTO findByName(RoomDTO roomDTO) throws NotFoundException {
        Optional<Room> roomFound=this.roomRepository.findByRoomName(roomDTO.getRoomName());
        if(!roomFound.isPresent()){
            throw new NotFoundException("Doesn't exists a room with that name %s".formatted(roomDTO.getRoomName()));
        }
        return  MapperRoomDTO.mapperRoomDTO(roomFound.get());
    }


}
