package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class RoomControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private RoomDTO roomDTO;
    private String url="http://localhost:8080/api/dojo-app/rooms";

    @BeforeEach
    void init(){
        roomDTO= new RoomDTOBuilder()
                .setRoomName("202")
                .setId(1L)
                .build();
    }

    @Order(1)
    @Test
    @DisplayName("Test Controller, create a room")
    void create() throws Exception {
        ResponseEntity<RoomDTO> response=testRestTemplate.postForEntity(url,roomDTO,RoomDTO.class);
        assertEquals(201,response.getStatusCodeValue());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertNotNull(response.getBody());
    }
    @Order(2)
    @Test
    void failCreate(){
        ResponseEntity<RoomDTO> response=testRestTemplate.postForEntity(url,roomDTO,RoomDTO.class);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    @Order(3)
    void findById(){
        ResponseEntity<RoomDTO> response=this.testRestTemplate.getForEntity(url+"/1",RoomDTO.class);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertThat(response.getBody()).isNotNull();
        assertEquals(1L,response.getBody().getId());
    }

    @Test
    @Order(4)
    void failFindById(){
        ResponseEntity<RoomDTO> response=this.testRestTemplate.getForEntity(url+"/2",RoomDTO.class);
        assertEquals(404,response.getStatusCodeValue());
    }

    @Test
    @Order(5)
    void findAll(){
        ResponseEntity<RoomResponse> response=this.testRestTemplate.getForEntity(url,RoomResponse.class);
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);
    }

    @Test
    void delete() throws Exception{
        ResponseEntity<RoomResponse> response=testRestTemplate.getForEntity(url,RoomResponse.class);
        assertThat(response.getBody().getContent().size()).isGreaterThan(0);

        Map<String,Long> pathVariables= new HashMap<>();
        pathVariables.put("id",1L);

        ResponseEntity<Void> exchange=testRestTemplate.exchange(url+"/1", HttpMethod.DELETE,null,Void.class,pathVariables);

        assertEquals(HttpStatus.NO_CONTENT,exchange.getStatusCode());
        assertEquals(204,exchange.getStatusCodeValue());
    }
}