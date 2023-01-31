package com.app.dojo.controllers;

import com.app.dojo.builders.builderDTO.DiplomaDTOBuilder;
import com.app.dojo.dtos.DiplomaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class DiplomaControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private DiplomaDTO diplomaDTO;

    @BeforeEach
    void begin(){
        diplomaDTO = new DiplomaDTOBuilder()
                .setDiplomaName("Certificado cinturón verde")
                .build();
    }
}
