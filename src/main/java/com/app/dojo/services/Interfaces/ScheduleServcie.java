package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.NotFoundException;

public interface ScheduleServcie {
    ScheduleDTO save(ScheduleDTO scheduleDTO);
    ScheduleDTO findOne(Long id) throws NotFoundException;
    ScheduleResponse findAll();
    ScheduleDTO update(Long id, ScheduleDTO scheduleDTO);
    void delete(Long id);

}
