package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Test LevelService, find all levels")
    void getAll(){
        //given
        Page<Level> levels= new PageImpl<>(List.of(level));
        given(this.levelRepository.findAll(any(Pageable.class))).willReturn(levels);
        given(this.mapperLevel.mapperLevelDTO(any(Level.class))).willReturn(levelDTO);
        //when
        LevelResponse response=this.levelService.getAll(0,10);
        //then
        assertEquals(0,response.getNumberPage());
        assertEquals(1,response.getTotalElements());
        assertThat(response.getContent().size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test LevelService, test to find a level")
    void getOne(){
        //given
        given(this.levelRepository.findById(anyLong())).willReturn(Optional.of(level));
        given(this.mapperLevel.mapperLevelDTO(any(Level.class))).willReturn(levelDTO);
        //when
        LevelDTO levelFound=this.levelService.getOne(level.getId());
        // then
        assertNotNull(levelFound);
        assertEquals(level.getId(),levelFound.getId());
        assertEquals(level.getName(),levelFound.getName());
    }

    @Test
    @DisplayName("Test LevelService, Test to verify failure when trying to find a level that doesn't exist")
    void failFindOne(){
        //given
        given(this.levelRepository.findById(anyLong())).willReturn(Optional.empty());
        //when
        NotFoundException exception=assertThrows(NotFoundException.class,()->{
           this.levelService.getOne(1L);
        });
        //then
        assertEquals("There is no level with this identification %s".formatted(1L),exception.getMessage());
    }

    @Test
    @DisplayName("Test LevelService, delete a level")
    void delete(){
        //given
        given(this.levelRepository.findById(anyLong())).willReturn(Optional.of(level));
        given(this.mapperLevel.mapperLevelDTO(any(Level.class))).willReturn(levelDTO);
        willDoNothing().given(this.levelRepository).deleteById(anyLong());
        //when
        this.levelService.delete(anyLong());
        //then
        verify(levelRepository,times(1)).deleteById(anyLong());
    }
}