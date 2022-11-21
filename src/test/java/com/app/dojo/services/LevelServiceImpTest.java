package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperLevel;
import com.app.dojo.models.Level;
import com.app.dojo.repositories.LevelRepository;
import com.app.dojo.services.implementation.LevelServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class LevelServiceImpTest {
    @InjectMocks
    private LevelServiceImp levelService;
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

    @Test
    @DisplayName("Test LevelService, create a level")
    void create(){
        //given
        given(this.levelRepository.findByName(any(String.class))).willReturn(Optional.empty());
        given(this.levelRepository.save(any(Level.class))).willReturn(level);

        given(this.mapperLevel.mapperLevel(any(LevelDTO.class))).willReturn(level);
        given(this.mapperLevel.mapperLevelDTO(any(Level.class))).willReturn(levelDTO);
        //when
        LevelDTO levelSaved=this.levelService.create(levelDTO);
        //then
        assertNotNull(levelSaved);
        assertEquals(levelDTO.getName(),levelSaved.getName());
        assertThat(levelSaved.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test LevelService, failure when trying to save a level that is already saved")
    void failCreate(){
        //given
        given(this.levelRepository.findByName(any(String.class))).willReturn(Optional.of(level));
        //when
        BadRequest exception= assertThrows(BadRequest.class,()->{
            this.levelService.create(levelDTO);
        });
        //then
        assertEquals("There is already a saved level with that name: %s".formatted(level.getName()),exception.getMessage());
        verify(this.levelRepository,never()).save(any(Level.class));
    }
}