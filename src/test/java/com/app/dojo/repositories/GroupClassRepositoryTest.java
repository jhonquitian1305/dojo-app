package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.*;
import com.app.dojo.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@DataJpaTest
@ActiveProfiles(profiles = "test")
class GroupClassRepositoryTest {

  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private ScheduleRepository scheduleRepository;
  @Autowired
  private GroupClassRepository groupClassRepository;
  @Autowired
  private LevelRepository levelRepository;
  private Course course;
  private Room room;
  private Schedule schedule;
  private GroupClass group;
  private Level level;

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

    group= new GroupClassBuilder()
        .setId(1L)
        .setCode("23456789")
        .setNameClass("PRINCIPIANTES 01")
        .setHoursPerWeek(2L)
        .setTotalHours(20L)
        .setWeeks(10L)
        .setId(1L)
        .build();

    course=new CourseBuilder()
        .setId(1L)
        .setPrice(500000.0)
        .setName("CINTA NEGRA PRINCIPIANTES")
        .setStartDate(startDate)
        .setFinishDate(finishDate)
        .build();
  }

  @Test
  @DisplayName("Test GroupClassRepository, Test to create a new group")
  void create(){
    //given
    Level levelSaved=this.levelRepository.save(level);
    course.setLevel(levelSaved);

    Course courseSaved=this.courseRepository.save(course);

    Schedule scheduleSaved=this.scheduleRepository.save(schedule);
    Room roomSaved=this.roomRepository.save(room);

    ArrayList<Room> rooms=new ArrayList<>();
    rooms.add(roomSaved);

    ArrayList<Schedule> schedules=new ArrayList<>();
    schedules.add(scheduleSaved);

    group.setRooms(rooms);
    group.setSchedules(schedules);
    group.setCourse(courseSaved);

    //when
    GroupClass groupSaved=this.groupClassRepository.save(group);
    //then
    assertAll(
        ()->assertNotNull(groupSaved),
        ()->assertThat(groupSaved.getId()).isGreaterThan(0),
        ()->assertThat(groupSaved.getRooms().size()).isGreaterThan(0),
        ()->assertThat(groupSaved.getSchedules().size()).isGreaterThan(0),
        ()->assertNotNull(groupSaved.getCourse())
    );
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to find one group")
  void findOne(){
    //given
    Level levelSaved=this.levelRepository.save(level);
    course.setLevel(levelSaved);

    Course courseSaved=this.courseRepository.save(course);

    Schedule scheduleSaved=this.scheduleRepository.save(schedule);
    Room roomSaved=this.roomRepository.save(room);

    ArrayList<Room> rooms=new ArrayList<>();
    rooms.add(roomSaved);

    ArrayList<Schedule> schedules=new ArrayList<>();
    schedules.add(scheduleSaved);

    group.setRooms(rooms);
    group.setSchedules(schedules);
    group.setCourse(courseSaved);

    GroupClass groupSaved=this.groupClassRepository.save(group);
    //when
    Optional<GroupClass> groupFound=this.groupClassRepository.findById(groupSaved.getId());
    //then
    assertAll(
        ()->assertTrue(groupFound.isPresent()),
        ()->assertNotNull(groupFound.get()),
        ()->assertThat(groupFound.get().getSchedules().size()).isGreaterThan(0),
        ()->assertThat(groupFound.get().getSchedules().size()).isGreaterThan(0),
        ()->assertNotNull(groupFound.get().getCourse())
    );
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to verify if a group exists by name")
  void existsGroupClassByNameClass(){
    //given
    GroupClass groupSaved=this.groupClassRepository.save(group);
    //when
    boolean hasGroup=this.groupClassRepository.existsGroupClassByNameClass(groupSaved.getNameClass());
    //then
    assertTrue(hasGroup);
  }

  @Test
  @DisplayName("Test GroupClassRepository,  test to check for false response when trying to verify if a group exists by name")
  void notExistsGroupClassByNameClass(){
    //given
    GroupClass groupSaved=this.groupClassRepository.save(group);
    //when
    boolean hasGroup=this.groupClassRepository.existsGroupClassByNameClass("");
    //then
    assertFalse(hasGroup);
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to verify if a group exists by room and schedule")
  void existsGroupClassByRoomsAndSchedules(){
    //given
    Level levelSaved=this.levelRepository.save(level);
    course.setLevel(levelSaved);
    Course courseSaved=this.courseRepository.save(course);

    Schedule scheduleSaved=this.scheduleRepository.save(schedule);
    Room roomSaved=this.roomRepository.save(room);

    ArrayList<Room> rooms=new ArrayList<>();
    rooms.add(roomSaved);

    ArrayList<Schedule> schedules=new ArrayList<>();
    schedules.add(scheduleSaved);

    group.setRooms(rooms);
    group.setSchedules(schedules);
    group.setCourse(courseSaved);

    this.groupClassRepository.save(group);
    //when
    boolean hasGroup=this.groupClassRepository.existsGroupClassByRoomsAndSchedules(roomSaved,scheduleSaved);
    //then
    assertTrue(hasGroup);
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to check for false response when trying to verify if a group exists by schedule and room")
  void notExistsGroupClassByRoomsAndSchedules(){
    //given
    this.groupClassRepository.save(group);
    room.setId(2L);
    //when
    boolean hasGroup=this.groupClassRepository.existsGroupClassByRoomsAndSchedules(room,schedule);
    //then
    assertFalse(hasGroup);
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to verify if a group exists by room , schedule and except id ")
  void existsGroupClassByRoomsAndSchedulesAndIdNot(){
    //given
    this.groupClassRepository.save(group);
    GroupClass groupSaved=this.groupClassRepository.save(group);
    //when
    boolean hasGroup=this.groupClassRepository.existsGroupClassByRoomsAndSchedulesAndIdNot(room,schedule,groupSaved.getId());
    //then
    assertFalse(hasGroup);
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to find all groups")
  void findAll(){
    //given
    this.groupClassRepository.save(group);
    //when
    List<GroupClass> groupsFound=this.groupClassRepository.findAll();
    //then
    assertNotNull(groupsFound);
    assertThat(groupsFound.size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to find groups by course")
  void findByCourse(){
    //given
    Course courseSaved=this.courseRepository.save(course);
    group.setCourse(courseSaved);
    this.groupClassRepository.save(group);
    //when
    Pageable pageable= PageRequest.of(0,10);
    Page<GroupClass> groups=this.groupClassRepository.findByCourse(courseSaved,pageable);
    //then
    assertAll(
        ()->assertThat(groups.getContent().size()).isGreaterThan(0),
        ()->assertEquals(1,groups.getTotalElements()),
        ()->assertEquals(1,groups.getTotalPages()),
        ()->assertTrue(groups.isLast())
    );
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to find groups by rooms")
  void findByRoom(){
    //given
    Room roomSaved=this.roomRepository.save(room);

    List<Room> rooms=new ArrayList<>();
    rooms.add(roomSaved);
    group.setRooms(rooms);

    this.groupClassRepository.save(group);

    //when
    Pageable pageable=PageRequest.of(0,10);
    Page<GroupClass> groups=this.groupClassRepository.findByRooms(roomSaved,pageable);
    //then
    assertAll(
        ()->assertThat(groups.getContent().size()).isGreaterThan(0),
        ()->assertEquals(1,groups.getTotalElements()),
        ()->assertEquals(1,groups.getTotalPages()),
        ()->assertTrue(groups.isLast())
    );
  }

  @Test
  @DisplayName("Test GroupClassRepository, test to find groups by schedule")
  void findBySchedule(){
    //given
    Schedule scheduleSaved=this.scheduleRepository.save(schedule);

    List<Schedule> schedules=new ArrayList<>();
    schedules.add(scheduleSaved);
    group.setSchedules(schedules);

    this.groupClassRepository.save(group);

    //when
    Pageable pageable=PageRequest.of(0,10);
    Page<GroupClass> groups=this.groupClassRepository.findBySchedules(scheduleSaved,pageable);
    //then
    assertAll(
        ()->assertThat(groups.getContent().size()).isGreaterThan(0),
        ()->assertEquals(1,groups.getTotalElements()),
        ()->assertEquals(1,groups.getTotalPages()),
        ()->assertTrue(groups.isLast())
    );
  }

  @Test
  @DisplayName("Test GroupClassRepository, Test to delete a group")
  void delete(){
    //given
    GroupClass groupSaved=this.groupClassRepository.save(group);
    //when
    this.groupClassRepository.deleteById(groupSaved.getId());
    //then
    Optional<GroupClass> groupFound=this.groupClassRepository.findById(groupSaved.getId());
    assertTrue(groupFound.isEmpty());
  }
}
