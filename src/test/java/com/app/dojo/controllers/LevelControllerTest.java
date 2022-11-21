package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.dtos.LevelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class LevelControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private LevelDTO levelDTO;
    private String url="http://localhost:8080/api/dojo-app/levels";

    @BeforeEach
    void init(){
        levelDTO= new LevelDTOBuilder()
                .setId(1L)
                .setName("Cinta Negra")
                .build();
    }
}