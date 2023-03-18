package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperRoom;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_ROOMS)
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private MapperRoom mapperRoom;
    @PostMapping()
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO)throws Exception{
        RoomDTO roomDTOCreated= mapperRoom.mapperRoomDTO(this.roomService.create(roomDTO));
        return new ResponseEntity(roomDTOCreated, HttpStatus.CREATED);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) throws Exception{
        RoomDTO roomFound = mapperRoom.mapperRoomDTO(this.roomService.findById(id));
        return ResponseEntity.ok(roomFound);
    }

    @GetMapping(ENDPOINT_ROOM_BY_NAME)
    public ResponseEntity<RoomDTO> getByName(@PathVariable("room") String room)throws NotFoundException{
        RoomDTO roomFound = mapperRoom.mapperRoomDTO(this.roomService.findByName(room));
        return ResponseEntity.ok(roomFound);
    }

    @GetMapping()
    public ResponseEntity<RoomResponse> finadAll(
            @RequestParam(value = "numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false)int pageSize

    ){
       return ResponseEntity.ok( this.roomService.findAll(numberPage,pageSize));
    }


    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        this.roomService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
