package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Room;

import java.util.List;

public interface RoomService {
    public Room create(RoomDTO roomDTO)throws Exception;
    public Room findById(Long id) throws  Exception;
    public Room findByName(String roomName) throws NotFoundException;
    public RoomResponse findAll(int numberPage, int pageSize);
    public void delete(Long id) throws Exception;
}
