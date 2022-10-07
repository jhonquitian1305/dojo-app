package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.dtos.RoomDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

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
}