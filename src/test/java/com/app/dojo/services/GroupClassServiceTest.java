package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.mappers.MapperGroupClass;
import com.app.dojo.models.*;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import com.app.dojo.services.implementation.GroupClassServiceImp;
import com.app.dojo.services.strategyGroups.GroupsContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class GroupClassServiceTest {
  @Mock
  private GroupClassRepository groupClassRepository;
  @Mock
  private CourseService courseService;
  @Mock
  private RoomService roomService;
  @Mock
  private ScheduleServcie scheduleServcie;
  @Mock
  private GroupsContext groupsContext;
  @Mock
  private MapperGroupClass mapperGroupClass;
  @InjectMocks
  private GroupClassServiceImp groupClassService;
  private Course course;
  private Room room;
  private Schedule schedule;
  private GroupClass group;
  private Level level;
  private GroupClassDTO groupClassDTO;

  @BeforeEach()
  void init() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");

    room= new RoomBuilder()
        .setRoomName("201")
        .setId(1L)
        .build();

    schedule=new ScheduleBuilder()
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .setId(1L)
        .build();

    level=new LevelBuilder()
        .setName("CINTA NEGRA")
        .setId(1L)
        .build();

    course=new CourseBuilder()
        .setId(1L)
        .setPrice(500000.0)
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .setLevel(level)
        .build();

    List<Room> rooms=new ArrayList<>();
    rooms.add(room);

    List<Schedule> schedules=new ArrayList<>();
    schedules.add(schedule);

    group= new GroupClassBuilder()
        .setId(1L)
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setCourse(course)
        .setRooms(rooms)
        .setSchedules(schedules)
        .build();
  }

  @Test
  @DisplayName("Test GroupClassService, test to create a group")
  void create() throws Exception {
    //given
    List<Long> roomsAndSchedulesId=new ArrayList<>();
    roomsAndSchedulesId.add(1L);
    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setCourse(1L)
        .setSchedules(roomsAndSchedulesId)
        .setRooms(roomsAndSchedulesId)
        .build();

    given(this.courseService.getOne(anyLong())).willReturn(course);
    given(this.scheduleServcie.findOne(anyLong())).willReturn(schedule);
    given(this.roomService.findById(anyLong())).willReturn(room);

    given(this.groupClassRepository.existsGroupClassByNameClass(anyString())).willReturn(false);
    given(this.groupClassRepository.existsGroupClassByRoomsAndSchedules(any(Room.class),any(Schedule.class))).willReturn(false);
    given(this.groupClassRepository.save(any(GroupClass.class))).willReturn(group);
    //when

    GroupClass groupSaved=this.groupClassService.create(groupClassDTO);

    //then
    assertAll(
        ()->assertNotNull(groupSaved),
        ()->assertNotNull(groupSaved.getCourse()),
        ()->assertThat(groupSaved.getSchedules().size()).isGreaterThan(0),
        ()->assertThat(groupSaved.getRooms().size()).isGreaterThan(0)
    );
  }
}