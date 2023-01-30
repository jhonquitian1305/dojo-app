package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.DiplomaBuilder;
import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.DiplomaById;
import com.app.dojo.models.Diploma;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class DiplomaRepositoryTest {

    @Autowired
    DiplomaRepository diplomaRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    private Diploma diploma;
    private Student student;
    private Teacher teacher;

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public DiplomaRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        diploma = new DiplomaBuilder()
                .setId(1L)
                .setDiplomaName("Certificado cinturón verde")
                .build();
        student = new StudentBuilder()
                .setId(1L)
                .setDni("12345678")
                .setNames("Jhon")
                .setLastnames("Quitian")
                .setBirthday(formatDate)
                .setEmail("jhonquitian@mail.com")
                .setPassword("12345678")
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
    }

    @DisplayName("Test Repository to create a diploma for a student")
    @Test
    void createDiplomaStudent(){
        User studentSaved = this.studentRepository.save(student);

        diploma.setUser(studentSaved);
        Diploma diplomaSaved = this.diplomaRepository.save(diploma);

        assertNotNull(diplomaSaved);
        assertThat(diplomaSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("Test Repository to create a diploma for a teacher")
    @Test
    void createDiplomaTeacher(){
        User teacherSaved = this.teacherRepository.save(teacher);

        diploma.setUser(teacherSaved);
        Diploma diplomaSaved = this.diplomaRepository.save(diploma);

        assertNotNull(diplomaSaved);
        assertThat(diplomaSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("Test Repository to know if there is a diploma by name")
    @Test
    void existsByDiplomaName(){
        User studentSaved = this.studentRepository.save(student);
        User teacherSaved = this.teacherRepository.save(teacher);

        diploma.setUser(studentSaved);
        Diploma diplomaSavedStudent = this.diplomaRepository.save(diploma);
        diploma.setUser(teacherSaved);
        Diploma diplomaSavedTeacher = this.diplomaRepository.save(diploma);

        boolean existDiplomaStudent = this.diplomaRepository.existsByDiplomaName(diplomaSavedStudent.getDiplomaName());
        boolean existDiplomaTeacher = this.diplomaRepository.existsByDiplomaName(diplomaSavedTeacher.getDiplomaName());

        assertTrue(existDiplomaStudent);
        assertTrue(existDiplomaTeacher);
    }

    @DisplayName("Test Repository to get all diplomas of a user")
    @Test
    void getDiplomas(){
        User studentSaved = this.studentRepository.save(student);
        diploma.setUser(studentSaved);
        this.diplomaRepository.save(diploma);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Diploma> diplomasFoundStudent = this.diplomaRepository.findByUser(student, pageable);

        assertNotNull(diplomasFoundStudent);
        assertEquals(1, diplomasFoundStudent.getTotalElements());
        assertTrue(diplomasFoundStudent.isLast());
    }

    @DisplayName("Test Repository to get one diploma")
    @Test
    void getOneDiploma(){
        User teacherSaved = this.teacherRepository.save(teacher);

        diploma.setUser(teacherSaved);
        this.diplomaRepository.save(diploma);

        Optional<Diploma> diplomaFound = this.diplomaRepository.findById(diploma.getId());

        assertNotNull(diplomaFound);
    }

    @DisplayName("Test Repository to delete one diploma")
    @Test
    void deleteOneDiploma(){
        User teacherSaved = this.teacherRepository.save(teacher);

        diploma.setUser(teacherSaved);
        this.diplomaRepository.save(diploma);

        this.diplomaRepository.deleteById(diploma.getId());

        Optional<Diploma> diplomaFound = this.diplomaRepository.findById(diploma.getId());

        assertTrue(diplomaFound.isEmpty());
    }
}
