package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.dtos.TeacherResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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

    @DisplayName("Test service to get all teachers")
    @Test
    void getAll(){
        Teacher teacher1 = new TeacherBuilder()
                .setDni("864297531")
                .setNames("Carlos")
                .setLastnames("Andrade")
                .setBirthday(formatDate)
                .setEmail("carlosandrade@mail.com")
                .setPassword("864297531")
                .build();
        Page<Teacher> teachers = new PageImpl<>(List.of(teacher, teacher1));
        given(this.teacherRepository.findAll(any(Pageable.class))).willReturn(teachers);
        given(this.mapperTeacher.mapTeacherDTO(any(Teacher.class))).willReturn(teacherDTO);

        TeacherResponse teacherResponse = this.teacherService.getAll(0, 10, "id", "desc");

        assertEquals(0, teacherResponse.getNumberPage());
        assertEquals(2, teacherResponse.getTotalElements());
        assertThat(teacherResponse.getContent().size()).isEqualTo(2);
    }

    @DisplayName("Test service to get a teacher by id, dni and email")
    @Test
    void getOne(){
        given(this.teacherRepository.findById(anyLong())).willReturn(Optional.of(teacher));
        given(this.teacherRepository.findTeacherByDni(this.teacherDTO.getDni())).willReturn(teacher);
        given(this.teacherRepository.findTeacherByEmail(this.teacherDTO.getEmail())).willReturn(teacher);

        Teacher teacherFoundById = this.teacherService.getById(1L);

        Teacher teacherFoundByDni = this.teacherService.getByDni(teacherDTO);

        Teacher teacherFoundByEmail = this.teacherService.getByEmail(teacherDTO);

        assertNotNull(teacherFoundById);
        assertNotNull(teacherFoundByDni);
        assertNotNull(teacherFoundByEmail);
        assertEquals(1L, teacherFoundById.getId());
        assertEquals(1L, teacherFoundByDni.getId());
        assertEquals(1L, teacherFoundByEmail.getId());
    }

    @DisplayName("Test service to get a teacher when doesn't exists")
    @Test
    void failGetOne(){
        given(this.teacherRepository.findById(anyLong())).willReturn(Optional.empty());
        given(this.teacherRepository.findTeacherByDni(this.teacherDTO.getDni())).willReturn(null);
        given(this.teacherRepository.findTeacherByEmail(this.teacherDTO.getEmail())).willReturn(null);

        NotFoundException teacherNotFoundById = assertThrows(NotFoundException.class, () -> {
            this.teacherService.getById(1L);
        });

        NotFoundException teacherNotFoundByDni = assertThrows(NotFoundException.class, () -> {
            this.teacherService.getByDni(this.teacherDTO);
        });

        NotFoundException teacherNotFoundByEmail = assertThrows(NotFoundException.class, () -> {
            this.teacherService.getByEmail(this.teacherDTO);
        });

        assertEquals("Teacher with id %s doesn't exists".formatted( 1L), teacherNotFoundById.getMessage());
        assertEquals("Teacher with dni %s doesn't exists".formatted(this.teacherDTO.getDni()), teacherNotFoundByDni.getMessage());
        assertEquals("Teacher with email %s doesn't exists".formatted(this.teacherDTO.getEmail()), teacherNotFoundByEmail.getMessage());
    }

    @DisplayName("Test service to update a teacher")
    @Test
    void updateOne(){
        given(this.teacherRepository.save(teacher)).willReturn(teacher);
        given(this.teacherRepository.findById(anyLong())).willReturn(Optional.of(teacher));
        teacherDTO.setDni("1984168878");
        teacherDTO.setNames("Ramiro");

        Teacher teacherUpdated = this.teacherService.updateOne(1L, teacherDTO);

        assertThat(teacherUpdated.getDni()).isEqualTo("1984168878");
        assertThat(teacherUpdated.getNames()).isEqualTo("Ramiro");
    }

    @DisplayName("Test service to delete a teacher")
    @Test
    void deleteOne(){
        given(this.teacherRepository.findById(anyLong())).willReturn(Optional.of(this.teacher));
        willDoNothing().given(this.teacherRepository).delete(this.teacher);

        this.teacherService.deleteOne(anyLong());

        verify(this.teacherRepository, times(1)).delete(this.teacher);
    }
}
