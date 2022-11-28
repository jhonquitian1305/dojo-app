package com.app.dojo.services.Interfaces;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Schedule;

public interface ScheduleServcie {
    Schedule save(ScheduleDTO scheduleDTO);
    Schedule findOne(Long id) throws NotFoundException;
    ScheduleResponse findAll(int numberPage, int pageSize);
    void delete(Long id);

}
