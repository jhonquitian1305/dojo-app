package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.mappers.MapperTeacher;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.TeacherRepository;
import com.app.dojo.services.implementation.TeacherServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
