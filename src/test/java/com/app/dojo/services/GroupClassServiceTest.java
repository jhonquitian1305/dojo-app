package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.GroupClassDTOBuilder;
import com.app.dojo.builders.builderModels.*;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperGroupClass;
import com.app.dojo.models.*;
import com.app.dojo.repositories.GroupClassRepository;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.RoomService;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import com.app.dojo.services.implementation.GroupClassServiceImp;
import com.app.dojo.services.strategyGroups.GroupsContext;
import com.app.dojo.services.strategyGroups.GroupsStrategy;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
  @Mock
  private GroupsStrategy groupsStrategy;
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

  @Test
  @DisplayName("Test GroupClassService, test to check for failure when trying to create a group with an already saved name")
  void failCreateGroupWithSimilarName(){
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
    given(this.groupClassRepository.existsGroupClassByNameClass(anyString())).willReturn(true);
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->this.groupClassService.create(groupClassDTO));
    //then
    assertEquals("There is already a group saved with that name %s".formatted(groupClassDTO.getNameClass()),badRequest.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService, test to check if there is a failure when trying to create a group with an incorrect hours per week")
  void failCreateGroupWithWrongHoursWeek(){
    //given
    List<Long> roomsAndSchedulesId=new ArrayList<>();
    roomsAndSchedulesId.add(1L);
    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(4L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setCourse(1L)
        .setSchedules(roomsAndSchedulesId)
        .setRooms(roomsAndSchedulesId)
        .build();

    given(this.scheduleServcie.findOne(anyLong())).willReturn(schedule);

    given(this.groupClassRepository.existsGroupClassByNameClass(anyString())).willReturn(false);
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->this.groupClassService.create(groupClassDTO));
    //then
    assertEquals("Hours per week do not coincide with the hours set in the schedule.",badRequest.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService, test to check if there is a failure when trying to create a group with an incorrect total hours")
  void failCreateGroupWithWrongTotalHours(){
    //given
    List<Long> roomsAndSchedulesId=new ArrayList<>();
    roomsAndSchedulesId.add(1L);
    groupClassDTO= new GroupClassDTOBuilder()
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(11L)
        .setCourse(1L)
        .setSchedules(roomsAndSchedulesId)
        .setRooms(roomsAndSchedulesId)
        .build();

    given(this.scheduleServcie.findOne(anyLong())).willReturn(schedule);
    given(this.groupClassRepository.existsGroupClassByNameClass(anyString())).willReturn(false);
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->this.groupClassService.create(groupClassDTO));
    //then
    assertEquals("Total hours do not match weeks entered and hours per week",badRequest.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService,test to check if there is a failure when trying to create a group with similar information")
  void failCreateGroupWithSimilarInformation() throws Exception {
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

    given(this.scheduleServcie.findOne(anyLong())).willReturn(schedule);
    given(this.roomService.findById(anyLong())).willReturn(room);

    given(this.groupClassRepository.existsGroupClassByNameClass(anyString())).willReturn(false);
    given(this.groupClassRepository.existsGroupClassByRoomsAndSchedules(any(Room.class),any(Schedule.class))).willReturn(true);

    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->this.groupClassService.create(groupClassDTO));
    //then
    assertEquals( "The %s room has already been assigned the %s schedule  on %s day".formatted(room.getRoomName(),schedule.getHoursClass(),schedule.getDayName()),badRequest.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService, test to find a group")
  void findOne(){
    //given
    given(this.groupClassRepository.findById(anyLong())).willReturn(Optional.of(group));
    //when
    GroupClass groupFound=this.groupClassService.getOne(anyLong());
    //then
    assertAll(
        ()->assertNotNull(groupFound),
        ()->assertNotNull(groupFound.getCourse()),
        ()-> AssertionsForClassTypes.assertThat(groupFound.getSchedules().size()).isGreaterThan(0),
        ()-> AssertionsForClassTypes.assertThat(groupFound.getRooms().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test GroupClassService, Test to check if there is a failure when trying to find a group that doesn't exist")
  void failFindOne(){
    //given
    given(this.groupClassRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException=assertThrows(NotFoundException.class,()->this.groupClassService.getOne(anyLong()));
    //then
    assertEquals("There isn't a group saved with that id %s".formatted(0),notFoundException.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService, Test to find all groups")
  void findAll() throws Exception {
    //given
    given(this.groupsContext.loadStrategy(anyString())).willReturn(groupsStrategy);
    Pageable pageable= PageRequest.of(0,1);
    Page<GroupClass> groups= new PageImpl<>(List.of(group));
    given(this.groupsStrategy.findGroups(pageable,1L)).willReturn(groups);
    //when
    GroupClassResponse groupsFound=this.groupClassService.getAll(0,1,1L,anyString());
    //then
    assertAll(
        ()->assertEquals(1,groupsFound.getSizePage()),
        ()->assertEquals(0,groupsFound.getNumberPage()),
        ()->assertEquals(1,groupsFound.getTotalElements()),
        ()->assertEquals(1,groupsFound.getTotalPages()),
        ()->assertTrue(groupsFound.isLastOne()),
        ()->assertThat(groupsFound.getContent().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test GroupClassService, Test to check if there is a failure when trying to find all groups and there are not")
  void failFindAll() throws Exception {
    //given
    given(this.groupsContext.loadStrategy(anyString())).willReturn(groupsStrategy);
    Pageable pageable= PageRequest.of(0,1);
    Page<GroupClass> groups= new PageImpl<>(List.of());
    given(this.groupsStrategy.findGroups(pageable,1L)).willReturn(groups);
    //when
    NotFoundException notFoundException=assertThrows(NotFoundException.class,()->this.groupClassService.getAll(0,1,1L,anyString()));
    //then
    assertEquals("There aren't groups saved",notFoundException.getMessage());
  }

  @Test
  @DisplayName("Test GroupClassService, Test to update a group")
  void update() throws Exception {
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
    given(this.mapperGroupClass.updateInformation(group,groupClassDTO,List.of(schedule),List.of(room),course)).willReturn(group);

    given(this.groupClassRepository.existsGroupClassByRoomsAndSchedulesAndIdNot(any(Room.class),any(Schedule.class),anyLong())).willReturn(false);
    given(this.groupClassRepository.save(any(GroupClass.class))).willReturn(group);
    given(this.groupClassRepository.findById(anyLong())).willReturn(Optional.of(group));
    //when
    GroupClass groupUpdated=this.groupClassService.update(anyLong(),groupClassDTO);
    //then
    assertAll(
        ()->assertNotNull(groupUpdated),
        ()->assertNotNull(groupUpdated.getCourse()),
        ()-> AssertionsForClassTypes.assertThat(groupUpdated.getSchedules().size()).isGreaterThan(0),
        ()-> AssertionsForClassTypes.assertThat(groupUpdated.getRooms().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test GroupClassService, Test to verify if there is a failure when trying to update a course with similar information")
  void failUpdate() throws Exception {
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

    given(this.scheduleServcie.findOne(anyLong())).willReturn(schedule);
    given(this.roomService.findById(anyLong())).willReturn(room);

    given(this.groupClassRepository.existsGroupClassByRoomsAndSchedulesAndIdNot(any(Room.class),any(Schedule.class),anyLong())).willReturn(true);
    given(this.groupClassRepository.findById(anyLong())).willReturn(Optional.of(group));
    //when
    BadRequest badRequest=assertThrows(BadRequest.class,()->this.groupClassService.update(anyLong(),groupClassDTO));
    //then
    assertEquals("The %s room has already been assigned the %s schedule  on %s day".formatted(room.getRoomName(),schedule.getHoursClass(),schedule.getDayName()),badRequest.getMessage());
  }
}