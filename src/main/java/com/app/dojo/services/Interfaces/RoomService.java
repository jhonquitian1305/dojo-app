package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Room;

import java.util.List;

public interface RoomService {
    public RoomDTO create(RoomDTO roomDTO)throws Exception;
    public RoomDTO findById(Long id) throws  Exception;
    public RoomDTO findByName(RoomDTO roomDTO) throws NotFoundException;
    public List<RoomDTO> findAll();
    public void delete(Long id) throws Exception;
}
