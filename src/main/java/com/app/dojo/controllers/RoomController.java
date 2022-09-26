package com.app.dojo.controllers;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.exception.errors.NotFoundException;
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
    @PostMapping()
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO)throws Exception{
        RoomDTO roomDTOCreated= this.roomService.create(roomDTO);
        return new ResponseEntity(roomDTOCreated, HttpStatus.CREATED);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) throws Exception{
        RoomDTO roomFound = this.roomService.findById(id);
        return ResponseEntity.ok(roomFound);
    }

    @GetMapping(ENDPOINT_ROOM_BY_NAME)
    public ResponseEntity<RoomDTO> getByName(@RequestBody RoomDTO roomDTO)throws NotFoundException{
        RoomDTO roomFound = this.roomService.findByName(roomDTO);
        return ResponseEntity.ok(roomFound);
    }

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> finadAll(){
        List<RoomDTO> rooms=this.roomService.findAll();
        return ResponseEntity.ok(rooms);
    }
}
