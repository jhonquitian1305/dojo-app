package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.CourseResponseBuilder;
import com.app.dojo.builders.builderModels.CourseBuilder;
import com.app.dojo.constants.Message;
import com.app.dojo.dtos.CourseDTO;
import com.app.dojo.dtos.CourseResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperCourse;
import com.app.dojo.models.*;
import com.app.dojo.repositories.CourseRepository;
import com.app.dojo.services.Interfaces.*;
import com.app.dojo.services.strategyCourses.CoursesContext;
import com.app.dojo.services.strategyCourses.CoursesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp  implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LevelService levelService;
    @Autowired
    private MapperCourse mapperCourse;
    @Autowired
    private CoursesContext coursesContext;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Override
    public Course create(CourseDTO courseDTO) throws Exception {
        if(!courseDTO.getFinishDate().after(courseDTO.getStartDate())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_DATE);
        if(courseRepository.existsCourseByName(courseDTO.getName().toUpperCase())) throw  new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_NAME);
        Level levelFound=this.levelService.getOne(courseDTO.getLevel());

        courseDTO.setTeachers(uniqueValues("teacher", courseDTO.getTeachers()));
        List<Teacher> teachersFound = this.searchTeachers(courseDTO.getTeachers());

        courseDTO.setStudents(uniqueValues("student", courseDTO.getStudents()));
        List<Student> studentsFound = this.searchStudents(courseDTO.getStudents());

       return this.courseRepository.save(
                new CourseBuilder()
                        .setPrice(courseDTO.getPrice())
                        .setName(courseDTO.getName().toUpperCase())
                        .setStartDate(courseDTO.getStartDate())
                        .setFinishDate(courseDTO.getFinishDate())
                        .setLevel(levelFound)
                        .setTeachers(teachersFound)
                        .setStudents(studentsFound)
                        .build()
        );
    }

    @Override
    public Course getOne(Long id) {
        Optional<Course> courseFound=this.courseRepository.findById(id);
        if(courseFound.isEmpty()) throw new NotFoundException(Message.MESSAGE_NOT_FOUND_BY_ID_COURSES.formatted(id));
        return courseFound.get();
    }

    @Override
    public CourseResponse getAll(int numberPage, int pageSize,String model, Long idCondition) throws Exception {
        Pageable pageable= PageRequest.of(numberPage,pageSize);
        CoursesStrategy coursesStrategy =coursesContext.loadStrategy(model.toUpperCase());
        Page<Course> coursesFound=coursesStrategy.findCourses(pageable,idCondition);

        return new CourseResponseBuilder()
                .setContent(coursesFound.getContent())
                .setLastOne(coursesFound.isLast())
                .setSizePage(coursesFound.getSize())
                .setTotalElements(coursesFound.getTotalElements())
                .setNumberPage(coursesFound.getNumber())
                .setTotalPages(coursesFound.getTotalPages())
                .build();
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) throws Exception {
        Course courseFound=getOne(id);

        if(courseRepository.existsCourseByNameAndIdNot(courseDTO.getName().toUpperCase(),id) && !courseFound.getName().equals(courseDTO.getName())) {
            throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_NAME);
        }
        if(!courseDTO.getFinishDate().after(courseDTO.getStartDate())) throw new BadRequest(Message.MESSAGE_BAD_REQUEST_COURSES_DATE);

        courseDTO.setTeachers(uniqueValues("teacher", courseDTO.getTeachers()));
        List<Teacher> teachersFound = this.searchTeachers(courseDTO.getTeachers());

        courseDTO.setStudents(uniqueValues("student", courseDTO.getStudents()));
        List<Student> studentsFound = this.searchStudents(courseDTO.getStudents());

        Level levelFound=this.levelService.getOne(courseDTO.getLevel());
        return this.courseRepository.save(this.mapperCourse.updateInformation(courseFound,courseDTO,levelFound, teachersFound, studentsFound));
    }

    @Override
    public void delete(Long id) {
        getOne(id);
        this.courseRepository.deleteById(id);
    }

    protected List<Long> uniqueValues(String model, List<Long> values){
        if(values == null || values.size() == 0) {
            throw new BadRequest(String.format("The %s field should have at least one record", model));
        }
        return values.stream().distinct().collect(Collectors.toList());
    }

    protected List<Teacher> searchTeachers(List<Long> teachers){
        List<Teacher> teachersFound = new ArrayList<>();
        for(Long teacher:teachers) teachersFound.add(this.teacherService.getById(teacher));
        return teachersFound;
    }

    protected List<Student> searchStudents(List<Long> students) throws Exception {
        List<Student> studentsFound = new ArrayList<>();
        for(Long student:students) studentsFound.add(this.studentService.getStudentById(student));
        return studentsFound;
    }
}
