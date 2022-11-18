package com.app.dojo.services;

import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.models.Schedule;
import com.app.dojo.repositories.ScheduleRepository;
import com.app.dojo.services.implementation.ScheduleServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImpTest {

  @InjectMocks
  private ScheduleServiceImp scheduleService;
  @Mock
  private ScheduleRepository scheduleRepository;
  @Mock
  private MapperSchedule mapperSchedule;
  private ScheduleDTO scheduleDTO;
  private Schedule schedule;

}