package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.models.Diploma;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class DiplomaRepositoryTest {

    @Autowired
    DiplomaRepository diplomaRepository;

    private Diploma diploma;

    @BeforeEach
    void begin(){
        diploma = new DiplomaBuilder()
                .setId(1L)
                .setDiplomaName("Certificado cinturón verde")
                .build();
    }
}
