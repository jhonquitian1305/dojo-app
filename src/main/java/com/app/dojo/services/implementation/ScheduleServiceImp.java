package com.app.dojo.services.implementation;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImp implements ScheduleServcie {
    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public ScheduleDTO findOne(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public ScheduleResponse findAll() {
        return null;
    }

    @Override
    public ScheduleDTO update(Long id, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
