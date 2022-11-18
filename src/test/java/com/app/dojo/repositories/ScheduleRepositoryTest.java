package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.models.Room;
import com.app.dojo.models.Schedule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    private static Schedule schedule;
    private static ScheduleBuilder builder= new ScheduleBuilder();

    @BeforeAll
    static void init(){
        schedule=builder
                .setId(1L)
                .setDayName("Lunes")
                .setHoursClass("8:00-10:00")
                .build();
    }

    @Test
    @DisplayName("Test Schedule Repository create a schedule")
    void create(){
        Schedule scheduleSaved=this.scheduleRepository.save(schedule);

        assertNotNull(scheduleSaved);
        assertThat(scheduleSaved.getId()).isGreaterThan(0L);
    }

    @Test
    @DisplayName("Test Schedule Repository find all schedules")
    void findAll(){
        // given
            this.scheduleRepository.save(schedule);
        // when
            List<Schedule> allSchedules=this.scheduleRepository.findAll();
        // then
            assertThat(allSchedules.size()).isGreaterThan(0);
            assertNotNull(allSchedules);
    }

    @Test
    @DisplayName("Test Schedule Repository find a schedule")
    void findOne(){
        //given
        Schedule scheduleSaved=this.scheduleRepository.save(schedule);
        //when
        Optional<Schedule> scheduleFound=this.scheduleRepository.findById(scheduleSaved.getId());
        //then
        assertFalse(scheduleFound.isEmpty());
        assertNotNull(scheduleFound.get());
        assertThat(scheduleFound.get().getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Schedule Repository delete a schedule")
    void delete(){
        //given
        Schedule scheduleSaved=this.scheduleRepository.save(schedule);
        //when
        Optional<Schedule> scheduleFound=this.scheduleRepository.findById(scheduleSaved.getId());
        //then
        this.scheduleRepository.deleteById(scheduleFound.get().getId());
        scheduleFound=this.scheduleRepository.findById(scheduleFound.get().getId());
        assertThat(scheduleFound.isEmpty()).isTrue();
    }
}