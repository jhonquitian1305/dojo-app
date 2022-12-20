package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher teacher;

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public TeacherRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        teacher = new TeacherBuilder()
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
    }
}
