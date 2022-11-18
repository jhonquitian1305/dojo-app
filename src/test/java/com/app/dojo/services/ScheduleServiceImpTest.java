package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.ScheduleDTOBuilder;
import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.ScheduleRepository;
import com.app.dojo.services.implementation.ScheduleServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class ScheduleServiceImpTest {

  @InjectMocks
  private ScheduleServiceImp scheduleService;
  @Mock
  private ScheduleRepository scheduleRepository;
  @Mock
  private MapperSchedule mapperSchedule;
  private ScheduleDTO scheduleDTO;
  private Schedule schedule;
  @BeforeEach
  void init(){
    schedule = new ScheduleBuilder()
        .setId(1L)
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .build();

    scheduleDTO = new ScheduleDTOBuilder()
        .setId(1L)
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .build();
  }

  @DisplayName("Schedule Service test to create a schedule")
  @Test()
  void create(){
    //given
    given(this.scheduleRepository.save(any(Schedule.class))).willReturn(schedule);
    given(this.scheduleRepository.findByDayNameAndHoursClass(scheduleDTO.getDayName(),scheduleDTO.getHoursClass())).willReturn(Optional.empty());

    given(this.mapperSchedule.mapperScheduleDTO(any(Schedule.class))).willReturn(scheduleDTO);
    given(this.mapperSchedule.mapperSchedule(any(ScheduleDTO.class))).willReturn(schedule);
    //when
    ScheduleDTO scheduleCreated=this.scheduleService.save(scheduleDTO);
    //then
    assertNotNull(scheduleCreated);
    assertEquals(1L,scheduleCreated.getId());
    assertEquals("Lunes",scheduleCreated.getDayName());
    assertEquals("8:00-10:00",scheduleCreated.getHoursClass());
  }

  @DisplayName("Schedule Service fail to try create a schedule")
  @Test
  void failCreate(){
    //given
    given(this.scheduleRepository.findByDayNameAndHoursClass(scheduleDTO.getDayName(),scheduleDTO.getHoursClass())).willReturn(Optional.of(schedule));
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->{
      this.scheduleService.save(scheduleDTO);
    });
    //then
    assertEquals("This schedule is already created",badRequest.getMessage());
    verify(this.scheduleRepository,never()).save(any(Schedule.class));
  }

  @DisplayName("Schedule Service find one a schedule")
  @Test
  void findOne(){
    //given
    given(this.scheduleRepository.findById(any(Long.class))).willReturn(Optional.of(schedule));
    given(this.mapperSchedule.mapperScheduleDTO(any(Schedule.class))).willReturn(scheduleDTO);
    //when
    ScheduleDTO scheduleDTOFound=this.scheduleService.findOne(any(Long.class));
    //then
    assertNotNull(scheduleDTOFound);
    assertEquals(schedule.getId(), scheduleDTOFound.getId());
  }

  @DisplayName("Schedule Service fail to try find a schedule")
  @Test
  void failFindOne(){
    //given
    given(this.scheduleRepository.findById(any(Long.class))).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->{
      this.scheduleService.findOne(1L);
    });
    //then
    assertEquals("Doesn't exist a schedule with that id 1",exception.getMessage());
  }

  @DisplayName("Schedule Service find all schedules")
  @Test
  void findAll(){
    //given
    Page<Schedule> schedules=new PageImpl<>(List.of(schedule));
    given(this.scheduleRepository.findAll(any(Pageable.class))).willReturn(schedules);
    given(this.mapperSchedule.mapperScheduleDTO(any(Schedule.class))).willReturn(scheduleDTO);
    //when
    ScheduleResponse response=this.scheduleService.findAll(0,10);
    //then
    assertEquals(0,response.getNumberPage());
    assertEquals(1,response.getTotalElements());
    assertThat(response.getContent().size()).isGreaterThan(0);
  }
  @DisplayName("Schedule Service Delete a schedule")
  @Test
  void delete(){
    //given
    given(this.scheduleRepository.findById(any(Long.class))).willReturn(Optional.of(schedule));
    willDoNothing().given(scheduleRepository).deleteById(any(Long.class));
    //when
    this.scheduleService.delete(anyLong());
    //then
    verify(scheduleRepository,times(1)).deleteById(anyLong());
  }
}