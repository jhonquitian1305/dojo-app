package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.DiplomaDTOBuilder;
import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.models.Diploma;
import com.app.dojo.repositories.DiplomaRepository;
import com.app.dojo.services.implementation.DiplomaServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class DiplomaServiceTest {

    @Mock
    private DiplomaRepository diplomaRepository;

    @InjectMocks
    private DiplomaServiceImp diplomaService;

    private Diploma diploma;
    private DiplomaDTO diplomaDTO;

    @BeforeEach
    void begin(){
        diploma = new DiplomaBuilder()
                .setId(1L)
                .setDiplomaName("Certificado cinturón verde")
                .build();
        diplomaDTO = new DiplomaDTOBuilder()
                .setDiplomaName("Certificado cinturón verde")
                .build();
    }
}
