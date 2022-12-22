package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import com.app.dojo.services.implementation.TeacherServiceImp;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherServiceImp teacherService;

    @Mock
    private MapperTeacher mapperTeacher;

    private Teacher teacher;

    private TeacherDTO teacherDTO;

    //Formatted date
    String date = "15/05/1990";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public TeacherServiceTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        teacher = new TeacherBuilder()
                .setId(1L)
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();

        teacherDTO = new TeacherDTOBuilder()
                .setId(1L)
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
    }

    @DisplayName("Test service to save a teacher")
    @Test
    void save(){
        given(this.teacherRepository.findTeacherByDni(this.teacherDTO.getDni())).willReturn(null);
        given(this.teacherRepository.findTeacherByEmail(this.teacherDTO.getEmail())).willReturn(null);
        given(this.teacherRepository.save(any(Teacher.class))).willReturn(teacher);

        given(this.mapperTeacher.mapTeacher(any(TeacherDTO.class))).willReturn(teacher);

        Teacher teacherSaved = this.teacherService.save(teacherDTO);

        assertNotNull(teacherSaved);
    }

    @DisplayName("Test service to save a teacher when the dni exists")
    @Test
    void failSaveWhenDniExists(){
        given(this.teacherRepository.findTeacherByDni(this.teacherDTO.getDni())).willReturn(teacher);

        BadRequest teacherFoundByDni = assertThrows(BadRequest.class, () -> {
            this.teacherService.save(teacherDTO);
        });

        verify(this.teacherRepository, never()).save(any(Teacher.class));
        assertEquals("This teacher already exists", teacherFoundByDni.getMessage());
    }

    @DisplayName("Test service to save a teacher when email exists")
    @Test
    void failSaveWhenEmailExists(){
        given(this.teacherRepository.findTeacherByEmail(this.teacherDTO.getEmail())).willReturn(teacher);

        BadRequest teacherFoundByEmail = assertThrows(BadRequest.class, () -> {
            this.teacherService.save(teacherDTO);
        });

        verify(this.teacherRepository, never()).save(any(Teacher.class));
        assertEquals("This email already exists", teacherFoundByEmail.getMessage());
    }
}
