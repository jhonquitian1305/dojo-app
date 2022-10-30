package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.ScheduleResponseBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.ScheduleRepository;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.app.dojo.constants.Message.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImp implements ScheduleServcie {

    @Autowired
    private  ScheduleRepository scheduleRepository;
    @Autowired
    private MapperSchedule mapperSchedule;
    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Optional<Schedule> scheduleFound=this.scheduleRepository.findByDayNameAndHoursClass(scheduleDTO.getDayName(),scheduleDTO.getHoursClass());
        if(scheduleFound.isPresent()) throw  new BadRequest(MESSAGE_BAD_REQUEST_CREATE_SCHEDULE);
        Schedule scheduleSaved=this.scheduleRepository.save(mapperSchedule.mapperSchedule(scheduleDTO));
        return mapperSchedule.mapperScheduleDTO(scheduleSaved);
    }

    @Override
    public ScheduleDTO findOne(Long id) throws NotFoundException {
        Optional<Schedule> scheduleFound=this.scheduleRepository.findById(id);
        if(scheduleFound.isEmpty()) throw  new NotFoundException(MESSAGE_NOT_FOUND_SCHEDULE_ID.formatted(id));
        return mapperSchedule.mapperScheduleDTO(scheduleFound.get());
    }

    @Override
    public ScheduleResponse findAll(int numberPage, int pageSize) {
        return null;
    }

    @Override
    public void delete(Long id) {
        this.findOne(id);
        this.scheduleRepository.deleteById(id);
    }
}
