package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.DiplomaDTOBuilder;
import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.mappers.MapperDiploma;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.DiplomaRepository;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.implementation.DiplomaServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class DiplomaServiceTest {

    @Mock
    private DiplomaRepository diplomaRepository;

    @InjectMocks
    private DiplomaServiceImp diplomaService;

    @Mock
    private StudentService studentService;

    @Mock
    private MapperDiploma mapperDiploma;

    private Diploma diplomaStudent;
    private DiplomaDTO diplomaStudentDTO;
    private Student student;

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public DiplomaServiceTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        student = new StudentBuilder()
                .setId(1L)
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
        diplomaStudent = new DiplomaBuilder()
                .setId(1L)
                .setDiplomaName("Certificado cinturón verde")
                .setUser(student)
                .build();
        diplomaStudentDTO = new DiplomaDTOBuilder()
                .setDiplomaName("Certificado cinturón verde")
                .setUser(1L)
                .build();
    }

    @DisplayName("Test Service to create a diploma for a student")
    @Test
    void createDiplomaStudent() throws Exception {
        given(this.diplomaRepository.existsByDiplomaName(anyString())).willReturn(false);
        given(this.diplomaRepository.save(any(Diploma.class))).willReturn(diplomaStudent);
        given(this.studentService.getStudentById(anyLong())).willReturn(student);
        given(this.mapperDiploma.createDiploma(any(DiplomaDTO.class), any(Student.class))).willReturn(diplomaStudent);

        Diploma diplomaSaved = this.diplomaService.saveDiplomaStudent(anyLong(), diplomaStudentDTO);

        assertNotNull(diplomaSaved);
    }
}
