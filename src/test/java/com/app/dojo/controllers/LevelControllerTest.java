package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.LevelDTOBuilder;
import com.app.dojo.dtos.LevelDTO;
import com.app.dojo.dtos.LevelResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Order(2)
    @Test
    @DisplayName("Test LevelController, verify failure when trying to create a level that is already saved")
    void fialCreate(){
        ResponseEntity<LevelDTO> response=this.testRestTemplate.postForEntity(url, levelDTO, LevelDTO.class);
        assertEquals(400,response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Order(3)
    @Test
    @DisplayName("Test LevelController, test to find all levels")
    void getAll(){
        ResponseEntity<LevelResponse> response=this.testRestTemplate.getForEntity(url,LevelResponse.class);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @Order(4)
    @Test
    @DisplayName("Test LevelController, Test to find a level by its id")
    void findOne(){
        ResponseEntity<LevelDTO> response=this.testRestTemplate.getForEntity(url+"/1", LevelDTO.class);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertThat(response.getBody()).isNotNull();
        assertEquals(1L,response.getBody().getId());
    }

    @Order(5)
    @Test
    @DisplayName("Test LevelController, test to verify the failure when trying to find a level that doesn't exist")
    void failFindOne(){
        ResponseEntity<LevelDTO> response=this.testRestTemplate.getForEntity(url+"/1878", LevelDTO.class);
        assertEquals(404,response.getStatusCodeValue());
    }

    @Order(6)
    @Test
    @DisplayName("Test LevelController, test to delete a level by its day")
    void delete(){
        ResponseEntity<LevelResponse> response=this.testRestTemplate.getForEntity(url,LevelResponse.class);
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);

        Map<String,Long> pathVariables= new HashMap<>();
        pathVariables.put("id",1L);

        ResponseEntity<Void> exchange=this.testRestTemplate.exchange(url+"/1", HttpMethod.DELETE,null, Void.class,pathVariables);
        assertEquals(204,exchange.getStatusCodeValue());
        assertEquals(HttpStatus.NO_CONTENT,exchange.getStatusCode());
    }
}