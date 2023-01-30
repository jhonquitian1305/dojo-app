package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.DiplomaDTOBuilder;
import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.DiplomaById;
import com.app.dojo.dtos.DiplomaDTO;
import com.app.dojo.dtos.DiplomaResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperDiploma;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.models.User;
import com.app.dojo.repositories.DiplomaRepository;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import com.app.dojo.services.implementation.DiplomaServiceImp;
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
    private TeacherService teacherService;

    @Mock
    private MapperDiploma mapperDiploma;

    @Mock
    private DiplomaById diplomaById;

    private Diploma diplomaStudent;
    private Diploma diplomaTeacher;
    private DiplomaDTO diplomaStudentDTO;
    private DiplomaDTO diplomaTeacherDTO;
    private Student student;
    private Teacher teacher;

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

        teacher = new TeacherBuilder()
                .setId(2L)
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
        diplomaTeacher = new DiplomaBuilder()
                .setId(2L)
                .setDiplomaName("Certificado cinturón verde")
                .setUser(teacher)
                .build();
        diplomaTeacherDTO = new DiplomaDTOBuilder()
                .setDiplomaName("Certificado cinturón verde")
                .setUser(2L)
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

    @DisplayName("Test Service to create a diploma for a teacher")
    @Test
    void createDiplomaTeacher(){
        given(this.diplomaRepository.existsByDiplomaName(anyString())).willReturn(false);
        given(this.diplomaRepository.save(any(Diploma.class))).willReturn(diplomaTeacher);
        given(this.teacherService.getById(anyLong())).willReturn(teacher);
        given(this.mapperDiploma.createDiploma(any(DiplomaDTO.class), any(Teacher.class))).willReturn(diplomaTeacher);

        Diploma diplomaSaved = this.diplomaService.saveDiplomaTeacher(anyLong(), diplomaTeacherDTO);

        assertNotNull(diplomaSaved);
    }

    @DisplayName("Test Service to create a diploma when the name exist")
    @Test
    void failSaveDiplomaWhenNameExist(){
        given(this.diplomaRepository.existsByDiplomaName(anyString())).willReturn(true);

        BadRequest diplomaFound = assertThrows(BadRequest.class, () ->{
           this.diplomaService.saveDiplomaStudent(anyLong(), diplomaStudentDTO);
        });

        assertEquals("This diploma whit name %s already exists".formatted(diplomaStudentDTO.getDiplomaName()), diplomaFound.getMessage());
    }

    @DisplayName("Test Service to get diplomas of a student")
    @Test
    void getAllDiplomasStudent() throws Exception {
        Page<Diploma> diplomas = new PageImpl<>(List.of(diplomaStudent));
        given(this.studentService.getStudentById(anyLong())).willReturn(student);
        given(this.diplomaRepository.findByUser(any(Student.class), any(Pageable.class))).willReturn(diplomas);

        DiplomaResponse diplomasFound = this.diplomaService.getDiplomasStudent(1L, 0, 10, "id", "desc");

        assertEquals(0, diplomasFound.getNumberPage());
        assertEquals(1, diplomasFound.getTotalElements());
        assertThat(diplomasFound.getContent().size()).isEqualTo(1);
    }

    @DisplayName("Test Service to get diplomas of a teacher")
    @Test
    void getAllDiplomasTeacher(){
        Page<Diploma> diplomas = new PageImpl<>(List.of(diplomaTeacher));
        given(this.teacherService.getById(anyLong())).willReturn(teacher);
        given(this.diplomaRepository.findByUser(any(Teacher.class), any(Pageable.class))).willReturn(diplomas);

        DiplomaResponse diplomasFound = this.diplomaService.getDiplomasTeacher(2L, 0, 10, "id", "desc");

        assertEquals(0, diplomasFound.getNumberPage());
        assertEquals(1, diplomasFound.getTotalElements());
        assertThat(diplomasFound.getContent().size()).isEqualTo(1);
    }

    @DisplayName("Test Service to get a diploma of a student")
    @Test
    void getOneDiplomaStudent() throws Exception {
        given(this.studentService.getStudentById(anyLong())).willReturn(student);
        given(this.diplomaRepository.findById(anyLong())).willReturn(Optional.of(diplomaStudent));
        given(this.diplomaRepository.findOneDiploma(anyLong(), anyLong())).willReturn(diplomaById);
        given(this.mapperDiploma.mapDiploma(any(DiplomaById.class))).willReturn(diplomaStudent);

        Diploma diplomaFound = this.diplomaService.getByIdDiplomaStudent(1L, 1L);

        assertNotNull(diplomaFound);
    }

    @DisplayName("Test Service to get a diploma of a teacher")
    @Test
    void getOneDiplomaTeacher(){
        given(this.teacherService.getById(anyLong())).willReturn(teacher);
        given(this.diplomaRepository.findById(anyLong())).willReturn(Optional.of(diplomaTeacher));
        given(this.diplomaRepository.findOneDiploma(anyLong(), anyLong())).willReturn(diplomaById);
        given(this.mapperDiploma.mapDiploma(any(DiplomaById.class))).willReturn(diplomaTeacher);

        DiplomaById diplomaFound = this.diplomaService.getByIdDiplomaTeacher(2L, 2L);

        assertNotNull(diplomaFound);
    }

    @DisplayName("Test Service to get a diploma when doesn't exist")
    @Test
    void failGetOneDiploma(){
        given(this.diplomaRepository.findById(anyLong())).willReturn(Optional.empty());

        NotFoundException diplomaNotFound = assertThrows(NotFoundException.class, () -> {
            this.diplomaService.getByIdDiplomaStudent(1L, 1L);
        });

        assertEquals("Diploma with id %s doesn't exist".formatted(1L), diplomaNotFound.getMessage());
    }
}
