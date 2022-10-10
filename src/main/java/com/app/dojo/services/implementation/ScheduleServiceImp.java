package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.ScheduleResponseBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleRequest;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.ScheduleRepository;
import com.app.dojo.services.Interfaces.DayServcie;
import com.app.dojo.services.Interfaces.HourService;
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
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MapperSchedule mapperSchedule;

    @Autowired
    private DayServcie dayServcie;

    @Autowired
    private HourService hourService;

    @Override
    public ScheduleDTO save(ScheduleRequest scheduleRequest) {
        List<Day> days=loadDays(scheduleRequest.getDays());
        List<Hour> hours=loadHours(scheduleRequest.getHours());
        verifySchedule(days,hours);
        Schedule scheduleToSave=new ScheduleBuilder()
                .setDays(days)
                .setHours(hours)
                .build();
        Schedule scheduleSaved= this.scheduleRepository.save(scheduleToSave);
        return this.mapperSchedule.mapperScheduleDTO(scheduleSaved);
    }

    @Override
    public ScheduleDTO findOne(Long id) throws NotFoundException {
        Optional<Schedule> scheduleFound=this.scheduleRepository.findById(id);
        if(scheduleFound.isEmpty()){
            throw  new NotFoundException(MESSAGE_NOT_FOUND_SCHEDULE_ID.formatted(id));
        }
        return mapperSchedule.mapperScheduleDTO(scheduleFound.get());
    }

    @Override
    public ScheduleResponse findAll(int numberPage, int pageSize) {
        Pageable pageable= PageRequest.of(numberPage,pageSize);
        Page<Schedule> schedulesFound=this.scheduleRepository.findAll(pageable);
        List<ScheduleDTO> allSchedules=schedulesFound.getContent()
                .stream()
                .map(schedule -> this.mapperSchedule.mapperScheduleDTO(schedule))
                .collect(Collectors.toList());
        return new ScheduleResponseBuilder()
                .setContent(allSchedules)
                .setNumberPage(schedulesFound.getNumber())
                .setSizePage(schedulesFound.getSize())
                .setTotalElements(schedulesFound.getTotalElements())
                .setTotalPages(schedulesFound.getTotalPages())
                .setLastOne(schedulesFound.isLast())
                .build();
    }

    @Override
    public ScheduleDTO update(Long id, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private List<Day> loadDays(List<String> days){
        return days.stream().map(day->this.dayServcie.findByName(day)).collect(Collectors.toList());
    }

    private List<Hour> loadHours(List<String> hours){
        return  hours.stream().map(hour->this.hourService.findByName(hour)).collect(Collectors.toList());
    }

    private void verifySchedule(List<Day> days, List<Hour> hours){
        days.stream().forEach(day->{
            hours.stream().forEach(hour -> {
                Boolean isExist=this.scheduleRepository.existsScheduleByDaysAndHours(day,hour);
                if(isExist) throw new BadRequest(MESSAGE_BAD_REQUEST_CREATE_SCHEDULE.formatted(hour.getHour(),day.getDay()));
            });
        });
    }
}
