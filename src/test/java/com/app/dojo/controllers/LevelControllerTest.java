package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.dtos.LevelDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Order(1)
    @Test
    @DisplayName("Test LevelController, create a level")
    void create(){
        ResponseEntity<LevelDTO> response=this.testRestTemplate.postForEntity(url,levelDTO,LevelDTO.class);
        assertEquals(201,response.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
    }
}