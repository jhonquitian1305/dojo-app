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
    public Schedule save(ScheduleDTO scheduleDTO) {
        Optional<Schedule> scheduleFound=this.scheduleRepository.findByDayNameAndHoursClass(scheduleDTO.getDayName(),scheduleDTO.getHoursClass());
        if(scheduleFound.isPresent()) throw  new BadRequest(MESSAGE_BAD_REQUEST_CREATE_SCHEDULE);
        return this.scheduleRepository.save(mapperSchedule.mapperSchedule(scheduleDTO));
    }

    @Override
    public Schedule findOne(Long id) throws NotFoundException {
        Optional<Schedule> scheduleFound=this.scheduleRepository.findById(id);
        if(scheduleFound.isEmpty()) throw  new NotFoundException(MESSAGE_NOT_FOUND_SCHEDULE_ID.formatted(id));
        return scheduleFound.get();
    }

    @Override
    public ScheduleResponse findAll(int numberPage, int pageSize) {
        Pageable pagination=PageRequest.of(numberPage,pageSize);
        Page<Schedule> allSchedules=this.scheduleRepository.findAll(pagination);
        List<Schedule> schedulesFound=allSchedules.getContent();
        List<ScheduleDTO> schedules=schedulesFound.stream().map(schedule->mapperSchedule.mapperScheduleDTO(schedule)).collect(Collectors.toList());
        return  new ScheduleResponseBuilder()
                .setContent(schedules)
                .setNumberPage(allSchedules.getNumber())
                .setSizePage(allSchedules.getSize())
                .setLastOne(allSchedules.isLast())
                .setTotalElements(allSchedules.getTotalElements())
                .setTotalPages(allSchedules.getTotalPages())
                .build();
    }

    @Override
    public void delete(Long id) {
        this.findOne(id);
        this.scheduleRepository.deleteById(id);
    }
}
