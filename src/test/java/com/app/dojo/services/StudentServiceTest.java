package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.StudentDTOBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.dtos.StudentDTO;
import com.app.dojo.dtos.StudentResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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
                .setId(1L)
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
                .build();

        studentDTO = new StudentDTOBuilder()
                .setId(1L)
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

        given(this.mapperStudent.mapStudent(any(StudentDTO.class), studentDTO.getPassword())).willReturn(student);

        Student studentSaved = this.studentService.saveStudent(this.studentDTO);

        assertNotNull(studentSaved);
    }

    @DisplayName("Test service to save a student when the dni exists")
    @Test
    void failSaveWhenDniExists(){
        given(this.studentRepository.findStudentByDni(studentDTO.getDni())).willReturn(student);

        assertThrows(BadRequest.class, () -> {
            this.studentService.saveStudent(studentDTO);
        });

        verify(this.studentRepository, never()).save(any(Student.class));
    }

    @DisplayName("Test service to save a student when the email exists")
    @Test
    void failSaveWhenEmailExists(){
        given(this.studentRepository.findStudentByEmail(studentDTO.getEmail())).willReturn(student);

        assertThrows(BadRequest.class, () -> {
            this.studentService.saveStudent(studentDTO);
        });

        verify(this.studentRepository, never()).save(any(Student.class));
    }

    @DisplayName("Test service to get all students")
    @Test
    void getAll(){
        Student student1 = new StudentBuilder()
                .setDni("135792468")
                .setNames("Jairo")
                .setLastnames("Montoya")
                .setBirthday(formatDate)
                .setEmail("jairomontoya@mail.com")
                .setPassword("135792468")
                .build();
        Page<Student> students = new PageImpl<>(List.of(student, student1));
        given(this.studentRepository.findAll(any(Pageable.class))).willReturn(students);
        given(this.mapperStudent.mapStudentDTO(any(Student.class))).willReturn(studentDTO);

        StudentResponse studentResponse = this.studentService.getAllStudents(0, 10, "id", "desc");

        assertEquals(0, studentResponse.getNumberPage());
        assertEquals(2, studentResponse.getTotalElements());
        assertThat(studentResponse.getContent().size()).isEqualTo(2);
    }

    @DisplayName("Test service to get a student by id, dni and email")
    @Test
    void getOne(){
        given(this.studentRepository.findById(anyLong())).willReturn(Optional.of(student));
        given(this.studentRepository.findStudentByDni(studentDTO.getDni())).willReturn(student);
        given(this.studentRepository.findStudentByEmail(studentDTO.getEmail())).willReturn(student);


        Student studentGetById = this.studentService.getStudentById(1L);

        Student studentGetByDni = this.studentService.getStudentByDni(studentDTO);

        Student studentGetByEmail = this.studentService.getStudentByEmail(studentDTO);

        assertNotNull(studentGetById);
        assertNotNull(studentGetByDni);
        assertNotNull(studentGetByEmail);
        assertEquals(1L, studentGetById.getId());
        assertEquals(1L, studentGetByDni.getId());
        assertEquals(1L, studentGetByEmail.getId());
    }

    @DisplayName("Test service to get a student when doesn't exists")
    @Test
    void failGetOne(){
        given(this.studentRepository.findById(anyLong())).willReturn(Optional.empty());
        given(this.studentRepository.findStudentByDni(studentDTO.getDni())).willReturn(null);
        given(this.studentRepository.findStudentByEmail(studentDTO.getEmail())).willReturn(null);

        NotFoundException studentNotFoundById = assertThrows(NotFoundException.class, () -> {
            this.studentService.getStudentById(1L);
        });

        NotFoundException studentNotFoundByDni = assertThrows(NotFoundException.class, () -> {
            this.studentService.getStudentByDni(studentDTO);
        });

        NotFoundException studentNotFoundByEmail = assertThrows(NotFoundException.class, () -> {
            this.studentService.getStudentByEmail(studentDTO);
        });

        assertEquals("Student with id %s doesn't exists".formatted(1L), studentNotFoundById.getMessage());
        assertEquals("Student with dni %s doesn't exists".formatted(studentDTO.getDni()), studentNotFoundByDni.getMessage());
        assertEquals("Student with email %s doesn't exists".formatted(studentDTO.getEmail()), studentNotFoundByEmail.getMessage());
    }

    @DisplayName("Test service to delete a student")
    @Test
    void deleteOne() throws Exception {
        given(this.studentRepository.findById(anyLong())).willReturn(Optional.of(student));
        willDoNothing().given(this.studentRepository).deleteById(anyLong());

        this.studentService.deleteStudent(anyLong());

        verify(this.studentRepository, times(1)).deleteById(anyLong());
    }

    @DisplayName("Test service to delete a student when doesn't exists")
    @Test
    void failDeleteOne(){
        given(this.studentRepository.findById(anyLong())).willReturn(Optional.empty());

        NotFoundException studentNotFound = assertThrows(NotFoundException.class, () -> {
            this.studentService.getStudentById(1L);
        });

        assertEquals("Student with id %s doesn't exists".formatted(1L), studentNotFound.getMessage());
    }
}
