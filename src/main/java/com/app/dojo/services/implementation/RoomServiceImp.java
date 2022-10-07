package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.RoomResponseBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperRoom;
import com.app.dojo.models.Room;
import com.app.dojo.repositories.RoomRepository;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MapperRoom mapperRoom;

    @Override
    public RoomDTO create(RoomDTO roomDTO) throws BadRequest {
        Optional<Room> roomFound=this.roomRepository.findByRoomName(roomDTO.getRoomName());
        if(roomFound.isPresent()){
            throw new BadRequest("Already exists one room with that name");
        }
        Room roomCreated=this.roomRepository.save(mapperRoom.mapperRoom(roomDTO));
        return mapperRoom.mapperRoomDTO(roomCreated);
    }

    @Override
    public RoomDTO findById(Long id) throws Exception {
        Optional<Room> roomFound= this.roomRepository.findById(id);
        if(!roomFound.isPresent()){
            throw new NotFoundException("Doesn't exists a room with that id  %s".formatted(id));
        }
        return mapperRoom.mapperRoomDTO(roomFound.get());
    }

    @Override
    public RoomDTO findByName(RoomDTO roomDTO) throws NotFoundException {
        Optional<Room> roomFound=this.roomRepository.findByRoomName(roomDTO.getRoomName());
        if(!roomFound.isPresent()){
            throw new NotFoundException("Doesn't exists a room with that name %s".formatted(roomDTO.getRoomName()));
        }
        return  mapperRoom.mapperRoomDTO(roomFound.get());
    }

    @Override
    public RoomResponse findAll(int numberPage, int pageSize) {
        Pageable pageable= PageRequest.of(numberPage,pageSize);
        Page<Room> roomsFound=this.roomRepository.findAll(pageable);
        List<Room> allRoomsFound=roomsFound.getContent();
        List<RoomDTO> rooms=allRoomsFound.stream().map(room->mapperRoom.mapperRoomDTO(room)).collect(Collectors.toList());
        return  new RoomResponseBuilder()
                .setContent(rooms)
                .setNumberPage(roomsFound.getNumber())
                .setSizePage(roomsFound.getSize())
                .setTotalElements(roomsFound.getTotalElements())
                .setTotalPages(roomsFound.getTotalPages())
                .setLastOne(roomsFound.isLast())
                .build();
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Room> roomFound= this.roomRepository.findById(id);
        if(!roomFound.isPresent()){
            throw new NotFoundException("Doesn't exists a room with that id  %s".formatted(id));
        }
        this.roomRepository.deleteById(id);
    }


}
