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

}