package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.StudentBuilder;
import com.app.dojo.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class StudentRepositoryTest  {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    //Formatted date
    String date = "23/11/2015";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public StudentRepositoryTest() throws ParseException {
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
    }

    @DisplayName("Test to save a student")
    @Test
    void create(){
        Student studenSaved = this.studentRepository.save(student);

        assertNotNull(studenSaved);
        assertThat(studenSaved.getId()).isGreaterThan(0);
    }
}
