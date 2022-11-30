package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.mappers.MapperStudent;
import com.app.dojo.models.Student;
import com.app.dojo.repositories.StudentRepository;
import com.app.dojo.services.implementation.StudentServiceImp;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImp studentService;

    @Mock
    private MapperStudent mapperStudent;

    private Student student;

    private StudentDTO studentDTO;

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public StudentServiceTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        student = new StudentBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();

        studentDTO = new StudentDTOBuilder()
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();
    }

    @DisplayName("Test service to save a student")
    @Test
    void save(){
        given(this.studentRepository.findStudentByDni(this.studentDTO.getDni())).willReturn(null);
        given(this.studentRepository.findStudentByEmail(this.studentDTO.getEmail())).willReturn(null);
        given(this.studentRepository.save(any(Student.class))).willReturn(student);

        given(this.mapperStudent.mapStudent(any(StudentDTO.class))).willReturn(student);

        StudentDTO studentSaved = this.studentService.saveStudent(this.studentDTO);

        assertNotNull(studentSaved);
    }
}
