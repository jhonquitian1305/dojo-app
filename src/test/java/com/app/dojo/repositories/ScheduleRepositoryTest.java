package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.ScheduleBuilder;
import com.app.dojo.models.Schedule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
    void create(){
        Schedule scheduleSaved=this.scheduleRepository.save(schedule);

        assertNotNull(scheduleSaved);
        assertThat(scheduleSaved.getId()).isGreaterThan(0L);
    }

}