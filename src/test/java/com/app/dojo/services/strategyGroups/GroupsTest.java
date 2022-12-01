package com.app.dojo.services.strategyGroups;

import com.app.dojo.builders.builderModels.*;
import com.app.dojo.models.*;
import com.app.dojo.repositories.GroupClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class GroupsTest {
  @InjectMocks
  private Groups groups;
  @Mock
  private GroupClassRepository groupClassRepository;
  private GroupClass group;

  @BeforeEach()
  void init() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = format.parse("2022-06-01");
    Date finishDate = format.parse("2022-06-30");

    Room room= new RoomBuilder()
        .setRoomName("201")
        .setId(1L)
        .build();

    Schedule schedule=new ScheduleBuilder()
        .setDayName("Lunes")
        .setHoursClass("8:00-10:00")
        .setId(1L)
        .build();

    Level level=new LevelBuilder()
        .setName("CINTA NEGRA")
        .setId(1L)
        .build();

    Course course=new CourseBuilder()
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
  @DisplayName("Test GroupsStrategy, Test to get all groups without any condition")
  void findGroups(){
    //given
    Page<GroupClass> groups=new PageImpl<>(List.of(group));
    Pageable pageable= PageRequest.of(1,10);
    given(this.groupClassRepository.findAll(pageable)).willReturn(groups);
    //when
    Page<GroupClass> coursesFound=this.groups.findGroups(pageable,anyLong());
    //then
    assertAll(
        ()->assertEquals(1,coursesFound.getTotalElements()),
        ()->assertEquals(1,coursesFound.getSize()),
        ()->assertEquals(1,coursesFound.getTotalPages()),
        ()->assertEquals(0,coursesFound.getNumber()),
        ()->assertTrue(coursesFound.isLast()),
        ()->assertNotNull(coursesFound.getContent()),
        ()->assertThat(coursesFound.getContent().size()).isGreaterThan(0)
    );
  }
}