package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.mappers.MapperLevel;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.LevelRepository;
import com.app.dojo.services.implementation.ScheduleServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class LevelServiceImpTest {
    @InjectMocks
    private ScheduleServiceImp scheduleServiceImp;
    @Mock
    private LevelRepository levelRepository;
    @Mock
    private MapperLevel mapperLevel;
    private Level level;
    private LevelDTO levelDTO;

    @BeforeEach()
    void init(){
        level= new LevelBuilder()
                .setId(1L)
                .setName("CINTA BLANCA")
                .build();
        levelDTO= new LevelDTOBuilder()
                .setId(1L)
                .setName("CINTA BLANCA")
                .build();
    }
}