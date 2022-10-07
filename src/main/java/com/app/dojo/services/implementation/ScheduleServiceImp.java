package com.app.dojo.services.implementation;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.ScheduleRepository;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImp implements ScheduleServcie {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MapperSchedule mapperSchedule;

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Schedule scheduleSaved= this.scheduleRepository.save(this.mapperSchedule.mapperSchedule(scheduleDTO));
        return this.mapperSchedule.mapperScheduleDTO(scheduleSaved);
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
