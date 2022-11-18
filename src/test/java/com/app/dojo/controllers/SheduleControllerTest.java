package com.app.dojo.controllers;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.ScheduleDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class SheduleControllerTest {
  @Autowired
  private TestRestTemplate testRestTemplate;
  private ScheduleDTO scheduleDTO;
  private String url="http://localhost:8080/api/dojo-app/rooms";
}