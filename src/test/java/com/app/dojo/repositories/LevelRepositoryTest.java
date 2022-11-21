package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.models.Level;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class LevelRepositoryTest {
    @Autowired
    private LevelRepository levelRepository;
    private static Level level;
    private static LevelBuilder builder= new LevelBuilder();

    @BeforeAll()
    static  void init(){
        level=builder
                .setId(1L)
                .setName("CINTA BLANCA")
                .build();
    }

    @Test
    @DisplayName("Test Repository Level, test to create a level")
    void create(){
        Level levelSaved=this.levelRepository.save(level);

        assertNotNull(levelSaved);
        assertThat(levelSaved.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Repository level, test find a level")
    void getOne(){
        //given
        Level levelSaved=this.levelRepository.save(level);
        //when
        Optional<Level> levelFound=this.levelRepository.findById(levelSaved.getId());
        //then
        assertThat(levelFound.get()).isNotNull();
        assertTrue(levelFound.isPresent());
        assertEquals(levelSaved.getName(),levelFound.get().getName());
    }

    @Test
    @DisplayName("Test Repository level, test find a level")
    void findByName(){
        //given
        Level levelSaved=this.levelRepository.save(level);
        //when
        Optional<Level> levelFound=this.levelRepository.findByName(levelSaved.getName());
        //then
        assertThat(levelFound.get()).isNotNull();
        assertTrue(levelFound.isPresent());
        assertEquals(levelSaved.getName(),levelFound.get().getName());
    }

    @Test
    @DisplayName("Test Repository Level, find all levels")
    void getAll(){
        //given
        this.levelRepository.save(level);
        //when
        List<Level> allLevels=this.levelRepository.findAll();
        //then
        assertThat(allLevels.size()).isGreaterThan(0);
        assertNotNull(allLevels);
    }

    @Test
    @DisplayName("Test Repository Level, delete a level")
    void delete(){
        //given
        Level levelSaved=this.levelRepository.save(level);
        //when
        this.levelRepository.deleteById(levelSaved.getId());
        //then
        Optional<Level> levelFound=this.levelRepository.findById(levelSaved.getId());
        assertThat(levelFound.isEmpty()).isTrue();
    }
}