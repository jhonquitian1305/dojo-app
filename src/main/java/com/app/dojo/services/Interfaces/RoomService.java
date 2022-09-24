package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.models.Room;

public interface RoomService {
    public RoomDTO create(RoomDTO roomDTO)throws Exception;
    public RoomDTO findById(Long id) throws  Exception;
}
