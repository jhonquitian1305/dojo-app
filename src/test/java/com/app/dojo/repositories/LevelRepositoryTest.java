package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.LevelBuilder;
import com.app.dojo.models.Level;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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

}